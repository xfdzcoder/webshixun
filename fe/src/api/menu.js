import request from '@/utils/request'
// 菜单：获取菜单
export const getMenuService = () => request.get('/menu/list')