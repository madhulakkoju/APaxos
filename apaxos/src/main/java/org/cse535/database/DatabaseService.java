package org.cse535.database;

import lombok.var;
import org.cse535.proto.BlockOfTransactions;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseService {

    public static HashMap< Integer, BlockOfTransactions> blocks = new HashMap<>();


    public static void addBlock(BlockOfTransactions block) {
        blocks.put(block.getBlockNum() , block);
    }

    public static BlockOfTransactions getBlock(int blockNum) {
            return blocks.get(blockNum);
    }

    public static List<BlockOfTransactions> getAllBlocks() {
        return (List<BlockOfTransactions>) blocks.values();
    }

    public static List<BlockOfTransactions> getBlocksFromGivenBlockNum(int blockNum) {

        return  ((List<BlockOfTransactions>) blocks.values()).stream()
                .filter(block -> block.getBlockNum() >= blockNum).collect(Collectors.toList());
    }



}
