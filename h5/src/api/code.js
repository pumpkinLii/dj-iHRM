import request from '@/utils/request'

// 用于获取码表 已改
export function getAllCode() {
  return request({
    url: '/login/get/idcode',
    method: 'post'
  })
}

// 用于获取管理机构下拉菜单  已改
export function getManageComCode() {
  return request({
    url: '/login/get/managecom',
    method: 'post'
  })
}
