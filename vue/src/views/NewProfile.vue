<template>
  <head>
    <meta charset="UTF-8">
    <title>wisdom connect</title>
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

  <div class="addprofiles_container">
    <div class="addprofiles_form">
      <div class="addprofiles_form_title"><p>Personal Info</p></div>

      <table class="addprofiles_upload_table">
        <tr>
          <td rowspan="2"><div class="addprofiles_upload_img"><img src="../image/camera_icon.png" alt="pic"></div></td>
          <td>
            <input type="input" placeholder="Name" class="Name">
            <input type="input" placeholder="Age" class="Age">
          </td>
        </tr>
      </table>



      <div class="addprofiles_form_title"><p>Select Your Interests</p></div>

      <div class="addprofiles_interests">
        <table class="addprofiles_interests_table">
          <tr>
            <table>
              <tr>
                <td>
                  <img :src="userinfo[0].icon">
                  <p>{{userinfo[0].name}}</p>
                </td>
                <td>
                  <img :src="userinfo[1].icon">
                  <p>{{userinfo[1].name}}</p>
                </td>
                <td>
                  <img :src="userinfo[2].icon">
                  <p>{{userinfo[1].name}}</p>
                </td>
              </tr>
            </table>
          </tr>

          <tr>
            <table>
              <tr>
                <td>
                  <img :src="userinfo[3].icon">
                  <p>{{userinfo[3].name}}</p>
                </td>
                <td>
                  <img :src="userinfo[4].icon">
                  <p>{{userinfo[4].name}}</p>
                </td>
                <td>
                  <img :src="userinfo[5].icon">
                  <p>{{userinfo[5].name}}</p>
                </td>
              </tr>
            </table>
          </tr>

          <tr>
              <table>
                <tr>
                  <td>
                    <img :src="userinfo[6].icon">
                    <p>{{userinfo[6].name}}</p>
                  </td>
                  <td>
                    <img :src="userinfo[7].icon">
                    <p>{{userinfo[7].name}}</p>
                  </td>
                  <td>
                    <img :src="userinfo[7].icon">
                    <p>{{userinfo[7].name}}</p>
                  </td>
                </tr>
              </table>
          </tr>

        </table>

        <table class="addprofiles_interests_btn_table">
          <tr>
            <td><button id="addinterests_btn">Refresh</button></td>
            <td><button id="refreshinterests_btn">Add</button></td>
          </tr>
        </table>

      </div>


      <hr>
      <div class="addprofiles_add">
        <button id="add_btn">Add profile</button>
      </div>

      <!-- <div class="addprofiles_logout">
          <button id="logout_btn">Log out</button>
      </div>
       -->

    </div>

  </div>

  </body>

</template>

<script>
import request from "@/utils/request";

export default {
  name: "NewProfile",
  data(){
    return{
      ProfileForm:{},
      rules: {
        username: [
          {required: true, message: 'please input username', trigger: 'blur'},
        ],
      },userinfo :null
    }
  },
  created() {
    this.load();
  },
  methods: {
    refreshUser(){
      this.user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}

    },
    load(){
      this.refreshUser();
      request.get("/hobby/page", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          query: this.query
        }
      }).then(res =>{
        console.log(res.data);
        this.userinfo = res.data.records
      })
    },
    AddProfile() {
      console.log("create");
      request.post("profile", this.form).then(res => {
        console.log(res);
        if (res.code === '200') {
          this.$message({
            type: "success",
            message: "Successfully add profile"
          })
          this.$router.push("/profilelogin")
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
#logo_and_title{
  width: 400px;
  height: 120px;
  text-align: center;
  padding-left: 37%;
  padding-top: 2%;
}
.addprofiles_container{
  /* background: url('jack-finnigan-M9EctVUPrp4-unsplash.jpg') no-repeat center center fixed; */

  height: 1300px;
  /* background-color: #f3e6f7; */
  /* padding: 50px; */
  /* background-color: azure; */
}

.addprofiles_form{
  width: 540px;
  height: 1160px;
  /* border: 2px solid red; */
  margin:20px auto;
  border:solid 2px;
  border-color: #864a98;
  text-align: center;
  border-radius: 5px;
}
.addprofiles_form_title{
  font-size: 28px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
  padding: 10px;
}
.addprofiles_upload_table{
  height: 120px;
}
.addprofiles_upload_img{
  height: 120px;
  width: 120px;
  margin-left: 60px;
  margin-bottom: 20px;
  border:dotted;
  border-radius: 5px;
  border-width:2px;
  border-color: #a777b5;
}
.addprofiles_upload_img img{
  height: 55px;
  width: 60px;
  padding-top: 30px;
}
.Name{
  height: 40px;
  width: 240px;
  border: dotted 2px;
  border-color: #a777b5;
  font-size: 18px;
  border-radius: 5px;
  margin-bottom: 20px;

}
.Age{
  height: 40px;
  width: 240px;
  border: dotted 2px;
  border-color: #a777b5;
  font-size: 18px;
  border-radius: 5px;
  margin-bottom: 20px;
}

.addprofiles_interests_table{
  margin-left: 50px;

}

.addprofiles_interests_table tr{
  width: 480px;
  height: 80px;
}

.addprofiles_interests_table td{
  width: 140px;
  height: 120px;
}
.addprofiles_interests_table img{
  width: 120px;
  height: 120px;
}

#addprofiles_interests_tag p{
  font-size: 14px;
}

.addprofiles_interests_btn_table{
  width: 400px;
  height: 60px;
  margin-left: 70px;
  margin-top: 30px;
  margin-bottom: 30px;
}
.addprofiles_interests_btn_table td{
  width: 240px;
  height: 60px;
  margin-left: 50px;
}

.addprofiles_interests_btn_table button{
  width: 160px;
  height: 30px;
  color: #864a98;
  border: solid 1px;
  border-color: #864a98;
  border-radius: 5px;
  background-color: #f3f3f3;
}


.addprofiles_add button{
  height: 44px;
  width: 240px;
  background-color: #bfa0c8;
  color: white;
  border: 0px;
  margin-top: 30px;
  font-size: 18px;
  border-radius: 5px;
}
/* .addprofiles_logout button{
    height: 44px;
    width: 240px;
    background-color: #bfa0c8;
    border: 0px;
    margin-top: 20px;
    font-size: 18px;
    color:white;
    margin-bottom: 40px;
    border-radius: 5px;

} */

</style>

