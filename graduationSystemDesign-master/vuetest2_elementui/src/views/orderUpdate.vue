<template>
  <el-container style="height: 750px; border: 1px solid #eee">
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="订单编号" prop="oid">
        <el-input v-model="ruleForm.oid" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="订单时间" prop="ordertime">
        <el-input v-model="ruleForm.ordertime" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="订单状态" prop="orderstate">
       <el-select v-model="value" placeholder="请选择" @change="change1($event)">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.label"
        >
        </el-option>
      </el-select>
      </el-form-item>
      <el-form-item label="订单总价" prop="totalprice">
        <el-input v-model="ruleForm.totalprice" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')"
          >提交</el-button
        >
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
    <el-main>
      <router-view>
        <!-- window.location.reload() -->
      </router-view>
    </el-main>
  </el-container>
</template>
<script>
export default {
  data() {
    return {
        currentId:1,
        oid:0,
      options: [
        {
          value: "选项1",
          label: "等待处理",
        },
        {
          value: "选项2",
          label: "出库成功",
        },
      ],
      value: "",
      ruleForm: {
        oid: "",
        ordertime: "",
        orderstate: "",
        totalprice: "",
      },
      open_list: ["1"],
      rules: {
        phonenumber: [
          { required: true, message: "请输入联系人电话", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
          change1(event) {
            console.log(event)
      //this.currentId = event.target.value; //wrong
     // alert("选择查询的种类是" + this.options[this.currentId - 1].label);
    },
    test() {
      console.log(this.ruleForm);
    },
    refresh() {},
    submitForm(formName) {
      const _this = this;
      //this.$alert(this.value)
      axios.get("http://localhost:8181/orderFront/updateOrderState/"+_this.oid+'/'+_this.value);
      this.$alert("订单状态已经更改")
      this.$router.push({
        path: "/orderProcess",
      });
      window.location.reload();
    },
    resetForm(formName) {
      // this.$refs[formName].resetFields();
      window.location.reload();
    },
  },

  created() {
    //alert(this.$route.query.id)
    const _this = this;
    this.oid = this.$route.query.id;
    axios
      .get("http://localhost:8181/orderFront/findByOid/" + this.$route.query.id)
      .then(function (resp) {
        _this.ruleForm = resp.data;
      });
  },
};
</script>
