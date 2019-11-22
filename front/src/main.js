import Vue from 'vue';
import App from './App';
import router from './router';
import axios from 'axios';
import ElementUI from 'element-ui';
import { instance, multipart } from 'src/components/util/api'
import * as filters from 'src/components/util/filters'
import 'src/assets/common.css'
import 'src/assets/common'
//import 'element-ui/lib/theme-default/index.css';    // 默认主题
// import '../static/css/theme-green/index.css';       // 浅绿色主题
import "babel-polyfill";
import VueQuillEditor  from 'vue-quill-editor'
import JsonExcel from 'vue-json-excel'

import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

Vue.use(ElementUI);
Vue.use(VueQuillEditor);
Vue.component('downloadExcel', JsonExcel)
// 添加过滤器
Object.keys(filters).forEach(key => {
    Vue.filter(key, filters[key])
})
Vue.prototype.$axios = instance;
Vue.prototype.multipart = multipart;
new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
