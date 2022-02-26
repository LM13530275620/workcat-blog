import axios from 'axios'
import Element from 'element-ui'
import router from './router'
import store from './store'


axios.defaults.baseURL = "http://localhost:8077"//每个端口发起请求时会进行一个访问端口路径拼接

// 前置拦截
axios.interceptors.request.use(config => {
  return config
})
//后置拦截(拦截每次返回异常信息)
axios.interceptors.response.use(response => {
    let res = response.data;//获取每次提交的信息

    console.log("=================")
    console.log(res)
    console.log("=================")

    if (res.code === 200) {
      return response//状态码200是请求生效进行往下走
    } else {

      Element.Message.error('错了哦，这是一条错误消息', {duration: 3 * 1000})//Element自带弹窗(弹窗存活时间)

      return Promise.reject(response.data.msg)
    }
  },
  error => {
    console.log(error)
    if(error.response.data) {//error返回的信息不为空时(赋值)
      error.message = error.response.data.msg
    }

    if(error.response.status === 401) {
      store.commit("REMOVE_INFO")//401时清空信息
      router.push("/login")
    }

    Element.Message.error(error.message, {duration: 3 * 1000})
    return Promise.reject(error)// 防止回滚
  }
)