package cn.jbit.dao;

import cn.jbit.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单Dao
 */
@Repository("orderDao")
public interface OrderDao {

    /**
     * 根据订单信息查找单条订单,
     * 参数事件可以精确到秒，但是为了区分一秒之内得东西，加了参数总金额
     * 主要为了返回查询得订单编号
     * @param order
     * @return
     */
    Order searchByInfo(Order order);

    /**
     * 增加订单信息
     * 默认得支付方式是1，将在支付成功之后改回实际的支付方式，和支付状态0成功
     * @param order
     * @return
     */
    int addOrder(Order order);

    /**
     * 修改订单信息
     * @param order
     * @return
     */
    int updateOrder(Order order);

    /**
     * 查找订单信息集合
     * @param uid
     * @return
     */
    List<Order> searchOrders(@Param("uid") int uid,
                             @Param("paymentState") int paymentState);

    /**
     *
     * @param uid
     * @param paymentState
     * @param index
     * @param pagesize
     * @return
     */
    List<Order> searchPage(@Param("uid") int uid,
                           @Param("paymentState") int paymentState,
                           @Param("index") int index,
                           @Param("pagesize") int pagesize);
    /**
     * 查找订单信息的条数
     * @param uid
     * @param paymentState
     * @return
     */
    int getcount(@Param("uid") int uid,
                 @Param("paymentState") int paymentState);


    /**
     * 删除订单信息
     * @param id
     * @return
     */
    int deleteOrder(@Param("id") Long id);
}
