import request from '@/utils/request'
// 员工：获取员工列表
export const employeeGetListService = (params) => {
    return request.get('/employee/list', {
        params
    })
}

// 员工：添加员工
// 注意：data需要是一个formData格式的对象
export const employeePublishService = (data) => request.post('/employee', data)

// 员工：获取员工详情
export const employeeGetDetailService = (id) =>
  request.get(`/employee/${id}`)

//员工：编辑员工接口
export const employeeEditService = (data) => request.put('/employee', data)

// 员工：删除员工接口
export const employeeDelService = (id) =>
  request.delete(`/employee/${id}`)
