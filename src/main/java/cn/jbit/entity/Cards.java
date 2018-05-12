package cn.jbit.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 点卡信息类
 */
public class Cards {
    private int cId;// 点卡编号',
    private String cName;// '点卡名称',
    private String titleImg;// '点卡图标',
    private Double iniPrice;//原价格',
    private Double price;// 售价',
    private int version;//版本号
    private String cInfo;// 点卡介绍',
    private Double praiseRate;// '好评率',
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//注解可以以该格式注入时间
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")   //默认通过ajax json解析后转化为的格式
    private Date upTime;// 上架时间',
    private int  isShelves ;//上架状态0上架1下架',
    private int number;// '数量'
    private int gid;//支持充值的游戏编号
    private String gName;//支持充值的游戏名称
    private int byNum;//购买数量
    private Double totalPrice;//价格小计，数据库不存在此列

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
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

    public String getcInfo() {
        return cInfo;
    }

    public void setcInfo(String cInfo) {
        this.cInfo = cInfo;
    }

    public Double getPraiseRate() {
        return praiseRate;
    }

    public void setPraiseRate(Double praiseRate) {
        this.praiseRate = praiseRate;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public int getIsShelves() {
        return isShelves;
    }

    public void setIsShelves(int isShelves) {
        this.isShelves = isShelves;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public int getByNum() {
        return byNum;
    }

    public void setByNum(int byNum) {
        this.byNum = byNum;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Cards() {
    }

    public Cards(int cId, String cName, Double price, int gid, String gName, int byNum) {
        this.cId = cId;
        this.cName = cName;
        this.price = price;
        this.gid = gid;
        this.gName = gName;
        this.byNum = byNum;
    }

    /**
     * 用于监听，此构造函数不能删除
     * @param cId
     * @param gid
     */
    public Cards(int cId, int gid) {
        this.cId = cId;
        this.gid = gid;
    }

    public Cards(int cId) {
        this.cId = cId;
    }

    public Cards(int cId, String cName, String titleImg, Double iniPrice, Double price, String cInfo, Double praiseRate, int number, int gid, String gName) {
        this.cId = cId;
        this.cName = cName;
        this.titleImg = titleImg;
        this.iniPrice = iniPrice;
        this.price = price;
        this.cInfo = cInfo;
        this.praiseRate = praiseRate;
        this.number = number;
        this.gid = gid;
        this.gName = gName;
    }

    public Cards(Double iniPrice, int gid) {
        this.iniPrice = iniPrice;
        this.gid = gid;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", titleImg='" + titleImg + '\'' +
                ", iniPrice=" + iniPrice +
                ", price=" + price +
                ", cInfo='" + cInfo + '\'' +
                ", praiseRate=" + praiseRate +
                ", upTime=" + upTime +
                ", isShelves=" + isShelves +
                ", number=" + number +
                ", gid=" + gid +
                ", gName='" + gName + '\'' +
                ", byNum=" + byNum +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
