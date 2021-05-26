/*
* Date: May 19th 2021
* Names: Tiffany, Krishna, Marc-Anthony, Elaine
* Teacher: Mr.Ho
* Description: A program that analyzes/compares (through a double bar graph) how much a student lifts vs. how much they can potentially 
* lift (using the Epley Formula), then provides each student with an individualized report containing how they can work 
* towards their calculated max lifts
*/
//file reading and writing
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//button, text, scene, label, text
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

//graphics 
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollBar;
import javafx.geometry.Orientation; 
import javafx.beans.value.ObservableValue;

public class FitnessCalculatorCS extends Application {
    public static void main(String[] args) {
        launch(args);
        //add methods into start(); instead of main
    }
    /*
    * Author: Elaine L
    * Run the program outside the terminal using favafx
    *
    * @param - new stage 
    *@ return - no return
    * */
    public void start(Stage primaryStage){

        /* for testing
        try {
            String[][] whatthehell = readData();
            for (String[] i: whatthehell) {
                for (String j: i) {
                    System.out.println(j);
                }
            }
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        */

        //Set title of the first scene
        primaryStage.setTitle("Student information");

        //prompt response from the user
        Label labelfirst= new Label("    How many students would you like to record?");
        Label label1= new Label();
        Button button= new Button("OK");
        TextField text1= new TextField();
        VBox layout= new VBox(5);
        //add labels, texts, buttons
        layout.getChildren().addAll(labelfirst,text1, button, label1);
        Scene scene1= new Scene(layout, 500, 500);
        primaryStage.setScene(scene1);
        primaryStage.show();
        //create button event
        button.setOnAction(f -> {
            //create user menu and menu bar
            primaryStage.setTitle("Menu");
            MenuBar menuBar = new MenuBar();
            VBox vBox = new VBox(menuBar);
            Scene scene = new Scene(vBox, 300, 400);
    
            //set scene
            primaryStage.setScene(scene);
            primaryStage.show();
            //set & add the three options
            Menu menu = new Menu("Menu");
            MenuItem menuItem1 = new MenuItem("Enter user information");
            MenuItem menuItem2 = new MenuItem("Generate Individualized report");
            MenuItem menuItem3 = new MenuItem("Calculate expected weight and print bar graph");
            menu.getItems().add(menuItem1);
            menu.getItems().add(menuItem2);
            menu.getItems().add(menuItem3);
            int number = Integer.parseInt(text1.getText());
            //define neccessary arrays
            String[][] csvArray = new String[0][0];
            String []genderArray = new String[number];
            String []numArray = new String[number];
            String []maxRepArray = new String[number]; //--> Added             (lol you can remove this comment (+the "added"s) once you've seen it 
            String []maxWeightArray = new String[number]; //--> Added            -just wanted to make sure my addition didn't disrupt your plans loll)
            String []expected1RMArray = new String[number]; //--> Added
            String []nameArray = new String[number];

            try {
                csvArray = readData();
                nameArray = new String[csvArray.length];
                genderArray = new String[csvArray.length];
                maxRepArray = new String[csvArray.length];
                for (int i = 0; i < csvArray.length; i++) {
                    nameArray[i] = csvArray[i][1];
                    genderArray[i] = csvArray[i][2];
                    maxRepArray[i] = csvArray[i][3];
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        //do //{
            //Subs in the entire max rep and max weight arrays and returns an array of expected 1RM results
        
        //expected1RMArray = EpleyCalculation(maxRepArray, maxWeightArray);
        //} while (true);
        //}
            //First button event (prompt students's info)
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                
                public void handle(ActionEvent e) {
                    
                    //add scroll bar to the scene and set the stage
                    final ScrollBar sc = new ScrollBar();
                    final VBox vb = new VBox(5);
                    Group root = new Group();
                    Scene scene1 = new Scene(root, 500, 900);
                    primaryStage.setScene(scene1);
                    primaryStage.setTitle("User Information");
                    root.getChildren().addAll(vb, sc);
                    vb.setLayoutX(10);
                    vb.setSpacing(10);
                    sc.setLayoutX(scene1.getWidth()-sc.getWidth());
                    sc.setMin(0);
                    sc.setOrientation(Orientation.VERTICAL);
                    sc.setPrefHeight(900);
                    sc.setVisibleAmount(50);
                    sc.setMax(scene1.getHeight()*number);
                    for (int i = 0; i < number; i++) {
                        // Prompt student information based on the number of students
                        Label labelfirst = new Label("    Enter student name");
                        Label label1 = new Label();
                        TextField text1 = new TextField();
                        Label label = new Label("    Student " + (i + 1));
                        Label labelsecond = new Label("    Enter student gender");
                        Label label2 = new Label();
                        TextField text2 = new TextField();
                        Label labelthird = new Label("    Enter the max number of reps");
                        Label label3 = new Label();
                        TextField text3 = new TextField();
                        // save and print the information
                        Button button = new Button("Save and show");
                        button.setOnAction(f -> {
                            label1.setText("    Student name:  " + text1.getText());
                            label2.setText("    Their gender is: " + text2.getText());
                            int numberReps = Integer.parseInt(text3.getText());
                            label3.setText("    The max number of they can do: " + numberReps);
                            vb.getChildren().addAll(label, label1, label2, label3);
                            try {
                                printData(text1.getText(), text2.getText(), numberReps);
                            }
                            catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            
                        });
                        VBox layout2 = new VBox(5);
                        // add elements to the scene
                        vb.getChildren().addAll(labelfirst, text1, labelsecond, text2, labelthird, text3, button,
                                layout2);
                }
                // scroll bar motion function
                sc.valueProperty()
                        .addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                            vb.setLayoutY(-new_val.doubleValue());
                        });
                // Go back to menu button
                Button button2 = new Button("Go back to menu");
                button2.setOnAction(f -> primaryStage.setScene(scene));
                vb.getChildren().addAll(button2);
                primaryStage.setScene(scene1);
                primaryStage.show();
                }
            };
            menuItem1.setOnAction(event);
            
            //call the second event (generate report)
            EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
                
                public void handle(ActionEvent e){
                    //add scroll bar
                    final ScrollBar sc = new ScrollBar();
                    final VBox vb = new VBox(5);
                    Group root = new Group();
                    Scene scene2 = new Scene(root, 500, 900);
                    //set first scene
                    primaryStage.setScene(scene2);
                    primaryStage.setTitle("User Information");
                    root.getChildren().addAll(vb, sc);
                    vb.setLayoutX(10);
                    vb.setSpacing(10);
                    sc.setLayoutX(scene2.getWidth()-sc.getWidth());
                    sc.setMin(0);
                    sc.setOrientation(Orientation.VERTICAL);
                    sc.setPrefHeight(900);
                    sc.setVisibleAmount(50);
                    sc.setMax(scene2.getHeight()*number);
                    //scroll bar motion 
                    sc.valueProperty().addListener((ObservableValue<? extends Number> ov, 
                        Number old_val, Number new_val) -> {
                        vb.setLayoutY(-new_val.doubleValue());
                    });
                    
                    //generate individualized reports
                    primaryStage.setTitle("Reports");
                    Label label= new Label();
                    label.setText("    Generating individual reports!");
                    Button button2 = new Button ("individual reports");
                    button2.setOnAction (f -> {
                        //analyse(expected1RMArray, primaryStage, scene, layout);
                    });
                    //go back to the main menu
                    Button button3= new Button("Go back to menu");
                    button3.setOnAction(f -> primaryStage.setScene(scene));
                    
                    //add element to scene
                    vb.getChildren().addAll(label, button3, button2);
                    primaryStage.setScene(scene2);
                    primaryStage.show();
                }
            };
            menuItem2.setOnAction(event2);

            try {
                csvArray = readData();
                numArray = csvArray[number];
                genderArray = csvArray[number];
                nameArray = csvArray[number];
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //call the third event
            EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
                
                public void handle(ActionEvent e){
                    
                    //create first scene
                    primaryStage.setTitle("Expected Weights + Bar Graph");
                    Label label= new Label();
                    label.setText("    Calculating the expected weights!");
                    Button button2= new Button("Go back to menu");
                    button2.setOnAction(f -> primaryStage.setScene(scene));
                    Button button3= new Button("Show bar graph");
                    //create double bar graph

                    button3.setOnAction(f -> {
                        CategoryAxis xAxis = new CategoryAxis();  
                        xAxis.setCategories(FXCollections.<String>
                        //Needs to be modified
                        observableArrayList(Arrays.asList("Name 1", "Name 2", "Name 3", "Name 4"))); //to be modified after reading the CSV file
                        xAxis.setLabel("Student name");
                        
                        NumberAxis yAxis = new NumberAxis();
                        yAxis.setLabel("Weight");
                        //Creating the Bar chart
                        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
                        barChart.setTitle("Expected Weight vs. Actual Weight");
                        //Prepare XYChart.Series objects by setting data       
                        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                        series1.setName("Expected weight");
    
                        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
                        series2.setName("Acutual weight");
                        
                        for(int i=0; i< number; i++){
                            try{
                                final String[][] tempCsvData = readData();
                                final String[] nameArray = fillArray(tempCsvData, 1);
                                final String[] genderArray = fillArray(tempCsvData, 2);
                                final String[] maxRepArray = fillArray(tempCsvData, 3);
                                

                                System.out.println(nameArray[i]);
                                
                                series1.getData().add(new XYChart.Data<>(nameArray[i], 5.0)); //expected
                                //series1.getData().add(new XYChart.Data<>("Name 2", 1.0));
                                //series1.getData().add(new XYChart.Data<>("Name 3", 1.0));
    
                                series2.getData().add(new XYChart.Data<>(nameArray[i], 5.0)); //actual
                                //series2.getData().add(new XYChart.Data<>("Name 2", 5.0));
                                //series2.getData().add(new XYChart.Data<>("Name 3", 5.0));
                            }
                            catch (Exception e1) {
                                e1.printStackTrace();
                            }
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
                    //set the scene
                    VBox layout= new VBox(5);
                    layout.getChildren().addAll(label, button2, button3);
                    Scene scene= new Scene(layout, 500, 500);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }
            };
            menuItem3.setOnAction(event3);
            menuBar.getMenus().add(menu);
        });
    }

    public static String[] fillArray(String[][] csvArray, int arrNum) {
        String[] temp = new String[csvArray.length];
        for (int j = 0; j < csvArray.length; j++) {
            temp[j] = csvArray[j][arrNum];
        }
        return temp;
    }

    /**
     * Exports the data given to a csv file
     * @author Marc F
     * @param name - the name of the person
     * @param gender - the gender of the person
     * @param number - the max number of reps that the person can do
     * @throws IOException if file is not found
     */
    public static void printData(String name, String gender, int number) throws IOException {
        // Initialise Variables
        String fileName = "student-info.csv";
        File file = new File(fileName);
        int trueStudentNumber = 0;
        String text = ""; // Text to use to export the data
        String delimiter = ","; // If you wanna change the delimiter, change here
        // Create a new file if it isnt made yet
        if (!file.exists()) {
            file.createNewFile();
            text += "Student Number,Name,Gender,Max number of reps\n";
            trueStudentNumber++;
        }
        // Initialise scanner
        Scanner reader = new Scanner(file);
        // Get the original contents of the csv file
        while (reader.hasNextLine()) {
            text += reader.nextLine() + "\n";
            trueStudentNumber++;
        }
        // Initialise FileWriter
        FileWriter writer = new FileWriter(file);
        // Add text by every category that is needed
        text += "Student " + trueStudentNumber;
        text += delimiter;
        text += name;
        text += delimiter;
        text += gender;
        text += delimiter;
        text += number;
        
        // Write the csv file back
        writer.write(text);
        // Close writer and reader
        writer.close();
        reader.close();
    }
    public static void printTxt(String text) {
        // will do this after
    }
    /**
     * Reads the data from the csv file and puts into an array
     * @author Marc F
     * @return 2D array containing the contents of the csv array
     * @throws FileNotFoundException if the file is not found
     */

    public static String[][] readData() throws FileNotFoundException {
        // Initialise Variables
        File file = new File("student-info.csv");
        Scanner reader = new Scanner(file);
        int length = 0;
        reader.nextLine(); // Need to skip the first line which is the title
        
        while (reader.hasNextLine()) {
            length++;
            reader.nextLine();
        }
        String[][] csvArray = new String[length][4]; 
        reader.close();
        reader = new Scanner(file); // reinitalise scanner to get it back to the top of the file
        reader.nextLine(); 
        
        length = 0; // reset length to use to populate the array
        while (reader.hasNextLine()) {
            // split each part of the file and populate the array with it
            String[] line = reader.nextLine().split(",");
            for (int i = 0; i < 4; i++) {
                csvArray[length][i] = line[i];
            }
            length++;
        }
    
        reader.close();
        return csvArray;
    }
    /**
     * @author Krishna 
     * Calculates expected 1RM and returns string to be stored in expected1RMArray
     * @param maxRep - array of max number of reps a person can do 
     * @param maxWeight - array of the max weight a person can lift 
     * @return oneRMAArray - calculated 1RM string 
     */
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
    /*
    * Author: Tiffany 
    * Loops through the array of each student's maximum weight that they can (in theory) lift, and provides everyone with a personal report (containing ways to improve)
    * @param expectedWeight - the array of all the student's calculated 1RM values
    * @param primaryStage - the primary window of the application
    * @param scene - displayed content regarding student's information/fitness inside window
    * @param layout - layout component that arranges in to column
    * no return
    * */
    
    public static void analyse(String[] expected1RMArray,Stage primaryStage,Scene scene, VBox layout){
        // Create double array for 1RM values
        double[] expectedDoubleArray = new double[expected1RMArray.length];
        
        for (int i = 0; i < expected1RMArray.length; i++){
            // Convert from string array to double array
            expectedDoubleArray[i] = Double.parseDouble(expected1RMArray[i]);
            // Reports:
            // 0-59
            Label label= new Label(); 
            label.setText("Here are some exercises you can try often to eventually reach your one-repetition maximum:");
            
            if ((expectedDoubleArray[i] > 0) && (expectedDoubleArray[i] < 60)){
                label.setText("Here are some exercises you can try often to eventually reach your one-repetition maximum:"+
                "(A maximum of 30s rests between each set and 45-60s between exercises)" + 
                "1. Do 1 set of as many slow and controlled pushups as you can until failure" + 
                "2. (Endurance improvement) Use a weight that is 70% of your 1RM and do pull ups for 6 sets of 15 reps"+
                "3. (To stress muscle fibres) Use a weight that is 80% of your 1RM and do bicep curls for 5 sets of 10 reps"+
                "4. (Power and speed improvement) Use a weight that is 90% of your 1RM and do deadlifts for 3 sets of 3-4 reps"+
                "5. (Push limits) Use a weight that is 95% of your 1RM and do for 2 sets of chest presses (sitting and/or standing) for 1-3 reps (have a spotter stand-by for this)"+
                "**Try to practice these as consistently as possible in order to see improvements/success!" + 
                "Check out the link for more information: https://barbend.com/how-to-increase-strength/");
                
            }
            else if ((expectedDoubleArray[i] >= 60) && (expectedDoubleArray[i] < 150)){
                label.setText("Here are some exercises you can try often to eventually reach your one-repetition maximum:"+
                "(A maximum of 30s rests between each set and 45-60s between exercises)" + 
                "1. Do 2 sets of as many slow and controlled pushups as you can until failure" + 
                "2. Hold a steady plank (no weights) for 2-3 minutes"+
                "3. (Endurance improvement) Use a weight that is 70% of your 1RM and do shoulder presses for 7 sets of 12 reps"+
                "4. (To stress muscle fibres) Use a weight that is 80% of your 1RM and do military/overhead presses for 4 sets of 12 reps"+
                "5. (Power and speed improvement) Use a weight that is 90% of your 1RM and do smith machine squats for 3 sets of 3-4 reps"+
                "6. (Push limits) Use a weight that is 95% of your 1RM and do for 2-3 sets of power cleans for 1-3 reps (have a spotter stand-by for this)" + 
                "**Try to practice these as consistently as possible in order to see improvements/success!" + 
                "Check out the link for more information: https://barbend.com/how-to-increase-strength/");
            }
            // 150-199
            else if ((expectedDoubleArray[i] >= 150) && (expectedDoubleArray[i] < 200)){
                label.setText("Here are some exercises you can try often to eventually reach your one-repetition maximum:"+
                "(A maximum of 30s rests between each set and 45-60s between exercises)" + 
                "1. Do 1 set of as many muscle ups as you can (just get reps in, form doesn’t matter as much for now)" + 
                "2. (Endurance improvement) Use a weight that is 70% of your 1RM and do pull ups for 10 sets of 10 reps" +
                "3. (To stress muscle fibres) Use a weight that is 80% of your 1RM and do lat pull downs for 7 sets of 7 reps"+
                "4. (Power and speed improvement) Use a weight that is 90% of your 1RM and do split squats for 4 sets of 3-4 reps"+
                "5. (Push limits) Use a weight that is 95% of your 1RM and do for 2 sets of supine presses for 2-3 reps (have a spotter stand-by for this)"+
                "6. (Push limits) Use a weight that is 95% of your 1RM and do for 2-3 sets of power cleans for 1-3 reps (have a spotter stand-by for this)" + 
                "**Try to practice these as consistently as possible in order to see improvements/success!" + 
                "Check out the link for more information: https://barbend.com/how-to-increase-strength/");
            }
            // 200-249
            
            else if ((expectedDoubleArray[i] >= 200) && (expectedDoubleArray[i] < 250)){
                label.setText("(A maximum of 30s rests between each set and 45-60s between exercises)" +
                "1. (Endurance improvement) Use a weight that is 70% of your 1RM and do single arm dumbbell rows for 7 sets of 20 reps (occasionally alternate between arms)" + 
                "2. (To stress muscle fibres) Use a weight that is 80% of your 1RM and do weighted push ups for 7 sets of 10 reps" +
                "3. (Power and speed improvement) Use a weight that is 90% of your 1RM and do back squats for 5 sets of 4-5 reps" +
                "4. (Push limits) Use a weight that is 95% of your 1RM and do for 3 sets of bench presses for 3 reps (have a spotter stand-by for this)"+
                "**Try to practice these as consistently as possible in order to see improvements/success!" + 
                "Check out the link for more information: https://barbend.com/how-to-increase-strength/");
            }
            // 250-299
            else if ((expectedDoubleArray[i] >= 250) && (expectedDoubleArray[i] < 300)){
                label.setText("Here are some exercises you can try often to eventually reach your one-repetition maximum:" +
                "(A maximum of 30s rests between each set and 45-60s between exercises)" + 
                "1. Hold a plank, carrying a fairly comfortable weight, for as long as you can until failure (at least 4 minutes)" + 
                "2. (Endurance improvement) Use a weight that is 70% of your 1RM and do muscle ups for 6-7 sets of 12 reps" + 
                "3. (To stress muscle fibres) Use a weight that is 80% of your 1RM and do bicep curls for 5 sets of 12 reps" + 
                "4. (Power and speed improvement) Use a weight that is 90% of your 1RM and do tricep pushdowns for 4 sets of 4 reps" + 
                "5. (Push limits) Use a weight that is 95% of your 1RM and do skullcrushers for 3 sets of 3 reps (have a spotter stand-by for this)" +
                "**Try to practice these as consistently as possible in order to see improvements/success!" + 
                "Check out the link for more information: https://barbend.com/how-to-increase-strength/");
                
            }
            // 300+
            else {
                label.setText("Here are some exercises you can try often to eventually reach your one-repetition maximum:" +
                "(A maximum of 30s rests between each set and 45-60s between exercises)" +
                "1. (Endurance improvement) Use a weight that is 70% of your 1RM and do bicep curls for 10 sets of 12 reps" +
                "2. (To stress muscle fibres) Use a weight that is 80% of your 1RM and do seated low rows for 8 sets of 10 reps" +
                "3. (Power and speed improvement) Use a weight that is 90% of your 1RM and do back squats for 4 sets of 3-4 reps" + 
                "4. (Push limits) Use a weight that is 95% of your 1RM and do for 3 sets of deadlifts for 3 reps (have a spotter stand-by for this)" + 
                "**Try to practice these as consistently as possible in order to see improvements/success!" + 
                "Check out the link for more information: https://barbend.com/how-to-increase-strength/");
            }
            
            layout.getChildren().addAll(label);
        }
    }
    /*
    public static void testing(Stage primaryStage,Scene scene, VBox layout){
        
        Label label= new Label();
        
        label.setText("Hello");
        
        layout.getChildren().addAll(label);
        //scene= new Scene(layout, 500, 500);
        
    }
    */
    
    
}