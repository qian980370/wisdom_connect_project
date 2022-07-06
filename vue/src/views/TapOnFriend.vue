<template>
<!--  <div class="header">-->
<!--    <h2>wisdom connect</h2>-->
<!--  </div>-->

  <div style="width: 100%; height: 120vh;  overflow: hidden">

    <div class="logo_and_title">
      <table>
        <tr>
          <td><img  src="../image/logo.png" alt="logo"></td>
          <td><h1>Wisdom Connect</h1></td>
        </tr>
      </table>
    </div>

    <div class="myprofile_container">
      <div class="myprofile_form">
        <div class="myprofile_form_header" style="margin: 30px auto"><p>Hi {{ username }}!</p></div>

        <hr>

        <div class="myprofile_addfriend">
          <!-- <input type="input" placeholder="add a friend"/></td> -->
          <button style="margin: 20px auto" @click="next()">Add Firends</button>
        </div>

        <div class="myprofile_display_container">
          <!--------Table------>
          <div class="myprofile_talk_to_sb_new" >
            <table>
              <tr>
                <td><img src="../image/flower1.png"></td>
                <td><p>Talk to someone new</p></td>
              </tr>
            </table>
          </div>

          <div class="myprofile_display_content" v-for="item in friendTableData">
            <table>
              <tr >
                <td><img :src="item.url"></td>
                <td><p>{{ item.username }}</p></td>
              </tr>
            </table>
          </div>



        </div>

        <div class="myprofile_switch">
          <table>
            <tr>
              <td><button id="videocall_btn">Make a Video Call</button></td>
              <td><button id="me_btn" @click="this.$router.push('/personal')">Me</button></td>
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
  name: "TapOnFriend",
  data() {
    return {
      username: null,
      friendTableData: [],
      profile: localStorage.getItem("profile") ? JSON.parse(localStorage.getItem("profile")) : null,
    }
  },
  created() {
    this.load();
    this.username = this.profile.username;
  },
  methods: {

    refreshProfile() {
      this.profile = localStorage.getItem("profile") ? JSON.parse(localStorage.getItem("profile")) : {}
      // console.log(this.profile);
      this.privacy = this.profile.privacy;
    },
    load(){
      this.refreshProfile();
      this.getAllFriends();
    },
    next(){
      this.$router.push('/addfriend')
    },
    getAllFriends(){
      request.get("/profile/friendList", {
        params: {
          profileID: this.profile.id,
        }
      }).then(res =>{
        console.log(res);
        this.friendTableData = res.data;
      })
    },
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
/*------------My Profile---------------*/
.myprofile_container{
  /* background: url('jack-finnigan-M9EctVUPrp4-unsplash.jpg') no-repeat center center fixed; */

  height: 1000px;
  /* background-color: #f3e6f7; */
  /* padding: 50px; */
  /* background-color: azure; */
}

.myprofile_form{
  width: 540px;
  height: 800px;
  /* border: 2px solid red; */
  margin:20px auto;
  text-align: center;
  border:solid 2px;
  border-color:#864a98;
  border-radius: 5px;
}
.myprofile_form_header p{
  font-size: 28px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
}

.myprofile_display_container{
  width: 440px;
  overflow-y:auto;
  overflow-x: hidden;
  height: 460px;
  border: solid 2px #bfa0c8;
  border-radius: 5px;
  margin-left: 50px;
}

.myprofile_display_container::-webkit-scrollbar{
  width: 10px;
}
.myprofile_display_container::-webkit-scrollbar-thumb{
  background-color: #bfa0c8;
  border-radius: 5px;
}
.myprofile_display_container::-webkit-scrollbar-button{
  display: none;
}
.myprofile_display_container::-webkit-scrollbar-track{
  background-color: #f3f3f3;
}


.myprofile_display{
  height: 480px;
  width: 400px;
  margin-left: 70px;
  border:dotted;
  border-radius: 5px;
  border-width:2px;
  border-color: #864a98;
}

.myprofile_display_content{
  width: 400px;
  height: 120px;
  background-color:#f3f3f3;
  margin-bottom: 10px;
  margin-left: 15px;
  margin-top: 10px;
  cursor: pointer;
}
.myprofile_display_content:hover{
  width: 400px;
  height: 120px;
  border: solid 2px #bfa0c8;
  background-color:#f3f3f3;
  margin-bottom: 10px;
  margin-left: 15px;
  margin-top: 10px;
  cursor: pointer;
}
.myprofile_display_content img{
  width: 100px;
  height: 100px;
}

.myprofile_display_content td{
  width: 180px;
  height: 120px;
}
.myprofile_display_content p{
  font-size: 18px;
  color:#864a98;
  font-weight: bold;
}

.myprofile_talk_to_sb_new{
  width: 400px;
  height: 120px;
  background-color:#f3e6f7;
  margin-bottom: 10px;
  margin-left: 15px;
  margin-top: 20px;
  cursor: pointer;
}
.myprofile_talk_to_sb_new:hover{
  width: 400px;
  height: 120px;
  border:solid 2px #bfa0c8;
  background-color:#f3e6f7;
  margin-bottom: 10px;
  margin-left: 15px;
  margin-top: 20px;
  cursor: pointer;
}
.myprofile_talk_to_sb_new img{
  width: 100px;
  height: 100px;
}

.myprofile_talk_to_sb_new td{
  width: 180px;
  height: 120px;
}

.myprofile_talk_to_sb_new p{
  font-size: 16px;
  color:#864a98;
  font-weight: bold;
}


.myprofile_addfriend{
  height: 50px;
  margin-bottom: 30px;
}

.myprofile_addfriend button {
  height: 42px;
  width: 430px;
  background-color: white;
  border: solid 2px;
  margin-left: 6px;
  margin-top: 15px;
  font-size: 18px;
  color:#bfa0c8;
  border-radius: 5px;
  cursor: pointer;
}
.myprofile_addfriend button:active {
  height: 42px;
  width: 430px;
  background-color: #bfa0c8;
  border: solid 2px;
  margin-left: 6px;
  margin-top: 15px;
  font-size: 18px;
  color:white;
  border-radius: 5px;
  cursor: pointer;
}

.myprofile_switch{
  margin-left: 50px;
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
  cursor: pointer;
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
#me_btn{
  height: 44px;
  width: 175px;
  background-color: #bfa0c8;
  color: white;
  border: 0px;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
  cursor: pointer;
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

</style>
