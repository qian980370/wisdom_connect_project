package com.project.wisdomconnect.common;

public interface Constants {
    String fastCode_Wait = "wait for code";
    String fastCode_Set = "wait for user apply";
    String fastCode_Done = "done";

    String CODE_200 = "200"; // SUCCESS
    String CODE_500 = "500"; // system error

    String CODE_301 = "301"; // token invalid
    String CODE_302 = "302"; // special symbol error
    String CODE_400 = "400"; // password or username error
    String CODE_401 = "401"; // permission denied
    String CODE_402 = "402"; // register username error
    String CODE_403 = "403"; // register email error

    String CODE_405 = "405"; // invalid role
    String CODE_406 = "406"; // special img could not be disabled, plz contact backend developer
    String CODE_407 = "407"; // invalid abn error, only facility could have abn
    String CODE_408 = "408"; // invalid register profile name
    String CODE_409 = "409"; // number of profile(s) exceed limitation
    String CODE_410 = "410"; // abn replicate
    String CODE_411 = "411"; // information lost
    String CODE_412 = "412"; // invalid fast code
    String CODE_413 = "413"; // register information not match original fast code information
    String CODE_414 = "414"; // you have been submitted fast code form, please check your email and our manager will contact you as soon as possible
    String CODE_415 = "415"; // profile id missed
    String CODE_416 = "416"; // database result unmatched issue
    String CODE_417 = "417"; // repeat request error
    String CODE_418 = "418"; // room unavailable error
    String CODE_419 = "419"; // user has own a room error
    String CODE_420 = "420"; // user not have a room error

    String CODE_200_MESSAGE = "success"; // SUCCESS
    String CODE_500_MESSAGE = "system error"; // system error

    String CODE_301_MESSAGE = "token invalid"; // token invalid
    String CODE_302_MESSAGE = "special symbol error"; // special symbol error
    String CODE_400_MESSAGE = "password or username error"; // password or username error
    String CODE_401_MESSAGE = "permission denied "; // permission denied
    String CODE_402_MESSAGE = "owner not exist"; // register error
    String CODE_403_MESSAGE = "register email error"; // register error

    String CODE_405_MESSAGE = "invalid role"; // invalid role
    String CODE_406_MESSAGE = "special img could not be disabled, plz contact backend developer"; // special img could not be disabled, plz contact backend developer
    String CODE_407_MESSAGE = "invalid abn error, only facility could have abn"; // invalid abn error, only facility could have abn
    String CODE_408_MESSAGE = "duplicate profile name"; // invalid register profile name
    String CODE_409_MESSAGE = "number of profile(s) exceed limitation"; // number of profile(s) exceed limitation
    String CODE_410_MESSAGE = "abn replicate"; // abn replicate
    String CODE_411_MESSAGE = "information lost"; // information lost
    String CODE_412_MESSAGE = "invalid fast code"; // invalid fast code
    String CODE_413_MESSAGE = "register information not match original fast code information"; // register information not match original fast code information
    String CODE_414_MESSAGE = "you have been submitted fast code form, please check your email and our manager will contact you as soon as possible"; // you have been submitted fast code form, please check your email and our manager will contact you as soon as possible
    String CODE_415_MESSAGE = "profile id missed"; // profile id missed
    String CODE_416_MESSAGE = "database result unmatched issue"; // database result unmatched issue
    String CODE_417_MESSAGE = "repeat request error"; // repeat request error
    String CODE_418_MESSAGE = "room unavailable error"; // room unavailable error
    String CODE_419_MESSAGE = "user has own a room error"; // user has own a room error
    String CODE_420_MESSAGE = "user not have a room error"; // user not have a room error
}
