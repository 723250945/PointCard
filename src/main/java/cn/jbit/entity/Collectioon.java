package cn.jbit.entity;
/**
 * 收藏表
 */
public class Collectioon {
    private int id;//编号',
    private int uId;// '用户编号',
    private int cId;// '点卡编号',
    private int gid;// 游戏编号'

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
}
