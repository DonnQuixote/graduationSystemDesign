<template>

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

        <div>
        <el-table
                :data="frontEndPageChange"
                 :header-cell-style="{background:'#eef1f6',color:'#606266'}"
                key="tableDataInstall"
                border
                style="width: 100%">
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
                    label="最晚有效日期"
                    align="center"
                    width="180"
                   >
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
                    prop="reason"
                     align="center"
                    label="删除原因"
                    width="120">
            </el-table-column>
            //fixed="right"
            <!-- <el-table-column
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="edit(scope.row)" type="text" size="small">修改</el-button>
                    <el-button @click="deleteBook(scope.row)" type="text" size="small">删除</el-button>
                </template>
            </el-table-column> -->
        </el-table>
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
    </div>
    

</template>

<script>
    export default {
        methods: {
            deleteBook(row){
                const _this = this
                axios.delete('http://localhost:8181/provider/deleteById/'+row.id).then(function (resp) {
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
                    path:'/providerUpdate',
                    query:{
                        id:row.id
                    }
                })
                //row.id
            },
            findMessage(){
            //alert(this.input1)
            if(this.input1){
                            if(this.currentId==1){
                                const _this = this
                                 axios.get('http://localhost:8181/drugLoss/medineFindName/'+_this.input1).then(function (resp) {
                                    //console.log(resp);
                                    _this.tableData = resp.data
                        })    
                            }
                            else if(this.currentId==2){
                                //alert('进入供应商循环')
                                const _this = this
                            axios.get('http://localhost:8181/drugLoss/medineFindProvider/'+_this.input1).then(function (resp) {
                                    //console.log(resp);
                                    _this.tableData = resp.data
                        })
                        }else if(this.currentId==3){
                            const _this = this
                            axios.get('http://localhost:8181/drugLoss/medineFindReason/'+_this.input1).then(function (resp) {
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
         axios.get('http://localhost:8181/drugLoss/findAll').then(function (resp) {
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
                
        created(){
            const _this = this
             axios.get('http://localhost:8181/drugLoss/findAll').then(function (resp) {
            // axios.get('http://localhost:8181/book/medineFindProvider/'+"测试").then(function (resp) {
                console.log(resp)
                 _this.tableData = resp.data
                // _this.total = resp.data.totalElements        
            })
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
        data() {
            return {
                                                                                                                                                                                                                                                                                
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
        		{id:1,val:"药品名称"},
                {id:2,val:"供应商名称"},
                {id:3,val:"删除原因"}
             ],
                paginationOptions: {
                     currentPage: 1, // 当前页
                     pageSize: 5, // 展示页数
                     pageSizes: [5,10, 20, 30, 40] // 可选择展示页数 数组
             },
            }
        }
    }
</script>
