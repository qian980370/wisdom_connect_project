<template>


  <body>

  <div class="header">
    <h2 >wisdom connect</h2>
  </div>

  <div class="blockedlist_container">
    <div class="blockedlist_form">
      <div class="blockedlist_form_header" style="margin: 30px auto"><p>Your Blocked List</p></div>

      <hr>

      <div class="blockedlist_display_header"><p>These people will not show up in your call list</p></div>


      <div class="blockedlist_display_container">
        <!--------Table------>

        <div class="blockedlist_display_content">
          <table>
            <tr>
              <td><img src="../image/flower1.png"></td>
              <td><p>James</p></td>
            </tr>
          </table>
        </div>

        <div class="blockedlist_display_content">
          <table>
            <tr>
              <td><img src="../image/flower1.png"></td>
              <td><p>James</p></td>
            </tr>
          </table>
        </div>

        <!--------Table------>
        <div class="blockedlist_display_content">
          <table>
            <tr>
              <td> <img src="../image/flower1.png"></td>
              <td><p>James</p></td>
            </tr>
          </table>
        </div>
        <!--------Table------>
        <div class="blockedlist_display_content">
          <table>
            <tr>
              <td> <img src="../image/flower1.png"></td>
              <td><p>James</p></td>
            </tr>
          </table>
        </div>
      </div>
    </div>

  </div>
  </body>
</template>

<script>
export default {
  name: "BlockedList",

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
.header{
  height: 70px;
  width: 292px;
  /* text-align: center; */
  font-size:19px;
  text-align: right;
  line-height: 70px;
  background-image: url(../image/WechatIMG2010.png);
  background-size: 100px 70px;
  background-repeat: no-repeat;
  background-position: left center;
  margin: 70px auto 0;
}
#logo_and_title{
  width: 400px;
  height: 120px;
  text-align: center;
  padding-left: 37%;
  padding-top: 2%;
}
.blockedlist_container{
  /* background: url('jack-finnigan-M9EctVUPrp4-unsplash.jpg') no-repeat center center fixed; */

  height: 1000px;
  /* background-color: #f3e6f7; */
  /* padding: 50px; */
  /* background-color: azure; */
}

.blockedlist_form{
  width: 540px;
  height: 740px;
  /* border: 2px solid red; */
  margin:20px auto;
  text-align: center;
  border:solid 2px;
  border-color:#864a98;
  border-radius: 5px;
}
.blockedlist_form_header p{
  font-size: 28px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
}
.blockedlist_display_header{
  margin-top: 30px;
}
.blockedlist_display_header p{
  font-size: 18px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
}

.blockedlist_display_container{
  width: 440px;
  overflow-y:auto;
  overflow-x: hidden;
  height: 460px;
  border: solid 2px #bfa0c8;
  border-radius: 5px;
  margin-left: 50px;
  margin-top: 40px;
}

.blockedlist_display_container::-webkit-scrollbar{
  width: 10px;
}
.blockedlist_display_container::-webkit-scrollbar-thumb{
  background-color: #bfa0c8;
  border-radius: 5px;
}
.blockedlist_display_container::-webkit-scrollbar-button{
  display: none;
}
.blockedlist_display_container::-webkit-scrollbar-track{
  background-color: #f3f3f3;
}


.blockedlist_display{
  height: 480px;
  width: 400px;
  margin-left: 70px;
  border:dotted;
  border-radius: 5px;
  border-width:2px;
  border-color: #864a98;
}

.blockedlist_display_content{
  width: 400px;
  height: 120px;
  background-color:#f3f3f3;
  margin-bottom: 10px;
  margin-left: 15px;
  margin-top: 10px;
}
.blockedlist_display_content img{
  width: 100px;
  height: 100px;
}

.blockedlist_display_content td{
  width: 180px;
  height: 120px;
}

.blockedlist_display_content p{
  font-size: 18px;
  color:#864a98;
  font-weight: bold;
}


</style>

