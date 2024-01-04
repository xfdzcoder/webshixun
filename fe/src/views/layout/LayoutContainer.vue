<script setup>
import {
  Management,
  Promotion,
  UserFilled,
  User,
  Crop,
  EditPen,
  SwitchButton,
  CaretBottom
} from '@element-plus/icons-vue'
import avatar from '@/assets/default.png'
import { useUserStore } from '@/stores'
import {onMounted} from 'vue'
import { useRouter } from "vue-router";

const userStore = useUserStore()
const router = useRouter()


onMounted(() => {
  userStore.getUser()
})

const handleCommand = async (key) => {
  if (key === 'logout') {
    // 退出操作
    await ElMessageBox.confirm('你确认要进行退出么', '温馨提示', {
      type: 'warning',
      confirmButtonText: '确认',
      cancelButtonText: '取消'
    })

    // 清除本地的数据 (token + user信息)
    userStore.removeToken()
    userStore.setUser({})
    router.push('/login')
  } else {
    // 跳转操作
    router.push(`/user/${key}`)
  }
}

</script>

<template>
  <!--
  el-menu 整个菜单组件
    :default-active="$route.path"  配置默认高亮的菜单项
    router  router选项开启，el-menu-item 的 index 就是点击跳转的路径

  el-menu-item 菜单项
    index="/article/channel" 配置的是访问的跳转路径，配合default-active的值，实现高亮
-->
  <el-container class="layout-container">
    <el-aside width="200px">
      <div class="el-aside__logo"></div>
      <el-menu
          active-text-color="#ffd04b"
          background-color="#232323"
          :default-active="$route.path"
          text-color="#fff"
          router
      >

        <el-sub-menu index="/personnel">
          <template #title>
            <el-icon><Management /></el-icon>
            <span>人事管理</span>
          </template>
          <el-menu-item index="/personnel/dept">
            <el-icon><Management /></el-icon>
            <span>部门管理</span>
          </el-menu-item>
          <el-menu-item index="/personnel/employee">
            <el-icon><Management /></el-icon>
            <span>员工管理</span>
          </el-menu-item>
          <el-menu-item index="/personnel/holiday">
            <el-icon><EditPen /></el-icon>
            <span>请假管理</span>
          </el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="/finance">
          <template #title>
            <el-icon><Promotion /></el-icon>
            <span>财务管理</span>
          </template>
          <el-menu-item index="/finance/reimburse">
            <el-icon><Management /></el-icon>
            <span>报销管理</span>
          </el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="/user">
          <template #title>
            <el-icon><UserFilled /></el-icon>
            <span>系统管理</span>
          </template>
          <el-menu-item index="/user/profile">
            <el-icon><User /></el-icon>
            <span>基本资料</span>
          </el-menu-item>
          <el-menu-item index="/user/avatar">
            <el-icon><Crop /></el-icon>
            <span>更换头像</span>
          </el-menu-item>
          <el-menu-item index="/user/password">
            <el-icon><EditPen /></el-icon>
            <span>重置密码</span>
          </el-menu-item>
          <el-menu-item index="/user/role">
            <el-icon><EditPen /></el-icon>
            <span>角色管理</span>
          </el-menu-item>
          <el-menu-item index="/user/permission">
            <el-icon><EditPen /></el-icon>
            <span>权限管理</span>
          </el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header>
        <div>欢迎用户：<strong>{{
          userStore.user.account
          }}</strong></div>
        <el-dropdown placement="bottom-end" @command="handleCommand">
<!--          展示给用户，默认看到的内容-->
          <span class="el-dropdown__box">
            <el-avatar :src="avatar" />
            <el-icon><CaretBottom /></el-icon>
          </span>
<!--          折叠的下拉部分-->
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="profile" :icon="User"
              >基本资料</el-dropdown-item
              >
              <el-dropdown-item command="avatar" :icon="Crop"
              >更换头像</el-dropdown-item
              >
              <el-dropdown-item command="password" :icon="EditPen"
              >重置密码</el-dropdown-item
              >
              <el-dropdown-item command="logout" :icon="SwitchButton"
              >退出登录</el-dropdown-item
              >
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
      <el-footer>OA人事管理系统</el-footer>
    </el-container>
  </el-container>
</template>

<style lang="scss" scoped>
.layout-container {
  height: 100vh;
  .el-aside {
    background-color: #232323;
    &__logo {
      height: 120px;
      background: url('@/assets/logo3.png') no-repeat center / 120px auto;
    }
    .el-menu {
      border-right: none;
    }
  }
  .el-header {
    background-color: #fff;
    display: flex;
    align-items: center;
    justify-content: space-between;
    .el-dropdown__box {
      display: flex;
      align-items: center;
      .el-icon {
        color: #999;
        margin-left: 10px;
      }

      &:active,
      &:focus {
        outline: none;
      }
    }
  }
  .el-footer {
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 14px;
    color: #666;
  }
}
</style>