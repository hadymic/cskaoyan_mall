package com.cskaoyan.mall.vo.wx.cart;

import java.util.List;

/**
 * 勾选购物车vo
 *
 * @author hadymic
 */
public class CartCheckedVo {
    private boolean isChecked;
    private List<Integer> productIds;

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean checked) {
        isChecked = checked;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }
}
