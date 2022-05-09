<template>
  <div style="width: 100%; height: 100vh; overflow: hidden">
    <div style="width: 400px; margin: 100px auto">
      <div style="font-size: 30px; text-align: center; padding: 30px 0">Welcome to Registration</div>
      <el-form ref="form" :model="form" size="normal" :rules="rules">
        <el-form-item prop="username">
          <el-input v-model="form.username" placeholder="Please input username">
            <template #prefix>
              <el-icon class="el-input__icon" :size="20"><user /></el-icon>
            </template>
          </el-input>

        </el-form-item>

        <el-form-item prop="email">
          <el-input v-model="form.email" placeholder="Please input email">
            <template #prefix>
              <el-icon class="el-input__icon" :size="20"><message /></el-icon>
            </template>
          </el-input>

        </el-form-item>

        <el-form-item prop="password">
          <el-input v-model="form.password" placeholder="Please input password" show-password>
            <template #prefix>
              <el-icon class="el-input__icon" :size="20"><lock /></el-icon>
            </template>
          </el-input>

        </el-form-item>
        <el-form-item prop="confirm">
          <el-input v-model="form.confirm" placeholder="Please input password again" show-password>
            <template #prefix>
              <el-icon class="el-input__icon" :size="20"><lock /></el-icon>
            </template>
          </el-input>

        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="register">Register</el-button>
        </el-form-item>
        <el-form-item><el-button type="text" @click="$router.push('/login')">&lt;&lt;Back to Login </el-button></el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import {Message, Document, LocationFilled, Lock, MapLocation, Money, Setting, User} from "@element-plus/icons-vue";

export default {
  name: "RegisterView",
  components: {Money, MapLocation, LocationFilled, Document, Message, Setting, Lock, User},
  data() {
    return {
      form: {},
      rules: {
        username: [
          {required: true, message: 'please input username', trigger: 'blur'},
        ],
        password: [
          {required: true, message: 'please input password', trigger: 'blur'},
        ],
        confirm: [
          {required: true, message: 'please input your password again', trigger: 'blur'},
        ],
      }
    }
  },
  methods: {
    register() {

      if (this.form.password !== this.form.confirm) {
        this.$message({
          type: "error",
          message: 'Inconsistent password input!'
        })
        return
      }

      this.$refs['form'].validate((valid) => {
        if (valid) {
          console.log("create");
          request.post("/user/register", this.form).then(res =>{
            console.log(res);
            if (res.code === '200'){
              this.$message({
                type: "success",
                message: "Successfully add user"
              })
              this.$router.push("/login")
            }else{
              this.$message({
                type: "error",
                message: res.msg
              })
            }
            this.load();
            this.dialogVisible=false;
          });
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
