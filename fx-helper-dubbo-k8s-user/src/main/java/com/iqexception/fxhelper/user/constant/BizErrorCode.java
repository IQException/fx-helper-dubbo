package com.iqexception.fxhelper.user.constant;


import com.iqexception.fxhelper.common.constant.ErrorCode;

/**
 * 业务错误码：AA-BB-CC：
 * AA表示业务或项目(1-9预留给通用错误），10代表用户，20代表订单；
 * BB表示更细分的业务模块， 默认00 直接表示AA业务下的错误；
 * CC表示具体的错误。
 */
public class BizErrorCode extends ErrorCode {

    /**
     * 用户不存在
     */
    public static final int USER_NOT_EXIST = 100001;

    public static final int USER_NO_LOGIN = 100002;

    public static final int USER_SESSION_EXPIRED = 100003;

    public static final int USER_AUTH_REQUIRED = 100004;


    /**
     * 商家不存在
     */
    public static final int SHOP_NOT_EXIST = 200001;

    /**
     * 返现关闭
     */
    public static final int SHOP_CLOSED = 200002;


    public static final int ACCOUNT_FROM_NOT_EXIST = 300001;

    public static final int ACCOUNT_TO_NOT_EXIST = 300002;

    public static final int ACCOUNT_BALANCE_INSUFFICIENT = 300003;


    public static final int ORDER_NOT_EXIST = 400001;

    public static final int ORDER_DUPLICATE = 400002;


    public static final int MESSAGE_NOT_EXIST = 500001;

    public static final int MESSAGE_NOT_SUBSCRIBED = 500002;

}
