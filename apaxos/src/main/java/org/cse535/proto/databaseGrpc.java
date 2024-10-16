package org.cse535.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: apaxos.proto")
public final class databaseGrpc {

  private databaseGrpc() {}

  public static final String SERVICE_NAME = "database";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.cse535.proto.DataSaveRequest,
      org.cse535.proto.DataSaveResponse> getSaveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "save",
      requestType = org.cse535.proto.DataSaveRequest.class,
      responseType = org.cse535.proto.DataSaveResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.cse535.proto.DataSaveRequest,
      org.cse535.proto.DataSaveResponse> getSaveMethod() {
    io.grpc.MethodDescriptor<org.cse535.proto.DataSaveRequest, org.cse535.proto.DataSaveResponse> getSaveMethod;
    if ((getSaveMethod = databaseGrpc.getSaveMethod) == null) {
      synchronized (databaseGrpc.class) {
        if ((getSaveMethod = databaseGrpc.getSaveMethod) == null) {
          databaseGrpc.getSaveMethod = getSaveMethod = 
              io.grpc.MethodDescriptor.<org.cse535.proto.DataSaveRequest, org.cse535.proto.DataSaveResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "database", "save"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.cse535.proto.DataSaveRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.cse535.proto.DataSaveResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new databaseMethodDescriptorSupplier("save"))
                  .build();
          }
        }
     }
     return getSaveMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.cse535.proto.DataGetRequest,
      org.cse535.proto.DataGetResponse> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "get",
      requestType = org.cse535.proto.DataGetRequest.class,
      responseType = org.cse535.proto.DataGetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.cse535.proto.DataGetRequest,
      org.cse535.proto.DataGetResponse> getGetMethod() {
    io.grpc.MethodDescriptor<org.cse535.proto.DataGetRequest, org.cse535.proto.DataGetResponse> getGetMethod;
    if ((getGetMethod = databaseGrpc.getGetMethod) == null) {
      synchronized (databaseGrpc.class) {
        if ((getGetMethod = databaseGrpc.getGetMethod) == null) {
          databaseGrpc.getGetMethod = getGetMethod = 
              io.grpc.MethodDescriptor.<org.cse535.proto.DataGetRequest, org.cse535.proto.DataGetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "database", "get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.cse535.proto.DataGetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.cse535.proto.DataGetResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new databaseMethodDescriptorSupplier("get"))
                  .build();
          }
        }
     }
     return getGetMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static databaseStub newStub(io.grpc.Channel channel) {
    return new databaseStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static databaseBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new databaseBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static databaseFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new databaseFutureStub(channel);
  }

  /**
   */
  public static abstract class databaseImplBase implements io.grpc.BindableService {

    /**
     */
    public void save(org.cse535.proto.DataSaveRequest request,
        io.grpc.stub.StreamObserver<org.cse535.proto.DataSaveResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSaveMethod(), responseObserver);
    }

    /**
     */
    public void get(org.cse535.proto.DataGetRequest request,
        io.grpc.stub.StreamObserver<org.cse535.proto.DataGetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSaveMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.cse535.proto.DataSaveRequest,
                org.cse535.proto.DataSaveResponse>(
                  this, METHODID_SAVE)))
          .addMethod(
            getGetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.cse535.proto.DataGetRequest,
                org.cse535.proto.DataGetResponse>(
                  this, METHODID_GET)))
          .build();
    }
  }

  /**
   */
  public static final class databaseStub extends io.grpc.stub.AbstractStub<databaseStub> {
    private databaseStub(io.grpc.Channel channel) {
      super(channel);
    }

    private databaseStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected databaseStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new databaseStub(channel, callOptions);
    }

    /**
     */
    public void save(org.cse535.proto.DataSaveRequest request,
        io.grpc.stub.StreamObserver<org.cse535.proto.DataSaveResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSaveMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void get(org.cse535.proto.DataGetRequest request,
        io.grpc.stub.StreamObserver<org.cse535.proto.DataGetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class databaseBlockingStub extends io.grpc.stub.AbstractStub<databaseBlockingStub> {
    private databaseBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private databaseBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected databaseBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new databaseBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.cse535.proto.DataSaveResponse save(org.cse535.proto.DataSaveRequest request) {
      return blockingUnaryCall(
          getChannel(), getSaveMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.cse535.proto.DataGetResponse get(org.cse535.proto.DataGetRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class databaseFutureStub extends io.grpc.stub.AbstractStub<databaseFutureStub> {
    private databaseFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private databaseFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected databaseFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new databaseFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.cse535.proto.DataSaveResponse> save(
        org.cse535.proto.DataSaveRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSaveMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.cse535.proto.DataGetResponse> get(
        org.cse535.proto.DataGetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAVE = 0;
  private static final int METHODID_GET = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final databaseImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(databaseImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAVE:
          serviceImpl.save((org.cse535.proto.DataSaveRequest) request,
              (io.grpc.stub.StreamObserver<org.cse535.proto.DataSaveResponse>) responseObserver);
          break;
        case METHODID_GET:
          serviceImpl.get((org.cse535.proto.DataGetRequest) request,
              (io.grpc.stub.StreamObserver<org.cse535.proto.DataGetResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class databaseBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    databaseBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.cse535.proto.ApaxosOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("database");
    }
  }

  private static final class databaseFileDescriptorSupplier
      extends databaseBaseDescriptorSupplier {
    databaseFileDescriptorSupplier() {}
  }

  private static final class databaseMethodDescriptorSupplier
      extends databaseBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    databaseMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (databaseGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new databaseFileDescriptorSupplier())
              .addMethod(getSaveMethod())
              .addMethod(getGetMethod())
              .build();
        }
      }
    }
    return result;
  }
}
