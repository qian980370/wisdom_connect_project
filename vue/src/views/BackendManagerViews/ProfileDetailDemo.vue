<template>
  <div style="padding: 10px; width: 100%">
    <div style="margin: 10px 0">
      <span>current profile: {{profile.username}}</span>
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
          <el-popconfirm title="Send Friend Request?" @confirm="sendFriendRequest">
            <template #reference>
              <el-button type="text" size="small" >Add Friend</el-button>
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
          <el-popconfirm title="Accept Friend Request?" @confirm="acceptFriendRequest">
            <template #reference>
              <el-button type="text" size="small" >Accept Friend</el-button>
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
          <el-popconfirm title="Send Friend Request?" @confirm="">
            <template #reference>
              <el-button type="text" size="small" >Add Friend</el-button>
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
          <el-popconfirm title="Are you sure to delete this?" @confirm="">
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
          <el-popconfirm title="Are you sure to delete this?" @confirm="">
            <template #reference>
              <el-button type="text" size="small" >Add Hobby</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>

    </el-table>



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
      form: {},
      query: '',
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
    }

  },
  created() {
    this.load();
  },
  methods:{

    refreshProfile(){
      this.profile = localStorage.getItem("profile") ? JSON.parse(localStorage.getItem("profile")) : {}
      console.log(this.profile);
    },
    // handleClose(){
    //   this.dialogVisible = false;
    // },
    load(){
      this.refreshProfile();
      this.getAllFriends();
      this.getRandomFriends();
      this.getFriendsRequest();
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

    getFriendsRequest(){
      request.get("/profile/friendsRequest", {
        params: {
          profileID: this.profile.id,
        }
      }).then(res =>{
        console.log(res);
        this.friendRequestTableData = res.data;
      })
    },

    getRandomFriends(){
      request.get("/profile/randomFriends", {
        params: {
          profileID: this.profile.id,
        }
      }).then(res =>{
        console.log(res);
        this.randomFriendTableData = res.data;
      })
    },

    acceptFriendRequest(id){
      request.put("/profile/acceptFriendRequest", {
        params: {
          profileID: this.profile.id,
          targetID: id,
        }
      }).then(res => {
        if (res.code === '200') {
          this.$message({
            type: "success",
            message: "successfully send request"
          })

        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    },

    sendFriendRequest(id){
      request.post("/profile/addFriend", {
        params: {
          profileID: this.profile.id,
          targetID: id,
        }
      }).then(res => {
        if (res.code === '200') {
          this.$message({
            type: "success",
            message: "successfully delete"
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
      request.delete("/profile/friendDelete",{
          params: {
            profileID: this.profile.id,
            targetID: id,
          }
      }).then(res => {
        if (res.code === '200') {
          this.$message({
            type: "success",
            message: "successfully delete"
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
      request.post("/profile/blockUser",{
        params: {
          profileID: this.profile.id,
          targetID: id,
        }
      }).then(res => {
        if (res.code === '200') {
          this.$message({
            type: "success",
            message: "successfully delete"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()
      })
    },

  },
}
</script>

<style scoped>

</style>