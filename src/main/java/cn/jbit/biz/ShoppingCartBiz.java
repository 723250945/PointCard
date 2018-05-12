package cn.jbit.biz;

import cn.jbit.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartBiz {

    int addShoppingCart(ShoppingCart cart);

    int deleteShoppingCart(ShoppingCart cart);

    List<ShoppingCart> searchCarts(int uId);

    int updateCart(ShoppingCart cart);
}
