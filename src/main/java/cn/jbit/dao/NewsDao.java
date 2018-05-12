package cn.jbit.dao;

import cn.jbit.entity.News;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("newsDao")
public interface NewsDao {

    /**
     * 根据类型查找新闻信息
     * @param type
     * @return
     */
    List<News> searchByType(@Param("type") String type);
}
