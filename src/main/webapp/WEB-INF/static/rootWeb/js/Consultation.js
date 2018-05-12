/**
 * Created by Administrator on 2017/11/1.
 */
$(document).ready(function(){


    //5.书讯循环向上轮播
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
    change();

});