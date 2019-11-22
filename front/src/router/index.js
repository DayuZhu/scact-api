import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/active'
        },
        {
            path: '/index',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            children:[
                {
                    path: '/',
                    component: resolve => require(['../components/page/active.vue'], resolve)
                },
                {
                    path: '/active',
                    component: resolve => require(['../components/page/active.vue'], resolve)
                },
                {
                    path: '/bill',
                    component: resolve => require(['../components/page/bill.vue'], resolve)
                },
            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
        {
            path: '/password',
            component: resolve => require(['../components/page/Pass.vue'], resolve)
        },
        {
            path: '/test',
            component: resolve => require(['../components/page/Test.vue'], resolve)
        },
    ]
})
