import request from '@/utils/request'

// mock 测试案例 URL在mock下可找到对应
export function getMockSample() {
  return request({
    url: '/vue-admin-template/table/list',
    method: 'post'
  })
}
