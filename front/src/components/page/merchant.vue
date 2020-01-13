<template>
	<div>
		<el-form :inline="true" :model="searchParam" class="demo-form-inline">
			<el-form-item style="display: block;float: left">
				<el-button type="primary" icon="el-icon-plus" @click="handleUpdata()">新建</el-button>
			</el-form-item>
		</el-form>
		<el-table
			v-loading="searchLoading"
			:data="tableData && tableData.list || []"
			border
			style="width: 100%">
			<el-table-column
				label="商户id"
				prop="merchantId"
				width="100"
				align="center">
			</el-table-column>
			<el-table-column
				prop="merchantName"
				label="商家名称">
			</el-table-column>
			<el-table-column
				prop="bossName"
				label="店主名称">
			</el-table-column>
			<el-table-column
				prop="bossTel"
				label="店主电话">
			</el-table-column>
			<el-table-column
				prop="name"
				label="法人姓名">
			</el-table-column>
			<el-table-column
				prop="mobile"
				label="法人手机号">
			</el-table-column>
			<el-table-column
				prop="socialCode"
				label="社会统一代码">
			</el-table-column>
			<el-table-column
				prop="createTime"
				label="创建时间">
			</el-table-column>
			<el-table-column
				min-width="100"
				label="操作"
				align="center">
				<template slot-scope="scope">
					<!--<el-button type="text" @click="handleUpdata(scope.row)" size="small">修改</el-button>-->
					<el-button type="text" @click="handleUpdata(scope.row, true)" size="small">查看</el-button>
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
				<el-form-item label="商家名称" prop="merchantName" :label-width="formLabelWidth">
					<el-input v-model="addParam.merchantName" v-if="!look"></el-input>
					<span v-if="!!look">{{addParam.merchantName}}</span>
				</el-form-item>
				<el-form-item label="店主名称" prop="bossName" :label-width="formLabelWidth">
					<el-input v-model="addParam.bossName" v-if="!look"></el-input>
					<span v-if="!!look">{{addParam.bossName}}</span>
				</el-form-item>
				<el-form-item label="店主电话" prop="bossTel" :label-width="formLabelWidth">
					<el-input v-model="addParam.bossTel" v-if="!look"></el-input>
					<span v-if="!!look">{{addParam.bossTel}}</span>
				</el-form-item>
				<el-form-item label="法人手机号" prop="mobile" :label-width="formLabelWidth">
					<el-input v-model="addParam.mobile" max-length="11" v-if="!look"></el-input>
					<span v-if="!!look">{{addParam.mobile}}</span>
				</el-form-item>
				<el-form-item label="法人姓名" prop="name" :label-width="formLabelWidth">
					<el-input v-model="addParam.name" v-if="!look"></el-input>
					<span v-if="!!look">{{addParam.name}}</span>
				</el-form-item>
				<el-form-item label="余额(元)" prop="balance" :label-width="formLabelWidth">
					<el-input v-model="addParam.balance" v-if="!look"></el-input>
					<span v-if="!!look">{{addParam.balance}}</span>
				</el-form-item>
				<el-form-item label="开户行" prop="merchantBank" :label-width="formLabelWidth">
					<el-input v-model="addParam.merchantBank" v-if="!look"></el-input>
					<span v-if="!!look">{{addParam.merchantBank}}</span>
				</el-form-item>
				<el-form-item label="银行卡号" prop="merchantCard" :label-width="formLabelWidth">
					<el-input v-model="addParam.merchantCard" v-if="!look"></el-input>
					<span v-if="!!look">{{addParam.merchantCard}}</span>
				</el-form-item>
				<el-form-item label="社会统一代码" prop="socialCode" :label-width="formLabelWidth">
					<el-input v-model="addParam.socialCode" v-if="!look"></el-input>
					<span v-if="!!look">{{addParam.socialCode}}</span>
				</el-form-item>
				<el-form-item label="商家地址" prop="address" :label-width="formLabelWidth">
					<el-input v-model="addParam.address" v-if="!look"></el-input>
					<span v-if="!!look">{{addParam.address}}</span>
				</el-form-item>
				<div class="dialog-footer" v-if="!look">
					<el-button @click="dialogFormVisible = false">取 消</el-button>
					<el-button type="primary" @click="handleAdd('addParam')">确 定</el-button>
				</div>
			</el-form>
		</el-dialog>
	</div>
</template>

<script>
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
				tableData: {
					list: []
				},
				searchParam: {
					pageSize: 20,
					pageIndex: 1,
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
					address: null,
				},
				rules: {
					activityName: [
						{required: true, message: '请输入活动名称', trigger: 'change'}
					],
					address: [
						{required: true, message: '请输入商家地址', trigger: 'change'}
					],
					bossName: [
						{required: true, message: '请输入店主名称', trigger: 'change'}
					],
					bossTel: [
						{required: true, message: '请输入店主电话', trigger: 'change'}
					],
					merchantBank: [
						{required: true, message: '请输入开户行', trigger: 'change'}
					],
					merchantCard: [
						{required: true, message: '请输入银行卡号', trigger: 'change'}
					],
					merchantName: [
						{required: true, message: '请输入商家名称', trigger: 'change'}
					],
					mobile: [
						{required: true, message: '请输入法人手机号', trigger: 'change'}
					],
					name: [
						{required: true, message: '请输入法人姓名', trigger: 'change'}
					],
					socialCode: [
						{required: true, message: '请输入社会统一代码', trigger: 'change'}
					],
					balance: [
						{required: true, message: '请输入余额', trigger: 'change'}
					],
				},
				formLabelWidth: '120px'
			}
		},
		methods: {
			initData(){
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
						address: null,
				}
			},
			onSearch(start) {//搜索
				this.searchLoading = true;
				this.searchParam.pageIndex = start || 1
				//console.log('搜索条件',this.searchParam);
				this.$axios.post("/mis/merchant/info/list", this.searchParam).then((res) => {
					this.searchLoading = false;
					this.tableData = res.data;
				})
			},
			handleAdd(formName) {//新增
				this.$refs[formName].validate((valid) => {
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
							address,
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
							balance: (balance * 100)
						}
						this.addLoading = true;
						this.$axios.post("/mis/merchant/create_modify", params).then((res) => {
							this.dialogFormVisible = false
							this.addLoading = false;
							this.$message({
								message: this.addParam.merchantId ? '修改成功' : '创建成功',
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
			ticketCard(list) {
				let card = ''
				list.forEach(item => {
					card += item.ticketCode + ','
				})
				return card
			},
			handleUpdata(item, look) {//修改
				this.initData();
				this.dialogFormVisible = true
				this.look = look
				if (item) {
					let {
						merchantId
					} = item
					this.addLoading = true;
					this.$axios.get('/mis/merchant/info', {params: {merchantId}}).then((res) => {
						this.addLoading = false;
						res.data.balance = (res.data.merchantAccountResponse.balance / 100).toFixed(2)
						res.data.merchantBank = res.data.merchantAccInfoResponse.merchantBank
						res.data.merchantCard = res.data.merchantAccInfoResponse.merchantCard
						this.addParam = res.data
					})
				}
			},
		},
		created() {
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
