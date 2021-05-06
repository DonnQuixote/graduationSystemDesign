
<template>
  <div >
    <div class="background">
    <img :src="imgSrc" width="100%" height="100%" alt="" />
    </div>
    <el-form ref="form" :rules="rules" :model="form" class="login-box">
      <h3 class="login-title">欢迎登录</h3>
      <el-form-item label="账号" prop="name">
        <el-input
          type="text"
          placeholder="请输入用户名"
          v-model="form.name"
        ></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          type="password"
          placeholder="请输入密码"
          v-model="form.password"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      imgSrc:require('../../pic/bac.jpg'),
      form: {
        name: "",
        password: "",
      },
      rules: {
        name: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 1, message: "长度至少一个字符", trigger: "blur" },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
    };
  },
  methods: {
    onSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
            sessionStorage.setItem("isLogin","true");
           // alert(this.form.name);
            if(this.form.name=='clerk'&&this.form.password=='123456'){
                 sessionStorage.setItem("isClerk","true");
                 this.$router.push("/frontShow");
            }else if(this.form.name=="admin"&&this.form.password=='123456'){
                sessionStorage.setItem("isAdmin","true");
                this.$router.push("/back");
            } else{
              this.form.name="";
              this.form.password="";
              this.$message({
                 message: "用户名或密码错误",
                 type: "warning",
          });
              //window.location.reload();
            }
        } else {
          //alert("请输入用户名和密码");
          this.$message({
            message: "用户名或密码错误",
            type: "warning",
          });
        }
      });
    },
  },
};
</script>
<style  scoped>
.background{
    width:100%;  
    height:100%;  /**宽高100%是为了图片铺满屏幕 */
    z-index:-1;
    position: absolute;
}
.login-box {
  width: 350px;
  margin: 150px;
  border: 1px solid #dcdfe6;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 30px #dcdfe6;

  text-align: center;
	background-color: #fff;
	border-radius: 10px;
	width: 300px;
	height: 350px;
	margin: auto;
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;

}
.login-title {
  text-align: center;
}

</style>

