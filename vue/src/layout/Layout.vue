<template>
  <!--    header-->
  <PageHeader/>
  <!--    body-->
  <div style="display: flex">
    <!--      aside-->
    <Aside/>
    <!--      main-part-->
    <router-view/>
  </div>
</template>

<script>
import PageHeader from "@/components/PageHeader";
import Aside from "@/components/Aside";

export default {
  name: "Layout",
  components:{
    PageHeader,
    Aside
  }
}
</script>

<style scoped>

</style>