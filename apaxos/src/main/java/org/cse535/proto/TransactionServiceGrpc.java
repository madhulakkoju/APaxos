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
    comments = "Source: paxos.proto")
public final class TransactionServiceGrpc {

  private TransactionServiceGrpc() {}

  public static final String SERVICE_NAME = "TransactionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.cse535.proto.Transaction,
      org.cse535.proto.Transaction> getAddTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addTransaction",
      requestType = org.cse535.proto.Transaction.class,
      responseType = org.cse535.proto.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.cse535.proto.Transaction,
      org.cse535.proto.Transaction> getAddTransactionMethod() {
    io.grpc.MethodDescriptor<org.cse535.proto.Transaction, org.cse535.proto.Transaction> getAddTransactionMethod;
    if ((getAddTransactionMethod = TransactionServiceGrpc.getAddTransactionMethod) == null) {
      synchronized (TransactionServiceGrpc.class) {
        if ((getAddTransactionMethod = TransactionServiceGrpc.getAddTransactionMethod) == null) {
          TransactionServiceGrpc.getAddTransactionMethod = getAddTransactionMethod = 
              io.grpc.MethodDescriptor.<org.cse535.proto.Transaction, org.cse535.proto.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "TransactionService", "addTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.cse535.proto.Transaction.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.cse535.proto.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new TransactionServiceMethodDescriptorSupplier("addTransaction"))
                  .build();
          }
        }
     }
     return getAddTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.cse535.proto.SyncRequest,
      org.cse535.proto.SyncResponse> getSynchronizeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "synchronize",
      requestType = org.cse535.proto.SyncRequest.class,
      responseType = org.cse535.proto.SyncResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.cse535.proto.SyncRequest,
      org.cse535.proto.SyncResponse> getSynchronizeMethod() {
    io.grpc.MethodDescriptor<org.cse535.proto.SyncRequest, org.cse535.proto.SyncResponse> getSynchronizeMethod;
    if ((getSynchronizeMethod = TransactionServiceGrpc.getSynchronizeMethod) == null) {
      synchronized (TransactionServiceGrpc.class) {
        if ((getSynchronizeMethod = TransactionServiceGrpc.getSynchronizeMethod) == null) {
          TransactionServiceGrpc.getSynchronizeMethod = getSynchronizeMethod = 
              io.grpc.MethodDescriptor.<org.cse535.proto.SyncRequest, org.cse535.proto.SyncResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "TransactionService", "synchronize"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.cse535.proto.SyncRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.cse535.proto.SyncResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TransactionServiceMethodDescriptorSupplier("synchronize"))
                  .build();
          }
        }
     }
     return getSynchronizeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TransactionServiceStub newStub(io.grpc.Channel channel) {
    return new TransactionServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TransactionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TransactionServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TransactionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TransactionServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TransactionServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void addTransaction(org.cse535.proto.Transaction request,
        io.grpc.stub.StreamObserver<org.cse535.proto.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getAddTransactionMethod(), responseObserver);
    }

    /**
     */
    public void synchronize(org.cse535.proto.SyncRequest request,
        io.grpc.stub.StreamObserver<org.cse535.proto.SyncResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSynchronizeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddTransactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.cse535.proto.Transaction,
                org.cse535.proto.Transaction>(
                  this, METHODID_ADD_TRANSACTION)))
          .addMethod(
            getSynchronizeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.cse535.proto.SyncRequest,
                org.cse535.proto.SyncResponse>(
                  this, METHODID_SYNCHRONIZE)))
          .build();
    }
  }

  /**
   */
  public static final class TransactionServiceStub extends io.grpc.stub.AbstractStub<TransactionServiceStub> {
    private TransactionServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TransactionServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TransactionServiceStub(channel, callOptions);
    }

    /**
     */
    public void addTransaction(org.cse535.proto.Transaction request,
        io.grpc.stub.StreamObserver<org.cse535.proto.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void synchronize(org.cse535.proto.SyncRequest request,
        io.grpc.stub.StreamObserver<org.cse535.proto.SyncResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSynchronizeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TransactionServiceBlockingStub extends io.grpc.stub.AbstractStub<TransactionServiceBlockingStub> {
    private TransactionServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TransactionServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TransactionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.cse535.proto.Transaction addTransaction(org.cse535.proto.Transaction request) {
      return blockingUnaryCall(
          getChannel(), getAddTransactionMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.cse535.proto.SyncResponse synchronize(org.cse535.proto.SyncRequest request) {
      return blockingUnaryCall(
          getChannel(), getSynchronizeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TransactionServiceFutureStub extends io.grpc.stub.AbstractStub<TransactionServiceFutureStub> {
    private TransactionServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TransactionServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TransactionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.cse535.proto.Transaction> addTransaction(
        org.cse535.proto.Transaction request) {
      return futureUnaryCall(
          getChannel().newCall(getAddTransactionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.cse535.proto.SyncResponse> synchronize(
        org.cse535.proto.SyncRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSynchronizeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_TRANSACTION = 0;
  private static final int METHODID_SYNCHRONIZE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TransactionServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TransactionServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_TRANSACTION:
          serviceImpl.addTransaction((org.cse535.proto.Transaction) request,
              (io.grpc.stub.StreamObserver<org.cse535.proto.Transaction>) responseObserver);
          break;
        case METHODID_SYNCHRONIZE:
          serviceImpl.synchronize((org.cse535.proto.SyncRequest) request,
              (io.grpc.stub.StreamObserver<org.cse535.proto.SyncResponse>) responseObserver);
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

  private static abstract class TransactionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TransactionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.cse535.proto.PaxosOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TransactionService");
    }
  }

  private static final class TransactionServiceFileDescriptorSupplier
      extends TransactionServiceBaseDescriptorSupplier {
    TransactionServiceFileDescriptorSupplier() {}
  }

  private static final class TransactionServiceMethodDescriptorSupplier
      extends TransactionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TransactionServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TransactionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TransactionServiceFileDescriptorSupplier())
              .addMethod(getAddTransactionMethod())
              .addMethod(getSynchronizeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
