package cn.jbit.dao;

import cn.jbit.entity.PraiseRate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("praiseRateDao")
public interface PraiseRateDao {

    /**
     * 根据点卡编号查找评论信息
     * @param cid
     * @return
     */
    List<PraiseRate> findByCid(@Param("cid") int cid);
}
