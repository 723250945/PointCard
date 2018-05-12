package cn.jbit.biz;

import cn.jbit.entity.News;

import java.util.List;

public interface NewsBiz {

    /**
     * 根据类型查找新闻信息
     * @param type
     * @return
     */
    List<News> searchNews(String type);
}
