<!--<template>-->
<!--  <div id="app">-->
<!--        <router-view></router-view>-->
<!--  </div>-->
<!--</template>-->
<template>
    <el-container style="height: 750px; border: 1px solid #eee">
        
        <el-main>
            <router-view>
               <!-- window.location.reload() -->
            </router-view>
        </el-main>
    </el-container>
</template>
<script>
  export default {
   data () {
     return {
       open_list:["0"],
     }
    },
      methods:{
          refresh(){
            
              //window.location.reload()
          
          }
      }
  };
</script>

<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>
