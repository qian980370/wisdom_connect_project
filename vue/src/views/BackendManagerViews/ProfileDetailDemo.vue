<template>
  <div style="padding: 10px; width: 100%">
    <div style="margin: 10px 0">
      <span>current profile: {{user.username}}</span>
    </div>

    <!--table of data-->
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
          <el-popconfirm title="Are you sure to delete this?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="text" size="small" >Delete</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>

    </el-table>
    <!--page control-->
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
      total: 0,
      currentPage: 1,
      pageSize: 10,
      pageNum: 10,
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
      user : localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null,
      uploadHeaders:{},
    }

  },
  created() {
    this.load();
    // console.log(this.user);
    this.uploadHeaders.token = this.user.token;
    // console.log(this.uploadHeaders);
  },
  methods:{

    refreshUser(){
      this.user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    },
    // handleClose(){
    //   this.dialogVisible = false;
    // },
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
        this.friendTableData = res.data.records;
        this.total = res.data.total;
      })
    },

    handleDelete(id){
      //console.log(id)
      request.delete("/profile/" + id).then(res => {
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
    },
  },
}
</script>

<style scoped>

</style>