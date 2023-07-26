<template>

    <el-container class="home">
      <!--头部-->
      <el-header class="header ub main-justify cross-center">
        <div class="header-left-text" style="padding-left:45%">企业薪资管理系统</div>
        <div class="header-right ub main-center cross-center">
         <div>
          <el-dropdown>
          <img class="user-img" src="../assets/images/avatar.jpg" alt="用户头像"/>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人中心</el-dropdown-item>
              <el-dropdown-item @click.native="logback">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

         </div>

         <div class="user-role">
          <div class="header-welcome">欢迎您，管理员</div>
          <div class="header-time">{{homeDate}}</div>
         </div>
        </div>
      </el-header>
       <!--内容部分-->
      <el-container>
          <!--左侧菜单-->
        <el-aside width="auto">
        <menu-bar></menu-bar>
         </el-aside>
        <el-container>
          <!--主体内容-->
          <el-main style="padding: 0;">
            <!--选项卡-->
            <i class="arrow-icon" :class="[isCollapse ? 'el-icon-s-unfold' : ' el-icon-s-fold']" @click="arrowBtn"></i>
          <tabs></tabs>
          <!-- 配置路由-->
          <router-view></router-view>
          </el-main>
         <!--尾部-->
          <el-footer class="footer ub main-center cross-center">copyright@版权所有：Panry Kee 2022-2030</el-footer>
        </el-container>
      </el-container>
    </el-container>

</template>

<script>
//引入菜单组件
import MenuBar from "../components/MenuBar.vue"
//引入tabs组件
import Tabs from "../components/Tabs.vue"
import {mapState} from 'vuex'

//定义一个全局变量
var $vueIndex;
export default {
  name: 'HomeView',
  //生命周期勾子函数，来调用显示时间的方法
  mounted(){
  $vueIndex=this;//获vue实例
  this.showTime();
  //动态刷新页面
  setInterval(function(){
    $vueIndex.showTime();
  },1000);
  },
    computed: {
    ...mapState({
      isCollapse: (state) => state.MenuStore.isCollapse,
    }),
  },
  components: {
    //注册菜单组件
    MenuBar,
    Tabs
  },
  data(){
   return{
    //显示头部时间
   homeDate:""
   }
  },methods: {
//收缩箭头点击事件
 arrowBtn(){
     //调用图标收缩的方法
     this.$store.commit('setOpenOrClose');
    },
    //显示时间
    showTime(){
var week = new Array(
"星期日",
"星期一",
"星期二",
"星期三",
"星期四",
"星期五",
"星期六"
);
var date = new Date();
var year = date.getFullYear();
var month = date.getMonth() + 1;
var day = date.getDate();
var hour = date.getHours();
var minutes = date.getMinutes();
var second = date.getSeconds();
this. homeDate =year +"."+(month <10 ? "0"+ month : month) +"."+day +" "+" "+ (hour < 10 ? "0" + hour : hour) +":"+(minutes < 10 ? "0" + minutes : minutes) +":"+ (second <10 ? "0"+ second : second) +"  "+ (week[date.getDay()] || "");

    },

    logback(){
     // console.log("*********");
     sessionStorage.clear();
     this.$router.push("/login");

    }

  }
}
</script>
<style lang="scss" scoped>
  /*解决图标不能点击的问题*/
 ::v-deep .el-tabs__header {
  position: static !important;
}

.home{
  height: 100%;
}
.header{
  background-color: #16bed8;
  color: #fff;
  padding: 0 20px;
}
.header-left-text{
  font-size: 24px;
  font-weight: bolder;
}
.header-welcome{
  font-size: 15px;
  font-weight: bolder;
}
.user-img{
    height: 45px;
    width: 45px;
    border-radius: 50%;
    cursor: pointer;

}
.header-time{
    margin-top: 2px;
    font-size: 14px;
  }

.user-role{
  margin-left: 10px;
}

  .el-aside{
    border-right:1px solid #e6e6e6;
  } 

.footer {
  background-color: #16bed8;
  color: #fff;
}
.arrow-icon{
  float: left;
  background: #eaedf1;
  border: 1px solid transparent;
  font-size: 25px;
  height: 39px;
  line-height: 39px!important;
  width: 40px;
  text-align: center;
}


</style>

