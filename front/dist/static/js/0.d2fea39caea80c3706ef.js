webpackJsonp([0],{

/***/ 208:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(566)

var Component = __webpack_require__(87)(
  /* script */
  __webpack_require__(545),
  /* template */
  __webpack_require__(560),
  /* scopeId */
  "data-v-9b66dc68",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 545:
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
//
//
//
//
//
//
//

const url = '';
/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            dialogFormVisible: false, //新增修改弹窗
            addLoading: false, //添加loading
            searchLoading: false, //搜索loading
            prizeParams: {
                loading: false,
                show: false,
                list: null
            },
            look: false,
            id: null,
            formData: null,
            // tableData: null,
            tableData: {
                list: []
            },
            searchParam: {
                pageSize: 20,
                pageIndex: 1
            },
            winnersList: null,
            addParam: {
                activityName: null,
                times: []
            },
            rules: {
                activityName: [{ required: true, message: '请输入活动名称', trigger: 'change' }],
                times: [{ required: true, message: '请选择活动时间', trigger: 'change' }]
            },
            formLabelWidth: '100px'
        };
    },
    methods: {
        onSearch(start) {
            //搜索
            this.searchLoading = true;
            this.searchParam.pageIndex = start || 1;
            //console.log('搜索条件',this.searchParam);
            this.$axios.post("/mis/activity/info/list", this.searchParam).then(res => {
                this.searchLoading = false;
                this.tableData = res.data;
            });
        },
        handleAdd(formName) {
            //新增
            this.$refs[formName].validate(valid => {
                if (valid) {
                    const {
                        activityName,
                        times
                    } = this.addParam;
                    const params = {
                        activityName,
                        startTime: times[0],
                        endTime: times[1]
                    };
                    this.addLoading = true;
                    this.$axios.post("/mis/activity/create_modify", params).then(res => {
                        this.dialogFormVisible = false;
                        this.addLoading = false;
                        this.$message({
                            message: this.addParam.activityId ? '修改成功' : '创建成功',
                            type: 'success'
                        });
                        this.onSearch();
                    }).catch(error => {
                        this.addLoading = false;
                        this.$message.error(error.response.data.message);
                    });
                } else {
                    return false;
                }
            });
        },
        handleDownLoad() {
            window.location = url + '/mis/activity/winners/download/excel';
        },
        ticketCard(list) {
            let card = '';
            list.forEach(item => {
                card += item.ticketCode + ',';
            });
            return card;
        },
        handleUpdata(item, look) {
            //修改
            console.log(item);
            this.dialogFormVisible = true;
            this.look = look;
            if (item) {
                let {
                    activityId
                } = item;
                this.addLoading = true;
                this.$axios.get('/mis/activity/info', { params: { activityId } }).then(res => {
                    this.addLoading = false;
                    if (!look) {
                        res.data.times = [res.data.startTime, res.data.endTime];
                    }
                    this.addParam = res.data;
                });
            }
        },
        lookPeople(item) {
            let {
                activityId
            } = item;
            this.prizeParams.show = true;
            this.prizeParams.loading = true;
            this.$axios.get('/mis/activity/winners/info', { params: { activityId } }).then(res => {
                this.prizeParams.loading = false;
                this.prizeParams.list = res.data;
            });
        },
        update(e, item) {
            console.log(e, item);
            this.searchLoading = true;
            this.file = e.target.files[0];
            let formData = new FormData();
            formData.append('file', this.file);
            this.$axios.post('/mis/activity/winners/upload/xlsx?activityId=' + item.activityId, formData).then(res => {
                this.searchLoading = false;
                if (res.retCode) {
                    this.$message.success('上传成功');
                }
            }).catch(err => {
                this.$message.error(err.retMsg);
            });
        }
    },
    created() {
        this.onSearch();
    }
});

/***/ }),

/***/ 551:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(206)(false);
// imports


// module
exports.push([module.i, ".handle-box[data-v-9b66dc68]{margin-bottom:20px}.handle-select[data-v-9b66dc68]{width:120px}.handle-input[data-v-9b66dc68]{width:300px;display:inline-block}", ""]);

// exports


/***/ }),

/***/ 560:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('el-form', {
    staticClass: "demo-form-inline",
    attrs: {
      "inline": true,
      "model": _vm.searchParam
    }
  }, [_c('el-form-item', {
    staticStyle: {
      "display": "block",
      "float": "left"
    }
  }, [_c('el-button', {
    attrs: {
      "type": "primary",
      "icon": "el-icon-plus"
    },
    on: {
      "click": function($event) {
        _vm.handleUpdata()
      }
    }
  }, [_vm._v("新建")])], 1), _vm._v(" "), _c('el-form-item', {
    staticStyle: {
      "display": "block",
      "float": "right"
    }
  }, [_c('el-button', {
    attrs: {
      "type": "primary",
      "icon": "el-icon-plus"
    },
    on: {
      "click": function($event) {
        _vm.handleDownLoad()
      }
    }
  }, [_vm._v("下载模板")])], 1)], 1), _vm._v(" "), _c('el-table', {
    directives: [{
      name: "loading",
      rawName: "v-loading",
      value: (_vm.searchLoading),
      expression: "searchLoading"
    }],
    staticStyle: {
      "width": "100%"
    },
    attrs: {
      "data": _vm.tableData && _vm.tableData.list || [],
      "border": ""
    }
  }, [_c('el-table-column', {
    attrs: {
      "label": "活动id",
      "prop": "activityId",
      "width": "100",
      "align": "center"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "activityName",
      "label": "活动名称"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "startTime",
      "label": "开始时间"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "endTime",
      "label": "结束时间"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "min-width": "100",
      "label": "操作",
      "align": "center"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-button', {
          attrs: {
            "type": "text",
            "size": "small"
          },
          on: {
            "click": function($event) {
              _vm.handleUpdata(scope.row, true)
            }
          }
        }, [_vm._v("查看")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "type": "text",
            "size": "small"
          },
          on: {
            "click": function($event) {
              _vm.lookPeople(scope.row)
            }
          }
        }, [_vm._v("查看中奖名单")]), _vm._v(" "), _c('label', {
          staticClass: "adFile"
        }, [_vm._v("\n                    上传活动中奖名单\n                    "), _c('input', {
          staticClass: "file",
          attrs: {
            "name": "file",
            "type": "file",
            "accept": "excel"
          },
          on: {
            "change": function($event) {
              _vm.update($event, scope.row)
            }
          }
        })])]
      }
    }])
  })], 1), _vm._v(" "), (_vm.tableData && _vm.tableData.total) ? _c('div', {
    staticClass: "pagination"
  }, [_c('el-pagination', {
    attrs: {
      "layout": "prev, pager, next",
      "page-size": _vm.searchParam.pageSize,
      "total": _vm.tableData.total || 0
    },
    on: {
      "current-change": _vm.onSearch
    }
  })], 1) : _vm._e(), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "活动",
      "visible": _vm.dialogFormVisible
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisible = $event
      }
    }
  }, [_c('el-form', {
    directives: [{
      name: "loading",
      rawName: "v-loading",
      value: (_vm.addLoading),
      expression: "addLoading"
    }],
    ref: "addParam",
    attrs: {
      "model": _vm.addParam,
      "rules": _vm.rules
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "活动名称",
      "prop": "activityName",
      "label-width": _vm.formLabelWidth
    }
  }, [(!_vm.look) ? _c('el-input', {
    model: {
      value: (_vm.addParam.activityName),
      callback: function($$v) {
        _vm.$set(_vm.addParam, "activityName", $$v)
      },
      expression: "addParam.activityName"
    }
  }) : _vm._e(), _vm._v(" "), (!!_vm.look) ? _c('span', [_vm._v(_vm._s(_vm.addParam.activityName))]) : _vm._e()], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "活动时间",
      "prop": "times",
      "label-width": _vm.formLabelWidth
    }
  }, [(!_vm.look) ? _c('el-date-picker', {
    attrs: {
      "type": "datetimerange",
      "value-format": "yyyy-MM-dd hh:mm:ss",
      "range-separator": "至",
      "start-placeholder": "开始日期",
      "end-placeholder": "结束日期"
    },
    model: {
      value: (_vm.addParam.times),
      callback: function($$v) {
        _vm.$set(_vm.addParam, "times", $$v)
      },
      expression: "addParam.times"
    }
  }) : _vm._e(), _vm._v(" "), (!!_vm.look) ? _c('span', [_vm._v("\n                    " + _vm._s(((_vm.addParam.startTime) + "~" + (_vm.addParam.endTime))) + "\n                ")]) : _vm._e()], 1), _vm._v(" "), (!_vm.look) ? _c('div', {
    staticClass: "dialog-footer"
  }, [_c('el-button', {
    on: {
      "click": function($event) {
        _vm.dialogFormVisible = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.handleAdd('addParam')
      }
    }
  }, [_vm._v("确 定")])], 1) : _vm._e()], 1)], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "中奖名单",
      "visible": _vm.prizeParams.show
    },
    on: {
      "update:visible": function($event) {
        _vm.$set(_vm.prizeParams, "show", $event)
      }
    }
  }, [_c('el-table', {
    directives: [{
      name: "loading",
      rawName: "v-loading",
      value: (_vm.prizeParams.loading),
      expression: "prizeParams.loading"
    }],
    staticStyle: {
      "width": "100%"
    },
    attrs: {
      "height": "400",
      "data": _vm.prizeParams.list || [],
      "border": ""
    }
  }, [_c('el-table-column', {
    attrs: {
      "prop": "activityId",
      "label": "活动id"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "awardAmount",
      "label": "中奖金额"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s((scope.row.awardAmount / 100).toFixed(2)) + "\n                ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "中奖人"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(scope.row.user.name) + "\n                ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "中奖人银行卡号"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(((scope.row.userAccInfo.cardNumber) + "(" + (scope.row.userAccInfo.bankName) + ")")) + "\n                ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "中奖产品"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(((scope.row.product.productName) + "(" + (scope.row.product.outProductId) + ")")) + "\n                ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "券号"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(_vm.ticketCard(scope.row.tickets)) + "\n                ")]
      }
    }])
  })], 1)], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 566:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(551);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(207)("7f6b5802", content, true);

/***/ })

});