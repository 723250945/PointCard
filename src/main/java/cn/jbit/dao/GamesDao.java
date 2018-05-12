package cn.jbit.dao;

import cn.jbit.entity.Games;
import cn.jbit.entity.GamesType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("gamesDao")
public interface GamesDao {

    /**
     * 根据游戏类型查找游戏集合
     * @param type
     * @return
     */
    List<Games> findByParent(GamesType type);
}
