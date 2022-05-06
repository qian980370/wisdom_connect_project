package com.project.wisdomconnect.common;

public interface Constants {

    String CODE_200 = "200"; // SUCCESS
    String CODE_500 = "500"; // system error

    String CODE_301 = "301"; // token invalid
    String CODE_302 = "302";
    String CODE_400 = "400"; // password or username error
    String CODE_401 = "401"; // unauth error
    String CODE_402 = "402"; // register username error
    String CODE_403 = "403"; // register email error

    String CODE_405 = "405"; // invalid role
    String CODE_406 = "406"; // special img could not be disabled, plz contact backend developer
    String CODE_407 = "407"; // invalid abn error, only facility could have abn
    String CODE_408 = "408"; // invalid register profile name
    String CODE_409 = "409"; // number of profile(s) exceed limitation


    String CODE_200_MESSAGE = "success"; // SUCCESS
    String CODE_500_MESSAGE = "system error"; // system error

    String CODE_301_MESSAGE = "token invalid"; // token invalid
    String CODE_302_MESSAGE = "special symbol error";
    String CODE_400_MESSAGE = "password or username error"; // password or username error
    String CODE_401_MESSAGE = "unauth error"; // unauth error
    String CODE_402_MESSAGE = "register username error"; // register error
    String CODE_403_MESSAGE = "register email error"; // register error

    String CODE_405_MESSAGE = "invalid role"; // invalid role
    String CODE_406_MESSAGE = "special img could not be disabled, plz contact backend developer"; // special img could not be disabled, plz contact backend developer
    String CODE_407_MESSAGE = "invalid abn error, only facility could have abn"; // invalid abn error, only facility could have abn
    String CODE_408_MESSAGE = "invalid register profile name"; // invalid register profile name
    String CODE_409_MESSAGE = "number of profile(s) exceed limitation"; // number of profile(s) exceed limitation
}
