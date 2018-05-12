package cn.jbit.biz.bizImpl;

import cn.jbit.biz.NewsBiz;
import cn.jbit.dao.NewsDao;
import cn.jbit.entity.News;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("newsBiz")
public class NewsBizImpl implements NewsBiz{

    @Resource(name = "newsDao")
    private NewsDao newsDao;


    public List<News> searchNews(String type) {
        return newsDao.searchByType(type);
    }
}
