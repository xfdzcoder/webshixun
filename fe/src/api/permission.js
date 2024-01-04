import request from '@/utils/request'
// 权限：获取权限列表
export const permissionGetListService = (params) => {
    return request.get('/permissions/list', {
        params
    })
}

// 权限：添加权限
// 注意：data需要是一个formData格式的对象
export const permissionPublishService = (data) => request.post('/permissions/add', data)

// 权限：获取权限详情
export const permissionGetDetailService = (id) =>
    request.get('/permissions/info', {
        params: { id }
    })

// 权限：编辑权限接口
// export const permissionEditService = (data) => request.put('/permissions/update', data)

// 权限：删除权限接口
export const permissionDelService = (id) =>
    request.delete('/permissions/delete', { params: { id } })