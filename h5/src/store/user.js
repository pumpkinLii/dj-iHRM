import * as API from '@/api/user'
import { Message } from 'element-ui'
import router from '../router'

const state = {
  isLogin: true
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
    API.login(param)
      .then(
        r => {
          console.log(r)
          commit('LOGIN')
          Message.success('登录成功')
          router.push('/dashboard')
        }
      ).catch(
        err => {
          console.log(err)
          Message.error('登录失败')
        }
      )
  },
  logout({ commit }) {
    API.logout()
      .then(
        r => {
          console.log(r)
          commit('LOGOUT')
          Message.success('注销成功')
          router.push('/login')
        }
      ).catch(
        err => {
          console.log(err)
          Message.error('注销失败')
        }
      )
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
