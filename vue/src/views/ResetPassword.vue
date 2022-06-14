<template>
  <head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
  </head>

  <body>

  <div class="logo_and_title">
    <table>
      <tr>
        <td><img  src="../image/logo.png" alt="logo"></td>
        <td><h1>Wisdom Connect</h1></td>
      </tr>
    </table>
  </div>

  <div class="reset_password_container">
    <div class="reset_password_form">
      <div class="reset_password_header"><p>Reset Password</p></div>

      <div class="reset_password_account" style="margin-bottom:10px ;">
        <input type="input" placeholder="Username/Email/Phone Number" id="input1">
      </div>
      <div class="reset_password_password" style="margin-bottom:10px ;">
        <input type="password" placeholder="New Password" id="input2">
      </div>
      <div class="reset_password_confirm_password">
        <input type="password" placeholder="Confirm Password" id="input3">
      </div>

      <div class="reset_password_confirm">
        <button id="login_btn" @click="$router.push('/profilelogin')"> Facility Login</button>
      </div>

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
.logo_and_title{
  width: 400px;
  height: 120px;
  text-align: center;
  padding-left: 37%;
  padding-top: 2%;
}
.logo_and_title img{
  width: 80px;
}
.logo_and_title h1{
  width: 300px;
}
.reset_password_container{
  height: 640px;

}
.reset_password_form{
  width: 540px;
  height: 540px;
  margin:20px auto;
  background-color: #f3f3f3;
  text-align: center;
  border-radius: 5px;
}

.reset_password_header p{
  font-size: 28px;
  font-weight: bold;
  color:#864a98;
  text-align: center;
  padding: 40px;
}

.reset_password_form input{
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



.reset_password_confirm button{
  height: 44px;
  width: 240px;
  background-color: #bfa0c8;
  color: white;
  border: 0px;
  margin-top: 70px;
  margin-bottom: 70px;
  font-size: 18px;
  border-radius: 5px;
  cursor: pointer;
}
.reset_password_confirm button:hover{
  height: 44px;
  width: 240px;
  background-color:#f3f3f3;
  color: #bfa0c8;
  border: solid 2px  #bfa0c8;
  margin-top: 70px;
  margin-bottom: 70px;
  font-size: 18px;
  border-radius: 5px;
  cursor: pointer;
}
.reset_password_confirm button:active{
  height: 44px;
  width: 240px;
  background-color:#f3f3f3;
  color: #bfa0c8;
  border: solid 2px  #bfa0c8;
  margin-top: 70px;
  margin-bottom: 70px;
  font-size: 18px;
  border-radius: 5px;
  cursor: pointer;
}
</style>
