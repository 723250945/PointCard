package cn.jbit.entity;

/**
 * 点卡用户类
 */

public class GameCardUser {

    private Integer id;//用户编号',
    private String loginName;// 登录账户',
    private String password;//登录密码',
    private Integer isAdmin;//是否管理员0是1不是',
    private Integer isState;//是否冻结0正常1冻结'

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getIsState() {
        return isState;
    }

    public void setIsState(Integer isState) {
        this.isState = isState;
    }

    public GameCardUser() {
    }

    public GameCardUser(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    public GameCardUser(String loginName, String password, Integer isAdmin, Integer isState) {
        this.loginName = loginName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isState = isState;
    }
}
