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

package com.iqexception.fxhelper.api.rest.order;

import org.apache.dubbo.common.stream.StreamObserver;
import com.google.protobuf.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.concurrent.CompletableFuture;

public interface OrderRestService extends org.apache.dubbo.rpc.model.DubboStub {

    String JAVA_SERVICE_NAME = "com.iqexception.fxhelper.api.rest.order.OrderRestService";
    String SERVICE_NAME = "com.iqexception.fxhelper.api.rest.order.OrderRestService";
    com.iqexception.fxhelper.api.rest.order.CreateOrderResponse createOrder(com.iqexception.fxhelper.api.rest.order.CreateOrderRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.rest.order.CreateOrderResponse> createOrderAsync(com.iqexception.fxhelper.api.rest.order.CreateOrderRequest request);



    com.iqexception.fxhelper.api.common.BaseResponse payOrder(com.iqexception.fxhelper.api.rest.order.PayOrderRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> payOrderAsync(com.iqexception.fxhelper.api.rest.order.PayOrderRequest request);








}
