// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: linearpbft.proto

package org.cse535.proto;

public final class Linearpbft {
  private Linearpbft() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PrePrepareRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_PrePrepareRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PrePrepareResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_PrePrepareResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PrepareRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_PrepareRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PrepareResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_PrepareResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CommitRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CommitRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CommitResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CommitResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ExecutionReplyRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ExecutionReplyRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ExecutionReplyResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ExecutionReplyResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ViewChangeRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ViewChangeRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ViewChangeResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ViewChangeResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_NewViewRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_NewViewRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_NewViewResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_NewViewResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_TransactionInputConfig_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_TransactionInputConfig_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Transaction_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Transaction_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_TxnResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_TxnResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_TxnRelayResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_TxnRelayResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ActivateServerRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ActivateServerRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ActivateServerResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ActivateServerResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DeactivateServerRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_DeactivateServerRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DeactivateServerResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_DeactivateServerResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CommandInput_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CommandInput_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CommandOutput_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CommandOutput_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020linearpbft.proto\032\037google/protobuf/time" +
      "stamp.proto\"\256\001\n\021PrePrepareRequest\022\014\n\004vie" +
      "w\030\001 \001(\005\022\026\n\016sequenceNumber\030\002 \001(\005\022!\n\013trans" +
      "action\030\003 \001(\0132\014.Transaction\022\021\n\tprocessId\030" +
      "\004 \001(\t\022-\n\ttimestamp\030\005 \001(\0132\032.google.protob" +
      "uf.Timestamp\022\016\n\006digest\030\006 \001(\t\"^\n\022PrePrepa" +
      "reResponse\022\014\n\004view\030\001 \001(\005\022\026\n\016sequenceNumb" +
      "er\030\002 \001(\005\022\021\n\tprocessId\030\003 \001(\t\022\017\n\007success\030\004" +
      " \001(\010\"\210\001\n\016PrepareRequest\022\014\n\004view\030\001 \001(\005\022\026\n" +
      "\016sequenceNumber\030\002 \001(\005\022\021\n\tprocessId\030\003 \001(\t" +
      "\022-\n\ttimestamp\030\004 \001(\0132\032.google.protobuf.Ti" +
      "mestamp\022\016\n\006digest\030\005 \001(\t\"[\n\017PrepareRespon" +
      "se\022\014\n\004view\030\001 \001(\005\022\026\n\016sequenceNumber\030\002 \001(\005" +
      "\022\021\n\tprocessId\030\003 \001(\t\022\017\n\007success\030\004 \001(\010\"\207\001\n" +
      "\rCommitRequest\022\014\n\004view\030\001 \001(\005\022\026\n\016sequence" +
      "Number\030\002 \001(\005\022\021\n\tprocessId\030\003 \001(\t\022-\n\ttimes" +
      "tamp\030\004 \001(\0132\032.google.protobuf.Timestamp\022\016" +
      "\n\006digest\030\005 \001(\t\"Z\n\016CommitResponse\022\014\n\004view" +
      "\030\001 \001(\005\022\026\n\016sequenceNumber\030\002 \001(\005\022\021\n\tproces" +
      "sId\030\003 \001(\t\022\017\n\007success\030\004 \001(\010\"\177\n\025ExecutionR" +
      "eplyRequest\022\014\n\004view\030\001 \001(\005\022\026\n\016sequenceNum" +
      "ber\030\002 \001(\005\022\021\n\tprocessId\030\003 \001(\t\022-\n\ttimestam" +
      "p\030\004 \001(\0132\032.google.protobuf.Timestamp\"b\n\026E" +
      "xecutionReplyResponse\022\014\n\004view\030\001 \001(\005\022\026\n\016s" +
      "equenceNumber\030\002 \001(\005\022\021\n\tprocessId\030\003 \001(\t\022\017" +
      "\n\007success\030\004 \001(\010\"c\n\021ViewChangeRequest\022\014\n\004" +
      "view\030\001 \001(\005\022\021\n\tprocessId\030\002 \001(\t\022-\n\ttimesta" +
      "mp\030\003 \001(\0132\032.google.protobuf.Timestamp\"F\n\022" +
      "ViewChangeResponse\022\014\n\004view\030\001 \001(\005\022\021\n\tproc" +
      "essId\030\002 \001(\t\022\017\n\007success\030\003 \001(\010\"\272\001\n\016NewView" +
      "Request\022\014\n\004view\030\001 \001(\005\022\021\n\tprocessId\030\002 \001(\t" +
      "\022-\n\ttimestamp\030\003 \001(\0132\032.google.protobuf.Ti" +
      "mestamp\022.\n\022viewChangeMessages\030\004 \003(\0132\022.Vi" +
      "ewChangeRequest\022(\n\017prepareMessages\030\005 \003(\013" +
      "2\017.PrepareRequest\"C\n\017NewViewResponse\022\014\n\004" +
      "view\030\001 \001(\005\022\021\n\tprocessId\030\002 \001(\t\022\017\n\007success" +
      "\030\003 \001(\010\"q\n\026TransactionInputConfig\022\021\n\tsetN" +
      "umber\030\001 \001(\005\022!\n\013transaction\030\002 \001(\0132\014.Trans" +
      "action\022\014\n\004view\030\003 \001(\005\022\023\n\013serverNames\030\005 \003(" +
      "\t\"\237\001\n\013Transaction\022\016\n\006sender\030\001 \001(\t\022\020\n\010rec" +
      "eiver\030\002 \001(\t\022\016\n\006amount\030\003 \001(\005\022-\n\ttimestamp" +
      "\030\004 \001(\0132\032.google.protobuf.Timestamp\022\027\n\017tr" +
      "ansactionHash\030\005 \001(\t\022\026\n\016transactionNum\030\006 " +
      "\001(\005\"2\n\013TxnResponse\022\017\n\007success\030\001 \001(\010\022\022\n\ns" +
      "erverName\030\002 \001(\t\"w\n\020TxnRelayResponse\022\017\n\007s" +
      "uccess\030\001 \001(\010\022\022\n\nserverName\030\002 \001(\t\022\016\n\006opti" +
      "on\030\003 \001(\005\022.\n\016executionReply\030\004 \001(\0132\026.Execu" +
      "tionReplyRequest\"+\n\025ActivateServerReques" +
      "t\022\022\n\nserverName\030\001 \001(\t\"=\n\026ActivateServerR" +
      "esponse\022\017\n\007success\030\001 \001(\010\022\022\n\nserverName\030\002" +
      " \001(\t\"-\n\027DeactivateServerRequest\022\022\n\nserve" +
      "rName\030\001 \001(\t\"?\n\030DeactivateServerResponse\022" +
      "\017\n\007success\030\001 \001(\010\022\022\n\nserverName\030\002 \001(\t\"\035\n\014" +
      "CommandInput\022\r\n\005input\030\001 \001(\t\"\037\n\rCommandOu" +
      "tput\022\016\n\006output\030\001 \001(\t2\302\003\n\nLinearPBFT\0222\n\007R" +
      "equest\022\027.TransactionInputConfig\032\014.TxnRes" +
      "ponse\"\000\022<\n\014RelayRequest\022\027.TransactionInp" +
      "utConfig\032\021.TxnRelayResponse\"\000\0227\n\nPrePrep" +
      "are\022\022.PrePrepareRequest\032\023.PrePrepareResp" +
      "onse\"\000\022.\n\007Prepare\022\017.PrepareRequest\032\020.Pre" +
      "pareResponse\"\000\022+\n\006Commit\022\016.CommitRequest" +
      "\032\017.CommitResponse\"\000\022C\n\016ExecutionReply\022\026." +
      "ExecutionReplyRequest\032\027.ExecutionReplyRe" +
      "sponse\"\000\0227\n\nViewChange\022\022.ViewChangeReque" +
      "st\032\023.ViewChangeResponse\"\000\022.\n\007NewView\022\017.N" +
      "ewViewRequest\032\020.NewViewResponse\"\0002\241\001\n\017Ac" +
      "tivateServers\022C\n\016activateServer\022\026.Activa" +
      "teServerRequest\032\027.ActivateServerResponse" +
      "\"\000\022I\n\020deactivateServer\022\030.DeactivateServe" +
      "rRequest\032\031.DeactivateServerResponse\"\0002\301\001" +
      "\n\010Commands\022+\n\010printLog\022\r.CommandInput\032\016." +
      "CommandOutput\"\000\022*\n\007printDB\022\r.CommandInpu" +
      "t\032\016.CommandOutput\"\000\022*\n\007FlushDB\022\r.Command" +
      "Input\032\016.CommandOutput\"\000\0220\n\rmakeByzantine" +
      "\022\r.CommandInput\032\016.CommandOutput\"\000B\024\n\020org" +
      ".cse535.protoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.TimestampProto.getDescriptor(),
        }, assigner);
    internal_static_PrePrepareRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_PrePrepareRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_PrePrepareRequest_descriptor,
        new java.lang.String[] { "View", "SequenceNumber", "Transaction", "ProcessId", "Timestamp", "Digest", });
    internal_static_PrePrepareResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_PrePrepareResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_PrePrepareResponse_descriptor,
        new java.lang.String[] { "View", "SequenceNumber", "ProcessId", "Success", });
    internal_static_PrepareRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_PrepareRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_PrepareRequest_descriptor,
        new java.lang.String[] { "View", "SequenceNumber", "ProcessId", "Timestamp", "Digest", });
    internal_static_PrepareResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_PrepareResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_PrepareResponse_descriptor,
        new java.lang.String[] { "View", "SequenceNumber", "ProcessId", "Success", });
    internal_static_CommitRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_CommitRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommitRequest_descriptor,
        new java.lang.String[] { "View", "SequenceNumber", "ProcessId", "Timestamp", "Digest", });
    internal_static_CommitResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_CommitResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommitResponse_descriptor,
        new java.lang.String[] { "View", "SequenceNumber", "ProcessId", "Success", });
    internal_static_ExecutionReplyRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_ExecutionReplyRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ExecutionReplyRequest_descriptor,
        new java.lang.String[] { "View", "SequenceNumber", "ProcessId", "Timestamp", });
    internal_static_ExecutionReplyResponse_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_ExecutionReplyResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ExecutionReplyResponse_descriptor,
        new java.lang.String[] { "View", "SequenceNumber", "ProcessId", "Success", });
    internal_static_ViewChangeRequest_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_ViewChangeRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ViewChangeRequest_descriptor,
        new java.lang.String[] { "View", "ProcessId", "Timestamp", });
    internal_static_ViewChangeResponse_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_ViewChangeResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ViewChangeResponse_descriptor,
        new java.lang.String[] { "View", "ProcessId", "Success", });
    internal_static_NewViewRequest_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_NewViewRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_NewViewRequest_descriptor,
        new java.lang.String[] { "View", "ProcessId", "Timestamp", "ViewChangeMessages", "PrepareMessages", });
    internal_static_NewViewResponse_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_NewViewResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_NewViewResponse_descriptor,
        new java.lang.String[] { "View", "ProcessId", "Success", });
    internal_static_TransactionInputConfig_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_TransactionInputConfig_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_TransactionInputConfig_descriptor,
        new java.lang.String[] { "SetNumber", "Transaction", "View", "ServerNames", });
    internal_static_Transaction_descriptor =
      getDescriptor().getMessageTypes().get(13);
    internal_static_Transaction_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Transaction_descriptor,
        new java.lang.String[] { "Sender", "Receiver", "Amount", "Timestamp", "TransactionHash", "TransactionNum", });
    internal_static_TxnResponse_descriptor =
      getDescriptor().getMessageTypes().get(14);
    internal_static_TxnResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_TxnResponse_descriptor,
        new java.lang.String[] { "Success", "ServerName", });
    internal_static_TxnRelayResponse_descriptor =
      getDescriptor().getMessageTypes().get(15);
    internal_static_TxnRelayResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_TxnRelayResponse_descriptor,
        new java.lang.String[] { "Success", "ServerName", "Option", "ExecutionReply", });
    internal_static_ActivateServerRequest_descriptor =
      getDescriptor().getMessageTypes().get(16);
    internal_static_ActivateServerRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ActivateServerRequest_descriptor,
        new java.lang.String[] { "ServerName", });
    internal_static_ActivateServerResponse_descriptor =
      getDescriptor().getMessageTypes().get(17);
    internal_static_ActivateServerResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ActivateServerResponse_descriptor,
        new java.lang.String[] { "Success", "ServerName", });
    internal_static_DeactivateServerRequest_descriptor =
      getDescriptor().getMessageTypes().get(18);
    internal_static_DeactivateServerRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_DeactivateServerRequest_descriptor,
        new java.lang.String[] { "ServerName", });
    internal_static_DeactivateServerResponse_descriptor =
      getDescriptor().getMessageTypes().get(19);
    internal_static_DeactivateServerResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_DeactivateServerResponse_descriptor,
        new java.lang.String[] { "Success", "ServerName", });
    internal_static_CommandInput_descriptor =
      getDescriptor().getMessageTypes().get(20);
    internal_static_CommandInput_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommandInput_descriptor,
        new java.lang.String[] { "Input", });
    internal_static_CommandOutput_descriptor =
      getDescriptor().getMessageTypes().get(21);
    internal_static_CommandOutput_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommandOutput_descriptor,
        new java.lang.String[] { "Output", });
    com.google.protobuf.TimestampProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
