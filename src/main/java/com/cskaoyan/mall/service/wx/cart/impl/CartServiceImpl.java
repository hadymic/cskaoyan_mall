package com.cskaoyan.mall.service.wx.cart.impl;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.wx.cart.CartService;
import com.cskaoyan.mall.vo.wx.cart.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsProductMapper goodsProductMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private GrouponRulesMapper grouponRulesMapper;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private SystemMapper systemMapper;

    @Override
    public CartListVo cartList(int id) {
        CartListVo vo = new CartListVo();
        List<Cart> carts = cartMapper.queryByUserId(id, false);
        vo.setCartList(carts);
        BigDecimal checkedGoodsAmount = BigDecimal.ZERO;
        BigDecimal checkedGoodsCount = BigDecimal.ZERO;
        BigDecimal goodsAmount = BigDecimal.ZERO;
        BigDecimal goodsCount = BigDecimal.ZERO;
        for (Cart cart : carts) {
            BigDecimal num = new BigDecimal(cart.getNumber());
            BigDecimal totalPrice = cart.getPrice().multiply(num);
            if (cart.getChecked()) {
                checkedGoodsAmount = checkedGoodsAmount.add(totalPrice);
                checkedGoodsCount = checkedGoodsCount.add(num);
            }
            goodsAmount = goodsAmount.add(totalPrice);
            goodsCount = goodsCount.add(num);
        }
        vo.setCartTotal(new CartTotal(checkedGoodsAmount, checkedGoodsCount, goodsAmount, goodsCount));
        return vo;
    }

    @Override
    public String addCart(CartAddVo vo, int userId) {
        // 判断商品是否还有库存
        GoodsProduct product = goodsProductMapper.selectByPrimaryKey(vo.getProductId());
        if (product.getNumber() <= 0) {
            return "商品太抢手啦！库存已空哦！";
        }
        //判断数据库中是否已有该商品
        Cart cartFromDb = cartMapper.queryByProductId(userId, vo.getProductId());
        if (cartFromDb == null) {
            //插入数据库
            Goods goods = goodsMapper.selectByPrimaryKey(vo.getGoodsId());
            Date date = new Date();
            Cart cart = new Cart(null, userId, vo.getGoodsId(), goods.getGoodsSn(), goods.getName(),
                    vo.getProductId(), product.getPrice(), vo.getNumber(), product.getSpecifications(),
                    true, goods.getPicUrl(), date, date, false);
            cartMapper.insert(cart);
        } else {
            // 数量相加
            cartFromDb.setNumber((short) (cartFromDb.getNumber() + vo.getNumber()));
            cartMapper.updateByPrimaryKey(cartFromDb);
        }
        return null;
    }

    @Override
    public boolean checkedCart(int userId, CartCheckedVo vo) {
        List<Integer> productIds = vo.getProductIds();
        Date date = new Date();
        for (Integer productId : productIds) {
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setChecked(vo.getIsChecked());
            cart.setProductId(productId);
            cart.setUpdateTime(date);
            cartMapper.updateByProductIdSelective(cart);
        }
        return true;
    }

    @Override
    public BigDecimal goodsCount(int userId) {
        //从数据库查询商品总数
        List<Cart> carts = cartMapper.queryByUserId(userId, false);
        BigDecimal goodsCount = BigDecimal.ZERO;
        for (Cart cart1 : carts) {
            BigDecimal num = new BigDecimal(cart1.getNumber());
            goodsCount = goodsCount.add(num);
        }
        return goodsCount;
    }

    @Override
    public int fastAdd(CartAddVo vo, int userId) {
        // 判断商品是否还有库存
        GoodsProduct product = goodsProductMapper.selectByPrimaryKey(vo.getProductId());
        if (product.getNumber() <= 0) {
            return -1;
        }
        //判断数据库中是否已有该商品
        Cart cartFromDb = cartMapper.queryByProductId(userId, vo.getProductId());
        if (cartFromDb == null) {
            //插入数据库
            Goods goods = goodsMapper.selectByPrimaryKey(vo.getGoodsId());
            Date date = new Date();
            Cart cart = new Cart(null, userId, vo.getGoodsId(), goods.getGoodsSn(), goods.getName(),
                    vo.getProductId(), product.getPrice(), vo.getNumber(), product.getSpecifications(),
                    true, goods.getPicUrl(), date, date, false);
            cartMapper.insert(cart);
            return cart.getId();
        } else {
            //数量直接赋值
            cartFromDb.setNumber(vo.getNumber());
            cartMapper.updateByPrimaryKey(cartFromDb);
            return cartFromDb.getId();
        }
    }

    @Override
    public CartCheckoutReturnVo checkout(CartCheckoutVo vo) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        CartCheckoutReturnVo returnVo = new CartCheckoutReturnVo();

        //商品总价
        BigDecimal goodsTotalPrice = BigDecimal.ZERO;
        if (vo.getCartId() != 0) {
            Cart cart = cartMapper.selectByPrimaryKey(vo.getCartId());
            List<Cart> carts = new ArrayList<>();
            carts.add(cart);
            returnVo.setCheckedGoodsList(carts);
            goodsTotalPrice = cart.getPrice().multiply(new BigDecimal(cart.getNumber()));
        } else {
            List<Cart> carts = cartMapper.queryByUserId(userId, true);
            returnVo.setCheckedGoodsList(carts);
            for (Cart cart : carts) {
                goodsTotalPrice = goodsTotalPrice.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            }
        }
        returnVo.setGoodsTotalPrice(goodsTotalPrice);

        //地址
        Address address;
        if (vo.getAddressId() != 0) {
            address = addressMapper.selectByPrimaryKey(vo.getAddressId());
        } else {
            address = addressMapper.selectDefaultAddressByUserId(userId);
        }
        returnVo.setCheckedAddress(address);
        returnVo.setAddressId(address.getId());

        //团购优惠价格
        BigDecimal grouponPrice = BigDecimal.ZERO;
        if (vo.getGrouponRulesId() != 0) {
            GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey(vo.getGrouponRulesId());
            returnVo.setGrouponRulesId(grouponRules.getId());
            grouponPrice = grouponRules.getDiscount();
        }
        returnVo.setGrouponPrice(grouponPrice);

        //优惠券的价格
        BigDecimal couponPrice = BigDecimal.ZERO;
        if (vo.getCouponId() != 0 && vo.getCouponId() != -1) {
            Coupon coupon = couponMapper.selectByPrimaryKey(vo.getCouponId());
            returnVo.setCouponId(coupon.getId());
            couponPrice = coupon.getDiscount();
        }
        returnVo.setCouponPrice(couponPrice);

        //订单总价
        BigDecimal orderTotalPrice = BigDecimal.ZERO;
        returnVo.setOrderTotalPrice(orderTotalPrice);

        //快递费
        BigDecimal freightPrice = BigDecimal.ZERO;
        BigDecimal freightMinPrice = BigDecimal.valueOf(Double.parseDouble(systemMapper.selectByKeyName("cskaoyan_mall_express_freight_min")));
        if (goodsTotalPrice.compareTo(freightMinPrice) < 0) {
            freightPrice = BigDecimal.valueOf(Double.parseDouble(systemMapper.selectByKeyName("cskaoyan_mall_express_freight_value")));
        }
        returnVo.setFreightPrice(freightPrice);
        //实际需要支付的总价
        returnVo.setActualPrice(goodsTotalPrice.add(freightPrice).subtract(couponPrice).subtract(grouponPrice));
        return returnVo;
    }

    @Override
    public boolean updateCart(CartUpdateVo vo) {
        Cart cart = new Cart();
        cart.setId(vo.getId());
        cart.setNumber(vo.getNumber());
        cart.setUpdateTime(new Date());
        cart.setGoodsId(vo.getGoodsId());
        cart.setProductId(vo.getProductId());
        return cartMapper.updateByPrimaryKeySelective(cart) == 1;
    }

    @Override
    public boolean deleteCart(CartDeleteVo vo, int userId) {
        boolean flag = true;
        for (Integer productId : vo.getProductIds()) {
            flag &= (cartMapper.deleteByProductIdAndUserId(productId, userId) == 1);
        }
        return flag;
    }
}
