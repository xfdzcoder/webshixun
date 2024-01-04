import request from '@/utils/request.js'

export const list = (data) => {
  return request.post('/student/list', data)
}

export const edit = (data) => {
  return request.put('/student', data)
}

export const deleteById = (id) => {
  return request.delete(`/student/${id}`)
}

export const save = (data) => {
  return request.post('/student', data)
}
