import request from '@/utils/request'

// 配置表：获取所有配置
export const getConfigService = () => request.get('/config/list')
// 配置表：添加配置
export const addConfigService = (data) => request.post('/config/add', data)
// 配置表：编辑配置
export const EditConfigService = (data) =>
    request.put('/config/info', data)
// 配置表：删除配置
export const delConfigService = (id) =>
    request.delete('/config/del', {
        params: { id }
    })