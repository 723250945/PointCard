package cn.jbit.entity;

import java.util.Date;

/**
 * 投诉建议表
 */
public class Proposal {
    private int id;// 投诉编号',
    private int uId;//'建议人',
    private int type;//投诉类型0建议1投诉',
    private String title;// 标题',
    private String content;//'建议内容',
    private Date upTime;// 提交时间',
    private int cUid;// '处理人',
    private int prate;// 处理状态2以处理1未处理',
    private Date ptime;// 回复时间'

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public int getcUid() {
        return cUid;
    }

    public void setcUid(int cUid) {
        this.cUid = cUid;
    }

    public int getPrate() {
        return prate;
    }

    public void setPrate(int prate) {
        this.prate = prate;
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }
}
