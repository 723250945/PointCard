package cn.jbit.dao;

import cn.jbit.entity.GameCardUser;
import org.springframework.stereotype.Repository;

@Repository("gameCardUserDao")
public interface GameCardUserDao {

    /**
     * 查找用户信息
     * @param user
     * @return
     */
    GameCardUser findByInfo(GameCardUser user);

    /**
     * 增加用户信息
     * @param user
     * @return
     */
    int addUser(GameCardUser user);
}
