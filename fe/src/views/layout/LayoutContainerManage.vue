
<template>
  <el-container style='height: 1000px'>
    <el-aside width='200px'>
      <div class='el-aside__logo'></div>
      <el-menu
        active-text-color='#ffd04b'
        :default-active='$route.path'
        text-color='#1e1f22'
        router
      >

        <el-sub-menu index='/c'>
          <template #title>
            <span>人事管理</span>
          </template>
          <el-menu-item index='/department'>
            <span>部门管理</span>
          </el-menu-item>
          <el-menu-item index='/employee'>
            <span>员工管理</span>
          </el-menu-item>
          <el-menu-item index='/userManager'>
            <span>用户管理</span>
          </el-menu-item>
          <el-menu-item index='/holiday'>
            <span>请假管理</span>
          </el-menu-item>

          <el-menu-item index='/clz'>
            <span>班级管理</span>
          </el-menu-item>
          <el-menu-item index='/student'>
            <span>学生管理</span>
          </el-menu-item>
        </el-sub-menu>
        <el-sub-menu index='/a'>
          <template #title>
            <span>个人中心</span>
          </template>
          <el-menu-item index='/user/email'>
            <span>基本资料</span>
          </el-menu-item>
          <el-menu-item index='/user/password'>
            <span>重置密码</span>
          </el-menu-item>
        </el-sub-menu>
        <el-sub-menu index='/b'>
          <template #title>
            <span>系统管理</span>
          </template>
          <el-menu-item index='/role'>
            <span>角色管理</span>
          </el-menu-item>
          <el-menu-item index='/permission'>
            <span>权限管理</span>
          </el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header>
        <div>欢迎用户</div>
        <el-dropdown placement="bottom-end" @command="handleCommand">
          <!--          展示给用户，默认看到的内容-->
          <span class="el-dropdown__box">
<!--            <el-avatar :src="userStore.user.userPic || avatar" />-->
            <el-icon><CaretBottom /></el-icon>
          </span>
          <!--          折叠的下拉部分-->
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="email" :icon="User"
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
<script setup>
import {
  CaretBottom,
  Crop,
  EditPen,
  Management,
  Promotion,
  SwitchButton,
  User,
  UserFilled
} from '@element-plus/icons-vue'
import avatar from '@/assets/default.png'
import { useOKStore, useUserStore } from '@/stores'
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'

const userStore = useUserStore()
const router = useRouter()

const handleCommand = async (key) => {
  if (key === 'logout') {
    // 退出操作
    await ElMessageBox.confirm('你确认要进行退出么', '温馨提示', {
      type: 'warning',
      confirmButtonText: '确认',
      cancelButtonText: '取消'
    })

    // 清除本地的数据 (token + user信息)
    localStorage.removeItem("userId")
    ok.setok('')
    router.push('/login')
  } else {
    // 跳转操作
    router.push(`/user/${key}`)
  }
}
</script>


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
