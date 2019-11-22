<template>
    <div>
        <el-form :inline="true" :model="searchParam" class="demo-form-inline">
            <el-form-item>
                <el-select v-model="searchParam.type" placeholder="类型">
                    <el-option label="平面主KV" :value="1"></el-option>
                    <el-option label="3D效果图" :value="2"></el-option>
                    <el-option label="Event视频" :value="3"></el-option>
                    <el-option label="BGM" :value="4"></el-option>
                    <el-option label="场地CAD" :value="5"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="onSearch()">搜索</el-button>
            </el-form-item>
        </el-form>
        <el-table
            v-loading="searchLoading"
            :data="tableData && tableData.list || []"
            border
            style="width: 100%">
            <el-table-column
                prop="opusName"
                label="名称">
            </el-table-column>
            <el-table-column
                prop="permitImg"
                width="300"
                align="center"
                label="图片">
                <template slot-scope="scope">
                    <img :src="scope.row.imageUrl"/>
                </template>
            </el-table-column>
            <el-table-column
                width="100"
                align="center"
                label="操作">
                <template slot-scope="scope">
                    <span :class="{aPush: !scope.row.push }" @click="pushIndex(scope.row)">取消推荐</span>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination" v-if="tableData && tableData.totalCount">
            <el-pagination
                @current-change="onSearch"
                layout="prev, pager, next"
                :page-size="10"
                :total="tableData.totalCount || 0">
            </el-pagination>
        </div>

    </div>
</template>

<script>
    export default {
        data() {
            return {
                dialogFormVisible: false,//新增修改弹窗
                addLoading: false,//添加loading
                searchLoading: false,//搜索loading
                formData: null,
                tableData: null,
                file: null,
                list,
                searchParam: {
                    type: 1
                },
                addParam: {},
                baseList: {
                    format: [],
                    used: []
                },
                freeList:['','免费内容','会员内容','付费内容']
            }
        },
        methods: {
            warrantFilter(id) { //授权模式
                return (this.list.warrantList.filter(item => item.val == id)[0] || {html: '无'})['html']
            },
            formatFilter(id) { //格式
                return (this.baseList.format.filter(item => item.id == id)[0] || {name: '无'})['name']
            },
            usedFilter(id) { //类别
                return (this.baseList.used.filter(item => item.id == id)[0] || {name: '无'})['name']
            },
            colorFilter(id) { // 色系
                if (id) {
                    return this.colorList.filter(item => item.id === id)[0].name
                } else {
                    return '无'
                }
            },
            onSearch(start) {//搜索
                this.searchLoading = true;
                this.searchParam.currentPage = start
                //console.log('搜索条件',this.searchParam);
                if (!this.searchParam.status) {
                    delete this.searchParam.status
                }
                this.$axios.post("/api/firstPage/getFirstPagePushListByPage", this.searchParam).then((res) => {
                    this.searchLoading = false;
                    this.tableData = res.data;
                })
            },
            pushIndex(obj) { //推荐到首页
                let { id , type } = obj
                this.$axios.post("/api/firstPage/deleteFirstPagePushById", { id, type}).then((res) => {
                    if (res.code === 200) {
                        this.$message({
                            message: '取消成功',
                            type: 'success'
                        });
                    }
                })
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
