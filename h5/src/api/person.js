import request from '@/utils/request'

// 查询团队信息
export function addPerson(data) {
  return request({
    url: '/test/doSave',
    method: 'post',
    data
  })
}

// 管理机构下拉列表
export function xiala() {
  return request({
    url: '/get/managecom',
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
    url: '/test/EchoGroup',
    method: 'post',
    data
  })
}
// 回显
export function huixian(data) {
  return request({
    url: '/test/EchoManager',
    method: 'post',
    data
  })
}
// // 提交
// export  function tijiao(){
//   return request({
//
//   })
// }
