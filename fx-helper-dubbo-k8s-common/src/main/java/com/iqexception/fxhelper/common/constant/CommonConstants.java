package com.iqexception.fxhelper.common.constant;

import java.time.format.DateTimeFormatter;

public class CommonConstants {

    /**
     * yyyy-MM-dd HH:mm:SS
     */
    public static final DateTimeFormatter BASIC_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final String MDC_USER_ID = "userId";

    public static final String MDC_OPEN_ID = "openId";

    public static final String MDC_SHOP_ID = "shopId";

    public static final String MDC_ACCOUNT_ID = "accountId";

    public static final String MDC_ACCOUNT_ID_FROM = "fromAccountId";

    public static final String MDC_ACCOUNT_ID_TO = "toAccountId";

    public static final String MDC_ORDER_ID = "orderId";

    public static final String HEADER_USER_ID = "FX-USER-ID";

    public static final String HEADER_OPEN_ID = "FX-OPEN-ID";

    public static final String HEADER_USER_TOKEN = "FX-USER-TOKEN";



}
