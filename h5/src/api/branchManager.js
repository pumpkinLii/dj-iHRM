import request from '@/utils/request'

// 查询团队信息 branchManager:负责人代码
export function getManagerInfoByCode(data) {
  return request({
    url: '/test/getGroupManager', // 待修改
    method: 'post',
    data
  })
}
