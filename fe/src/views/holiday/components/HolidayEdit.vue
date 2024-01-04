<script setup>
import {onMounted,ref } from 'vue'
import ConfigSelect from './ConfigSelect.vue'
// import { Plus } from '@element-plus/icons-vue'
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
import {
  holidayPublishService,
  holidayGetDetailService,
  holidayEditService,
  holidayEditService1
} from '@/api/holiday.js'
import {useOKStore,useUserStore} from "@/stores/index.js";
// 控制抽屉显示隐藏
const visibleDrawer = ref(false)
const useOK = useOKStore()
const userStore = useUserStore()
const flag = ref(false)
onMounted(() => {
if (useOK.ok8 || useOK.ok9) {
  flag.value = true
}else {
  flag.value = false
}
})
// 默认数据
const defaultForm = {
  id: '', //请假id
  no: '', // 请假编号
  userNo: userStore.user.account, // 账户编号
  typeId: '', //请假类型
  bz: '', // 请假事由
  startTime: '', // 开始时间
  endTime: '' ,// 结束时间
  status: '', //申请状态
  noAgree: '' //审批状态
}

// 准备数据
const formModel = ref({ ...defaultForm })

// 提交
const emit = defineEmits(['success'])
const onPublish = async (state) => {
  // 将已发布还是草稿状态，存入 formModel
  formModel.value.status = state

  // 注意：当前接口，需要的是 formData 对象
  // 将普通对象 => 转换成 => formData对象
  const fd = new FormData()
  for (let key in formModel.value) {
    fd.append(key, formModel.value[key])
  }

  // 发请求
  if (formModel.value.id) {
    if (useOK.ok8 || useOK.ok9) {
      // 编辑操作
      await holidayEditService1(fd)
      ElMessage.success('修改成功')
      visibleDrawer.value = false
      emit('success', 'edit')
    }else {
      // 编辑操作
      await holidayEditService(fd)
      ElMessage.success('修改成功')
      visibleDrawer.value = false
      emit('success', 'edit')
    }
  } else {
    // 添加操作
    await holidayPublishService(fd)
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
    const res = await holidayGetDetailService(row.id)
    formModel.value = res.data.data

  } else {
    formModel.value = { ...defaultForm } // 基于默认的数据，重置form数据
    // editorRef.value.setHTML('')
  }
}


defineExpose({
  open
})
</script>

<template>
  <el-drawer
      v-model="visibleDrawer"
      :title="formModel.id ? '编辑请假' : '添加请假'"
      direction="rtl"
      size="50%"
  >
    <!-- 请假表单 -->
    <el-form :model="formModel" ref="formRef" label-width="100px">
      <el-form-item label="请假编号" prop="no" v-if="!flag">
        <el-input v-model="formModel.no" placeholder="请输入请假编号" ></el-input>
      </el-form-item>
      <el-form-item label="申请人" prop="userNo">
        <el-input v-model="formModel.userNo" placeholder="请输入申请人" disabled></el-input>
      </el-form-item>
      <el-form-item label="请假类型" prop="typeId" v-if="!flag">
        <config-select
            v-model="formModel.typeId"
            width="100%"
        ></config-select>
      </el-form-item>
      <el-form-item label="请假事由" prop="bz" v-if="!flag">
        <div class="editor" >
          <quill-editor
              ref="editorRef"
              v-model:content="formModel.bz"
              content-type="html"
              theme="snow"
          ></quill-editor>
        </div>
      </el-form-item>
      <el-form-item label="开始时间" prop="startTime" v-if="!flag">
        <el-date-picker
            v-model="formModel.startTime"
            type="datetime"
            placeholder="请选择开始时间" />
      </el-form-item>
      <el-form-item label="结束时间" prop="endTime" v-if="!flag">
        <el-date-picker
            v-model="formModel.endTime"
            type="datetime"
            placeholder="请选择结束时间" />
      </el-form-item>
      <el-form-item label="审批状态" prop="noAgree" v-if="flag">
        <el-input v-model="formModel.noAgree" placeholder="请输入审批理由，同意或驳回" ></el-input>
      </el-form-item>

      <el-form-item>
        <el-button @click="onPublish('已提交')" type="primary">提交</el-button>
        <el-button @click="onPublish('草稿')" type="info" v-if="!flag">草稿</el-button>
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
