<template>
  <wisdom-header></wisdom-header>

  <div class="add_friends_container">
    <div class="add_friends_form">

      <div class="add_friends_form_header">
        <div class="add_friends_back_button" @click="back()"><img src="../image/back_icon.png" style="height: 30px;"></div>
      </div>

      <hr>

      <div class="add_friends_display_header"><p>Add random friends</p></div>


      <div class="add_friends_display_container">

        <div class="add_friends_display_content">
          <table>
            <tr>
              <td  v-for="(friend,index) in randomFriendTableData" :key='index' @click="selectFriend(index,friend.id)" :class="index === selectedIndex?'selected':''" >
                <img :src="friend.icon" >
                <p>{{friend.username}}</p>
<!--                <p>{{index}}</p>-->
              </td>

            </tr>
          </table>
        </div>

      </div>

      <div class="add_friends_buttons">
        <table>
          <tr>
            <td><button id="add_friends_refresh_btn" @click="getRandomFriends()">Refresh</button></td>
            <td><button id="add_friends_add_btn" @click="sendFriendRequest(friendId)">Add</button></td>
          </tr>
        </table>
      </div>

      <hr>

      <div class="add_friends_display_header"><p>Search by name</p></div>

      <div class="add_friends_search">
        <table>
          <tr>
            <td><input type="input" placeholder="Name"/></td>
            <td><a href="javascript:"><button id="add_friends_search_button">Search</button></a></td>
          </tr>
        </table>
      </div>

      <div class="add_friends_search_content">
        <table>
          <tr>
            <td><img src="../image/flower1.jpg"></td>
            <td><p>James</p></td>
          </tr>
        </table>
      </div>

    </div>

  </div>


</template>


<script>
import WisdomHeader from '../components/WisdomHeader.vue'
import request from "@/utils/request";


export default {
  components:{WisdomHeader},
  data(){
    return {
      randomFriendTableData:[
      ],
      selectedIndex: -1,
      friendId:-1,
      profile : localStorage.getItem("profile") ? JSON.parse(localStorage.getItem("profile")) : null,

    };
  },
  computed:{
    IsIndex(index){
      console.log(this.selectedIndex )
      console.log(index)

      return this.selectedIndex === index;
    }
  },
  created(){
    this.getRandomFriends();
    console.log('randomdata')
    console.log(this.randomFriendTableData)
  },
  methods:{
    back(){
      this.$router.push('/taponfriend')
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
    selectFriend(index,id){
      this.selectedIndex = index;
      this.friendId = id;
      console.log(this.selectedIndex);
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

  },
}


</script>



<style scoped>
.add_friends_container{
  /* background: url('jack-finnigan-M9EctVUPrp4-unsplash.jpg') no-repeat center center fixed; */

  height: 1000px;
  /* background-color: #f3e6f7; */
  /* padding: 50px; */
  /* background-color: azure; */
}

.add_friends_form{
  width: 540px;
  height: 960px;
  /* border: 2px solid red; */
  margin:20px auto;
  text-align: center;
  border:solid 2px;
  border-color:#864a98;
  border-radius: 5px;
}
.add_friends_form_header {
  height: 40px;
  width: 100px;
}
.add_friends_back_button{
  margin-top: 20px;
  cursor: pointer;
}
/* .add_friends_form_header p{
    width: 370px;
    font-size: 28px;
    font-weight: bold;
    color:#864a98;
    text-align: center;
} */
.add_friends_display_header{
  margin-top: 30px;
}
.add_friends_display_header p{
  font-size: 18px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
}

.add_friends_display_container{
  width: 440px;
  overflow-y:auto;
  overflow-x: hidden;
  height: 260px;
  border: solid 2px #bfa0c8;
  border-radius: 5px;
  margin-left: 50px;
  margin-top: 40px;
}

.add_friends_display_container::-webkit-scrollbar{
  width: 10px;
}
.add_friends_display_container::-webkit-scrollbar-thumb{
  background-color: #bfa0c8;
  border-radius: 5px;
}
.add_friends_display_container::-webkit-scrollbar-button{
  display: none;
}
.add_friends_display_container::-webkit-scrollbar-track{
  background-color: #f3f3f3;
}





.add_friends_display_content{
  width: 400px;
  height: 140px;
  margin-top: 20px;
  margin-bottom: 10px;
  margin-left: 15px;
}

.add_friends_display_content img{
  width: 100px;
  height: 100px;
  cursor: pointer;
}

.add_friends_display_content td{
  width: 240px;
  height: 150px;
  /*border: solid 2px white;*/
}
.add_friends_display_content td{
  width: 240px;
  height: 150px;
}

.selected {
  border: solid 2px #bfa0c8;
}


.add_friends_display_content tr{
  padding-top: 40px;
}

.add_friends_display_content p{
  font-size: 14px;
  color:#864a98;
  font-weight: bold;
  text-align: center;
}
.add_friends_buttons{
  margin-left: 110px;
}
.add_friends_buttons td{
  width: 160px;
}
#add_friends_refresh_btn{
  height: 36px;
  width: 140px;
  background-color: #bfa0c8;
  border: 0px;
  color: white;
  margin-top: 40px;
  margin-bottom: 40px;
  font-size: 18px;
  border-radius: 5px;
  cursor: pointer;
}
#add_friends_refresh_btn:hover{
  height: 36px;
  width: 140px;
  background-color: white;
  border: solid 2px #bfa0c8;
  color: #bfa0c8;
  margin-top: 40px;
  margin-bottom: 40px;
  font-size: 18px;
  border-radius: 5px;
  cursor: pointer;
}
#add_friends_refresh_btn:active{
  height: 36px;
  width: 140px;
  background-color: white;
  border: solid 2px #bfa0c8;
  color: #bfa0c8;
  margin-top: 40px;
  margin-bottom: 40px;
  font-size: 18px;
  border-radius: 5px;
  cursor: pointer;
}
#add_friends_add_btn{
  height: 36px;
  width: 140px;
  background-color: #bfa0c8;
  border:0px;
  color: white;
  margin-top: 40px;
  margin-bottom: 40px;
  font-size: 18px;
  border-radius: 5px;
  cursor: pointer;
}
#add_friends_add_btn:hover{
  height: 36px;
  width: 140px;
  background-color: white;
  border: solid 2px #bfa0c8;
  color: #bfa0c8;
  margin-top: 40px;
  margin-bottom: 40px;
  font-size: 18px;
  border-radius: 5px;
  cursor: pointer;
}
#add_friends_add_btn:active{
  height: 36px;
  width: 140px;
  background-color: white;
  border: solid 2px #bfa0c8;
  color: #bfa0c8;
  margin-top: 40px;
  margin-bottom: 40px;
  font-size: 18px;
  border-radius: 5px;
  cursor: pointer;
}

.add_friends_search{
  margin-left: 65px;
}
.add_friends_search input{
  height: 32px;
  width: 280px;
  border: solid 2px #bfa0c8;
  font-size: 18px;
  border-radius: 5px;
  outline: none;
}
#add_friends_search_button {
  height: 36px;
  width: 90px;
  background-color: white;
  border: solid 2px #bfa0c8;
  margin-left: 20px;
  font-size: 18px;
  color:#bfa0c8;
  border-radius: 5px;
  cursor: pointer;
}
#add_friends_search_button:active{
  height: 36px;
  width: 90px;
  background-color: #bfa0c8;
  border: solid 2px #bfa0c8;
  margin-left: 20px;
  font-size: 18px;
  color:white;
  border-radius: 5px;
  cursor: pointer;
}
#add_friends_search_button:hover{
  height: 36px;
  width: 90px;
  background-color: #bfa0c8;
  border: solid 2px #bfa0c8;
  margin-left: 20px;
  font-size: 18px;
  color: white;
  border-radius: 5px;
  cursor: pointer;
}
#add_friends_search_button:active{
  height: 36px;
  width: 90px;
  background-color: #bfa0c8;
  border: solid 2px;
  margin-left: 20px;
  font-size: 18px;
  color: white;
  border-radius: 5px;
  cursor: pointer;
}

.add_friends_search_content{
  width: 400px;
  height: 120px;
  background-color:#f3f3f3;
  margin-bottom: 10px;
  margin-left: 70px;
  margin-top: 30px;
  cursor: pointer;
}
.add_friends_search_content img{
  width: 100px;
  height: 100px;
}

.add_friends_search_content td{
  width: 180px;
  height: 120px;
}
.add_friends_search_content p{
  font-size: 18px;
  color:#864a98;
  font-weight: bold;
}


</style>