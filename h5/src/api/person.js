import request from '@/utils/request'

// 查询团队信息
export function addPerson(data) {
  return request({
    url: '/query/group',
    method: 'post',
    data
  })
}
