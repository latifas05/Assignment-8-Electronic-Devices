package org.example.electronicdevices;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class HelloController {
    @FXML private RadioButton rdSmartphone;
    @FXML private RadioButton rdLaptop;
    @FXML private RadioButton rdTablet;

    @FXML private TextField textScreenSize;
    @FXML private TextField textCameraResolution;
    @FXML private TextField textRamSize;
    @FXML private TextField textProcessorType;
    @FXML private TextField textBatteryLife;
    @FXML private CheckBox checkHasStylus;

    @FXML private Label labelScreenSize;
    @FXML private Label labelCameraResolution;
    @FXML private Label labelRamSize;
    @FXML private Label labelProcessorType;
    @FXML private Label labelBatteryLife;
    @FXML private Label label; // Assuming you have a label for messages
    @FXML private TextField textName; // Make sure this is defined
    @FXML private TextField textPrice; // Make sure this is defined
    @FXML private TextField textWeight; // Make sure this is defined
    @FXML private ListView<String> listView; // Make sure this is defined

    private ObservableList<String> deviceList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set toggle group for radio buttons
        ToggleGroup deviceGroup = new ToggleGroup();
        rdSmartphone.setToggleGroup(deviceGroup);
        rdLaptop.setToggleGroup(deviceGroup);
        rdTablet.setToggleGroup(deviceGroup);

        // Set the list view items
        listView.setItems(deviceList);

        // Hide additional fields initially
        hideAdditionalFields();
    }

    // This method is called when a device type is selected
    @FXML
    private void onDeviceSelected(ActionEvent event) {
        hideAdditionalFields(); // Hide all fields initially
        if (rdSmartphone.isSelected()) {
            textScreenSize.setVisible(true);
            textCameraResolution.setVisible(true);
            labelScreenSize.setVisible(true);
            labelCameraResolution.setVisible(true);
        } else if (rdLaptop.isSelected()) {
            textRamSize.setVisible(true);
            textProcessorType.setVisible(true);
            labelRamSize.setVisible(true);
            labelProcessorType.setVisible(true);
        } else if (rdTablet.isSelected()) { // Tablet
            textBatteryLife.setVisible(true);
            checkHasStylus.setVisible(true);
            labelBatteryLife.setVisible(true);
        }
    }

    @FXML
    void onAddClick(ActionEvent event) {
        try {
            String name = textName.getText().trim();
            if (name.isEmpty()) {
                label.setText("Name cannot be empty.");
                return;
            }

            double price = Double.parseDouble(textPrice.getText().trim());
            double weight = Double.parseDouble(textWeight.getText().trim());
            String deviceDetails;

            if (rdSmartphone.isSelected()) {
                double screenSize = Double.parseDouble(textScreenSize.getText().trim());
                double cameraResolution = Double.parseDouble(textCameraResolution.getText().trim());
                deviceDetails = String.format("Smartphone - Name: %s, Price: %.2f, Weight: %.2f, Screen Size: %.1f, Camera: %.1f MP",
                        name, price, weight, screenSize, cameraResolution);
            } else if (rdLaptop.isSelected()) {
                int ramSize = Integer.parseInt(textRamSize.getText().trim());
                String processorType = textProcessorType.getText().trim();
                if (processorType.isEmpty()) {
                    label.setText("Processor Type cannot be empty.");
                    return;
                }
                deviceDetails = String.format("Laptop - Name: %s, Price: %.2f, Weight: %.2f, RAM: %d GB, Processor: %s",
                        name, price, weight, ramSize, processorType);
            } else { // Tablet
                double batteryLife = Double.parseDouble(textBatteryLife.getText().trim());
                boolean hasStylus = checkHasStylus.isSelected();
                deviceDetails = String.format("Tablet - Name: %s, Price: %.2f, Weight: %.2f, Battery Life: %.1f hours, Has Stylus: %s",
                        name, price, weight, batteryLife, hasStylus ? "Yes" : "No");
            }

            deviceList.add(deviceDetails);
            clearFields();
            label.setText("Device added successfully!");
        } catch (NumberFormatException e) {
            label.setText("Please enter valid numerical values.");
        }
    }

    @FXML
    void onRemoveClick(ActionEvent event) {
        String selectedDevice = listView.getSelectionModel().getSelectedItem();
        if (selectedDevice != null) {
            deviceList.remove(selectedDevice);
            label.setText("Device removed successfully!");
        } else {
            label.setText("Please select a device to remove.");
        }
    }

    private void hideAdditionalFields() {
        textScreenSize.setVisible(false);
        textCameraResolution.setVisible(false);
        textRamSize.setVisible(false);
        textProcessorType.setVisible(false);
        textBatteryLife.setVisible(false);
        checkHasStylus.setVisible(false);
        labelScreenSize.setVisible(false);
        labelCameraResolution.setVisible(false);
        labelRamSize.setVisible(false);
        labelProcessorType.setVisible(false);
        labelBatteryLife.setVisible(false);
    }

    private void clearFields() {
        textName.clear();
        textPrice.clear();
        textWeight.clear();
        textScreenSize.clear();
        textCameraResolution.clear();
        textRamSize.clear();
        textProcessorType.clear();
        textBatteryLife.clear();
        checkHasStylus.setSelected(false);
        label.setText("");
        rdSmartphone.setSelected(true); // Reset to default selection
        hideAdditionalFields(); // Hide fields after clearing
    }
}
