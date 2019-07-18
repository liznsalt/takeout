// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI, {Notification} from 'element-ui' //引入js
import 'element-ui/lib/theme-chalk/index.css'//引入css
import db from'./utils/localstorage'
import store from './store'
import request from './utils/request'
import _global from './utils/global'

Vue.use(ElementUI);
Vue.use(db)


Vue.prototype.$db = db

// axios接口
Vue.prototype.$post = request.post
Vue.prototype.$get = request.get
Vue.prototype.$put = request.put
Vue.prototype.$delete = request.delete

Vue.prototype.$syspost = request.syspost
Vue.prototype.$sysget = request.sysget

// config
Vue.config.productionTip = false;
Vue.prototype.GLOBAL = _global; // 定义全局变量

// js时间格式化 @[网上找的方法]
Date.prototype.Format = function (fmt) {
  let o = {
    "M+": this.getMonth() + 1, //月份
    "d+": this.getDate(), //日
    "h+": this.getHours(), //小时
    "m+": this.getMinutes(), //分
    "s+": this.getSeconds(), //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    "S": this.getMilliseconds() //毫秒
  }
  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (let k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
}

// 判断当前浏览器是否支持WebSocket
// 1  消息通知
// 2  购买通知
if ('WebSocket' in window) {
  Vue.prototype.$webscoket = new WebSocket("ws://localhost:8080/takeout/myWebSocket")
  Vue.prototype.$buyNotification = new WebSocket("ws://localhost:8080/takeout/buyNotification")
  // 设置新订单通知
  Vue.prototype.$buyNotification.onmessage = function (res) {
    // 判断是否是自己的新订单
    let data = JSON.parse(res.data)
    if (db.get('ROLES') === '"shop"' && String(db.get('USER_ID')) === String(data.SID)) {
      Notification({
        title: "提示",
        message: "您有新的订单了，请及时处理！",
        duration: 0 // FIXME: 不关掉，让商家自己关
      })
    }
  }
} else {
  alert('当前浏览器不支持websocket');
}

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>',
  beforeCreate () {
    Vue.prototype.bus = this; // 定义全局组件通信

    // test
    // this.$db.clear()
    // db.save('USER_TOKEN', [1])
    // db.save('USER', 'liznsalt')
    // db.save('USER_ROUTER', [1])
  },
  closed() {
    // 关闭 websocket
    if (Vue.prototype.$webscoket !== null) {
      Vue.prototype.$webscoket.close()
      Vue.prototype.$buyNotification.close()
    }
  }
});
