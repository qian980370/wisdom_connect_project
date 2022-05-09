<template>
  <el-button type="text" @click="$router.push('/second')">start</el-button>
</template>

<script>
export default {
  name: "BlockedList",
  created() {
    this.load();
  },
  methods: {
    load() {
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

