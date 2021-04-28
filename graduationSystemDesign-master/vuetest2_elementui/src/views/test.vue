
<template>
  <div >
    {{msg}}
    <div>
    <el-button   @click="findMessage()" >find</el-button>
    </div>
        <div>
    <el-button   @click="resetRow()" >reset</el-button>
    </div>
    <div>
      <el-table
        :data="frontEndPageChange"
        border
        stripe
        style="width: 100%;margin:30px auto;"
        height="550"
      >
        <el-table-column prop="id" label="编号" width="100">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="100">
        </el-table-column>
        <el-table-column prop="storagedate" label="出厂日期" width="150">
        </el-table-column>
        <el-table-column prop="enddate" label="截止日期" width="150">
        </el-table-column>
        <el-table-column prop="count" label="数量" width="100">
        </el-table-column>
        <el-table-column prop="purchaseprice" label="进价" width="100">
        </el-table-column>
        <el-table-column prop="unitprice" label="售价" width="100">
        </el-table-column>
        <el-table-column prop="provider" label="供应商" width="200">
        </el-table-column>


      </el-table>
    </div>
    <div >
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
    name: "frontEndPage",
    data() {
      return {
        msg: "前端分页",
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
        paginationOptions: {
          currentPage: 1, // 当前页
          pageSize: 10, // 展示页数
          pageSizes: [5,10, 20, 30, 40] // 可选择展示页数 数组
        },
      };
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
    methods: {
      // 改变分页数量
      handleSizeChange(val) {
        this.paginationOptions.pageSize = val;
      },
      // 改变当前页
      handlePageChange(val) {
        this.paginationOptions.currentPage = val;
      },
      findMessage(){
        const _this = this
        axios.get('http://localhost:8181/book/medineFindProvider/'+'1').then(function (resp) {
                console.log(resp)
                 _this.tableData = resp.data
                // window.location.reload()
            })
      },
      resetRow(){
        const _this = this
         axios.get('http://localhost:8181/book/findAll').then(function (resp) {
                console.log(resp)
                 _this.tableData = resp.data     
            })
      }
    }
    ,created(){
            const _this = this
             axios.get('http://localhost:8181/book/findAll').then(function (resp) {
            // axios.get('http://localhost:8181/book/medineFindProvider/'+"测试").then(function (resp) {
                console.log(resp)
                 _this.tableData = resp.data
                // _this.total = resp.data.totalElements        
            })
        },
  };
</script>
