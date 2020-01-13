<template>
    <div class="wrapper">
        <v-header></v-header>
        <v-left></v-left>
        <div class="content">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
    import vHeader from './Header.vue';
    import vLeft from './Left.vue';
    export default {
        components:{
            vHeader,
            vLeft,
        },
        created(){
            if (!localStorage.getItem('isLoagin')){
                this.$message.error('请先登录');
                this.$router.push('/login');
            }
        }
    }
</script>
