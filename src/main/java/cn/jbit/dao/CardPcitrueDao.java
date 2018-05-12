package cn.jbit.dao;

import cn.jbit.entity.CardPcitrue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("cardPcitrueDao")
public interface CardPcitrueDao {

    /**
     * 根据点卡编号查找点卡子集图标信息
     * @param cid
     * @return
     */
    List<CardPcitrue> findByCid(@Param("cid") int cid);
}
