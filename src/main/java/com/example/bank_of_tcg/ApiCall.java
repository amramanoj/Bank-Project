package com.example.bank_of_tcg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;

public class ApiCall {
     String fn,ln,mn,an,pa;

    public  String apiCallRegistration(String[] data){
        this.fn=data[0];
        this.ln=data[1];
        this.mn=data[2];
        this.an=data[3];
        this.pa=data[4];
String result="";

        try {
            // open a connection to the site
            URL url = new URL("https://playludo.online/tcgbank.php");
            URLConnection con = url.openConnection();
            // activate the output
            con.setDoOutput(true);
            PrintStream ps = new PrintStream(con.getOutputStream());
            // send your parameters to your site
            ps.print("&fn=" + fn);
            ps.print("&ln=" + ln);
            ps.print("&mn=" + mn);
            ps.print("&an=" + an);
            ps.print("&pn=" + pa);


            // we have to get the input stream in order to actually send the request
            con.getInputStream();
            // print out to confirm
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                result=line;
                // close the print stream
                ps.close();
            }
        }catch (Exception e){  System.out.println(e);}

return result;
        }

}
