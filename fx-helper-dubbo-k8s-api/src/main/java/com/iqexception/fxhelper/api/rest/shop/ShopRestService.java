/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.iqexception.fxhelper.api.rest.shop;

import org.apache.dubbo.common.stream.StreamObserver;
import com.google.protobuf.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.concurrent.CompletableFuture;

public interface ShopRestService extends org.apache.dubbo.rpc.model.DubboStub {

    String JAVA_SERVICE_NAME = "com.iqexception.fxhelper.api.rest.shop.ShopRestService";
    String SERVICE_NAME = "com.iqexception.fxhelper.api.rest.shop.ShopRestService";
    com.iqexception.fxhelper.api.rest.shop.CreateResponse create(com.iqexception.fxhelper.api.rest.shop.CreateRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.rest.shop.CreateResponse> createAsync(com.iqexception.fxhelper.api.rest.shop.CreateRequest request);



    com.iqexception.fxhelper.api.common.BaseResponse update(com.iqexception.fxhelper.api.rest.shop.UpdateRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> updateAsync(com.iqexception.fxhelper.api.rest.shop.UpdateRequest request);



    com.iqexception.fxhelper.api.rest.shop.GetShopListResponse getShopList(com.iqexception.fxhelper.api.common.BaseRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.rest.shop.GetShopListResponse> getShopListAsync(com.iqexception.fxhelper.api.common.BaseRequest request);



    com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoResponse getShopPublicInfo(com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoResponse> getShopPublicInfoAsync(com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoRequest request);



    com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoResponse getShopDetailInfo(com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoResponse> getShopDetailInfoAsync(com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoRequest request);



    com.iqexception.fxhelper.api.rest.shop.GetQrCodesResponse getQrCodes(com.iqexception.fxhelper.api.rest.shop.GetQrCodesRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.rest.shop.GetQrCodesResponse> getQrCodesAsync(com.iqexception.fxhelper.api.rest.shop.GetQrCodesRequest request);



    com.iqexception.fxhelper.api.common.BaseResponse switchFx(com.iqexception.fxhelper.api.rest.shop.SwitchFxRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> switchFxAsync(com.iqexception.fxhelper.api.rest.shop.SwitchFxRequest request);








}
