<template>
    <div>
        <el-form :inline="true" :model="searchParam" class="demo-form-inline">
            <el-form-item style="display: block;">
                <el-button type="primary" icon="el-icon-plus" @click="handleEdit()">新建</el-button>
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
                prop="used"
                label="类别">
            </el-table-column>
            <el-table-column
                prop="format"
                label="格式">
            </el-table-column>
            <el-table-column
                prop="color"
                label="色系">
                <template slot-scope="scope">
                    {{colorFilter(scope.row.color)}}
                </template>
            </el-table-column>
            <el-table-column
                prop="dpi"
                label="分辨率(dpi)">
            </el-table-column>
            <el-table-column
                prop="keyword"
                label="关键字">
            </el-table-column>
            <el-table-column
                prop="currentPrice"
                label="当前价格(单位元)">
            </el-table-column>
            <el-table-column
                prop="oldPrice"
                label="原价格(单位元)">
            </el-table-column>
            <el-table-column
                prop="isBan"
                label="是否禁用">
                <template slot-scope="scope">
                    {{scope.row.isBan ? '是' : '否'}}
                </template>
            </el-table-column>
            <el-table-column
                prop="isFree"
                label="是否免费">
                <template slot-scope="scope">
                    {{freeList[scope.row.isFree]}}
                </template>
            </el-table-column>
            <el-table-column
                prop="warrant"
                label="授权模式">
                <template slot-scope="scope">
                    {{
                    warrantFilter(scope.row.warrant)
                    }}
                </template>
            </el-table-column>
            <el-table-column
                prop="push"
                label="是否推荐首页">
                <template slot-scope="scope">
                    <span :class="{aPush: !scope.row.push }" @click="pushIndex(scope.row)">{{scope.row.push ? '是' : '推荐'}}</span>
                </template>
            </el-table-column>
            <el-table-column
                prop="createTime"
                label="创建时间">
            </el-table-column>
            <el-table-column
                min-width="130"
                label="操作"
                align="center">
                <template slot-scope="scope">
                    <el-button type="text" @click="deleteS(scope.row)" size="small">删除</el-button>
                    <el-button type="text" @click="handleEdit(scope.row.id)" size="small">修改</el-button>
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

        <el-dialog title="平面" :visible.sync="dialogFormVisible">
            <div v-if="uploadParam.showProgress"  style="position: absolute;left:0;top: 0;display: flex;bottom:0;right:0; justify-content: center;align-items: center;z-index: 1;background:rgba(255,255,255,0.9)">
                <el-progress type="circle" :percentage="uploadParam.startValue"></el-progress>
            </div>
            <el-form :model="addParam" :rules="rules" ref="addParam" v-loading="addLoading">
                <el-form-item label="图片" prop="opusName" :label-width="formLabelWidth">
                    <label class="adFile">
                        上传图片
                        <input class="file" name="file" type="file" accept="image/png,image/gif,image/jpeg"
                               @change="update"/>
                    </label>
                    <div class="bannerImg">
                        <span v-for="item in files">{{item.name}} <i @click="deleteImg(item.name)">×</i></span>
                    </div>
                </el-form-item>
                <el-form-item label="名称" prop="opusName" :label-width="formLabelWidth">
                    <el-input v-model="addParam.opusName"></el-input>
                </el-form-item>
                <el-form-item label="关键字" prop="keyword" :label-width="formLabelWidth">
                    <el-input v-model="addParam.keyword"></el-input>
                </el-form-item>
                <el-form-item label="类别" prop="used" :label-width="formLabelWidth">
                    <el-select v-model="addParam.used">
                        <el-option v-for="item in baseList.used" :key="item.id" :label="item.name"
                                   :value="item.name"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="格式" prop="format" :label-width="formLabelWidth">
                    <el-select v-model="addParam.format">
                        <el-option v-for="item in baseList.format" :key="item.id" :label="item.name"
                                   :value="item.name"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="像素" prop="pixel" :label-width="formLabelWidth">
                    <el-input v-model="addParam.pixel"></el-input>
                </el-form-item>
                <el-form-item label="分辨率" prop="dpi" :label-width="formLabelWidth">
                    <el-input v-model="addParam.dpi" style="width: 80px;margin-right:10px;"></el-input>dpi
                </el-form-item>
                <el-form-item label="版权所有" prop="copyright" :label-width="formLabelWidth">
                    <el-input v-model="addParam.copyright"></el-input>
                </el-form-item>
                <el-form-item label="是否禁用" :label-width="formLabelWidth">
                    <el-radio v-model="addParam.isBan" :label="0">否</el-radio>
                    <el-radio v-model="addParam.isBan" :label="1">是</el-radio>
                </el-form-item>
                <el-form-item label="是否免费" :label-width="formLabelWidth">
                    <el-radio v-model="addParam.isFree" :label="1">免费内容</el-radio>
                    <el-radio v-model="addParam.isFree" :label="2">会员内容</el-radio>
                    <el-radio v-model="addParam.isFree" :label="3">付费内容</el-radio>
                </el-form-item>
                <el-form-item label="当前价格" prop="currentPrice" :label-width="formLabelWidth" v-if="addParam.isFree === 3">
                    <el-input type="number" style="width: 100px;" v-model="addParam.currentPrice"></el-input>
                    元
                </el-form-item>
                <el-form-item label="原价格" prop="oldPrice" :label-width="formLabelWidth" v-if="addParam.isFree === 3">
                    <el-input type="number" style="width: 100px;" v-model="addParam.oldPrice"></el-input>
                    元
                </el-form-item>
                <el-form-item label="色系" :label-width="formLabelWidth">
                    <el-select v-model="addParam.color">
                        <el-option v-for="item in list.colorList" :key="item.id" :label="item.name"
                                   :value="item.id">
                            <span :style="{float: 'left'}">{{ item.name }}</span>
                            <span :style="{float: 'right', backgroundColor: item.color}"></span>
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="授权模式" :label-width="formLabelWidth">
                    <el-select v-model="addParam.warrant">
                        <el-option v-for="item in list.warrantList" :key="item.val" :label="item.html"
                                   :value="item.val"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="文件包" prop="opusName" :label-width="formLabelWidth">
                    <label class="adFile">
                        上传文件
                        <input class="file" name="file" type="file" @change="update2"/>
                    </label>
                    <div class="bannerImg">
                        <span>{{filePackages && filePackages.name}}</span>
                    </div>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addInit()">取 消</el-button>
                <el-button type="primary" @click="handleAdd('addParam')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            let currentPriceFun = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入当前价格'));
                } else if (value <= 0) {
                    callback(new Error('请输入正确的价格!'));
                } else {
                    callback();
                }
            };
            let oldPriceFun = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入原价格'));
                } else if (value <= 0) {
                    callback(new Error('请输入正确的价格!'));
                } else {
                    callback();
                }
            };
            return {
                dialogFormVisible: false,//新增修改弹窗
                addLoading: false,//添加loading
                searchLoading: false,//搜索loading
                files: [],
                filePackages: null,
                formData: null,
                tableData: null,
                baseList: {
                    format: [],
                    used: []
                },
                list,
                searchParam: {},
                addParam: {
                    isBan: 0,
                    isFree: 1,
                    warrant: 1,
                    color: 1,
                    copyright: '@会设计',
                },
                rules: {
                    opusName: [{required: true, message: '请输入名称', trigger: 'change'}],
                    keyword: [{required: true, message: '请输入关键字', trigger: 'change'}],
                    pixel: [{required: true, message: '请输入像素（尺寸）', trigger: 'change'}],
                    dpi: [{required: true, message: '请输入分辨率', trigger: 'change'}],
                    copyright: [{required: true, message: '请输入版权所有', trigger: 'change'}],
                    currentPrice: [{required: true, validator: currentPriceFun, trigger: 'change'}],
                    oldPrice: [{required: true, validator: oldPriceFun, trigger: 'change'}],
                },
                form: {},
                formLabelWidth: '100px',
                freeList:['','免费内容','会员内容','付费内容'],
                uploadParam:{
                    showProgress:false,
                    startValue:0,
                }
            }
        },
        methods: {
            deleteImg(name){
                this.files = this.files.filter(item=>item.name !== name)
            },
            pushIndex(obj) { //推荐到首页
                let {id, push} = obj
                if (!push) {
                    this.$axios.post("/api/firstPage/addFirstPagePush", {resourceId: id, type: 1}).then((res) => {
                        if (res.code === 200) {
                            this.$message({
                                message: '推荐成功',
                                type: 'success'
                            });
                            this.onSearch()
                        }
                    })
                }
            },
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
                    return this.list.colorList.filter(item => item.id === id)[0].name
                } else {
                    return '无'
                }
            },
            getBaseDate(pid, used) {
                this.$axios.post("/api/baseData/getBaseDataListByPidUsed", {pid, used}).then((res) => {
                    if (used === 1) {
                        this.baseList.format = res.data;
                    } else if (used === 2) {
                        this.baseList.used = res.data;
                    }
                })
            },
            addInit(type) { // 创建成功后初始化数据
                this.dialogFormVisible = type || false;
                this.files = []
                this.filePackages = null
                this.addParam = {
                    isBan: 0,
                    isFree: 1,
                    warrant: 1,
                    color: 1,
                    copyright: '@会设计',
                    used: this.baseList.used[0].name,
                    format: this.baseList.format[0].name
                };
            },
            onSearch(start) {//搜索
                this.searchLoading = true;
                this.searchParam.currentPage = start
                //console.log('搜索条件',this.searchParam);
                this.$axios.post("/api/design/getDesignListByPage", this.searchParam).then((res) => {
                    this.searchLoading = false;
                    this.tableData = res.data;
                })
            },
            handleAdd(formName) {//新增
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if(!this.files.length){
                            this.$message.error('请上传图片！');
                            return false
                        }

                        if(!this.filePackages){
                            this.$message.error('请上传源文件！');
                            return false
                        }
                        console.log(this.addParam)
                        // this.addLoading = true;
                        this.formData = new FormData()
                        this.files.forEach(file => {
                            this.formData.append('files', file)
                        })
                        // this.formData.append('files', this.files[0])
                        if (this.filePackages) {
                            this.formData.append('filePackage', this.filePackages)
                        }
                        if (this.addParam.id) {
                            this.formData.append('id', this.addParam.id)
                        }
                        this.formData.append('keyword', this.addParam.keyword)
                        this.formData.append('opusName', this.addParam.opusName)
                        this.formData.append('used', this.addParam.used)
                        this.formData.append('color', this.addParam.color)
                        this.formData.append('format', this.addParam.format)
                        this.formData.append('dpi', this.addParam.dpi)
                        this.formData.append('pixel', this.addParam.pixel)
                        this.formData.append('warrant', this.addParam.warrant)
                        this.formData.append('copyright', this.addParam.copyright)
                        this.formData.append('isBan', this.addParam.isBan)
                        this.formData.append('isFree', this.addParam.isFree)
                        if (this.addParam.isFree === 3){
                            this.formData.append('currentPrice', this.addParam.currentPrice)
                            this.formData.append('oldPrice', this.addParam.oldPrice)
                        }
                        this.$axios.post("/api/design/addOrUpdateDesign", this.formData,{onUploadProgress:(progressEvent) => {
                                if (progressEvent.lengthComputable) {
                                    let val = (progressEvent.loaded / progressEvent.total * 50).toFixed(0);
                                    this.uploadParam.showProgress = true;
                                    this.uploadParam.startValue = parseInt(val)
                                }
                            }}).then((res) => {
                            this.addLoading = false;
                            if (res.code === 200) {
                                this.getQueryUploadPercent()
                                /*this.$message({
                                    message: this.addParam.id ? '修改成功' : '创建成功',
                                    type: 'success'
                                });
                                this.addInit();
                                this.onSearch();*/
                            }else{
                                this.uploadParam.showProgress = false;
                            }
                        }).catch((error) => {
                            this.addLoading = false
                            this.uploadParam.showProgress = false;
                            this.$message.error(error.response.data.message);
                        })
                    } else {
                        return false;
                    }
                });
            },
            getQueryUploadPercent(){
                this.$axios.post('/api/queryUploadPercent').then((res) => {
                    console.log(res)
                    if(res.code === 200){
                        this.uploadParam.startValue = parseInt((this.uploadParam.startValue * 1 + res.data.percent/2))
                        if(res.data.end <= 0){
                            setTimeout(()=>{
                                this.clearUpload();
                                this.addInit();
                                this.onSearch();
                                this.uploadParam.showProgress = false;
                                this.addLoading = false;
                            }, 500)
                        }else{
                            setTimeout(()=>{
                                this.getQueryUploadPercent()
                            }, 500)
                        }
                    }
                })
            },
            clearUpload(){
                this.$axios.post('/api/clearUploadPercent').then()
            },
            handleEdit(id) {//修改
                this.addInit(true);
                if (this.$refs['addParam'] !== undefined) {
                    this.$refs['addParam'].resetFields();
                }
                if (id) {
                    this.addLoading = true;
                    this.$axios.post('/api/design/getDesignById', {id}).then((res) => {
                        this.addLoading = false;
                        this.addParam = res.data
                    })
                }
            }, // 删除
            deleteS(obj) {
                this.$axios.post('/api/design/deleteDesign', [obj.id]).then((res) => {
                    if (res.code === 200) {
                        this.$message.success('删除成功')
                        this.onSearch()
                    }
                })
            },
            update(e) {
                this.files.push(e.target.files[0]);
                //this.handleAdd('addParam')
            },
            update2(e) {
                this.filePackages = e.target.files[0]
                //this.handleAdd('addParam')
            },
        },
        created() {
            this.onSearch()
            this.getBaseDate(1, 1)
            this.getBaseDate(1, 2)
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
