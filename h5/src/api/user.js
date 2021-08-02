import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/test/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/vue-admin-template/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}

export function text(data, parme) {
  const url = parme ? '/test/info' + '?' + parme : '/test/info'
  return request({
    url: url,
    method: 'post',
    data
  })
}
