package com.example.bank_of_tcg;

import com.example.bank_of_tcg.php.TraHisGet;
import com.example.bank_of_tcg.php.TrasectionHistorySave;

import java.util.ArrayList;

public class Main  {
    public static void main(String[] args) {

//        String [] data={"amra","ram","6378820280","3167213174230","pass1234"};
////new ApiCall().apiCallRegistration(data);
//        //new AllDetail().allDetails(10021);
//      //  new GetId().myId("6378820280");
//       String str= new GetId().myId("6378820280");
//       for (String s:new Main().valueGet(str)){
//           System.out.println(s);

HelloApplication.main(args);
//       }

    }
    public String[] valueGet(String str){
        ArrayList<String> list=new ArrayList<>();
        str=str.replace("}",",");
        int i=0;
        String[] valuearr=new String[9];
        String[] splited = str.split(":");
       for(String vv:splited){
           if(i>0){
           //    System.out.println(vv.substring(0,vv.indexOf(",")));
list.add(vv.substring(0,vv.indexOf(",")));
           }
           i++;
       }
       String[] result=new String[list.size()];
       int p=0;
      for(String val:list){

          val=val.replace("\"","");
          result[p]=val;
          p++;
      }
return result;
    }
}
