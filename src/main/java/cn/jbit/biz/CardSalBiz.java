package cn.jbit.biz;

import cn.jbit.entity.CardSal;

import java.util.List;

public interface CardSalBiz {

    List<CardSal> searchByNum();

    List<CardSal> searchByUid(int uid);
}
