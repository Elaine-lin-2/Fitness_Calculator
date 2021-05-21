/*
* Date: May 19th 2021
* Names: Tiffany, Krishna, Marc-Anthony, Elaine
* Teacher: Mr.Ho
* Description: A program that analyzes/compares (through a double bar graph) how much a student lifts vs. how much they can potentially 
                lift (using the Epley Formula), then provides each student with an individualized report containing how they can work 
                towards their calculated max lifts
*/

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
//import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

//text
//import static javafx.application.Application.launch;
//import javafx.scene.text.*;
import javafx.scene.control.TextField;

//graphs
import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


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
            String []maxRepArray = new String[number]; //--> Added             (lol you can remove this comment (+the "added"s) once you've seen it 
   	 	    String []maxWeightArray = new String[number]; //--> Added            -just wanted to make sure my addition didn't disrupt your plans loll)
   	 	    String []expected1RMArray = new String[number]; //--> Added

            //do //{
   	 		//Subs in the entire max rep and max weight arrays and returns an array of expected 1RM results
    		expected1RMArray = EpleyCalculation(maxRepArray, maxWeightArray);
            //} while (true);
            //}

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

                    Button button3= new Button("Show bar graph");
                    button3.setOnAction(f -> {

                        CategoryAxis xAxis = new CategoryAxis();  
                        xAxis.setCategories(FXCollections.<String>
                        observableArrayList(Arrays.asList("Name 1", "Name 2", "Name 3", "Name 4")));
                        xAxis.setLabel("Student name");
                        
                        NumberAxis yAxis = new NumberAxis();
                        yAxis.setLabel("Weight");
                        
                        //Creating the Bar chart
                        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis); 
                        barChart.setTitle("Expect Weight vs. Actual Weight");
                        
                        //Prepare XYChart.Series objects by setting data       
                        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                        series1.setName("Expected weight");
    
                        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
                        series2.setName("Acutual weight");

                        for(int i=0; i< number; i++){
                            series1.getData().add(new XYChart.Data<>("Name 1", 1.0));
                            series1.getData().add(new XYChart.Data<>("Name 2", 1.0));
                            series2.getData().add(new XYChart.Data<>("Name 1", 5.0));
                            series2.getData().add(new XYChart.Data<>("Name 2", 5.0));

                            //Setting the data to bar chart       
                            barChart.getData().addAll(series1, series2);

                        }
                        
                        
                        //Creating a Group object 
                        Group root = new Group(barChart);
                        
                        

                        //Creating a scene object
                        Scene scene = new Scene(root, 600, 400);

                        //Setting title to the Stage
                        primaryStage.setTitle("Bar Chart");
                            
                        //Adding scene to the stage
                        primaryStage.setScene(scene);
                            
                        //Displaying the contents of the stage
                        primaryStage.show(); 
                    });

                    VBox layout= new VBox(5);
                    layout.getChildren().addAll(label, button2, button3);
                    
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
<<<<<<< HEAD

=======
    public static void printData() throws Exception { // need to add parameters so i can export data
        // initialise variables
        String fileLocation = "export.csv";
        File file = new File(fileLocation);
        FileWriter writer = new FileWriter(file);

        if (!file.exists()) {
            file.createNewFile();
            writer.write("title, title, more titles"); // Writes the title of the csv file if it wasn't made yet, will 
                                                       // change title to actual titles later once i know exactly what they are
        }

        writer.close();
    }
<<<<<<< HEAD

    public static String[] EpleyCalculation(String []maxRep, String []maxWeight) {
    	//1RM = w(1 + r/30) --> Epley's Formula
    	
    	//Going to be the array that will be returned, it contains the one rep max for each person
    	//maxRep.length is used so that the returned 1RM array has the amount on indices as the max rep and max weight array
    	String []oneRMArray = new String[maxRep.length];
    	
    	//Temporary string variables to hold values of each index in each array
    	String maxRepString = "";
    	String maxWeightString = "";
    	
    	//Temporary double variable to hold values of each index in each array, stored as a double to be used in calculations
    	double maxRepDouble = 0;
    	double maxWeightDouble = 0;
    	
    	//Loop through each index in both arrays
    	for (int i = 0; i < maxRep.length; i++) {
    		//Stores the string of each value into the other string variable (makes a copy)
    		maxRepString = maxRep[i];
    		maxWeightString = maxWeight[i];
    		
    		//Stores the string of each value into the double variable (makes a double copy of the string)
    		maxRepDouble = Double.parseDouble(maxRepString);
    		maxWeightDouble = Double.parseDouble(maxWeightString);
    		
    		//Calculates the expected 1RM for this person and stores result as a string
    		oneRMArray[i] = String.valueOf(maxWeightDouble * (1.0 + (maxRepDouble / 30.0)));
    	}
    	
    	//Return the string and store it in the "expected1RMArray" array
    	return oneRMArray;
    }
=======
>>>>>>> 65bde9bc69c51f56d54690f331648ff097205b20
>>>>>>> 6efa7af486a9c9d6d7af9f2ed0e673fec2f492a3
}