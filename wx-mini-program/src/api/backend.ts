/* eslint-disable */
/* tslint:disable */
/*
 * ---------------------------------------------------------------
 * ## THIS FILE WAS GENERATED VIA SWAGGER-TYPESCRIPT-API        ##
 * ##                                                           ##
 * ## AUTHOR: acacode                                           ##
 * ## SOURCE: https://github.com/acacode/swagger-typescript-api ##
 * ---------------------------------------------------------------
 */

import auth from "@/service/user";
import utils from "@/utils";
import constants from "@/utils/constants";
import Taro from "@tarojs/taro";

export interface BaseRequest {
  head: MobileRequestHead;
}
export interface BaseResponse {
  status: ResponseStatus;
}

/** AccountPartnerQueryParam */
export interface AccountPartnerQueryParam {
  /** @format int64 */
  accountId: number;
}

/** AccountPartnerQueryResult */
export interface AccountPartnerQueryResult {
  /** @format int64 */
  accountId: number;
  balance: string;
  /** @format date-time */
  createdAt: string;
  /** @format int32 */
  status: number;
  /** @format date-time */
  updatedAt: string;
  /** @format int64 */
  userId: number;
}

/** AccountTransferParam */
export interface AccountTransferParam {
  amount: string;
  /** @format int64 */
  fromAccountId: number;
  secretKey: string;
  /** @format int64 */
  toAccountId: number;
}

/** AccountTransferResult */
export type AccountTransferResult = object;

/** AccountUserQueryResult */
export interface AccountUserQueryResult {
  /** @format int64 */
  accountId: number;
  balance: string;
  /** @format date-time */
  createdAt: string;
  /** @format int32 */
  status: number;
  /** @format date-time */
  updatedAt: string;
  /** @format int64 */
  userId: number;
}

/** BaseRequest */
export interface BaseRequest {
  head: MobileRequestHead;
}

/** DepositParam */
export interface DepositParam {
  amount: string;
}

/** DepositResult */
export interface DepositResult {
  appId: string;
  nonceStr: string;
  package: string;
  paySign: string;
  signType: string;
  timeStamp: string;
}

/** GetLinkParam */
export interface GetQrCodesParam {
  /** @format int32 */
  number: number;
  shopId: number;
}

/** GetLinkResult */
export interface GetLinkResult {
  qrCodes: string[];
}

/** MobileRequestHead */
export interface MobileRequestHead {
  deviceId?: string;
  ip?: string;
  latitude?: string;
  longitude?: string;
  mac?: string;
  platform?: string;
  brand?: string;
  model?: string;
  system?: string;
  sign?: string;
  token?: string;
  uid?: string;
}

/** ModelAndView */
export interface ModelAndView {
  empty?: boolean;
  model?: object;
  modelMap?: Record<string, object>;
  reference?: boolean;
  status?:
    | "ACCEPTED"
    | "ALREADY_REPORTED"
    | "BAD_GATEWAY"
    | "BAD_REQUEST"
    | "BANDWIDTH_LIMIT_EXCEEDED"
    | "CHECKPOINT"
    | "CONFLICT"
    | "CONTINUE"
    | "CREATED"
    | "DESTINATION_LOCKED"
    | "EXPECTATION_FAILED"
    | "FAILED_DEPENDENCY"
    | "FORBIDDEN"
    | "FOUND"
    | "GATEWAY_TIMEOUT"
    | "GONE"
    | "HTTP_VERSION_NOT_SUPPORTED"
    | "IM_USED"
    | "INSUFFICIENT_SPACE_ON_RESOURCE"
    | "INSUFFICIENT_STORAGE"
    | "INTERNAL_SERVER_ERROR"
    | "I_AM_A_TEAPOT"
    | "LENGTH_REQUIRED"
    | "LOCKED"
    | "LOOP_DETECTED"
    | "METHOD_FAILURE"
    | "METHOD_NOT_ALLOWED"
    | "MOVED_PERMANENTLY"
    | "MOVED_TEMPORARILY"
    | "MULTIPLE_CHOICES"
    | "MULTI_STATUS"
    | "NETWORK_AUTHENTICATION_REQUIRED"
    | "NON_AUTHORITATIVE_INFORMATION"
    | "NOT_ACCEPTABLE"
    | "NOT_EXTENDED"
    | "NOT_FOUND"
    | "NOT_IMPLEMENTED"
    | "NOT_MODIFIED"
    | "NO_CONTENT"
    | "OK"
    | "PARTIAL_CONTENT"
    | "PAYLOAD_TOO_LARGE"
    | "PAYMENT_REQUIRED"
    | "PERMANENT_REDIRECT"
    | "PRECONDITION_FAILED"
    | "PRECONDITION_REQUIRED"
    | "PROCESSING"
    | "PROXY_AUTHENTICATION_REQUIRED"
    | "REQUESTED_RANGE_NOT_SATISFIABLE"
    | "REQUEST_ENTITY_TOO_LARGE"
    | "REQUEST_HEADER_FIELDS_TOO_LARGE"
    | "REQUEST_TIMEOUT"
    | "REQUEST_URI_TOO_LONG"
    | "RESET_CONTENT"
    | "SEE_OTHER"
    | "SERVICE_UNAVAILABLE"
    | "SWITCHING_PROTOCOLS"
    | "TEMPORARY_REDIRECT"
    | "TOO_EARLY"
    | "TOO_MANY_REQUESTS"
    | "UNAUTHORIZED"
    | "UNAVAILABLE_FOR_LEGAL_REASONS"
    | "UNPROCESSABLE_ENTITY"
    | "UNSUPPORTED_MEDIA_TYPE"
    | "UPGRADE_REQUIRED"
    | "URI_TOO_LONG"
    | "USE_PROXY"
    | "VARIANT_ALSO_NEGOTIATES";
  view?: View;
  viewName?: string;
}

/** MsgSubsParam */
export interface MsgSubsParam {
  templateId: string;
}

/** MsgSubsResult */
export type MsgSubsResult = object;

/** OrderCreateParam */
export interface OrderCreateParam {
  capture: string;
  serialNo: string;
  /** @format int64 */
  shopId: number;
}

/** OrderCreateResult */
export interface OrderCreateResult {
  /** @format int64 */
  orderId: number;
}

/** OrderDetail */
export interface OrderDetail {
  amount: string;
  capture: string;
  /** @format date-time */
  createdAt: string;
  failMsg?: string;
  /** @format date-time */
  fxTime?: string;
  /** @format int64 */
  id: number;
  /** @format int64 */
  shopId: number;
  /** @format int32 */
  status: number;
  /** @format date-time */
  updatedAt: string;
  /** @format int64 */
  userId: number;
}

export interface UserOrderDetail extends OrderDetail {
  shopName: string;
  shopLogo: string;
}

/** OrderDetail */
export interface ShopOrderDetail extends OrderDetail {
  nickName: string;
  avatar: string;
}

/** OrderPayParam */
export interface OrderPayParam {
  /** @format int64 */
  orderId: number;
}

/** OrderPayResult */
export type OrderPayResult = object;

/** ResponseStatus */
export interface ResponseStatus {
  errorCode?: string;
  errorMessage?: string;
  errors?: Record<string, string>;
}

/** ShopCreateParam */
export interface ShopCreateParam {
  address: string;
  intro: string;
  logo: string;
  name: string;
  paySecret: string;
  phone: string;
}

/** ShopCreateResult */
export interface ShopCreateResult {
  /** @format int64 */
  shopId: number;
}

/** ShopDetail */
export interface ShopDetail {
  address: string;
  /** @format date-time */
  createdAt: string;
  intro: string;
  logo: string;
  /** @format int64 */
  ownerUserId: number;
  phone: string;
  /** @format int64 */
  shopId: number;
  shopName: string;
  /** @format date-time */
  updatedAt: string;
  status: number;
}

/** ShopListResult */
export interface ShopListResult {
  shopInfos?: ShopDetail[];
}

/** ShopOrderListParam */
export interface ShopOrderListParam {
  /** @format date-time */
  from?: string;
  /**
   * @format int32
   * @max 20
   * @exclusiveMax false
   */
  limit: number;
  /** @format int32 */
  offset: number;
  /** @format int64 */
  shopId: number;
  /** @format int32 */
  status?: number;
  /** @format date-time */
  to?: string;
}

/** ShopOrderListResult */
export interface ShopOrderListResult {
  orderInfos?: ShopOrderDetail[];
}

/** ShopQueryParam */
export interface GetShopParam {
  /** @format int64 */
  shopId: number;
}

/** ShopQueryResult */
export interface GetShopResult {
  address: string;
  /** @format date-time */
  createdAt: string;
  intro: string;
  logo: string;
  /** @format int64 */
  ownerUserId: number;
  phone: string;
  /** @format int64 */
  shopId: number;
  shopName: string;
  /** @format date-time */
  updatedAt: string;
  status: number;
  balance: string;
  orderCount: number;
  orderTotalAmount: string;
}

export interface ShopQueryResult {
  address: string;
  /** @format date-time */
  createdAt: string;
  intro: string;
  logo: string;
  /** @format int64 */
  ownerUserId: number;
  phone: string;
  /** @format int64 */
  shopId: number;
  shopName: string;
}

export interface GetPublicShopInfoResult {
  address: string;
  intro: string;
  logo: string;
  shopName: string;
}

/** ShopUpdateParam */
export interface ShopUpdateParam {
  address: string;
  intro: string;
  logo: string;
  name: string;
  phone: string;
  /** @format int64 */
  shopId: number;
}

/** ShopUpdateResult */
export type ShopUpdateResult = object;

/** SignUploadUrlResult */
export interface SignUploadUrlResult {
  signedUrl: string;
}

/** SwitchParam */
export interface SwitchParam {
  /** @format int64 */
  shopId: number;
  /**
   * @format int32
   * @min 0
   * @exclusiveMin false
   * @max 1
   * @exclusiveMax false
   */
  switchValue: number;
}

/** SwitchResult */
export type SwitchResult = object;

/** UserLoginParam */
export interface UserLoginParam {
  code: string;
}

/** UserLoginResult */
export interface UserLoginResult {
  /** @format int64 */
  token: string;
  newUser: boolean;
}

/** UserOrderListParam */
export interface UserOrderListParam {
  /** @format date-time */
  from?: string;
  /**
   * @format int32
   * @max 20
   * @exclusiveMax false
   */
  limit: number;
  /** @format int32 */
  offset: number;
  /** @format int32 */
  status?: number;
  /** @format date-time */
  to?: string;
}

/** UserOrderListResult */
export interface UserOrderListResult {
  orderInfos?: OrderDetail[];
}

/** View */
export interface View {
  contentType?: string;
}

/** WithdrawParam */
export interface WithdrawParam {
  amount: string;
}

/** WithdrawResult */
export type WithdrawResult = object;

/** Request«AccountPartnerQueryParam» */
export interface RequestAccountPartnerQueryParam {
  head: MobileRequestHead;
  param: AccountPartnerQueryParam;
}

/** Request«AccountTransferParam» */
export interface RequestAccountTransferParam {
  head: MobileRequestHead;
  param: AccountTransferParam;
}

/** Request«DepositParam» */
export interface RequestDepositParam {
  head: MobileRequestHead;
  param: DepositParam;
}

/** Request«GetLinkParam» */
export interface RequestGetQrCodesParam {
  head: MobileRequestHead;
  param: GetQrCodesParam;
}

/** Request«MsgSubsParam» */
export interface RequestMsgSubsParam {
  head: MobileRequestHead;
  param: MsgSubsParam;
}

/** Request«OrderCreateParam» */
export interface RequestOrderCreateParam {
  head: MobileRequestHead;
  param: OrderCreateParam;
}

/** Request«OrderPayParam» */
export interface RequestOrderPayParam {
  head: MobileRequestHead;
  param: OrderPayParam;
}

/** Request«ShopCreateParam» */
export interface RequestShopCreateParam {
  head: MobileRequestHead;
  param: ShopCreateParam;
}

/** Request«ShopOrderListParam» */
export interface RequestShopOrderListParam {
  head: MobileRequestHead;
  param: ShopOrderListParam;
}

/** Request«GetShopParam» */
export interface RequestGetShopDetailParam {
  head: MobileRequestHead;
  param: GetShopParam;
}

/** Request«ShopQueryParam» */
export interface RequestShopGetDetailInfoParam {
  head: MobileRequestHead;
  param: ShopQueryParam;
}
/** Request«ShopQueryParam» */
export interface RequestGetPublicShopInfoParam {
  head: MobileRequestHead;
  param: GetPublicShopInfoParam;
}

export interface GetPublicShopInfoParam {
  /** @format int64 */
  shopId: number;
}

export interface ShopQueryParam {
  /** @format int64 */
  shopId: number;
}

/** Request«ShopUpdateParam» */
export interface RequestShopUpdateParam {
  head: MobileRequestHead;
  param: ShopUpdateParam;
}

/** Request«SwitchParam» */
export interface RequestSwitchParam {
  head: MobileRequestHead;
  param: SwitchParam;
}

/** Request«UserLoginParam» */
export interface RequestUserLoginParam {
  head: MobileRequestHead;
  param: UserLoginParam;
}

/** Request«UserOrderListParam» */
export interface RequestUserOrderListParam {
  head: MobileRequestHead;
  param: UserOrderListParam;
}

/** Request«WithdrawParam» */
export interface RequestWithdrawParam {
  head: MobileRequestHead;
  param: WithdrawParam;
}

/** Response«AccountPartnerQueryResult» */
export interface ResponseAccountPartnerQueryResult {
  result?: AccountPartnerQueryResult;
  status: ResponseStatus;
}

/** Response«AccountTransferResult» */
export interface ResponseAccountTransferResult {
  result?: AccountTransferResult;
  status: ResponseStatus;
}

/** Response«AccountUserQueryResult» */
export interface ResponseGetAccountResult {
  result?: AccountUserQueryResult;
  status: ResponseStatus;
}

/** Response«DepositResult» */
export interface ResponseDepositResult {
  result?: DepositResult;
  status: ResponseStatus;
}

/** Response«GetLinkResult» */
export interface ResponseGetQrCodesResult {
  result?: GetLinkResult;
  status: ResponseStatus;
}

/** Response«OrderCreateResult» */
export interface ResponseOrderCreateResult {
  result?: OrderCreateResult;
  status: ResponseStatus;
}

/** Response«OrderPayResult» */
export interface ResponseOrderPayResult {
  result?: OrderPayResult;
  status: ResponseStatus;
}

/** Response«ShopCreateResult» */
export interface ResponseShopCreateResult {
  result?: ShopCreateResult;
  status: ResponseStatus;
}

/** Response«ShopListResult» */
export interface ResponseShopListResult {
  result?: ShopListResult;
  status: ResponseStatus;
}

/** Response«ShopOrderListResult» */
export interface ResponseShopOrderListResult {
  result?: ShopOrderListResult;
  status: ResponseStatus;
}

/** Response«ShopQueryResult» */
export interface ResponseGetShopDetailResult {
  result?: GetShopResult;
  status: ResponseStatus;
}

export interface ResponseShopGetDetailInfoResult {
  result?: ShopQueryResult;
  status: ResponseStatus;
}

export interface ResponseGetPublicShopInfoResult {
  result?: GetPublicShopInfoResult;
  status: ResponseStatus;
}

/** Response«SignUploadUrlResult» */
export interface ResponseSignUploadUrlResult {
  result?: SignUploadUrlResult;
  status: ResponseStatus;
}

/** Response«GetUploadPolicyResult» */
export interface ResponseGetUploadPolicyResult {
  result?: GetUploadPolicyResult;
  status: ResponseStatus;
}

export interface GetUploadPolicyResult {
  accessid: string;
  policy: string;
  signature: string;
  dir?: string;
  host: string;
  expire: string;
  key: string;
}

/** Response«SwitchResult» */
export interface ResponseSwitchResult {
  result?: SwitchResult;
  status: ResponseStatus;
}

/** Response«UserLoginResult» */
export interface ResponseUserLoginResult {
  result?: UserLoginResult;
  status: ResponseStatus;
}

/** Response«UserOrderListResult» */
export interface ResponseUserOrderListResult {
  result?: UserOrderListResult;
  status: ResponseStatus;
}

/** Response«WithdrawResult» */
export interface ResponseWithdrawResult {
  result?: WithdrawResult;
  status: ResponseStatus;
}

export type RequestParams = Omit<
  Taro.request.Option,
  "url" | "method" | "data" | "success"
>;

function autoHandleError(
  target: any,
  key: string,
  descriptor: PropertyDescriptor
) {
  const originalMethod = descriptor.value;
  descriptor.value = async function(...args: any[]) {
    Taro.showLoading({
      title: "正在处理...",
    });
    let result: Taro.request.SuccessCallbackResult<BaseResponse> = await originalMethod.apply(
      this,
      args
    );
    console.log("request method: %s, result: %o", key, result);
    // 请求错误
    if (!result.data) {
      utils.failureToast();
      return result;
    }
    // 正常情况
    if (utils.success(result.data)) {
      Taro.hideLoading();
      return result;
    }
    // 未登录，重新登录后再请求
    if (
      result.data.status.errorCode == "100002" ||
      result.data.status.errorCode == "100003"
    ) {
      await auth.login();
      result = await originalMethod.apply(this, args);
      Taro.hideLoading();
    } else {
      // 其他业务异常
      utils.failureToast(result.data.status);
    }
    return result;
  };
  return descriptor;
}

function enableToken(target: any, key: string, descriptor: PropertyDescriptor) {
  const originalMethod = descriptor.value;
  descriptor.value = async function(...args: any[]) {
    const token = Taro.getStorageSync(constants.HEADER_USER_TOKEN);
    if (token) {
      console.log("user token :%s", token);
      if (args.length == 1) {
        args.push({});
      }
      const param = args[1];
      const header = param.header;
      if (header) {
        header[constants.HEADER_USER_TOKEN] = token;
      } else {
        param.header = {
          [constants.HEADER_USER_TOKEN]: token,
        };
      }
    }
    const result: Taro.request.SuccessCallbackResult<BaseResponse> = await originalMethod.apply(
      this,
      args
    );

    const setToken = result.header[constants.HEADER_USER_TOKEN];
    if (setToken) {
      Taro.setStorageSync(constants.HEADER_USER_TOKEN, setToken);
    }

    return result;
  };
  return descriptor;
}

class Api {
  // private static BASE_URL = "https://localhost:8080";

  private static BASE_URL = "https://www.sgj.cool";

  private static instance: Api;

  private constructor() {}

  static getInstance() {
    if (!Api.instance) {
      Api.instance = new Api();
    }
    return Api.instance;
  }
  /**
   * No description
   *
   * @tags user-controller
   * @name Login
   * @summary login
   * @request POST:/fx-helper-user/login
   */
  @enableToken
  async login(request: RequestUserLoginParam, params: RequestParams = {}) {
    return Taro.request<ResponseUserLoginResult, RequestUserLoginParam>({
      url: Api.BASE_URL + `/fx-helper-user/login`,
      method: "POST",
      data: request,
      ...params,
    });
  }

  /**
   * No description
   *
   * @tags account-controller
   * @name GetAccount
   * @summary getAccount
   * @request POST:/fx-helper-account/get_account
   */
  @autoHandleError
  @enableToken
  async getAccount(request: BaseRequest, params: RequestParams = {}) {
    return Taro.request<ResponseGetAccountResult, BaseRequest>({
      url: Api.BASE_URL + `/fx-helper-account/get_account`,
      method: "POST",
      data: request,
      ...params,
    });
  }

  /**
   * No description
   *
   * @tags misc-controller
   * @name getUploadPolicy
   * @summary getUploadPolicy
   * @request POST:/fx-helper-misc/get_upload_policy
   */
  @autoHandleError
  @enableToken
  async getUploadPolicy(request: BaseRequest, params: RequestParams = {}) {
    return Taro.request<ResponseGetUploadPolicyResult, BaseRequest>({
      url: Api.BASE_URL + `/fx-helper-misc/get_upload_policy`,
      method: "POST",
      data: request,
      ...params,
    });
  }

  /**
   * No description
   *
   * @tags misc-controller
   * @name MsgSubscribe
   * @summary msgSubscribe
   * @request POST:/fx-helper-misc/wx/msg_subscribe
   */
  @autoHandleError
  @enableToken
  async msgSubscribe(request: RequestMsgSubsParam, params: RequestParams = {}) {
    return Taro.request<BaseResponse, RequestMsgSubsParam>({
      url: Api.BASE_URL + `/fx-helper-misc/wx/msg_subscribe`,
      method: "POST",
      data: request,
      ...params,
    });
  }

  /**
   * No description
   *
   * @tags order-controller
   * @name createOrder
   * @summary createOrder
   * @request POST:/fx-helper-order/create
   */
  @autoHandleError
  @enableToken
  async createOrder(
    request: RequestOrderCreateParam,
    params: RequestParams = {}
  ) {
    return Taro.request<ResponseOrderCreateResult, RequestOrderCreateParam>({
      url: Api.BASE_URL + `/fx-helper-order/create`,
      method: "POST",
      data: request,
      ...params,
    });
  }

  /**
   * No description
   *
   * @tags agg-controller
   * @name listShopOrders
   * @summary listShopOrders
   * @request POST:/fx-helper-agg/list_shop_orders
   */
  @autoHandleError
  @enableToken
  async listShopOrders(
    request: RequestShopOrderListParam,
    params: RequestParams = {}
  ) {
    return Taro.request<ResponseShopOrderListResult, RequestShopOrderListParam>(
      {
        url: Api.BASE_URL + `/fx-helper-agg/list_shop_orders`,
        method: "POST",
        data: request,
        ...params,
      }
    );
  }

  /**
   * No description
   *
   * @tags agg-controller
   * @name listUserOrders
   * @summary listUserOrders
   * @request POST:/fx-helper-agg/list_user_orders
   */
  @autoHandleError
  @enableToken
  async listUserOrders(
    request: RequestUserOrderListParam,
    params: RequestParams = {}
  ) {
    return Taro.request<ResponseUserOrderListResult, RequestUserOrderListParam>(
      {
        url: Api.BASE_URL + `/fx-helper-agg/list_user_orders`,
        method: "POST",
        data: request,
        ...params,
      }
    );
  }

  /**
   * No description
   *
   * @tags order-controller
   * @name payOrder
   * @summary pay
   * @request POST:/fx-helper-order/pay
   */
  @autoHandleError
  @enableToken
  async payOrder(request: RequestOrderPayParam, params: RequestParams = {}) {
    return Taro.request<ResponseOrderPayResult, RequestOrderPayParam>({
      url: Api.BASE_URL + `/fx-helper-order/pay`,
      method: "POST",
      data: request,
      ...params,
    });
  }

  /**
   * No description
   *
   * @tags pay-controller
   * @name deposit
   * @summary deposit
   * @request POST:/fx-helper-pay/deposit
   */
  @autoHandleError
  @enableToken
  async deposit(request: RequestDepositParam, params: RequestParams = {}) {
    return Taro.request<ResponseDepositResult, RequestDepositParam>({
      url: Api.BASE_URL + `/fx-helper-pay/deposit`,
      method: "POST",
      data: request,
      ...params,
    });
  }

  /**
   * No description
   *
   * @tags pay-controller
   * @name withdraw
   * @summary withdraw
   * @request POST:/fx-helper-pay/withdraw
   */
  @autoHandleError
  @enableToken
  async withdraw(request: RequestWithdrawParam, params: RequestParams = {}) {
    return Taro.request<ResponseWithdrawResult, RequestWithdrawParam>({
      url: Api.BASE_URL + `/fx-helper-pay/withdraw`,
      method: "POST",
      data: request,
      ...params,
    });
  }

  /**
   * No description
   *
   * @tags shop-controller
   * @name createShop
   * @summary create
   * @request POST:/fx-helper-shop/create
   */
  @autoHandleError
  @enableToken
  async createShop(
    request: RequestShopCreateParam,
    params: RequestParams = {}
  ) {
    return Taro.request<ResponseShopCreateResult, RequestShopCreateParam>({
      url: Api.BASE_URL + `/fx-helper-shop/create`,
      method: "POST",
      data: request,
      ...params,
    });
  }
  /**
   * No description
   *
   * @tags shop-controller
   * @name updateShop
   * @summary update
   * @request POST:/fx-helper-shop/update
   */
  @autoHandleError
  @enableToken
  async updateShop(
    request: RequestShopUpdateParam,
    params: RequestParams = {}
  ) {
    return Taro.request<BaseResponse, RequestShopUpdateParam>({
      url: Api.BASE_URL + `/fx-helper-shop/update`,
      method: "POST",
      data: request,
      ...params,
    });
  }

  /**
   * No description
   *
   * @tags shop-controller
   * @name getDetailInfo
   * @summary getDetailInfo
   * @request POST:/fx-helper-shop/get_detail_info
   */
  @autoHandleError
  @enableToken
  async getDetailInfo(
    request: RequestShopGetDetailInfoParam,
    params: RequestParams = {}
  ) {
    return Taro.request<
      ResponseShopGetDetailInfoResult,
      RequestShopGetDetailInfoParam
    >({
      url: Api.BASE_URL + `/fx-helper-shop/get_detail_info`,
      method: "POST",
      data: request,
      ...params,
    });
  }

  /*
   * No description
   *
   * @tags shop-controller
   * @name getPublicShopInfo
   * @summary getPublicShopInfo
   * @request POST:/fx-helper-shop/get_public_info
   */
  @autoHandleError
  @enableToken
  async getPublicShopInfo(
    request: RequestGetPublicShopInfoParam,
    params: RequestParams = {}
  ) {
    return Taro.request<
      ResponseGetPublicShopInfoResult,
      RequestGetPublicShopInfoParam
    >({
      url: Api.BASE_URL + `/fx-helper-shop/get_public_info`,
      method: "POST",
      data: request,
      ...params,
    });
  }

  @autoHandleError
  @enableToken
  async getShopDetail(
    request: RequestGetShopDetailParam,
    params: RequestParams = {}
  ) {
    return Taro.request<ResponseGetShopDetailResult, RequestGetShopDetailParam>(
      {
        url: Api.BASE_URL + `/fx-helper-agg/get_shop`,
        method: "POST",
        data: request,
        ...params,
      }
    );
  }

  /**
   * No description
   *
   * @tags shop-controller
   * @name getShopList
   * @summary getShopList
   * @request POST:/fx-helper-shop/get_shop_list
   */
  @autoHandleError
  @enableToken
  async getShopList(request: BaseRequest, params: RequestParams = {}) {
    return Taro.request<ResponseShopListResult, BaseRequest>({
      url: Api.BASE_URL + `/fx-helper-shop/get_shop_list`,
      method: "POST",
      data: request,
      ...params,
    });
  }

  /**
   * No description
   *
   * @tags shop-controller
   * @name getQrCodes
   * @summary getQrCodes
   * @request POST:/fx-helper-shop/get_qr_codes
   */
  @autoHandleError
  @enableToken
  async getQrCodes(
    request: RequestGetQrCodesParam,
    params: RequestParams = {}
  ) {
    return Taro.request<ResponseGetQrCodesResult, RequestGetQrCodesParam>({
      url: Api.BASE_URL + `/fx-helper-shop/get_qr_codes`,
      method: "POST",
      data: request,
      ...params,
    });
  }

  /**
   * No description
   *
   * @tags shop-controller
   * @name switchFx
   * @summary switchFx
   * @request POST:/fx-helper-shop/switch
   */
  @autoHandleError
  @enableToken
  async switchFx(request: RequestSwitchParam, params: RequestParams = {}) {
    return Taro.request<ResponseSwitchResult, RequestSwitchParam>({
      url: Api.BASE_URL + `/fx-helper-shop/switch`,
      method: "POST",
      data: request,
      ...params,
    });
  }
}

export default Api.getInstance();
