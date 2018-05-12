package cn.jbit.biz.bizImpl;

import cn.jbit.biz.GamesTypeBiz;
import cn.jbit.dao.GamesDao;
import cn.jbit.dao.GamesTypeDao;
import cn.jbit.entity.Games;
import cn.jbit.entity.GamesType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("gamesTypeBiz")
public class GamesTypeBizImpl implements GamesTypeBiz {
    @Resource(name = "gamesTypeDao")
    private GamesTypeDao gamesTypeDao;
    @Resource(name = "gamesDao")
    private GamesDao gamesDao;

    /**
     * 查找所有的游戏类型
     * @return
     */
    public List<GamesType> findAll() {
        return gamesTypeDao.findAll();
    }

}
