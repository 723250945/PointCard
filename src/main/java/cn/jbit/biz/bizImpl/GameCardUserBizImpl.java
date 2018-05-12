package cn.jbit.biz.bizImpl;

import cn.jbit.biz.GameCardUserBiz;
import cn.jbit.dao.GameCardUserDao;
import cn.jbit.entity.GameCardUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("gameCardUserBiz")
public class GameCardUserBizImpl implements GameCardUserBiz {

    /**
     * 登录
     */
    @Resource(name = "gameCardUserDao")
    private GameCardUserDao gameCardUserDao;
    public GameCardUser login(GameCardUser user) {
        return gameCardUserDao.findByInfo(user);
    }

    /**
     * 查找改用户名是否存在
     * @param username
     * @return
     */
    public GameCardUser fingByName(String username) {
        GameCardUser user=new GameCardUser();
        user.setLoginName(username);
        return gameCardUserDao.findByInfo(user);
    }

    /**
     * 增加用户信息
     * @param user
     * @return
     */
    public int addUser(GameCardUser user) {
        gameCardUserDao.addUser(user);
        return gameCardUserDao.findByInfo(user).getId();//返回插入得用户得id
    }
}
