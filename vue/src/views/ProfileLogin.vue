<template>
  <el-button type="logout" @click="$router.push('/first')">start</el-button>
  <el-button type="Create a new profile" @click="$router.push('/newprofile')">start</el-button>
  <el-button type="text" @click="$router.push('/second')">start</el-button>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "ProfileLogin",
  created() {
    this.load();
  },
  methods: {
    load() {
      request.get("user", {
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
      request.get("profile", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          query: this.query
        }
      }).then(res => {
        console.log(res);
        this.tableData = res.data.records;
        this.total = res.data.total;
      })
    }
  }
}

</script>

<style scoped>

</style>

input
#user profile
#username

router
#to profileselect
#to home

