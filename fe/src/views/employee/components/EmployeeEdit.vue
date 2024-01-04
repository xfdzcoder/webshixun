<script setup>
import { ref } from 'vue'
import DeptSelect from './DeptSelect.vue'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
import {
  employeePublishService,
  employeeGetDetailService,
  employeeEditService
} from '@/api/employee.js'

// 控制抽屉显示隐藏
const visibleDrawer = ref(false)

// 默认数据
const defaultForm = {
  id: '', //员工id
  no: '', // 员工编号
  name: '', // 员工姓名
  deptId: '', //部门ID
  sex: '1', // 性别
  entryTime: '' //入职时间

}

// 准备数据
const formModel = ref({ ...defaultForm })



// 提交
const emit = defineEmits(['success'])
const onPublish = async () => {
  // 注意：当前接口，需要的是 formData 对象
  // 将普通对象 => 转换成 => formData对象
  const fd = new FormData()
  for (let key in formModel.value) {
    fd.append(key, formModel.value[key])
  }

  // 发请求
  if (formModel.value.id) {
    // 编辑操作
    await employeeEditService(fd)
    ElMessage.success('修改成功')
    visibleDrawer.value = false
    emit('success', 'edit')
  } else {
    // 添加操作
    await employeePublishService(fd)
    ElMessage.success('添加成功')
    visibleDrawer.value = false
    // 通知到父组件，添加成功了
    emit('success', 'add')
  }
}

// 组件对外暴露一个方法 open，基于open传来的参数，区分添加还是编辑
// open({})  => 表单无需渲染，说明是添加
// open({ id, ..., ... })  => 表单需要渲染，说明是编辑
// open调用后，可以打开抽屉
const open = async (row) => {
  visibleDrawer.value = true // 显示抽屉

  if (row.id) {
    // 需要基于 row.id 发送请求，获取编辑对应的详情数据，进行回显
    const res = await employeeGetDetailService(row.id)
    formModel.value = res.data.data

  } else {
    formModel.value = { ...defaultForm } // 基于默认的数据，重置form数据
  }
}


defineExpose({
  open
})
</script>

<template>
  <el-drawer
      v-model="visibleDrawer"
      :title="formModel.id ? '编辑员工信息' : '添加员工'"
      direction="rtl"
      size="50%"
  >
    <!-- 员工信息表单 -->
    <el-form :model="formModel" ref="formRef" label-width="100px">
      <el-form-item label="员工编号" prop="no">
        <el-input v-model="formModel.no" placeholder="请输入员工编号"></el-input>
      </el-form-item>
      <el-form-item label="员工姓名" prop="name">
        <el-input v-model="formModel.name" placeholder="请输入员工姓名"></el-input>
      </el-form-item>
      <el-form-item  label="员工性别" prop="sex">
        <el-radio-group v-model="formModel.sex"  >
          <el-radio :label="1" size="large">男</el-radio>
          <el-radio :label="0" size="large">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="员工所属部门" prop="deptId">
        <dept-select
            v-model="formModel.deptId"
            width="100%"
        ></dept-select>
      </el-form-item>
      <el-form-item label="员工入职时间" prop="entryTime">
        <el-date-picker
            v-model="formModel.entryTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择入职时间"
        />
      </el-form-item>

      <el-form-item>
        <el-button @click="onPublish" type="primary">完成</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

<style lang="scss" scoped>
.avatar-uploader {
  :deep {
    .avatar {
      width: 178px;
      height: 178px;
      display: block;
    }
    .el-upload {
      border: 1px dashed var(--el-border-color);
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
      transition: var(--el-transition-duration-fast);
    }
    .el-upload:hover {
      border-color: var(--el-color-primary);
    }
    .el-icon.avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 178px;
      height: 178px;
      text-align: center;
    }
  }
}

.editor {
  width: 100%;
  :deep(.ql-editor) {
    min-height: 200px;
  }
}
</style>
