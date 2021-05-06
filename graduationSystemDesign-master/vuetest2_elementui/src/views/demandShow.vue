

<template>
  <div>
    <el-container style="height: 750px; border: 1px solid #eee">
      <div>
        <div style="margin-top: 10px">
          <el-input
            placeholder="请输入想要查询的关键字"
            v-model="input1"
            class="input-with-select"
          >
            <select
              v-model="select"
              slot="prepend"
              placeholder="请选择类型"
              @change="change1($event)"
            >
              <option
                v-for="(item, index) in productList"
                :key="index"
                :value="item.id"
                v-text="item.val"
              ></option>
            </select>
            <el-button
              slot="append"
              @click="findMessage()"
              icon="el-icon-search"
            ></el-button>
            <el-button slot="append" @click="resetRow()" icon=""
              >重置</el-button
            >
          </el-input>
        </div>
        <div style="margin-top: 30px">
          <el-table
            :header-cell-style="{background:'#eef1f6',color:'#606266'}"
            :data="frontEndPageChange"
            key="tableDataInstall"
            border
            style="width: 100%"
          >
            <el-table-column fixed align="center" prop="id" label="编号" width="150">
            </el-table-column>
            <el-table-column prop="name" align="center" label="需求药品名称" width="120">
            </el-table-column>
            <el-table-column prop="count" align="center" label="数量" width="120">
            </el-table-column>
            <el-table-column prop="price" align="center" label="大致价钱" width="120">
            </el-table-column>
            <el-table-column prop="provider" align="center" label="供应商" width="120">
            </el-table-column>
            <el-table-column prop="eid" align="center" label="父进药订单编号" width="120" v-if="false">
            </el-table-column>
            <el-table-column label="操作" align="center" width="100">
              <template slot-scope="scope">
                <el-button
                  @click="addOrderFront(scope.row)"
                  type="text"
                  size="small"
                  >添加订单</el-button
                >
                <el-button @click="deleteDemand(scope.row)" type="text" size="small">删除需求</el-button>
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
  name: "search",
  name: "frontEndPage",
  methods: {
    refresh() {},
    deleteDemand(row){
      axios.delete('http://localhost:8181/demand/deleteById/'+row.id);
      window.location.reload();
      this.$alert(row.name+"已经删除");
    },
    addOrderFront(row) {
              this.ordertime = new Date();
              const _this = this
              axios.get('http://localhost:8181/drugEntered/getLatestOrder').then(function(resp){
                console.log(resp)
                if(resp.data[0].price==null){
                  row.eid = resp.data[0].eid
                  
                  // 错误弥补方案
                  //添加新订单时订单编号会跟不上，猜测是axios的问题，目前处理方案两次添加购物车在后台更改了错误的父订单编号。
                  //猜测数据库数据没有及时更新

                 // _this.$alert("继续购物")
                  axios.get('http://localhost:8181/drugShoppingCart/save/'+row.eid+'/'+row.name+'/'+"1"+'/'
                                                                    +row.price+'/'+row.provider).then(function(resp){
                    if(resp.data=="success"){
                      _this.$alert("成功加入购物车中")
                      axios.delete('http://localhost:8181/demand/deleteById/'+row.id);
                      window.location.reload();
                    }else{
                      _this.$alert("加入购物车中失败")
                    }
        })
                }else{
                    _this.$alert("创建新订单")
                    axios.post('http://localhost:8181/drugEntered/save/'+_this.ordertime);
                    axios.get('http://localhost:8181/drugEntered/getLatestOrder').then(function(tp){
                      row.eid = tp.data[0].eid
                     // _this.$alert(row.oid);
                      console.log(tp);

                      axios.get('http://localhost:8181/drugShoppingCart/save/'+row.eid+'/'+row.name+'/'+"1"+'/'
                                                                    +row.price+'/'+row.provider).then(function(resp){
                    if(resp.data=="success"){
                      _this.$alert("成功加入购物车中")
                      axios.delete('http://localhost:8181/demand/deleteById/'+row.id);
                      window.location.reload();
                    }else{
                      _this.$alert("加入购物车中失败")
                    }
        })
                    });
                }
            })
    },
    open2(r) {
      const _this = this;
      this.$prompt("请输入删除原因", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
      })
        .then(({ value }) => {
          this.$message({
            type: "success",
            message: "你的删除理由是: " + value,
          });
          _this.reason = value;
          //  _this.$alert(_this.reason)
          r.reason = _this.reason;
          //  r.enddate =
          r.type = 1;
          axios
            .post("http://localhost:8181/drugLoss/save", r)
            .then(function (resp) {
              if (resp.data == "success") {
                axios
                  .delete("http://localhost:8181/book/deleteById/" + r.id)
                  .then(function (resp) {
                    _this.$alert(r.name + "药品信息" + "已经删除", "删除成功", {
                      confirmButtonText: "确定",
                      callback: (action) => {
                        //_this.$router.push('/pageOne')
                        window.location.reload();
                      },
                    });
                  });
              }
            });
        })
        .catch(() => {
          _this.$alert(r);
          this.$message({
            type: "info",
            message: "取消删除",
          });
        });
    },
    open(row) {
      const _this = this;
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.open2(row);
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    findMessage() {
      //alert(this.input1)
      if (this.input1) {
        if (this.currentId == 1) {
          const _this = this;
          axios
            .get("http://localhost:8181/demand/demandFindName/" + _this.input1)
            .then(function (resp) {
              //console.log(resp);
              _this.tableData = resp.data;
            });
        } else if (this.currentId == 2) {
          //alert('进入循环')
          const _this = this;
          axios
            .get(
              "http://localhost:8181/demand/demandFindProvider/" + _this.input1
            )
            .then(function (resp) {
              //console.log(resp);
              _this.tableData = resp.data;
            });
        }
      } else {
        created();
      }
    },
    change1(event) {
      this.currentId = event.target.value;
      alert("选择查询的种类是" + this.productList[this.currentId - 1].val);
    },
    // search(event){
    //     alert(event.currentTarget.value)
    //     console.log(event.currentTarget.value)
    // },
    resetRow() {
      const _this = this;
      axios.get("http://localhost:8181/demand/findAll").then(function (resp) {
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
    axios.get("http://localhost:8181/demand/findAll").then(function (resp) {
      // axios.get('http://localhost:8181/book/medineFindProvider/'+"测试").then(function (resp) {
      console.log(resp);
      _this.tableData = resp.data;
      // _this.total = resp.data.totalElements
    });
  },
  data() {
    return {
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
      reason: null,
      enddate: null,
      input1: "",
      select: "1",
      currentId: "1",
      productList: [
        { id: 1, val: "需求药品信息" },
        { id: 2, val: "供应商信息" },
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
