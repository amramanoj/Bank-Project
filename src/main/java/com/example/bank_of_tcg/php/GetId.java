package com.example.bank_of_tcg.php;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;

public class GetId {
    public String myId(String mobileNum){
        String result="";
try{
        URL url = new URL("https://playludo.online/tcgId.php");
        URLConnection con = url.openConnection();
        // activate the output
        con.setDoOutput(true);
        PrintStream ps = new PrintStream(con.getOutputStream());
        // send your parameters to your site
        ps.print("&mobileNum=" + mobileNum);



        // we have to get the input stream in order to actually send the request
        con.getInputStream();
        // print out to confirm
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line = null;
        while ((line = in.readLine()) != null) {
            result=line;
            System.out.println(line);
            // close the print stream
            ps.close();
        }
    }catch (Exception e){  System.out.println(e);}

return result;
}
    }

