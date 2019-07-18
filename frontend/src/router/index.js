import Vue from 'vue'
import Router from 'vue-router'
import LogIn from "@/views/login/LogIn"
import SignIn from "@/views/login/SignIn"
import ShoppingTrolley from "@/views/consumer/ShoppingTrolley"
import SetUp from "@/views/shop/SetUp"
import Index from "@/views/Index"
import TakeawayOrder from "@/views/consumer/TakeawayOrder"

import db from '../utils/localstorage'
import request from '../utils/request'
import App from "@/App"

import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import TakeawayService from "@/views/common/TakeawayService"
import ConsumerInfo from "@/views/consumer/info/ConsumerInfo"
import Info from "@/views/consumer/info/Info"
import Password from "@/views/consumer/info/Password"
import Setting from "@/views/consumer/info/Setting"
import History from "@/views/consumer/info/History"
import ShopInfo from "@/views/shop/info/ShopInfo"
import MenuEdit from "@/views/shop/menu_edit/MenuEdit"
import SInfo from "@/views/shop/info/SInfo"
import SHistory from "@/views/shop/info/SHistory"
import SContact from "@/views/shop/info/SContact"
import SPassword from "@/views/shop/info/SPassword"
import SSetting from "@/views/shop/info/SSetting"
import Contact from "@/views/consumer/info/Contact"
import ConsumerSignIn from "@/views/login/ConsumerSignIn"
import ShopSignIn from "@/views/login/ShopSignIn"
import Verify from "@/views/login/Verify"
import {Message, MessageBox} from "element-ui"
import ShopDetail from "@/views/consumer/shop_view/ShopDetail"
import OrderManager from "@/views/shop/service/OrderManager"
import PasswordReset from "@/views/login/PasswordReset"

// nprogress样式文件
NProgress.configure({
  easing: 'ease',  // 动画方式
  speed: 500,  // 递增进度条的速度
  showSpinner: false, // 是否显示加载ico
  trickleSpeed: 200, // 自动递增间隔
  minimum: 0.3 // 初始化时的最小百分比
})

Vue.use(Router);

let constRouter = [
  {
    path: '/',
    redirect: '/index'
  },
  {
    path: '/index',
    component: Index
  },
  {
    path: '/takeawayorder',
    component: TakeawayOrder
  },
  {
    path: '/login',
    component: LogIn,
  },
  // 更新密码
  {
    path: '/password_reset/:role',
    component: PasswordReset
  },
  {
    path: '/verify',
    component: Verify
  },
  {
    path: '/signin',
    component: SignIn,
  },
  {
    path:'/signin/consumer',
    component: ConsumerSignIn
  },
  {
    path:'/signin/shop',
    component: ShopSignIn
  },
  {
    path: '/shoppingtrolley',
    component: ShoppingTrolley
  },
  {
    path: '/setup',
    component: SetUp
  },
  {
    path: '/service',
    component: TakeawayService
  },
  {
    path: '/consumerinfo',
    component: ConsumerInfo,
    children: [
      {
        path: '/consumerinfo/',
        redirect: '/consumerinfo/info'
      },
      {
        path: '/consumerinfo/info',
        component: Info
      },
      {
        path: '/consumerinfo/history',
        component: History
      },
      {
        path: '/consumerinfo/password',
        component: Password
      },
      {
        path: '/consumerinfo/setting',
        component: Setting
      },
      {
        path: '/consumerinfo/contact',
        component: Contact
      }
    ]
  },
  {
    path: '/shopinfo',
    component: ShopInfo,
    children: [
      {
        path: '/shopinfo/',
        redirect: '/shopinfo/info'
      },
      {
        path: '/shopinfo/info',
        component: SInfo
      },
      {
        path: '/shopinfo/history',
        component: SHistory
      },
      {
        path: '/shopinfo/contact',
        component: SContact
      },
      {
        path: '/shopinfo/password',
        component: SPassword
      },
      {
        path: '/shopinfo/setting',
        component: SSetting
      }
    ]
  },
  {
    path: '/menuedit',
    component: MenuEdit
  },
  {
    path: '/ordermanager',
    component: OrderManager
  },
//  用户购买部分
  {
    path: '/shop/:id',
    component: ShopDetail
  }
]

let router = new Router({
  routes: constRouter
})

const whiteList = [
  '/index',
  '/login',
  '/verify',
  '/signin',
  '/signin/consumer',
  '/signin/shop',
  '/password_reset/consumer',
  '/password_reset/shop'
]

router.beforeEach((to, from, next) => {
  // 开启进度条
  NProgress.start();
  if (whiteList.indexOf(to.path) !== -1) {
    next()
  } else {
    let token = db.get('USER_TOKEN')
    let user = db.get('USER')
    if (token.length && user) {
      next()
    } else {
      Message.info("请先登录")
      // 本来就是登录界面，就不用更改
      if (from.path === '/login') {
        // 先结束路由进度
        NProgress.done()
        return
      }
      next('/login')
    }
  }
})

router.afterEach(() => {
  // 关闭进度条
  NProgress.done()
})

export default router
