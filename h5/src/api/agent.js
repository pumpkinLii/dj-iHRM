import request from '@/utils/request'

// 用于获得职级下拉菜单 已改
export function getCurAgentGrade() {
  return request({
    url: '/login/get/grade',
    method: 'post'
  })
}

// 获取四级下拉列表 已改
export function getTargetManageCom() {
  return request({
    url: '/login/get/managecom',
    method: 'post'
  })
}

// 获取目标团队架构 已改 王佳智
export function getTargetBranchCode(data) {
  return request({
    url: '/login/back/gradeteam',
    method: 'post',
    data
  })
}

// 职级调整接口 王欣艺 已改
export function saveAdjust(data) {
  return request({
    // url: process.env.NODE_ENV === 'development' ? 'http://10.11.114.128:9999/rank/alter' : '/rank/alter',
    url: '/login/rank/alter',
    method: 'post',
    data
  })
}
// 查询按钮  池皓月 已改
export function find(data, page) {
  // const url = process.env.NODE_ENV === 'development' ? 'http://10.11.114.56:9999/test/GradeQuery' : '/test/GradeQuery'
  const url = '/login/rank/gradeQuery'
  const param = '?limit=' + page.pageSize + '&' + 'page=' + page.currentPage
  return request({
    url: url + param,
    method: 'post',
    data
  })
}
// 管理机构下拉框  王佳智 已改
export function manage() {
  return request({
    url: '/login/get/comresource',
    method: 'post'
  })
}
// 岗位职级下拉框  王佳智 已改
export function staff() {
  return request({
    url: '/login/get/grade',
    method: 'post'
  })
}
