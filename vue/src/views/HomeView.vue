<template>
  <div style="padding: 10px; width: 100%">
    <div style="margin: 10px 0">
      <el-button type="primary" @click="addUser">New</el-button>
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
          prop="admin"
          label="Admin">
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
            <el-form-item label="Age">
              <el-input type="number" v-model="form.age" />
            </el-form-item>
            <el-form-item label="Address">
              <el-input v-model="form.address" />
            </el-form-item>
            <el-form-item label="Gender">
              <el-radio v-model="form.gender" label="Male" value="male" />
              <el-radio v-model="form.gender" label="Woman" value="woman" />
              <el-radio v-model="form.gender" label="Secret" value="secret" />
            </el-form-item>
            <el-form-item label="Administrator">
              <el-switch v-model="form.admin"
                         :active-value="1"
                         :inactive-value="0"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">Apply</el-button>
              <el-button>Cancel</el-button>
            </el-form-item>
          </el-form>
        </div>


<!--          <el-form-item label="Activity time">-->
<!--            <el-col :span="11">-->
<!--              <el-date-picker-->
<!--                  v-model="form.date1"-->
<!--                  type="date"-->
<!--                  placeholder="Pick a date"-->
<!--                  style="width: 100%"/>-->
<!--            </el-col>-->
<!--            <el-col :span="2" class="text-center">-->
<!--              <span class="text-gray-500">-</span>-->
<!--            </el-col>-->
<!--            <el-col :span="11">-->
<!--              <el-time-picker-->
<!--                  v-model="form.date2"-->
<!--                  placeholder="Pick a time"-->
<!--                  style="width: 100%"-->
<!--              />-->
<!--            </el-col>-->
<!--          </el-form-item>-->

<!--          <el-form-item label="Activity type">-->
<!--            <el-checkbox-group v-model="form.type">-->
<!--              <el-checkbox label="Online activities" name="type" />-->
<!--              <el-checkbox label="Promotion activities" name="type" />-->
<!--              <el-checkbox label="Offline activities" name="type" />-->
<!--              <el-checkbox label="Simple brand exposure" name="type" />-->
<!--            </el-checkbox-group>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="Resources">-->
<!--            <el-radio-group v-model="form.resource">-->
<!--              <el-radio label="Sponsor" />-->
<!--              <el-radio label="Venue" />-->
<!--            </el-radio-group>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="Activity form">-->
<!--            <el-input v-model="form.desc" type="textarea" />-->
<!--          </el-form-item>-->




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
      form: {},
      dialogTitle:"Create new user",
      dialogVisible: false,
      query: '',
      total: 0,
      currentPage: 1,
      pageSize: 10,
      pageNum: 10,
      search: '',
      tableData:[

      ],

    }

  },
  created() {
    this.load();
  },
  methods:{
    // handleClose(){
    //   this.dialogVisible = false;
    // },
    load(){
      
      request.get("user", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          query: this.query
        }

      }).then(res =>{
        this.tableData = res.data.records;
        this.total = res.data.total;
      })
    },
    addUser(){
      this.dialogVisible = true;
      this.form = {};
      this.dialogTitle = "Create new user";
    },

    onSubmit(){
      if (this.form.id){
        console.log("update");
        request.put("/user", this.form).then(res =>{
          console.log(res);
          if (res.code === '0'){
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
        request.post("/user", this.form).then(res =>{
          console.log(res);
          if (res.code === '0'){
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
    }
  }

}



</script>
