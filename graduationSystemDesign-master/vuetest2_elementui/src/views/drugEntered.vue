
<template>
  <div>
    <!-- <h1>medicineShow</h1> -->
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
        <div style="margin-top: 10px" >
          <div>
            {{ "订单总价: " }} {{ this.totalPrice |numFilter}}
            <!-- <el-button slot="append"  @click="submitOrder()" icon="">提交订单</el-button> -->
            <download-excel
              class="export-excel-wrapper"
              :data="json_data"
              :fields="json_fields"
              name="进药订单.xls"
            >
              <!-- 上面可以自定义自己的样式，还可以引用其他组件button -->
              <el-button type="primary" @click="submitOrder2()"
                >生成excel文件</el-button
              >
            </download-excel>

            <el-button slot="append" @click="submitOrder()" icon=""
              >提交订单</el-button
            >
            <el-button slot="append" @click="destructionOrder()" icon=""
              >销毁订单</el-button
            >
          </div>
        </div>
        <div style="margin-top: 30px">
          <el-table
          :header-cell-style="{background:'#eef1f6',color:'#606266'}"
            :data="frontEndPageChange"
            key="tableDataInstall"
            border
            style="width: 100%"
          >
            <el-table-column
              fixed
              prop="medicineid"
              v-if="false"
              label="药品编号"
              width="150"
            >
            </el-table-column>
            <el-table-column
              fixedy
              prop="eid"
              label="父编号"
              v-if="false"
              width="150"
            >
            </el-table-column>
            <el-table-column prop="name" align="center" label="药品名称" width="120">
            </el-table-column>
            <el-table-column prop="count" align="center" label="数量" width="120">
            </el-table-column>
            <el-table-column
              prop="add"
              label="增加数量"
              v-if="false"
              width="120"
            >
            </el-table-column>
            <el-table-column prop="price" align="center" label="单价" width="120">
            </el-table-column>
            <el-table-column prop="provider" align="center" label="供应商" width="120">
            </el-table-column>
            <el-table-column label="操作" align="center" width="100">
              <template slot-scope="scope">
                <el-button
                  @click="deleteOrder(scope.row)"
                  type="text"
                  size="small"
                  >删除</el-button
                >
                <el-button @click="addOrder(scope.row)" type="text" size="small"
                  >增加数量</el-button
                >
                <el-button
                  @click="decreaseOrder(scope.row)"
                  type="text"
                  size="small"
                  >减少数量</el-button
                >
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
      filters: {
numFilter(value) {
// 截取当前数据到小数点后两位

let realVal = Number(value).toFixed(2)

// num.toFixed(2)获取的是字符串

return Number(realVal)
}
},
  name: "search",
  name: "frontEndPage",
  methods: {
    submitOrder() {
       //this.json_data = this.tableData;
      const _this = this;
      axios
        .get("http://localhost:8181/drugShoppingCart/findAll/")
        .then(function (resp) {
          console.log(resp);
          _this.eid = resp.data[0].eid;
         // _this.$alert(_this.eid);
          axios
            .post(
              "http://localhost:8181/drugEnteredDetails/saveAll/",
              resp.data
            )
            .then(function (resp) {
              if (resp.data == "success") {
                _this.$alert("订单提交成功");
              } else {
                _this.$alert("订单提交失败");
              }
            });
          axios.post(
            "http://localhost:8181/drugEntered/orderFrontPrice/" +
              _this.totalPrice +
              "/" +
              _this.eid
          );
        });
      axios.get("http://localhost:8181/drugShoppingCart/deleteAll/");
      window.location.reload
        // _this.$router.push({
        //   path: "/drugEnteredHistory"
        // });
  
    },
    submitOrder2() {
      this.json_data = this.tableData;
     //this.submitOrder();
    },
    destructionOrder() {
      axios.get("http://localhost:8181/drugShoppingCart/deleteAll/");
      //this.$alert("购物车已经销毁清空");
      this.$alert(this.eid);
      axios.delete("http://localhost:8181/drugEntered/deleteByEid/" + this.eid);
      //window.location.reload();
    },
    addOrder(row) {
      const _this = this;
      this.$prompt("请输入要增加的数量", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputPattern: /^[1-9]\d*$/,
        inputErrorMessage: "只能输入正整数",
      })
        .then(({ value }) => {
          this.temp = parseInt(row.count) + parseInt(value);
          this.totalPrice = this.temp;
          this.$message({
            type: "success",
            message: "现在的数量是: " + this.temp,
          });
          // this.updateOrderData = value;
          // this.$alert(this.updateOrderData);
          row.add = value;
          //this.$alert(row.count);
          axios
            .get(
              "http://localhost:8181/drugShoppingCart/save/" +
                row.eid +
                "/" +
                row.name +
                "/" +
                row.add +
                "/" +
                row.price +
                "/" +
                row.provider
            )
            .then(function (resp) {
              if (resp.data == "success") {
                //window.location.reload();
                row.count = _this.temp;
                _this.$alert("修改成功");
              } else {
                _this.$alert("修改失败");
              }
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消输入",
          });
        });
    },
    decreaseOrder(row) {
      const _this = this;
      this.$prompt("请输入要减少的数量", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputPattern: /^[1-9]\d*$/,
        inputErrorMessage: "只能输入正整数",
      })
        .then(({ value }) => {
          if (value > row.count) {
            this.$message({
              type: "warning",
              message: "减少的数量不能大于购物车内的数量 ",
            });
          } else {
            this.temp = row.count - value;
            this.totalPrice = this.temp;
            this.$message({
              type: "success",
              message: "现在的数量是: " + this.temp,
            });
            // this.updateOrderData = value;
            // this.$alert(this.updateOrderData);
            row.add = -value;
            // //this.$alert(row.add);
            axios
              .get(
                "http://localhost:8181/drugShoppingCart/save/" +
                  row.eid +
                  "/" +
                  row.name +
                  "/" +
                  row.add +
                  "/" +
                  row.price +
                  "/" +
                  row.provider
              )
              .then(function (resp) {
                if (resp.data == "success") {
                  //window.location.reload();
                  row.count = _this.temp;
                  _this.$alert("修改成功");
                } else {
                  _this.$alert("修改失败");
                }
              });
          }
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消输入",
          });
        });
    },
    deleteOrder(row) {
      const _this = this;
      axios
        .delete("http://localhost:8181/drugShoppingCart/deleteById/" + row.id)
        .then(function (resp) {
          _this.$alert(row.name + "已经从购物车移除", "删除成功", {
            confirmButtonText: "确定",
            callback: (action) => {
              //_this.$router.push('/pageOne')
              window.location.reload();
            },
          });
        });
    },
    refresh() {},
    edit(row) {
      this.$router.push({
        path: "/medicineUpdate",
        query: {
          id: row.id,
        },
      });
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
            .get(
              "http://localhost:8181/drugShoppingCart/findByName/" +
                _this.input1
            )
            .then(function (resp) {
              //console.log(resp);
              _this.tableData = resp.data;
            });
        } else if (this.currentId == 2) {
          //alert('进入循环')
          const _this = this;
          axios
            .get(
              "http://localhost:8181/drugShoppingCart/findByProvider/" +
                _this.input1
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
      axios
        .get("http://localhost:8181/drugShoppingCart/findAll")
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
    axios
      .get("http://localhost:8181/drugShoppingCart/findAll")
      .then(function (resp) {
        console.log(resp);
        _this.tableData = resp.data;
        if (resp != null) {
          _this.eid = resp.data[0].eid;
        }
        for (var i = 0; i < resp.data.length; i++) {
          _this.totalPrice += resp.data[i].price * resp.data[i].count;
        }
        // _this.$alert(_this.totalPrice)
        // _this.total = resp.data.totalElements
      });
  },
  data() {
    return {
      json_fields: {
        药品名称: "name", //常规字段
        药品数量: "count", //支持嵌套属性
        药品单价: "price",
        药品供应商: "provider",
      },
      json_data: {
        name: "Tony Peña",
        count: "New York",
        price: "United States",
        provider: "1978-03-15",
      },
      json_meta: [
        [
          {
            " key ": " charset ",
            " value ": " utf- 8 ",
          },
        ],
      ],
      temp: 0,
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
        { id: 1, val: "药品名称" },
        { id: 2, val: "供应商名称" },
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
