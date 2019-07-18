import Vue from 'vue'
import Router from 'vue-router'
import db from '../utils/localstorage'
import Login from "../views/login/Login"
import Index from "../views/Index"
import ConsumerTable from "../views/manager/ConsumerTable"
import ShopTable from "../views/manager/ShopTable"
import Home from "../views/common/Home"
import OrderTable from "../views/manager/OrderTable"
import ChatTable from "../views/manager/ChatTable"
import Monitor from "../views/monitor/Monitor"
import AdminInfo from "../views/common/AdminInfo"

Vue.use(Router)

let router = new Router({
  routes: [
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/index',
      redirect: '/home',
      component: Index,
      children: [
        {
          path: '/consumer',
          component: ConsumerTable
        },
        {
          path: '/shop',
          component: ShopTable
        },
        {
          path: '/home',
          component: Home
        },
        {
          path: '/order',
          component: OrderTable
        },
        {
          path: '/chat',
          component: ChatTable
        },
        {
          path: '/monitor',
          component: Monitor
        },
        {
          path: '/admininfo',
          component: AdminInfo
        }
      ]
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    next()
  } else {
    let token = db.get('USER_TOKEN')
    let user = db.get('USER')
    if (token.length && user) {
      next()
    } else {
      next('/login')
    }
  }
})

export default router
