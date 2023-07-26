<template>
    <el-main>
      <!--搜索框 -->
      <el-form :model="householdForm" size="mini" label-width="80px">
        <el-row>
          <el-col :span="5">
            <el-form-item label="部门名">
              <el-input v-model=" householdForm.deptname" placeholder="请输入部门名"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="楼栋号">
              <el-input v-model=" householdForm.building" placeholder="请输入楼栋号"></el-input>
            </el-form-item>
          </el-col>
          <el-button class="searchBtn" type="primary" size="mini" icon="el-icon-search" @click="seachBtn">查询</el-button>
          <el-button class="newBtn" type="primary" size="mini" icon=" el-icon-plus" @click="addHouseholdShow">新增</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="deleteAllHousehold">批量删除</el-button>
        </el-row>
      </el-form>
      <el-table :data="tableData" :height="tableHeight"  @selection-change="handleSelectionChange" span-method="objectSpanMethod" border style="width: 100%">
      <!--列表-->
        <el-table-column type="selection" width="60"></el-table-column>
        <el-table-column prop="deptid" label="#"></el-table-column>
        <el-table-column prop="deptname" label="部门名"></el-table-column>
        <el-table-column class="sectable" prop="deptPos" label="下属岗位">
              <template slot-scope="scope">
                  <el-table :data="scope.row.deptPos">
                        <el-table-column prop="posname" label="岗位名"></el-table-column>
                        <el-table-column prop="basesal" label="底薪"></el-table-column>
                        <el-table-column prop="PosSal" label="加薪设计" align="center">
                          <template slot-scope="SalScope">
                            <el-popover trigger="hover" placement="top">
                              <p>薪资项: {{ SalScope.row.PosSal.salname }}</p>
                              <p>薪资单位: {{ SalScope.row.PosSal.salunit }}</p>
                              <p>单位数额: {{ SalScope.row.PosSal.unitval }}</p>
                              <div slot="reference" class="name-wrapper">
                                <el-tag size="medium"> 详情 </el-tag>
                              </div>
                            </el-popover>
                          </template>
                        </el-table-column>                       
                  </el-table>
              </template>
        </el-table-column> 
        
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
       <!-- 新增对话框-->
       <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="32%" center>
       <!-- 对话框内容-->
       <el-form :rules="rules" ref="addHousehold" :model="deptForm.deptPos.PosSal" label-width="100px">
       <el-form-item prop="deptname" label="部门名">
        <el-input v-model="deptForm.deptname" placeholder="部门名"></el-input>
       </el-form-item>
       <el-form-item prop="deptPos" label="下属岗位">
        <el-input v-model="deptForm.deptPos" placeholder="下属岗位"></el-input>
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
      this.getDeptList();
    },
    data() {
      return{
               //批量删除选中id
                selectionList:[],
                //每页显示的条数
                pageSize:10,
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
                householdForm:{
                  deptname:"",
                  building:"",
                },
                //弹出窗校验
                rules:{
                  deptname: [
                  {
                    required: true,
                    trigger: "change",
                    message: "请输入部门名"
                  }
                  ],
                },
                //添加数据绑定
                deptForm:{
                    deptid:"",
                    deptname:"",
                    deptPos:{
                      posid:"",
                      posname:"",
                      basesal:"",
                      PosSal:{
                          salid:"",
                          salname:"",
                          salunit:"",
                          unitval:""
                      }
                    }
                },
                //设置表的高度
                tableHeight:window.innerHeight,
                //表格的数据
                tableData: [{
                    deptid:1,
                    deptname:"测试用部门",
                    deptPos:[{
                      posid:1,
                      posname:"测试用职业1",
                      basesal:2000,
                      PosSal:{
                          salid:1,
                          salname:"测试用薪资1",
                          salunit:"测试用单元1",
                          unitval:10
                      }
                    },
                    {
                    posid:2,
                      posname:"测试用职业2",
                      basesal:2000,
                      PosSal:{
                          salid:2,
                          salname:"测试用薪资2",
                          salunit:"测试用单元2",
                          unitval:20
                      }
                    }
                  ]
                }],
      }
    },
    methods: {
      


      //批量选择时触发
      handleSelectionChange(selection){
        this.selectionList = [],
        selection.forEach(element => {
            this.selectionList.push(element.deptid)
        });
         console.log("批量选择"+this.selectionList);
        },


      //获得部门列表数据
      async getDeptList() {
        let parm = {
          currentPage: this.currentPage,
          pageSize: this.pageSize,
          title:this.deptForm.deptname,
        };
        let { data: res } = await this.$http.post("/j2ee/dept/DeptSearch", parm);
        console.log(res.code);
        if (res.code == 100) {
          this.tableData = res.data.records;
          this.currentPage = res.data.current;
          this.pageSize = res.data.size;
          this.total = res.data.total;
        }else{
          this.$message({
            type:'error',
            message:res.msg
          })
        }
      },


      //模糊查询
      seachBtn() {
        //查询前给当前页赋为1
       this.currentPage=1;
       this.getDeptList();
      },

      //新增弹窗显示
      addHouseholdShow(){
        this.editTag='0';
      //调用对话框表单清空方法
        this.restDialogForm("addHousehold");
        //设置对话框标题
       this.dialogTitle="添加住户";
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
      _this.$refs.addHousehold.validate(async valid => {
        if (valid) {
           let url="";
           if(this.editTag=='0'){
            url="/j2ee/dept/addHousehold";
           } else{
             url="/j2ee/dept/updateHousehold";
           }

          let{data:res}=await _this.$http.post(url,_this.deptForm);
          console.log("*住户添加*********"+res.code);
          if(res.code==100){
            this.$message({
            type:'success',
            message:res.msg
          });
          //刷新数据
          _this.getDeptList();
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
      SalShow(row){
      // console.log("****编辑*****"+row.householdname);
        this.editTag = "1";
        this.restDialogForm("addHousehold");
        this.dialogTitle = "编辑住户"
        this.dialogVisible=true;
      },


      //编辑
      editRow(row){
      // console.log("****编辑*****"+row.householdname);
      this.editTag = "1";
      //调用对话框表单清空方法
      this.restDialogForm("addHousehold");
     //设置对话框标题
      this.dialogTitle = "编辑住户"
       this.dialogVisible=true;
       //根据id查询业主
       this.getHouseholdById(row.deptid);
      },
     //根据id进行查询
     async getHouseholdById(id){
      let parm={
        deptid:id
       }
       let {data:res} = await this.$http.post( "/j2ee/dept/queryHouseholdById",parm);
          console.log("+++查询+++"+res.data);
          if(res.code==100){
            this.deptForm.deptid=res.data.deptid;
            this.deptForm.building=res.data.building;
            this.deptForm.deptname=res.data.deptname;
            this.deptForm.deptPos=res.data.deptPos;
            this.deptForm.floor=res.data.floor;
            this.deptForm.room=res.data.room;
          }else{
              this.$message({
            type:'error',
            message:res.msg});
          }
      },
      //单个删除
      deleteRow(row){
        let _this=this;
        _this.$confirm("确定删除吗？","系统提示",{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async()=>{
            let parm = {
            deptid: row.deptid,
          };
          let { data: res } = await _this.$http.post("/j2ee/dept/deleteHousehold",parm);
          if (res.code == 100) {
            //信息提示
            _this.$message({
              message: res.msg,
              type: "success",
            });
            //刷新数据
            _this.getDeptList();
          } else {
            //信息提示
              _this.$message({
                message: res.msg,
                type: "error",
              });
            }
        })
      },
      //批量删除
      deleteAllHousehold(){
        let _this=this;
        _this.$confirm("确定删除吗？","系统提示",{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async()=>{
          let parm = {
            ids: this.selectionList
          };
          let { data: res } = await _this.$http.post("/j2ee/dept/deleteHouseholdAll",parm);
          console.log("****批量删除*****"+res.code);
          if (res.code == 100) {
            //信息提示
            _this.$message({
              message: res.msg,
              type: "success",
            });
            //刷新数据
            this.getDeptList();
          } else {
            //信息提示
            _this.$message({
              message: res.msg,
              type: "error",
            });
            }
        })
      },
      //改变页容量时调用
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`);
        this.pageSize = val;
        this.getDeptList();
      },
      //改变当前页时调用
      handleCurrentChange(val) {
        //console.log(`当前页: ${val}`);
        this.currentPage = val;
        this.getDeptList();
      },
    }, //该钩子函数执行时所有的DOM挂载和渲染都已完成，此时在该钩子函数中进行任何DOM操作都不 会有问题
    // 在数据变化后要执行的某个操作，而这个操作需要使用随数据改变而改变的DOM结构的时候，
    // 这个操作都应该放进Vue.nextTick()的回调函数中
    mounted() {
      this.$nextTick(() => {
        this.tableHeight = window.innerHeight - 275; //后面的50：根据需求空出的高度，自行调整
      });
    },
  };
  </script>
  
  <style lang="scss" scoped>
  .searchBtn {
    margin-left: 15px;
  }
  .el-main {
    padding-top: 5px !important;
  }
  </style>
  