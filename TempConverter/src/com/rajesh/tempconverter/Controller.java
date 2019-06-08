package com.rajesh.tempconverter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

	@FXML
	public Label welcomeLabel;
	@FXML
	public ChoiceBox<String> choiceBox;
	@FXML
	public TextField userInputField;
	@FXML
	public Button convertButton;

	private static final  String C_TO_F = "C to F";
	private static final  String F_TO_C = "F to C";

	private boolean isC_TO_F = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		choiceBox.getItems().add(C_TO_F);
		choiceBox.getItems().add(F_TO_C);
		choiceBox.setValue(C_TO_F);

		choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue.equals(C_TO_F)){
				isC_TO_F = true;
			}
			else{
				isC_TO_F = false;
			}
		});

		convertButton.setOnAction(event -> {
			convert();
		});
	}

	private void convert(){
		Float inputTemp = 0.0f;
		Float newTemp = 0.0f;
		try {
			inputTemp = Float.parseFloat(userInputField.getText());
			if(isC_TO_F){
				newTemp = (inputTemp*9/5)+32;
			}
			else{
				newTemp = (inputTemp-32)*5/9;
			}
			display(newTemp,inputTemp);
		}
		catch (Exception e){
			warnUser();
			return;
		}
	}

	private void warnUser() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Ocurred");
		alert.setHeaderText("Invalid Temp entered");
		alert.setContentText("ENter valid temp");
		alert.show();
	}

	private void display(Float newTemp, Float inpuTemp) {
		System.out.println("The converted temperature is: "+ newTemp+(isC_TO_F?"F":"C"));

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Converted Temp");
		alert.setHeaderText("The converted temperature is: ");
		alert.setContentText(newTemp+(isC_TO_F?"F":"C"));
		alert.show();
	}

}