package cn.jbit.control;

import cn.jbit.biz.*;
import cn.jbit.entity.*;
import cn.jbit.util.Calculation;
import cn.jbit.util.Pages;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 前端页面控制层，需要登录权限
 */
@Controller
public class ReceptionControl {

    @Resource(name = "gameCardUserBiz")
    private GameCardUserBiz gameCardUserBiz;
    @Resource(name = "gameCardUserDatilBiz")
    private GameCardUserDatilBiz gameCardUserDatilBiz;
    @Resource(name = "cardSalBiz")
    private CardSalBiz cardSalBiz;
    @Resource(name = "gamesTypeBiz")
    private GamesTypeBiz gamesTypeBiz;
    @Resource(name = "gamesBiz")
    private GamesBiz gamesBiz;
    @Resource(name = "cardBiz")
    private CardsBiz cardsBiz;
    @Resource(name = "orderBiz")
    private OrderBiz orderBiz;
    @Resource(name = "shoppingCartBiz")
    private ShoppingCartBiz shoppingCartBiz;


    /**
     * 重定向到页面，这个需要用户权限验证
     * @param jspUrl
     * @return
     */
    @RequestMapping("/resultjsp/{jspUrl}")
    public String resultjsp(@PathVariable("jspUrl") String jspUrl){
        return jspUrl;
    }
    /**
     * 跳转到我的账户页面
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/toHome")
    public String toHome(HttpServletRequest request)throws IOException {
        GameCardUser user = (GameCardUser)request.getSession().getAttribute("user");
        int orderCount=orderBiz.getCount(user.getId(),0);//获得用户的订单信息数量
        request.getSession().setAttribute("orderCount",orderCount);
        return "redirect:/resultjsp/home";
    }

    /**
     * 跳转到游戏充值页面
     * @return
     */
    @RequestMapping(value = "/toProduct")
    public String toProduct(){
        return "redirect:/resultjsp/product";
    }

    /**
     * 账户充值的方法
     * @return
     */
    @RequestMapping(value = "/recharge/{type}/{rechargeNum}")
    public String recharge(@PathVariable("type") int type,
                           @PathVariable("rechargeNum") int rechargeNum,
                           HttpServletRequest request){
        GameCardUserDatil userDatil=(GameCardUserDatil)request.getSession().getAttribute("userDatil");
        userDatil.setUserChange(userDatil.getUserChange()+rechargeNum);
        int i = gameCardUserDatilBiz.updateUserDatil(userDatil);
        if(i>0){
            return "redirect:/resultjsp/toHome";
        }else {
            return "redirect:/resultjsp/recharge";
        }
    }
    /**
     * 跳转到购物车
     * @return
     */
    @RequestMapping(value = "/toShop")
    public String toShop(HttpServletRequest request){
        GameCardUser user =(GameCardUser)request.getSession().getAttribute("user");
        List<ShoppingCart> cartList=shoppingCartBiz.searchCarts(user.getId());//获取用户购物车信息
        List<Cards> browseList=cardsBiz.searchBrowse();//获取用户的浏览记录
        request.getSession().setAttribute("cartList",cartList);
        request.getSession().setAttribute("browseList",browseList);
        return "redirect:/resultjsp/shopping";
    }

    /**
     * 跳转到账户充值页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/torecharge")
    public String toReCharge(HttpServletRequest request){
        return "redirect:/resultjsp/recharge";
    }

    /**
     * 退出登陆
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpServletRequest request)throws IOException {
        request.getSession().removeAttribute("user");
        return "redirect:/resultJsp/login";
    }


    /**
     * 获取所有游戏类型得游戏集合
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getGamesByPant")
    public String getGamesByPant(){
        return JSON.toJSONString(gamesBiz.findAll());
    }

    /**
     * 游戏充值页面分页查找点卡信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCardsPage")
    public String getCardsPage(HttpServletRequest request){
        String id=request.getParameter("id");//获得游戏id
        String index=request.getParameter("index");
        String info=request.getParameter("info");//模糊查询的条件
        int pageindex=index==""?1:Integer.parseInt(index);//获得起始页
        //区分第二次查询分页数据，游戏类型就保存在session中,第二次查询tid 传值为0
        int gid=Integer.parseInt(id);
        if(info=="" ||info==null){ //如果没有输入模糊查询的条件，避开两个类型条件的混合：
            if(gid==0){
                gid=(Integer) request.getSession().getAttribute("gid");
            }else {
                request.getSession().setAttribute("gid",gid);
            }
        }
        Pages<Cards> pages=cardsBiz.searchPage(0,gid,info,pageindex,8);
        return JSON.toJSONString(pages);
    }


    /**
     * 选择单个商品跳转到提交订单页面
     * @return
     */
    @RequestMapping(value = "/toPayment/{cid}/{gid}/{price}/{gName}/{cName}/{num}")
    public String toPayment(@PathVariable("cid") int cid,
                            @PathVariable("gid") int gid,
                            @PathVariable("price") Double price,
                            @PathVariable("gName") String gName,
                            @PathVariable("cName") String cName,
                            @PathVariable("num") int num,
                           HttpServletRequest request) throws Exception{
        try {
            Cards bycards=new Cards(cid,cName,price,gid,gName,num);
            bycards.setTotalPrice(Calculation.getDouble(bycards.getPrice(),bycards.getByNum()));
            request.getSession().setAttribute("bycards",bycards);
            request.getSession().setAttribute("cty",1);//页面共用，用于区分
        }catch (NullPointerException n){
            request.getSession().setAttribute("error","操作已执行，请务重复提交！");
        }
        return "redirect:/resultjsp/payment";
    }

    /**
     * 购物车添加订单请求成功跳转到订单页面
     * @return
     */
    @RequestMapping(value = "/toPaymentOnly")
    public String toPaymentOnly(HttpServletRequest request){
        request.getSession().setAttribute("cty",2);//页面共用，用于区分
        return "redirect:/resultjsp/payment";
    }
    /**
     * 选择购物车商品提交订单跳转到商品详情页面
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/toPaymentother")
    public String toPaymentother(HttpServletRequest request) throws IOException{
        String jsonStr = IOUtils.toString(request.getInputStream(),"UTF-8");
        List<ShoppingCart> cardsListJson = JSON.parseObject(jsonStr, new TypeReference<List<ShoppingCart>>() {});
        /*System.out.println("JSONTOJAVAOBJ============"+cardsListJson);*/
        Double totalPir=0.0;
        int ret=0;
        if(cardsListJson.size()>0){
            ret=1;
            for (ShoppingCart c:cardsListJson) {
                c.setTotalPrice(Calculation.getDouble(c.getPrice(),c.getNum()));
                totalPir+=c.getTotalPrice();
            }
            request.getSession().setAttribute("totalPir",totalPir);
            request.getSession().setAttribute("cardsListJson",cardsListJson);
        }
        return JSON.toJSONString(ret);
    }

    /**
     * 订单成功跳转到选择支付方式页面
     * @param byType
     * @param request
     * @return
     */
    @RequestMapping(value = "/toChoicePayment/{byType}")
    public String toChoicePayment(@PathVariable("byType") int byType,
                                  HttpServletRequest request)throws  Exception{
        try {
            GameCardUser user = (GameCardUser)request.getSession().getAttribute("user");
            Order order=new Order();
            List<ShoppingCart> cardsListJson=new ArrayList<ShoppingCart>();
            if(byType==1){//单个订单详情
                Cards bycards = (Cards)request.getSession().getAttribute("bycards");
                order=new Order((bycards.getcName()+"充值"+bycards.getgName()),user.getId(),new Date(),(bycards.getTotalPrice()),1,1);//创建一个订单对象
                Orderdatil orderdatil=new Orderdatil(bycards.getcId(),bycards.getGid(),bycards.getByNum(),order.getByTime(),order.getTotalPrice());
                order.getOrderdatilList().add(orderdatil);
            }
            if(byType==2){//购物车多选的订单
                cardsListJson=(List<ShoppingCart>) request.getSession().getAttribute("cardsListJson");
                Double price=(Double) request.getSession().getAttribute("totalPir");
                order=new Order((cardsListJson.get(0).getcName()+"充值"+cardsListJson.get(0).getgName()),user.getId(),new Date(),(price),1,1);//创建一个订单对象
                for (ShoppingCart c:cardsListJson) {
                    Orderdatil orderdatil=new Orderdatil(c.getcId(),c.getGid(),c.getNum(),order.getByTime(),c.getTotalPrice());
                    order.getOrderdatilList().add(orderdatil);
                }
            }
            Order orders=orderBiz.addOrder(order);
            if(orders!=null){
                if(byType==1){
                    request.getSession().removeAttribute("bycards");//删除在session中的提交订单的点卡信息
                }
                if(byType==2){
                    for (ShoppingCart ca:cardsListJson) {
                        shoppingCartBiz.deleteShoppingCart(ca);//删除购物车数据
                    }
                    request.getSession().removeAttribute("totalPir");//删除在session中的提交订单的点卡信息
                    request.getSession().removeAttribute("cardsListJson");//删除在session中的提交订单的点卡信息
                }
            }
            request.getSession().setAttribute("ordId",1);//区别于一个订单和多个订单，也可以整合成一个集合
            request.getSession().setAttribute("orders",orders);
        }catch (NullPointerException n){
            request.getSession().setAttribute("error","操作已执行，请务重复提交！");
        }
        return "redirect:/resultjsp/paymentChoice";

    }

    /**
     * 帐户余额支付9
     * @param oid
     * @param request 9
     * @return
     */
    @RequestMapping(value = "/paymentCard/{oid}")
    public String paymentCard(@PathVariable("oid") Long oid,
                              HttpServletRequest request) throws Exception{
        try {
            GameCardUserDatil userDatil=(GameCardUserDatil)request.getSession().getAttribute("userDatil");
            if(oid==1){//支付全部未支付的订单
                List<Order> orderList=orderBiz.searchOrderByType(userDatil.getLoginID(),1);//可能有删除的操作，不能在session中获取
                int result=orderBiz.updateOrders(orderList,userDatil,9);
                request.getSession().setAttribute("orderList",orderList);
                request.getSession().setAttribute("result",result);
                request.getSession().setAttribute("ordId",2);//区别于一个订单和多个订单
                request.getSession().removeAttribute("myorderList");//删除session中的数据
                request.getSession().removeAttribute("totalPrcie");
            }else {
                Order order=(Order) request.getSession().getAttribute("orders");
                if(oid.longValue()!=order.getId().longValue()){//确认订单，不是就重新查找
                    order=orderBiz.searchByInfo(new Order(oid));
                }
                int result=orderBiz.updateOrder(order,userDatil,9);
                request.getSession().setAttribute("order",order);
                request.getSession().setAttribute("ordId",1);//区别于一个订单和多个订单，也可以整合成一个集合
                request.getSession().setAttribute("result",result);
                request.getSession().removeAttribute("orders");//删除session中的数据
            }
        }catch (NullPointerException n){
            request.getSession().setAttribute("error","操作已执行，请务重复提交！");
        }
        return "redirect:/resultjsp/paymentResult";
    }



    /**
     * 跳转到我得订单页面
     * @return
     */
    @RequestMapping(value = "/tomyOrders")
    public String tomyOrders(HttpServletRequest request){
        GameCardUser user = (GameCardUser)request.getSession().getAttribute("user");
        List<Order> myorderList=orderBiz.searchOrderByType(user.getId(),1);//查找未支付的订单集合信息
        request.getSession().setAttribute("myorderList",myorderList);
        return "redirect:/resultjsp/myOrders";
    }

    /**
     * 跳转到我的全部订单
     * @param request
     * @return
     */
    @RequestMapping(value = "/toOrderList")
    public String toOrderList(HttpServletRequest request){
        GameCardUser user = (GameCardUser)request.getSession().getAttribute("user");
        Pages<Order> orderPages=orderBiz.searchPage(user.getId(),0,1,10);
        request.getSession().setAttribute("orderPages",orderPages);
        return "redirect:/resultjsp/orderList";
    }

    /**
     * 加入购物车
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveCart")
    public String saveCart(HttpServletRequest request){
        int cid=Integer.parseInt(request.getParameter("cid"));
        int gid=Integer.parseInt(request.getParameter("gid"));
        double price=Double.parseDouble(request.getParameter("price"));
        int num=Integer.parseInt(request.getParameter("num"));
        int uId=Integer.parseInt(request.getParameter("uId"));
        ShoppingCart cart=new ShoppingCart(uId,cid,gid,num,Calculation.getDouble(price,num));
        int result=shoppingCartBiz.addShoppingCart(cart);
        return JSON.toJSONString(result);
    }

    /**
     * 删除单个订单信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/toDeleteOrder")
    public String toDeleteOrder(HttpServletRequest request){
        Long ordId=Long.parseLong(request.getParameter("ordId")) ;
        int result=orderBiz.deleteOrderById(ordId);
        return JSON.toJSONString(result);
    }

    /**
     * 删除多个订单信息
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteOrders")
    public String deleteOrders(HttpServletRequest request){
        String[] id=request.getParameterValues("id");
        List<Order> orders=new ArrayList<Order>();
        for (int i=0;i<id.length;i++){
            orders.add(new Order(Long.parseLong(id[i])));
        }
        int ret=orderBiz.deleteOrderList(orders);
        return JSON.toJSONString(ret);
    }

    /**
     * 全部订单提交支付
     * @param request
     * @param
     * @return
     */
    @RequestMapping(value = "/getOrders")
    public String getOrders(HttpServletRequest request) throws Exception{
        try {
            GameCardUser user = (GameCardUser)request.getSession().getAttribute("user");
            List<Order> myorderList=orderBiz.searchOrderByType(user.getId(),1);//查找未支付的订单集合信息
            double totalPrcie=0;
            for (Order o:myorderList) {
                totalPrcie+=o.getTotalPrice();
            }
            request.getSession().setAttribute("myorderList",myorderList);
            request.getSession().setAttribute("totalPrcie",totalPrcie);
            request.getSession().setAttribute("ordId",2);//区别于一个订单和多个订单，也可以整合成一个集合
        }catch (NullPointerException n){
            request.getSession().setAttribute("error","操作已执行，请务重复提交！");
        }
        return "redirect:/resultjsp/paymentChoice";
    }
}
