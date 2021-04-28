<template>

    <el-container style="height: 750px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
            <el-menu router :default-openeds="open_list" >
                <el-submenu v-for="(item,index) in $router.options.routes" test = index :index="index+''" v-if="item.show" >
                    <template slot="title"><i class="el-icon-message"></i>{{item.name}}</template>
                    <el-menu-item @click="refresh()" v-for="(items2,index2) in item.children" :index="items2.path" :class="$route.path==items2.path?'is-active':''">{{items2.name}}</el-menu-item>
                </el-submenu>
            </el-menu>
        </el-aside>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="供应商名称" prop="provider">
            <el-input v-model="ruleForm.provider" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="联系人" prop="director">
            <el-input v-model="ruleForm.director"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="phonenumber">
            <el-input placeholder="输入类型只能是数字" v-model="ruleForm.phonenumber"  type="text"
        oninput="value=value.replace(/[^\d]/g,'')"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
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
                ruleForm: {
                    provider: '',
                    phonenumber: '',
                    provider:'',
                },
                open_list:["1"],
                rules: {
                     phonenumber: [
                        { required: true, message: '请输入联系人电话', trigger: 'blur' },
                    ],
                     director: [
                        { required: true, message: '请输入联系人姓名', trigger: 'blur' },
                    ],
                     provider: [
                        { required: true, message: '请输入供应商', trigger: 'blur' },
                        { min: 1, message: '长度至少为1', trigger: 'blur' }
                    ],
                    
                }
            };
        },
         methods: {
            test(){
                console.log(this.ruleForm)
            },
            refresh(){

            },
            submitForm(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // alert('校验通过!');
                        // console.log(_this.ruleForm)
                        axios.put('http://localhost:8181/provider/update',this.ruleForm).then(function (resp) {
                            if(resp.data=='success'){
                                // alert('添加成功')
                                // _this.$router.push('/pageOne')
                                _this.$alert( '供应商：'+_this.ruleForm.provider+' 信息已经修改并入库', '修改成功', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/providerShow')
                                    }
                                });
                            }
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                // this.$refs[formName].resetFields();
                 window.location.reload()
            }
        },

        created() {
            //alert(this.$route.query.id)
            const _this = this
            axios.get('http://localhost:8181/provider/findById/'+this.$route.query.id).then(function (resp) {
                _this.ruleForm = resp.data
            })
        }
    }
</script>
