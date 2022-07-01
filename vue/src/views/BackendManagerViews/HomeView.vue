<template>
  <div style="padding: 10px; width: 100%">
    <div style="margin: 10px 0">
      <span>current user: {{user.username}}</span>
    </div>

    <div style="margin: 10px 0">
<!--      <el-button type="primary" @click="addUser">New</el-button>-->
      <el-button type="primary" @click="addProfile">new profile</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="showUser">User</el-button>
      <el-button type="primary" @click="showProfile">Profile</el-button>
      <el-button type="primary" @click="testHobby">test hobby</el-button>
<!--      <el-button type="primary" @click="login">login</el-button>-->
      <el-button type="primary" @click="logout">logout</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="query" placeholder="please input username information to search" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 7px" @click="load">Search</el-button>
    </div>

    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%">
      <el-table-column
          prop="id"
          label="ID">
      </el-table-column>
      <el-table-column
          prop="registerTime"
          label="Register Time">
      </el-table-column>
      <el-table-column
          prop="owner"
          label="Owner">
      </el-table-column>
      <el-table-column
          prop="username"
          label="Username">
      </el-table-column>
      <el-table-column
          prop="password"
          label="Password">
      </el-table-column>
      <el-table-column
          prop="email"
          label="Email">
      </el-table-column>
      <el-table-column
          prop="gender"
          label="Gender">
      </el-table-column>
      <el-table-column
          prop="age"
          label="Age">
      </el-table-column>
      <el-table-column
          prop="address"
          label="Address">
      </el-table-column>
      <el-table-column
          prop="hobby"
          label="Hobby">
      </el-table-column>
      <el-table-column
          prop="block"
          label="Block">
      </el-table-column>
      <el-table-column
          prop="privacy"
          label="Privacy">
      </el-table-column>
      <el-table-column
          prop="friend"
          label="Friend">
      </el-table-column>



      <el-table-column fixed="right" label="Operations" width="200px">
        <template #default="scope">
          <el-button type="text" size="small" @click="handleEdit(scope.row)">Edit</el-button>
          <el-popconfirm title="Are you sure to delete this?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="text" size="small" >Delete</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>

    </el-table>

    <div style="margin-top: 15px">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
      />

<!--      <el-dialog id="loginForm" v-model="dialogLoginVisible" :title=dialogLoginTitle width="30%">-->
<!--        <div style="width: 85%">-->
<!--          <el-form :model="loginForm" label-width="120px">-->
<!--            <el-form-item label="Username">-->
<!--              <el-input v-model="loginForm.username" />-->
<!--            </el-form-item>-->
<!--            <el-form-item label="Password">-->
<!--              <el-input type="password" v-model="loginForm.password" />-->
<!--            </el-form-item>-->
<!--            <el-form-item label="Email Address">-->
<!--              <el-input type="email" v-model="loginForm.email" />-->
<!--            </el-form-item>-->
<!--            <el-form-item>-->
<!--              <el-button type="primary" @click="loginSubmit">Apply</el-button>-->
<!--              <el-button @click="dialogVisible = false">Cancel</el-button>-->
<!--            </el-form-item>-->
<!--          </el-form>-->
<!--        </div>-->
<!--      </el-dialog>-->

      <el-dialog id="userForm" v-model="dialogVisible" :title=dialogTitle width="30%">
        <div style="width: 85%">
          <el-form :model="form" label-width="120px">
            <el-form-item label="Username">
              <el-input v-model="form.username" />
            </el-form-item>
            <el-form-item label="Password">
              <el-input type="password" v-model="form.password" />
            </el-form-item>
            <el-form-item label="Email Address">
              <el-input type="email" v-model="form.email" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">Apply</el-button>
              <el-button @click="dialogVisible = false">Cancel</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-dialog>

        <el-dialog id="profileForm" v-model="dialogProfileVisible" :title=dialogProfileTitle width="30%">
          <div style="width: 85%">
            <el-form :model="formProfile" label-width="120px">

              <el-form-item label="Username">
                <el-input v-model="formProfile.username" />
              </el-form-item>
              <el-form-item label="Age">
                <el-input type="number" v-model="formProfile.age" />
              </el-form-item>
              <el-form-item label="Gender">
                <el-radio v-model="formProfile.gender" label="Male" value="male" />
                <el-radio v-model="formProfile.gender" label="Woman" value="woman" />
                <el-radio v-model="formProfile.gender" label="Secret" value="secret" />
              </el-form-item>
              <el-form-item label="Privacy">
                <el-switch v-model="formProfile.privacy"
                           :active-value="1"
                           :inactive-value="0"/>
              </el-form-item>
              <el-form-item label="Hobby">
                <el-input v-model="formProfile.hobby" />
              </el-form-item>
              <el-form-item label="Block">
                <el-input v-model="formProfile.block" />
              </el-form-item>
              <el-form-item label="Friend">
                <el-input v-model="formProfile.friend" />
              </el-form-item>
              <el-form-item label="Icon">
                <el-input v-model="formProfile.icon" />
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onProfileSubmit">Apply</el-button>
                <el-button @click="dialogProfileVisible = false">Cancel</el-button>
              </el-form-item>
            </el-form>
          </div>
      </el-dialog>

    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'Home',
  components: {

  },
  data(){
    return{
      loginForm:{},
      form: {},
      formProfile: {},
      dialogTitle:"Create new user",
      dialogProfileTitle:"Create new profile",
      dialogLoginTitle: "login",
      dialogVisible: false,
      dialogProfileVisible: false,
      dialogLoginVisible: false,
      query: '',
      total: 0,
      currentPage: 1,
      pageSize: 10,
      pageNum: 10,
      search: '',
      mode: 0,
      tableData:[

      ],

      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}

    }

  },
  created() {
    this.load();
  },
  methods:{
    refreshUser(){
      this.user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    },
    // handleClose(){
    //   this.dialogVisible = false;
    // },
    load(){
      if(this.mode === 0){
        request.get("user/page", {
          params: {
            pageNum: this.currentPage,
            pageSize: this.pageSize,
            query: this.query
          }

        }).then(res =>{
          console.log(res);
          this.tableData = res.data.records;
          this.total = res.data.total;
        })
      }else if(this.mode === 1){
        request.get("profile/page", {
          params: {
            pageNum: this.currentPage,
            pageSize: this.pageSize,
            query: this.query
          }

        }).then(res =>{
          console.log(res);
          this.tableData = res.data.records;
          this.total = res.data.total;
        })
      }else if(this.mode === 2){

      }
      

    },
    // addUser(){
    //   this.dialogVisible = true;
    //   this.form = {};
    //   this.dialogTitle = "Create new user";
    // },
    // login(){
    //   this.dialogLoginVisible = true;
    //   this.form = {};
    //   this.dialogLoginTitle = "login";
    // },
    logout(){
      localStorage.removeItem("user");
      localStorage.removeItem("profile");
      this.refreshUser();
      this.$message({
        type: "success",
        message: "Successfully logout"
      })
      this.$router.push("/login")
    },
    addProfile(){
      this.dialogProfileVisible = true;
      this.form = {};
      this.dialogProfileTitle = "Create new profile";
    },
    // loginSubmit() {
    //   console.log("login");
    //   request.post("/user/login", this.loginForm).then(res => {
    //     console.log(res);
    //     if (res.code === '200') {
    //       localStorage.setItem("user", JSON.stringify(res.data));
    //       this.refreshUser()
    //       this.$message({
    //         type: "success",
    //         message: "Successfully login"
    //       })
    //     } else {
    //       this.$message({
    //         type: "error",
    //         message: res.msg
    //       })
    //     }
    //     this.dialogVisible = false;
    //   });
    //
    // },
    onSubmit(){
      if (this.form.id){
        console.log("update");
        request.put("/user", this.form).then(res =>{
          console.log(res);
          if (res.code === '200'){
            this.$message({
              type: "success",
              message: "Successfully update user"
            })
          }else{
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load();
          this.dialogVisible=false;

        });
      }else{
        // console.log("create");
        // request.post("/user/register", this.form).then(res =>{
        //   console.log(res);
        //   if (res.code === '200'){
        //     this.$message({
        //       type: "success",
        //       message: "Successfully add user"
        //     })
        //   }else{
        //     this.$message({
        //       type: "error",
        //       message: res.msg
        //     })
        //   }
        //   this.load();
        //   this.dialogVisible=false;
        // });
      }


    },onProfileSubmit(){
      if (this.formProfile.id){
        console.log("update");
        request.put("/profile", this.formProfile).then(res =>{
          console.log(res);
          if (res.code === '0'){
            this.$message({
              type: "success",
              message: "Successfully update profile"
            })
          }else{
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load();
          this.dialogVisible=false;

        });
      }else{
        console.log("create");
        request.post("/profile/create", this.formProfile).then(res =>{
          console.log(res);
          if (res.code === "200"){
            this.$message({
              type: "success",
              message: "Successfully add profile"
            })
          }else{
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load();
          this.dialogVisible=false;
        });
      }


    },
    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible = true;

      this.dialogTitle = "Update user information";
    },
    handleDelete(id){
      console.log(id)
      request.delete("/user/" + id).then(res => {
        if (res.code === '0') {
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
    handleSizeChange(pageSize){
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum){
      this.currentPage = pageNum;
      this.load();
    },
    showUser(){
      this.mode = 0;
      this.load();
    },
    showProfile(){
      this.mode = 1;
      this.load();
    },
    testHobby(){
      request.get("hobby/page", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          query: this.query
        }
      }).then(res =>{
        this.tableData = res.data.records;
        this.total = res.data.total;
        console.log(this.tableData)
      })
    }
  },

}



</script>
