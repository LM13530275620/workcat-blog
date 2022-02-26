import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: '',
    userInfo: JSON.parse(sessionStorage.getItem("userInfo"))
  },
  mutations: {
    //set
    //获取登录时候返回的全局变量
    SET_TOKEN: (state, token) => {
      state.token = token
      localStorage.setItem("token", token)
    },//获取登录时候返回的全局变量
    SET_USERINFO: (state, userInfo) => {
      state.userInfo = userInfo
      sessionStorage.setItem("userInfo", JSON.stringify(userInfo))//会话保存(下次登录时认证) 实例化stringify
    },
    REMOVE_INFO: (state) => {
      state.token = ''
      state.userInfo = {}
      localStorage.setItem("token", '')
      sessionStorage.setItem("userInfo", JSON.stringify(''))
    }

  },
  getters: {
    // get
    getUser: state => {
      return state.userInfo//获取登录信息
    }

  },
  actions: {
  },
  modules: {
  }
})
