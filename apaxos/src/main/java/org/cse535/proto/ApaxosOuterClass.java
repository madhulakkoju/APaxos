// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: apaxos.proto

package org.cse535.proto;

public final class ApaxosOuterClass {
  private ApaxosOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_TransactionInputConfig_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_TransactionInputConfig_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_TxnResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_TxnResponse_fieldAccessorTable;
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
    internal_static_AcceptRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AcceptRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AcceptRequest_SyncBlocksEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AcceptRequest_SyncBlocksEntry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AcceptResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AcceptResponse_fieldAccessorTable;
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
    internal_static_Transaction_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Transaction_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_BlockOfTransactions_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_BlockOfTransactions_fieldAccessorTable;
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
      "\n\014apaxos.proto\032\037google/protobuf/timestam" +
      "p.proto\"c\n\026TransactionInputConfig\022\021\n\tset" +
      "Number\030\001 \001(\005\022!\n\013transaction\030\002 \001(\0132\014.Tran" +
      "saction\022\023\n\013serverNames\030\005 \003(\t\"2\n\013TxnRespo" +
      "nse\022\017\n\007success\030\001 \001(\010\022\022\n\nserverName\030\002 \001(\t" +
      "\"j\n\016PrepareRequest\022\026\n\016proposalNumber\030\001 \001" +
      "(\005\022\021\n\tprocessId\030\002 \001(\t\022-\n\ttimestamp\030\003 \001(\013" +
      "2\032.google.protobuf.Timestamp\"\327\001\n\017Prepare" +
      "Response\022\026\n\016proposalNumber\030\001 \001(\005\022\021\n\tproc" +
      "essId\030\002 \001(\t\022\"\n\014transactions\030\004 \003(\0132\014.Tran" +
      "saction\022\037\n\027AcceptNumProposalNumber\030\005 \001(\005" +
      "\022\032\n\022AcceptNumProcessId\030\006 \001(\t\022\'\n\tAcceptVa" +
      "l\030\007 \001(\0132\024.BlockOfTransactions\022\017\n\007success" +
      "\030\003 \001(\010\"\245\002\n\rAcceptRequest\022\026\n\016proposalNumb" +
      "er\030\001 \001(\005\022\021\n\tprocessId\030\002 \001(\t\022*\n\024transacti" +
      "onsToAccept\030\004 \003(\0132\014.Transaction\0222\n\nsyncB" +
      "locks\030\005 \003(\0132\036.AcceptRequest.SyncBlocksEn" +
      "try\022\021\n\tneedsSync\030\006 \001(\010\022-\n\ttimestamp\030\003 \001(" +
      "\0132\032.google.protobuf.Timestamp\032G\n\017SyncBlo" +
      "cksEntry\022\013\n\003key\030\001 \001(\005\022#\n\005value\030\002 \001(\0132\024.B" +
      "lockOfTransactions:\0028\001\"h\n\016AcceptResponse" +
      "\022\026\n\016proposalNumber\030\001 \001(\005\022\021\n\tprocessId\030\002 " +
      "\001(\t\022\032\n\022acceptedServerName\030\003 \001(\t\022\017\n\007succe" +
      "ss\030\004 \001(\010\"\216\001\n\rCommitRequest\022\026\n\016proposalNu" +
      "mber\030\001 \001(\005\022\021\n\tprocessId\030\002 \001(\t\022#\n\005block\030\003" +
      " \001(\0132\024.BlockOfTransactions\022-\n\ttimestamp\030" +
      "\004 \001(\0132\032.google.protobuf.Timestamp\"h\n\016Com" +
      "mitResponse\022\026\n\016proposalNumber\030\001 \001(\005\022\017\n\007s" +
      "uccess\030\004 \001(\010\022\021\n\tprocessId\030\002 \001(\t\022\032\n\022accep" +
      "tedServerName\030\003 \001(\t\"\237\001\n\013Transaction\022\016\n\006s" +
      "ender\030\001 \001(\t\022\020\n\010receiver\030\002 \001(\t\022\016\n\006amount\030" +
      "\003 \001(\005\022-\n\ttimestamp\030\004 \001(\0132\032.google.protob" +
      "uf.Timestamp\022\027\n\017transactionHash\030\005 \001(\t\022\026\n" +
      "\016transactionNum\030\006 \001(\005\"\247\001\n\023BlockOfTransac" +
      "tions\022\"\n\014transactions\030\001 \003(\0132\014.Transactio" +
      "n\022\020\n\010blockNum\030\002 \001(\005\0223\n\017blockCommitTime\030\003" +
      " \001(\0132\032.google.protobuf.Timestamp\022\021\n\tbloc" +
      "kHash\030\004 \001(\t\022\022\n\ntermNumber\030\005 \001(\005\"+\n\025Activ" +
      "ateServerRequest\022\022\n\nserverName\030\001 \001(\t\"=\n\026" +
      "ActivateServerResponse\022\017\n\007success\030\001 \001(\010\022" +
      "\022\n\nserverName\030\002 \001(\t\"-\n\027DeactivateServerR" +
      "equest\022\022\n\nserverName\030\001 \001(\t\"?\n\030Deactivate" +
      "ServerResponse\022\017\n\007success\030\001 \001(\010\022\022\n\nserve" +
      "rName\030\002 \001(\t\"\016\n\014CommandInput\"\037\n\rCommandOu" +
      "tput\022\016\n\006output\030\001 \001(\t2\222\001\n\006Apaxos\022.\n\007prepa" +
      "re\022\017.PrepareRequest\032\020.PrepareResponse\"\000\022" +
      "+\n\006accept\022\016.AcceptRequest\032\017.AcceptRespon" +
      "se\"\000\022+\n\006commit\022\016.CommitRequest\032\017.CommitR" +
      "esponse\"\0002\241\001\n\017ActivateServers\022C\n\016activat" +
      "eServer\022\026.ActivateServerRequest\032\027.Activa" +
      "teServerResponse\"\000\022I\n\020deactivateServer\022\030" +
      ".DeactivateServerRequest\032\031.DeactivateSer" +
      "verResponse\"\0002O\n\014TnxPropagate\022?\n\024propaga" +
      "teTransaction\022\027.TransactionInputConfig\032\014" +
      ".TxnResponse\"\0002\304\001\n\010Commands\022/\n\014printBala" +
      "nce\022\r.CommandInput\032\016.CommandOutput\"\000\022+\n\010" +
      "printLog\022\r.CommandInput\032\016.CommandOutput\"" +
      "\000\022*\n\007printDB\022\r.CommandInput\032\016.CommandOut" +
      "put\"\000\022.\n\013Performance\022\r.CommandInput\032\016.Co" +
      "mmandOutput\"\000B\024\n\020org.cse535.protoP\001b\006pro" +
      "to3"
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
    internal_static_TransactionInputConfig_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_TransactionInputConfig_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_TransactionInputConfig_descriptor,
        new java.lang.String[] { "SetNumber", "Transaction", "ServerNames", });
    internal_static_TxnResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_TxnResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_TxnResponse_descriptor,
        new java.lang.String[] { "Success", "ServerName", });
    internal_static_PrepareRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_PrepareRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_PrepareRequest_descriptor,
        new java.lang.String[] { "ProposalNumber", "ProcessId", "Timestamp", });
    internal_static_PrepareResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_PrepareResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_PrepareResponse_descriptor,
        new java.lang.String[] { "ProposalNumber", "ProcessId", "Transactions", "AcceptNumProposalNumber", "AcceptNumProcessId", "AcceptVal", "Success", });
    internal_static_AcceptRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_AcceptRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AcceptRequest_descriptor,
        new java.lang.String[] { "ProposalNumber", "ProcessId", "TransactionsToAccept", "SyncBlocks", "NeedsSync", "Timestamp", });
    internal_static_AcceptRequest_SyncBlocksEntry_descriptor =
      internal_static_AcceptRequest_descriptor.getNestedTypes().get(0);
    internal_static_AcceptRequest_SyncBlocksEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AcceptRequest_SyncBlocksEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    internal_static_AcceptResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_AcceptResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AcceptResponse_descriptor,
        new java.lang.String[] { "ProposalNumber", "ProcessId", "AcceptedServerName", "Success", });
    internal_static_CommitRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_CommitRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommitRequest_descriptor,
        new java.lang.String[] { "ProposalNumber", "ProcessId", "Block", "Timestamp", });
    internal_static_CommitResponse_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_CommitResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommitResponse_descriptor,
        new java.lang.String[] { "ProposalNumber", "Success", "ProcessId", "AcceptedServerName", });
    internal_static_Transaction_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_Transaction_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Transaction_descriptor,
        new java.lang.String[] { "Sender", "Receiver", "Amount", "Timestamp", "TransactionHash", "TransactionNum", });
    internal_static_BlockOfTransactions_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_BlockOfTransactions_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_BlockOfTransactions_descriptor,
        new java.lang.String[] { "Transactions", "BlockNum", "BlockCommitTime", "BlockHash", "TermNumber", });
    internal_static_ActivateServerRequest_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_ActivateServerRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ActivateServerRequest_descriptor,
        new java.lang.String[] { "ServerName", });
    internal_static_ActivateServerResponse_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_ActivateServerResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ActivateServerResponse_descriptor,
        new java.lang.String[] { "Success", "ServerName", });
    internal_static_DeactivateServerRequest_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_DeactivateServerRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_DeactivateServerRequest_descriptor,
        new java.lang.String[] { "ServerName", });
    internal_static_DeactivateServerResponse_descriptor =
      getDescriptor().getMessageTypes().get(13);
    internal_static_DeactivateServerResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_DeactivateServerResponse_descriptor,
        new java.lang.String[] { "Success", "ServerName", });
    internal_static_CommandInput_descriptor =
      getDescriptor().getMessageTypes().get(14);
    internal_static_CommandInput_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommandInput_descriptor,
        new java.lang.String[] { });
    internal_static_CommandOutput_descriptor =
      getDescriptor().getMessageTypes().get(15);
    internal_static_CommandOutput_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommandOutput_descriptor,
        new java.lang.String[] { "Output", });
    com.google.protobuf.TimestampProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
