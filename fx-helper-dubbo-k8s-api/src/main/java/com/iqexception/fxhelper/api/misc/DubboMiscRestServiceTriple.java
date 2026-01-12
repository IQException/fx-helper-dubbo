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

package com.iqexception.fxhelper.api.misc;

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

public final class DubboMiscRestServiceTriple {

    public static final String SERVICE_NAME = MiscRestService.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME,MiscRestService.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME,MiscRest.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboMiscRestServiceTriple::newStub);
        StubSuppliers.addSupplier(MiscRestService.JAVA_SERVICE_NAME,  DubboMiscRestServiceTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(MiscRestService.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("all")
    public static MiscRestService newStub(Invoker<?> invoker) {
        return new MiscRestServiceStub((Invoker<MiscRestService>)invoker);
    }

    private static final StubMethodDescriptor msgSubscribeMethod = new StubMethodDescriptor("msgSubscribe",
    com.iqexception.fxhelper.api.misc.MsgSubscribeRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.misc.MsgSubscribeRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor msgSubscribeAsyncMethod = new StubMethodDescriptor("msgSubscribe",
    com.iqexception.fxhelper.api.misc.MsgSubscribeRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.misc.MsgSubscribeRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor msgSubscribeProxyAsyncMethod = new StubMethodDescriptor("msgSubscribeAsync",
    com.iqexception.fxhelper.api.misc.MsgSubscribeRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.misc.MsgSubscribeRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);
    private static final StubMethodDescriptor getUploadPolicyMethod = new StubMethodDescriptor("getUploadPolicy",
    com.iqexception.fxhelper.api.common.BaseRequest.class, com.iqexception.fxhelper.api.misc.GetUploadPolicyResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.common.BaseRequest::parseFrom,
    com.iqexception.fxhelper.api.misc.GetUploadPolicyResponse::parseFrom);

    private static final StubMethodDescriptor getUploadPolicyAsyncMethod = new StubMethodDescriptor("getUploadPolicy",
    com.iqexception.fxhelper.api.common.BaseRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.common.BaseRequest::parseFrom,
    com.iqexception.fxhelper.api.misc.GetUploadPolicyResponse::parseFrom);

    private static final StubMethodDescriptor getUploadPolicyProxyAsyncMethod = new StubMethodDescriptor("getUploadPolicyAsync",
    com.iqexception.fxhelper.api.common.BaseRequest.class, com.iqexception.fxhelper.api.misc.GetUploadPolicyResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.common.BaseRequest::parseFrom,
    com.iqexception.fxhelper.api.misc.GetUploadPolicyResponse::parseFrom);




    static{
        serviceDescriptor.addMethod(msgSubscribeMethod);
        serviceDescriptor.addMethod(msgSubscribeProxyAsyncMethod);
        serviceDescriptor.addMethod(getUploadPolicyMethod);
        serviceDescriptor.addMethod(getUploadPolicyProxyAsyncMethod);
    }

    public static class MiscRestServiceStub implements MiscRestService{
        private final Invoker<MiscRestService> invoker;

        public MiscRestServiceStub(Invoker<MiscRestService> invoker) {
            this.invoker = invoker;
        }

        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse msgSubscribe(com.iqexception.fxhelper.api.misc.MsgSubscribeRequest request){
            return StubInvocationUtil.unaryCall(invoker, msgSubscribeMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> msgSubscribeAsync(com.iqexception.fxhelper.api.misc.MsgSubscribeRequest request){
            return StubInvocationUtil.unaryCall(invoker, msgSubscribeAsyncMethod, request);
        }

        public void msgSubscribe(com.iqexception.fxhelper.api.misc.MsgSubscribeRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, msgSubscribeMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.misc.GetUploadPolicyResponse getUploadPolicy(com.iqexception.fxhelper.api.common.BaseRequest request){
            return StubInvocationUtil.unaryCall(invoker, getUploadPolicyMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.misc.GetUploadPolicyResponse> getUploadPolicyAsync(com.iqexception.fxhelper.api.common.BaseRequest request){
            return StubInvocationUtil.unaryCall(invoker, getUploadPolicyAsyncMethod, request);
        }

        public void getUploadPolicy(com.iqexception.fxhelper.api.common.BaseRequest request, StreamObserver<com.iqexception.fxhelper.api.misc.GetUploadPolicyResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getUploadPolicyMethod , request, responseObserver);
        }



    }

    public static abstract class MiscRestServiceImplBase implements MiscRestService, ServerService<MiscRestService> {

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
        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> msgSubscribeAsync(com.iqexception.fxhelper.api.misc.MsgSubscribeRequest request){
                return CompletableFuture.completedFuture(msgSubscribe(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.misc.GetUploadPolicyResponse> getUploadPolicyAsync(com.iqexception.fxhelper.api.common.BaseRequest request){
                return CompletableFuture.completedFuture(getUploadPolicy(request));
        }

        /**
        * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
        * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
        */
        public void msgSubscribe(com.iqexception.fxhelper.api.misc.MsgSubscribeRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            msgSubscribeAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void getUploadPolicy(com.iqexception.fxhelper.api.common.BaseRequest request, StreamObserver<com.iqexception.fxhelper.api.misc.GetUploadPolicyResponse> responseObserver){
            getUploadPolicyAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }

        @Override
        public final Invoker<MiscRestService> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String,StubMethodHandler<?, ?>> handlers = new HashMap<>();

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/msgSubscribe");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/msgSubscribeAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/msgSubscribe");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/msgSubscribeAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getUploadPolicy");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getUploadPolicyAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getUploadPolicy");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getUploadPolicyAsync");


            BiConsumer<com.iqexception.fxhelper.api.misc.MsgSubscribeRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> msgSubscribeFunc = this::msgSubscribe;
            handlers.put(msgSubscribeMethod.getMethodName(), new UnaryStubMethodHandler<>(msgSubscribeFunc));
            BiConsumer<com.iqexception.fxhelper.api.misc.MsgSubscribeRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> msgSubscribeAsyncFunc = syncToAsync(this::msgSubscribe);
            handlers.put(msgSubscribeProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(msgSubscribeAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.common.BaseRequest, StreamObserver<com.iqexception.fxhelper.api.misc.GetUploadPolicyResponse>> getUploadPolicyFunc = this::getUploadPolicy;
            handlers.put(getUploadPolicyMethod.getMethodName(), new UnaryStubMethodHandler<>(getUploadPolicyFunc));
            BiConsumer<com.iqexception.fxhelper.api.common.BaseRequest, StreamObserver<com.iqexception.fxhelper.api.misc.GetUploadPolicyResponse>> getUploadPolicyAsyncFunc = syncToAsync(this::getUploadPolicy);
            handlers.put(getUploadPolicyProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getUploadPolicyAsyncFunc));




            return new StubInvoker<>(this, url, MiscRestService.class, handlers);
        }


        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse msgSubscribe(com.iqexception.fxhelper.api.misc.MsgSubscribeRequest request){
            throw unimplementedMethodException(msgSubscribeMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.misc.GetUploadPolicyResponse getUploadPolicy(com.iqexception.fxhelper.api.common.BaseRequest request){
            throw unimplementedMethodException(getUploadPolicyMethod);
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
