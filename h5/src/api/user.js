import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/test/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/test/logout',
    method: 'post'
  })
}
