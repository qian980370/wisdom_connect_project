<template>

  <div style="width: 100%; height: 120vh;  overflow: hidden">

    <div class="logo_and_title">
      <table>
        <tr>
          <td><img  src="../image/logo.png" alt="logo"></td>
          <td><h1>Wisdom Connect</h1></td>
        </tr>
      </table>
    </div>

    <div class="me_container">
      <div class="me_form">
        <div class="me_form_header"><p>Me</p></div>
        <hr>

        <div class="me_display_container">
          <!--------Table------>
          <div class="me_profile" @click="$router.push('/personalchange')">

            <table>
              <tr>
                <td><img :src="profile.icon"></td>
                <td><p>{{profile.username}}</p></td>
              </tr>
            </table>

          </div>

          <div class="me_display_content" @click="$router.push('/interestlist')">
            <p>Interests</p>
          </div>

          <div class="me_display_content" @click="$router.push('/blockedlist')">
            <p>Blocked Lists</p>
          </div>

          <div class="me_display_content_logout" @click="logout()">
            <p>Logout</p>
          </div>
        </div>

        <div class="me_switch">
          <table>
            <tr>
              <td><button id="videocall_btn" @click="$router.push('/videocall')">Make a Video Call</button></td>
              <td><button id="me_btn">Me</button></td>
            </tr>
          </table>
        </div>
      </div>

    </div>

  </div>

</template>

<script>
import request from "@/utils/request";

export default {
  name: "Personal",
  data(){
    return{
      profile: localStorage.getItem("profile") ? JSON.parse(localStorage.getItem("profile")) : null,
    }
  },
  created() {
    this.load();

    // console.log(this.user);
  },
  methods: {
    refreshProfile() {
      this.profile = localStorage.getItem("profile") ? JSON.parse(localStorage.getItem("profile")) : {}
      console.log(this.profile);
      this.privacy = this.profile.privacy;
    },
    load(){
      this.refreshProfile();
    },
    cleanLocalStorage(){
      localStorage.clear();
    },
    logout(){
      this.cleanLocalStorage();
      this.$router.push('/facilitylogin');
    }

  }
}

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

/*----------------Me---------------------*/
.me_container{
  /* background: url('jack-finnigan-M9EctVUPrp4-unsplash.jpg') no-repeat center center fixed; */

  height: 1000px;
  /* background-color: #f3e6f7; */
  /* padding: 50px; */
  /* background-color: azure; */
}

.me_form{
  width: 540px;
  height: 700px;
  /* border: 2px solid red; */
  margin:20px auto;
  text-align: center;
  border:solid 2px;
  border-color:#864a98;
  border-radius: 5px;
}
.me_form_header{
  margin-top: 30px;
  margin-bottom: 30px;
}
.me_form_header p{
  font-size: 28px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
}

.me_display_container{
  width: 440px;
  height: 380px;
  margin-left: 50px;
}

.me_display{
  height: 360px;
  width: 400px;
  margin-left: 70px;
  border:dotted;
  border-radius: 5px;
  border-width:2px;
  border-color: #864a98;
}
.me_profile{
  width: 400px;
  height: 120px;
  background-color:#f3f3f3;
  margin-bottom: 10px;
  margin-left: 15px;
  margin-top: 30px;
  cursor: pointer;
}
.me_profile img{
  width: 100px;
  height: 100px;
}

.me_profile td{
  width: 240px;
  height: 120px;
}

.me_profile p{
  font-size: 18px;
  color:#864a98;
  font-weight: bold;
}

.me_display_content{
  width: 400px;
  height: 50px;
  background-color:#e8dded;
  border-radius: 5px;
  margin-left: 15px;
  margin-top: 30px;
  cursor: pointer;
}

.me_display_content p{
  font-size: 18px;
  color:#864a98;
  font-weight: bold;
  text-align: center;
  padding-top: 10px;
}
.me_display_content p:hover{
  font-size: 18px;
  color:white;
  background-color:#e8dded;
  font-weight: bold;
  text-align: center;
  padding-top: 10px;
}
.me_display_content_logout{
  width: 400px;
  height: 50px;
  background-color:#e8dded;
  border-radius: 5px;
  margin-left: 15px;
  margin-top: 30px;
  cursor: pointer;
}
.me_display_content_logout p{
  font-size: 18px;
  color:white;
  font-weight: bold;
  text-align: center;
  padding-top: 10px;
}
.me_display_content_logout p:hover{
  font-size: 18px;
  color: #864a98;
  font-weight: bold;
  text-align: center;
  padding-top: 10px;
}


.me_switch{
  margin-left: 50px;
}
#me_btn {
  height: 44px;
  width: 175px;
  background-color: #bfa0c8;
  color: white;
  border: 0px;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
#me_btn:hover{
  height: 44px;
  width: 175px;
  background-color: white;
  color: #bfa0c8;
  border: solid 2px #bfa0c8;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
#me_btn:active{
  height: 44px;
  width: 175px;
  background-color: white;
  color: #bfa0c8;
  border: solid 2px #bfa0c8;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
#videocall_btn{
  height: 44px;
  width: 255px;
  background-color: #bfa0c8;
  color: white;
  border: 0px;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
#videocall_btn:hover{
  height: 44px;
  width: 255px;
  background-color: white;
  color: #bfa0c8;
  border: solid 2px #bfa0c8;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
#videocall_btn:active{
  height: 44px;
  width: 255px;
  background-color: white;
  color: #bfa0c8;
  border: solid 2px #bfa0c8;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
</style>


