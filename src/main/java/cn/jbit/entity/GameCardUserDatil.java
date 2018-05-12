package cn.jbit.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户详细信息表
 */
public class GameCardUserDatil {
    private int id;// 用户详细信息编号',
    private int loginID;//'用户编号',
    private String loginName;// 登录账户',数据库不存在此列
    private String password;//登录密码',
    private String userName;// '用户姓名',
    private String userAddress;// 用户住址',
    private long userQQ;// 用户QQ',
    private long telephone;//电话
    private String userEmail;// 用户邮箱',
    private int userStep;// 会员等级',
    private Double userChange;// 账户余额',
    private Double totalPrice;//消费金额'
    @DateTimeFormat(pattern = "YYYY-MM-dd")//注解可以以该格式注入时间
    @JSONField(format = "YYYY-MM-dd")   //默认通过ajax json解析后转化为的格式
    private Date createTime;//注册时间
    private String paypwd;//支付密码
    private int year;//会员使用年限

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoginID() {
        return loginID;
    }

    public void setLoginID(int loginID) {
        this.loginID = loginID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserStep() {
        return userStep;
    }

    public void setUserStep(int userStep) {
        this.userStep = userStep;
    }

    public Double getUserChange() {
        return userChange;
    }

    public void setUserChange(Double userChange) {
        this.userChange = userChange;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPaypwd() {
        return paypwd;
    }

    public void setPaypwd(String paypwd) {
        this.paypwd = paypwd;
    }

    public GameCardUserDatil() {
    }

    public GameCardUserDatil(int loginID) {
        this.loginID = loginID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public long getUserQQ() {
        return userQQ;
    }

    public void setUserQQ(long userQQ) {
        this.userQQ = userQQ;
    }

    public long getTelephone() {
        return telephone;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }
}
