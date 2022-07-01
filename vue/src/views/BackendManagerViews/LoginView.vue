<template>
  <div style="width: 100%; height: 100vh; background-color: mediumpurple; overflow: hidden">
    <div style="width: 450px; margin: 150px auto">
      <div style="color: #ffffff; font-size: 32px; text-align: center; padding: 30px">Login</div>
      <el-form ref="form" :model="loginForm" size="large" :rules="rules">
        <el-form-itme prop="username">
          <el-input v-model="loginForm.username" placeholder="Please input username">
            <template #prefix>
              <el-icon class="el-input__icon" :size="20"><user /></el-icon>
            </template>
          </el-input>
        </el-form-itme>
        <el-form-itme prop="password">
          <el-input v-model="loginForm.password" style="width: 100%; margin: 11px 0 0 0;" show-password placeholder="Please input password">
            <template #prefix>
              <el-icon class="el-input__icon" :size="20"><lock /></el-icon>
            </template>
          </el-input>
        </el-form-itme>
        <el-form-itme>
          <el-button style="width: 100%; margin: 20px 0 0 0;" type="primary" @click="login">Login</el-button>
        </el-form-itme>

        <el-form-item><el-button type="text" @click="$router.push('/register')">Not own a account? Register Now!</el-button></el-form-item>
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
  name: "LoginView",
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
          this.$router.push("/home")
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.dialogVisible = false;
      });
    }
  }
}
</script>

<style scoped>

</style>