
<template>
<WisdomHeader></WisdomHeader>
  <div class="addprofiles_container">
    <div class="addprofiles_form">
      <div class="addprofiles_form_title"><p>Personal Info Change</p></div>

      <el-form v-model="form" label-width="80px" style="margin-left: 30px;">
        <el-form-item label="Upload Profile Icon">
          <el-upload
              class="avatar-uploader"
              action="http://localhost:9090/file/upload"
              :show-file-list="false"
              :on-change="handleLicensePreview"
              :before-upload="beforeLicenseUpload"
              :on-success="handleAvatarSuccess"

          >
            <img v-if="profile.icon != null" :src="profile.icon" class="avatar" />
<!--            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>-->
          </el-upload>
        </el-form-item>

        <el-form-item label="Profile Name">
          <el-input v-model="profile.username" />
        </el-form-item>
        <el-form-item label="Privacy">
          <el-radio-group v-model="profile.privacy">
            <el-radio :label=1>hide</el-radio>
            <el-radio :label=0>open</el-radio>
          </el-radio-group>

        </el-form-item>

        <el-form-item label="Age">
          <el-input v-model="profile.age" />
        </el-form-item>

        <el-form-item label="Gender">
          <el-radio-group v-model="profile.gender">
            <el-radio label="male">Male</el-radio>
            <el-radio label="female">Female</el-radio>
            <el-radio label="secret">Secret</el-radio>
          </el-radio-group>

        </el-form-item>

        <el-form-item>
          <div>
            <el-button type="primary" @click="onSubmit" class="form-button">Change</el-button>
          </div>

        </el-form-item>
        <el-form-item>
          <div>
            <el-button @click="$router.push('/personal')" class="form-button">Cancel</el-button>
          </div>
        </el-form-item>
      </el-form>

    </div>

  </div>

</template>

<script>
import WisdomHeader from '../components/WisdomHeader.vue'
import request from "@/utils/request";
import { Plus } from '@element-plus/icons-vue';


export default {
  name: "PersonalChange",
  components:{WisdomHeader,Plus},
  data(){
    return {
      profile:{},

    };
  },
  created(){
    this.load();
  },
  methods:{
    refreshProfile() {
      this.profile = localStorage.getItem("profile") ? JSON.parse(localStorage.getItem("profile")) : {}
      console.log(this.profile);
      // this.privacy = this.profile.privacy;
    },
    load(){
      this.refreshProfile();
    },
    handleLicensePreview(res){
      const isLt2M = res.size / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.$message.error('Avatar picture size can not exceed 2MB!');
      } else {
        //store file
        // this.img = URL.createObjectURL(res.raw);
      }
    },
    handleAvatarSuccess(res){
      // console.log(res);
      this.form.icon = res;

      this.img = res;
      console.log(this.img)
      this.load();
      this.$message({
        type: "success",
        message: "Successfully upload file"
      })
    },
    beforeLicenseUpload(res){
      //console.log(res);
      if (res.type !== 'image/jpeg' && res.type !== 'image/png' && res.type !== 'image/jpg') {
        this.$message({
          type: "error",
          message: 'Avatar picture must be JPG format!'
        })
        return false
      } else if (res.size / 1024 / 1024 > 2) {
        this.$message({
          type: "error",
          message: 'Avatar picture size can not exceed 2MB!'
        })
        return false
      }
      return true

    },
    onSubmit(){
      console.log('mdmd')
      console.log(this.profile)
      if (this.profile.id){
        console.log("update");
        request.put("/profile", this.profile).then(res =>{
          // console.log(res);
          if (res.code === '200'){
            localStorage.setItem("profile", JSON.stringify(this.profile));
            console.log(res)
            this.$message({
              type: "success",
              message: "Successfully update user"
            })

          }else{
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.$router.push('/personal')
          // this.load();
          // this.dialogProfileVisible=false;
        });
      }else{
        console.log("create");
        console.log(this.profile);
        if (this.profile.level !== 1 && this.profile.level !== 2){
          this.profile.level = 1;
        }
        console.log(this.profile);
        request.post("/profile/create", this.profile).then(res =>{
          console.log(res);
          if (res.code === '200'){
            this.$message({
              type: "success",
              message: "Successfully add hobby"
            })
          }else{
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.$router.push('/personal')

          // this.load();
          // this.dialogProfileVisible=false;
        });
      }
    },

  }
}
</script>

<style scoped>
.addprofiles_container{
  /* background: url('jack-finnigan-M9EctVUPrp4-unsplash.jpg') no-repeat center center fixed; */

  height: 1300px;

}



.addprofiles_form{
  width: 540px;
  height: 1160px;
  /* border: 2px solid red; */
  margin:20px auto;
  border:solid 2px;
  border-color: #864a98;
  text-align: center;
  border-radius: 5px;
}

.addprofiles_form >>> .el-input__inner{
  height: 50px;
  width: 360px;
  border: 0px;
  padding-left: 10px;
  font-size: 18px;
  border-radius: 5px;
  outline-color: #bfa0c8;
}
.addprofiles_form_title{
  height: 100px;
  margin-top: 40px;
  font-size: 28px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
  padding: 10px;
}
.addprofiles_upload_table{
  height: 120px;
}
.addprofiles_upload_img{
  height: 120px;
  width: 120px;
  margin-left: 60px;
  margin-bottom: 20px;
  border:dotted;
  border-radius: 5px;
  border-width:2px;
  border-color: #a777b5;
}
.addprofiles_upload_img img{
  height: 55px;
  width: 60px;
  padding-top: 30px;
}
.Name{
  height: 40px;
  width: 240px;
  border: dotted 2px;
  border-color: #a777b5;
  font-size: 18px;
  border-radius: 5px;
  margin-bottom: 20px;

}
.Age{
  height: 40px;
  width: 240px;
  border: dotted 2px;
  border-color: #a777b5;
  font-size: 18px;
  border-radius: 5px;
  margin-bottom: 20px;
}

.addprofiles_interests_table{
  margin-left: 50px;

}

.addprofiles_interests_table tr{
  width: 480px;
  height: 80px;
}

.addprofiles_interests_table td{
  width: 140px;
  height: 120px;
}
.addprofiles_interests_table img{
  width: 120px;
  height: 120px;
}

#addprofiles_interests_tag p{
  font-size: 14px;
}

.addprofiles_interests_btn_table{
  width: 400px;
  height: 60px;
  margin-left: 70px;
  margin-top: 30px;
  margin-bottom: 30px;
}
.addprofiles_interests_btn_table td{
  width: 240px;
  height: 60px;
  margin-left: 50px;
}

.addprofiles_interests_btn_table button{
  width: 160px;
  height: 30px;
  color: #864a98;
  border: solid 1px;
  border-color: #864a98;
  border-radius: 5px;
  background-color: #f3f3f3;
}

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



.form-button {
  height: 44px;
  width: 240px;
  background-color: #bfa0c8;
  color: white;
  border: 0px;
  margin-top: 30px;
  font-size: 18px;
  border-radius: 5px;
  margin-left: 40px;
}

</style>
