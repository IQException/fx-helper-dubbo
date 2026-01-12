package com.iqexception.fxhelper.common.constant;


/**
 * 通用错误码：成功：0; 客户端错误：100XX; 服务端错误：900XX；
 */
public class ErrorCode {

    /**
     * 成功
     */
    public static final int SUCCESS = 0;

    /**
     * 参数错误
     */
    public static final int PARAM_ERROR = 10001;

    /**
     * 签名错误
     */
    public static final int SIGN_ERROR = 10002;

    /**
     * 密码错误
     */
    public static final int PASSWORD_ERROR = 10003;

    /**
     * 系统异常
     */
    public static final int SYSTEM_ERROR = 90001;

    /**
     * 数据库异常
     */
    public static final int DB_ERROR = 90002;

    /**
     * 请求其他服务异常
     */
    public static final int REQUEST_ERROR = 90003;


    /**
     * 用户不存在
     */
    public static final int USER_NOT_EXIST = 100001;

    public static final int USER_NO_LOGIN = 100002;

    public static final int USER_SESSION_EXPIRED = 100003;

}
