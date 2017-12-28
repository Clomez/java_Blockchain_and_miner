package com.clomez.blockchain;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class RemoteMiner {


    String json = readUrl("http://127.0.0.1:8080");

    Gson gson = new Gson();
    Block block = gson.fromJson(json, Block.class);


    public RemoteMiner() throws Exception {
       Block latest = block;
       System.out.println("PrevHash: " + latest.prevHash);
        System.out.println("Current Hash: " + latest.hash);
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

}
