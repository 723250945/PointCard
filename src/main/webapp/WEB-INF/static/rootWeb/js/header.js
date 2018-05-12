/**
 * Created by Administrator on 2017/11/2.
 */
 $(document).ready(function(){
     //3.新手入门
     $("#menu").hover(function(){
         $("#dd_menu_top_down").slideDown("slow");
     },function(){
         $("#dd_menu_top_down").slideUp("slow");
     });
 })