package com.cskaoyan.mall.service.wx.cart.impl;

import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsProduct;
import com.cskaoyan.mall.mapper.CartMapper;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import com.cskaoyan.mall.service.wx.cart.CartService;
import com.cskaoyan.mall.vo.wx.cart.CartAddVo;
import com.cskaoyan.mall.vo.wx.cart.CartCheckedVo;
import com.cskaoyan.mall.vo.wx.cart.CartListVo;
import com.cskaoyan.mall.vo.wx.cart.CartTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsProductMapper goodsProductMapper;

    @Override
    public CartListVo cartList(int id) {
        CartListVo vo = new CartListVo();
        List<Cart> carts = cartMapper.queryByUserId(id);
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
            return "商品太抢手啦，库存已空哦！";
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
        List<Cart> carts = cartMapper.queryByUserId(userId);
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
}
