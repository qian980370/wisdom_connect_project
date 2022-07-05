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

    <div class="blockedlist_container">
      <div class="blockedlist_form">
        <div class="blockedlist_form_header">
          <table class="=blockedlist_form_header_table">
            <tr>
              <td><div class="back_button" @click="$router.push('/personal')"><img src="../image/back_icon.png" style="height: 30px;" alt="back_icon"></div></td>
              <td><p>Your Blocked List</p></td>
            </tr>
          </table>
        </div>


        <hr>

        <div class="blockedlist_display_header"><p>These people will not show up in your call list</p></div>


        <div class="blockedlist_display_container">
          <!--------Table------>

          <div class="myprofile_display_content" v-for="item in blockTableData">
            <table>
              <tr >
                <td><img :src="item.url"></td>
                <td><p>{{ item.username }}</p></td>
              </tr>
            </table>
          </div>

        </div>
      </div>

    </div>

  </div>

</template>

<script>
import request from "@/utils/request";

export default {
  name: "BlockedList",
  data(){
    return{
      blockTableData:[
      ],
      profile : localStorage.getItem("profile") ? JSON.parse(localStorage.getItem("profile")) : null,
    }

  },
  created() {
    this.load();
  },

  methods: {
    refreshProfile(){
      this.profile = localStorage.getItem("profile") ? JSON.parse(localStorage.getItem("profile")) : {}
      // console.log(this.profile);
      this.privacy = this.profile.privacy;
    },
    load(){
      this.getAllBlock();
    },
    getAllBlock(){
      request.get("/profile/blockList", {
        params: {
          profileID: this.profile.id,
        }
      }).then(res =>{
        console.log(res);
        this.blockTableData = res.data;
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
/*-------------Blocked List----------------*/

.blockedlist_container{
  /* background: url('jack-finnigan-M9EctVUPrp4-unsplash.jpg') no-repeat center center fixed; */

  height: 1000px;
  /* background-color: #f3e6f7; */
  /* padding: 50px; */
  /* background-color: azure; */
}

.blockedlist_form{
  width: 540px;
  height: 740px;
  /* border: 2px solid red; */
  margin:20px auto;
  text-align: center;
  border:solid 2px;
  border-color:#864a98;
  border-radius: 5px;
}
.blockedlist_form_header p{
  width: 370px;
  font-size: 28px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
}
.blockedlist_display_header{
  margin-top: 30px;
}
.blockedlist_display_header p{
  font-size: 18px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
}

.blockedlist_display_container{
  width: 440px;
  overflow-y:auto;
  overflow-x: hidden;
  height: 460px;
  border: solid 2px #bfa0c8;
  border-radius: 5px;
  margin-left: 50px;
  margin-top: 40px;
}

.blockedlist_display_container::-webkit-scrollbar{
  width: 10px;
}
.blockedlist_display_container::-webkit-scrollbar-thumb{
  background-color: #bfa0c8;
  border-radius: 5px;
}
.blockedlist_display_container::-webkit-scrollbar-button{
  display: none;
}
.blockedlist_display_container::-webkit-scrollbar-track{
  background-color: #f3f3f3;
}


.blockedlist_display{
  height: 480px;
  width: 400px;
  margin-left: 70px;
  border:dotted;
  border-radius: 5px;
  border-width:2px;
  border-color: #864a98;
}

.blockedlist_display_content{
  width: 400px;
  height: 120px;
  background-color:#f3f3f3;
  margin-bottom: 10px;
  margin-left: 15px;
  margin-top: 10px;
}
.blockedlist_display_content img{
  width: 100px;
  height: 100px;
}

.blockedlist_display_content td{
  width: 180px;
  height: 120px;
}

.blockedlist_display_content p{
  font-size: 18px;
  color:#864a98;
  font-weight: bold;
}
.back_button{
  width: 80px;
  cursor: pointer;
}
.blockedlist_form_header{
  margin-top: 30px;
  margin-bottom: 30px;
}
</style>

