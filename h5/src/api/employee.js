import request from '@/utils/request'

// 王欣艺 已改
export function idCheck(data) {
  return request({
    url: '/login/YlAgentInsert/idCheck',
    method: 'post',
    data
  })
}

// 获得城市列表 已改
export function getCityList() {
  return request({
    url: '/login/get/area',
    method: 'post'
  })
}

// 获取码表
export function getCodes() {
  return request({
    url: '/login/get/idcode',
    method: 'post'
  })
}

// 提交新增人员 张晓成 已改
export function submit(data) {
  return request({
    url: '/login/YlAgentInsert/doSave',
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

// 总监 张毅珑 已改
export function posit() {
  return request({
    url: '/login/get/director',
    method: 'post'
  })
}

// 经理 张毅珑 已改
export function posit1() {
  return request({
    url: '/login/get/manage',
    method: 'post'
  })
}

// 团队架构 池浩玥 已改
export function zhiji(data) {
  return request({
    url: '/login/back/echoGroup',
    method: 'post',
    data
  })
}

// 回显 池浩玥 已改
export function huixian(data) {
  return request({
    url: '/login/back/echoManager',
    method: 'post',
    data
  })
}

// 根据agentCode查询人员信息接口 池浩玥 已改
export function queryAgent(agentCode) {
  return request({
    url: '/login/YlAgentUpdate/queryWithCode?agentCode=' + agentCode,
    method: 'post'
  })
}

// 修改团队信息 张晓成 已改
export function editPerson(data) {
  return request({
    url: '/login/YlAgentUpdate/update',
    method: 'post',
    data
  })
}
