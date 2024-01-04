import { defineStore } from "pinia";
import { ref } from 'vue'
import { userGetMenuService} from "@/api/user.js";


//用户模块 token setToken removeToken
export const useOKStore = defineStore('ok', () => {
    const ok1 = ref('')
    const ok2 = ref('')
    const ok3 = ref('')
    const ok4 = ref('')
    const ok5 = ref('')
    const ok6 = ref('')
    const ok7 = ref('')
    const ok8 = ref('')
    const ok9 = ref('')
    const ok10 = ref('')
    const ok11 = ref('')
    const ok12 = ref('')
    const getok = async () => {
        const res = await userGetMenuService() // 请求获取数据
        if (res.data.data.dept !== '1') {
            ok1.value = false
        }else {
            ok1.value = true
        }
        if (res.data.data.employee !== '1') {
            ok2.value = false
        }else {
            ok2.value = true
        }
        if (res.data.data.userManager !== '1') {
            ok10.value = false
        }else {
            ok10.value = true
        }
        if (res.data.data.holiday !== '1') {
            ok3.value = false
        }else {
            ok3.value = true
        }
        if (res.data.data.reimburse !== '1') {
            ok4.value = false
        }else {
            ok4.value = true
        }
        if (res.data.data.email !== '1') {
            ok5.value = false
        }else {
            ok5.value = true
        }
        if (res.data.data.avatar !== '1') {
            ok6.value = false
        }else {
            ok6.value = true
        }
        if (res.data.data.password !== '1') {
            ok7.value = false
        }else {
            ok7.value = true
        }
        if (res.data.data.role !== '1') {
            ok8.value = false
        }else {
            ok8.value = true
        }
        if (res.data.data.permission !== '1') {
            ok9.value = false
        }else {
            ok9.value = true
        }
        if (res.data.data.clz !== '1') {
            ok11.value = false
        }else {
            ok11.value = true
        }
        if (res.data.data.student !== '1') {
            ok12.value = false
        }else {
            ok12.value = true
        }
    }
    const setok = (v) => {
        ok1.value = v
        ok2.value = v
        ok3.value = v
        ok4.value = v
        ok5.value = v
        ok6.value = v
        ok7.value = v
        ok8.value = v
        ok9.value = v
        ok10.value = v
        ok11.value = v
        ok12.value = v
    }
    return{
        ok1,
        ok2,
        ok3,
        ok4,
        ok5,
        ok6,
        ok7,
        ok8,
        ok9,
        ok10,
        ok11,
        ok12,
        setok,
        getok
    }
},{
    persist: true
})
