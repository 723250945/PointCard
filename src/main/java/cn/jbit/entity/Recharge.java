package cn.jbit.entity;

/**
 * 充值表
 */
public class Recharge {
    private int id;//充值编号',
    private int uId;//用户编号',
    private Double money;// 充值金额',
    private int paymenttype;// 充值类型',
    private int rate;//充值状态2成功1失败'

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public int getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(int paymenttype) {
        this.paymenttype = paymenttype;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
