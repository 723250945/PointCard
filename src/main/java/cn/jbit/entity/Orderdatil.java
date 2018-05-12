package cn.jbit.entity;

import java.util.Date;

/**
 * 订单详细类
 */
public class Orderdatil {

    private long oid;//编号',
    private long id;//'订单编号',
    private int cId;//点卡编号',
    private int gid;//充值游戏编号',
    private int mun;//'购买数量',
    private Date byTime;//创建时间',
    private Double countPrice;//消费金额统计',

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getMun() {
        return mun;
    }

    public void setMun(int mun) {
        this.mun = mun;
    }

    public Date getByTime() {
        return byTime;
    }

    public void setByTime(Date byTime) {
        this.byTime = byTime;
    }

    public Double getCountPrice() {
        return countPrice;
    }

    public void setCountPrice(Double countPrice) {
        this.countPrice = countPrice;
    }

    public Orderdatil(int id, int cId, int gid, int mun, Date byTime, Double countPrice) {
        this.id = id;
        this.cId = cId;
        this.gid = gid;
        this.mun = mun;
        this.byTime = byTime;
        this.countPrice = countPrice;
    }

    public Orderdatil(int cId, int gid, int mun, Date byTime, Double countPrice) {
        this.cId = cId;
        this.gid = gid;
        this.mun = mun;
        this.byTime = byTime;
        this.countPrice = countPrice;
    }

    public Orderdatil() {
    }
}
