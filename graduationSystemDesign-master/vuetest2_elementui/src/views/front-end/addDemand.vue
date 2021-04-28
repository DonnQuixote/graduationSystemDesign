<template>

    <el-container style="height: 750px; width:1500px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
            <el-menu router :default-openeds="open_list" >
                <el-submenu v-for="(item,index) in $router.options.routes2" test = index :index="index+''" v-if="item.show" >
                    <template slot="title"><i class="el-icon-message"></i>{{item.name}}</template>
                    <el-menu-item @click="refresh()" v-for="(items2,index2) in item.children" :index="items2.path" :class="$route.path==items2.path?'is-active':''">{{items2.name}}</el-menu-item>
                </el-submenu>
            </el-menu>
        </el-aside>

    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="300px" class="demo-ruleForm">
        <el-form-item label="需求药品名称" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="药品供应商" prop="provider">
            <el-input v-model="ruleForm.provider"></el-input>
        </el-form-item>
        <el-form-item label="大致价钱" prop="price">
            <el-input placeholder="输入类型只能是数字,可包含小数" v-model="ruleForm.price"  type="text"
         oninput="value=value.replace(/[^0-9.]/g,'')"></el-input>
        </el-form-item>
        <el-form-item label="需求数量" prop="count">
            <el-input placeholder="输入类型只能是数字" v-model="ruleForm.count"  type="text"
        oninput="value=value.replace(/[^\d]/g,'')" ></el-input>
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
                open_list:["0","1","2"],
                ruleForm: {
                    provider: '',
                    phonenumber: '',
                    provider:'',
                },
                rules: {
                     price: [
                        { required: true, message: '请输入需求药品大致价钱', trigger: 'blur' },
                    ],
                     name: [
                        { required: true, message: '请输入需求药品名称', trigger: 'blur' },
                    ],
                     provider: [
                        { required: true, message: '请输入供应商', trigger: 'blur' },
                        { min: 1, message: '长度至少为1', trigger: 'blur' }
                    ],
                     count: [
                        { required: true, message: '请输入需求数量', trigger: 'blur' },
                        { min: 1, message: '长度至少为1', trigger: 'blur' }
                    ],
                    
                }
            };
        },
        methods: {
            test(){
                console.log(this.ruleForm)
            },
            submitForm(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // alert('校验通过!');
                        // console.log(_this.ruleForm)
                        axios.post('http://localhost:8181/demand/save',this.ruleForm).then(function (resp) {
                            if(resp.data=='success'){
                                // alert('添加成功')
                                // _this.$router.push('/pageOne')
                                _this.$alert( '需求药品： '+_this.ruleForm.name+' 信息已经入库', '添加成功', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/demand')
                                         //window.location.reload()
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
                this.$refs[formName].resetFields();
            }
        }
    }
</script>
