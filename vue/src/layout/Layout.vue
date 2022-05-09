<template>
  <!--    header-->
  <PageHeader/>

  <!--    body-->
  <div style="display: flex">
    <!--      aside-->
    <AsideComponent/>
    <!--      main-part-->
    <router-view/>
  </div>
</template>

<script>
import PageHeader from "@/components/PageHeader";
import Aside from "@/components/Aside";

import AsideComponent from "@/components/AsideComponent";

export default {
  name: "Layout",
  components:{
    PageHeader,
    Aside,
    AsideComponent
  }
}
</script>

<style scoped>

</style>