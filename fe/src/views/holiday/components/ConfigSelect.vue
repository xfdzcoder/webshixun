<script setup>
import { getConfigService } from '@/api/config.js'
import { ref } from 'vue'

defineProps({
  modelValue: {
    type: [Number, String]
  },
  width: {
    type: String
  }
})
const emit = defineEmits(['update:modelValue'])
const configList = ref([])
const getConfigList = async () => {
  const res = await getConfigService()
  configList.value = res.data.data
}
//页面加载时就执行这个函数
getConfigList()
</script>

<template>
  <!-- label 展示给用户看的，value 收集起来提交给后台的 -->
  <el-select
      :modelValue="modelValue"
      @update:modelValue="emit('update:modelValue', $event)"
      :style="{ width }"
  >
    <el-option
        v-for="config in configList"
        :key="config.id"
        :label="config.name"
        :value="config.id"
    ></el-option>
  </el-select>
</template>
