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


    <div class="interests_container">
      <div class="interests_form">

        <div class="interests_form_header">
          <table class="=interests_form_header_table">
            <tr>
              <td><div class="back_button" @click="$router.push('/personal')"><img src="../image/back_icon.png" style="height: 30px;" alt="back_icon"></div></td>
              <td><p>Your Interest List</p></td>
            </tr>
          </table>
        </div>

        <hr>

        <div class="interests_display_header"><p>Click to remove from list</p></div>


        <div class="interests_display_container">
          <div class="interests_display_content">
            <table>
              <tr v-for="(row,index) in sliceList(hobbyTableData,3)" >
                <td v-for="(data,i) in row " :key="i">
                  <div class="interests_display_content_img">
                    <img :src="data.icon">
                    <button id="delete_interest_btn" @click="deleteHobby(data.id)"></button>
                  </div>
                  <p>{{data.name}}</p>
                </td>
              </tr>
            </table>
          </div>
          <!--------Table------>



          <!--------Table------>


        </div>

        <div class="interests_buttons">
          <table>
            <tr>
              <td><a href="javescript:"><button id="remove_btn">Remove</button></a></td>
              <td><a href="more_interests.html"><button id="more_btn">More</button></a></td>
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
  name: "InterestList",
  data(){
    return{
      form: {},
      query: '',
      search: '',
      hobbyTableData:[
      ],
      profile : localStorage.getItem("profile") ? JSON.parse(localStorage.getItem("profile")) : null,
    }
  },
  computed: {
    sliceList() {
      return function (data,count) {
        if (data != undefined) {
          let index = 0;
          let arrTemp = [];
          for (let i = 0; i < data.length; i++) {
            index = parseInt(i / count);
            if (arrTemp.length <= index) {
              arrTemp.push([]);
            }
            arrTemp[index].push(data[i])
          }
          return arrTemp
        }
      }
    }
  },
  created() {
    this.load()
    console.log(this.profile);
  },
  methods:{
    refreshProfile(){
      this.profile = localStorage.getItem("profile") ? JSON.parse(localStorage.getItem("profile")) : {}
      // console.log(this.profile);
      this.privacy = this.profile.privacy;
    },
    load(){
      this.refreshProfile();
      this.getAllHobbies();
      this.getRandomHobbies();

    },
    getAllHobbies(){
      request.get("/hobby/hobbyList", {
        params: {
          profileID: this.profile.id,
        }
      }).then(res =>{
        console.log(res);
        this.hobbyTableData = res.data;
      })
    },
    getRandomHobbies(){
      request.get("/hobby/randomHobbies", {
        params: {
          profileID: this.profile.id,
        }
      }).then(res =>{
        console.log(res);
        this.randomHobbyTableData = res.data;
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
/*--------------Interests-----------------*/

.interests_container{
  /* background: url('jack-finnigan-M9EctVUPrp4-unsplash.jpg') no-repeat center center fixed; */

  height: 1000px;
  /* background-color: #f3e6f7; */
  /* padding: 50px; */
  /* background-color: azure; */
}

.interests_form{
  width: 540px;
  height: 840px;
  /* border: 2px solid red; */
  margin:20px auto;
  text-align: center;
  border:solid 2px;
  border-color:#864a98;
  border-radius: 5px;
}

.interests_form_header p{
  width: 370px;
  font-size: 28px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
}
.interests_display_header{
  margin-top: 30px;
}
.interests_display_header p{
  font-size: 18px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
}

.interests_display_container{
  width: 440px;
  overflow-y:auto;
  overflow-x: hidden;
  height: 460px;
  border: solid 2px #bfa0c8;
  border-radius: 5px;
  margin-left: 50px;
  margin-top: 40px;
}

.interests_display_container::-webkit-scrollbar{
  width: 10px;
}
.interests_display_container::-webkit-scrollbar-thumb{
  background-color: #bfa0c8;
  border-radius: 5px;
}
.interests_display_container::-webkit-scrollbar-button{
  display: none;
}
.interests_display_container::-webkit-scrollbar-track{
  background-color: #f3f3f3;
}


.interests_display{
  height: 480px;
  width: 400px;
  margin-left: 70px;
  border:dotted;
  border-radius: 5px;
  border-width:2px;
  border-color: #864a98;
}

.interests_display_content{
  width: 400px;
  height: 140px;
  margin-top: 20px;
  margin-bottom: 10px;
  margin-left: 15px;
}
.interests_display_content img{
  width: 100px;
  height: 100px;
  cursor: pointer;
  z-index: 0;
}

.interests_display_content td{
  width: 240px;
  height: 150px;
  border:solid 2px white;
}
.interests_display_content td:hover{
  width: 240px;
  height: 150px;
  border:solid 2px #bfa0c8;
}
.interests_display_content tr{
  padding-top: 40px;
}

.interests_display_content p{
  font-size: 14px;
  color:#864a98;
  font-weight: bold;
  text-align: center;
}
.interests_buttons{
  margin-left: 50px;
}

.interests_display_content_img{
  position: relative;
}

#delete_interest_btn{
  position: absolute;
  height: 30px;
  width: 30px;
  z-index: 20;
  left: 96px;
  background: url("../image/delete_icon.png") no-repeat;
  background-size: 28px 28px;
  border: none;
}

#remove_btn{
  height: 44px;
  width: 215px;
  background-color: #bfa0c8;
  color: white;
  border: 0px;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
#remove_btn:hover{
  height: 44px;
  width: 215px;
  background-color:white;
  color:  #bfa0c8;
  border: solid 2px #bfa0c8;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
#remove_btn:active{
  height: 44px;
  width: 215px;
  background-color:white;
  color:  #bfa0c8;
  border: solid 2px #bfa0c8;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
#more_btn{
  height: 44px;
  width: 215px;
  background-color: #bfa0c8;
  color: white;
  border: 0px;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
#more_btn:hover{
  height: 44px;
  width: 215px;
  background-color:white;
  color:  #bfa0c8;
  border: solid 2px #bfa0c8;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
#more_btn:active{
  height: 44px;
  width: 215px;
  background-color:white;
  color:  #bfa0c8;
  border: solid 2px #bfa0c8;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
.back_button{
  width: 80px;
  cursor: pointer;
}
.interests_form_header{
  margin-top: 30px;
  margin-bottom: 30px;
}

</style>
