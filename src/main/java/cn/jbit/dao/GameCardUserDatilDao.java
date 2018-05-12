package cn.jbit.dao;

import cn.jbit.entity.GameCardUserDatil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("gameCardUserDatilDao")
public interface GameCardUserDatilDao {

    /**
     * 根据用户编号查找用户详细信息
     * @param uid
     * @return
     */
    GameCardUserDatil findbyUid(@Param("uid")int uid);


    /**
     * 增加用户详细信息
     * @param userDatil
     * @return
     */
    int addUserDatil(GameCardUserDatil userDatil);


    /**
     * 修改用户信息
     * @param userDatil
     * @return
     */
    int updateUserDatil(GameCardUserDatil userDatil);
}
