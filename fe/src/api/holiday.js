import request from '@/utils/request'
// 假期：获取假期列表
export const holidayGetListService = (params) => {
    return request.get('/holiday/list', {
        params
    })
}
// 假期：获取所有假期列表
export const holidayGetListService1 = (params) => {
    return request.get('/holiday/list1', {
        params
    })
}
// 假期：添加假期
// 注意：data需要是一个formData格式的对象
export const holidayPublishService = (data) => request.post('/holiday', data)

// 假期：获取假期详情
export const holidayGetDetailService = (id) =>
    request.get('/holiday', {
        params: { id }
    })

// 假期：编辑假期接口
export const holidayEditService = (data) => request.put('/holiday', data)
// 假期：编辑假期接口

// 假期：删除假期接口
export const holidayDelService = (id) =>
    request.delete('/holiday/delete', { params: { id } })
