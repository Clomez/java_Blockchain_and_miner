package com.clomez.blockchain;


import java.util.Date;

public class Block {

    public String hash;
    public String prevHash;
    private String data;
    private Long timeStamp;
    private int nonce;

    //Block constructor
    public Block(String prevHash, String data) {

        this.prevHash = prevHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();

    }

    public String calculateHash() {
        String calculatedHash = StringUtil.applyHash(
                prevHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedHash;
    }


    public void mineBlock(int Difficulty) {
        String target = new String(new char[Difficulty]).replace("\0", "0");
        while(!hash.substring(0, Difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block mined! with hash: " + hash);

    }


}
