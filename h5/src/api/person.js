import request from '@/utils/request'

// 查询团队信息
export function addPerson(data) {
  return request({
    url: 'http://10.11.115.18:9999/test/doSave',
    method: 'post',
    data
  })
}

// 管理机构下拉列表
export function xiala() {
  return request({
    url: 'http://10.11.115.18:9999/get/managecom',
    method: 'post'
  })
}
// 总监
export function posit() {
  return request({
    url: 'http://10.11.115.18:9999/tes/g',
    method: 'post'
  })
}
// 经理
export function posit1() {
  return request({
    url: 'http://10.11.115.18:9999/tes/m',
    method: 'post'
  })
}
// 团队架构
export function zhiji(data) {
  return request({
    url: 'http://10.11.115.18:9999/test/EchoGroup',
    method: 'post',
    data
  })
}
// 回显
export function huixian(data) {
  return request({
    url: 'http://10.11.115.18:9999/test/EchoManager',
    method: 'post',
    data
  })
}

// 根据agentCode查询人员信息接口
export function queryAgent(r) {
  return request({
    url: 'http://10.11.115.18:9999/agent/query/one?agentCode=' + r,
    method: 'post'
  })
}

// 修改团队信息
export function editPerson(data) {
  return request({
    url: 'http://10.11.115.18:9999/agent/update',
    method: 'post',
    data
  })
}
