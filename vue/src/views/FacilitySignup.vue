<template>
  <div style="width: 100%; height: 120vh;  overflow: hidden">
<!--    <div class="header">-->
<!--      <h2 >wisdom connect</h2>-->
<!--    </div>-->

    <div class="logo_and_title">
      <table>
        <tr>
          <td><img  src="../image/logo.png" alt="logo"></td>
          <td><h1>Wisdom Connect</h1></td>
        </tr>
      </table>
    </div>


    <div class="signup_container">
      <div  class="signup_form">
        <!---------header--------->
        <div class="signup_form_header"><p>Enter Nursing Home Information</p></div>

        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm">
          <!---------name--------->
          <div class="signup_facility_name">
            <table>
              <tr>
                <td><p>Name</p></td>
                <td>
                  <el-form-item prop="name">
                    <el-input v-model="ruleForm.name"></el-input>
                  </el-form-item>
                </td>
              </tr>
            </table>
          </div>
          <!---------Password--------->
          <div class="signup_facility_password">
            <table>
              <tr>
                <td><p>Password</p></td>
                <td>
                  <el-form-item prop="password">
                    <el-input v-model="ruleForm.password"></el-input>
                  </el-form-item>
                </td>
              </tr>
            </table>
          </div>
          <!---------Address--------->
          <div class="signup_facility_address">
            <table>
              <tr>
                <td><p>Address</p></td>
                <td>
                  <el-form-item prop="address">
                    <el-input v-model="ruleForm.address"></el-input>
                  </el-form-item>
                </td>
              </tr>
            </table>
          </div>
          <!---------ABN--------->
          <div class="signup_facility_abn">
            <table>
              <tr>
                <td><p>ABN</p></td>
                <td>
                  <el-form-item prop="abn">
                    <el-input v-model="ruleForm.abn" ></el-input>
                  </el-form-item>
                </td>
              </tr>
            </table>
          </div>

          <!---------header--------->
          <div class="signup_form_header"><p>Enter Fastcode</p></div>


          <div class="signup_facility_fastcode" style="margin-bottom: 50px;">
            <table>
              <!---------Email--------->
              <tr>
                <td><p>Email</p></td>
                <td>
                  <el-form-item prop="email">
                    <el-input v-model="ruleForm.email"></el-input>
                  </el-form-item>
                </td>
                <td>
                  <el-form-item prop="email">
                    <el-button  type="primary" @click="applyFastCode" >{{status}}</el-button>
                  </el-form-item>
                </td>
              </tr>
              <!---------Fastcode--------->
              <tr>
                <td><p>Fastcode</p></td>
                <td>
                  <el-form-item prop="fastcode">
                    <el-input v-model="ruleForm.fastcode" ></el-input>
                  </el-form-item>
                </td>
<!--                <td>-->
<!--                  <a href="javascript:"><button>Verify</button></a>-->
<!--                </td>-->
              </tr>
            </table>
          </div>

          <hr >
          <!---------Signup Button--------->
          <div class="signup_create">
            <el-button  type="primary"  @click="submit" class="signup_create">Sign up</el-button>
          </div>
        </el-form>

      </div>
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
            {min: 3, max: 8, message: 'valid password length should be 3 to 8', trigger: 'blur'}],
        address: [
          { required: true, message: 'please input the address', trigger: 'blur' }

        ],
        abn: [
          { required: true, message: 'please input the abn', trigger: 'blur' },
          // {validator(rule, value, callback){
          //     const reg= /^[a-zA-Z][\w-. @]*$/;
          //     if(value === '' || value === undefined){
          //       callback();
          //     }else {
          //       if (!reg.test(value)){
          //         callback(new Error('要求为：英文字母开头，后续为字母数字及_-. @符号'));
          //       }else {
          //         callback();
          //       }
          //     }
          //   },
          //   trigger: 'blur'}

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
.logo_and_title{
  width: 400px;
  height: 120px;
  text-align: center;
  padding-left: 37%;
  padding-top: 2%;
}
.logo_and_title img{
  width: 80px;
}
.logo_and_title h1{
  width: 300px;
}
.signup_container{
  height: 1200px;
}
.signup_form{
  width: 600px;
  height: 840px;
  margin:20px auto;
  background-color: #f3f3f3;
  text-align: center;
  border-radius: 5px;

}
.signup_form_header{
  margin-bottom: 20px;
}
.signup_form_header p{
  font-size: 24px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
  padding-top: 40px;
  padding-bottom: 10px;
}
.signup_create button{
  height: 44px;
  width: 360px;
  background-color: #bfa0c8;
  border: 0px;
  margin-top: 40px;
  font-size: 18px;
  color:white;
  margin-bottom: 30px;
  border-radius: 5px;
  cursor: pointer;
}
.signup_create button:hover{
  height: 44px;
  width: 360px;
  background-color: #f3f3f3;
  border: solid 2px #bfa0c8;
  margin-top: 40px;
  font-size: 18px;
  color: #bfa0bf;
  margin-bottom: 30px;
  border-radius: 5px;
  cursor: pointer;
}


.signup_facility_name{
  margin-left: 70px;
  margin-top: 30px;
}
.signup_facility_name td{
  height: 60px;
}
.signup_facility_name p{
  width: 100px;
  font-size: 18px;
  font-weight: bold;
  color:#864a98;
  text-align: left;
  padding-bottom: 30px;
}
.signup_facility_name >>> .el-input__inner{
  height: 50px;
  width: 350px;
  border: 0px;
  font-size: 18px;
  border-radius: 5px;
  outline-color: #bfa0c8;
}

.signup_facility_address{
  margin-left: 70px;
}
.signup_facility_address td{
  height: 60px;
}
.signup_facility_address p{
  width: 100px;
  font-size: 18px;
  font-weight: bold;
  color:#864a98;
  text-align: left;
  padding-bottom: 20px;
}
.signup_facility_address >>> .el-input__inner{
  height: 50px;
  width: 350px;
  border: 0px;
  padding-left: 10px;
  font-size: 18px;
  border-radius: 5px;
  outline-color: #bfa0c8;
}

.signup_facility_abn{
  margin-left: 70px;
}
.signup_facility_abn td{
  height: 60px;
}
.signup_facility_abn p{
  width: 100px;
  font-size: 18px;
  font-weight: bold;
  color:#864a98;
  text-align: left;
  padding-bottom: 20px;
}
.signup_facility_abn >>> .el-input__inner{
  height: 50px;
  width: 350px;
  border: 0px;
  padding-left: 10px;
  font-size: 18px;
  border-radius: 5px;
  outline-color: #bfa0c8;
}

.signup_facility_password{
  margin-left: 70px;
}
.signup_facility_password td{
  height: 60px;
}
.signup_facility_password p{
  width: 100px;
  font-size: 18px;
  font-weight: bold;
  color:#864a98;
  text-align: left;
  padding-bottom: 20px;
}
.signup_facility_password >>> .el-input__inner{
  height: 50px;
  width: 350px;
  border: 0px;
  padding-left: 10px;
  font-size: 18px;
  border-radius: 5px;
  outline-color: #bfa0c8;
}


.signup_facility_fastcode{
  margin-left: 70px;
}
.signup_facility_fastcode td{
  height: 60px;
}
.signup_facility_fastcode p{
  width: 100px;
  font-size: 18px;
  font-weight: bold;
  color:#864a98;
  text-align: left;
  padding-bottom: 20px;
}
.signup_facility_fastcode >>> .el-input__inner{
  height: 50px;
  width: 220px;
  border: 0px;
  font-size: 18px;
  border-radius: 5px;
  outline-color: #bfa0c8;
}
.signup_facility_fastcode button {
  height: 44px;
  width: 100px;
  margin-left: 20px;
  background-color: #f3f3f3;
  border: solid 2px;
  font-size: 18px;
  color:#bfa0c8;
  border-radius: 5px;
  cursor: pointer;
}
.signup_facility_fastcode button:hover {
  height: 44px;
  width: 100px;
  margin-left: 20px;
  background-color: #bfa0c8;
  border: solid 2px #bfa0c8;
  font-size: 18px;
  color:white;
  border-radius: 5px;
  cursor: pointer;
}
.signup_facility_fastcode button:active {
  height: 44px;
  width: 100px;
  margin-left: 20px;
  background-color: #bfa0c8;
  border: solid 2px #bfa0c8;
  font-size: 18px;
  color:white;
  border-radius: 5px;
  cursor: pointer;
}
</style>
