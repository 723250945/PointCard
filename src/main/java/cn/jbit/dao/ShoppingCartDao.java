package cn.jbit.dao;

import cn.jbit.entity.ShoppingCart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 购物车DAo
 */
@Repository("shoppingCartDao")
public interface ShoppingCartDao {

    /**
     * 增加购物车
     * @param cart
     * @return
     */
    int addShoppingCart(ShoppingCart cart);

    /**
     * 删除购物车信息
     * @param cart
     * @return
     */
    int deleteCart(ShoppingCart cart);

    /**
     * 查找某个会员得订单信息
     * @param uId
     * @return
     */
    List<ShoppingCart> searchCarts(@Param("uId") Integer uId);

    /**
     * 修改购物车信息
     * @param cart
     * @return
     */
    int updateCart(ShoppingCart cart);

    /**
     * 查询单个购物车信息
     * @param cart
     * @return
     */
    ShoppingCart searchCart(ShoppingCart cart);
}
