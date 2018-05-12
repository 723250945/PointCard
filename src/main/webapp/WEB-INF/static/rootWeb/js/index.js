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

    /*//4.切换图片鼠标悬浮事件
    function changeImg(){
        var index=0;//在集合中的索引
        var result=false;//控制判断
        var $img=$("#scroll_img").children("li");//图片集合，jQuery对象一定要带“$”
        var $page = $("#scroll_number").children("li");//数字集合

        //鼠标悬浮改变事件
        $page.mouseover(function(){
            result=true;//当有鼠标悬浮，赋值true
            index=$page.index($(this));//鼠标悬浮的(数字li)在它所在的数字集合中的索引
            $img.eq(index).stop(true,true).fadeIn().siblings().fadeOut();//显示当前下标的图片，它的同辈元素隐藏
            $(this).addClass("scroll_number_over").stop(true,true).siblings().removeClass("scroll_number_over");//给当前元素增加样式
        }).mouseout(function(){
            result=false;//鼠标不悬浮，不做处理，false
        });
        //定时器显示图片
        setInterval(function(){
            if(result==true){
                return;//鼠标悬浮时，不执行自动切换,跳出
            }else{
                index++;
                if(index>=$img.length){//当索引达到最大值时，重置为0
                    index=0;
                }
                $img.eq(index).stop(true,true).fadeIn().siblings().fadeOut();//显示当前下标的图片
                $page.eq(index).addClass("scroll_number_over").stop(true,true).siblings().removeClass("scroll_number_over");//显示数字
            }
        },2000);
    }
    changeImg();*/

   /* //5.书讯循环向上轮播
    function change(){
        var marginTop=0;//书讯信息第一个li标签上外边距
        var result=false;//流程控制
        //定时轮播
        var interval=setInterval(function(){
            if(result==true){
                return;
            }else{
                $("#express").children("li").first().animate({"margin-top":marginTop--},0,function(){//第一个li执行向上的动画
                    var $first=$(this);
                    if((!$first.is(":animated"))&&(-marginTop)>=$first.height()){//如果第一个li不在动画序列中,并且移动的绝对值大于等于第一个li的高度
                        $first.css({"margin-top":0}).appendTo($("#express")); //更正回第一个li的样式插入到ul中的末尾,该方法也可以用于上面的图片切换
                        marginTop=0;//重置
                    }
                });
            }
        },40);//40这个值可以调整速度的，值越大速度越慢
        //鼠标悬浮,当鼠标悬浮其上时为true,停止轮播
        $("#express").mouseover(function(){
            result=true;
        }).mouseout(function(){
            result=false;
        });
    }
    change();*/

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