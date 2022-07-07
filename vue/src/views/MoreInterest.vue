<template>
  <head>
    <meta charset="UTF-8">
    <title>More Interests</title>
  </head>

  <body>

  <div id="logo_and_title">
    <table>
      <tr>
        <td width="100px"><img  src="../image/logo.png" alt="logo" width="80px"></td>
        <td><h1>Wisdom Connect</h1></td>
      </tr>
    </table>
  </div>

  <div class="more_interests_container">
    <div class="more_interests_form">
      <div class="more_interests_form_header"><p>Add Interests</p></div>

      <hr>

      <div class="more_interests_display_header"><p>Click to add in your list</p></div>


      <div class="more_interests_display_container">
        <!--------Table------>
        <div class="more_interests_display_content">
          <table>
            <tr>
              <td>
                <div class="more_interests_display_content_img">
                  <button id="add_interest_btn"></button>
                </div>
                <p>Basketball</p>
              </td>

            </tr>
          </table>
        </div>


      </div>

      <div class="more_interests_buttons">
        <table>
          <tr>
            <td><a href="javascript:"><button id="more_interests_add_btn">Add</button></a></td>
            <td><a href="javascript:"><button id="more_interests_refresh_btn">Refresh</button></a></td>
          </tr>
        </table>
      </div>

    </div>

  </div>

  </body>

</template>

<script>
export default {
  name: "MoreInterest"
}
</script>

<style scoped>
#logo_and_title{
  width: 400px;
  height: 120px;
  text-align: center;
  padding-left: 37%;
  padding-top: 2%;
}
.more_interests_container{
  /* background: url('jack-finnigan-M9EctVUPrp4-unsplash.jpg') no-repeat center center fixed; */

  height: 1000px;
  /* background-color: #f3e6f7; */
  /* padding: 50px; */
  /* background-color: azure; */
}

.more_interests_form{
  width: 540px;
  height: 800px;
  /* border: 2px solid red; */
  margin:20px auto;
  text-align: center;
  border:solid 2px;
  border-color:#864a98;
  border-radius: 5px;
}
.more_interests_form_header p{
  width: 370px;
  font-size: 28px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
}
.interests_display_header{
  margin-top: 30px;
}
.more_interests_display_header p{
  font-size: 18px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
}

.more_interests_display_container{
  width: 440px;
  overflow-y:auto;
  overflow-x: hidden;
  height: 460px;
  border: solid 2px #bfa0c8;
  border-radius: 5px;
  margin-left: 50px;
  margin-top: 40px;
}

.more_interests_display_container::-webkit-scrollbar{
  width: 10px;
}
.more_interests_display_container::-webkit-scrollbar-thumb{
  background-color: #bfa0c8;
  border-radius: 5px;
}
.more_interests_display_container::-webkit-scrollbar-button{
  display: none;
}
.more_interests_display_container::-webkit-scrollbar-track{
  background-color: #f3f3f3;
}


.more_interests_display{
  height: 480px;
  width: 400px;
  margin-left: 70px;
  border:dotted;
  border-radius: 5px;
  border-width:2px;
  border-color: #864a98;
}

.more_interests_display_content{
  width: 400px;
  height: 140px;
  margin-top: 20px;
  margin-bottom: 10px;
  margin-left: 15px;
}
.more_interests_display_content img{
  width: 100px;
  height: 100px;
  cursor: pointer;
  z-index: 0px;
}

.more_interests_display_content td{
  width: 240px;
  height: 150px;
  border: solid 2px white;
}
.more_interests_display_content td:hover{
  width: 240px;
  height: 150px;
  border: solid 2px #bfa0c8;
}
.more_interests_display_content tr{
  padding-top: 40px;
}

.more_interests_display_content p{
  font-size: 14px;
  color:#864a98;
  font-weight: bold;
  text-align: center;
}
.more_interests_buttons{
  margin-left: 50px;
}

.more_interests_display_content_img{
  position: relative;
}

#add_interest_btn{
  position: absolute;
  height: 30px;
  width: auto;
  z-index: 20px;
  left: 96px;
}


#more_interests_add_btn{
  height: 44px;
  width: 215px;
  background-color: #bfa0c8;
  color: white;
  border: 0px;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
#more_interests_add_btn:hover{
  height: 44px;
  width: 215px;
  background-color: white;
  color: #bfa0c8;
  border: solid 2px #bfa0c8;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
#more_interests_add_btn:active{
  height: 44px;
  width: 215px;
  background-color: white;
  color: #bfa0c8;
  border: solid 2px #bfa0c8;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
#more_interests_refresh_btn{
  height: 44px;
  width: 215px;
  background-color: #bfa0c8;
  color: white;
  border: 0px;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
#more_interests_refresh_btn:hover{
  height: 44px;
  width: 215px;
  background-color: white;
  color: #bfa0c8;
  border: solid 2px #bfa0c8;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
#more_interests_refresh_btn:active{
  height: 44px;
  width: 215px;
  background-color: white;
  color: #bfa0c8;
  border: solid 2px #bfa0c8;
  margin-top: 70px;
  font-size: 18px;
  border-radius: 5px;
}
</style>
