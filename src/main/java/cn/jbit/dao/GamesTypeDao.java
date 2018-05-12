package cn.jbit.dao;

import cn.jbit.entity.GamesType;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("gamesTypeDao")
public interface GamesTypeDao {

    /**
     * 查找所有游戏类型
     * @return
     */
    List<GamesType> findAll();
}
