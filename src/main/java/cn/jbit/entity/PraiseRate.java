package cn.jbit.entity;

import java.util.Date;

/**
 * 点卡商品评论类
 */
public class PraiseRate {
    private int pId;// 评编号',
    private int cId;// 点卡编号',
    private int uId ;//评论用户编号',
    private String loginName;//评论用户昵称，数据库无此列
    private String pInfo;// 评论内容',
    private Date rateTime;// 评论时间',
    private int isPraise;// 好评或者差评0好评1差评'

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getpInfo() {
        return pInfo;
    }

    public void setpInfo(String pInfo) {
        this.pInfo = pInfo;
    }

    public Date getRateTime() {
        return rateTime;
    }

    public void setRateTime(Date rateTime) {
        this.rateTime = rateTime;
    }

    public int getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(int isPraise) {
        this.isPraise = isPraise;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public PraiseRate(int cId) {
        this.cId = cId;
    }

    public PraiseRate() {
    }
}
