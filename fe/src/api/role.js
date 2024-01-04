import request from '@/utils/request'
// 角色：获取角色
export const getRoleService = () => request.get('/role/list')
// 角色：添加角色
export const addRoleService = (data) => request.post('/role/add', data)
// 角色：编辑角色
export const EditRoleService = (data) =>
    request.put('/role/info', data)
// 角色：删除角色
export const delRoleService = (id) =>
    request.delete('/role/del', {
        params: { id }
    })

