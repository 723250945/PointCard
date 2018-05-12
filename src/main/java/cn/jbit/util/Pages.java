package cn.jbit.util;

import java.util.List;

/**
 * 分页类
 */
public class Pages<T> {

    private int totalPageCount;//总页数
    private int pageSzie;//每页显示的数量
    private int totalCount;//新闻信息的总数量
    private int currPageNo;//当前页码
    private List<T> newList;//每页信息的集合

    public int getTotalPageCount() {
        return totalPageCount;
    }
    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount=totalPageCount;
    }
    public int getPageSzie() {
        return pageSzie;
    }
    public void setPageSzie(int pageSzie) {
        if(pageSzie>0) {
            this.pageSzie = pageSzie;
        }
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        if(totalCount>0) {
            this.totalCount=totalCount;
            //计算总页数
            totalPageCount=this.totalCount % pageSzie==0 ? (this.totalCount/pageSzie):(this.totalCount/pageSzie+1);
        }
    }
    public int getCurrPageNo() {
        if(totalPageCount==0) {
            return 0;
        }
        return currPageNo;
    }
    public void setCurrPageNo(int currPageNo) {
        if(currPageNo>0) {
            this.currPageNo = currPageNo;
        }
    }
    public List<T> getNewList() {
        return newList;
    }
    public void setNewList(List<T> newList) {
        this.newList = newList;
    }

}
