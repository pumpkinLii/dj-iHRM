import request from '@/utils/request'

// 用于获得下拉菜单选项
export function getSelectOptional() {
  return request({
    url: '/start/get',
    method: 'post'
  })
}

export function idCheck(data) {
  return request({
    url: '/test/idCheck',
    method: 'post',
    data
  })
}

// 获得城市列表  //王欣艺的ip
export function getCityList() {
  return request({
    url: '/area/get',
    method: 'post'
  })
}

// 获取码表
export function getCodes() {
  return request({
    url: '/start/get',
    method: 'post'
  })
}

// 提交新增人员
export function submit(data) {
  return request({
    url: '/agent/doSave',
    method: 'post',
    data
  })
}

// 获取管理机构下拉列表 data:{code:'管理机构code'}
export function getManageCom(code) {
  return request({
    url: '/get/gets?Code=' + code,
    method: 'post'
  })
}

// 总监
export function posit() {
  return request({
    url: '/tes/g',
    method: 'post'
  })
}

// 经理
export function posit1() {
  return request({
    url: '/tes/m',
    method: 'post'
  })
}

// 团队架构
export function zhiji(data) {
  return request({
    url: 'http://10.11.114.60:9999/test/EchoGroup',
    method: 'post',
    data
  })
}

// 回显
export function huixian(data) {
  return request({
    url: 'http://10.11.114.60:9999/test/EchoManager',
    method: 'post',
    data
  })
}

// 根据agentCode查询人员信息接口
export function queryAgent(agentCode) {
  return request({
    url: '/test/QueryWithCode?agentCode=' + agentCode,
    method: 'post'
  })
}

// 修改团队信息
export function editPerson(data) {
  return request({
    url: '/agent/update',
    method: 'post',
    data
  })
}
