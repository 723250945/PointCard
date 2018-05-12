package cn.jbit.dao;

import cn.jbit.entity.CardSal;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cardSalDao")
public interface CardSalDao {

    /**
     * 按照销售数量查询点卡销售信息
     * @return
     */
    List<CardSal> searchByNum(@Param("top") Integer top);


    /**
     * 根据用户的编号查找订单信息
     * @param uId
     * @return
     */
    List<CardSal> searchByUid(@Param("uId") Integer uId);


    /**
     * 增加订单信息
     * @param cardSal
     * @return
     */
    int addCardSal(CardSal cardSal);
}
