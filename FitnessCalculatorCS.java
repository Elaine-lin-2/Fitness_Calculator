/*
* Date: May 19th 2021
* Names: Tiffany, Krishna, Marc-Anthony, Elaine
* Teacher: Mr.Ho
* Description: A program that analyzes/compares (through a double bar graph) how much a student lifts vs. how much they can potentially 
                lift (using the Epley Formula), then provides each student with an individualized report containing how they can work 
                towards their calculated max lifts
* 
*/

//FitnessCalculatorCS 

//button
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//button events
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

//text
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.*;

//file reading and writing
import java.io.File;
import java.io.FileWriter;

public class FitnessCalculatorCS extends Application {

    public static void main(String[] args) {
        launch(args);
        //add methods into start(); instead of main
    }

    public void start(Stage primaryStage) {

        primaryStage.setTitle("Student information");
        Label labelfirst= new Label("    How many students would you like to record?");
        Label label1= new Label();
        Button button= new Button("OK");
        TextField text1= new TextField();

        VBox layout= new VBox(5);
        layout.getChildren().addAll(labelfirst,text1, button, label1);

        Scene scene1= new Scene(layout, 500, 500);
        primaryStage.setScene(scene1);
        primaryStage.show();

        button.setOnAction(f -> {

            //user menu
            primaryStage.setTitle("Menu");

            MenuBar menuBar = new MenuBar();
            VBox vBox = new VBox(menuBar);
            Scene scene = new Scene(vBox, 300, 400);
    
            primaryStage.setScene(scene);
            primaryStage.show();

            Menu menu = new Menu("Menu");
            MenuItem menuItem1 = new MenuItem("Enter user information");
            MenuItem menuItem2 = new MenuItem("Calculate expected weight and print bar graph");
            MenuItem menuItem3 = new MenuItem("Generate Individualized report");
            
            menu.getItems().add(menuItem1);
            menu.getItems().add(menuItem2);
            menu.getItems().add(menuItem3);

            //TO BE MODIFIED
            int number = Integer.parseInt(text1.getText());
            String []nameArray = new String[number];
            String []genderArray = new String[number];
            String []numArray = new String[number];

            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e){
                    //text
                    primaryStage.setTitle("User Information");
                    VBox layout= new VBox(5);

                    for(int i=0; i<number; i++){
                        
                        Label labelfirst= new Label("    Enter student name");
                        Label label1= new Label();
                        TextField text1= new TextField();
                        
                        Label labelsecond= new Label("    Enter student gender");
                        Label label2= new Label();
                        TextField text2= new TextField();

                        Label labelthird= new Label("    Enter the max number of reps");
                        Label label3= new Label();
                        TextField text3= new TextField();
                        
                        Button button= new Button("Show");

                        button.setOnAction(f -> {

                            //collects information -> to be printed in CSV
                            for(int j=0; j<number; j++){
                                nameArray[j] = text1.getText();
                                genderArray[j] = text2.getText();
                                numArray[j] = text3.getText();
                            }

                            for(int k=0; k<number; k++){

                                label1.setText("    Student name:  " + nameArray[k]);
                                label2.setText("    Their gender is: " + genderArray[k]);
                                label3.setText("    The max number of they can do: " + numArray[k]);
                            }

                        });
                        VBox layout2= new VBox(5);  

                        layout.getChildren().addAll(labelfirst, text1, labelsecond,
                        text2, labelthird, text3, button, layout2, label1, label2, label3);
                    }


                    Button button2= new Button("Go back to menu");
                    button2.setOnAction(f -> primaryStage.setScene(scene));
                    layout.getChildren().addAll(button2);
                    

                    
                    layout.setPrefSize(900,900);
                    primaryStage.setScene(new Scene(layout));
                    primaryStage.show();
                    
                }
                
            };
            //call event

            
            menuItem1.setOnAction(event);

            EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
                
                public void handle(ActionEvent e){
                    
                    primaryStage.setTitle("Expected weights");

                    Label label= new Label();
                    label.setText("    Calculating the expected weights!");

                    VBox layout= new VBox(5);
            
                    Button button2= new Button("Go back to menu");
                    button2.setOnAction(f -> primaryStage.setScene(scene));

                    layout.getChildren().addAll(label, button2);

                    Scene scene= new Scene(layout, 500, 500);
                    primaryStage.setScene(scene);

                    primaryStage.show();
                    
                }
            };
            menuItem2.setOnAction(event2);

            EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
                
                public void handle(ActionEvent e){
                    
                    primaryStage.setTitle("Reports");

                    Label label= new Label();
                    label.setText("    Generating individual reports!");

                    Button button2= new Button("Go back to menu");
                    button2.setOnAction(f -> primaryStage.setScene(scene));

                    VBox layout= new VBox(5);
                    layout.getChildren().addAll(label, button2);
                    
                    Scene scene= new Scene(layout, 500, 500);
                    primaryStage.setScene(scene);

                    primaryStage.show();
                    
                }
            };
            menuItem3.setOnAction(event3);

            //MenuBar menuBar = new MenuBar();
            menuBar.getMenus().add(menu);
                
        });
        
    }
    public static void printData() throws Exception { // need to add parameters so i can export data
        String fileLocation = "export.csv";
        File file = new File(fileLocation);
        FileWriter writer = new FileWriter(file);

        if (!file.exists()) {
            file.createNewFile();
            writer.write("title, title, more titles"); // Writes the title of the csv file if it wasn't made yet, will 
                                                       // change title to actual titles later once i know exactly what they are
        }

    }
}