<template>

    <!-- 表单部分 -->
    <div >
      <page-container title="基本资料" >
        <el-form ref="formRef" :model="form" :rules="rules" label-width="100px" style='width: 300px;margin: auto'>
          <el-form-item label="用户名">
            <el-input v-model="form.account" disabled></el-input>
          </el-form-item>
          <el-form-item label="邮箱地址" prop="email">
            <el-input v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm">提交修改</el-button>
          </el-form-item>
        </el-form>
      </page-container>
    </div>

</template>
<script setup>
import PageContainer from '@/components/PageContainer.vue'
import { ref } from 'vue'
import { useUserStore } from '@/stores'
import { userUpdateInfoService } from '@/api/user'

const formRef = ref()

//是在使用仓库中数据的初始值 (无需响应式) 解构无问题
const {
  user: { email, id,  account },
  getUser
} = useUserStore()

const form = ref({
  id,
  account,
  email
})

const rules = ref({
  email: [
    { required: true, message: '请输入用户邮箱', trigger: 'blur' },
    {
      type: 'email',
      message: '请输入正确的邮箱格式',
      trigger: ['blur', 'change']
    }
  ]
})

const submitForm = async () => {
  // 等待校验结果
  await formRef.value.validate()
  // 提交修改
  await userUpdateInfoService(form.value)
  // 通知 user 模块，进行数据的更新
  getUser()
  // 提示用户
  ElMessage.success('修改成功')
}

</script>

