<template>

</template>

<script>
import request from "@/utils/request";

export default {
  name: "NewProfile",
  data(){
    return{
      ProfileForm:{},
      rules: {
        username: [
          {required: true, message: 'please input username', trigger: 'blur'},
        ],

      },
    }
  },
  created() {
    this.load();
  },
  methods: {
    load() {
    },
    AddProfile() {
      console.log("create");
      request.post("profile", this.form).then(res => {
        console.log(res);
        if (res.code === '200') {
          this.$message({
            type: "success",
            message: "Successfully add profile"
          })
          this.$router.push("/profilelogin")
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    },


  }
}

</script>

<style scoped>

</style>
#hobby接口
