<template>
  <div style="padding: 10px; width: 100%">
    <div style="margin: 10px 0">
      <span>current user: {{user.username}}</span>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="addUser">New User</el-button>
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
          prop="lastLogin"
          label="Last Login Time">
      </el-table-column>
      <el-table-column
          prop="role"
          label="Role">
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
          prop="address"
          label="Address">
      </el-table-column>

      <el-table-column
          prop="abn"
          label="ABN">
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
    </div>

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
          <el-form-item label="Role">
            <el-radio v-model="form.role" label="manager" @click="notChooseFacility">Manager</el-radio>
            <el-radio v-model="form.role" label="facility" @click="chooseFacility">Facility</el-radio>
            <el-radio v-model="form.role" label="resident" @click="notChooseFacility">Resident</el-radio>
            <el-radio v-model="form.role" label="nurse" @click="notChooseFacility">Nurse</el-radio>
          </el-form-item>
          <el-form-item label="ABN">
            <el-input type="abn" :disabled="!this.isFacility" v-model="form.abn" />
          </el-form-item>
          <el-form-item label="Address">
            <el-input type="address" v-model="form.address" />
          </el-form-item>
          <el-form-item label="Fast Code">
            <el-input :disabled="!this.isFacility" v-model="form.code" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onApplyFastCode">Get FastCode</el-button>

            <el-button type="primary" @click="onSubmit">Apply</el-button>

            <el-button @click="dialogVisible = false">Cancel</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'UserControllerView',
  components: {

  },
  data(){
    return{
      isFacility: false,
      loginForm:{},
      form: {},
      formProfile: {},
      dialogTitle:"Create new user",
      dialogLoginTitle: "login",
      dialogVisible: false,
      query: '',
      total: 0,
      currentPage: 1,
      pageSize: 10,
      pageNum: 10,
      search: '',
      mode: 0,
      fastCodeFrom:{},
      tableData:[

      ],
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }

  },
  created() {
    this.load();
  },
  methods:{
    onApplyFastCode(){
      this.fastCodeFrom.name = this.form.username;
      this.fastCodeFrom.email = this.form.email;
      this.fastCodeFrom.address = this.form.address;
      this.fastCodeFrom.abn = this.form.abn;
      this.fastCodeFrom.role = this.form.role;
      request.post("/fastcode/create", this.fastCodeFrom).then(res =>{
        console.log(res);
        if (res.code === '200'){
          this.$message({
            type: "success",
            message: "Successfully Apply user fast code"
          })
        }else{
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      });
    },
    chooseFacility(){
      this.isFacility = true;
      console.log(this.isFacility);
    },
    notChooseFacility(){
      this.isFacility = false;
      console.log(this.isFacility);
    },
    refreshUser(){
      this.user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    },
    load(){
      request.get("/user/page", {
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
    },
    addUser(){
      this.dialogVisible = true;
      this.form = {};
      this.dialogTitle = "Create new user";
      this.isFacility = false;
    },
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
        console.log("create");
        if (this.form.role !== "facility"){
          this.form.abn = null;
        }
        console.log(this.form);
        request.post("/user/register", this.form).then(res =>{
          console.log(res);
          if (res.code === '200'){
            this.$message({
              type: "success",
              message: "Successfully add user"
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
    }, handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible = true;
      this.dialogTitle = "Update user information";
    },
    handleDelete(id){
      console.log(id)
      request.delete("/user/" + id).then(res => {
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
    handleSizeChange(pageSize){
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum){
      this.currentPage = pageNum;
      this.load();
    }
  },

}
</script>

<style scoped>

</style>