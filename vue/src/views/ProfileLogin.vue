<template>
<!--  <div class="header">-->
<!--    <h2>wisdom connect</h2>-->
<!--  </div>-->
  <div style="width: 100%; height: 120vh;  overflow: hidden">
    <wisdom-header></wisdom-header>


    <div class="profiles_container">
      <div class="profiles_form">
        <div class="profiles_form_header"><p>Resident's Profiles</p></div>

        <div class="profiles_display_container">
          <!--------Table------>
          <div class="profiles_display_content" v-for="item in userinfo" @click=next(item)>
            <table >
              <tr>
                <td><img  :src="item.icon"></td>
                <td><p>{{ item.username }}</p></td>
              </tr>
            </table>
          </div>

        </div>

        <div class="profiles_create">
          <button id="create_btn" @click="$router.push('/newprofile')">Create a new profile</button>
        </div>

        <div class="profiles_logout">
          <button id="logout_btn" @click="logout()">Log out</button>
        </div>


      </div>

    </div>
  </div>

</template>

<script>
import request from "@/utils/request";
import WisdomHeader from '../components/WisdomHeader.vue'


export default {
  name: "ProfileLogin",
  components:{WisdomHeader},
  data(){
    return{
      user : null,

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
    handleAccess(row){
      localStorage.setItem("profile", JSON.stringify(row));
      this.$router.push({
        name: 'ProfileDetailView',
        params:{
          profileId: this.form.id
        }
      })
    },
    next(item){
      localStorage.setItem("profile", JSON.stringify(item));
      this.$router.push('/taponfriend')
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
        console.log(res);
        this.userinfo = res.data.records
        console.log(res.data.records);

        // this.tableData = res.data.records;
        // this.total = res.data.total;
      })
    },
    cleanLocalStorage(){
      localStorage.clear();
    },
    logout(){
      this.cleanLocalStorage();
      this.$router.push('/facilitylogin');
    }
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

/*------------Profiles---------------*/
.profiles_container {
  height: 1000px;
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
}
.profiles_display_content:hover{
  width: 400px;
  height: 120px;
  background-color:#f3f3f3;
  border: solid 2px #bfa0c8;
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
  cursor: pointer;
}
.profiles_create button:hover{
  height: 44px;
  width: 240px;
  background-color: white;
  color: #bfa0c8;
  border: solid 2px #bfa0c8;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
  cursor: pointer;
}
.profiles_create button:active{
  height: 44px;
  width: 240px;
  background-color: white;
  color: #bfa0c8;
  border: solid 2px #bfa0c8;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
  cursor: pointer;
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
  cursor: pointer;
}
.profiles_logout button:hover{
  height: 44px;
  width: 240px;
  background-color: white;
  border: solid 2px #bfa0c8;
  margin-top: 20px;
  font-size: 18px;
  color:#bfa0c8;;
  margin-bottom: 40px;
  border-radius: 5px;
  cursor: pointer;
}
</style>


