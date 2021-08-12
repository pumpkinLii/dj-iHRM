import request from '@/utils/request'
import Vue from 'vue'

// 添加一个新的资格证信息
export function insert(data) {
  return request({
    url: Vue.config.productionTip === true ? '/certificate/insert' : 'http://10.11.116.111:9999/certificate/insert',
    method: 'post',
    data
  })
}

// 修改一个新的资格证信息
export function update(data) {
  return request({
    url: '/certificate/Update',
    method: 'post',
    data
  })
}

// 通过人员工号获取人员姓名
export function getAgentNameByAgentCode(data) {
  return request({
    url: Vue.config.productionTip === true ? '/certificate/searchNameById' : 'http://10.11.116.111:9999/certificate/searchNameById',
    method: 'post',
    data
  })
}

export function queryQualification(data, page) {
  return request({
    url: 'http://10.11.114.126:9999/certificate/retrieve?pageSize=' + page.pageSize + '&' + 'currentPage=' + page.currentPage,
    method: 'post',
    data
  })
}

export function getNextOptions(code) {
  return request({
    url: 'http://10.11.114.126:9999/certificate/returnCom?code=' + code,
    method: 'post'
  })
}

export function getInitializeList() {
  return request({
    url: 'http://10.11.114.126:9999/certificate/initList',
    method: 'post'
  })
}
