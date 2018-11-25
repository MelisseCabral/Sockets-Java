package Main;

import Model.Client;
import Model.Server;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent> {

    private Button btnEnter;
    private TextField txtPort;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../views/connectScreen.fxml"));
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Chat Sockets");
        primaryStage.setScene(new Scene(root, 300, 400));
        primaryStage.show();

        btnEnter = (Button) root.lookup("#btnEnter");
        if (btnEnter != null){
            btnEnter.setText("Connect");
            btnEnter.setOnAction(this);
        }
        txtPort = (TextField) root.lookup("#txtBoxPort");

    }

    public void handle(ActionEvent event){

        if(event.getSource() == btnEnter){
            try {
                Server server = new Server(Integer.parseInt(txtPort.getText()));
                Client client = new Client(Integer.parseInt(txtPort.getText()));

                server.start();
                client.start();

                Parent chat = FXMLLoader.load(getClass().getResource("../views/chatScreen.fxml"));
                primaryStage.setScene(new Scene(chat, 500,400));

            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }
    public static void main(String[] args) {
        launch(args);

    }


}
