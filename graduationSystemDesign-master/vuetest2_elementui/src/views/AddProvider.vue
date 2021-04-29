<template>
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    label-width="100px"
    class="demo-ruleForm"
  >
    <el-form-item label="供应商名称" prop="provider">
      <el-input v-model="ruleForm.provider"></el-input>
    </el-form-item>
    <el-form-item label="联系人" prop="director">
      <el-input v-model="ruleForm.director"></el-input>
    </el-form-item>
    <el-form-item label="联系电话" prop="phonenumber">
      <el-input
        placeholder="输入类型只能是数字"
        v-model="ruleForm.phonenumber"
        type="text"
        oninput="value=value.replace(/[^\d]/g,'')"
      ></el-input>
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
      ruleForm: {
        provider: "",
        phonenumber: "",
        provider: "",
      },
      rules: {
        phonenumber: [
          { required: true, message: "请输入联系人电话", trigger: "blur" },
          { min: 1, message: "长度至少为1", trigger: "blur" },
        ],
        director: [
          { required: true, message: "请输入联系人姓名", trigger: "blur" },
        ],
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
            .post("http://localhost:8181/provider/save", this.ruleForm)
            .then(function (resp) {
              if (resp.data == "success") {
                // alert('添加成功')
                // _this.$router.push('/pageOne')
                _this.$alert(
                  "供应商： " + _this.ruleForm.provider + " 信息已经入库",
                  "添加成功",
                  {
                    confirmButtonText: "确定",
                    callback: (action) => {
                      _this.$router.push("/providerShow");
                      //window.location.reload()
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
