<script setup>
import { ref } from 'vue'
import { EditUserService, addUserService } from '@/api/user.js'
const dialogVisible = ref(false)
const formRef = ref()
const formModel = ref({
  account: '',
  password: '',
  roleId: ''

})
const rules = {
  account: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 5, max: 10, message: '用户名必须是5-10位的字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    {
      pattern: /^\S{6,15}$/,
      message: '密码必须是6-15位的非空字符',
      trigger: 'blur'
    }
  ],
  roleId: [
    { required: true, message: '请输入角色ID', trigger: 'blur' },
    {
      pattern: /^\S{1,10}$/,
      message: 'id名必须是 1-10 位的非空字符',
      trigger: 'blur'
    }
  ]
}

const emit = defineEmits(['success'])
const onSubmit = async () => {
  await formRef.value.validate()
  const isEdit = formModel.value.id
  if (isEdit) {
    await EditUserService(formModel.value)
    ElMessage.success('编辑成功')
  } else {
    await addUserService(formModel.value)
    ElMessage.success('添加成功')
  }
  dialogVisible.value = false
  emit('success')
}

// 组件对外暴露一个方法 open，基于open传来的参数，区分添加还是编辑
// open({})  => 表单无需渲染，说明是添加
// open({ id, cate_name, ... })  => 表单需要渲染，说明是编辑
// open调用后，可以打开弹窗
const open = (row) => {
  dialogVisible.value = true
  formModel.value = { ...row } // 添加 → 重置了表单内容，编辑 → 存储了需要回显的数据
}

// 向外暴露方法
defineExpose({
  open
})
</script>

<template>
  <el-dialog
      v-model="dialogVisible"
      :title="formModel.id ? '编辑用户' : '添加用户'"
      width="30%"
  >
    <el-form
        ref="formRef"
        :model="formModel"
        :rules="rules"
        label-width="100px"
        style="padding-right: 30px"
    >
      <el-form-item v-if="!formModel.id" label="账户" prop="account">
        <el-input
            v-model="formModel.account"
            placeholder="请输入账户名称"
        ></el-input>
      </el-form-item>
      <el-form-item v-if="!formModel.id" label="密码" prop="password">
        <el-input
            v-model="formModel.password"
            placeholder="请输入账户密码"
        ></el-input>
      </el-form-item>
      <el-form-item label="角色ID" prop="roleId">
        <el-input
            v-model="formModel.roleId"
            placeholder="请输入角色ID"
        ></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="onSubmit"> 确认 </el-button>
      </span>
    </template>
  </el-dialog>
</template>
