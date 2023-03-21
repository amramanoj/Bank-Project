package com.example.bank_of_tcg.php;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AllDetail {
    public String allDetails(int bankId){
        String result="";
        try {
            URL url=new URL("https://playludo.online/tcgalldata.php")  ;

            URLConnection urlConnection= url.openConnection();
            urlConnection.setDoOutput(true);
            PrintStream printStream=new PrintStream(urlConnection.getOutputStream());
            printStream.print("&bankId="+bankId);

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                result=line;
                System.out.println(line);
                // close the print stream
                printStream.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public String update(int amount,int id){
        String result="";
        try {
            URL url=new URL("https://playludo.online/tcgupdate.php")  ;

            URLConnection urlConnection= url.openConnection();
            urlConnection.setDoOutput(true);
            PrintStream printStream=new PrintStream(urlConnection.getOutputStream());
            printStream.print("&amount="+amount);
            printStream.print("&id="+id);

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
