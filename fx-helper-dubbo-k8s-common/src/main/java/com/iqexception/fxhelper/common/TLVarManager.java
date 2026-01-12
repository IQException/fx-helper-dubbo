package com.iqexception.fxhelper.common;

public class TLVarManager {

    private static final ThreadLocal<Long> userId = new ThreadLocal<>();
    private static final ThreadLocal<String> openId = new ThreadLocal<>();
    private static final ThreadLocal<ClientInfo> clientInfo = new ThreadLocal<>();

    public static void setUserId(Long userId) {
        TLVarManager.userId.set(userId);
    }

    public static void setOpenId(String openId) {
        TLVarManager.openId.set(openId);
    }

    public static void setClientInfo(ClientInfo clientInfo) {
        TLVarManager.clientInfo.set(clientInfo);
    }

    public static Long getUserId() {
        return userId.get();
    }

    public static String getOpenId() {
        return openId.get();
    }

    public static ClientInfo getClientInfo() {
        return clientInfo.get();
    }

    public static void removeUserId() {
        TLVarManager.userId.remove();
    }

    public static void removeOpenId() {
        TLVarManager.openId.remove();
    }

    public static void removeClientInfo() {
        TLVarManager.clientInfo.remove();
    }
}
