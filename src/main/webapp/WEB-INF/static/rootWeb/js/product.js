/**
 * Created by Administrator on 2017/10/19.
 */
$(document).ready(function(){

    //单击分类下的一级菜单,显示二级菜单,再次点击隐藏二级菜单
    var initial=true;
    $(".nav dt").click(function(){
        if(initial==true){
            $(".nav dt").nextAll("dd").slideUp();
            $(this).nextAll("dd").slideDown();
            initial=false;
        }else{
            $(this).nextAll("dd").slideUp();
            initial=true;
        }
    });

    //获取大图形式元素
    function getBigBook(){
        var $html=$("#product_storyList_content");
        var $bookImg = $html.find(".product_storyList_content_left");
        var contents="";
        $html.find(".product_storyList_content_right").find("ul").each(function(i,ele){
            var div="";
            var content=[div,"<div class='big_img_list'><ul><li class='bookImg'>"+$($bookImg[i]).html()+"</li>"];
            var $li=$(ele).children("li");
            var $price=$($li[6]).find("span");
            content.push("<li><dl><dd class='footer_dull_red'>"
                +$($price[1]).text()+"</dd><dd class='product_content_delete'>"
                +$($price[2]).text()+"</dd><dd class='footer_dull_red'>"
                +$($price[0]).text()+"</dd></dl></li>");//价格
            content.push("<li class='detail'>"+$($li[5]).html()+"</li>");//简介
            content.push("<li class='detail'>"+$($li[2]).html()+"</li>");//作　者
            content.push("<li class='detail'>"+$($li[1]).html()+"</li>");//顾客评分
            content.push("<li class='detail'>"+$($li[3]).html()+"</li>");//出版社
            content.push("<li class='detail'>"+$($li[4]).html()+"</li></ul></div>");//出版时间
            contents+=content.join("");
        });
        return contents;
    }

    //2.列表模式和大图标模式的切换
    function listOrBig(){
        var $list=$("#storyBooksssss").siblings().clone(true);//复制列表形式元素
        var bigBookList=getBigBook();//获取大图形式List
        //点击切换大图/列表模式
        $("#product_array").children("a").click(function(){
            //判断
            $(this).siblings().removeClass("click").end().addClass("click");
            if($(this).attr("name")=="array"){//列表
                $("#product_storyList_content").empty().html($list);//添加列表元素
            }else{//大图
                $list=$("#storyBooksssss").siblings().clone(true);//复制列表形式元素
                $("#product_storyList_content").empty().html(bigBookList);//添加大图元素
                //
                $("#product_storyList_content").children(".big_img_list").find("ul").mouseover(function(){
                    $(this).addClass("over");
                    $(this).parent().addClass("over");
                }).mouseout(function(){
                    $(this).removeClass("over");
                    $(this).parent().removeClass("over");
                });
            }
        });
    }
    listOrBig();

    //动态加载 js /css
    function loadjscssfile(filename, filetype) {
        if (filetype == "js") { //判定文件类型
            var fileref = document.createElement('script')//创建标签
            fileref.setAttribute("type", "text/javascript")//定义属性type的值为text/javascript
            fileref.setAttribute("src", filename)//文件的地址
        }
        else if (filetype == "css") { //判定文件类型
            var fileref = document.createElement("link")
            fileref.setAttribute("rel", "stylesheet")
            fileref.setAttribute("type", "text/css")
            fileref.setAttribute("href", filename)
        }
        if (typeof fileref != "undefined")
            document.getElementsByTagName("head")[0].appendChild(fileref)
    }
});