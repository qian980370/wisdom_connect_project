<template>
  <div style="width: 100%; height: 100vh;  overflow: hidden">
    <div class="header">
      <h2>wisdom connect</h2>
    </div>
    <div style="width: 540px; height:500px;margin: 30px auto;background-color: #f3f3f3;border-radius: 5px">


      <div style="color: #864a98; font-size: 32px; text-align: center; padding-top: 70px;padding-bottom: 50px">Facility Login</div>
      <el-form ref="form" :model="loginForm" size="large" :rules="rules" style="width: 80%;margin:auto">
        <el-form-itme prop="username" class="textarea" >
          <el-input v-model="loginForm.username" placeholder="Please input username" style="height: 30px" >

          </el-input>
        </el-form-itme>
        <div style="margin-top: 30px">
        <el-form-itme prop="password" class="textarea">
          <el-input v-model="loginForm.password" style="width: 100%; margin: 11px 0 0 0;" show-password placeholder="Please input password">

          </el-input>
        </el-form-itme>
        </div>
        <div style="margin: 10px auto;text-align: center">
        <el-form-itme >
          <el-button  type="primary" @click="login" style="margin-top: 20px;background-color: #bfa0c8;width: 200px">Faculty Login</el-button>

        </el-form-itme>

        </div>
        <div style="margin: 0 auto;text-align: center">
          <el-form-itme >
            <el-button  type="primary" @click="signup" style="margin-top: 20px;background-color: #bfa0c8;width: 200px">Faculty Signup</el-button>

          </el-form-itme>

        </div>


      </el-form>

    </div>
  </div>


</template>

<script>
import {Document, LocationFilled, MapLocation, Money, Setting, Lock, User} from '@element-plus/icons-vue'
import { ElForm } from 'element-plus'
import { ElButton } from 'element-plus'
import { ElFormItem } from 'element-plus'
import request from "@/utils/request";

export default {
  name: "FacilityLoginView",
  components: {Money, MapLocation, LocationFilled, Document, Setting, Lock, User},
  data(){
    return{
      loginForm:{},
      rules: {
        username: [
          {required: true, message: 'please input username', trigger: 'blur'},
        ],
        password: [
          {required: true, message: 'please input password', trigger: 'blur'},
        ],
      },
    }
  },

  methods:{
    login(){
      console.log("login");
      request.post("/user/login", this.loginForm).then(res => {
        console.log(res);
        if (res.code === '200') {
          localStorage.setItem("user", JSON.stringify(res.data));
          this.$message({
            type: "success",
            message: "Successfully login"
          })
          this.$router.push("/profilelogin")
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        // this.dialogVisible = false;
      });
    },
    signup(){
      this.$router.push("/facilitysignup")
    }
  }
}
</script>

<style scoped>
.header{
  height: 70px;
  width: 292px;
  margin: 0 auto;
  /* text-align: center; */
  font-size:19px;
  text-align: right;
  line-height: 70px;
  background-image: url(../image/WechatIMG2010.png);
  background-size: 100px 70px;
  background-repeat: no-repeat;
  background-position: left center;
  margin-top: 70px;
}
.textarea>>>.el-input__inner{
  height: 48px;
}

</style>