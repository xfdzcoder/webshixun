import request from '@/utils/request'

// 注册接口
export const userRegisterService = ({ account, password, repassword ,roleId }) =>
    request.post('/common/register', { account, password, repassword ,roleId })

// 登录接口
export const userLoginService = ({ account, password }) =>
    request.post('/common/login', { account, password })

// 获取用户基本信息
export const userGetInfoService = () => request.get('/common/userinfo')

// 获取用户菜单权限信息
export const userGetMenuService = () => request.get('/common/getMenu')

// 更新用户基本信息
export const userUpdateInfoService = ({ id,  email }) =>
    request.put('/user/email', { id, email })

// 更新用户密码
export const userUpdatePasswordService = ({ old_pwd, new_pwd, re_pwd, id }) =>
  request.patch('/user/password', { oldPwd, newPwd, rePwd, id })

//=========================================================================
// 用户：获取所有用户
export const getUserService = () => request.get('/user/list')
// 用户：添加用户
export const addUserService = (data) => request.post('/user', data)
// 用户：编辑用户
export const EditUserService = (data) =>
  request.put(`/user`, data)
// 用户：删除用户
export const delUserService = (id) =>
  request.delete(`/user/${id}`)
