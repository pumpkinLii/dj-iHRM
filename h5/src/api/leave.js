import request from '@/utils/request'

// 审核通过 王佳智 已改
export function check(data, par) {
  return request({
    url: '/login/YlAgentDimission/approval?num=' + par,
    method: 'post',
    data
  })
}

