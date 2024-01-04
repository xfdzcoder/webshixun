import request from '@/utils/request.js'

export const list = (data) => {
  return request.post('/reimburse/list', data)
}

export const edit = (data) => {
  return request.put('/reimburse', data)
}

export const deleteById = (id) => {
  return request.delete(`/reimburse/${id}`)
}

export const save = (data) => {
  return request.post('/reimburse', data)
}
