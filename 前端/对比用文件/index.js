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
    path: '/login',
    name: 'login',
    component: () => import('../views/LoginView.vue')
  },
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
      {
        path:'/deparmentList',
        name: 'departmentList',
        component: () => import( '@/views/system/Department/DepartmentList.vue')
      },
      {
        path:'/userList',
        name: 'userList',
        component: () => import( '@/views/system/User/UserList.vue')
      },
      {
        path:'/menuList',
        name: 'menuList',
        component: () => import( '@/views/system/Menu/MenuList.vue')
      },
      {
        path:'/roleList',
        name: 'roleList',
        component: () => import( '@/views/system/Role/RoleList.vue')
      }
    ]
  },

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
