<template>

  <head>
    <meta charset="UTF-8">
    <title>Me</title>
  </head>

  <body>

  <div id="logo_and_title">
    <table>
      <tr>
        <td width="100px"><img  src="../image/logo.png" alt="logo" width="80px"></td>
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
                <td><img :src="icon"></td>
                <td><p>{{username}}</p></td>
              </tr>
            </table>

        </div>

        <div class="me_display_content" @click="$router.push('/interestlist')">
          <p>Interests</p>
        </div>

        <div class="me_display_content" @click="$router.push('/blockedlist')">
          <p>Blocked Lists</p>
        </div>

        <div class="me_display_content" @click="$router.push('/second')">
          <p style="color: white;">Logout</p>
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
  </body>


</template>

<script>
import request from "@/utils/request";

export default {
  name: "Personal",
  data(){
    return{
      user : localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null,
      userinfo :null,
      userid:'',
      username:'',
      icon:'',
    }
  },
  created() {
    this.load();
    this.userid = this.$route.params.userId
    this.username = this.$route.params.userName
    this.icon=this.$route.params.icon

    // console.log(this.user);
  },
  methods: {
    refreshUser() {
      this.user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    },
    // handleClose(){
    //   this.dialogVisible = false;
    // },
    load() {
      this.refreshUser();
      request.get("/profile/page", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          query: this.query
        }
      }).then(res => {
        console.log(res);
        this.userinfo = res.data.records;
      })
    },

  }
}

</script>


<style scoped>
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

.me_switch{
  margin-left: 50px;
}
#logo_and_title{
  width: 400px;
  height: 120px;
  text-align: center;
  padding-left: 37%;
  padding-top: 2%;
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
</style>


