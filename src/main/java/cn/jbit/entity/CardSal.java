package cn.jbit.entity;

import java.util.Date;

/**
 * 点卡销售信息类
 */
public class CardSal {
     private int sId;//点卡的销售编号',
    private int cId ;//点卡编号',
    private String cName;//点卡名称，#数据库不存在此列
    private String titleImg;//点卡图标，#数据库不存在此列
    private Double iniPrice;//原价格',#数据库不存在此列
    private Double price;// 售价',#数据库不存在此列
    private String cInfo;//点卡的介绍，#数据库不存在此列
    private int gid;// 充值游戏编号',
    private String    gName;//'游戏名称',#数据库不存在此列
    private int uId ;//'购买客户的编号',
    private Date buyTime;//'购买时间',
    private int num;//'购买的张数',
    private Double totalPrice;//总金额',

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }


    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getcInfo() {
        return cInfo;
    }

    public void setcInfo(String cInfo) {
        this.cInfo = cInfo;
    }

    public Double getIniPrice() {
        return iniPrice;
    }

    public void setIniPrice(Double iniPrice) {
        this.iniPrice = iniPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public CardSal() {
    }

    public CardSal(int cId, int gid, int uId, Date buyTime, int num, Double totalPrice) {
        this.cId = cId;
        this.gid = gid;
        this.uId = uId;
        this.buyTime = buyTime;
        this.num = num;
        this.totalPrice = totalPrice;
    }
}
