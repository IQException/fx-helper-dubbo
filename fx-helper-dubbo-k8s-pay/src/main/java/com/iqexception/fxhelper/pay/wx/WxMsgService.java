package com.iqexception.fxhelper.pay.wx;

import com.iqexception.fxhelper.common.BaseService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.constant.CommonConstants;
import com.iqexception.fxhelper.pay.constant.BillType;
import com.iqexception.fxhelper.pay.stub.MiscServiceStub;
import com.iqexception.fxhelper.pay.wx.model.BillMessage;
import com.iqexception.fxhelper.pay.wx.model.ValueObject;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Component
public class WxMsgService extends BaseService {

    private static final String BILL_MESSAGE_ID = "tXNxjN_zMuK-ud_Ai7mhp6Jy7YDbS6XRd6I6I0wpoPg";

    public static final String PAGE_SHOP_DETAIL = "pages/shop/shop_detail/shop_detail";

    private final MiscServiceStub miscServiceStub;

    public WxMsgService(MessageSource messageSource,
                        JsonMapper jsonMapper,
                        MiscServiceStub miscServiceStub) {
        super(messageSource, jsonMapper);
        this.miscServiceStub = miscServiceStub;
    }

    public void sendBillMessage(BillType billType,
                                Long shopId,
                                String shopName,
                                String openId,
                                BigDecimal amount,
                                Long orderNo,
                                LocalDateTime createTime) {


        miscServiceStub.sendMessageQuietly(
                BILL_MESSAGE_ID,
                openId,
                PAGE_SHOP_DETAIL + "?shopId=" + shopId,
                jsonMapper.serialize(buildBillMessage(billType, shopName, amount, orderNo, createTime)));

    }

    private BillMessage buildBillMessage(BillType billType,
                                         String shopName,
                                         BigDecimal amount,
                                         Long orderNo,
                                         LocalDateTime createTime) {

        BillMessage message = new BillMessage();
        message.setShopName(new ValueObject(shopName));
        message.setAmount(new ValueObject("￥" + amount.setScale(2, RoundingMode.DOWN)));
        message.setCreateTime(new ValueObject(createTime.format(CommonConstants.BASIC_FORMATTER)));
        message.setOrderNo(new ValueObject(orderNo.toString()));
        message.setBillType(new ValueObject(billType.getVal()));

        return message;

    }
}
