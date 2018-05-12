/**
 * Created by Administrator on 2017/11/1.
 */
$(document).ready(function(){

    //1.弹出广告窗口
    window.open("open.html");
    //2.单击关闭 标签 隐藏
    $(window).scroll(function(){
        var st = $(this).scrollTop()+50;
        $("#right").css("top",st);
    });
    $("#dd_close>a").click(function(){
       $(this).parent().parent().hide();
    });
    var index=0;//在集合中的索引

    //6.鼠标移入图片时放大,用动画无意义
    $(".book ul li img").mouseenter(function(){
        $(this).css("width","100%");
    }).mouseleave(function(){
        $(this).css("width","80%");
    })

    //7.切换榜单,显示排行
    $(".tab ol li").mouseover(function(){
        var $li=$(".tab ol li");
        var $ul=$(".tab ul");
        var index=$li.index($(this));//得到当前选中ol li的索引
        $(this).css("background","#ffffff").add().siblings().css("background","#efefef");//改变背景颜色
        $ul.eq(index).stop(true,true).fadeIn().siblings("ul").fadeOut();//显示当前下标的ul，它的同辈元素隐藏
    })
    //鼠标经过榜单内容显示详细信息
    $(".tab ul li").mouseover(function(){
        //非常荣幸用到了end(),显示当前li的dl元素,隐藏其他li的dl元素并显示p元素(标题)
        $(this).children("dl").css("display","block").end().siblings("li").children("dl").css("display","none").end().siblings("li").children("p").css("display","block");
    });
});