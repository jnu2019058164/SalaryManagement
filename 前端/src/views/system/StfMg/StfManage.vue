<template>
  <el-main>
    <el-form :model="CompForm" size="mini" label-width="80px">
      <el-row>
        <el-col :span="5">
          <el-form-item label="名称">
            <el-input v-model="CompForm.stfname" placeholder="请输入业主名称"></el-input>
          </el-form-item>
        </el-col>
        <el-button class="searchBtn" type="primary" size="mini" icon="el-icon-search" @click="searchBtn">查询</el-button>
        <el-button class="newBtn" type="primary" size="mini" icon=" el-icon-plus" @click="addCompShow">新增</el-button>
        <el-button class="Refresh" type="primary" size="mini" @click="getCompList">刷新</el-button>
      </el-row>
    </el-form>
    <el-table :data="tableData" :height="tableHeight" border style="width: 100%">
      <el-table-column prop="stfid" label="员工编号"></el-table-column>
      <el-table-column prop="stfname" label="员工姓名"></el-table-column>
      <el-table-column prop="entrydate" :formatter="dateFormat" label="入职日期"></el-table-column>
      <el-table-column label="操作" width="160" align="center">
        <template slot-scope="scope">
          <el-button @click.native.prevent="getDetailsShow(scope.row)" type="primary" size="mini">详情
          </el-button>
          <el-button @click.native.prevent="deleteRow(scope.row)" type="danger" size="mini">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination 
      @size-change="handleSizeChange" @current-change="handleCurrentChange"
      :current-page.sync="currentPage" :page-size="pageSize" layout="total, prev, pager, next"
      :total="total">
    </el-pagination>

    <!-- 新增对话框-->
    <el-dialog title="添加员工" :visible.sync="AddDialogVisible" width="32%" center>
      <!-- 对话框内容-->
      <el-form :rules="rules" ref="addComp" :model="addCompForm" label-width="100px">
        <el-form-item prop="stfname" label="员工姓名">
          <el-input v-model="addCompForm.stfname" placeholder="员工姓名"></el-input>
        </el-form-item>
      </el-form>
      <!-- 对话框底部-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="AddDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmBtn">确 定</el-button>
      </span>
    </el-dialog>


    <!-- 投诉信息对话框-->
    <el-dialog title="投诉信息" :visible.sync="DetailDialogVisible" width="32%" center>
      <!-- 对话框内容-->
      <el-form :model="addCompForm" label-width="100px">
        <el-form-item prop="stfname" label="员工姓名">
          {{RowEO}}
        </el-form-item>
      </el-form>
      <!-- 对话框底部-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="DetailDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="DetailConfirm">已 看</el-button>
      </span>
    </el-dialog>

  </el-main>
</template>

<script>
  import moment from 'moment'
  export default {
    data() {
      return {
        //每页显示的条数
        pageSize: 5,
        //当前页
        currentPage: 1,
        //添加弹窗显示变量
        AddDialogVisible: false,
        //详情弹窗显示变量
        DetailDialogVisible: false,
        //总记录数
        total:0,
        //当前行数据
        RowEO: "",
        RowD: "",

        //搜索框数据绑定
        CompForm: {
          stfname: "",
        },
        //弹出窗校验
        rules: {
          estateowner: [
            {
              required: true,
              trigger: "change",
              message: "请输入员工名字"
            }
          ],
          details: [
            {
              required: false,
              trigger: "change",
              message: "请输入投诉内容"
            }
          ]

        },

        //添加数据绑定
        addCompForm: {
          stfid: "",
          stfname: "",
          entrydate: "",
        },

        //设置表的高度
        tableHeight: window.innerHeight,
        //表格的数据
        tableData: [{
          stfid: 1,
          stfname: "测试用员工",
          entrydate: "2021-11-22",
        }]
      }
    },
    methods: {

      // 日期格式化输出
      dateFormat: function (data) {
        return moment(data.entrydate).format("YYYY-MM-DD");
      },

      //获取投诉信息
      async getCompList() {
        let parm = {
          currentPage: this.currentPage,
          pageSize: this.pageSize,
          title: ""
        }
        console.log(parm);
        let { data: res } = await this.$http.post("/j2ee/stf/SearchStf", parm);
        //  console.log("*********获得部门*"+res.code);
        if (res.code == 100) {
          this.tableData = res.data.records;
          this.currentPage = res.data.current;
          this.pageSize = res.data.size;
          this.total = res.data.total;
        } else {
          this.$message({
            type: 'error',
            message: res.msg
          })
        }
      },

      // 详情弹窗提示
      getDetailsShow(row) {
        this.editTag = '0';
          this.RowEO = row.stfid;
          this.RowD = row.stfname;
        // console.log(row);
        // this.resetForm("getDetails");
        this.dialogTitle = "投诉信息";
        this.DetailDialogVisible = true;
      },

      //添加弹窗显示
      addCompShow() {
        this.resetForm("addComp");
        this.AddDialogVisible = true;
      },


      //清空表单数据
      resetForm(formName) {
        if (this.$refs[formName]) {
          this.$refs[formName].resetFields();
        }
      },


      //添加数据确定
      async confirmBtn() {
        let _this = this;
        _this.$refs.addComp.validate(async valid => {
          if (valid) {
            //成功后，添加该员工信息
              let param = {
                stfname: _this.addCompForm.stfname,
              }
              console.log(param);
              let { data: res2 } = await _this.$http.post("/j2ee/stf/StfAdd", param);
              console.log(param);
              if (res2.code == 100) {
                this.$message({
                  type: 'success',
                  message: res2.msg
                });
                //刷新数据
                _this.getCompList();
                _this.AddDialogVisible = false;
              }
              else {
                this.$message({
                  type: 'error',
                  message: res2.msg
                });
              }

          }
        });
      },

      //模糊搜索投诉信息
      async SearchCompList() {
        let parm = {
          currentPage: this.currentPage,
          pageSize: this.pageSize,
          title: this.CompForm.stfname
        }
        let { data: res } = await this.$http.post("/j2ee/stf/SearchStf", parm);
        if (res.code == 100) {
          this.tableData = res.data.records;
          this.currentPage = res.data.current;
          this.pageSize = res.data.size;
          this.total = res.data.total;
        } else {
          this.$message({
            type: 'error',
            message: res.msg
          })
        }
      },

      //模糊查询
      searchBtn(res) {
        //查询前给当前页赋为1
        this.currentPage = 1;
         this.SearchCompList(res);
      },

      //详情确定
      DetailConfirm() {
        let _this = this;
        _this.DetailDialogVisible = false;
      },

      // //删除
      // deleteRow(row) {
      //   let _this = this;
      //   this.del(row.complaintid);
      // },

      //单个删除
      deleteRow(row) {
        let _this = this;
        _this.$confirm("确定删除吗？", "系统提示", {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          let parm = {
            stfid: row.stfid,
          };
          let { data: res } = await _this.$http.post("/j2ee/stf/StfDel", parm);
          if (res.code == 100) {
            //信息提示
            _this.$message({
              message: res.msg,
              type: "success",
            });

          } else {
            //信息提示
            _this.$message({
              message: res.msg,
              type: "error",
            });
          }
          //刷新数据
          this.getCompList();
        })
      },

      // //测试用删除方法
      // del(item) {
      //   for (let i = 0; i < this.tableData.length; i++) {
      //     if (this.tableData[i].complaintid == item) {
      //       this.tableData.splice(i);
      //       console.log("删除：" + i);
      //     }
      //   }
      // },


      //分页相关方法
      handleSizeChange(val) {
        this.pageSize=val;
        this.getCompList();  
      },
      handleCurrentChange(val) {
        this.currentPage = val;
         this.getCompList();
      }
    },

    //钩子函数
    created() {
      this.getCompList();
    },

    mounted() {
      this.$nextTick(() => {
        this.tableHeight = window.innerHeight - 350; //后面的50：根据需求空出的高度，自行调整
      });
    },
  }
</script>

<style lang="scss" scoped>
  .searchBtn {
    margin-left: 30px;
  }

  .el-main {
    padding-top: 5px !important;
  }
</style>