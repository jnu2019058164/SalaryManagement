<template>
  <fragment>
  <template v-for="menu in menuList">
   <el-submenu  v-if='menu.children.length>0'  :index="menu.path" :key='menu.path'>
      <template slot="title">
        <i :class="menu.icon"></i>
        <span style="font-size:15px;font-weight:600" slot="title">{{menu.label}}</span>
      </template>
     <!--循环调用子菜单-->
    <menu-item :menuList='menu.children'></menu-item>
    </el-submenu>
    <el-menu-item  @click="selectMenu(menu)" v-else :index="menu.path" :key='menu.path'>
      <i :class="menu.icon"></i>
      <span style="font-size:15px;font-weight:600"
  slot="title">{{menu.label}}</span>
    </el-menu-item>
  </template>
  </fragment>
</template>

<script>
    export default {
        name:'MenuItem',
        props:['menuList'],//接收父组件传过来的数据
        methods:{
           selectMenu(item){
           // console.log(item);
            //设置选项卡
           this.$store.commit('selectMenu',item);
         //设置路由
       this.$router.push({name:item.name});
      // this.$router.push(item.name);

           }
        }
    }
</script>

<style lang="scss" scoped>

</style>