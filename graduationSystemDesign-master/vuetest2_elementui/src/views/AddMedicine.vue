<template>
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    label-width="100px"
    class="demo-ruleForm"
  >
    <el-form-item label="药品名称" prop="name">
      <el-input v-model="ruleForm.name"></el-input>
    </el-form-item>
    <el-form-item label="药品状态" prop="state" v-if="false">
      <el-input v-model="ruleForm.state"></el-input>
    </el-form-item>
    <el-form-item label="生产日期" prop="storagedate">
      <el-date-picker
        v-model="ruleForm.storagedate"
        type="date"
        placeholder="选择药品生产日期"
        :picker-options="pickerOptions1"
      >
      </el-date-picker>
      <!-- //<el-input v-model="ruleForm.storagedate"></el-input> -->
    </el-form-item>
    <el-form-item label="截止日期" prop="enddate">
      <el-date-picker
        v-model="ruleForm.enddate"
        type="date"
        placeholder="选择药品有效截止日期"
        :picker-options="pickerOptions0"
      >
      </el-date-picker>
      <!-- //<el-input v-model="ruleForm.storagedate"></el-input> -->
    </el-form-item>
    <!-- <el-form-item label="有限期限" prop="expirydates">
            <el-input placeholder="输入类型只能是数字，单位为月，如：36" v-model="ruleForm.expirydates"></el-input>
        </el-form-item> -->
    <el-form-item label="数量" prop="count">
      <el-input
        placeholder="输入类型只能是数字"
        v-model="ruleForm.count"
         type="text"
        oninput="value=value.replace(/[^\d]/g,'')"
      ></el-input>
    </el-form-item>
    <el-form-item label="进价" prop="purchaseprice">
      <el-input
        placeholder="输入类型只能是数字,可有小数部分"
        v-model="ruleForm.purchaseprice"
         type="text"
        oninput="value=value.replace(/[^0-9.]/g,'')"
      ></el-input>
    </el-form-item>
    <el-form-item label="单价" prop="unitprice">
      <el-input
        placeholder="输入类型只能是数字，可有小数部分"
        v-model="ruleForm.unitprice"
         type="text"
         oninput="value=value.replace(/[^0-9.]/g,'')"
      ></el-input>
    </el-form-item>
    <el-form-item label="供应商" prop="provider">
      <el-input v-model="ruleForm.provider"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
export default {
  data() {
    return {
      pickerOptions0: {
        disabledDate(time) {
          return time.getTime() < Date.now() - 8.64e7;
        },
      },
      pickerOptions1: {
        disabledDate(time) {
          return time.getTime() > Date.now() - 8.64e6;
        },
      },
      ruleForm: {
        name: "",
        storagedate: "",
        enddate: "",
        count: "",
        expirydates: "",
        purchaseprice: "",
        unitprice: "",
        provider: "",
      },
      rules: {
        name: [
          { required: true, message: "请输入药品名称", trigger: "blur" },
          { min: 1, message: "长度至少一个字符", trigger: "blur" },
        ],
        storagedate: [
          { required: true, message: "请选择药品生产日期", trigger: "blur" },
        ],
        enddate: [
          {
            required: true,
            message: "请选择药品有效截止日期",
            trigger: "blur",
          },
        ],
        expirydates: [
          { required: true, message: "请输入药品有限期限", trigger: "blur" },
        ],
        count: [{ required: true, message: "请输入药品数量", trigger: "blur" }],
        purchaseprice: [
          { required: true, message: "请输入进价", trigger: "blur" },
        ],
        unitprice: [{ required: true, message: "请输入单价", trigger: "blur" }],
        provider: [
          { required: true, message: "请输入供应商", trigger: "blur" },
          { min: 1, message: "长度至少为1", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    test() {
      console.log(this.ruleForm);
    },
    submitForm(formName) {
      const _this = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('校验通过!');
          // console.log(_this.ruleForm)
          axios
            .post("http://localhost:8181/book/save", this.ruleForm)
            .then(function (resp) {
              if (resp.data == "success") {
                // alert('添加成功')
                // _this.$router.push('/pageOne')
                _this.$alert(
                  "《" + _this.ruleForm.name + "》" + "已经入库",
                  "添加成功",
                  {
                    confirmButtonText: "确定",
                    callback: (action) => {
                      _this.$router.push("/medicineShow");
                    },
                  }
                );
              }
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>
