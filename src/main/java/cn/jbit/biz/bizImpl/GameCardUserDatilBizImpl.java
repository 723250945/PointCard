package cn.jbit.biz.bizImpl;

import cn.jbit.biz.GameCardUserDatilBiz;
import cn.jbit.dao.GameCardUserDatilDao;
import cn.jbit.entity.GameCardUserDatil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("gameCardUserDatilBiz")
public class GameCardUserDatilBizImpl implements GameCardUserDatilBiz {

    @Resource(name = "gameCardUserDatilDao")
    private GameCardUserDatilDao gameCardUserDatilDao;


    /**
     * 查找用户详细信息
     * @param loginId
     * @return
     */
    public GameCardUserDatil searchnfo(int loginId) {
        return gameCardUserDatilDao.findbyUid(loginId);
    }

    /**
     * 增加用户详细信息
     * @param user
     * @return
     */
    public int addUserDatil(GameCardUserDatil user) {
        return gameCardUserDatilDao.addUserDatil(user);
    }

    /**
     * 修改用户信息信息
     * @param userDatil
     * @return
     */
    public int updateUserDatil(GameCardUserDatil userDatil) {
        return gameCardUserDatilDao.updateUserDatil(userDatil);
    }
}
