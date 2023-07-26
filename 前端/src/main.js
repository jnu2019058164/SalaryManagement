import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Fragment from 'vue-fragment'
//引入弹性盒子布局样式
import './assets/css/flex.css'
//引入ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';
Vue.use(Fragment.Plugin)

Vue.use(ElementUI);
Vue.use(Fragment.Plugin);

Vue.config.productionTip = false
Vue.prototype.$http = axios;

router.beforeEach((to, from, next) => {
  //to 即将进入的路由
  //from即将离开的路由
  //设置tabs数据
  store.commit('getTabs');
  //设置激活选项卡
  store.commit('setActiveTabs', to.name);
  next(); //继续往下执行
})


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
