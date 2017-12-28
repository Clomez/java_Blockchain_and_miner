package com.clomez.blockchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class BlockchainApplication {

	public static int difficulty = 5;

	public static ArrayList<Block> blockchain = new ArrayList<Block>();

	public static boolean isChainValid() {
		Block current;
		Block previous;
		String hashTarget = new String(new char[difficulty]).replace("\0","0");

		//Loop the chain
		for(int i = 1; i < blockchain.size(); i++) {
			current = blockchain.get(i);
			previous = blockchain.get(i-1);

			//Compare current hashes
			if(!current.hash.equals(current.calculateHash()) ){
				System.out.println("Problem with current block in chain!");
				return false;
			}
			//Compare previous hashes
			if(!previous.hash.equals(previous.calculateHash()) ) {
				System.out.println("Problem with last block in chain!");
				return false;
			}
			if(!current.hash.substring(0, difficulty).equals(hashTarget)) {
				//System.out.println("Block not mined yet");
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BlockchainApplication.class, args);

		blockchain.add(new Block("First block","0"));
		blockchain.get(0).mineBlock(difficulty);

		int c = 1;
		while(true){

			String n = Integer.toString(c);

			blockchain.add(new Block(n, blockchain.get(blockchain.size()-1).hash));
			blockchain.get(c).mineBlock(difficulty);
			//System.out.println("is chain valid: " + isChainValid());
			c++;
			if(c == 10) {
				//String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
				//System.out.println(blockchainJson);
				RemoteMiner remote = new RemoteMiner();

			}

		}

	}

	@RestController
	public class Rest {

		@RequestMapping("/")
		public Block block() {
			Block latest = blockchain.get(blockchain.size()-1);
			return latest;
		}
	}
}
