<template>
  <el-main>
    <el-form :model="feeForm" size="mini" label-width="80px">
      <el-row>
        <el-col :span="5">
          <el-form-item label="住户名称">
            <el-input
              v-model="feeForm.estateowner"
              placeholder="请输入住户名称"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-button
          class="searchBtn"
          type="primary"
          size="mini"
          icon="el-icon-search"
          @click="searchBtn"
          >查询</el-button
        >
        <el-button
          class="newBtn"
          type="primary"
          size="mini"
          icon=" el-icon-plus"
          @click="addFeeRecordShow"
          >新增</el-button
        >
        <el-button
          type="danger"
          size="mini"
          icon="el-icon-delete"
          @click="deleteAllRecord"
          >删除</el-button
        >
      </el-row>
    </el-form>
    <el-table
      :data="tableData"
      :height="tableHeight"
      @selection-change="handleSelectionChange"
      border
      style="width: 100%"
    >
      <!--添加该选项 -->
      <el-table-column type="selection" width="60"></el-table-column>
      <el-table-column prop="payid" label="序号"></el-table-column>
      <el-table-column prop="estateowner" label="住户名称"></el-table-column>
      <el-table-column prop="address" label="住户地址"></el-table-column>
      <el-table-column prop="feeshouldpay" label="应缴费用"></el-table-column>
      <el-table-column prop="paydetail" label="备注"></el-table-column>
      <el-table-column label="操作" width="160" align="center">
        <template slot-scope="scope">
          <el-button
            @click.native.prevent="editRow(scope.row)"
            type="primary"
            size="mini"
            >编辑
          </el-button>
          <el-button
            @click.native.prevent="deleteRow(scope.row)"
            type="danger"
            size="mini"
            >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync="currentPage"
      :page-size="pageSize"
      layout="total, prev, pager, next"
      :total="total"
    >
    </el-pagination>
    <!-- 新增对话框-->
    <el-dialog
      :title="dialogTitle"
      :visible.sync="dialogVisible"
      width="32%"
      center
    >
      <!-- 对话框内容-->
      <el-form
        :rules="rules"
        ref="addFeeRecord"
        :model="addFeeForm"
        label-width="100px"
      >
        <el-form-item prop="householdid" label="住户名称">
          <el-select v-model="addFeeForm.householdid" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.householdid"
              :label="item.estateowner"
              :value="item.householdid"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="feeshouldpay" label="应缴费用">
          <el-input
            v-model="addFeeForm.feeshouldpay"
            placeholder="0"
          ></el-input>
        </el-form-item>
        <el-form-item prop="paydetail" label="备注">
          <el-input
            v-model="addFeeForm.paydetail"
            placeholder="备注"
          ></el-input>
        </el-form-item>
      </el-form>
      <!-- 对话框底部-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmBtn">确 定</el-button>
      </span>
    </el-dialog>
  </el-main>
</template>

<script>
export default {
  created() {
    //调用获得缴费记录函数
    this.getFeeRecordList();
  },
  data() {
    return {
      //批量删除选中id
      selectionList: [],
      //每页显示的条数
      pageSize: 5,
      //当前页
      currentPage: 1,
      //总记录数
      total: 0,
      //弹窗显示变量
      dialogVisible: false,
      //弹窗标题
      dialogTitle: "",
      //编辑标识
      editTag: 0,
      //搜索框数据绑定
      feeForm: {
        estateowner: "",
      },
      //弹出窗校验
      rules: {
        householdid: [
          {
            required: true,
            trigger: "change",
            message: "请选择住户名称",
          },
        ],
        feeshouldpay: [
          {
            validator: checkMinVal,
            trigger: "change",
          },
        ],
      },
      //添加数据绑定
      addFeeForm: {
        payid: "",
        householdid: "",
        feeshouldpay: "",
        paydetail: "",
      },
      //设置表的高度
      tableHeight: window.innerHeight,
      //表格的数据
      tableData: [],
      options: [],
      value: "",
    };
  },
  methods: {
    //批量选择时触发
    handleSelectionChange(selection) {
      (this.selectionList = []),
        selection.forEach((element) => {
          this.selectionList.push(element.payid);
        });
      //console.log("批量选择" + this.selectionList);
    },

    //获得缴费记录列表
    async getFeeRecordList() {
      let parm = {
        currentPage: this.currentPage,
        pageSize: this.pageSize,
        hName: this.feeForm.estateowner,
      };
      let { data: res } = await this.$http.post("/jnu/pay/getFeeRecordList",parm);
      if (res.code == 100) {
        this.tableData = res.data.records;
        this.currentPage = res.data.current;
        this.pageSize = res.data.size;
        this.total = res.data.total;
      } else {
        this.$message({
          type: "error",
          message: res.msg,
        });
      }
    },
    //模糊查询
    searchBtn() {
      //查询前给当前页赋为1
      this.currentPage = 1;
      this.getFeeRecordList();
    },
    //新增弹窗显示
    addFeeRecordShow() {
      this.editTag = "0";
      //调用对话框表单清空方法
      this.restDialogForm("addFeeRecord");
      this.options=[];
      //设置对话框标题
      this.dialogTitle = "添加缴费信息";
      this.getHouseholdName();
      this.dialogVisible = true;
    },
    async getHouseholdName(){
      let { data: res } = await this.$http.get("/jnu/pay/getHouseholdName");
      this.options = res.data;
    },
    //清空表单数据
    restDialogForm(formName) {
      if (this.$refs[formName]) {
        this.$refs[formName].resetFields();
      }
    },
    //添加数据确定
    async confirmBtn() {
      let _this = this;
      _this.$refs.addFeeRecord.validate(async (valid) => {
        if (valid) {
          let url = "";
          if (this.editTag == "0") {
            url = "/jnu/pay/addFeeRecord";
          } else {
            url = "/jnu/pay/updateFeeRecord";
          }
          let { data: res } = await _this.$http.post(url, _this.addFeeForm);
          //console.log("*部门添加*********" + res.code);
          if (res.code == 100) {
            this.$message({
              type: "success",
              message: res.msg,
            });
            //刷新数据
            _this.getFeeRecordList();
            //关闭窗口
            _this.dialogVisible = false;
          } else {
            this.$message({
              type: "error",
              message: res.msg,
            });
          }
        }
      });
    //console.log(this.addFeeForm);
    },
    //编辑
    editRow(row) {
      // console.log("****编辑*****"+row.adminName);
      this.editTag = "1";      
      this.options=[];
      this.getHouseholdName();
      this.addFeeForm.payid=row.payid;
      this.addFeeForm.feeshouldpay = row.feeshouldpay;
      this.addFeeForm.paydetail = row.paydetail;
      //设置对话框标题
      this.dialogTitle = "编辑缴费信息";
      this.dialogVisible = true;
      //根据id查询部门
      //this.getFeeRecordById(row.payid);
    },
    //删除代码
    deleteFeeRecord(id) {
      let _this = this;
      _this
        .$confirm("确定删除吗？", "系统提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(async () => {
          let parm = {
            payid: id,
          };
          let { data: res } = await _this.$http.post("/jnu/pay/deleteFeeRecord",parm);
          if (res.code == 100) {
            //信息提示
            _this.$message({
              message: res.msg,
              type: "success",
            });
            //刷新数据
            _this.getFeeRecordList();
          } else {
            //信息提示
            _this.$message({
              message: res.msg,
              type: "error",
            });
          }
        });
    },
    //单个删除
    deleteRow(row) {
      this.deleteFeeRecord(row.payid);
    },
    //批量删除
    deleteAllRecord() {
      let _this = this;
      _this.$confirm("确定删除吗？", "系统提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(async () => {
          let parm = {
            ids: this.selectionList,
          };
          let { data: res } = await _this.$http.post("/jnu/pay/deleteFeeRecords",parm);
          if (res.code == 100) {
            //信息提示
            _this.$message({
              message: res.msg,
              type: "success",
            });
            //刷新数据
            _this.getFeeRecordList();
          } else {
            //信息提示
            _this.$message({
              message: res.msg,
              type: "error",
            });
          }
        });
    },
    //分页相关方法
    handleSizeChange(val) {
      //改变页容量时调用
      // console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.getFeeRecordList();
    },
    handleCurrentChange(val) {
      // console.log(`当前页: ${val}`);
      //改变当前页时调用
      this.currentPage = val;
      this.getFeeRecordList();
    },
  },
  mounted() {
    this.$nextTick(() => {
      this.tableHeight = window.innerHeight - 300; //后面的50：根据需求空出的高度，自行调整
    });
  },
};
export function checkMinVal(rule, value, callback) {
  if (value < 0) {
    callback(new Error("输入值不能小于0"));
  } else {
    callback();
  }
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