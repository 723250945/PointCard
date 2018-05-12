/**
 * Created by Administrator on 2017/11/1.
 */
$(document).ready(function(){


    //4.切换图片鼠标悬浮事件
    function changeImg(){
        var indexaa=0;//在集合中的索引
        var result=false;//控制判断
        var $img=$("#scroll_img").children("li");//图片集合，jQuery对象一定要带“$”
        var $page = $("#scroll_number").children("li");//数字集合

        //鼠标悬浮改变事件
        $page.mouseover(function(){
            result=true;//当有鼠标悬浮，赋值true
            indexaa=$page.index($(this));//鼠标悬浮的(数字li)在它所在的数字集合中的索引
            $img.eq(indexaa).stop(true,true).fadeIn().siblings().fadeOut();//显示当前下标的图片，它的同辈元素隐藏
            $(this).addClass("scroll_number_over").stop(true,true).siblings().removeClass("scroll_number_over");//给当前元素增加样式
        }).mouseout(function(){
            result=false;//鼠标不悬浮，不做处理，false
        });
        //定时器显示图片
        setInterval(function(){
            if(result==true){
                return;//鼠标悬浮时，不执行自动切换,跳出
            }else{
                indexaa++;
                if(indexaa>=$img.length){//当索引达到最大值时，重置为0
                    indexaa=0;
                }
                $img.eq(indexaa).stop(true,true).fadeIn().siblings().fadeOut();//显示当前下标的图片
                $page.eq(indexaa).addClass("scroll_number_over").stop(true,true).siblings().removeClass("scroll_number_over");//显示数字
            }
        },2000);
    }
    changeImg();

});