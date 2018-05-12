package cn.jbit.biz;

import cn.jbit.entity.GameCardUser;

public interface GameCardUserBiz {
    /**
     * 登录
     * @param user
     * @return
     */
    GameCardUser login(GameCardUser user);


    /**
     * 查找改用户名是否存在
     * @return
     */
    GameCardUser fingByName(String username);


    /**
     * 增加用户信息
     * @param user
     * @return
     */
    int addUser(GameCardUser user);
}
