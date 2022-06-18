
<template>


</template>

<script>
export default {
  name: "PersonalChange",
  methods:{
    handleLicensePreview(res){
      const isLt2M = res.size / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.$message.error('Avatar picture size can not exceed 2MB!');
      } else {
        //store file
        // this.img = URL.createObjectURL(res.raw);
      }
    },
    handleAvatarSuccess(res){
      // console.log(res);
      this.form.icon = res;

      this.img = res;
      // console.log(this.img)
      this.load();
      this.$message({
        type: "success",
        message: "Successfully upload file"
      })
    },
    beforeLicenseUpload(res){
      //console.log(res);
      if (res.type !== 'image/jpeg' && res.type !== 'image/png' && res.type !== 'image/jpg') {
        this.$message({
          type: "error",
          message: 'Avatar picture must be JPG format!'
        })
        return false
      } else if (res.size / 1024 / 1024 > 2) {
        this.$message({
          type: "error",
          message: 'Avatar picture size can not exceed 2MB!'
        })
        return false
      }
      return true

    },
  }
}
</script>

<style scoped>

</style>
