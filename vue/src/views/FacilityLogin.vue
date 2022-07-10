<template>
  <div style="width: 100%; height: 100vh;  overflow: hidden">
    <wisdom-header></wisdom-header>

    <div class="login_container">
      <div class="login_form">
        <!---------header--------->
        <div class="login_form_header"><p>Welcome to Wisdom Connect!</p></div>

        <el-form ref="form" :model="loginForm" size="large" :rules="rules" style="width: 80%; margin-top: 40px; margin-left: 50px">

          <el-form-itme prop="username" class="login_account" >
            <el-input v-model="loginForm.username" placeholder="Username" style="margin-bottom:10px;"></el-input>
          </el-form-itme>

          <el-form-itme prop="password" class="login_password">
            <el-input v-model="loginForm.password"  show-password placeholder="Password"></el-input>
          </el-form-itme>

        </el-form>

          <div class="login_facility_confirm">
            <el-form-itme>
              <el-button  id="login_btn" type="primary" @click="login" >Facility Login</el-button>
            </el-form-itme>
            <hr>
            <div id="hint_text" @click="$router.push('/resetpassword')"><p>Forgotten account?</p></div>
          </div>


      </div>
    </div>
  </div>


</template>

<script>
import {Document, LocationFilled, MapLocation, Money, Setting, Lock, User} from '@element-plus/icons-vue'
import { ElForm } from 'element-plus'
import { ElButton } from 'element-plus'
import { ElFormItem } from 'element-plus'
import request from "@/utils/request";

import WisdomHeader from '../components/WisdomHeader.vue'

export default {
  name: "FacilityLoginView",
  components: {Money, MapLocation, LocationFilled, Document, Setting, Lock, User, WisdomHeader},
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

.logo_and_title img{
  width: 80px;
}
.logo_and_title h1{
  width: 300px;
}

.login_container{
  height: 640px;
}

.login_form{
  width: 540px;
  height: 540px;
  margin:20px auto;
  background-color: #f3f3f3;
  text-align: center;
  border-radius: 5px;
}

.login_form_header p{
  font-size: 28px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
  padding: 40px;
}

.login_account{
  margin-left: 40px;
}
.login_password{
  margin-left: 40px;
}
.login_form >>> .el-input__inner{
  height: 50px;
  width: 360px;
  border: 0px;
  padding-left: 10px;
  font-size: 18px;
  border-radius: 5px;
  outline-color: #bfa0c8;
}
#hint_text{
  margin-top: 20px;
}
#hint_text p{
  color: #bfbfbf;
}
#hint_text p:hover{
  color:#864a98;
}

.login_facility_confirm button{
  height: 44px;
  width: 240px;
  background-color: #bfa0c8;
  color: white;
  border: 0px;
  margin-top: 70px;
  margin-bottom: 70px;
  font-size: 18px;
  border-radius: 5px;
  cursor: pointer;
}
.login_facility_confirm button:hover{
  height: 44px;
  width: 240px;
  background-color:#f3f3f3;
  color: #bfa0c8;
  border: solid 2px  #bfa0c8;
  margin-top: 70px;
  margin-bottom: 70px;
  font-size: 18px;
  border-radius: 5px;
  cursor: pointer;
}
.login_facility_confirm button:active{
  height: 44px;
  width: 240px;
  background-color:#f3f3f3;
  color: #bfa0c8;
  border: solid 2px  #bfa0c8;
  margin-top: 70px;
  margin-bottom: 70px;
  font-size: 18px;
  border-radius: 5px;
  cursor: pointer;
}

/*.header{*/
/*  height: 70px;*/
/*  width: 292px;*/
/*  !* text-align: center; *!*/
/*  font-size:19px;*/
/*  text-align: right;*/
/*  line-height: 70px;*/
/*  background-image: url(../image/WechatIMG2010.png);*/
/*  background-size: 100px 70px;*/
/*  background-repeat: no-repeat;*/
/*  background-position: left center;*/
/*  margin: 70px auto 0;*/
/*}*/

</style>