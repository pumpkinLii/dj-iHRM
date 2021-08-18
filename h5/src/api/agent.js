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
