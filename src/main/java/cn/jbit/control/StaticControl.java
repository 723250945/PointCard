package cn.jbit.control;

import cn.jbit.biz.*;
import cn.jbit.entity.*;
import cn.jbit.util.Browse;
import cn.jbit.util.Calculation;
import cn.jbit.util.Pages;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公共部分控制层
 */
@Controller
public class StaticControl {

    @Resource(name = "cardSalBiz")
    private CardSalBiz cardSalBiz;
    @Resource(name = "gamesTypeBiz")
    private GamesTypeBiz gamesTypeBiz;
    @Resource(name = "cardBiz")
    private CardsBiz cardsBiz;
    @Resource(name = "newsBiz")
    private NewsBiz newsBiz;
    @Resource(name = "gameCardUserBiz")
    private GameCardUserBiz gameCardUserBiz;
    @Resource(name = "gameCardUserDatilBiz")
    private GameCardUserDatilBiz gameCardUserDatilBiz;

    /**
     * 重定向到请求成功的页面,这个方法需要在拦截器里面放过，是没有经过用户验证的公用方法
     * @return
     */
    @RequestMapping("/resultJsp/{jspUrl}")
    public String resultJSP(@PathVariable("jspUrl") String jspUrl){
        return jspUrl;
    }
    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value = {"/index.jsp","/","index","/static/index"})
    public String todindex(){
        return "index";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping(value = "/static/toregist")
    public String toRegist(){
        return "redirect:/resultJsp/register";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping(value = "/static/tologin")
    public String tologin(){
        return "redirect:/resultJsp/login";
    }


    /**
     * 使用第三方帐号登录
     * @return
     */
    @RequestMapping(value = "/static/tologinOther")
    public String tologinOther(){
        return "redirect:/resultJsp/loginOther";
    }
    /**
     * 获得游戏类型
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/static/getGameType")
    public String getGameTypes(HttpServletRequest request){
        List<GamesType> gamesTypeList=new ArrayList<GamesType>();
        gamesTypeList=(List<GamesType>) request.getSession().getAttribute("gamesTypeList");
        if(null==gamesTypeList){
            gamesTypeList=gamesTypeBiz.findAll();
            request.getSession().setAttribute("gamesTypeList",gamesTypeList);
        }
        return JSON.toJSONString(gamesTypeList);
    }

    /**
     * 分页查询首页点卡信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/static/getCards")
    public String getPageByType(HttpServletRequest request)throws IOException {
        String id=request.getParameter("id");//获得游戏类型参数
        String index=request.getParameter("index");
        String info=request.getParameter("info");//模糊查询的条件
        int pageindex=index==""?1:Integer.parseInt(index);//获得起始页
        //区分第二次查询分页数据，游戏类型就保存在session中,第二次查询tid 传值为0
        int tid=Integer.parseInt(id);
        if(info=="" ||info==null){ //如果没有输入模糊查询的条件，避开两个类型条件的混合：
            if(tid==0){
                tid=(Integer) request.getSession().getAttribute("tid");
            }else {
                request.getSession().setAttribute("tid",tid);
            }
        }
        Pages<Cards> pages=cardsBiz.searchPage(tid,0,info,pageindex,6);
        return JSON.toJSONString(pages);
    }

    /**
     * 获取点卡销售榜单
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/static/getCardSalTop")
    public String getCardTop(HttpServletRequest request){
        List<CardSal> cardSalList=new ArrayList<CardSal>();
        cardSalList=(List<CardSal>) request.getSession().getAttribute("cardSalList");
        if(null==cardSalList){
            cardSalList=cardSalBiz.searchByNum();
            request.getSession().setAttribute("cardSalList",cardSalList);
        }
        return JSON.toJSONString(cardSalList);
    }

    /**
     * 获取最新上架点卡信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/static/getNewCards")
    public String getNewCardTop(HttpServletRequest request){
        List<Cards> newCards=new ArrayList<Cards>();
        newCards=(List<Cards>) request.getSession().getAttribute("newCards");
        if(null==newCards){
           newCards=cardsBiz.searchNewTime();
           request.getSession().setAttribute("newCards",newCards);
        }
        return JSON.toJSONString(newCards);
    }

    /**
     * 获取轮播广告信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/static/getCarousel")
    public String getCarousel(HttpServletRequest request){
        List<News> newsList=new ArrayList<News>();
        newsList=(List<News>) request.getSession().getAttribute("newsList");
        if(null==newsList){
            newsList=newsBiz.searchNews("广告链接");
            request.getSession().setAttribute("newsList",newsList);
        }
        return JSON.toJSONString(newsList);
    }

    /**
     * 获取弹窗广告信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/static/getWindoadvertising")
    public String getWindoadvertising(HttpServletRequest request){
        News windowNews=new News();
        windowNews=(News) request.getSession().getAttribute("windowNews");
        if(null==windowNews){
            windowNews=newsBiz.searchNews("弹窗广告链接").get(0);
            request.getSession().setAttribute("windowNews",windowNews);
        }
        return JSON.toJSONString(windowNews);
    }

    /**
     * 获取首页咨询信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/static/getCardCarousel")
    public String getCardCarousel(HttpServletRequest request){
        List<News> cardSals=new ArrayList<News>();
        cardSals=(List<News>) request.getSession().getAttribute("cardSals");
        if(null==cardSals){
            cardSals=newsBiz.searchNews("销售咨询");
            request.getSession().setAttribute("cardSals",cardSals);
        }
        return JSON.toJSONString(cardSals);
    }

    /**
     * 首页查看资讯详细信息
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/static/toNewsView/{id}")
    public String toNewsIndex(@PathVariable("id") int id,
                              HttpServletRequest request){
        List<News> newlist=(List<News>)request.getSession().getAttribute("cardSals");
        if(null==request.getSession().getAttribute("recruitList")){//这个判断避免重复查询，减少数据库压力
            List<News> recruitList=newsBiz.searchNews("诚聘英才");
            List<News> cooperationList=newsBiz.searchNews("商务合作");
            List<News> concatList=newsBiz.searchNews("联系我们");
            List<News> weList=newsBiz.searchNews("关于我们");
            request.getSession().setAttribute("recruitList",recruitList);
            request.getSession().setAttribute("cooperationList",cooperationList);
            request.getSession().setAttribute("concatList",concatList);
            request.getSession().setAttribute("weList",weList);
        }
        News news=null;
        for (News newss:newlist) {
            if(newss.getnId()==id){
                news=newss;
                break;
            }
        }
        request.getSession().setAttribute("news",news);
        return "redirect:/resultJsp/NewsView";
    }
    /**
     * 跳转到关于我们
     * @return
     */
    @RequestMapping(value = "/static/toNews")
    public String toNewPage(HttpServletRequest request){
        //新闻咨询在首页已经加载
        List<News> weList=new ArrayList<News>();
        if(null==request.getSession().getAttribute("weList")){//这个判断避免重复查询，减少数据库压力
            List<News> recruitList=newsBiz.searchNews("诚聘英才");
            List<News> cooperationList=newsBiz.searchNews("商务合作");
            List<News> concatList=newsBiz.searchNews("联系我们");
            weList=newsBiz.searchNews("关于我们");
            request.getSession().setAttribute("recruitList",recruitList);
            request.getSession().setAttribute("cooperationList",cooperationList);
            request.getSession().setAttribute("concatList",concatList);
            request.getSession().setAttribute("weList",weList);
        }else {
            weList=(List<News>) request.getSession().getAttribute("weList");
        }
        News news=weList.get(0);
        request.getSession().setAttribute("news",news);
        return "redirect:/resultJsp/NewsBulletin";
    }

    /**
     * 登录的方法
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/static/login")
    public String login(HttpServletRequest request)throws IOException{
        String loginName=request.getParameter("name");
        String password=request.getParameter("pwd");
        GameCardUser user=gameCardUserBiz.login(new GameCardUser(loginName,password));
        int result=user==null?0:2;
        //判断用户存在并且账号正常，IsState=1冻结账号不能使用
        if(user!=null && user.getIsState()==0){//0账号密码错误1正常登陆2账号冻结
            result=1;
            GameCardUserDatil userDatil=gameCardUserDatilBiz.searchnfo(user.getId());//获得用户得详细信息
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("userDatil",userDatil);
            if(Browse.operation==1){//判断是要买某件商品
               result=3;
            }
        }
        return JSON.toJSONString(result);
    }

    /**
     * 验证注册得用户名是否可用
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/static/checkName")
    public String checkName(HttpServletRequest request){
        String APKName=request.getParameter("APKName");
        GameCardUser user=gameCardUserBiz.fingByName(APKName);
        String result=user==null?"notexist":"exist";
        return JSON.toJSONString(result);
    }

    /**
     * 用户注册
     * @param userDatil
     * @return 结果大于2插入两条数据成功能
     */
    @ResponseBody
    @RequestMapping(value = "/static/registuser")
    public Object registUser(GameCardUserDatil userDatil){
        int result=gameCardUserBiz.addUser(new GameCardUser(userDatil.getLoginName(),userDatil.getPassword(),1,0));
        userDatil.setUserChange(0.0);
        userDatil.setLoginID(result);
        userDatil.setTotalPrice(0.0);
        userDatil.setUserStep(0);
        userDatil.setCreateTime(new Date());
        int isok=0;
        if(result>0){ //第一条插入成功，再插入第二条
            isok=gameCardUserDatilBiz.addUserDatil(userDatil);
        }
        return JSON.toJSONString(isok);
    }



    /**
     * 跳转到新闻列表
     * @param id
     * @return
     */
    @RequestMapping(value = "/static/toNewList/{id}")
    public String toNewList(@PathVariable("id") int id,
                            HttpServletRequest request){
        List<News> newlist=new ArrayList<News>();
        switch (id){
            case 1:
                newlist=(List<News>)request.getSession().getAttribute("cardSals");
                break;
            case 2:
                newlist=(List<News>)request.getSession().getAttribute("recruitList");
                break;
            case 3:
                newlist=(List<News>)request.getSession().getAttribute("cooperationList");
                break;
            case 4:
                newlist=(List<News>)request.getSession().getAttribute("concatList");
                break;
            case 5:
                newlist=(List<News>)request.getSession().getAttribute("weList");
                break;
        }
        request.getSession().setAttribute("newlist",newlist);
        request.getSession().setAttribute("tid",id);
        return "redirect:/resultJsp/newList";
    }

    /**
     * 跳转到新闻详情页面
     * @param type
     * @param id
     * @return
     */
    @RequestMapping(value = "/static/toNewView/{type}/{id}")
    public String toNewsView(@PathVariable("type") int type,
                             @PathVariable("id") int id,
                             HttpServletRequest request){
        News news=null;
        List<News> newlist=new ArrayList<News>();
        switch(type){
            case 1:
                newlist=(List<News>)request.getSession().getAttribute("cardSals");
                break;
            case 2:
                newlist=(List<News>)request.getSession().getAttribute("recruitList");
                break;
            case 3:
                newlist=(List<News>)request.getSession().getAttribute("cooperationList");
                break;
            case 4:
                newlist=(List<News>)request.getSession().getAttribute("concatList");
                break;
            case 5:
                newlist=(List<News>)request.getSession().getAttribute("weList");
                break;
        }
        for (News newss:newlist) {
            if(newss.getnId()==id){
                news=newss;
                break;
            }
        }
        request.getSession().setAttribute("news",news);
        return "redirect:/resultJsp/NewsView";
    }



    /**
     * 跳转到点卡详情页面
     * @param cid
     * @param gid
     * @return
     */
    @RequestMapping(value = "/static/toCardView/{cid}/{gid}")
    public String toCardView(@PathVariable("cid") int cid,
                             @PathVariable("gid") int gid,
                             HttpServletRequest request){

        Cards cards=new Cards(cid,gid);
        cards=cardsBiz.searchCard(cards);
        List<PraiseRate> praiseRateList=cardsBiz.searchPraiseRate(cards.getcId());
        List<CardPcitrue> pcitrueList=cardsBiz.searchByCid(cards.getcId());
        request.getSession().setAttribute("cards",cards);
        request.getSession().setAttribute("praiseRateList",praiseRateList);
        request.getSession().setAttribute("pcitrueList",pcitrueList);
        Browse.records.add(new Cards(cid,gid));//保存浏览记录
        Calculation.removesizeCard(Browse.records,10);
        return "redirect:/resultJsp/cardView";
    }

    /**
     * 记忆登陆
     * @return
     */
    @RequestMapping(value = "/byAgin")
    public String byAgin(){
        return "redirect:/resultJsp/cardView";
    }

    /**
     * 跳转到帮助中心
     * @return
     */
    @RequestMapping(value = "/static/help")
    public String tohelp(HttpServletRequest request){
        List<News> guideList=newsBiz.searchNews("购物指南");
        List<News> guaranteeList=newsBiz.searchNews("正版保障");
        List<News> membeList=newsBiz.searchNews("会员折扣");
        List<News> afterSalList=newsBiz.searchNews("售后服务");
        request.getSession().setAttribute("guideList",guideList);
        request.getSession().setAttribute("guaranteeList",guaranteeList);
        request.getSession().setAttribute("membeList",membeList);
        request.getSession().setAttribute("afterSalList",afterSalList);
        List<News> helpList=guideList;//默认显示第一个
        request.getSession().setAttribute("helpList",helpList);
        return "redirect:/resultJsp/helpList";
    }

    /**
     * 帮助中心显示帮助子菜单
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/static/tohelfList/{id}")
    public String tohelfList(@PathVariable("id")int id,
                             HttpServletRequest request){
        List<News> helpList=new ArrayList<News>();
        switch (id){
            case 1:
                helpList=(List<News>) request.getSession().getAttribute("guideList");
                break;
            case 2:
                helpList=(List<News>) request.getSession().getAttribute("guaranteeList");
                break;
            case 3:
                helpList=(List<News>) request.getSession().getAttribute("membeList");
                break;
            case 4:
                helpList=(List<News>) request.getSession().getAttribute("afterSalList");
                break;

        }
        request.getSession().setAttribute("helpList",helpList);
        request.getSession().setAttribute("nid",id);
        return "redirect:/resultJsp/helpList";
    }

    /**
     * 跳转到帮助中心详细信息页面
     * @param id
     * @param nid
     * @param request
     * @return
     */
    @RequestMapping(value = "/static/tohelpView/{id}/{nid}")
    public String tohelfView(@PathVariable("id") int id,
                             @PathVariable("nid") int nid,
                             HttpServletRequest request){
        News news=null;
        List<News> newlist=new ArrayList<News>();
        switch(id){
            case 1:
                newlist=(List<News>)request.getSession().getAttribute("guideList");
                break;
            case 2:
                newlist=(List<News>)request.getSession().getAttribute("guaranteeList");
                break;
            case 3:
                newlist=(List<News>)request.getSession().getAttribute("membeList");
                break;
            case 4:
                newlist=(List<News>)request.getSession().getAttribute("afterSalList");
                break;
        }
        for (News newss:newlist) {
            if(newss.getnId()==nid){
                news=newss;
                break;
            }
        }
        request.getSession().setAttribute("news",news);
        return "redirect:/resultJsp/helfView";
    }

}
