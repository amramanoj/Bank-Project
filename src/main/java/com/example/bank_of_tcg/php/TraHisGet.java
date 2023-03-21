package com.example.bank_of_tcg.php;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;

public class TraHisGet {
    public String getHistory(int seid,int reid){
        String result="";
        try {
            URL url=new URL("https://playludo.online/tcghisget.php")  ;

            URLConnection urlConnection= url.openConnection();
            urlConnection.setDoOutput(true);
            PrintStream printStream=new PrintStream(urlConnection.getOutputStream());
            printStream.print("&seId="+seid);
            printStream.print("&reId="+reid);
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                result=line;
                // close the print stream
                printStream.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
