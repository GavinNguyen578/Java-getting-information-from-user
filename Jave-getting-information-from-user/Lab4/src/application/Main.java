package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	private Object GridPane;
	public final String EMPTY ="";
	@Override
	public void start(Stage stage) {
	System.out.println("Opening GridPane....");
	
	StackPane root = new StackPane();
	root.getChildren().add(Exercise1());
	Scene scene = new Scene(root, 400, 400)=;
	stage.setScene(scene); 
	stage.show();
	}
	private GridPane Exercise1() {
	// Create a pane and set its properties
	GridPane grid = new GridPane();
	grid.setPadding(new Insets(14, 14, 14, 14));
	grid.setHgap(10);
	grid.setVgap(10);
	

	// Place nodes in the pane
	Label name = new Label("Name:");
	grid.add(name, 0, 0);
	TextField txtname = new TextField();
	grid.add(txtname,1,0);
	

	Label address = new Label("Address:");
	grid.add(address, 0, 1);
	TextField txtaddress = new TextField();
	grid.add(txtaddress,1,1);
	
	
	Label provience = new Label("Provience:");
	grid.add(provience, 0, 2);
	TextField txtprovience = new TextField();
	grid.add(txtprovience,1,2);
	

	Label city = new Label("City:");
	grid.add(city, 0, 3);
	TextField txtcity = new TextField();
	grid.add(txtcity,1,3);
	
	

	Label postalcode = new Label("Postal Code:");
	grid.add(postalcode, 0, 4);
	TextField txtpostalcode = new TextField();
	grid.add(txtpostalcode,1,4);
	

	Label phonenumber = new Label("Phone Number");
	grid.add(phonenumber, 0, 5);
	TextField txtphonenumber = new TextField();
	grid.add(txtphonenumber,1,5);
	
	Label email = new Label("Email");
	grid.add(email, 0, 6);
	TextField txtemail = new TextField();
	grid.add(txtemail,1,6);
	
	
	 //Creating a Display Button 
	 Button disbutton = new Button ("Display");
	 grid.add(disbutton,3,7);
	
	
	//Creating CheckBox
	 CheckBox studentCouncil = new CheckBox("Student Council");
	    grid.add(studentCouncil,3,1);
	    
	    CheckBox volunteer = new CheckBox("Student Council");
	    grid.add(new CheckBox("Volunteer Work"),3,5);
	
	// Creating Radio Button to only select either Computer Science or Business 
	RadioButton radio1 = new RadioButton("Computer Science");
	radio1.setSelected(true);
   
	RadioButton radio2 = new RadioButton("Business");
    
	ToggleGroup radioGroup = new ToggleGroup();
    radio1.setToggleGroup(radioGroup);
    radio2.setToggleGroup(radioGroup);
    HBox hbox = new HBox(radio1,radio2);
    grid.add(hbox, 8, 0);

    // Creating a ComboBox
    String Pick = "--Choose--";
	String[] computerCourses = {Pick, "Java", "C", "C#", "Python"};
	String[] businessCourses = {Pick, "Accounting", "HR", "Finance", "Marketing"};
    
	ComboBox<String> courses = new ComboBox<String>(FXCollections.observableArrayList(computerCourses));
	courses.getSelectionModel().selectFirst();
	courses.setPrefSize(150, 50);
	grid.add(courses, 8, 1, 1, 2);
	
	ListView<String> selectedCourses = new ListView<String>();
	grid.add(selectedCourses, 8, 3, 1 ,4);
	
	  
	TextArea resultsTextArea = new TextArea();
	grid.add(resultsTextArea, 1, 10, 4, 1);
	resultsTextArea.setEditable(false);
	

	   disbutton.setOnAction(actionEvent -> {
		String name1 = txtname.getText();
		String address1 = txtaddress.getText();
		String province1 = txtprovience.getText();
		String city1 = txtcity.getText();
		String postalCode1 = txtpostalcode.getText();
		String phoneNumber1 = txtphonenumber.getText();
		String email1 = txtemail.getText();
			
		if (EMPTY.equals(name1) || EMPTY.equals(address1) || EMPTY.equals(province1) || EMPTY.equals(city1) || EMPTY.equals(postalCode1) || EMPTY.equals(phoneNumber1)) {
			this.alert("Required input missing", "Please provide all inputs", AlertType.ERROR);
		} else {
			StringBuffer ds = new StringBuffer();
			ds.append(name1+", "+address1+", "+province1+", "+city1+", "+postalCode1+", "+phoneNumber1+", "+email1);
			ds.append("\nCourses:");
			for (String course: selectedCourses.getItems()) {
				ds.append("\n"+course);
			}
			ds.append("\nAdditional Info: \nStudent Council Member? "+(studentCouncil.isSelected()?"Yes":"No")+"\nVolunteer Work? "+(volunteer.isSelected()?"Yes":"No"));
			
 		resultsTextArea.setText(ds.toString());
		}
	});

	radio1.setOnAction(ae -> {
		selectedCourses.getItems().clear();
		courses.getSelectionModel().clearSelection();
		courses.setItems(FXCollections.observableArrayList(computerCourses));
		courses.getSelectionModel().selectFirst();
	});
	
	radio2.setOnAction(ae -> {
		selectedCourses.getItems().clear();
		courses.getSelectionModel().clearSelection();
		courses.setItems(FXCollections.observableArrayList(businessCourses));
		courses.getSelectionModel().selectFirst();
	});
	
	courses.setOnAction(ae -> {
		String selection = courses.getSelectionModel().getSelectedItem();
		if (!selectedCourses.getItems().contains(selection) && !Pick.equals(selection) && selection!=null) {
			selectedCourses.getItems().add(selection);
		}
	});
    	
     return grid;

	}
	private void alert(String title, String message, AlertType error) {
		Alert alert = new Alert(error);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
		
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
