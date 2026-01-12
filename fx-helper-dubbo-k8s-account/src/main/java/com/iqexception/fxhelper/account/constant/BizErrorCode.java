package com.iqexception.fxhelper.account.constant;


import com.iqexception.fxhelper.common.constant.ErrorCode;

/**
 * 业务错误码：AA-BB-CC：
 * AA表示业务或项目(1-9预留给通用错误），10代表用户，20代表订单；
 * BB表示更细分的业务模块， 默认00 直接表示AA业务下的错误；
 * CC表示具体的错误。
 */
public class BizErrorCode extends ErrorCode {


    public static final int ACCOUNT_FROM_NOT_EXIST = 300001;

    public static final int ACCOUNT_TO_NOT_EXIST = 300002;

    public static final int ACCOUNT_BALANCE_INSUFFICIENT = 300003;

}
