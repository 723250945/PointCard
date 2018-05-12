package cn.jbit.entity;

/**
 * 购物车类
 */
public class ShoppingCart {
    private int id;//购物车编号',
    private int uId;// '用户编号',
    private int cId;// 点卡编号',
    private String cName;// '点卡名称',数据库不存在此列
    private String titleImg;// '点卡图标',数据库不存在此列
    private int number;// '库存数量'，,数据库不存在此列
    private Double iniPrice;//原价格',,数据库不存在此列
    private Double price;// 售价',,数据库不存在此列
    private int gid;// 游戏编号',
    private String    gName;//'游戏名称',',数据库不存在此列
    private int num;// 购买数量',
    private Double totalPrice;// 金额统计'

    public ShoppingCart() {
    }

    public ShoppingCart(int uId, int cId, int gid, int num, Double totalPrice) {
        this.uId = uId;
        this.cId = cId;
        this.gid = gid;
        this.num = num;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
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

    public Double getIniPrice() {
        return iniPrice;
    }

    public void setIniPrice(Double iniPrice) {
        this.iniPrice = iniPrice;
    }
}
