
<template>
<div>
<el-container style="height: 750px; border: 1px solid #eee">
        <div>
                    
        <!-- <div style="margin-top:10px;">
        
         <select v-model="select" slot="prepend" placeholder="请选择类型" @change="change1($event)">
         <option v-for="(item,index) in productList" :key="index" :value="item.id" v-text="item.val"></option> 
         </select>
         <el-button slot="append"  @click="findMessage()" icon="el-icon-search"></el-button>
         <el-button slot="append"  @click="resetRow()" icon="">重置</el-button>
         </el-input>
        </div> -->
        <div style="margin-top:30px;" >
        <el-table
                :data="frontEndPageChange"
                key="tableDataInstall"
                border
                style="width: 100%">
            <el-table-column
                    fixed
                    prop="eid"
                    label="进药订单编号"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="ordertime"
                    label="下单时间"
                    align="center"
                    width="200">
                    <template slot-scope="scope">{{scope.row.ordertime | dateFormat}}</template>
            </el-table-column>
            </el-table-column>
            <el-table-column
                    prop="price"
                    label="进药订单总价"
                    width="120">
            </el-table-column>
            <el-table-column
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="detailsOrder(scope.row)" type="text" size="small">订单详情</el-button>
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
  name: "search",
  name: "frontEndPage",
  methods: {
    refresh() {},
    detailsOrder(row) {
      this.$router.push({
        path: "/drugEnteredSingle",
        query: {
          id: row.eid,
        },
      });
    },
    findMessage() {
      //alert(this.input1)
      const _this = this;
      axios
        .get(
          "http://localhost:8181/orderFront/findByOrderState/" +
            _this.productList[_this.currentId - 1].val
        )
        .then(function (resp) {
          _this.tableData = resp.data;
        });
    },
    change1(event) {
      this.currentId = event.target.value;
      alert("选择查询的订单状态是" + this.productList[this.currentId - 1].val);
    },
    // search(event){
    //     alert(event.currentTarget.value)
    //     console.log(event.currentTarget.value)
    // },
    resetRow() {
      const _this = this;
      axios
        .get("http://localhost:8181/orderFront/findAll")
        .then(function (resp) {
          console.log(resp);
          alert("请重新查找");
          _this.input1 = null;
          _this.tableData = resp.data;
        });
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
      let start =
        (this.paginationOptions.currentPage - 1) *
        this.paginationOptions.pageSize;
      if (start >= this.tableData.length) start = 0;
      let end =
        this.paginationOptions.currentPage * this.paginationOptions.pageSize;
      if (end >= this.tableData.length) end = this.tableData.length;
      return this.tableData.slice(start, end);
    },
  },
  created() {
    const _this = this;
    axios.get("http://localhost:8181/drugEntered/findAll").then(function (resp) {
      // axios.get('http://localhost:8181/book/medineFindProvider/'+"测试").then(function (resp) {
      console.log(resp);
      _this.tableData = resp.data;
      if (resp != null) {
        _this.eid = resp.data[0].eid;
      }
      for (var i = 0; i < resp.data.length; i++) {
        _this.totalPrice += resp.data[i].price * resp.data[i].medicinecount;
      }
      // _this.$alert(_this.totalPrice)
      // _this.total = resp.data.totalElements
    });
  },
  data() {
    return {
      totalPrice: 0.0,
      updateOrderData: "",
      eid: 0,
      ordertime: "",
      open_list: ["0", "1", "2"],
      total: null,
      tableData: [
        {
          date: "2016-05-02",
          name: "黄小虎",
          address: "上海市普陀区金沙江路 1513 弄",
          age: 18,
          height: "180cm",
        },
        {
          date: "2016-05-04",
          name: "秦小虎",
          address: "上海市普陀区金沙江路 1514 弄",
          age: 18,
          height: "180cm",
        },
      ],
      list: [
        {
          id: "",
          oid: "",
          medicineid: "",
          price: "",
          medicinecount: "",
          provider: "",
        },
      ],
      reason: null,
      enddate: null,
      input1: "",
      select: "1",
      currentId: "1",
      productList: [
        { id: 1, val: "等待处理" },
        { id: 2, val: "出库成功" },
      ],
      paginationOptions: {
        currentPage: 1, // 当前页
        pageSize: 10, // 展示页数
        pageSizes: [5, 10, 20, 30, 40], // 可选择展示页数 数组
      },
    };
  },
};
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
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>
