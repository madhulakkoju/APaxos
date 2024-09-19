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
public final class PaxosGrpc {

  private PaxosGrpc() {}

  public static final String SERVICE_NAME = "Paxos";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.cse535.proto.PrepareRequest,
      org.cse535.proto.PrepareResponse> getPrepareMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "prepare",
      requestType = org.cse535.proto.PrepareRequest.class,
      responseType = org.cse535.proto.PrepareResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.cse535.proto.PrepareRequest,
      org.cse535.proto.PrepareResponse> getPrepareMethod() {
    io.grpc.MethodDescriptor<org.cse535.proto.PrepareRequest, org.cse535.proto.PrepareResponse> getPrepareMethod;
    if ((getPrepareMethod = PaxosGrpc.getPrepareMethod) == null) {
      synchronized (PaxosGrpc.class) {
        if ((getPrepareMethod = PaxosGrpc.getPrepareMethod) == null) {
          PaxosGrpc.getPrepareMethod = getPrepareMethod = 
              io.grpc.MethodDescriptor.<org.cse535.proto.PrepareRequest, org.cse535.proto.PrepareResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Paxos", "prepare"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.cse535.proto.PrepareRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.cse535.proto.PrepareResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PaxosMethodDescriptorSupplier("prepare"))
                  .build();
          }
        }
     }
     return getPrepareMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.cse535.proto.AcceptRequest,
      org.cse535.proto.AcceptResponse> getAcceptMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "accept",
      requestType = org.cse535.proto.AcceptRequest.class,
      responseType = org.cse535.proto.AcceptResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.cse535.proto.AcceptRequest,
      org.cse535.proto.AcceptResponse> getAcceptMethod() {
    io.grpc.MethodDescriptor<org.cse535.proto.AcceptRequest, org.cse535.proto.AcceptResponse> getAcceptMethod;
    if ((getAcceptMethod = PaxosGrpc.getAcceptMethod) == null) {
      synchronized (PaxosGrpc.class) {
        if ((getAcceptMethod = PaxosGrpc.getAcceptMethod) == null) {
          PaxosGrpc.getAcceptMethod = getAcceptMethod = 
              io.grpc.MethodDescriptor.<org.cse535.proto.AcceptRequest, org.cse535.proto.AcceptResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Paxos", "accept"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.cse535.proto.AcceptRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.cse535.proto.AcceptResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PaxosMethodDescriptorSupplier("accept"))
                  .build();
          }
        }
     }
     return getAcceptMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.cse535.proto.CommitRequest,
      org.cse535.proto.CommitResponse> getCommitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "commit",
      requestType = org.cse535.proto.CommitRequest.class,
      responseType = org.cse535.proto.CommitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.cse535.proto.CommitRequest,
      org.cse535.proto.CommitResponse> getCommitMethod() {
    io.grpc.MethodDescriptor<org.cse535.proto.CommitRequest, org.cse535.proto.CommitResponse> getCommitMethod;
    if ((getCommitMethod = PaxosGrpc.getCommitMethod) == null) {
      synchronized (PaxosGrpc.class) {
        if ((getCommitMethod = PaxosGrpc.getCommitMethod) == null) {
          PaxosGrpc.getCommitMethod = getCommitMethod = 
              io.grpc.MethodDescriptor.<org.cse535.proto.CommitRequest, org.cse535.proto.CommitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Paxos", "commit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.cse535.proto.CommitRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.cse535.proto.CommitResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PaxosMethodDescriptorSupplier("commit"))
                  .build();
          }
        }
     }
     return getCommitMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PaxosStub newStub(io.grpc.Channel channel) {
    return new PaxosStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PaxosBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PaxosBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PaxosFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PaxosFutureStub(channel);
  }

  /**
   */
  public static abstract class PaxosImplBase implements io.grpc.BindableService {

    /**
     */
    public void prepare(org.cse535.proto.PrepareRequest request,
        io.grpc.stub.StreamObserver<org.cse535.proto.PrepareResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPrepareMethod(), responseObserver);
    }

    /**
     */
    public void accept(org.cse535.proto.AcceptRequest request,
        io.grpc.stub.StreamObserver<org.cse535.proto.AcceptResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAcceptMethod(), responseObserver);
    }

    /**
     */
    public void commit(org.cse535.proto.CommitRequest request,
        io.grpc.stub.StreamObserver<org.cse535.proto.CommitResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCommitMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPrepareMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.cse535.proto.PrepareRequest,
                org.cse535.proto.PrepareResponse>(
                  this, METHODID_PREPARE)))
          .addMethod(
            getAcceptMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.cse535.proto.AcceptRequest,
                org.cse535.proto.AcceptResponse>(
                  this, METHODID_ACCEPT)))
          .addMethod(
            getCommitMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.cse535.proto.CommitRequest,
                org.cse535.proto.CommitResponse>(
                  this, METHODID_COMMIT)))
          .build();
    }
  }

  /**
   */
  public static final class PaxosStub extends io.grpc.stub.AbstractStub<PaxosStub> {
    private PaxosStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PaxosStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PaxosStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PaxosStub(channel, callOptions);
    }

    /**
     */
    public void prepare(org.cse535.proto.PrepareRequest request,
        io.grpc.stub.StreamObserver<org.cse535.proto.PrepareResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPrepareMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void accept(org.cse535.proto.AcceptRequest request,
        io.grpc.stub.StreamObserver<org.cse535.proto.AcceptResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAcceptMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void commit(org.cse535.proto.CommitRequest request,
        io.grpc.stub.StreamObserver<org.cse535.proto.CommitResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCommitMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PaxosBlockingStub extends io.grpc.stub.AbstractStub<PaxosBlockingStub> {
    private PaxosBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PaxosBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PaxosBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PaxosBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.cse535.proto.PrepareResponse prepare(org.cse535.proto.PrepareRequest request) {
      return blockingUnaryCall(
          getChannel(), getPrepareMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.cse535.proto.AcceptResponse accept(org.cse535.proto.AcceptRequest request) {
      return blockingUnaryCall(
          getChannel(), getAcceptMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.cse535.proto.CommitResponse commit(org.cse535.proto.CommitRequest request) {
      return blockingUnaryCall(
          getChannel(), getCommitMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PaxosFutureStub extends io.grpc.stub.AbstractStub<PaxosFutureStub> {
    private PaxosFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PaxosFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PaxosFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PaxosFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.cse535.proto.PrepareResponse> prepare(
        org.cse535.proto.PrepareRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPrepareMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.cse535.proto.AcceptResponse> accept(
        org.cse535.proto.AcceptRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAcceptMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.cse535.proto.CommitResponse> commit(
        org.cse535.proto.CommitRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCommitMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PREPARE = 0;
  private static final int METHODID_ACCEPT = 1;
  private static final int METHODID_COMMIT = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PaxosImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PaxosImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PREPARE:
          serviceImpl.prepare((org.cse535.proto.PrepareRequest) request,
              (io.grpc.stub.StreamObserver<org.cse535.proto.PrepareResponse>) responseObserver);
          break;
        case METHODID_ACCEPT:
          serviceImpl.accept((org.cse535.proto.AcceptRequest) request,
              (io.grpc.stub.StreamObserver<org.cse535.proto.AcceptResponse>) responseObserver);
          break;
        case METHODID_COMMIT:
          serviceImpl.commit((org.cse535.proto.CommitRequest) request,
              (io.grpc.stub.StreamObserver<org.cse535.proto.CommitResponse>) responseObserver);
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

  private static abstract class PaxosBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PaxosBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.cse535.proto.PaxosOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Paxos");
    }
  }

  private static final class PaxosFileDescriptorSupplier
      extends PaxosBaseDescriptorSupplier {
    PaxosFileDescriptorSupplier() {}
  }

  private static final class PaxosMethodDescriptorSupplier
      extends PaxosBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PaxosMethodDescriptorSupplier(String methodName) {
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
      synchronized (PaxosGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PaxosFileDescriptorSupplier())
              .addMethod(getPrepareMethod())
              .addMethod(getAcceptMethod())
              .addMethod(getCommitMethod())
              .build();
        }
      }
    }
    return result;
  }
}
