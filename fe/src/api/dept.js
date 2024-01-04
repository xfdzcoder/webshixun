import request from '@/utils/request'

// 部门：获取所有部门
export const getDeptService = () => request.get('/dept/list')
// 部门：添加部门
export const addDeptService = (data) => request.post('/dept/add', data)
// 部门：编辑部门
export const EditDeptService = (data) =>
    request.put('/dept/info', data)
// 部门：删除部门
export const delDeptService = (id) =>
    request.delete('/dept/del', {
        params: { id }
    })