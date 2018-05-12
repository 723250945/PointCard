package cn.jbit.entity;

/**
 * 游戏类
 */
public class Games {
    private int gid;//游戏编号',
    private String    gName;//'游戏名称',
    private int tId;//所属游戏类型'
    private String tName;//游戏类型名称,#数据库无此列

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
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

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public Games(int gid) {
        this.gid = gid;
    }

    public Games() {
    }
}
