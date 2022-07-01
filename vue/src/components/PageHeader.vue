<template>
  <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex">
    <div style="width: 200px; padding-left: 25px; font-weight: bold; ">Back-end Control</div>
    <div style="flex: 1"></div>
    <div style="width: 100px">
      <el-dropdown>
        <span class="el-dropdown-link">
          <br>{{this.username}}<el-icon class="el-icon--right"><ArrowDown/></el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>Personal Information</el-dropdown-item>
            <el-dropdown-item @click="logout">logout</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import {ArrowDown, Document, LocationFilled, MapLocation, Money, Setting} from "@element-plus/icons-vue";

export default {
  name: "myHeader",
  components:{
    ArrowDown
  },
  methods:{
    logout(){
      localStorage.removeItem("user");
      localStorage.removeItem("profile");
      this.$message({
        type: "success",
        message: "Successfully logout"
      })
      this.$router.push("/login")
    },
  },
  data() {
    return{
      user : localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null,
      username: '',
    }
  },
  created() {
    this.username = this.user.username;
  }

}
</script>

<style scoped>

</style>