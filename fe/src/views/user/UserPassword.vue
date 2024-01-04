<template>
  <div>
    <page-container title="修改密码">
      <el-row>
        <el-col :span="12">
          <el-form
            ref="formRef"
            :model="pwdForm"
            :rules="rules"
            label-width="100px" style='width: 300px;margin: auto'
          >
            <el-form-item label="原密码" prop="old_pwd">
              <el-input v-model="pwdForm.oldPwd" show-password></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="new_pwd">
              <el-input v-model="pwdForm.newPwd" show-password></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="re_pwd">
              <el-input v-model="pwdForm.rePwd" show-password></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm">修改密码</el-button>
              <el-button @click="resetForm">重置</el-button>
            </el-form-item>
          </el-form></el-col
        >
      </el-row>
    </page-container>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { userUpdatePasswordService } from '@/api/user'
import { useUserStore } from '@/stores'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'

const formRef = ref()
const pwdForm = ref({
  oldPwd: '',
  newPwd: '',
  rePwd: '',
  id: localStorage.getItem("userId")
})

const checkDifferent = (rule, value, callback) => {
  // 校验新密码和原密码不能一样
  if (value === pwdForm.value.oldPwd) {
    callback(new Error('新密码不能与原密码一样'))
  } else {
    callback()
  }
}
const checkSameAsNewPwd = (rule, value, callback) => {
  // 校验确认密码必须和新密码一样
  if (value !== pwdForm.value.newPwd) {
    callback(new Error('确认密码必须和新密码一样'))
  } else {
    callback()
  }
}
const rules = ref({
  oldPwd: [
    { required: true, message: '请输入原密码', trigger: 'blur' },
    { min: 6, max: 15, message: '原密码长度在6-15位之间', trigger: 'blur' }
  ],
  newPwd: [
    { required: true, message: '请输入新密码', trigger: 'blur' },
    { min: 6, max: 15, message: '新密码长度在6-15位之间', trigger: 'blur' },
    { validator: checkDifferent, trigger: 'blur' }
  ],
  rePwd: [
    { required: true, message: '请再次输入新密码', trigger: 'blur' },
    { min: 6, max: 15, message: '确认密码长度在6-15位之间', trigger: 'blur' },
    { validator: checkSameAsNewPwd, trigger: 'blur' }
  ]
})

const userStore = useUserStore()
const router = useRouter()

const submitForm = async () => {
  await formRef.value.validate()
  await userUpdatePasswordService(pwdForm.value)
  ElMessage.success('密码修改成功')

  // 拦截登录
  router.push('/login')
}

const resetForm = () => {
  formRef.value.resetFields()
}
</script>


