
<template>
<div>
<el-container style="height: 750px; border: 1px solid #eee">
  
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
            <el-menu router :default-openeds="open_list" 
            default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        background-color="#DDD"
        active-text-color="#ffd04b">
                <el-submenu v-for="(item,index) in $router.options.routes2" test = index :index="index+''" v-if="item.show" >
                    <template slot="title"><i class="el-icon-document"></i>{{item.name}}</template>
                    <el-menu-item @click="refresh()" v-for="(items2,index2) in item.children" :index="items2.path" :class="$route.path==items2.path?'is-active':''">{{items2.name}}</el-menu-item>
                </el-submenu l-submenu>
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
            :header-cell-style="{background:'#eef1f6',color:'#606266'}"
                :data="frontEndPageChange"
                key="tableDataInstall"
                border
                style="width: 100%">
            <el-table-column
                    fixed
                    prop="medicineid"
                    align="center"
                    label="编号"
                    width="150">
            </el-table-column>
            <el-table-column
                    fixedy
                    prop="oid"
                    label="父编号"
                    v-if="false"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="ordertime"
                    label="订单时间"
                     v-if:"false"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="name"
                    align="center"
                    label="药品名称"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="storagedate"
                    label="出厂日期"
                    align="center"
                    width="180"
                   >
                   <template slot-scope="scope">{{scope.row.storagedate | moment}}</template>
            </el-table-column>
            <el-table-column
                    prop="enddate"
                    label="截止日期"
                    align="center"
                    width="180">
                    <template slot-scope="scope">{{scope.row.enddate | moment}}</template>
            </el-table-column>
            <el-table-column
                    prop="count"
                    label="数量"
                    align="center"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="expirydates"
                    label="有效期限(月)"
                     v-if:"false"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="purchaseprice"
                    label="进价"
                    align="center"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="unitprice"
                    label="单价"
                    align="center"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="provider"
                    label="供应商"
                    align="center"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="type"
                    label="类型"
                    v-if="false"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="reason"
                    label="删除原因"
                    v-if="false"
                    width="120">
            </el-table-column>
            <el-table-column
                    label="操作"
                    align="center"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="shop(scope.row)" type="text" size="small">加入订单</el-button>
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
            shop(row){
              this.ordertime = new Date();
              //console.log(row)
              //alert(this.ordertime)
              //axios.post('http://localhost:8181/orderFront/save/'+this.ordertime)
              const _this = this
              axios.get('http://localhost:8181/orderFront/getLatestOrder').then(function(resp){
                console.log(resp)
                if(resp.data[0].totalprice==null){
                  row.oid = resp.data[0].oid
                  
                  // 错误弥补方案
                  //添加新订单时订单编号会跟不上，猜测是axios的问题，目前处理方案两次添加购物车在后台更改了错误的父订单编号。
                  //猜测数据库数据没有及时更新

                 // _this.$alert("继续购物")
                  axios.get('http://localhost:8181/shopping_cart/shoppingCartTest/'+row.oid+'/'+"1"+'/'+row.medicineid+'/'
                                                                    +row.unitprice+'/'+row.provider+'/'+row.name).then(function(resp){
                    if(resp.data=="success"){
                      _this.$alert("成功加入购物车中")
                    }else{
                      _this.$alert("加入购物车中失败")
                    }
        })
                }else{
                    _this.$alert("创建新订单")
                    axios.post('http://localhost:8181/orderFront/save/'+_this.ordertime);
                    axios.post('http://localhost:8181/orderFront/getLatestOrder2').then(function(tp){
                      row.oid = tp.data[0].oid
                     // _this.$alert(row.oid);
                      console.log(tp);

                      axios.get('http://localhost:8181/shopping_cart/shoppingCartTest/'+row.oid+'/'+"1"+'/'+row.medicineid+'/'
                                                                    +row.unitprice+'/'+row.provider+'/'+row.name).then(function(resp){
                    if(resp.data=="success"){
                      _this.$alert("成功加入购物车中")
                    }else{
                      _this.$alert("加入购物车中失败")
                    }
        })
                    });
                }
               //console.log(resp)
                //_this.$alert(resp.data[0].oid) //获取到最新订单的编号
                //row.oid = resp.data[0].oid
                // _this.list[0].oid = row.oid
                // _this.list[0].medicineid = row.id
                // _this.list[0].price = row.unitprice
                // _this.list[0].medicinecount = 1
                // _this.list[0].provider = row.provider
               // _this.$alert(row.medicineid)
                // _this.$alert(row.provider)
                //_this.$alert(row.oid)             
              // _this.$alert(resp.data)
             // _this.$alert(row.oid)
            })

            },
            deleteBook(row){
                const _this = this
                axios.delete('http://localhost:8181/book/deleteById/'+row.id).then(function (resp) {
                    _this.$alert( '《'+row.name+'》'+'已经出库', '删除成功', {
                        confirmButtonText: '确定',
                        callback: action => {
                             //_this.$router.push('/pageOne')
                            window.location.reload()
                        }
                    });
                })
            },
            refresh(){

            },
            edit(row) {
                this.$router.push({
                    path:'/medicineUpdate',
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
                                 axios.get('http://localhost:8181/book/medineLikeName/'+_this.input1).then(function (resp) {
                                    //console.log(resp);
                                    _this.tableData = resp.data
                        })    
                            }
                            else if(this.currentId==2){
                                //alert('进入循环')
                                const _this = this
                            axios.get('http://localhost:8181/book/medineLikeProvider/'+_this.input1).then(function (resp) {
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
         axios.get('http://localhost:8181/book/findAll').then(function (resp) {
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
             axios.get('http://localhost:8181/book/findAll').then(function (resp) {
            // axios.get('http://localhost:8181/book/medineFindProvider/'+"测试").then(function (resp) {
                console.log(resp)
                 _this.tableData = resp.data
                // _this.total = resp.data.totalElements        
            })
        },
        data() {
            return {
                oid:"",
                ordertime:"",
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
                list:[{
                  id:"",
                  oid:"",
                  medicineid:"",
                  price:"",
                  medicinecount:"",
                  provider:"",
                }],
                reason:null,
                enddate:null,
                input1:'',
                select:'1',
                currentId:'1',
                productList:[
        		{id:1,val:"药品信息"},
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
