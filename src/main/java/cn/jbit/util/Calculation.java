package cn.jbit.util;

import cn.jbit.entity.Cards;

import java.math.BigDecimal;
import java.util.List;

public class Calculation {

    /**
     * 计算乘法获得四舍五入的两位小数型数据
     * @param price
     * @param num
     * @return
     */
    public static double getDouble(double price,int num){
        BigDecimal  b  = new BigDecimal(price*num);
        double  f1  =b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    /**
     * 将一个double数值四舍五入保存两个小数
     * @param price
     * @return
     */
    public static double parsetDouble(double price){
        BigDecimal  b  = new BigDecimal(price);
        double  f1  =b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    /**
     * 保存最新Integer集合
     * @param list
     * @param size
     * @return
     */
    public static List<Integer> removSizeInt(List<Integer> list, int size){
        if(list.size()>size){
            list.remove(0);
        }
        return list;
    }

    /**
     * 保存card最新集合信息
     * @param list
     * @param size
     * @return
     */
    public static List<Cards> removesizeCard(List<Cards> list,int size){
        if(list.size()>size){
            list.remove(0);
        }
        return list;
    }

}
