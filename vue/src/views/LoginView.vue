<template>
  <div style="width: 100%; height: 100vh; background-color: mediumpurple; overflow: hidden">
    <div style="width: 450px; margin: 150px auto">
      <div style="color: #ffffff; font-size: 32px; text-align: center; padding: 30px">Login</div>
      <el-form ref="form" :model="form" size="large" :rules="rules">
        <el-form-itme prop="username">
          <el-input v-model="form.username" placeholder="Please input username">
            <template #prefix>
              <el-icon class="el-input__icon" :size="20"><user /></el-icon>
            </template>
          </el-input>
        </el-form-itme>
        <el-form-itme prop="password">
          <el-input v-model="form.password" style="width: 100%; margin: 11px 0 0 0;" show-password placeholder="Please input password">
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
import request from "@/utils/request";
export default {
  name: "LoginView",
  components: {Money, MapLocation, LocationFilled, Document, Setting, Lock, User},
  data(){
    return{
      form:{},
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
      this.$refs['form'].validate((valid) => {
        if (valid){
          console.log("login");
          request.post("/user/login", this.form).then(res =>{
            console.log(res);
            if (res.code === '0'){
              this.$message({
                type: "success",
                message: "Successfully login"
              })
              this.$router.push({ //jump to home page
                name: 'Home',
                params: {
                  username: this.form.username
                }
              })
            }else{
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          });
        }

      })


    }
  }
}
</script>

<style scoped>

</style>