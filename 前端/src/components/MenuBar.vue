<template>
  <el-menu :default-active="$route.path" class="el-menu-vertical-demo" :collapse="isCollapse" :unique-opened="true">
    <menu-item :menuList="menuList"></menu-item>
  </el-menu>
</template>

<script>
  import MenuItem from "../components/MenuItem.vue";
  import { mapState } from "vuex";
  export default {
    name: "MenuBar",
    computed: {
      ...mapState({
        //获取菜单数据
        menuList: (state) => state.MenuStore.menu_data,
        //获取vuex中的isCollapse收缩属性
        isCollapse: (state) => state.MenuStore.isCollapse,
      }),
    },
    components: {
      MenuItem,
    },
    data() {
      return {
        isCollapse: this.isCollapse,
        menuList: [


          // 标签头括号，括号内容为一个父标签及其子标签
          {
            // 子类标签，父类在下面
            children: [
              {
                children: [],
                code: "sys:Comp",
                createTime: 1586703509000,
                icon: "el-icon-phone-outline",
                id: 33,
                isHome: 0,
                label: "员工管理",
                name: "StfManage",
                orderNum: 2,
                parentId: 17,
                path: "/StfManage",
                remark: "员工管理",
                type: "1",
                updateTime: 1586337139000,
                url: "/system/StfMg/StfManage",
              },

              {
                children: [],
                code: "sys:Hsh",
                createTime: 1586703509001,
                icon: "el-icon-user",
                id: 34,
                isHome: 0,
                label: "部门管理",
                name: "DeptMg",
                orderNum: 2,
                parentId: 17,
                path: "/DeptMg",
                remark: "部门管理",
                type: "1",
                updateTime: 1586337139000,
                url: "/system/DeptMg/DeptManage"//views下面的system做自己的项目，做好了通过url引入到对应的组件中
              },

              {
                children: [],
                code: "sys:Rpl",
                createTime: 1691464271000,
                icon: "el-icon-suitcase",
                id: 35,
                isHome: 0,
                label: "薪资报表",
                name: "SalaryMg",
                orderNum: 4,
                parentId: 17,
                path: "/SalaryMg",
                type: "1",
                updateTime: 1691565988000,
                url: "/system/SalaryMg/SalaryManage"
              },
              {
                children: [],
                code: "sys:SalMg",
                createTime: 1691464271000,
                icon: "el-icon-wallet",
                id: 36,
                isHome: 0,
                label: "薪资项管理",
                name: "SalMg",
                orderNum: 5,
                parentId: 17,
                path: "/SalMg",
                type: "1",
                updateTime: 1691565988000,
                url: "/system/SalMg/SalManage"
              }
            ],

            // 从这里开始是上面子类标签的父类
            code: "sys:CompM",
            createTime: 1691464271000,
            icon: "el-icon-s-tools",
            id: 17,
            isHome: 0,
            label: "系统管理",
            orderNum: 1,
            parentId: 0,
            path: "/system",
            type: "0",
            updateTime: 1691565988000,
          },


        ],
      };
    },
  };
</script>

<style lang="scss" scoped>
  .el-menu {
    border-right: none !important;
  }

  .menu-bar:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
</style>