<template>
  <el-menu
      style="width: 200px; min-height: calc(100vh - 50px)"
      default-active="2"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
  >
    <el-sub-menu index="1">
      <template #title>
        <el-icon><map-location/></el-icon>
        <span>User List</span>
      </template>

      <el-menu-item index="1-1">All</el-menu-item>
      <el-menu-item index="1-2">User</el-menu-item>
      <el-menu-item index="1-3">Nurse</el-menu-item>
      <el-menu-item index="1-4">Manager</el-menu-item>

    </el-sub-menu>

    <el-menu-item index="2">
      <el-icon><money/></el-icon>
      <span>ALL USERS</span>

    </el-menu-item>

    <el-menu-item index="4">
      <el-icon><setting/></el-icon>
      <span>DEVELOPING</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
import {Document, LocationFilled, MapLocation, Money, Setting} from "@element-plus/icons-vue";
export default {
  name: "Aside",
  components: {Money, MapLocation, LocationFilled, Document, Setting}
}
</script>

<style scoped>

</style>