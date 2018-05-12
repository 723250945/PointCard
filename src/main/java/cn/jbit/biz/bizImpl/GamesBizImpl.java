package cn.jbit.biz.bizImpl;

import cn.jbit.biz.GamesBiz;
import cn.jbit.dao.GamesDao;
import cn.jbit.entity.Games;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("gamesBiz")
public class GamesBizImpl implements GamesBiz {

    @Resource(name = "gamesDao")
    private GamesDao gamesDao;

    public List<Games> findAll() {
        return gamesDao.findByParent(null);
    }
}
