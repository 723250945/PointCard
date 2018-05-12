package cn.jbit.biz;

import cn.jbit.entity.GameCardUserDatil;
import cn.jbit.entity.Order;
import cn.jbit.entity.Orderdatil;
import cn.jbit.util.Pages;

import java.util.List;

public interface OrderBiz {

    /**
     * 增加订单信息,增加订单详细
     * @param order
     * @return
     */
    Order addOrder(Order order);

    /**
     * 分页查找订单信息
     * @param index
     * @param pagesize
     * @return
     */
    public Pages<Order> searchPage(int uid,int type, int index, int pagesize);

    /**
     * 获取订单得数量
     * @param uid
     * @param type
     * @return
     */
    public int getCount(int uid,int type);

    /**
     * 支付成功修改订单信息，增加销售记录
     * @param order 订单
     * @param userDatil 用户详细信息
     * @param payType 支付类型，比如支付宝，网银
     * @return
     */
    int updateOrder(Order order, GameCardUserDatil userDatil,int payType);


    /**
     * 批量修改完成订单支付，增加销售记录
     * @param orders
     * @param userDatil
     * @param type
     * @return
     */
    int updateOrders(List<Order> orders,GameCardUserDatil userDatil,int type);

    /**
     * 查找订单信息
     * @param order
     * @return
     */
    Order searchByInfo(Order order);


    /**
     * 查找支付、未支付的订单信息
     * @param uid 用户id
     * @param type 支付状态1没有支付2支付成功
     * @return
     */
    List<Order> searchOrderByType(int uid,int type);

    /**
     * 根据订单编号删除订单信息，包括订单详情
     * @param id
     * @return
     */
    int deleteOrderById(Long id);


    /**
     * 删除多个订单信息
     * @param orders
     * @return
     */
    int deleteOrderList(List<Order> orders);
}
