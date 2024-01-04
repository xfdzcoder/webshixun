import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      component: () => import('@/views/login/LoginPage.vue'),
      meta: {
        showInMenu: true
      }
    }, // 登录页

    {
      path: '/',
      component: () => import('@/views/layout/LayoutContainerManage.vue'),
      redirect: '/user/email',

      children: [
        {
          path: '/department',
          component: () => import('@/views/department/Dept.vue')
        },
        {
          path: '/employee',
          component: () => import('@/views/employee/Employee.vue')
        },
        {
          path: '/userManager',
          component: () => import('@/views/user/User.vue')

        },
        {
          path: '/holiday',
          component: () => import('@/views/holiday/Holiday.vue')
        },
        {
          path: '/user/email',
          component: () => import('@/views/user/UserProfile.vue')
        },
        {
          path: '/user/password',
          component: () => import('@/views/user/UserPassword.vue')
        },
        {
          path: '/role',
          component: () => import('@/views/role/Role.vue')
        },
        {
          path: '/permission',
          component: () => import('@/views/permission/Permission.vue')
        },
        {
          path: '/clz',
          component: () => import('@/views/clz/Clz.vue')
        },
        {
          path: '/student',
          component: () => import('@/views/student/Student.vue')
        }
      ]
    }
  ]
})

export default router
