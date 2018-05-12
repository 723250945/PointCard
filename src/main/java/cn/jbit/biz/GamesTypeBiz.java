package cn.jbit.biz;

import cn.jbit.entity.Games;
import cn.jbit.entity.GamesType;

import java.util.List;
import java.util.Map;

public interface GamesTypeBiz {
    /**
     * 查询所有游戏类型
     * @return
     */
    List<GamesType> findAll();


}
