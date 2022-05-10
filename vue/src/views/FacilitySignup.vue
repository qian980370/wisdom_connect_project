<template>
  <div style="width: 100%; height: 100vh;  overflow: hidden">
    <div class="header">
      <h2 >wisdom connect</h2>
    </div>
    <div style="width: 540px; height:600px;margin: 30px auto;background-color: #f3f3f3;border-radius: 5px">
      <div style="text-align: center;margin: 30px auto;padding-top: 30px">
        <h2 style="color: #bfa0c8">Enter Nursing Home Information</h2>
      </div>

      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

      <el-form-item label="Username" prop="name">
        <el-input v-model="ruleForm.name" class="textarea" style="width: 80%"></el-input>
      </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input v-model="ruleForm.password" class="textarea" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="Address" prop="address">
          <el-input v-model="ruleForm.address" class="textarea" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="ABN" prop="abn">
          <el-input v-model="ruleForm.abn" class="textarea" style="width: 80%"></el-input>
        </el-form-item>

        <div style="text-align: center;padding-top: 30px;padding-bottom: 30px">
        <h2 style="color: #bfa0c8">Enter FastCode</h2>
        </div>
        <el-form-item label="Email" prop="email">
          <el-input v-model="ruleForm.email" class="textarea" style="width: 70%"></el-input>
          <el-button  type="primary" @click="applyFastCode" style="background-color: #bfa0c8;width: 100px">{{status}}</el-button>

        </el-form-item>
        <el-form-item label="Fastcode" prop="fastcode">
          <el-input v-model="ruleForm.fastcode" class="textarea" style="width: 70%"></el-input>

        </el-form-item>

        <div style="text-align: center;margin-top: 30px">
        <el-button  type="primary"  @click="submit" style="background-color: #bfa0c8;width: 200px">sign up</el-button>
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
        password: '',
        address: '',
        abn: '',
        email: '',
        fastcode: '',


      },
      fastCodeFrom:{},
      Form:{},
      status:'send code',
      img: null,
      user : localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null,

      rules: {
        name: [
          { required: true, message: 'please input the username', trigger: 'blur' }

        ],
        password:[
            {required:true,message:'please input the password',trigger: 'blur'},
            {min: 3, max: 8, message: 'invalid password', trigger: 'blur'}],
        address: [
          { required: true, message: 'please input the address', trigger: 'blur' }

        ],
        abn: [
          { required: true, message: 'please input the abn', trigger: 'blur' },
          {validator(rule, value, callback){
              const reg= /^[a-zA-Z][\w-. @]*$/;
              if(value === '' || value === undefined){
                callback();
              }else {
                if (!reg.test(value)){
                  callback(new Error('要求为：英文字母开头，后续为字母数字及_-. @符号'));
                }else {
                  callback();
                }
              }
            },
            trigger: 'blur'}

        ],
        email:[
            {required:'true',message:'please input email',trigger:'blur'},
          {type:'email',message: 'please input in correct email format',trigger: 'blur'}],
      },

    }
  },
  methods: {
    applyFastCode(){
      this.status = 'processing'
      setTimeout(()=>{
        this.status = 'send code'

      },5000)
      this.fastCodeFrom.name = this.ruleForm.name;
      this.fastCodeFrom.email = this.ruleForm.email;
      this.fastCodeFrom.address = this.ruleForm.address;
      this.fastCodeFrom.abn = this.ruleForm.abn;
      this.fastCodeFrom.role = 'facility';
      // console.log(this.fastCodeFrom)
      request.post("/fastcode/create", this.fastCodeFrom).then(res =>{
        console.log(res);
        if (res.code === '200'){
          this.$message({
            type: "success",
            message: "Successfully Apply user fast code"
          })
        }else{
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      });
    },
    submit(){
      console.log("create");


      this.Form.username = this.ruleForm.name;
      this.Form.password = this.ruleForm.password;
      this.Form.email = this.ruleForm.email;
      this.Form.address = this.ruleForm.address;
      this.Form.abn = this.ruleForm.abn;
      this.Form.role = 'facility';
      this.Form.code = this.ruleForm.fastcode;
      console.log(this.Form);
      request.post("/user/register", this.Form).then(res =>{
        console.log(res);
        if (res.code === '200'){
          this.$message({
            type: "success",
            message: "Successfully add user"
          })
          this.$router.push("/profilelogin")

        }else{
          this.$message({
            type: "error",
            message: res.msg
          })
        }

      });

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
.textarea>>>.el-input__inner{
  height: 40px;
}
</style>
