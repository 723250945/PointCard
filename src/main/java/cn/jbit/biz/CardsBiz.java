package cn.jbit.biz;

import cn.jbit.entity.*;
import cn.jbit.util.Pages;

import java.util.List;

public interface CardsBiz {
    /**
     * 分页查询点卡信息
     * @param typeId
     * @param index
     * @param pagesize
     * @return
     */
    public Pages<Cards> searchPage(int typeId,int gid,String searchInfo,int index,int pagesize);


    /**
     * 查询最新上架的点卡信息前10名
     * @return
     */
    public List<Cards> searchNewTime();

    /**
     * 根据点卡id查找点卡子集图标
     * @param cid
     * @return
     */
    public List<CardPcitrue> searchByCid(int cid);

    /**
     * 根据点卡id查找评论信息
     * @param cid
     * @return
     */
    public List<PraiseRate> searchPraiseRate(int cid);


    /**
     * 修改点卡信息
     * @param cards
     * @return
     */
    int updateCards(Cards cards);


    /**
     * 查找单个点卡信息
     * @param cards
     * @return
     */
    Cards searchCard(Cards cards);

    /**
     * 查找用户浏览记录的点卡信息
     * @return
     */
    List<Cards> searchBrowse();
}
