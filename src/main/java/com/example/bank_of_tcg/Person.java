package com.example.bank_of_tcg;
public class Person {
   private String dat;
    private String client;


    private int amount;

private String type;
    public Person(String dat,String client,int amount,String type) {
        this.client = client;

        this.amount=amount;
        this.type=type;
this.dat=dat;
    }

    public String getDat() {
        return dat;
    }

    public String getClient() {
        return client;
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        if(type.equals("-1")){
            type="Deposit";
        }
        if(type.equals("-2")){
            type="Withdrawal";
        }
        return type;
    }
}