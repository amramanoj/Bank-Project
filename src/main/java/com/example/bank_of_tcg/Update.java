package com.example.bank_of_tcg;

import java.sql.*;

public class Update {
    public String update(int amount,int id ,String type,int reid){
        int update_balance=-3;
        String isupdate="no";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bank", "root", "");
            Statement smt=connection.createStatement();
            ResultSet resultSet=smt.executeQuery("select balance from bank where bankid = "+id);
            while (resultSet.next()){
                if(type.equals("d")){
                update_balance=Integer.parseInt(resultSet.getString("balance"))+amount;

                }
                if(type.equals("w")||type.equals("t")){
                    int ac_balance=Integer.parseInt(resultSet.getString("balance"));

                    if(ac_balance>=amount){
                        update_balance=ac_balance-amount;

                    }



                }


            }
            if(!(update_balance<0)) {


                if(type.equals("t")){
                    smt.executeUpdate("update bank set balance =" + update_balance + " where bankid = " + id);
              isupdate=transferFund(amount,reid);

                }else {
                    smt.executeUpdate("update bank set balance =" + update_balance + " where bankid = " + id);
                    isupdate = "yes";
                }

            }
        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
return isupdate;
    }
    public String transferFund(int amount,int reid ){
        String re="no";
        int updatebalance=-3;
        try {
        Connection    connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bank", "root", "");
            Statement smt=connection.createStatement();
            ResultSet resultSet=smt.executeQuery("select balance from bank where bankid = "+reid);

              while (resultSet.next()){
                  updatebalance=Integer.parseInt(resultSet.getString("balance"))+amount;
              }
              if(updatebalance!=-3){
            smt.executeUpdate("update bank set balance =" + updatebalance + " where bankid = " + reid);
re="yes";}
            }catch (Exception e){

        }
        return re;
    }
}
