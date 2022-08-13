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
                <td>
                  <p class="myprofile_display_content_status_online">online</p>
                  <p class="myprofile_display_content_status_available">available</p>
                  <button id="call_a_friend_btn"></button>
                </td>
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
      websocket: null,
      webSocketURL: 'ws://ericbackend.azurewebsites.net/chat',
      oncall: false,
      roomId: null,
      dialogChatVisible1: false,
      dialogChatVisible2: false,
      disableAccept : false,
      state: 'no call',

      username: null,
      friendTableData: [],
      profile: localStorage.getItem("profile") ? JSON.parse(localStorage.getItem("profile")) : null,
    }
  },
  watch:{
    $route(to,from){
      this.websocket.close()
      if (this.oncall){
        this.chatClose()
      }
    }
  },

  created() {
    this.load();

    this.initialWebSocket()
  },


  destroyed() {
    this.websocket.close()
    if (this.oncall){
      console.log("des")
      this.chatClose()
    }
  },
  methods:{
    searchUser(){
      request.get("/profile/searchProfile", {
        params: {
          profileID: this.profile.id,
          query: this.query
        }
      }).then(res =>{
        // console.log(res);
        this.randomFriendTableData = res.data;
      })
    },
    initialWebSocket(){
      this.webSocketURL = 'ws://ericbackend.azurewebsites.net/chat/' + this.user.token + '/' + this.profile.id
      if(typeof WebSocket === 'undefined'){
        return console.log('your browser is not support websocket')
      }
      console.log(this.webSocketURL)
      this.websocket = new WebSocket(this.webSocketURL)
      this.websocket.onmessage = this.websocketOnMessage
      this.websocket.onopen = this.websocketOnOpen
      this.websocket.onerror = this.websocketOnError
      this.websocket.onclose = this.websocketClose

    },
    websocketOnOpen() {


    },
    chatAccept(){
      this.disableAccept = true
      let callForm = {};
      callForm.sender = this.profile.id
      callForm.receiver = this.roomId
      this.state = 'calling';
      callForm.message = "1"

      this.websocketSend(JSON.stringify(callForm))
    },
    chatClose(){
      let callForm = {};
      callForm.sender = this.profile.id
      callForm.receiver = this.roomId
      callForm.message = "2"

      this.websocketSend(JSON.stringify(callForm))
      this.state = 'no call';
      this.oncall = false;
      this.roomId = null;
      this.dialogChatVisible1 = false;
      this.dialogChatVisible2 = false;
    },
    websocketOnError() {
      // 连接建立失败重连
      this.initialWebSocket()
    },
    websocketOnMessage(e) {
      // 数据接收
      let res = JSON.parse(e.data)
      console.log('receive: ', res)
      if (res.isSystem){ //friend online offline notifications
        this.$message({
          type: "success",
          message: res.message + "; friend: " + res.fromName
        })
      }else {
        if (res.isGetRoomID){ // room build
          this.oncall = true;

          this.roomId = res.message;


          //get room information
          request.get("/chatcontroller/room", {
            params: {
              roomID: this.roomId,
            }
          }).then(res2 =>{
            if(res2.data.holderone === this.profile.id){ //current user is chat sender
              this.dialogChatVisible2 = true
            }else { //current user is chat receiver
              this.dialogChatVisible1 = true
            }
            this.state = 'on call';
          })
        }else {
          if(res.message == 1){
            this.state = 'calling';
          }else {
            this.$message({
              type: "error",
              message: "your friend closed your call"
            })

            this.state = 'no call';
            this.oncall = false;
            this.roomId = null;
            this.dialogChatVisible1 = false;
            this.dialogChatVisible2 = false;
          }
        }
      }
      this.getAllFriends()
    },
    websocketSend(Data) {

      this.websocket.send(Data)
    },
    websocketClose(e) {

      console.log('close connection', e)
    },

    callFriend(id){
      //console.log(id)

      let callForm = {};
      callForm.sender = this.profile.id
      callForm.receiver = id
      callForm.message = "need url"

      this.websocketSend(JSON.stringify(callForm))


    },

    created() {
    this.load();
    this.username = this.profile.username;
      this.initialWebSocket()
  },
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
.myprofile_display_content_status_online{
  font-size: 6px;
  color: green;
}
.myprofile_display_content_status_offline{
  font-size: 6px;
  color: red;
}
.myprofile_display_content_status_available{
  font-size: 6px;
}
.myprofile_display_content_status_unavailable{
  font-size: 6px;
  color: gray;
}
#call_a_friend_btn{
  height: 30px;
  width: 30px;
  z-index: 20px;
  left: 96px;
  background: url(../image/call_icon.png) no-repeat;
  background-size: 28px 28px;
  border: none;
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
