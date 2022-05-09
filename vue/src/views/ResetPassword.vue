<template>
  <head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
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

  <div class="login_container">
    <div class="login_form">
      <div class="login_form_header"><p>Reset Password</p></div>

      <div class="login_account" style="margin-bottom:10px ;">
        <input type="input" placeholder="Username/Email/Phone Number" id="input1">
      </div>
      <div class="login_password" style="margin-bottom:10px ;">
        <input type="password" placeholder="New Password" id="input2">
      </div>
      <div class="login_password_confirm">
        <input type="password" placeholder="Confirm Password" id="input3">
      </div>

      <div class="login_facility_confirm">
        <a href="profiles.html"><button id="login_btn"> Facility Login</button></a>
      </div>
      <!-- <div class="login_create">
          <button id="b2">Facility Signup</button>
      </div> -->

    </div>

  </div>
  </body>

</template>

<script>
export default {
  name: "ResetPassword"
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
.login_container{
  /* background: url('jack-finnigan-M9EctVUPrp4-unsplash.jpg') no-repeat center center fixed; */

  height: 640px;
  /* background-color: #f3e6f7; */
  /* padding: 50px; */
  /* background-color: azure; */
}
.login_main_form{
  width: 540px;
  height: 540px;
  margin:180px auto;
  /* background-color: #f3f3f3; */
  text-align: center;
  border-radius: 5px;
  border: solid 2px;
  border-color: #864a98;

}

.login_form{
  width: 540px;
  height: 540px;
  margin:20px auto;
  background-color: #f3f3f3;
  text-align: center;
  border-radius: 5px;
}

#login_logo_and_title{
  width: 400px;
  height: 120px;
  text-align: center;
  padding-left: 22%;
  padding-top: 12%;
}

.login_form_header p{
  font-size: 28px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
  padding: 40px;
}

.login_form input{
  /* padding: 10px; */
  height: 50px;
  width: 360px;
  border: 0px;
  padding-left: 10px;
  font-size: 18px;
  border-radius: 5px;
  outline-color: #bfa0c8;
  /* margin-bottom: 10px; */
}

.login_facility_login button{
  height: 44px;
  width: 240px;
  background-color: #bfa0c8;
  color: white;
  border: 0px;
  /* margin-top: 45px; */
  margin-top: 140px;
  font-size: 18px;
  border-radius: 5px;
}
.login_facility_signup button{
  height: 44px;
  width: 240px;
  background-color: #bfa0c8;
  border: 0px;
  margin-top: 20px;
  font-size: 18px;
  color:white;
  margin-bottom: 40px;
  border-radius: 5px;

}


.login_confirm button{
  height: 44px;
  width: 240px;
  background-color: #bfa0c8;
  color: white;
  border: 0px;
  margin-top: 45px;
  font-size: 18px;
  border-radius: 5px;
}
.login_create button{
  height: 44px;
  width: 240px;
  background-color: #bfa0c8;
  border: 0px;
  margin-top: 20px;
  font-size: 18px;
  color:white;
  margin-bottom: 40px;
  border-radius: 5px;

}

#hint_text{
  margin-top: 20px;
}
#hint_text p{
  color: #bfbfbf;
}
#hint_text p:hover{
  color:#864a98;
}

.login_agreement input{

}

.login_facility_confirm button{
  height: 44px;
  width: 240px;
  background-color: #bfa0c8;
  color: white;
  border: 0px;
  margin-top: 70px;
  margin-bottom: 70px;
  font-size: 18px;
  border-radius: 5px;
}

</style>
