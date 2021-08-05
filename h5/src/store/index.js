import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import app from './app'
import settings from './settings'
import user from './user'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    settings,
    user
  },
  getters
})

export default store
