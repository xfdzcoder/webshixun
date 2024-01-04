import request from '@/utils/request'

// 配置表：获取所有配置
export const getConfigService = () => request.get('/config/list')
