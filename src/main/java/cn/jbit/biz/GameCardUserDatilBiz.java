package cn.jbit.biz;
import cn.jbit.entity.GameCardUserDatil;

public interface GameCardUserDatilBiz {

    /**
     * 根据用户id查找详细信息
     * @return
     */
    GameCardUserDatil searchnfo(int loginId);

    /**
     * 增加用户详细信息
     * @param user
     * @return
     */
    int addUserDatil(GameCardUserDatil user);


    /**
     * 修改用户详细信息
     * @param userDatil
     * @return
     */
    int updateUserDatil(GameCardUserDatil userDatil);
}
