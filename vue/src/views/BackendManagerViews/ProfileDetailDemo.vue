<template>
  <div style="padding: 10px; width: 100%">
    <div style="margin: 10px 0">
      <span>current profile: {{profile.username}}</span>
    </div>

    <!--privacy-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="setPrivacy">setPrivacy</el-button>
      privacy state: {{this.privacy}}
    </div>

    <!--table of data-->
    <!--Friend List-->
    <div style="margin: 10px 0">
      <span>Your Friends</span>
    </div>
    <el-table :data="friendTableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID">
      </el-table-column>
      <el-table-column prop="owner" label="Owner ID">
      </el-table-column>
      <el-table-column prop="username" label="Profile Username">
      </el-table-column>

      <el-table-column label="Header">
        <template #default="imgScope">
          <el-image style="width: 100px; height: 100px" :src="imgScope.row.icon" />
        </template>
      </el-table-column>

      <el-table-column prop="privacy" label="privacy">
      </el-table-column>

      <el-table-column prop="age" label="Age">
      </el-table-column>

      <el-table-column prop="gender" label="Gender">
      </el-table-column>

      <el-table-column prop="online" label="Online">
      </el-table-column>

      <el-table-column prop="oncall" label="Oncall">
      </el-table-column>

      <el-table-column prop="lsatLogin" label="Lsat Login Time">
      </el-table-column>

      <el-table-column fixed="right" label="Operations" width="200px">
        <template #default="scope">

          <el-popconfirm title="Are you sure to call this?" @confirm="callFriend(scope.row.id)">
            <template #reference>
              <el-button type="text" size="small" >Call</el-button>
            </template>
          </el-popconfirm>

          <el-popconfirm title="Are you sure to delete this?" @confirm="deleteFriend(scope.row.id)">
            <template #reference>
              <el-button type="text" size="small" >Delete</el-button>
            </template>
          </el-popconfirm>

          <el-popconfirm title="Are you sure to block this user?" @confirm="blockUser(scope.row.id)">
            <template #reference>
              <el-button type="text" size="small" >Block</el-button>
            </template>
          </el-popconfirm>
        </template>

      </el-table-column>

    </el-table>


    <!--Random Friend List-->
    <div style="margin: 10px 0">
      <span>Random Friends</span>
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="query" placeholder="please input information of file name to search" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 7px" @click="searchUser">Search</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="getRandomFriends">Random Friend</el-button>
    </div>
    <el-table :data="randomFriendTableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID">
      </el-table-column>
      <el-table-column prop="owner" label="Owner ID">
      </el-table-column>
      <el-table-column prop="username" label="Profile Username">
      </el-table-column>

      <el-table-column label="Header">
        <template #default="imgScope">
          <el-image style="width: 100px; height: 100px" :src="imgScope.row.icon" />
        </template>
      </el-table-column>

      <el-table-column prop="privacy" label="privacy">
      </el-table-column>

      <el-table-column prop="age" label="Age">
      </el-table-column>

      <el-table-column prop="gender" label="Gender">
      </el-table-column>

      <el-table-column prop="online" label="Online">
      </el-table-column>

      <el-table-column prop="oncall" label="Oncall">
      </el-table-column>

      <el-table-column prop="lsatLogin" label="Lsat Login Time">
      </el-table-column>

      <el-table-column fixed="right" label="Operations" width="200px">
        <template #default="scope">
          <el-popconfirm title="Send Friend Request?" @confirm="sendFriendRequest(scope.row.id)">
            <template #reference>
              <el-button type="text" size="small" >Add Friend</el-button>
            </template>
          </el-popconfirm>
          <el-popconfirm title="Are you sure to block this user?" @confirm="blockUser(scope.row.id)">
            <template #reference>
              <el-button type="text" size="small" >Block</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>

    </el-table>

    <!--Friend Request List-->
    <div style="margin: 10px 0">
      <span>Friends Request</span>
    </div>
    <el-table :data="friendRequestTableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID">
      </el-table-column>
      <el-table-column prop="owner" label="Owner ID">
      </el-table-column>
      <el-table-column prop="username" label="Profile Username">
      </el-table-column>

      <el-table-column label="Header">
        <template #default="imgScope">
          <el-image style="width: 100px; height: 100px" :src="imgScope.row.icon" />
        </template>
      </el-table-column>

      <el-table-column prop="privacy" label="privacy">
      </el-table-column>

      <el-table-column prop="age" label="Age">
      </el-table-column>

      <el-table-column prop="gender" label="Gender">
      </el-table-column>

      <el-table-column prop="online" label="Online">
      </el-table-column>

      <el-table-column prop="oncall" label="Oncall">
      </el-table-column>

      <el-table-column prop="lsatLogin" label="Lsat Login Time">
      </el-table-column>

      <el-table-column fixed="right" label="Operations" width="200px">
        <template #default="scope">
          <el-popconfirm title="Accept Friend Request?" @confirm="acceptFriendRequest(scope.row.id)">
            <template #reference>
              <el-button type="text" size="small" >Accept Friend</el-button>
            </template>
          </el-popconfirm>
          <el-popconfirm title="Reject Friend Request?" @confirm="deleteFriend(scope.row.id)">
            <template #reference>
              <el-button type="text" size="small" >Reject Friend</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>

    </el-table>

    <!--Block List-->
    <div style="margin: 10px 0">
      <span>Block List</span>
    </div>
    <el-table :data="blockTableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID">
      </el-table-column>
      <el-table-column prop="owner" label="Owner ID">
      </el-table-column>
      <el-table-column prop="username" label="Profile Username">
      </el-table-column>

      <el-table-column label="Header">
        <template #default="imgScope">
          <el-image style="width: 100px; height: 100px" :src="imgScope.row.icon" />
        </template>
      </el-table-column>

      <el-table-column prop="privacy" label="privacy">
      </el-table-column>

      <el-table-column prop="age" label="Age">
      </el-table-column>

      <el-table-column prop="gender" label="Gender">
      </el-table-column>

      <el-table-column prop="online" label="Online">
      </el-table-column>

      <el-table-column prop="oncall" label="Oncall">
      </el-table-column>

      <el-table-column prop="lsatLogin" label="Lsat Login Time">
      </el-table-column>

      <el-table-column fixed="right" label="Operations" width="200px">
        <template #default="scope">
          <el-popconfirm title="Unblock?" @confirm="unBlock(scope.row.id)">
            <template #reference>
              <el-button type="text" size="small" >Unblock</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>

    </el-table>

    <!--Hobby List-->
    <div style="margin: 10px 0">
      <span>Hobby List</span>
    </div>
    <el-table :data="hobbyTableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID">
      </el-table-column>

      <el-table-column prop="name" label="Hobby Name">
      </el-table-column>

      <el-table-column label="Hobby Img">
        <template #default="imgScope">
          <el-image style="width: 100px; height: 100px" :src="imgScope.row.icon" />
        </template>
      </el-table-column>

      <el-table-column prop="level" label="Hobby Level">
      </el-table-column>

      <el-table-column prop="tophobbyname" label="Top Hobby ID">
      </el-table-column>

      <el-table-column prop="icon" label="Img Url">
      </el-table-column>

      <el-table-column fixed="right" label="Operations" width="200px">
        <template #default="scope">
          <el-popconfirm title="Are you sure to delete this hobby?" @confirm="deleteHobby(scope.row.id)">
            <template #reference>
              <el-button type="text" size="small" >Delete</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>

    </el-table>

    <!--Random Hobby List-->
    <div style="margin: 10px 0">
      <span>Random Hobby List</span>
    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="getRandomHobbies">Random Hobby</el-button>
    </div>
    <el-table :data="randomHobbyTableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID">
      </el-table-column>

      <el-table-column prop="name" label="Hobby Name">
      </el-table-column>

      <el-table-column label="Hobby Img">
        <template #default="imgScope">
          <el-image style="width: 100px; height: 100px" :src="imgScope.row.icon" />
        </template>
      </el-table-column>

      <el-table-column prop="level" label="Hobby Level">
      </el-table-column>

      <el-table-column prop="tophobbyname" label="Top Hobby ID">
      </el-table-column>

      <el-table-column prop="icon" label="Img Url">
      </el-table-column>

      <el-table-column fixed="right" label="Operations" width="200px">
        <template #default="scope">
          <el-popconfirm title="Are you sure to add this?" @confirm="addHobby(scope.row.id)">
            <template #reference>
              <el-button type="text" size="small" >Add Hobby</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>

    </el-table>

    <el-dialog id="chatForm" v-model="dialogChatVisible1" :title=this.roomId width="30%" :before-close="chatClose">
      <div style="width: 85%">

        <el-form>
          <el-form-item :label="this.state">

          </el-form-item>
          <el-form-item :label="this.message">
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="chatAccept" :disabled="this.disableAccept">Accept</el-button>
            <el-button @click="chatClose">Refuse</el-button>
          </el-form-item>
        </el-form>

      </div>
    </el-dialog>

    <el-dialog id="chatForm2" v-model="dialogChatVisible2" :title=this.roomId width="30%" :before-close="chatClose">
      <div style="width: 85%">

        <el-form>
          <el-form-item :label="this.state">

          </el-form-item>
          <el-form-item :label="this.message">
          </el-form-item>
          <el-form-item>

            <el-button @click="chatClose">Close</el-button>
          </el-form-item>
        </el-form>

      </div>
    </el-dialog>

  </div>
</template>

<script>
import request from "@/utils/request";
import router from "@/router";
import { Plus } from '@element-plus/icons-vue';


export default {
  name: "ProfileDetailView",
  components: {
    Plus
  },
  data(){
    return{
      message: null,
      conferenceRoomID: null,
      conference: null,
      pin: null,
      websocket: null,
      //webSocketURL: 'ws://ericbackend.azurewebsites.net/chat/',
      webSocketURL: 'ws://localhost:9090/chat/',
      oncall: false,
      roomId: null,
      dialogChatVisible1: false,
      dialogChatVisible2: false,
      disableAccept : false,
      state: 'no call',

      privacy: '123',
      form: {},
      query: null,
      search: '',
      //store the friend list data
      friendTableData:[
      ],
      //store the friend request data
      friendRequestTableData:[
      ],
      //store the random friend data
      randomFriendTableData:[
      ],
      //store the hobby list data
      hobbyTableData:[
      ],
      //store the random hobby data
      randomHobbyTableData:[
      ],
      //store the block list data
      blockTableData:[
      ],
      profile : localStorage.getItem("profile") ? JSON.parse(localStorage.getItem("profile")) : null,
      user : localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null,
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
    this.releaseConferenceRoom();
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

    //initial websocket state
    initialWebSocket(){
      //send token and profile id to backend, please follow the below format
      // this.webSocketURL = 'ws://ericbackend.azurewebsites.net/chat/' + this.user.token + '/' + this.profile.id
      this.webSocketURL = 'ws://localhost:9090/chat/' + this.user.token + '/' + this.profile.id
      // check the supporting of your browser
      if(typeof WebSocket === 'undefined'){
        return console.log('your browser is not support websocket')
      }
      // debug log
      console.log(this.webSocketURL)

      // store websocket attribute
      this.websocket = new WebSocket(this.webSocketURL)
      this.websocket.onmessage = this.websocketOnMessage
      this.websocket.onopen = this.websocketOnOpen
      this.websocket.onerror = this.websocketOnError
      this.websocket.onclose = this.websocketClose

    },
    websocketOnOpen() {


    },
    // click accept when get a call request
    chatAccept(){
      this.disableAccept = true

      // call form is used to build websocket request
      let callForm = {};
      // sender is current user's profile id
      callForm.sender = this.profile.id
      // receiver is used to save the chat room id
      callForm.receiver = this.roomId
      // update your state
      this.state = 'calling';
      // message = 1 is means this is a call acceptance websocket request
      callForm.message = "1"

      this.websocketSend(JSON.stringify(callForm))
    },
    chatClose(){

      // call form is used to build websocket request
      let callForm = {};
      // sender is current user's profile id
      callForm.sender = this.profile.id
      // receiver is used to save the chat room id
      callForm.receiver = this.roomId
      // message = 2 is means this is a call disacceptance websocket request
      callForm.message = "2"
      this.releaseConferenceRoom();
      this.websocketSend(JSON.stringify(callForm))
      this.state = 'no call';
      this.oncall = false;
      this.roomId = null;
      this.message = null;
      this.dialogChatVisible1 = false;
      this.dialogChatVisible2 = false;
    },
    websocketOnError() {
      // 连接建立失败重连
      this.initialWebSocket()
    },
    websocketOnMessage(e) {
      // receive websocket reply or broadcast message
      let res = JSON.parse(e.data)
      console.log('receive: ', res)
      if (res.isSystem){ //friend online offline notifications; broadcast message
        this.$message({
          type: "success",
          message: res.message + "; friend: " + res.fromName
        })
      }else {
        if (res.isGetRoomID){ // room build, receive chat room id; Be attention Chat room is different with pxiep conference node room
          this.oncall = true;

          this.roomId = res.message;


          //get room information
          request.get("/chatcontroller/room", {
            params: {
              roomID: this.roomId,
            }
          }).then(res2 =>{

            this.message = res2.data.message;

            if(res2.data.holderone === this.profile.id){ //current user is chat sender
              this.dialogChatVisible2 = true
            }else { //current user is chat receiver
              this.dialogChatVisible1 = true
            }
            this.state = 'on call';
          })
        }else { //accept or refuse call feedback message
          if(res.message == 1){ //accept
            this.state = 'calling';
          }else { //refuse
            this.$message({
              type: "error",
              message: "your friend closed your call"
            })
            this.releaseConferenceRoom();
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

    // get conference room
    getConferenceRoom(id){
      request.get("/conference/getRoom", {
        params: {
          profileID: this.profile.id,
        }
      }).then(res =>{
        // console.log(res);
        if (res.code === '200') {
          this.conferenceRoom = res.data
          this.conference = res.data.conferenceid;
          this.pin = res.data.pin;
          this.$message({
            type: "success",
            message: "successfully get a room"
          })
          //console.log(this.conferenceRoom);
          let callForm = {};
          callForm.sender = this.profile.id;
          callForm.receiver = id;
          callForm.message = "node-" + this.conference + ";pin-" + this.pin;
          this.websocketSend(JSON.stringify(callForm))
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    },


    // get conference room
    releaseConferenceRoom(){
      let releaseRoomRequestForm;
      releaseRoomRequestForm = {};

      releaseRoomRequestForm.profileID = this.profile.id;
      releaseRoomRequestForm.targetID = null;
      request.post("/conference/releaseRoom", releaseRoomRequestForm).then(res =>{
        // console.log(res);
        if (res.code === '200') {
          this.conferenceRoom = null;
          this.conference = null;
          this.pin = null;
          this.$message({
            type: "success",
            message: "successfully release a room"
          })

        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    },

    callFriend(id){
      //console.log(id)




      this.getConferenceRoom(id);
      //因为异步问题，所以websocket部分移交至this.getConferenceRoom()内部执行



    },


    refreshProfile(){
      this.profile = localStorage.getItem("profile") ? JSON.parse(localStorage.getItem("profile")) : {}
      // console.log(this.profile);
      this.privacy = this.profile.privacy;
    },

    // handleClose(){
    //   this.dialogVisible = false;
    // },
    load(){
      this.refreshProfile();
      this.getAllFriends();
      this.getRandomFriends();
      this.getFriendsRequest();
      this.getAllBlock();
      this.getAllHobbies();
      this.getRandomHobbies();
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
    getAllHobbies(){
      request.get("/hobby/hobbyList", {
        params: {
          profileID: this.profile.id,
        }
      }).then(res =>{
        // console.log(res);
        this.hobbyTableData = res.data;
      })
    },
    getRandomHobbies(){
      request.get("/hobby/randomHobbies", {
        params: {
          profileID: this.profile.id,
        }
      }).then(res =>{
        // console.log(res);
        this.randomHobbyTableData = res.data;
      })
    },

    addHobby(id){
      let friendRequestForm;
      friendRequestForm = {};

      friendRequestForm.profileID = this.profile.id;
      friendRequestForm.targetID = id;

      request.post("/hobby/addHobby", friendRequestForm).then(res => {
        if (res.code === '200') {
          this.$message({
            type: "success",
            message: "successfully add hobby"
          })
          this.load()
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    },

    deleteHobby(id){
      //console.log(id)
      let friendRequestForm;
      friendRequestForm = {};

      friendRequestForm.profileID = this.profile.id;
      friendRequestForm.targetID = id;

      request.delete("/hobby/hobbyDelete", {data: friendRequestForm}).then(res => {
        if (res.code === '200') {
          this.$message({
            type: "success",
            message: "successfully delete hobby"
          })
          this.load()
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }

      })
    },

    getAllBlock(){
      request.get("/profile/blockList", {
        params: {
          profileID: this.profile.id,
        }
      }).then(res =>{
        // console.log(res);
        this.blockTableData = res.data;
      })
    },

    getFriendsRequest(){
      request.get("/profile/friendsRequest", {
        params: {
          profileID: this.profile.id,
        }
      }).then(res =>{
        // console.log(res);
        this.friendRequestTableData = res.data;
      })
    },

    getRandomFriends(){
      request.get("/profile/randomFriends", {
        params: {
          profileID: this.profile.id,
        }
      }).then(res =>{
        // console.log(res);
        this.randomFriendTableData = res.data;
      })
    },

    acceptFriendRequest(id){
      let friendRequestForm;
      friendRequestForm = {};

      friendRequestForm.profileID = this.profile.id;
      friendRequestForm.targetID = id;

      request.put("/profile/acceptFriendRequest", friendRequestForm).then(res => {
        if (res.code === '200') {
          this.$message({
            type: "success",
            message: "successfully accept request"
          })
          this.load()
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    },

    sendFriendRequest(id){
      let friendRequestForm;
      friendRequestForm = {};

      friendRequestForm.profileID = this.profile.id;
      friendRequestForm.targetID = id;

      request.post("/profile/addFriend", friendRequestForm).then(res => {
        if (res.code === '200') {
          this.$message({
            type: "success",
            message: "successfully send friend request"
          })
          this.load()
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })


    },

    deleteFriend(id){
      //console.log(id)
      let friendRequestForm;
      friendRequestForm = {};

      friendRequestForm.profileID = this.profile.id;
      friendRequestForm.targetID = id;

      request.delete("/profile/friendDelete", {data: friendRequestForm}).then(res => {
        if (res.code === '200') {
          this.$message({
            type: "success",
            message: "successfully delete or reject"
          })
          this.load()
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }

      })
    },
    blockUser(id){
      //console.log(id)
      let friendRequestForm;
      friendRequestForm = {};
      friendRequestForm.profileID = this.profile.id;
      friendRequestForm.targetID = id;
      request.post("/profile/blockUser", friendRequestForm).then(res => {
        if (res.code === '200') {
          this.$message({
            type: "success",
            message: "successfully block"
          })
          this.load()
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()
      })
    },
    unBlock(id){
      //console.log(id)
      let friendRequestForm;
      friendRequestForm = {};

      friendRequestForm.profileID = this.profile.id;
      friendRequestForm.targetID = id;

      request.delete("/profile/unBlock", {data: friendRequestForm}).then(res => {
        if (res.code === '200') {
          this.$message({
            type: "success",
            message: "successfully unblock"
          })
          this.load()
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }

      })
    },
    setPrivacy(){
      let friendRequestForm;
      friendRequestForm = {};

      friendRequestForm.profileID = this.profile.id;
      request.put("/profile/updatePrivacy", friendRequestForm).then(res => {
        if (res.code === '200') {
          this.$message({
            type: "success",
            message: "successfully set privacy"
          })

          localStorage.setItem("profile", JSON.stringify(res.data));
          this.refreshProfile()

        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    }
  },

}
</script>

<style scoped>

</style>