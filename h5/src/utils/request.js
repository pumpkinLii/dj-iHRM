import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '@/store'

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent

    if (store.getters.token) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      // config.headers['X-Token'] = getToken()
    }
    return config
  },
  error => {
    console.log(error) // for debug TODO 上线前删除
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  response => {
    const res = response.data
    if (res.code && res.code !== 0) {
      if (res.code === 500) {
        // 500为服务器表示用户有一个错误
        Message({
          message: res.msg || '请求时发生错误',
          type: 'error',
          duration: 5 * 1000
        })
      } else if (res.code === 501) {
        // 501为服务器警告用户
        Message({
          message: res.msg || '请求参数错误',
          type: 'warning',
          duration: 5 * 1000
        })
      } else if (res.code === 502) {
        // 502为用户登录信息过期，需要用户重新登陆
        MessageBox.confirm('您的会话已过期，可以取消以停留在此页面，或重新登录', '提示', {
          confirmButtonText: '重新登陆',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // store.dispatch('user/resetToken').then(() => {
          //   location.reload()
          // })
          console.log('重新登陆')
        })
      }
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.log(error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
