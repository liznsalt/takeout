export default {
  urlToTag(url) {
    switch (url) {
      case "/consumer": return "用户管理"
      case "/shop": return "商家管理"
      case "/home": return "首页"
      case "/index": return "首页"
      case "/order": return "订单管理"
      case "/monitor": return "访问监控"
      case "/chat": return "互动管理"
      case "/admininfo": return "个人中心"
    }
  },

  tagToUrl(tag) {
    switch (tag) {
      case "用户管理": return "/consumer"
      case "商家管理": return "/shop"
      case "首页": return "/home"
      case "订单管理": return "/order"
      case "访问监控": return "/monitor"
      case "互动管理": return "/chat"
      case "个人中心": return "/admininfo"
    }
  }
}
