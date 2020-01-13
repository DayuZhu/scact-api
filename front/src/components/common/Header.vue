<template>
    <div class="header">
        <h2>后台管理系统</h2>
        <div class="userInfo" v-if="false">
            欢迎你,
            {{
                name
            }},
            <router-link to="/password">修改密码</router-link>
            <a @click="logut()">退出</a>
        </div>
    </div>
</template>
<script>
    import { Message } from 'element-ui';
    export default {
        data(){
            return{
                name:sessionStorage.getItem('user') ? JSON.parse(sessionStorage.getItem('user')).name : '未登录',
            }
        },
        methods:{
            logut(){
                sessionStorage.clear();
                this.$router.push('/login');
                Message.success('退出成功！');
            }
        }
    }
</script>
