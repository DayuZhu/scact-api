webpackJsonp([6],{

/***/ 535:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(570)

var Component = __webpack_require__(87)(
  /* script */
  __webpack_require__(543),
  /* template */
  __webpack_require__(563),
  /* scopeId */
  "data-v-b316f72e",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 543:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

/* harmony default export */ __webpack_exports__["default"] = ({
    data: function () {
        return {
            isLoading: false,
            ruleForm: {
                userName: '',
                password: ''
            },
            rules: {
                userName: [{ required: true, message: '请输入用户名', trigger: 'change' }],
                password: [{ required: true, message: '请输入密码', trigger: 'change' }]
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate(valid => {
                if (valid) {
                    console.log(this.ruleForm);
                    if (this.ruleForm.userName === 'admin' && this.ruleForm.password === 'admin') {
                        this.$message.success('登录成功！');
                        localStorage.setItem('isLoagin', 1);
                        this.$router.push('/active');
                    } else {
                        this.$message.error('用户名或密码不正确！');
                    }
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        }
    }
});

/***/ }),

/***/ 553:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(206)(false);
// imports


// module
exports.push([module.i, ".login-wrap[data-v-b316f72e]{position:relative;width:100%;height:100%}.ms-title[data-v-b316f72e]{position:absolute;top:50%;width:100%;margin-top:-230px;text-align:center;font-size:30px;color:#fff}.ms-login[data-v-b316f72e]{position:absolute;left:50%;top:50%;width:300px;height:160px;margin:-150px 0 0 -190px;padding:40px;border-radius:5px;background:#fff}.login-btn[data-v-b316f72e]{text-align:center}.login-btn button[data-v-b316f72e]{width:100%;height:36px}", ""]);

// exports


/***/ }),

/***/ 563:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "login-wrap"
  }, [_c('div', {
    staticClass: "ms-title"
  }, [_vm._v("后台管理系统")]), _vm._v(" "), _c('div', {
    staticClass: "ms-login"
  }, [_c('el-form', {
    ref: "ruleForm",
    staticClass: "demo-ruleForm",
    attrs: {
      "model": _vm.ruleForm,
      "rules": _vm.rules,
      "label-width": "0px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "prop": "userName"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "用户名"
    },
    model: {
      value: (_vm.ruleForm.userName),
      callback: function($$v) {
        _vm.$set(_vm.ruleForm, "userName", $$v)
      },
      expression: "ruleForm.userName"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "prop": "password"
    }
  }, [_c('el-input', {
    attrs: {
      "type": "password",
      "placeholder": "密码"
    },
    nativeOn: {
      "keyup": function($event) {
        if (!('button' in $event) && _vm._k($event.keyCode, "enter", 13, $event.key, "Enter")) { return null; }
        _vm.submitForm('ruleForm')
      }
    },
    model: {
      value: (_vm.ruleForm.password),
      callback: function($$v) {
        _vm.$set(_vm.ruleForm, "password", $$v)
      },
      expression: "ruleForm.password"
    }
  })], 1), _vm._v(" "), _c('div', {
    staticClass: "login-btn"
  }, [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.submitForm('ruleForm')
      }
    }
  }, [_vm._v("登录")])], 1)], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 570:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(553);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(207)("ac6c811a", content, true);

/***/ })

});