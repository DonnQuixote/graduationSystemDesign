import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import Vuex from 'vuex'


Vue.use(Vuex);

Vue.config.productionTip = false

router.beforeEach((to,from,next)=>{
  let isLogin = sessionStorage.getItem("isLogin");
  let admin = sessionStorage.getItem("isAdmin");
  let clerk = sessionStorage.getItem("isClerk");
  //注销
  if(to.path=="/logout"){
        //清空
        sessionStorage.clear();
        next({path:'/login'});
  }else if(to.path=="/"){
    if(isLogin!=null){
        if(admin!=null&&clerk==null)
          next({path:'/back'});
          else if(admin==null&&clerk!=null){
            next({path:'/frontShow'})
          }
    }
  }else if(isLogin==null){
    next({path:'/'});
  }
  next();
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')


Vue.filter('dateFormat', function(originVal) {
  const dt = new Date(originVal)

  const y = dt.getFullYear()
  const m = (dt.getMonth() + 1 + '').padStart(2, '0')
  const d = (dt.getDate() + '').padStart(2, '0')

  const hh = (dt.getHours() + '').padStart(2, '0')
  const mm = (dt.getMinutes() + '').padStart(2, '0')
  const ss = (dt.getSeconds() + '').padStart(2, '0')

  return `${y}-${m}-${d} ${hh}:${mm}:${ss}`
})

import moment from 'moment/moment'
Vue.filter('moment', function (value, formatString) {
    formatString = formatString || 'YYYY-MM-DD';
return moment(value).format(formatString);

});


import JsonExcel from 'vue-json-excel'
Vue.component('downloadExcel', JsonExcel)

import echarts from "echarts";
Vue.prototype.$echarts = echarts;