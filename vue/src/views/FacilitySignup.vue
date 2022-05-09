<template>
  <div style="width: 100%; height: 100vh;  overflow: hidden">
    <div class="header">
      <h2 >wisdom connect</h2>
    </div>
    <div style="width: 540px; height:500px;margin: 30px auto;background-color: #f3f3f3;border-radius: 5px">
      <div style="text-align: center;margin: 30px auto">
        <h2 style="color: #bfa0c8">Enter Nursing Home Information</h2>
      </div>

      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

      <el-form-item label="username" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
        <el-form-item label="password" prop="password">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="Address" prop="Address">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="ABN" prop="abn">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>

        <div style="text-align: center">
        <h2 style="color: #bfa0c8">Enter FastCode</h2>
        </div>
        <el-form-item label="Email" prop="email">
          <el-input v-model="ruleForm.name" style="width: 50%"></el-input>
          <el-button  type="primary"  style="background-color: #bfa0c8;width: 200px">send</el-button>

        </el-form-item>
        <el-form-item label="Fastcode" prop="fastcode">
          <el-input v-model="ruleForm.name" style="width: 50%"></el-input>
          <el-button  type="primary"  style="background-color: #bfa0c8;width: 200px">verify</el-button>

        </el-form-item>

        <div style="text-align: center">
        <el-button  type="primary"  style="background-color: #bfa0c8;width: 200px">sign up</el-button>
        </div>
      </el-form>
    </div>
    </div>
</template>

<script>
import request from "@/utils/request";
import {Message, Document, LocationFilled, Lock, MapLocation, Money, Setting, User} from "@element-plus/icons-vue";
import { ElForm } from 'element-plus'
import { ElButton } from 'element-plus'
export default {
  name: "FacilitySignup",
  data() {
    return {
      ruleForm: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      rules: {
        name: [
          { required: true, message: 'please input the username', trigger: 'blur' }

        ],
        password:[
            {required:true,message:'please input the password',trigger: 'blur'},
            {min: 3, max: 8, message: 'invalid password', trigger: 'blur'}],

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
        if (valid) {
          console.log("create");
          request.post("/user/register", this.form).then(res =>{
            console.log(res);
            if (res.code === '200'){
              this.$message({
                type: "success",
                message: "Successfully add user"
              })
              this.$router.push("/facilitylogin")
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
      }
},}
</script>

<style scoped>
.header{
  height: 70px;
  width: 292px;
  /* text-align: center; */
  font-size:19px;
  text-align: right;
  line-height: 70px;
  background-image: url(../image/WechatIMG2010.png);
  background-size: 100px 70px;
  background-repeat: no-repeat;
  background-position: left center;
  margin: 70px auto 0;
}
</style>
