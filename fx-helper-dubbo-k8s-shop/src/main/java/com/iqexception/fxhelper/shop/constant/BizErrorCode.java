package com.iqexception.fxhelper.shop.constant;


import com.iqexception.fxhelper.common.constant.ErrorCode;

/**
 * 业务错误码：AA-BB-CC：
 * AA表示业务或项目(1-9预留给通用错误），10代表用户，20代表订单；
 * BB表示更细分的业务模块， 默认00 直接表示AA业务下的错误；
 * CC表示具体的错误。
 */
public class BizErrorCode extends ErrorCode {

    /**
     * 商家不存在
     */
    public static final int SHOP_NOT_EXIST = 200001;

    /**
     * 返现关闭
     */
    public static final int SHOP_CLOSED = 200002;

    /**
     * 序列号不存在
     */

    public static final int SERIAL_NO_NOT_EXIST = 200003;
}
