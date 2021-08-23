import * as API from '@/api/user'
import { Message } from 'element-ui'
import router from '../router'

const state = {
  isLogin: sessionStorage.getItem('isLogin') === 'true'
}

const mutations = {
  LOGIN: state => {
    state.isLogin = true
  },
  LOGOUT: state => {
    state.isLogin = false
  }
}

const actions = {
  login({ commit }, param) {
    return API.login(param)
      .then(
        r => {
          sessionStorage.setItem('isLogin', 'true')
          commit('LOGIN')
          Message.success('登录成功')
          // router.push('/dashboard')
        }
      )
  },
  logout({ commit }) {
    sessionStorage.setItem('isLogin', 'false')
    commit('LOGOUT')
    Message.success('注销成功')
    router.push('/login')
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
