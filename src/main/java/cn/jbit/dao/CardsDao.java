package cn.jbit.dao;

import cn.jbit.entity.Cards;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("cardsDao")
public interface CardsDao {

    /**
     * 根据充值卡信息查找单个充值卡信息,包括包含游戏信息,必须要cid和gid两个参数才会返回唯一结果
     * @param cards
     * @return
     */
    Cards searchByInfo(Cards cards);


    /**
     * 查询单个充值卡信息，不包含不在该表得信息，用于修改点数据
     * @param cards
     * @return
     */
    Cards searchById(Cards cards);
    /**
     * 分页查询点卡集合信息
     * @param tid 游戏类型编号
     * @param gid 游戏编号
     * @param searchInfo 包括（点卡名称，游戏名称，点卡面值，游戏类型名称）
     * @param index
     * @param pagesize
     * @return
     */
    List<Cards> searchList(@Param("tid") int tid,
                           @Param("gid") int gid,
                           @Param("searchInfo") String searchInfo,
                           @Param("index") int index,
                           @Param("pagesize") int pagesize);

    /**
     * 获得分页信息得条数
     * @param tid 游戏类型编号
     * @param gid 游戏编号
     * @param searchInfo 包括（点卡名称，游戏名称，价钱，游戏类型名称）
     * @return
     */
    int getcount(@Param("tid") int tid,
                 @Param("gid") int gid,
                 @Param("searchInfo") String searchInfo);

    /**
     * 查询最新上架的点卡信息
     * @param top 获取信息的数量
     * @return
     */
    List<Cards> searchNewTime(@Param("top") Integer top);

    /**
     * 修改点卡信息
     * @param cards
     * @return
     */
    int updateCards(Cards cards);

}
