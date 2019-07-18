import axios from 'axios'
import db from './localstorage'
import {Dialog, Notification, MessageBox, Message} from "element-ui"
import store from '../store'
import moment from 'moment'
moment.locale('zh-cn')

// 统一配置
let REQUEST = axios.create({
  baseURL: 'http://localhost:8080/takeout/',
  responseType: 'json',
  validateStatus (status) {
    // 200 外的状态码都认定为失败
    return status === 200
  }
})

/////////////////
let SYS_REQUEST = axios.create({
  baseURL: 'http://localhost:8080/takeout/',
  responseType: 'json',
  validateStatus (status) {
    // 200 外的状态码都认定为失败
    return status === 200
  }
})
///////////////////

REQUEST.interceptors.request.use((config) => {
  let expireTime = store.state.account.expireTime
  let now = (new Date()).valueOf()
  // 让token早10秒种过期，提升“请重新登录”弹窗体验
  if (now - expireTime >= -10 * 1000) {
    MessageBox.alert(
      '很抱歉，登录已过期，请重新登录',
      '系统提示',
      {
        confirmButtonText: '确定',
        type: 'warning',
        center: true
      }
    ).then(() => {
      // 刷新
      db.clear();
      location.reload()
    })
  }
  // 有 token就带上
  if (store.state.account.token) {
    config.headers.Authentication = store.state.account.token
  }
  return config
}, (error) => {
  return Promise.reject(error)
})

// 拦截响应
REQUEST.interceptors.response.use((config) => {
  return config
}, (error) => {
  if (error.response) {
    let errorMessage = error.response.data === null ? '错误' : error.response.data.message
    console.log(error.response)
    switch (error.response.status) {
      case 404:
        Message.error({
          message: '很抱歉，资源找不到'
        })
        break
      case 403:
      case 401:
        Message.error({
          message: '操作失败，请联系管理员'
        })
        break
      default:
        Message.error({
          message: errorMessage
        })
        break
    }
  }
  return Promise.reject(error)
})

const request = {
  post (url, params) {
    return REQUEST.post(url, params, {
      transformRequest: [(params) => {
        let result = ''
        Object.keys(params).forEach((key) => {
          if (!Object.is(params[key], undefined) && !Object.is(params[key], null)) {
            result += encodeURIComponent(key) + '=' + encodeURIComponent(params[key]) + '&'
          }
        })
        return result
      }],
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    })
  },
  put (url, params) {
    return REQUEST.put(url, params, {
      transformRequest: [(params) => {
        let result = ''
        Object.keys(params).forEach((key) => {
          if (!Object.is(params[key], undefined) && !Object.is(params[key], null)) {
            result += encodeURIComponent(key) + '=' + encodeURIComponent(params[key]) + '&'
          }
        })
        return result
      }],
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    })
  },
  get (url, params) {
    let _params
    if (Object.is(params, undefined)) {
      _params = ''
    } else {
      _params = '?'
      for (let key in params) {
        if (params.hasOwnProperty(key) && params[key] !== null) {
          _params += `${key}=${params[key]}&`
        }
      }
    }
    return REQUEST.get(`${url}${_params}`)
  },
  delete (url, params) {
    let _params
    if (Object.is(params, undefined)) {
      _params = ''
    } else {
      _params = '?'
      for (let key in params) {
        if (params.hasOwnProperty(key) && params[key] !== null) {
          _params += `${key}=${params[key]}&`
        }
      }
    }
    return REQUEST.delete(`${url}${_params}`)
  },

  // 系统内置
  syspost (url, params) {
    return SYS_REQUEST.post(url, params, {
      transformRequest: [(params) => {
        let result = ''
        Object.keys(params).forEach((key) => {
          if (!Object.is(params[key], undefined) && !Object.is(params[key], null)) {
            result += encodeURIComponent(key) + '=' + encodeURIComponent(params[key]) + '&'
          }
        })
        return result
      }],
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    })
  },
  sysget (url, params) {
    let _params
    if (Object.is(params, undefined)) {
      _params = ''
    } else {
      _params = '?'
      for (let key in params) {
        if (params.hasOwnProperty(key) && params[key] !== null) {
          _params += `${key}=${params[key]}&`
        }
      }
    }
    return SYS_REQUEST.get(`${url}${_params}`)
  }
}

export default request
