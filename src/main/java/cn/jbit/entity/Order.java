package cn.jbit.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单类
 */
public class Order {
    private Long id;//订单编号',
    private String orderName;//订单名称
    private int uId;//'用户编号',
    private Date byTime;//创建时间',
    private List<Orderdatil> orderdatilList=new ArrayList<Orderdatil>();//订单详情集合,集合的引用必须要new不然就会报错
    private Double totalPrice;//总消费金额',
    private int paymenttype;//'支付方式',1支付宝2微信3中国银行4交通银行5中国工商银行6中国农业银行7中国建设银行8招商银行
    private int paymentState;//支付状态2支付1未支付',状态2在添加到销售订单

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public Date getByTime() {
        return byTime;
    }

    public void setByTime(Date byTime) {
        this.byTime = byTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(int paymenttype) {
        this.paymenttype = paymenttype;
    }

    public int getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(int paymentState) {
        this.paymentState = paymentState;
    }

    public List<Orderdatil> getOrderdatilList() {
        return orderdatilList;
    }

    public void setOrderdatilList(List<Orderdatil> orderdatilList) {
        this.orderdatilList = orderdatilList;
    }

    public Order() {
    }

    public Order(String orderName,int uId, Date byTime, Double totalPrice, int paymenttype, int paymentState) {
        this.orderName=orderName;
        this.uId = uId;
        this.byTime = byTime;
        this.totalPrice = totalPrice;
        this.paymenttype = paymenttype;
        this.paymentState = paymentState;
    }

    public Order(Long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
}
