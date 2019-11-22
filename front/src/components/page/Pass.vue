<template>
    <div class="login-wrap">
        <div class="ms-title">修改密码</div>
        <div class="ms-login">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
                <el-form-item prop="passWord">
                    <el-input type="password" placeholder="新密码" v-model="ruleForm.passWord" @keyup.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                <el-form-item prop="passWord2">
                    <el-input type="password" placeholder="确认新密码" v-model="ruleForm.passWord2" @keyup.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                </div>
                <!--<p style="font-size:12px;line-height:30px;color:#999;">登录界面</p>-->
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        data: function(){
            let validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.ruleForm.passWord2 !== '') {
                        this.$refs.ruleForm.validateField('passWord2');
                    }
                    callback();
                }
            };
            let validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm.passWord) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                isLoading: false,
                ruleForm: {
                    passWord: '',
                    passWord2: ''
                },
                rules: {
                    passWord: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    passWord2: [
                        { validator: validatePass2, trigger: 'blur' }
                    ],
                }
            }
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.isLoading = true;
                        this.$axios.post('/edu/manage/updateUserPassWord',this.ruleForm).then((res)=>{
                            this.isLoading = false;
                            if(res.data.success){
                                this.$message.success('修改成功！');
                                //sessionStorage.setItem('user',JSON.stringify(res.data.value))
                                this.$router.push('/login');
                            }
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .login-wrap{
        position: relative;
        width:100%;
        height:100%;
    }
    .ms-title{
        position: absolute;
        top:50%;
        width:100%;
        margin-top: -230px;
        text-align: center;
        font-size:30px;
        color: #fff;
    }
    .ms-login{
        position: absolute;
        left:50%;
        top:50%;
        width:300px;
        height:160px;
        margin:-150px 0 0 -190px;
        padding:40px;
        border-radius: 5px;
        background: #fff;
    }
    .login-btn{
        text-align: center;
    }
    .login-btn button{
        width:100%;
        height:36px;
    }
</style>
