package cn.jbit.entity;

import java.util.Date;

/**
 * 点卡描述图标子集类
 */
public class CardPcitrue {
    private int id;// '编号',
    private int cId;// '点卡编号',
    private String path;// '图片路径',
    private Date rateTime;// '创建时间时间'

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getRateTime() {
        return rateTime;
    }

    public void setRateTime(Date rateTime) {
        this.rateTime = rateTime;
    }

    public CardPcitrue() {
    }


}
