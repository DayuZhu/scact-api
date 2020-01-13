webpackJsonp([2],{

/***/ 539:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(568)

var Component = __webpack_require__(87)(
  /* script */
  __webpack_require__(548),
  /* template */
  __webpack_require__(561),
  /* scopeId */
  "data-v-5ee80750",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 548:
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
				merchantId: null,
				merchantName: null,
				bossName: null,
				balance: null,
				bossTel: null,
				socialCode: null,
				mobile: null,
				name: null,
				merchantBank: null,
				merchantCard: null,
				address: null
			},
			rules: {
				activityName: [{ required: true, message: '请输入活动名称', trigger: 'change' }],
				address: [{ required: true, message: '请输入商家地址', trigger: 'change' }],
				bossName: [{ required: true, message: '请输入店主名称', trigger: 'change' }],
				bossTel: [{ required: true, message: '请输入店主电话', trigger: 'change' }],
				merchantBank: [{ required: true, message: '请输入开户行', trigger: 'change' }],
				merchantCard: [{ required: true, message: '请输入银行卡号', trigger: 'change' }],
				merchantName: [{ required: true, message: '请输入商家名称', trigger: 'change' }],
				mobile: [{ required: true, message: '请输入法人手机号', trigger: 'change' }],
				name: [{ required: true, message: '请输入法人姓名', trigger: 'change' }],
				socialCode: [{ required: true, message: '请输入社会统一代码', trigger: 'change' }],
				balance: [{ required: true, message: '请输入余额', trigger: 'change' }]
			},
			formLabelWidth: '120px'
		};
	},
	methods: {
		initData() {
			this.addParam = {
				merchantId: null,
				merchantName: null,
				bossName: null,
				balance: null,
				bossTel: null,
				socialCode: null,
				mobile: null,
				name: null,
				merchantBank: null,
				merchantCard: null,
				address: null
			};
		},
		onSearch(start) {
			//搜索
			this.searchLoading = true;
			this.searchParam.pageIndex = start || 1;
			//console.log('搜索条件',this.searchParam);
			this.$axios.post("/mis/merchant/info/list", this.searchParam).then(res => {
				this.searchLoading = false;
				this.tableData = res.data;
			});
		},
		handleAdd(formName) {
			//新增
			this.$refs[formName].validate(valid => {
				if (valid) {
					const {
						merchantName,
						bossName,
						balance,
						bossTel,
						socialCode,
						mobile,
						name,
						merchantBank,
						merchantCard,
						address
					} = this.addParam;
					const params = {
						merchantName,
						bossName,
						bossTel,
						socialCode,
						mobile,
						name,
						merchantBank,
						merchantCard,
						address,
						balance: balance * 100
					};
					this.addLoading = true;
					this.$axios.post("/mis/merchant/create_modify", params).then(res => {
						this.dialogFormVisible = false;
						this.addLoading = false;
						this.$message({
							message: this.addParam.merchantId ? '修改成功' : '创建成功',
							type: 'success'
						});
						this.initData();
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
		ticketCard(list) {
			let card = '';
			list.forEach(item => {
				card += item.ticketCode + ',';
			});
			return card;
		},
		handleUpdata(item, look) {
			//修改
			this.initData();
			this.dialogFormVisible = true;
			this.look = look;
			if (item) {
				let {
					merchantId
				} = item;
				this.addLoading = true;
				this.$axios.get('/mis/merchant/info', { params: { merchantId } }).then(res => {
					this.addLoading = false;
					res.data.balance = (res.data.merchantAccountResponse.balance / 100).toFixed(2);
					res.data.merchantBank = res.data.merchantAccInfoResponse.merchantBank;
					res.data.merchantCard = res.data.merchantAccInfoResponse.merchantCard;
					this.addParam = res.data;
				});
			}
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
exports.push([module.i, ".handle-box[data-v-5ee80750]{margin-bottom:20px}.handle-select[data-v-5ee80750]{width:120px}.handle-input[data-v-5ee80750]{width:300px;display:inline-block}", ""]);

// exports


/***/ }),

/***/ 561:
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
  }, [_vm._v("新建")])], 1)], 1), _vm._v(" "), _c('el-table', {
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
      "label": "商户id",
      "prop": "merchantId",
      "width": "100",
      "align": "center"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "merchantName",
      "label": "商家名称"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "bossName",
      "label": "店主名称"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "bossTel",
      "label": "店主电话"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "name",
      "label": "法人姓名"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "mobile",
      "label": "法人手机号"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "socialCode",
      "label": "社会统一代码"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "createTime",
      "label": "创建时间"
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
        }, [_vm._v("查看")])]
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
      "label": "商家名称",
      "prop": "merchantName",
      "label-width": _vm.formLabelWidth
    }
  }, [(!_vm.look) ? _c('el-input', {
    model: {
      value: (_vm.addParam.merchantName),
      callback: function($$v) {
        _vm.$set(_vm.addParam, "merchantName", $$v)
      },
      expression: "addParam.merchantName"
    }
  }) : _vm._e(), _vm._v(" "), (!!_vm.look) ? _c('span', [_vm._v(_vm._s(_vm.addParam.merchantName))]) : _vm._e()], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "店主名称",
      "prop": "bossName",
      "label-width": _vm.formLabelWidth
    }
  }, [(!_vm.look) ? _c('el-input', {
    model: {
      value: (_vm.addParam.bossName),
      callback: function($$v) {
        _vm.$set(_vm.addParam, "bossName", $$v)
      },
      expression: "addParam.bossName"
    }
  }) : _vm._e(), _vm._v(" "), (!!_vm.look) ? _c('span', [_vm._v(_vm._s(_vm.addParam.bossName))]) : _vm._e()], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "店主电话",
      "prop": "bossTel",
      "label-width": _vm.formLabelWidth
    }
  }, [(!_vm.look) ? _c('el-input', {
    model: {
      value: (_vm.addParam.bossTel),
      callback: function($$v) {
        _vm.$set(_vm.addParam, "bossTel", $$v)
      },
      expression: "addParam.bossTel"
    }
  }) : _vm._e(), _vm._v(" "), (!!_vm.look) ? _c('span', [_vm._v(_vm._s(_vm.addParam.bossTel))]) : _vm._e()], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "法人手机号",
      "prop": "mobile",
      "label-width": _vm.formLabelWidth
    }
  }, [(!_vm.look) ? _c('el-input', {
    attrs: {
      "max-length": "11"
    },
    model: {
      value: (_vm.addParam.mobile),
      callback: function($$v) {
        _vm.$set(_vm.addParam, "mobile", $$v)
      },
      expression: "addParam.mobile"
    }
  }) : _vm._e(), _vm._v(" "), (!!_vm.look) ? _c('span', [_vm._v(_vm._s(_vm.addParam.mobile))]) : _vm._e()], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "法人姓名",
      "prop": "name",
      "label-width": _vm.formLabelWidth
    }
  }, [(!_vm.look) ? _c('el-input', {
    model: {
      value: (_vm.addParam.name),
      callback: function($$v) {
        _vm.$set(_vm.addParam, "name", $$v)
      },
      expression: "addParam.name"
    }
  }) : _vm._e(), _vm._v(" "), (!!_vm.look) ? _c('span', [_vm._v(_vm._s(_vm.addParam.name))]) : _vm._e()], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "余额(元)",
      "prop": "balance",
      "label-width": _vm.formLabelWidth
    }
  }, [(!_vm.look) ? _c('el-input', {
    model: {
      value: (_vm.addParam.balance),
      callback: function($$v) {
        _vm.$set(_vm.addParam, "balance", $$v)
      },
      expression: "addParam.balance"
    }
  }) : _vm._e(), _vm._v(" "), (!!_vm.look) ? _c('span', [_vm._v(_vm._s(_vm.addParam.balance))]) : _vm._e()], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "开户行",
      "prop": "merchantBank",
      "label-width": _vm.formLabelWidth
    }
  }, [(!_vm.look) ? _c('el-input', {
    model: {
      value: (_vm.addParam.merchantBank),
      callback: function($$v) {
        _vm.$set(_vm.addParam, "merchantBank", $$v)
      },
      expression: "addParam.merchantBank"
    }
  }) : _vm._e(), _vm._v(" "), (!!_vm.look) ? _c('span', [_vm._v(_vm._s(_vm.addParam.merchantBank))]) : _vm._e()], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "银行卡号",
      "prop": "merchantCard",
      "label-width": _vm.formLabelWidth
    }
  }, [(!_vm.look) ? _c('el-input', {
    model: {
      value: (_vm.addParam.merchantCard),
      callback: function($$v) {
        _vm.$set(_vm.addParam, "merchantCard", $$v)
      },
      expression: "addParam.merchantCard"
    }
  }) : _vm._e(), _vm._v(" "), (!!_vm.look) ? _c('span', [_vm._v(_vm._s(_vm.addParam.merchantCard))]) : _vm._e()], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "社会统一代码",
      "prop": "socialCode",
      "label-width": _vm.formLabelWidth
    }
  }, [(!_vm.look) ? _c('el-input', {
    model: {
      value: (_vm.addParam.socialCode),
      callback: function($$v) {
        _vm.$set(_vm.addParam, "socialCode", $$v)
      },
      expression: "addParam.socialCode"
    }
  }) : _vm._e(), _vm._v(" "), (!!_vm.look) ? _c('span', [_vm._v(_vm._s(_vm.addParam.socialCode))]) : _vm._e()], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "商家地址",
      "prop": "address",
      "label-width": _vm.formLabelWidth
    }
  }, [(!_vm.look) ? _c('el-input', {
    model: {
      value: (_vm.addParam.address),
      callback: function($$v) {
        _vm.$set(_vm.addParam, "address", $$v)
      },
      expression: "addParam.address"
    }
  }) : _vm._e(), _vm._v(" "), (!!_vm.look) ? _c('span', [_vm._v(_vm._s(_vm.addParam.address))]) : _vm._e()], 1), _vm._v(" "), (!_vm.look) ? _c('div', {
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
  }, [_vm._v("确 定")])], 1) : _vm._e()], 1)], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 568:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(551);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(207)("a858a3ca", content, true);

/***/ })

});