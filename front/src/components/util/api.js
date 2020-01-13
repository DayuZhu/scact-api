/*window.getUrlParms = function(name){
    let reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    let r = window.location.search.substr(1).match(reg);
    if(r!=null)
        return unescape(r[2]);
    return null;
}*/

import axios from 'axios';
import {Message} from 'element-ui';
// import { getCookie } from "./cookie";


//公用axios
const instance = axios.create({
	// headers: {'authentication': getCookie('authentication') }
})
instance.defaults.withCredentials = true
/*instance.defaults.transformRequest = [function (data) {
    let newData = ''
    for (let k in data) {
        newData += encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) + '&'
    }
    return newData
}]*/

//中间件
// 请求
instance.interceptors.request.use(request => {
	// request.headers.Authentication = localStorage.getItem('token') || ''//store.getters.token
	/*if (!sessionStorage.getItem('user')) {
		window.location.href = '#/login'
	}*/
	return request
})

// 响应
instance.interceptors.response.use(response => {
	console.log(response.data.retCode, 12312321);
	if (response.headers.authentication) {
		localStorage.setItem('token', response.headers.authentication)
	}
	if (response.data.retCode !== '0') {
		Message.error(response.data.retMsg)
		return Promise.resolve(err.response)
	} else if (response.data.retCode === 1000) {
		window.location.href = '/#/login'
	} else {
		return response.data
	}
}, err => {
	if (err && err.response) {
		if (err.response.data.message === "用户未登录") {
			sessionStorage.clear();
			window.location.href = '/#/login'
		}
		//错误信息
		Message.error(err.response.data.message);
	}
	return Promise.resolve(err.response)
})


//图片上传
const multipart = axios.create({
	withCredentials: true
})

multipart.interceptors.request.use(function (config) {
	config.headers['Content-Type'] = 'multipart/form-data'
	return config
})

export {
	instance, multipart
}
