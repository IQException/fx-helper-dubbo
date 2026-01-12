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

package com.iqexception.fxhelper.api.shop;

import org.apache.dubbo.common.stream.StreamObserver;
import com.google.protobuf.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.concurrent.CompletableFuture;

public interface ShopService extends org.apache.dubbo.rpc.model.DubboStub {

    String JAVA_SERVICE_NAME = "com.iqexception.fxhelper.api.shop.ShopService";
    String SERVICE_NAME = "com.iqexception.fxhelper.api.shop.ShopService";
    com.iqexception.fxhelper.api.shop.GetUserShopListResponse getUserShopList(com.iqexception.fxhelper.api.shop.GetUserShopListRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.shop.GetUserShopListResponse> getUserShopListAsync(com.iqexception.fxhelper.api.shop.GetUserShopListRequest request);



    com.iqexception.fxhelper.api.shop.GetShopListResponse getShopList(com.iqexception.fxhelper.api.shop.GetShopListRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.shop.GetShopListResponse> getShopListAsync(com.iqexception.fxhelper.api.shop.GetShopListRequest request);



    com.iqexception.fxhelper.api.shop.GetSerialNoResponse getSerialNo(com.iqexception.fxhelper.api.shop.GetSerialNoRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.shop.GetSerialNoResponse> getSerialNoAsync(com.iqexception.fxhelper.api.shop.GetSerialNoRequest request);



    com.iqexception.fxhelper.api.shop.GetShopResponse getShop(com.iqexception.fxhelper.api.shop.GetShopRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.shop.GetShopResponse> getShopAsync(com.iqexception.fxhelper.api.shop.GetShopRequest request);








}
