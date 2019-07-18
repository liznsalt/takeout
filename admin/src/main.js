// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import request from './utils/request'
import db from './utils/localstorage'
import store from './store'
import _gl from './utils/global'

Vue.use(ElementUI)
Vue.use(db)

Vue.prototype.$db = db

Vue.prototype.$post = request.post
Vue.prototype.$get = request.get
Vue.prototype.$put = request.put
Vue.prototype.$delete = request.delete
Vue.prototype.$syspost = request.syspost
Vue.prototype.$sysget = request.sysget

Vue.prototype.GLOBAL = _gl // 全局

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>',
  beforeCreate () {
    Vue.prototype.bus = this; // 定义全局组件通信
  }
})
