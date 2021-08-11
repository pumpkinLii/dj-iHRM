import request from '@/utils/request'

// 添加一个新的资格证信息
export function insert(data) {
  return request({
    url: '/...', // 待修改
    method: 'post',
    data
  })
}

// 修改一个新的资格证信息
export function update(data) {
  return request({
    url: '/...', // 待修改
    method: 'post',
    data
  })
}
