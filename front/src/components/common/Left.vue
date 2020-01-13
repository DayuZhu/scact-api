<template>
    <el-menu :default-active="onRoutes" class="el-menu-demo" mode="horizontal" @select="handleSelect" router>
        <template v-for="item in items">
            <el-menu-item :index="item.index">
                <i :class="item.icon"></i>{{ item.title }}
            </el-menu-item>
        </template>
    </el-menu>
</template>
<script>
    export default {
        data() {
            return {
                items: [
                    {
                        icon: 'el-icon-picture',
                        index: 'active',
                        title: '活动管理'
                    },
		                {
			                icon: 'el-icon-coin',
			                index: 'merchant',
			                title: '商户管理'
		                },
                    {
                        icon: 'el-icon-coin',
                        index: 'bill',
                        title: '分账'
                    },
                ]
            }
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
        },
        computed: {
            onRoutes() {
                return this.$route.path.replace('/', '');
            }
        }
    }
</script>
