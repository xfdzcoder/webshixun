<script setup>
import { ref } from 'vue'
import RoleSelect from './RoleSelect.vue'
import MenuSelect from './MenuSelect.vue'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
import {
  permissionPublishService,
  permissionGetDetailService
} from '@/api/permission.js'
import { ElMessage } from 'element-plus'

// 控制抽屉显示隐藏
const visibleDrawer = ref(false)

// 默认数据
const defaultForm = {
  id: '', //权限id
  menuId: '', // 菜单id
  menuName: '', //菜单名称
  roleId: '', // 角色分类id
  roleName: '', //角色名称
  createTime : '' //创建时间

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
  if (!formModel.value.id) {
    // 添加操作
    await permissionPublishService(formModel.value)
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
const editorRef = ref()
const open = async (row) => {
  visibleDrawer.value = true // 显示抽屉

  if (row.id) {
    // 需要基于 row.id 发送请求，获取编辑对应的详情数据，进行回显
    const res = await permissionGetDetailService(row.id)
    formModel.value = res.data.data
  } else {
    formModel.value = { ...defaultForm } // 基于默认的数据，重置form数据
    editorRef.value.setHTML('')
  }
}


defineExpose({
  open
})
</script>

<template>
  <el-dialog
      v-model="visibleDrawer"
      :title="formModel.id ? '编辑权限' : '添加权限'"
      direction="rtl"
      size="50%"
  >
    <!-- 权限表单 -->
    <el-form :model="formModel" ref="formRef" label-width="100px">
      <el-form-item label="权限" prop="menuId">
        <menu-select
            v-model="formModel.menuId"
            width="100%"
        ></menu-select>
      </el-form-item>
      <el-form-item label="角色" prop="roleId">
        <role-select
            v-model="formModel.roleId"
            width="100%"
        ></role-select>
      </el-form-item>

      <el-form-item>
        <el-button @click="onPublish" type="primary">添加</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
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
