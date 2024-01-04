import request from '@/utils/request.js'

export const list = (data) => {
  return request.post('/clz/list', data)
}

export const edit = (data) => {
  return request.put('/clz', data)
}

export const deleteById = (id) => {
  return request.delete(`/clz/${id}`)
}

export const save = (data) => {
  return request.post('/clz', data)
}
