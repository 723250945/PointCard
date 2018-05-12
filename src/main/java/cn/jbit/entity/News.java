package cn.jbit.entity;

import java.util.Date;

/**
 * 新闻信息类
 */
public class News {
    private int nId;// 新闻编号',
    private String title ;//新闻标题',
    private Date createTime;// 创建时间',
    private String from;// DEFAULT'未知' COMMENT'信息来源',广告的话就是连接地址
    private String context;// '新闻内容',
    private String npath;//广告图标
    private String type;// 新闻类型，新闻，公告等'

    public int getnId() {
        return nId;
    }

    public void setnId(int nId) {
        this.nId = nId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNpath() {
        return npath;
    }

    public void setNpath(String npath) {
        this.npath = npath;
    }

    public News() {
    }

    /**
     * 新闻id
     * @param nId
     */
    public News(int nId) {
        this.nId = nId;
    }

    /**
     * 新闻类型
     * @param type
     */
    public News(String type) {
        this.type = type;
    }
}
