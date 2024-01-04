import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '@/stores'


//createRouter 创建路由实例
//配置 history 模式
// 1.history模式：createWebHistory     地址栏不带#
// 2.hash模式：   createWebHashHistory 地址栏带#
//vite中的环境变量 import.meta.env.BASE_URL 就是 vite.config.js 中的 base 配置项


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
          path: '/reimburse',
          component: () => import('@/views/reimburse/Reimburse.vue')
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
        },
        {
          path: '/reimburse',
          component: () => import('@/views/reimburse/Reimburse.vue')
        }
      ]
    }
  ]
})

// 登录访问拦截 => 默认是直接放行的
// 根据返回值决定，是放行还是拦截
// 返回值：
// 1. undefined / true  直接放行
// 2. false 拦回from的地址页面
// 3. 具体路径 或 路径对象  拦截到对应的地址
//    '/login'   { name: 'login' }
/*router.beforeEach((to) => {
  // 如果没有token, 且访问的是非登录页，拦截到登录，其他情况正常放行
  const useStore = useUserStore()
  if (!useStore.token && to.path !== '/login') return '/login'

})*/

export default router
