import request from '@/utils/request'

export function ttt() {
  return request({
    url: '/text/zc',
    method: 'post'
  })
}

export function abc() {
  return request({
    url: '/get/comresource',
    method: 'post'
  })
}

export function staff() {
  return request({
    url: '/get/grade',
    method: 'post'
  })
}
