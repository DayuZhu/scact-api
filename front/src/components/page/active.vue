<template>
	<div>
		<el-form :inline="true" :model="searchParam" class="demo-form-inline">
			<el-form-item style="display: block;float: left">
				<el-button type="primary" icon="el-icon-plus" @click="handleUpdata()">新建</el-button>
			</el-form-item>
			<el-form-item style="display: block;float: right">
				<el-button type="primary" icon="el-icon-plus" @click="handleDownLoad()">下载模板</el-button>
			</el-form-item>
		</el-form>
		<el-table
			v-loading="searchLoading"
			:data="tableData && tableData.list || []"
			border
			style="width: 100%">
			<el-table-column
				label="活动id"
				prop="activityId"
				width="100"
				align="center">
			</el-table-column>
			<el-table-column
				prop="activityName"
				label="活动名称">
			</el-table-column>
			<el-table-column
				prop="startTime"
				label="开始时间">
			</el-table-column>
			<el-table-column
				prop="endTime"
				label="结束时间">
			</el-table-column>
			<el-table-column
				min-width="100"
				label="操作"
				align="center">
				<template slot-scope="scope">
					<!--<el-button type="text" @click="handleUpdata(scope.row)" size="small">修改</el-button>-->
					<el-button type="text" @click="handleUpdata(scope.row, true)" size="small">查看</el-button>
					<el-button type="text" @click="lookPeople(scope.row)" size="small">查看中奖名单</el-button>
					<label class="adFile">
						上传活动中奖名单
						<input class="file" name="file" type="file" accept="excel"
						       @change="update($event, scope.row)"/>
					</label>
				</template>
			</el-table-column>
		</el-table>
		<div class="pagination" v-if="tableData && tableData.total">
			<el-pagination
				@current-change="onSearch"
				layout="prev, pager, next"
				:page-size="searchParam.pageSize"
				:total="tableData.total || 0">
			</el-pagination>
		</div>

		<el-dialog title="活动" :visible.sync="dialogFormVisible">
			<el-form :model="addParam" :rules="rules" ref="addParam" v-loading="addLoading">
				<el-form-item label="活动名称" prop="activityName" :label-width="formLabelWidth">
					<el-input v-model="addParam.activityName" v-if="!look"></el-input>
					<span v-if="!!look">{{addParam.activityName}}</span>
				</el-form-item>
				<el-form-item label="商户" prop="merchantId" :label-width="formLabelWidth">
					<el-select v-model="addParam.merchantId" placeholder="请选择" v-if="!look">
						<el-option
							v-for="item in merchantList"
							:key="item.merchantId"
							:label="item.merchantName+'(店长：'+item.bossName+')'"
							:value="item.merchantId">
						</el-option>
					</el-select>
					<span v-if="!!look">{{addParam.merchantName}}</span>
				</el-form-item>
				<el-form-item label="活动时间" prop="times" :label-width="formLabelWidth">
					<el-date-picker
						v-if="!look"
						v-model="addParam.times"
						type="datetimerange"
						value-format="yyyy-MM-dd hh:mm:ss"
						range-separator="至"
						start-placeholder="开始日期"
						end-placeholder="结束日期">
					</el-date-picker>
					<span v-if="!!look">
                        {{`${addParam.startTime}~${addParam.endTime}`}}
                    </span>
				</el-form-item>
				<el-form-item label="活动描述" prop="activityDesc" :label-width="formLabelWidth">
					<el-input type="textarea" v-model="addParam.activityDesc" v-if="!look"></el-input>
					<span v-if="!!look">{{addParam.activityDesc}}</span>
				</el-form-item>
				<el-form-item label="活动状态" prop="state" :label-width="formLabelWidth">
					<el-radio v-model="addParam.state" :label="1" v-if="!look">上线</el-radio>
					<el-radio v-model="addParam.state" :label="0" v-if="!look">下线</el-radio>
					<span v-if="!!look">{{addParam.state ? '上线' : '下线'}}</span>
				</el-form-item>
				<div class="dialog-footer" v-if="!look">
					<el-button @click="dialogFormVisible = false">取 消</el-button>
					<el-button type="primary" @click="handleAdd('addParam')">确 定</el-button>
				</div>
			</el-form>
		</el-dialog>


		<el-dialog title="中奖名单" :visible.sync="prizeParams.show">
			<el-table
				height="400"
				v-loading="prizeParams.loading"
				:data="prizeParams.list || []"
				border
				style="width: 100%">
				<el-table-column
					prop="activityId"
					label="活动id">
				</el-table-column>
				<el-table-column
					prop="awardAmount"
					label="中奖金额">
					<template slot-scope="scope">
						{{(scope.row.awardAmount / 100).toFixed(2)}}
					</template>
				</el-table-column>
				<el-table-column
					label="中奖人">
					<template slot-scope="scope">
						{{scope.row.user.name}}
					</template>
				</el-table-column>
				<el-table-column
					label="中奖人银行卡号">
					<template slot-scope="scope">
						{{`${scope.row.userAccInfo.cardNumber}(${scope.row.userAccInfo.bankName})`}}
					</template>
				</el-table-column>
				<el-table-column
					label="中奖产品">
					<template slot-scope="scope">
						{{`${scope.row.product.productName}(${scope.row.product.outProductId})`}}
					</template>
				</el-table-column>
				<el-table-column
					label="券号">
					<template slot-scope="scope">
						{{ticketCard(scope.row.tickets)}}
					</template>
				</el-table-column>
			</el-table>
		</el-dialog>
	</div>
</template>

<script>
	const url = ''
	export default {
		data() {
			return {
				dialogFormVisible: false,//新增修改弹窗
				addLoading: false,//添加loading
				searchLoading: false,//搜索loading
				prizeParams: {
					loading: false,
					show: false,
					list: null
				},
				look: false,
				id: null,
				formData: null,
				// tableData: null,
				merchantList: [],
				tableData: {
					list: []
				},
				searchParam: {
					pageSize: 20,
					pageIndex: 1,
				},
				winnersList: null,
				addParam: {
					activityName: null,
					state: 1,
					activityDesc: null,
					times: [],
				},
				rules: {
					activityName: [
						{required: true, message: '请输入活动名称', trigger: 'change'}
					],
					activityDesc: [
						{required: true, message: '请输入活动描述', trigger: 'change'}
					],
					state: [
						{required: true, message: '请选择活动状态', trigger: 'change'}
					],
					merchantId: [
						{required: true, message: '请选择商家', trigger: 'change'}
					],
					times: [
						{required: true, message: '请选择活动时间', trigger: 'change'}
					],
				},
				formLabelWidth: '100px'
			}
		},
		methods: {
			initData(){
				this.addParam = {
					activityName: null,
					state: 1,
					activityDesc: null,
					times: [],
				}
			},
			getMerchantList() {//搜索
				this.$axios.post("/mis/merchant/info/list", {pageIndex: 1,pageSize: 200}).then((res) => {
					this.merchantList = res.data.list;
				})
			},
			onSearch(start) {//搜索
				this.searchLoading = true;
				this.searchParam.pageIndex = start || 1
				//console.log('搜索条件',this.searchParam);
				this.$axios.post("/mis/activity/info/list", this.searchParam).then((res) => {
					this.searchLoading = false;
					this.tableData = res.data;
				})
			},
			handleAdd(formName) {//新增
				this.$refs[formName].validate((valid) => {
					if (valid) {
						const {
							activityDesc,
							activityName,
							merchantId,
							state,
							times
						} = this.addParam
						const params = {
							activityDesc,
							activityName,
							merchantId,
							state,
							startTime: times[0],
							endTime: times[1],
						}
						this.addLoading = true;
						this.$axios.post("/mis/activity/create_modify", params).then((res) => {
							this.dialogFormVisible = false
							this.addLoading = false;
							this.$message({
								message: this.addParam.activityId ? '修改成功' : '创建成功',
								type: 'success'
							});
							this.initData();
							this.onSearch();
						}).catch((error) => {
							this.addLoading = false
							this.$message.error(error.response.data.message);
						})
					} else {
						return false;
					}
				});
			},
			handleDownLoad() {
				window.location = url + '/mis/activity/winners/download/excel'
			},
			ticketCard(list) {
				let card = ''
				list.forEach(item => {
					card += item.ticketCode + ','
				})
				return card
			},
			handleUpdata(item, look) {//修改
				console.log(item);
				this.initData();
				this.dialogFormVisible = true
				this.look = look
				if (item) {
					let {
						activityId
					} = item
					this.addLoading = true;
					this.$axios.get('/mis/activity/info', {params: {activityId}}).then((res) => {
						this.addLoading = false;
						if (!look) {
							res.data.times = [res.data.startTime, res.data.endTime]
						}
						this.addParam = res.data
					})
				}
			},
			lookPeople(item) {
				let {
					activityId
				} = item
				this.prizeParams.show = true
				this.prizeParams.loading = true
				this.$axios.get('/mis/activity/winners/info', {params: {activityId}}).then((res) => {
					this.prizeParams.loading = false
					this.prizeParams.list = res.data
				})
			},
			update(e, item) {
				console.log(e, item);
				this.searchLoading = true;
				this.file = e.target.files[0];
				let formData = new FormData();
				formData.append('file', this.file)
				this.$axios.post('/mis/activity/winners/upload/xlsx?activityId=' + item.activityId, formData,).then(res => {
					this.searchLoading = false;
					if (res.retCode) {
						this.$message.success('上传成功');
					}
				}).catch((err) => {
					this.$message.error(err.retMsg);
				})
			},
		},
		created() {
			this.getMerchantList()
			this.onSearch()
		}
	}
</script>

<style scoped>
	.handle-box {
		margin-bottom: 20px;
	}

	.handle-select {
		width: 120px;
	}

	.handle-input {
		width: 300px;
		display: inline-block;
	}
</style>
