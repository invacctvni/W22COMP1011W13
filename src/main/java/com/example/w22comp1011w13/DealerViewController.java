package com.example.w22comp1011w13;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class DealerViewController implements Initializable {

    @FXML
    private ListView<Car> carListView;

    @FXML
    private Label dealerNameLabel;

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<String> typeofCarListView;

    @FXML
    private Label numOfVehiclesLabel;

    @FXML
    private Label vehicleValueLabel;


    private ArrayList<Car> inventory;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Dealership dealership = ReadJson.getDealershipFromJson("carData.json");
        dealerNameLabel.setText(dealership.getDealershipName());

        inventory = dealership.getInventory();
        Collections.sort(inventory);
        carListView.getItems().addAll(inventory);

        TreeSet<String> types = new TreeSet<>();
        for(Car car : inventory) {
            for (String type: car.getTypes()) {
                types.add(type);
            }
        }
        updateLabels();
        //Add a listener to the search text field.
        searchTextField.textProperty().addListener((obs,old,newValue)->{
            filter();
        });


        //add the types to the filter list.
        typeofCarListView.getItems().addAll(types);

        //attach a listener to the type list view to filter the list of cars.
        typeofCarListView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, typeSelected)->{
            //clear existing filter/selection
//        carListView.getItems().clear();

        //loop over the inventory to check if the car is the correct type
//            if it is add to the list
//        for (Car car: inventory) {
//            if (car.isType(typeSelected)) {
//                carListView.getItems().add(car);
//            }
//        }
//            System.out.println("Type selected " + newValue);
            //performing the filter using a stream.
//            carListView.getItems().addAll(inventory.stream()
//                    .filter(car -> car.isType(typeSelected))
//                    .filter(car -> car.contains(searchTextField.getText()))
//                    .toList());
            filter();
        });
    }

    private void filter() {
        carListView.getItems().clear();

        String typeSelected  = typeofCarListView.getSelectionModel().getSelectedItem();

        if(typeSelected == null) {
            carListView.getItems().addAll(inventory.stream()
                    .filter(car -> car.contains(searchTextField.getText())).sorted()
                    .toList());
        }
        else
            //perform the filter using a stream
        carListView.getItems().addAll(inventory.stream()
                .filter(car -> car.isType(typeSelected))
                .filter(car -> car.contains(searchTextField.getText())).sorted()
                .toList());
        updateLabels();
    }

    private void updateLabels() {
        numOfVehiclesLabel.setText("# of vehicles: " + carListView.getItems().size());
//        double sum = 0;
//        for (Car car: carListView.getItems()) {
//            sum += car.getPrice();
//        }
//        vehicleValueLabel.setText("Value of Inventory $" + carListView.getItems().stream().mapToDouble(car -> car.getPrice())
//                .sum()
        vehicleValueLabel.setText(String.format("Value of Inventory $%.0f", carListView.getItems().stream()
                .mapToDouble(car -> car.getPrice()).sum()));
    }

    //What type of data structure will sort elements and prevent duplicates?
    //tree - sorting.
    //sets - prevent duplicates.
    //treeset - both. comparable..
    //hashset - use hash value to sort
    //map ?
}
