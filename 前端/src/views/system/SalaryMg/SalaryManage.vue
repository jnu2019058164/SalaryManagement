<template>
<el-main>
<el-form :model="repairForm" size="mini" label-width="80px" >
 <el-row>
   <el-col :span="5">
    <el-form-item label="报修细节">
    <el-input v-model="repairForm.details" placeholder="请输入报修细节"></el-input>
    </el-form-item>
   </el-col>
   <!-- <el-col :span="5">
          <el-form-item label="电话">
            <el-input v-model=" empForm.empTel" placeholder="请输入电话号码"></el-input>
          </el-form-item>
    </el-col> -->
     <el-button class="searchBtn" type="primary" size="mini" icon="el-icon-search" @click="searchBtn">查询</el-button>
  <el-button class="newBtn" type="primary" size="mini" icon=" el-icon-plus" @click="addRepairShow">新增</el-button>
  <el-button type="danger" size="mini" icon="el-icon-delete" @click="deleteAllRepair">删除</el-button>

 </el-row>
</el-form>
<el-table :data="tableData" :height="tableHeight"  @selection-change="handleSelectionChange" borderstyle="width: 100%">
<!--添加该选项 -->
 <el-table-column type="selection" width="60"></el-table-column>
<!-- <el-table-column prop="Repairid" label="#"></el-table-column> -->
    <el-table-column prop="repairid" label="报修编号"></el-table-column>
      <el-table-column prop="householdid" label="住户ID"></el-table-column>
      <el-table-column prop="details" label="详细细节"></el-table-column>
      <el-table-column prop="repairfee" label="维修费用"></el-table-column>
      <el-table-column prop="repairdate" label="维修日期"> </el-table-column>
    <el-table-column label="操作" width="160" align="center">
   <template slot-scope="scope">
   <el-button @click.native.prevent="editRow(scope.row)" type="primary" size="mini">编辑
  </el-button>
  <el-button @click.native.prevent="deleteRow(scope.row)" type="danger" size="mini">删除
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
      :total="total">
    </el-pagination>
 <!-- 新增对话框-->
 
<el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="32%" center>
   <!-- 对话框内容-->
   <el-form :rules="rules" ref="addRepair" :model="addRepairForm" label-width="100px">
        <el-form-item prop="repairid" label="报修编号">
          <el-input v-model="addRepairForm.repairid" placeholder="报修编号"></el-input>
        </el-form-item>
        <el-form-item prop="householdid" label="住户ID">
         <el-input v-model="addRepairForm.householdid" placeholder="住户ID"></el-input>
        </el-form-item>
        <el-form-item prop="details" label="详细细节">
         <el-input v-model="addRepairForm.details" placeholder="详细细节"></el-input>
        </el-form-item>
        <el-form-item prop="repairfee" label="维修费用">
         <el-input v-model="addRepairForm.repairfee" placeholder="维修费用"></el-input>
        </el-form-item>
        <el-form-item prop="repairdate" label="维修日期">
         <el-input v-model="addRepairForm.repairdate" placeholder="维修日期"></el-input>
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
    created(){
        //调用获得报修函数
      this.getRepairList();
    },
        data(){
            return{
               //批量删除选中id
                selectionList:[],
                //每页显示的条数
                pageSize:5,
                //当前页
                currentPage:1,
                //总记录数
                total:0,
                //弹窗显示变量
                dialogVisible:false,
                //弹窗标题
                dialogTitle:"",
                //编辑标识
                editTag:0,
                //搜索框数据绑定
                repairForm:{
                    details:"",
                },
                //弹出窗校验
                rules:{
          repairid: [
             {
            required: true,
            trigger: "change",
            message: "请输入报修编号"
            }
           ],
        householdid: [
          {
            required: true,
            trigger: "change",
            message: "请输入住户id"
          }
          ]
                },
                //添加数据绑定
                addRepairForm:{
                repairid:"",
                householdid:"",
                details:"",
                repairfee:"",
                repairdate:""
                },
                //设置表的高度
               tableHeight:window.innerHeight,
                //表格的数据
                tableData: []
            }
        }, 
         methods:{
          //批量选择时触发
      handleSelectionChange(selection){
        this.selectionList = [],
        selection.forEach(element => {
            this.selectionList.push(element.repairid)
        });
         console.log("批量选择"+this.selectionList);
        },

      //获得报修列表
     async getRepairList(){
       let parm={
        currentPage:this.currentPage,
        pageSize:this.pageSize,
        // title:this.repairForm.details
       }
       let{data:res}=await this.$http.post("/jnu/repair/getRepairList",parm);
        console.log("*********获得报修*"+res.code);
       if(res.code==100){
        this.tableData=res.data.records;
        this.currentPage=res.data.current;
        this.pageSize=res.data.size;
        this.total=res.data.total;
       }else{
        this.$message({
            type:'error',
            message:res.msg
          })
       }
      },
      //模糊查询
      searchBtn(){
       //查询前给当前页赋为1
       this.currentPage=1;
       this.getRepairList();
      },
       //新增弹窗显示
      addRepairShow(){
        this.editTag='0';
      //调用对话框表单清空方法
        this.restDialogForm("addRepair");
        //设置对话框标题
       this.dialogTitle="添加报修";
       this.dialogVisible=true;
      },
     //清空表单数据
     restDialogForm(formName){
       if(this.$refs[formName]){
        this.$refs[formName].resetFields();
       }
     },
      //添加数据确定
     async confirmBtn(){
        let _this = this;
      _this.$refs.addRepair.validate(async valid => {
        if (valid) {
           let url="";
           if(this.editTag=='0'){
            url="/jnu/repair/addRepair";
           } else{
             url="/jnu/repair/updateRepair";
           }

          let{data:res}=await _this.$http.post(url,_this.addRepairForm);
          console.log("*报修添加*********"+res.code);
          if(res.code==100){
            this.$message({
            type:'success',
            message:res.msg
          });
          //刷新数据
          _this.getRepairList();
          //关闭窗口
          _this.dialogVisible = false;
          }else{
            this.$message({
            type:'error',
            message:res.msg
          });
          }
        }
      });
      },
      //编辑
      editRow(row){
      // console.log("****编辑*****"+row.details);
      this.editTag = "1";
      //调用对话框表单清空方法
      this.restDialogForm("addRepair");
     //设置对话框标题
      this.dialogTitle = "编辑报修"
       this.dialogVisible=true;
       //根据id查询报修
       this.getRepairById(row.repairid);
      },
     //根据id进行查询
     async getRepairById(id){
  
      let parm={
        repairid:id
       }
       let {data:res} = await this.$http.post( "/jnu/repair/queryRepairById",parm);
          console.log("+++查询+++"+res.data);
          if(res.code==100){
           this.addRepairForm.repairid=res.data.repairid;
            this.addRepairForm.householdid=res.data.householdid;
             this.addRepairForm.details=res.data.details;
             this.addRepairForm.repairfee=res.data.repairfee;
          }else{
              this.$message({
            type:'error',
            message:res.msg});
          }
      },
      //删除代码
      deleteRepair(id){
        let _this=this;
     _this.$confirm("确定删除吗？","系统提示",{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
     }).then(async()=>{
      let parm = {
            repairid: id,
          };
          let { data: res } = await _this.$http.post("/jnu/repair/deleteRepair",parm);
    if (res.code == 100) {
            //信息提示
            _this.$message({
              message: res.msg,
              type: "success",
            });
            //刷新数据
            _this.getRepairList();
          } else {
            //信息提示
            _this.$message({
              message: res.msg,
              type: "error",
            });
            }
     })
      },
         //单个删除
      deleteRow(row){
       this.deleteRepair(row.repairid);
      },
        //批量删除
      deleteAllRepair(){
         let _this=this;
     _this.$confirm("确定删除吗？","系统提示",{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
     }).then(async()=>{
      let parm = {
            ids: this.selectionList
          };
          let { data: res } = await _this.$http.post("/jnu/repair/deleteRepairAll",parm);
          console.log("****批量删除*****"+res.code);
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
      this.getRepairList();
     })
   
      },
    //   //单个删除
    //   deleteRow(row){
    //         let _this=this;
    //  _this.$confirm("确定删除吗？","系统提示",{
    //       confirmButtonText: '确定',
    //       cancelButtonText: '取消',
    //       type: 'warning'
    //  }).then(async()=>{
    //   let parm = {
    //         repairid: row.repairid,
    //       };
    //       let { data: res } = await _this.$http.post("/jnu/repair/deleteRepair",parm);
    // if (res.code == 100) {
    //         //信息提示
    //         _this.$message({
    //           message: res.msg,
    //           type: "success",
    //         });

    //       } else {
    //         //信息提示
    //         _this.$message({
    //           message: res.msg,
    //           type: "error",
    //         });
    //         }
    //      //刷新数据
    //   this.getRepairList();
    //  })

    //   },
   
      //分页相关方法
        handleSizeChange(val) {//改变页容量时调用

          //改变当前页时调用
        // console.log(`每页 ${val} 条`);
         this.pageSize=val;
        this.getRepairList();   
      },
      handleCurrentChange(val) { 
       // console.log(`当前页: ${val}`);
          //改变当前页时调用
         this.currentPage = val;
         this.getRepairList();
      }
        },
        mounted() {
    this.$nextTick(() => {
      this.tableHeight = window.innerHeight - 350; //后面的50：根据需求空出的高度，自行调整
    });
  },
    }
</script>

<style lang="scss" scoped>
.searchBtn{
    margin-left: 30px;
}
.el-main{
    padding-top: 5px !important;
}

</style>