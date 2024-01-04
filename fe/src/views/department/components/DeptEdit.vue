<script setup>
import { ref } from 'vue'
import { EditDeptService, addDeptService } from '@/api/dept.js'
import { ElMessage } from 'element-plus'
const dialogVisible = ref(false)
const formRef = ref()
const formModel = ref({
  no: '',
  name: '',
  address: '',
  user: ''

})
const rules = {
  no: [
    { required: true, message: '请输入部门编号', trigger: 'blur' },
    { min: 5, max: 10, message: '部门编号必须是5-10位的字符', trigger: 'blur' }
  ],
  name: [
    { required: true, message: '请输入部门名称', trigger: 'blur' },
    { trigger: 'blur' }
  ],
  address: [
    { required: true, message: '请输入部门位置', trigger: 'blur' },
    { trigger: 'blur' }
  ],
  user: [
    { required: true, message: '请输入部门负责人', trigger: 'blur' },
    { trigger: 'blur' }
  ]
}

const emit = defineEmits(['success'])
const onSubmit = async () => {
  await formRef.value.validate()
  const isEdit = formModel.value.id
  if (isEdit) {
    await EditDeptService(formModel.value)
    ElMessage.success('编辑成功')
  } else {
    await addDeptService(formModel.value)
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
      :title="formModel.id ? '编辑部门' : '添加部门'"
      width="30%"
  >
    <el-form
        ref="formRef"
        :model="formModel"
        :rules="rules"
        label-width="100px"
        style="padding-right: 30px"
    >
      <el-form-item label="部门编号" prop="no">
        <el-input
            v-model="formModel.no"
            placeholder="请输入部门编号"
        ></el-input>
      </el-form-item>
      <el-form-item label="部门名称" prop="name">
        <el-input
            v-model="formModel.name"
            placeholder="请输入部门名称"
        ></el-input>
      </el-form-item>
      <el-form-item label="部门位置" prop="address">
        <el-input
            v-model="formModel.address"
            placeholder="请输入部门位置"
        ></el-input>
      </el-form-item>
      <el-form-item label="部门负责人" prop="user">
        <el-input
            v-model="formModel.user"
            placeholder="请输入部门负责人"
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
