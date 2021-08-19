import request from '@/utils/request'

// 用于获得职级下拉菜单
export function getCurAgentGrade() {
  return request({
    url: '/get/grade',
    method: 'post'
  })
}

// 获取四级下拉列表
export function getTargetManageCom() {
  return request({
    url: '/get/managecom',
    method: 'post'
  })
}

// 获取目标团队架构
export function getTargetBranchCode(data) {
  return request({
    url: '/get/gradeteam',
    method: 'post',
    data
  })
}

// 职级调整接口 王欣艺
export function saveAdjust(data) {
  return request({
    url: 'http://10.11.114.128:9999/rank/alter',
    method: 'post',
    data
  })
}
// 查询按钮  池皓月
export function find(data, page) {
  return request({
    url: 'http://10.11.114.56:9999/test/GradeQuery?limit=' + page.pageSize + '&' + 'page=' + page.currentPage,
    method: 'post',
    data
  })
}
// 管理机构下拉框  王佳智
export function manage() {
  return request({
    url: '/get/comresource',
    method: 'post'
  })
}
// 岗位职级下拉框  王佳智
export function staff() {
  return request({
    url: '/get/grade',
    method: 'post'
  })
}
