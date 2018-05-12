package cn.jbit.biz.bizImpl;

import cn.jbit.biz.ShoppingCartBiz;
import cn.jbit.dao.ShoppingCartDao;
import cn.jbit.entity.ShoppingCart;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("shoppingCartBiz")
public class ShoppingCartBizImpl implements ShoppingCartBiz {

    @Resource(name = "shoppingCartDao")
    private ShoppingCartDao shoppingCartDao;

    /**
     * 添加购物车，新增一个判断商品名称相同，只增加数量
     * @param cart
     * @return
     */
    public int addShoppingCart(ShoppingCart cart) {
        ShoppingCart cart1=shoppingCartDao.searchCart(cart);
        int ret=0;
        if(null==cart1){
            ret=shoppingCartDao.addShoppingCart(cart);
        }else {
            cart1.setNum(cart.getNum()+cart1.getNum());//数量相加,尽量把运算放在代码，数据库不擅长处理运算
            cart1.setTotalPrice(cart.getTotalPrice()+cart1.getTotalPrice());
            ret=shoppingCartDao.updateCart(cart1);
        }
        return ret;
    }

    public int deleteShoppingCart(ShoppingCart cart) {
        return shoppingCartDao.deleteCart(cart);
    }

    public List<ShoppingCart> searchCarts(int uId) {
        return shoppingCartDao.searchCarts(uId);
    }

    public int updateCart(ShoppingCart cart) {
        return shoppingCartDao.updateCart(cart);
    }
}
