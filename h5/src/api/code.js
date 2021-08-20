import request from '@/utils/request'

// 用于获取码表
export function getAllCode() {
  return request({
    url: '/start/get',
    method: 'post'
  })
}

// 用于获取管理机构下拉菜单
export function getManageComCode() {
  return request({
    url: '/get/managecom',
    method: 'post'
  })
}
