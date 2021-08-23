import request from '@/utils/request'

export function getDepartReasonSelect() {
  return request({
    // url: process.env.NODE_ENV === 'development' ? 'http://11.11.111.1111:9999/returnMes/com4Info' : '/xxx/xxx',
    url: 'http://11.11.111.1111:9999/xxx/xxx',
    method: 'post'
  })
}
