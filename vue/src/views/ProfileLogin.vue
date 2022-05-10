<template>
  <div class="header">
    <h2>wisdom connect</h2>
  </div>

  <div class="profiles_container">
    <div class="profiles_form">
      <div class="profiles_form_header"><p>Resident's Profiles</p></div>

      <div class="profiles_display_container">
        <!--------Table------>
        <div class="profiles_display_content" v-for="item in userinfo">
          <table>
            <tr>
              <td><img src="item.icon"></td>
              <td><p>{{ item.username }}</p></td>
            </tr>
          </table>
        </div>







      </div>

      <div class="profiles_create">
        <button id="create_btn" @click="$router.push('/newprofile')">Create a new profile</button>
      </div>

      <div class="profiles_logout">
        <button id="logout_btn" @click="$router.push('/facilitylogin')">Log out</button>
      </div>


    </div>

  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "ProfileLogin",
  data(){
    return{
      user : localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null,

      userinfo :null
    }
  },
  created() {
    this.load()
  },
  methods:{
    refreshUser(){
      this.user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    },
    load(){
      this.refreshUser();
      request.get("/profile/page", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          query: this.query
        }
      }).then(res =>{
        console.log(res.data.records);
        this.userinfo = res.data.records
        // this.tableData = res.data.records;
        // this.total = res.data.total;
      })
    }
  }
}
</script>

<style scoped>
.header{
  height: 70px;
  width: 292px;
  margin: 0 auto;
  /* text-align: center; */
  font-size:19px;
  text-align: right;
  line-height: 70px;
  background-image: url(../image/WechatIMG2010.png);
  background-size: 100px 70px;
  background-repeat: no-repeat;
  background-position: left center;
  margin-top: 70px;
}
/*------------Profiles---------------*/
.profiles_container{
  /* background: url('jack-finnigan-M9EctVUPrp4-unsplash.jpg') no-repeat center center fixed; */

  height: 1000px;
  /* background-color: #f3e6f7; */
  /* padding: 50px; */
  /* background-color: azure; */
}

.profiles_form{
  width: 540px;
  height: 880px;
  /* border: 2px solid red; */
  margin:20px auto;;
  text-align: center;
  border:solid 2px;
  border-color:#864a98;
  border-radius: 5px;
}
.profiles_form_header p{
  font-size: 28px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
  padding: 40px;
}

.profiles_display_container{
  width: 510px;
  overflow-y:auto;
  height: 460px;
}

.profiles_display_container::-webkit-scrollbar{
  width: 10px;
}
.profiles_display_container::-webkit-scrollbar-thumb{
  background-color: #bfa0c8;
  border-radius: 5px;
}
.profiles_display_container::-webkit-scrollbar-button{
  display: none;
}
.profiles_display_container::-webkit-scrollbar-track{
  background-color: #f3f3f3;
}


.profiles_display{
  height: 480px;
  width: 400px;
  margin-left: 70px;
  border:dotted;
  border-radius: 5px;
  border-width:2px;
  border-color: #864a98;
}

.profiles_display_content{
  width: 400px;
  height: 120px;
  background-color:#f3f3f3;
  margin-bottom: 10px;
  margin-left: 70px;
  cursor: pointer;
}
.profiles_display_content img{
  width: 100px;
  height: 100px;
}

.profiles_display_content td{
  width: 180px;
  height: 120px;
}

.profiles_display_content p{
  font-size: 18px;
  color:#864a98;
  font-weight: bold;
}

.profiles_create button{
  height: 44px;
  width: 240px;
  background-color: #bfa0c8;
  color: white;
  border: 0px;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
.profiles_logout button{
  height: 44px;
  width: 240px;
  background-color: #bfa0c8;
  border: 0px;
  margin-top: 20px;
  font-size: 18px;
  color:white;
  margin-bottom: 40px;
  border-radius: 5px;

}
</style>


