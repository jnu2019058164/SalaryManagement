import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    redirect:"/login",
    children:[
      {
        path:' /desktop',
        name: 'desktop',
        component: () => import( '@/views/Desktop.vue')
      },

      //投诉管理
      {
        path:' /StfManage',         
        name: 'StfManage',          
        component: () => import( '@/views/system/StfMg/StfManage.vue')  //你的页面目录
      },
      // 住户管理
      {
        path:' /DeptMg',         
        name: 'DeptMg',          
        component: () => import( '@/views/system/DeptMg/DeptManage.vue')  //你的页面目录
      },
      // 缴费管理
      {
        path:' /SalMg',         
        name: 'SalMg',          
        component: () => import( '@/views/system/SalMg/SalManage.vue')  //你的页面目录
      },
      // 修理管理
      {
        path:' /SalaryMg',         
        name: 'SalaryMg',          
        component: () => import( '@/views/system/SalaryMg/SalaryManage.vue')  //你的页面目录
      },
    ]
  },
  // 登录
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/LoginView.vue')
  },
  // 关于
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },{
    path: '/box',
    name: 'box',
    component: () => import( '../views/test/Box.vue')
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
