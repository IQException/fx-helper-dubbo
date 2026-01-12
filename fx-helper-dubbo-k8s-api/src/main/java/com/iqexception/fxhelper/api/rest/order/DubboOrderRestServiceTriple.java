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
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.PathResolver;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.ServerService;
import org.apache.dubbo.rpc.TriRpcStatus;
import org.apache.dubbo.rpc.model.MethodDescriptor;
import org.apache.dubbo.rpc.model.ServiceDescriptor;
import org.apache.dubbo.rpc.model.StubMethodDescriptor;
import org.apache.dubbo.rpc.model.StubServiceDescriptor;
import org.apache.dubbo.rpc.stub.BiStreamMethodHandler;
import org.apache.dubbo.rpc.stub.ServerStreamMethodHandler;
import org.apache.dubbo.rpc.stub.StubInvocationUtil;
import org.apache.dubbo.rpc.stub.StubInvoker;
import org.apache.dubbo.rpc.stub.StubMethodHandler;
import org.apache.dubbo.rpc.stub.StubSuppliers;
import org.apache.dubbo.rpc.stub.UnaryStubMethodHandler;

import com.google.protobuf.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.concurrent.CompletableFuture;

public final class DubboOrderRestServiceTriple {

    public static final String SERVICE_NAME = OrderRestService.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME,OrderRestService.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME,OrderRest.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboOrderRestServiceTriple::newStub);
        StubSuppliers.addSupplier(OrderRestService.JAVA_SERVICE_NAME,  DubboOrderRestServiceTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(OrderRestService.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("all")
    public static OrderRestService newStub(Invoker<?> invoker) {
        return new OrderRestServiceStub((Invoker<OrderRestService>)invoker);
    }

    private static final StubMethodDescriptor createOrderMethod = new StubMethodDescriptor("createOrder",
    com.iqexception.fxhelper.api.rest.order.CreateOrderRequest.class, com.iqexception.fxhelper.api.rest.order.CreateOrderResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.order.CreateOrderRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.order.CreateOrderResponse::parseFrom);

    private static final StubMethodDescriptor createOrderAsyncMethod = new StubMethodDescriptor("createOrder",
    com.iqexception.fxhelper.api.rest.order.CreateOrderRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.order.CreateOrderRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.order.CreateOrderResponse::parseFrom);

    private static final StubMethodDescriptor createOrderProxyAsyncMethod = new StubMethodDescriptor("createOrderAsync",
    com.iqexception.fxhelper.api.rest.order.CreateOrderRequest.class, com.iqexception.fxhelper.api.rest.order.CreateOrderResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.order.CreateOrderRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.order.CreateOrderResponse::parseFrom);
    private static final StubMethodDescriptor payOrderMethod = new StubMethodDescriptor("payOrder",
    com.iqexception.fxhelper.api.rest.order.PayOrderRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.order.PayOrderRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor payOrderAsyncMethod = new StubMethodDescriptor("payOrder",
    com.iqexception.fxhelper.api.rest.order.PayOrderRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.order.PayOrderRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor payOrderProxyAsyncMethod = new StubMethodDescriptor("payOrderAsync",
    com.iqexception.fxhelper.api.rest.order.PayOrderRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.order.PayOrderRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);




    static{
        serviceDescriptor.addMethod(createOrderMethod);
        serviceDescriptor.addMethod(createOrderProxyAsyncMethod);
        serviceDescriptor.addMethod(payOrderMethod);
        serviceDescriptor.addMethod(payOrderProxyAsyncMethod);
    }

    public static class OrderRestServiceStub implements OrderRestService{
        private final Invoker<OrderRestService> invoker;

        public OrderRestServiceStub(Invoker<OrderRestService> invoker) {
            this.invoker = invoker;
        }

        @Override
        public com.iqexception.fxhelper.api.rest.order.CreateOrderResponse createOrder(com.iqexception.fxhelper.api.rest.order.CreateOrderRequest request){
            return StubInvocationUtil.unaryCall(invoker, createOrderMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.rest.order.CreateOrderResponse> createOrderAsync(com.iqexception.fxhelper.api.rest.order.CreateOrderRequest request){
            return StubInvocationUtil.unaryCall(invoker, createOrderAsyncMethod, request);
        }

        public void createOrder(com.iqexception.fxhelper.api.rest.order.CreateOrderRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.order.CreateOrderResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, createOrderMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse payOrder(com.iqexception.fxhelper.api.rest.order.PayOrderRequest request){
            return StubInvocationUtil.unaryCall(invoker, payOrderMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> payOrderAsync(com.iqexception.fxhelper.api.rest.order.PayOrderRequest request){
            return StubInvocationUtil.unaryCall(invoker, payOrderAsyncMethod, request);
        }

        public void payOrder(com.iqexception.fxhelper.api.rest.order.PayOrderRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, payOrderMethod , request, responseObserver);
        }



    }

    public static abstract class OrderRestServiceImplBase implements OrderRestService, ServerService<OrderRestService> {

        private <T, R> BiConsumer<T, StreamObserver<R>> syncToAsync(java.util.function.Function<T, R> syncFun) {
            return new BiConsumer<T, StreamObserver<R>>() {
                @Override
                public void accept(T t, StreamObserver<R> observer) {
                    try {
                        R ret = syncFun.apply(t);
                        observer.onNext(ret);
                        observer.onCompleted();
                    } catch (Throwable e) {
                        observer.onError(e);
                    }
                }
            };
        }

        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.rest.order.CreateOrderResponse> createOrderAsync(com.iqexception.fxhelper.api.rest.order.CreateOrderRequest request){
                return CompletableFuture.completedFuture(createOrder(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> payOrderAsync(com.iqexception.fxhelper.api.rest.order.PayOrderRequest request){
                return CompletableFuture.completedFuture(payOrder(request));
        }

        /**
        * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
        * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
        */
        public void createOrder(com.iqexception.fxhelper.api.rest.order.CreateOrderRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.order.CreateOrderResponse> responseObserver){
            createOrderAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void payOrder(com.iqexception.fxhelper.api.rest.order.PayOrderRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            payOrderAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }

        @Override
        public final Invoker<OrderRestService> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String,StubMethodHandler<?, ?>> handlers = new HashMap<>();

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/createOrder");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/createOrderAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/createOrder");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/createOrderAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/payOrder");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/payOrderAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/payOrder");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/payOrderAsync");


            BiConsumer<com.iqexception.fxhelper.api.rest.order.CreateOrderRequest, StreamObserver<com.iqexception.fxhelper.api.rest.order.CreateOrderResponse>> createOrderFunc = this::createOrder;
            handlers.put(createOrderMethod.getMethodName(), new UnaryStubMethodHandler<>(createOrderFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.order.CreateOrderRequest, StreamObserver<com.iqexception.fxhelper.api.rest.order.CreateOrderResponse>> createOrderAsyncFunc = syncToAsync(this::createOrder);
            handlers.put(createOrderProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(createOrderAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.order.PayOrderRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> payOrderFunc = this::payOrder;
            handlers.put(payOrderMethod.getMethodName(), new UnaryStubMethodHandler<>(payOrderFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.order.PayOrderRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> payOrderAsyncFunc = syncToAsync(this::payOrder);
            handlers.put(payOrderProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(payOrderAsyncFunc));




            return new StubInvoker<>(this, url, OrderRestService.class, handlers);
        }


        @Override
        public com.iqexception.fxhelper.api.rest.order.CreateOrderResponse createOrder(com.iqexception.fxhelper.api.rest.order.CreateOrderRequest request){
            throw unimplementedMethodException(createOrderMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse payOrder(com.iqexception.fxhelper.api.rest.order.PayOrderRequest request){
            throw unimplementedMethodException(payOrderMethod);
        }





        @Override
        public final ServiceDescriptor getServiceDescriptor() {
            return serviceDescriptor;
        }
        private RpcException unimplementedMethodException(StubMethodDescriptor methodDescriptor) {
            return TriRpcStatus.UNIMPLEMENTED.withDescription(String.format("Method %s is unimplemented",
                "/" + serviceDescriptor.getInterfaceName() + "/" + methodDescriptor.getMethodName())).asException();
        }
    }

}
