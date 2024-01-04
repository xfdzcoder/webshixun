<script setup>
import { getMenuService } from '@/api/menu.js'
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
const menuList = ref([])
const getMenuList = async () => {
  const res = await getMenuService()
  menuList.value = res.data.data
}
//页面加载时就执行这个函数
getMenuList()
</script>

<template>
  <!-- label 展示给用户看的，value 收集起来提交给后台的 -->
  <el-select
      :modelValue="modelValue"
      @update:modelValue="emit('update:modelValue', $event)"
      :style="{ width }"
  >
    <el-option
        v-for="menu in menuList"
        :key="menu.id"
        :label="menu.name"
        :value="menu.id"
    ></el-option>
  </el-select>
</template>
