package cn.jbit.biz.bizImpl;

import cn.jbit.biz.OrderBiz;
import cn.jbit.dao.*;
import cn.jbit.entity.*;
import cn.jbit.util.Calculation;
import cn.jbit.util.Pages;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Transactional
@Service("orderBiz")
public class OrderBizImpl implements OrderBiz {

    @Resource(name = "orderdatilDao")
    private OrderdatilDao orderdatilDao;
    @Resource(name = "orderDao")
    private OrderDao orderDao;
    @Resource(name = "cardSalDao")
    private CardSalDao cardSalDao;
    @Resource(name = "gameCardUserDatilDao")
    private GameCardUserDatilDao gameCardUserDatilDao;
    @Resource(name = "cardsDao")
    private CardsDao cardsDao;
    /**
     * 增加订单信息
     * @param order
     * @return
     */
    @AfterThrowing
    @Transactional(rollbackFor = {SQLException.class})
    public Order addOrder(Order order) {
        //在插入数据前统一日期格式
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date= dateFormat2.format(order.getByTime());
        try {
            Date myDate2 =dateFormat2.parse(date);
            order.setByTime(myDate2);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("订单生成失败！");
        }
        int result=orderDao.addOrder(order);
        if(result>0){
            Order order1=orderDao.searchByInfo(order);//返回订单包括订单编号
            for (Orderdatil o:order.getOrderdatilList()) {//增加订单详细信息
                o.setId(order1.getId());
                result+=orderdatilDao.addOrderdatil(o);
            }
            order.setId(order1.getId());
        }else {
            order=null;
        }
        return order;
    }


    /**
     * 支付成功修改订单信息，增加销售记录
     * @param order
     * @return
     */
    @AfterThrowing
    @Transactional(rollbackFor = {SQLException.class})
    public int updateOrder(Order order, GameCardUserDatil userDatil, int payType){
        List<Integer> result=new ArrayList<Integer>();
        int big=1;
        try {
            userDatil.setUserChange(Calculation.parsetDouble((userDatil.getUserChange()-order.getTotalPrice())));
            userDatil.setTotalPrice(Calculation.parsetDouble(userDatil.getTotalPrice()+order.getTotalPrice()));
            int ret=(int)(userDatil.getTotalPrice()/1000);
            userDatil.setUserStep(ret);
            order.setPaymentState(2);
            order.setPaymenttype(payType);//帐户余额支付
            result.add(gameCardUserDatilDao.updateUserDatil(userDatil));//1.更改用户详情
            result.add(orderDao.updateOrder(order));//2.修改订单支付类型和支付状态成功
            List<Orderdatil> orderdatilList=orderdatilDao.searchByOId(order.getId());
            for (Orderdatil or:orderdatilList) {//3.根据订单详情增加销售记录表
                CardSal cardSal=new CardSal(or.getcId(),or.getGid(),userDatil.getLoginID(),or.getByTime(),or.getMun(),or.getCountPrice());
                //4.修改点卡得数量
                Cards cards=cardsDao.searchById(new Cards(or.getcId()));
                cards.setNumber(cards.getNumber()-or.getMun());
                if(cards.getNumber()<=0){
                    big=1;
                    throw new RuntimeException(cards.getcName()+"编号："+cards.getcId()+"库存数量不足！");
                }
                //减少数据得传递，只修改数量和id是必须得
                Cards cardsa=new Cards();
                cardsa.setNumber(cards.getNumber());
                cardsa.setIsShelves(-2);
                cardsa.setcId(cards.getcId());
                cardsa.setVersion(cards.getVersion());
                result.add(cardsDao.updateCards(cardsa));
                result.add(cardSalDao.addCardSal(cardSal));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("执行订单操作错误！");
        }
        for (Integer i:result) {
            if(i==0){
                big=0;
                throw new RuntimeException("执行回滚！");
            }
        }
        return big;
    }

    /**
     * 批量完成订单支付
     * @param orders
     * @param userDatil
     * @param type
     * @return
     */
    @Transactional(rollbackFor = {SQLException.class})
    public int updateOrders(List<Order> orders, GameCardUserDatil userDatil, int type) {
        List<Integer> result=new ArrayList<Integer>();
        int big=1;//
        double total=0;//总金额
        try {
            for(Order o:orders) {
                List<Orderdatil> orderdatilList=orderdatilDao.searchByOId(o.getId());
                for (Orderdatil or:orderdatilList) { //根据订单详情增加销售记录表
                    CardSal cardSal=new CardSal(or.getcId(),or.getGid(),userDatil.getLoginID(),or.getByTime(),or.getMun(),or.getCountPrice());
                    //修改点卡得数量
                    Cards cards=cardsDao.searchById(new Cards(or.getcId()));
                    cards.setNumber(cards.getNumber()-or.getMun());
                    if(cards.getNumber()<=0){
                        big=1;
                        throw new RuntimeException(cards.getcName()+"编号："+cards.getcId()+"库存数量不足！");
                    }
                    //减少数据得传递，只修改数量和id是必须得
                    Cards cardsa=new Cards();
                    cardsa.setNumber(cards.getNumber());
                    cardsa.setcId(cards.getcId());
                    cardsa.setIsShelves(-2);
                    cardsa.setVersion(cards.getVersion());
                    result.add(cardsDao.updateCards(cardsa));//1.修改点卡得数量
                    result.add(cardSalDao.addCardSal(cardSal));//2..根据订单详情增加销售记录表
                }
                total+=o.getTotalPrice();
                o.setPaymentState(2);
                o.setPaymenttype(type);//帐户余额支付9
                result.add(orderDao.updateOrder(o));//3.修改订单支付类型和支付状态成功
            }
            userDatil.setUserChange(Calculation.parsetDouble((userDatil.getUserChange()-total)));
            userDatil.setTotalPrice(Calculation.parsetDouble(userDatil.getTotalPrice()+total));
            int ret=(int)(userDatil.getTotalPrice()/1000);
            userDatil.setUserStep(ret);
            result.add(gameCardUserDatilDao.updateUserDatil(userDatil));//4.更改用户详情
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("执行订单操作错误！");
        }
        for (Integer i:result) {
            if(i==0){
                big=0;
                throw new RuntimeException("执行回滚！");
            }
        }
        return big;
    }

    /**
     * 查找订单信息
     * @param order
     * @return
     */
    public Order searchByInfo(Order order) {
        return orderDao.searchByInfo(order);
    }


    /**
     * 获取订单得数量
     * @param uid
     * @param type
     * @return
     */
    public int getCount(int uid,int type){
        return orderDao.getcount(uid,type);
    }

    /**
     * 分页面查找订单信息
     * @param index
     * @param pagesize
     * @return
     */
    public Pages<Order> searchPage(int uid,int type, int index, int pagesize) {
        Pages<Order> cardsPages=new Pages<Order>();
        cardsPages.setPageSzie(pagesize);
        cardsPages.setCurrPageNo(index);
        //获取总行数
        cardsPages.setTotalCount(orderDao.getcount(uid,type));
        //获取当前页数据起始点
        index=(index-1)*pagesize;
        cardsPages.setNewList(orderDao.searchPage(uid,type,index,pagesize));
        return cardsPages;
    }

    /**
     * 查找支付、未支付的订单信息
     * @param uid 用户变好
     * @param type 支付状态1没有支付2支付成功
     * @return
     */
    public List<Order> searchOrderByType(int uid,int type){
        return orderDao.searchOrders(uid,type);
    }

    /**
     * 根据订单编号删除订单信息，包括订单详情
     * @param id
     * @return
     */
    public int deleteOrderById(Long id){
        int ret=orderdatilDao.deleteByParentId(id);
        ret+=orderDao.deleteOrder(id);
        return ret;
    }

    /**
     * 删除多个订单信息
     * @param orders
     * @return
     */
    public int deleteOrderList(List<Order> orders) {
        int ret=0;
        try {
            for (Order or:orders) {
                ret=orderdatilDao.deleteByParentId(or.getId());
                ret+=orderDao.deleteOrder(or.getId());
            }
            ret=1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }


}
