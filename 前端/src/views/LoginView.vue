<template>
  <!--html-->
  <section class="section_background">
    <div class="section_background_sr">
      <span><h1>用户登录</h1></span> 
      <input id="username" type="text" name="username" v-model="username" />
      <input id="password" type="password" name="password" v-model="password" />

      <el-button type="text" size="medium" @click="showPwd">忘记密码</el-button>
      <input
        id="login_sumbit"
        type="submit"
        @click="login_click"
        value="登录"
      />
    </div>

    <el-dialog title="你的用户密码是：" :visible.sync="dialogVisible" width="32%" center>
      <el-form>
      <el-form-item prop="username" label="用户账号：">
        {{username}}
      </el-form-item>
      <el-form-item prop="deptName" label="密码：">
       {{password}}
      </el-form-item>
      </el-form>
  
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>

  </section>

  
</template>

<script>
import axios from "axios";
export default {
  //双向绑定
  data() {

    return {
      dialogVisible:false,
      username: "zhangsan",
      password: "123",
    };
  },

  methods: {
    codeClick() {
      this.codeImg = "/codeImg?date" + new Date();
    },

    showPwd(){
       this.dialogVisible=true;
    },

    login_click() {
      /*
      //alert('111');
      //发送/login请求，并携带username,password
      this.postRequest("/login", {
        username:  this.username,
        password: this.password,
        code: this.code,
      })
        .then((res) => {
          //alert(res.data);
          //console.log(res)
          //引用element-ui的消息
          //this.$message.success(res.data)
          if(res.code==200){
            //alert(res.data);
            //取出后端的数据，将数据存sessionStorage作用域中
           sessionStorage.setItem('user',JSON.stringify(res.data))
          this.$router.replace('/home');//通过路由跳转
          }
        })
        .catch((error) => {
          //this.$message.error('失败')
        });*/
      this.$router.replace("/desktop");
    },
  },
};
</script>

<style lang="scss">
.section_background {
  width: 100vw;
  height: 100vh;
  background-image: url("@/assets/login.jpeg");
  background-size: cover;
  #code {
    width: 200px;
    height: 48px;
    border-radius: 0.3125rem;
    border: 1px solid #c0c0c0;
    font-size: 18px;
    padding-left: 10px;
    color: Gray;
    outline-style: none;
  }

  .section_background_sr {
    width: 500px;
    height: 400px;
    background-color: white;
    position: absolute;
    top: 40%;
    left: 52%;
    transform: translate(-50%, -50%);
    border-radius: 0.625rem;

    display: flex;
    display: -webkit-flex; /*需要webkit内核*/
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  .section_background_sr > #username,
  .section_background_sr > #password {
    width: 390px;
    height: 48px;
    border-radius: 0.3125rem;
    border: 1px solid #c0c0c0;
    margin-bottom: 15px;
    font-size: 18px;
    padding-left: 10px;
    color: Gray;
    outline-style: none;
  }

  .section_background_sr > .wj {
    color: #ffa500;
    font-weight: 700;
    margin-left: 330px;
    cursor: pointer;
    /*双击不选中*/
    user-select: none;
    margin-bottom: 15px;
  }

  .section_background_sr > #login_sumbit {
    width: 400px;
    height: 48px;
    border-radius: 0.3125rem;
    background-color: #ffa500;
    border: white 0rem;
    margin-bottom: 15px;
    cursor: pointer;
    font-size: 20px;
    color: white;
    font-weight: 900;
  }

  .wjmm {
    background-color: rgba(0, 0, 0, 0.4);
    width: 180px;
    height: 30px;
    display: flex;
    display: -webkit-flex;
    justify-content: center;
    align-items: center;
    color: white;
    border-radius: 50px;
    font-size: 15px;
    user-select: none;
    margin-bottom: 15px;

    position: absolute;
    top: 63%;
    left: 50%;
    transform: translate(-50%, -50%);
    border-radius: 0.625rem;
    text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.4);
  }

  .wjmm > a {
    color: #ffa500;
    outline: none;
    text-decoration: none;
  }
  .error_msg {
    width: 400px;
    height: 24px;
    color: red;
    margin-bottom: 20px;
    font-size: 15px;
  }
}
</style>
