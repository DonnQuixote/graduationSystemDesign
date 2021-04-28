

<template>
<div>
      <h1>demand</h1>
      <el-container style="height: 750px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
            <el-menu router :default-openeds="open_list" >
                <el-submenu v-for="(item,index) in $router.options.routes2" test = index :index="index+''" v-if="item.show" >
                    <template slot="title"><i class="el-icon-message"></i>{{item.name}}</template>
                    <el-menu-item @click="refresh()" v-for="(items2,index2) in item.children" :index="items2.path" :class="$route.path==items2.path?'is-active':''">{{items2.name}}</el-menu-item>
                </el-submenu>
            </el-menu>
        </el-aside>

        <div>

        <div style="margin-top:10px;">
        <el-input placeholder="请输入想要查询的关键字" v-model="input1" class="input-with-select">
         <select v-model="select" slot="prepend" placeholder="请选择类型" @change="change1($event)">
         <option v-for="(item,index) in productList" :key="index" :value="item.id" v-text="item.val"></option> 
         </select>
         <el-button slot="append"  @click="findMessage()" icon="el-icon-search"></el-button>
         <el-button slot="append"  @click="resetRow()" icon="">重置</el-button>
         </el-input>
        </div>
        <div style="margin-top:30px;" >
        <el-table
                :data="frontEndPageChange"
                key="tableDataInstall"
                border
                style="width: 100%">
            <el-table-column
                    fixed
                    prop="id"
                    label="编号"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="需求药品名称"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="count"
                    label="数量"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="price"
                    label="大致价钱"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="provider"
                    label="供应商"
                    width="120">
            </el-table-column>
            <el-table-column
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="edit(scope.row)" type="text" size="small">修改</el-button>
                    <!-- <el-button @click="deleteBook(scope.row)" type="text" size="small">删除</el-button> -->
                     <!-- <el-button @click="open(scope.row)" type="text" size="small">删除</el-button> -->
                </template>
            </el-table-column>
        </el-table>
        </div>

      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handlePageChange"
        class="z-pagination"
        :current-page="paginationOptions.currentPage"
        :page-size="paginationOptions.pageSize"
        :page-sizes="paginationOptions.pageSizes"
        layout="sizes, prev, pager, next, jumper,total"
        :total="tableData.length"
      >
      </el-pagination>

    </div>

        <el-main>
            <router-view>
               <!-- window.location.reload() -->
            </router-view>
        </el-main>
    </el-container>



</div>
    
</template>
<script>
    export default {
        name:"search",
        name: "frontEndPage",
        methods: {
          refresh(){

          },
            deleteBook(row){
                const _this = this
                axios.delete('http://localhost:8181/demand/deleteById/'+row.id).then(function (resp) {
                    _this.$alert( '《'+row.name+'》'+'已经出库', '删除成功', {
                        confirmButtonText: '确定',
                        callback: action => {
                             //_this.$router.push('/pageOne')
                            window.location.reload()
                        }
                    });
                })
            },
            edit(row) {
                this.$router.push({
                    path:'/updateDemand',
                    query:{
                        id:row.id
                    }
                })
            },
             open2(r) {
                  const _this = this
                  this.$prompt('请输入删除原因', '提示', {
                  confirmButtonText: '确定',
                  cancelButtonText: '取消',
                    }).then(({ value }) => {
                  this.$message({
                  type: 'success',
                  message: '你的删除理由是: ' + value,
          });
                    _this.reason = value          
                  //  _this.$alert(_this.reason)
                    r.reason = _this.reason
                  //  r.enddate = 
                        r.type = 1
                        axios.post('http://localhost:8181/drugLoss/save',r).then(function (resp) {
                            if(resp.data=='success'){
                            axios.delete('http://localhost:8181/book/deleteById/'+r.id).then(function(resp){
                                _this.$alert( r.name+'药品信息'+'已经删除', '删除成功', {
                                confirmButtonText: '确定',
                                 callback: action => {
                                  //_this.$router.push('/pageOne')
                                 window.location.reload()
                                 }                        
                        })
                            })
                            }
                        })
        }).catch(() => {
          _this.$alert(r)
          this.$message({
            type: 'info',
            message: '取消删除'
          });       
        });
      },
            open(row) {
                 const _this = this;
                 this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                 confirmButtonText: '确定',
                 cancelButtonText: '取消',
                 type: 'warning'
                     }).then(() => {
                         this.open2(row);
                   }).catch(() => {
                 this.$message({
                 type: 'info',
                 message: '已取消删除'
          });      
        });
                 
      },
        findMessage(){
            //alert(this.input1)
            if(this.input1){
                            if(this.currentId==1){
                                const _this = this
                                 axios.get('http://localhost:8181/demand/demandFindName/'+_this.input1).then(function (resp) {
                                    //console.log(resp);
                                    _this.tableData = resp.data
                        })    
                            }
                            else if(this.currentId==2){
                                //alert('进入循环')
                                const _this = this
                            axios.get('http://localhost:8181/demand/demandFindProvider/'+_this.input1).then(function (resp) {
                                    //console.log(resp);
                                    _this.tableData = resp.data
                        })
                        }
            }else{
                created();
            }          
        },
        change1(event){
            this.currentId = event.target.value
            alert('选择查询的种类是'+this.productList[this.currentId-1].val)
        },
        // search(event){
        //     alert(event.currentTarget.value)
        //     console.log(event.currentTarget.value)
        // },
        resetRow(){
        const _this = this
         axios.get('http://localhost:8181/demand/findAll').then(function (resp) {
                console.log(resp)
                alert("请重新查找")
                _this.input1 = null
                 _this.tableData = resp.data     
            })
      },
        // 改变分页数量
      handleSizeChange(val) {
        this.paginationOptions.pageSize = val;
      },
      // 改变当前页
      handlePageChange(val) {
        this.paginationOptions.currentPage = val;
      },
        },

        computed: {
            // 计算属性对数据进行处理
            frontEndPageChange() {
                let start = (this.paginationOptions.currentPage - 1) * this.paginationOptions.pageSize;
                if (start >= this.tableData.length) start = 0;
                let end = this.paginationOptions.currentPage * this.paginationOptions.pageSize;
                if (end >= this.tableData.length) end = this.tableData.length;
                return this.tableData.slice(start, end);
     }
    },
      created(){
            const _this = this
             axios.get('http://localhost:8181/demand/findAll').then(function (resp) {
            // axios.get('http://localhost:8181/book/medineFindProvider/'+"测试").then(function (resp) {
                console.log(resp)
                 _this.tableData = resp.data
                // _this.total = resp.data.totalElements        
            })
        },
        data() {
            return {
                open_list:["0","1","2"],                                                                                                                                                                                                                                                          
                total:null,
                tableData : [
  {
    date: "2016-05-02",
    name: "黄小虎",
    address: "上海市普陀区金沙江路 1513 弄",
    age: 18,
    height: "180cm"
  },
  {
    date: "2016-05-04",
    name: "秦小虎",
    address: "上海市普陀区金沙江路 1514 弄",
    age: 18,
    height: "180cm"
  }
                ],
                reason:null,
                enddate:null,
                input1:'',
                select:'1',
                currentId:'1',
                productList:[
        		{id:1,val:"需求药品信息"},
                {id:2,val:"供应商信息"},
                
             ],
                paginationOptions: {
                     currentPage: 1, // 当前页
                     pageSize: 10, // 展示页数
                     pageSizes: [5,10, 20, 30, 40] // 可选择展示页数 数组
             },
            }
        }
    }
</script>
<style >
  .el-select .el-input {
    width: 130px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: rgb(255, 255, 255);
  }
</style>


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
