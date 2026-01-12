CREATE DATABASE fx;
USE fx;

CREATE TABLE  fx_user
(
    user_id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    nick_name   VARCHAR(20)  NOT NULL,
    avatar      VARCHAR(255) NOT NULL,
    open_id     VARCHAR(255) NOT NULL UNIQUE,
    union_id    VARCHAR(255),
    session_key VARCHAR(255),
    phone       CHAR(11),
    pay_secret  CHAR(64),
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE INDEX  fx_user_idx_nick_name ON fx_user (nick_name);
CREATE INDEX  fx_user_idx_open_id ON fx_user (open_id);
CREATE INDEX  fx_user_idx_union_id ON fx_user (union_id);
CREATE INDEX  fx_user_idx_session_key ON fx_user (session_key);
CREATE INDEX  fx_user_idx_phone ON fx_user (phone);
CREATE INDEX  fx_user_idx_created_at ON fx_user (created_at);
CREATE INDEX  fx_user_idx_updated_at ON fx_user (updated_at);

CREATE TABLE fx_account
(
    account_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT         NOT NULL UNIQUE,
    balance    NUMERIC(10, 2) NOT NULL DEFAULT 0.0,
    --  0 正常 其他不正常
    status     INTEGER        NOT NULL DEFAULT 0,
    created_at TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE INDEX fx_account_idx_created_at ON fx_account (created_at);
CREATE INDEX fx_account_idx_updated_at ON fx_account (updated_at);



CREATE TABLE fx_wx_msg_subs
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    open_id     VARCHAR(255) NOT NULL,
    template_id VARCHAR(255) NOT NULL,
    count       INTEGER      NOT NULL DEFAULT 1,
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE UNIQUE INDEX fx_wx_msg_subs_idx_open_id__template_id on fx_wx_msg_subs (open_id, template_id);

CREATE TABLE fx_wx_message
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    open_id     VARCHAR(255)  NOT NULL,
    template_id VARCHAR(255)  NOT NULL,
    page        VARCHAR(255),
    msg_body    VARCHAR(2000) NOT NULL,
    -- 0 created 1 succeed -1 failed
    status      INTEGER       NOT NULL DEFAULT 0,
    fail_msg    VARCHAR(2000),
    created_at  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE INDEX fx_message_idx_open_id ON fx_wx_message (open_id);
CREATE INDEX fx_message_idx_template_id ON fx_wx_message (template_id);
CREATE INDEX fx_message_idx_created_at ON fx_wx_message (created_at);
CREATE INDEX fx_message_idx_updated_at ON fx_wx_message (updated_at);

CREATE TABLE fx_order
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    shop_id    BIGINT         NOT NULL,
    user_id    BIGINT         NOT NULL,
    capture    VARCHAR(255)   NOT NULL,
    amount     NUMERIC(10, 2) NOT NULL,
    fx_time    TIMESTAMP,
    -- 0 待审核 1 已返现 2 返现中 9 返现失败
    status     INTEGER        NOT NULL DEFAULT 0,
    -- 序列号 同一店铺每个二维码都有一个唯一的序列号，防止重复返现
    serial_no  VARCHAR(100)   NOT NULL,
    fail_msg   VARCHAR(2000),
    created_at TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE INDEX fx_order_idx_shop_id ON fx_order (shop_id);
CREATE INDEX fx_order_idx_user_id ON fx_order (user_id);
CREATE INDEX fx_order_idx_fx_time ON fx_order (fx_time);
CREATE UNIQUE INDEX fx_order_idx_shop_id__serial_no ON fx_order (shop_id, serial_no);
CREATE INDEX fx_order_idx_created_at ON fx_order (created_at);
CREATE INDEX fx_order_idx_updated_at ON fx_order (updated_at);

CREATE TABLE fx_serial_no
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    shop_id    BIGINT       NOT NULL,
    serial_no  VARCHAR(100) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX fx_serial_idx_no_created_at ON fx_serial_no (created_at);
CREATE UNIQUE INDEX fx_serial_idx_no_shop_i__serial_no ON fx_serial_no (shop_id, serial_no);

CREATE TABLE fx_pay_info
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    -- 1 余额 2 微信
    from_channel   INTEGER        NOT NULL,
    from_account   VARCHAR(200)   NOT NULL,
    from_trx_id    VARCHAR(200),
    -- 1 余额 2 微信
    to_channel     INTEGER        NOT NULL,
    to_account     VARCHAR(200)   NOT NULL,
    to_trx_id      VARCHAR(200),
    biz_id         VARCHAR(200)   NOT NULL UNIQUE,
    amount         NUMERIC(10, 2) NOT NULL,
    status         VARCHAR(20),
    pay_acct_info  JSON,
    recv_acct_info JSON,
    created_at     TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE INDEX fx_pay_info_idx_from_account ON fx_pay_info (from_account);
CREATE INDEX fx_pay_info_idx_to_account ON fx_pay_info (to_account);
CREATE INDEX fx_pay_info_idx_biz_id ON fx_pay_info (biz_id);
CREATE INDEX fx_pay_info_idx_created_at ON fx_pay_info (created_at);
CREATE INDEX fx_pay_info_idx_updated_at ON fx_pay_info (updated_at);

CREATE TABLE  fx_shop
(
    shop_id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    owner_user_id BIGINT       NOT NULL,
    shop_name     VARCHAR(50)  NOT NULL,
    logo          VARCHAR(255) NOT NULL,
    intro         VARCHAR(255) NOT NULL,
    address       VARCHAR(255) NOT NULL,
    --  0 关闭返现 1 开启返现
    status        INTEGER      NOT NULL DEFAULT 0,
    phone         CHAR(11)     NOT NULL,
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE INDEX fx_shop_idx_shop_name  ON fx_shop (shop_name);
CREATE INDEX fx_shop_idx_phone  ON fx_shop (phone);
CREATE INDEX fx_shop_idx_owner_user_id ON fx_shop (owner_user_id);
CREATE UNIQUE INDEX fx_shop_idx_shop_name__address ON fx_shop (shop_name, address);
CREATE INDEX fx_shop_idx_created_at ON fx_shop (created_at);
CREATE INDEX fx_shop_idx_updated_at ON fx_shop (updated_at);

