export default {
  welcome (username) {
    const date = new Date()
    const hour = date.getHours()
    let time = hour < 6 ? '早上好' : (hour <= 11 ? '上午好' : (hour <= 13 ? '中午好' : (hour <= 18 ? '下午好' : '晚上好')))
    let welcomeArr = [
      '喝杯咖啡休息下吧☕',
      '要不要和朋友打局LOL',
      '要不要和朋友打局守望先锋',
      '几天没见又更好看了呢😍',
      '今天又写了几个Bug🐞呢',
      '今天在群里吹水了吗',
      '今天吃了什么好吃的呢',
      '今天您微笑了吗😊',
      '今天帮助别人解决问题了吗',
      '准备吃些什么呢',
      '周末要不要去看电影？',
      '今天水惊惊群了吗？'
    ]
    let index = Math.floor((Math.random() * welcomeArr.length))
    return `${time}，${username}，${welcomeArr[index]}`
  },

  userString(str) {
    str = str.substr(1);
    str = str.substr(0, str.length - 1);
    return str
  },

  /**
   * data 按 name 分类
   * @param data
   * @param name String
   */
  formatList(data, name) {
    // 检测传参
    if(!name && typeof data !== Array && typeof name !== String){
      return
    }
    let obj = {}  // 申明一个大对象用来归类
    let Arr = []  // 申明一个数组仓库
    data.forEach(item => {
      !obj[item[name]] && (obj[item[name]] = [])
      obj[item[name]].push(item)
    })
    // 最后合并，把大对象里的所有值push进Arrr数组中
    Object.values(obj).forEach(item => {
      Arr.push(item)
    })
    return Arr
  },

  filterByTimeAndName(orderList, time, name) {
    let obj = {}
    let Arr = []
    orderList.forEach(item => {
      !obj[item[time]] && (obj[item[time]] = [])
      obj[item[time]].push(item)
    })
    for (let attr in obj) {
      let arr = obj[attr]
      obj[attr].total = 0
      obj[attr].time = attr
      for (let x of arr) {
        obj[attr].total += x.total
        obj[attr].consumerName = x.consumerName
        obj[attr].shopName = x.shopName
        obj[attr].cid = x.cid
        obj[attr].sid = x.sid
      }
    }
    // 最后合并，把大对象里的所有值push进Arrr数组中
    Object.values(obj).forEach(item => {
      Arr.push(item)
    })
    return Arr
  }
}
