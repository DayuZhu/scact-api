<template>
    <div>
        <el-table
            v-loading="searchLoading"
            :data="tableData && tableData.list || []"
            border
            style="width: 100%">
            <el-table-column
                label="id"
                prop="accSepRecordId"
                width="80"
                align="center">
            </el-table-column>
            <el-table-column
                prop="bankName"
                label="入金银行">
            </el-table-column>
            <el-table-column
                prop="cardName"
                label="入金人姓名">
            </el-table-column>
            <el-table-column
                prop="cardNumber"
                label="入金人卡号">
            </el-table-column>
            <el-table-column
                prop="poBankName"
                label="出金银行">
            </el-table-column>
            <el-table-column
                prop="poCardName"
                label="出金人姓名">
            </el-table-column>
            <el-table-column
                prop="poCardNo"
                label="出金人卡号">
            </el-table-column>
            <el-table-column
                prop="payoutAmount"
                label="出金金额">
                <template slot-scope="scope">
                    {{(scope.row.payoutAmount / 100).toFixed(2)}}
                </template>
            </el-table-column>
            <el-table-column
                label="出金状态">
                <template slot-scope="scope">
                    {{states[scope.row.status]}}
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
    </div>
</template>

<script>
    export default {
        data() {
            return {
                id: null,
                searchLoading: false,//搜索loading
                tableData: null,
                searchParam: {
                    pageSize: 20,
                    pageIndex: 1,
                },
                states: ['处理中', '成功', '失败', '未知失败']
            }
        },
        methods: {
            onSearch(start) {//搜索
                this.searchLoading = true;
                this.searchParam.pageIndex = start || 1
                //console.log('搜索条件',this.searchParam);
                this.$axios.post("/mis/acc/sep/record/info/list", this.searchParam).then((res) => {
                    this.searchLoading = false;
                    this.tableData = res.data;
                })
            },
        },
        created() {
            this.onSearch ()
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
