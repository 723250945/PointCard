package cn.jbit.biz.bizImpl;

import cn.jbit.biz.CardSalBiz;
import cn.jbit.dao.CardSalDao;
import cn.jbit.entity.CardSal;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cardSalBiz")
public class CardSalBizImpl implements CardSalBiz {

    @Resource(name = "cardSalDao")
    private CardSalDao cardSalDao;

    /**
     * 查询销售前10点卡信息
     * @return
     */
    public List<CardSal> searchByNum() {
        return cardSalDao.searchByNum(10);
    }

    /**
     * 根据客户编号查找订单信息
     * @param uid
     * @return
     */
    public List<CardSal> searchByUid(int uid) {
        return cardSalDao.searchByUid(uid);
    }
}
