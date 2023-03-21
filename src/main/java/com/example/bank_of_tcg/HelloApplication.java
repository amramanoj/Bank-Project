package com.example.bank_of_tcg;

import com.example.bank_of_tcg.php.AllDetail;
import com.example.bank_of_tcg.php.GetId;
import com.example.bank_of_tcg.php.TraHisGet;
import com.example.bank_of_tcg.php.TrasectionHistorySave;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class HelloApplication extends Application {
    Stage stage;
    int bankId=0;
    String[] data=new String[5];
    TableView history_table;
    Label balx=null;
    @Override
    public void start(Stage stage) throws IOException {

        this.stage=stage;
  logIn(stage);

    }

    public static void main(String[] args) {
        launch();
    }


    public void resister(Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("resister.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 660, 500);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("BANK OF TCG");
        stage.setScene(scene);
        stage.show();



        TextField fn=(TextField) scene.lookup("#fna");
        TextField ln=(TextField) scene.lookup("#lna");
        TextField mn=(TextField) scene.lookup("#mna");

        TextField an=(TextField) scene.lookup("#an");
        TextField pa=(TextField) scene.lookup("#pa");
        TextField cpa=(TextField) scene.lookup("#cpa");
CheckBox checkbox=(CheckBox)scene.lookup("#ck1") ;
        Button submit=(Button) scene.lookup("#submit");
        submit.setOnAction(e->{
          data[0]=fn.getText().toString();
            data[1]=ln.getText().toString();
            data[2]=mn.getText().toString();
            data[3]=an.getText().toString();
            data[4]=pa.getText().toString();

            if(fn.getText().toString().trim().length()>3&&mn.getText().trim().length()>=10&&mn.getText().trim().length()<14
            &&an.getText().toString().trim().length()==12&&pa.getText().trim().length()>6&&cpa.getText().trim().length()>6){

                if(cpa.getText().toString().equals(pa.getText().toString())){

                    if(checkbox.isSelected()){


registerPageSendData();

logIn(stage);



                    }else {
                        alert("please tick and agree checkbox");
                    }
                }else {

                    alert("Password mismatch");
                }

            }else {
                alert("Please Enter Right Data . space not allowed.");
            }


        });
        Label gotoback=(Label) scene.lookup("#gotoback");
        gotoback.setOnMouseClicked(e->{
            logIn(stage);
        });

    }
    public void logIn(Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 350);
            Label newac=(Label) scene.lookup("#newac");
            newac.setOnMouseClicked(e->{
                resister(stage);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("BANK OF TCG");
        stage.setScene(scene);
        stage.show();
TextField user=(TextField)scene.lookup("#user");

        TextField password=(TextField)scene.lookup("#password");
Button signInButton=(Button) scene.lookup("#signIn");
signInButton.setOnAction(e->{

 String id= new Main().valueGet(new GetId().myId(user.getText()))[0];

    if(!id.equals("fail")){
        if(password.getText().equals(logIn(id)[4])){
bankId=Integer.parseInt(id);
            mainbox(stage,logIn(id),bankId);
         //for Admin
         /*
           if(user.getText().equals("amra1234")&&password.getText().equals("Aa@771771")){

           }
          */
        }else {
            alert("wrong userName or password or network problem try again..");
            user.setText("");
            password.setText("");
        }
    }else {
        alert("wrong userName or password or network problem try again..");
        user.setText("");
        password.setText("");
    }



});

    }


    public void mainbox(Stage stage,String[] dataFromServer,int id){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1000, 650);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("BANK OF TCG");
        stage.setScene(scene);

        //
        Label adi=(Label)scene.lookup("#aid");
        balx=(Label)scene.lookup("#bal");
        ScrollPane sp=(ScrollPane) scene.lookup("#scroll");
        VBox vBox=(VBox) scene.lookup("#main_box");
        AnchorPane add=(AnchorPane)scene.lookup("#add") ;
        AnchorPane account_detail=(AnchorPane)scene.lookup("#account_detail") ;
        TextField amount=(TextField) scene.lookup("#amount");
        Button btn_aw=(Button) scene.lookup("#btn_aw");
        Button deposit=(Button) scene.lookup("#deposit");
        Button account_detail_btn=(Button) scene.lookup("#account_detail_btn");
        AnchorPane transfer_pane=(AnchorPane)scene.lookup("#transfer_pane") ;
        Button send=(Button)scene.lookup("#send") ;
        Button transfer=(Button)scene.lookup("#transfer");
        Button history_btn=(Button)scene.lookup("#history_btn");
        TextField re_bank_id=(TextField)scene.lookup("#re_bankId");
        TextField re_amount=(TextField)scene.lookup("#re_amount");

        AnchorPane id1=(AnchorPane) scene.lookup("#id1");
        AnchorPane id2=(AnchorPane) scene.lookup("#id2");
        AnchorPane id3=(AnchorPane) scene.lookup("#id3");
        AnchorPane id4=(AnchorPane) scene.lookup("#id4");
        AnchorPane id5=(AnchorPane) scene.lookup("#id5");

         history_table=(TableView)scene.lookup("#bank_history");

        Label  full_name=(Label) scene.lookup("#full_name");
        Label  mobile_number=(Label) scene.lookup("#mobile_number");
        Label  adr_number=(Label) scene.lookup("#adr_number");
        Label  ac_balance=(Label) scene.lookup("#ac_balance");
        Button withdrawal=(Button) scene.lookup("#withdrawal");
        //


        adi.setText(""+id);
        balx.setText(logIn(""+id)[5]);

        stage.show();



send.setOnAction(e->{
    try {
        int amountOfReceiver = Integer.parseInt(re_amount.getText());
        int idofReceiver = Integer.parseInt(re_bank_id.getText().toString());

        if (logIn("" + idofReceiver).length != 1) {

            update(amountOfReceiver, bankId, "t", idofReceiver);

        } else {

            alert("receiver bank Id not found");
        }
    }catch (Exception ex){
        alert("Enter valid data");
    }


});




        account_detail_btn.setOnAction(e->{
            history_table.setVisible(false);
add.setVisible(false);
            vBox.setVisible(false);
           account_detail.setVisible(true);
            transfer_pane.setVisible(false);

            full_name.setText(logIn(""+bankId)[0]+"  "+logIn(""+bankId)[1]);
            mobile_number.setText(logIn(""+bankId)[2]);
            adr_number.setText(logIn(""+bankId)[3]);
            ac_balance.setText(logIn(""+bankId)[5]);
            adi.setText(""+id);
            balx.setText(logIn(""+id)[5]);
        });
id1.setOnMouseClicked(e->{
    add.setVisible(false);
    history_table.setVisible(false);
    vBox.setVisible(false);
    account_detail.setVisible(true);
    transfer_pane.setVisible(false);

    full_name.setText(logIn(""+bankId)[0]+"  "+logIn(""+bankId)[1]);
    mobile_number.setText(logIn(""+bankId)[2]);
    adr_number.setText(logIn(""+bankId)[3]);
    ac_balance.setText(logIn(""+bankId)[5]);
    adi.setText(""+id);
    balx.setText(logIn(""+id)[5]);
});


        deposit.setOnAction(e->{
            history_table.setVisible(false);
            account_detail.setVisible(false);
        vBox.setVisible(false);
            transfer_pane.setVisible(false);
        btn_aw.setText("Deposit");
        add.setVisible(true);
        });
id2.setOnMouseClicked(e->{
    history_table.setVisible(false);
    account_detail.setVisible(false);
    vBox.setVisible(false);
    transfer_pane.setVisible(false);
    btn_aw.setText("Deposit");
    add.setVisible(true);
});


withdrawal.setOnAction(e->{
    history_table.setVisible(false);
    transfer_pane.setVisible(false);
    account_detail.setVisible(false);
            vBox.setVisible(false);
            btn_aw.setText("Withdrawal");
            add.setVisible(true);
        });
id3.setOnMouseClicked(e->{
    history_table.setVisible(false);
    transfer_pane.setVisible(false);
    account_detail.setVisible(false);
    vBox.setVisible(false);
    btn_aw.setText("Withdrawal");
    add.setVisible(true);
});

        transfer.setOnAction(e->{
            history_table.setVisible(false);
            add.setVisible(false);
            vBox.setVisible(false);
            account_detail.setVisible(false);
            transfer_pane.setVisible(true);
        });
        id4.setOnMouseClicked(e->{
            history_table.setVisible(false);
            add.setVisible(false);
            vBox.setVisible(false);
            account_detail.setVisible(false);
            transfer_pane.setVisible(true);
        });

        history_btn.setOnAction(e-> {

            history_table.setVisible(false);
            add.setVisible(false);
            vBox.setVisible(false);
            account_detail.setVisible(false);
            transfer_pane.setVisible(false);
history_table.setVisible(true);

            TableColumn dat = (TableColumn) history_table.getColumns().get(0);

            dat.setCellValueFactory(new PropertyValueFactory<>("dat"));
            dat.setStyle( "-fx-alignment: CENTER;");
            TableColumn client = (TableColumn) history_table.getColumns().get(1);
            client.setCellValueFactory(new PropertyValueFactory<>("client"));

           client.setStyle( "-fx-alignment: CENTER;");
            TableColumn amt= (TableColumn) history_table.getColumns().get(2);
            amt.setCellValueFactory(new PropertyValueFactory<>("amount"));
            amt.setStyle( "-fx-alignment: CENTER;");

            TableColumn type = (TableColumn) history_table.getColumns().get(3);
            type.setCellValueFactory(new PropertyValueFactory<>("type"));

            type.setStyle("-fx-text-fill: black; -fx-font-size: 13px;-fx-alignment: CENTER;");
            for ( int i = 0; i<history_table.getItems().size(); i++) {
                history_table.getItems().clear();
            }
            String data=new TraHisGet().getHistory(bankId,bankId);
            System.out.println();
            int p=0;
            Person tabledat=null;
            String[] all=new Main().valueGet(data);
            for(int i=0;i<all .length/5;i++){

                if(!all[p+4].equals("-3")){
                    all[p]="---";
                }
if(all[p+4].equals("-3")&&all[p].equals(""+bankId)){
    all[p+4]="Send money";
    all[p]=all[p+1];
}
                if(all[p+4].equals("-3")&&(!all[p].equals(""+bankId))){
                    all[p+4]="Receive money";

                }


                tabledat=   new Person(all[p+2],all[p],Integer.parseInt(all[p+3]),all[p+4]);
                p+=5;
                history_table.getItems().add(tabledat);
            }

                });

id5.setOnMouseClicked(e->{


    history_table.setVisible(false);
    add.setVisible(false);
    vBox.setVisible(false);
    account_detail.setVisible(false);
    transfer_pane.setVisible(false);
    history_table.setVisible(true);

    TableColumn dat = (TableColumn) history_table.getColumns().get(0);

    dat.setCellValueFactory(new PropertyValueFactory<>("dat"));
    dat.setStyle( "-fx-alignment: CENTER;");
    TableColumn client = (TableColumn) history_table.getColumns().get(1);
    client.setCellValueFactory(new PropertyValueFactory<>("client"));

    client.setStyle( "-fx-alignment: CENTER;");
    TableColumn amt= (TableColumn) history_table.getColumns().get(2);
    amt.setCellValueFactory(new PropertyValueFactory<>("amount"));
    amt.setStyle( "-fx-alignment: CENTER;");

    TableColumn type = (TableColumn) history_table.getColumns().get(3);
    type.setCellValueFactory(new PropertyValueFactory<>("type"));

    type.setStyle("-fx-text-fill: black; -fx-font-size: 13px;-fx-alignment: CENTER;");
    for ( int i = 0; i<history_table.getItems().size(); i++) {
        history_table.getItems().clear();
    }
    String data=new TraHisGet().getHistory(bankId,bankId);
    System.out.println();
    int p=0;
    Person tabledat=null;
    String[] all=new Main().valueGet(data);
    for(int i=0;i<all .length/5;i++){

        if(!all[p+4].equals("-3")){
            all[p]="---";
        }
        if(all[p+4].equals("-3")&&all[p].equals(""+bankId)){
            all[p+4]="Send money";
            all[p]=all[p+1];
        }
        if(all[p+4].equals("-3")&&(!all[p].equals(""+bankId))){
            all[p+4]="Receive money";

        }


        tabledat=   new Person(all[p+2],all[p],Integer.parseInt(all[p+3]),all[p+4]);
        p+=5;
        history_table.getItems().add(tabledat);
    }

});


btn_aw.setOnAction(e->{
    try{
    int amountb=Integer.parseInt(amount.getText().toString());
    if(amountb<=0){
        alert("Please Enter valid amount");
    }else {
        if(btn_aw.getText().toString().equals("Deposit")){
         update(amountb,bankId,"d",0);


        }
        else if(btn_aw.getText().toString().equals("Withdrawal")){
            update(amountb,bankId,"w",0);
        }
    }
    }catch (Exception ex){
        alert("Enter valid data");
    }

});
Button logOut=(Button) scene.lookup("#logOut");
logOut.setOnAction(e->{

    Locale.setDefault(new Locale("en", "English"));
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Message");
    alert.setHeaderText("Are you sure you want to LogOut?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get().equals(ButtonType.OK)) {
        logIn(stage);
    }else if (result.get().equals(ButtonType.NO)) {
        alert.close();
    }
});
Label home=(Label) scene.lookup("#home");
home.setOnMouseClicked(e->{

    mainbox(stage,logIn(""+bankId),bankId);
});

    }


public void registerPageSendData(){
    String res=new Main().valueGet(new ApiCall().apiCallRegistration(data))[0];
    if(res.equals("1")){
        String getId=new Main().valueGet(new GetId().myId(data[2]))[0];
        if(!getId.equals("fail")){
            alertinfo("successfully open account ! ac id is -  "+getId);
        }else {
            alert("Mobile number Already Exists or other error ");
        }
    }else {
        alert("Mobile number Already Exists or other error ");
    }
}
public String[] logIn(String id){
   String[] result=new Main().valueGet(new AllDetail().allDetails(Integer.parseInt(id)));
return result;
}
public void update(int amount ,int id ,String type,int foregnId){
        if(amount<=0){
            alert("Enter valid amount and try again..");
            return;
        }
        int bal = Integer.parseInt(logIn("" + id)[5]);


      if(type.equals("d")) {
          if (new Main().valueGet(new AllDetail().update(bal + amount, id))[0].equals("1")) {


              if(new Main().valueGet(new TrasectionHistorySave().saveHistory(0,id,amount,-1))[0].equals("1")){
                  alertinfo("Success");
                  balx.setText(logIn(""+id)[5]);
                  mainbox(stage, logIn("" + bankId), bankId);
              }




          } else {
              alert("fail try again");
          }
      }
      if(type.equals("w")){
         if(bal>=amount) {
             if (new Main().valueGet(new AllDetail().update(bal - amount, id))[0].equals("1")) {

                 if(new Main().valueGet(new TrasectionHistorySave().saveHistory(0,id,amount,-2))[0].equals("1")){
                     alertinfo("Success");
                     balx.setText(logIn(""+id)[5]);
                     mainbox(stage, logIn("" + bankId), bankId);
                 }

             } else {
                 alert("fail try again");
             }
         }
         else {
            alert("Account does not have sufficient balance");
         }

      }


    if(type.equals("t")){

if(id==foregnId){
    alert("you cant send money in same account ");
    return;
}

            if(bal>=amount) {
                if (new Main().valueGet(new AllDetail().update(bal - amount, bankId))[0].equals("1")) {

                    if (new Main().valueGet(new AllDetail().update(Integer.parseInt(logIn(""+foregnId)[5] )+ amount, foregnId))[0].equals("1")) {

                        if(new Main().valueGet(new TrasectionHistorySave().saveHistory(foregnId,id,amount,-3))[0].equals("1")){
                            alertinfo("Success");
                            balx.setText(logIn(""+id)[5]);
                            mainbox(stage, logIn("" + bankId), bankId);
                        }

                    }



                } else {
                    alert("fail try again");
                }
            }
            else {
                alert("Account does not have sufficient balance");
            }



    }


}

public void alert(String msg){
    Locale.setDefault(new Locale("en", "English"));
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Alert Box");
    alert.setHeaderText(msg);
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get().equals(ButtonType.OK)) {

    }else if (result.get().equals(ButtonType.NO)) {
        alert.close();
    }
}
    public void alertinfo(String msg){
        Locale.setDefault(new Locale("en", "English"));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert Box");
        alert.setHeaderText(msg);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(ButtonType.OK)) {

        }else if (result.get().equals(ButtonType.NO)) {
            alert.close();
        }
    }

}


/*


 */