<script setup>
import { ref } from 'vue'
import { EditRoleService, addRoleService } from '@/api/role.js'
const dialogVisible = ref(false)
const formRef = ref()
const formModel = ref({
  id: '',
  name: '',
  createTime: ''
})
const rules = {
  name: [
    { required: true, message: '请输入角色名称', trigger: 'blur' },
    {
      pattern: /^\S{1,10}$/,
      message: '角色名必须是 1-10 位的非空字符',
      trigger: 'blur'
    }
  ]
}

const emit = defineEmits(['success'])
const onSubmit = async () => {
  await formRef.value.validate()
  const isEdit = formModel.value.id
  if (isEdit) {
    await EditRoleService(formModel.value)
    ElMessage.success('编辑成功')
  } else {
    await addRoleService(formModel.value)
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
      :title="formModel.id ? '编辑角色' : '添加角色'"
      width="30%"
  >
    <el-form
        ref="formRef"
        :model="formModel"
        :rules="rules"
        label-width="100px"
        style="padding-right: 30px"
    >
      <el-form-item label="角色名称" prop="cateName">
        <el-input
            v-model="formModel.cateName"
            placeholder="请输入角色名称"
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
