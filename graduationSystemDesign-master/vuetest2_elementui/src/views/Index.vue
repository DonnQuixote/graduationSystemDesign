<!--<template>-->
<!--  <div id="app">-->
<!--        <router-view></router-view>-->
<!--  </div>-->
<!--</template>-->
<template>
    <el-container style="height: 750px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
            <el-menu router :default-openeds="open_list" >
                <el-submenu v-for="(item,index) in $router.options.routes" test = index :index="index+''" v-if="item.show" >
                    <template slot="title"><i class="el-icon-message"></i>{{item.name}}</template>
                    <el-menu-item @click="refresh()" v-for="(items2,index2) in item.children" :index="items2.path" :class="$route.path==items2.path?'is-active':''">{{items2.name}}</el-menu-item>
                </el-submenu>
            </el-menu>
        </el-aside>
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
