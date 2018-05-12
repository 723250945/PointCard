package cn.jbit.dao;

import cn.jbit.entity.Orderdatil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单详情Dao
 */
@Repository("orderdatilDao")
public interface OrderdatilDao {

    /**
     * 增加订单详情，一个订单对应多条订单详情
     * @param orderdatil
     * @return
     */
    int addOrderdatil(Orderdatil orderdatil);


    /**
     * 根据订单编号查找订单详情
     * @param oId
     * @return
     */
    List<Orderdatil> searchByOId(@Param("oId") Long oId);


    /**
     * 根据订单编号删除订单详情
     * @param id
     * @return
     */
    int deleteByParentId(@Param("id") Long id);
}
