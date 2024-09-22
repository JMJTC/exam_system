import Vue from 'vue'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"

//设置项目样式为mini
Vue.use(Element,{size:"mini"});

// 引入axios
import axios from '@/axios'
// 将axios挂载到Vue实例的原型上，方便在组件中使用
Vue.prototype.$axios = axios
// 将服务器地址挂载到Vue实例的原型上，方便在组件中使用
Vue.prototype.$httpUrl=process.env.VUE_APP_SERVER
// 将文件上传地址挂载到Vue实例的原型上，方便在组件中使用
Vue.prototype.$httpUploadFile=process.env.VUE_APP_SERVER+"/file/upload/exam"
// console.log("环境",process.env.NODE_ENV)
// console.log("服务器",process.env.VUE_APP_SERVER)
// console.log(process.env)

import CustomPages from "@/components/CustomPages";
import CustomDrawer from "@/components/CustomDrawer";
import CustomEditor from "@/components/CustomEditor";
//全局引入
Vue.component("CustomDrawer",CustomDrawer)
Vue.component("CustomPages",CustomPages)
Vue.component("CustomEditor",CustomEditor)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
