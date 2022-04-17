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

    private ArrayList<Car> inventory;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Dealership dealership = ReadJson.getDealershipFromJson("carData.json");
        dealerNameLabel.setText(dealership.getDealershipName());
        carListView.getItems().addAll(dealership.getInventory());
        inventory = dealership.getInventory();

        TreeSet<String> types = new TreeSet<>();
        for(Car car : inventory) {
            for (String type: car.getTypes()) {
                types.add(type);
            }
        }
        typeofCarListView.getItems().addAll(types);

        //attach a listener to the type list view to filter the list of cars.
        typeofCarListView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, typeSelected)->{
            //clear existing filter/selection
        carListView.getItems().clear();

        //loop over the inventory to check if the car is the correct type
//            if it is add to the list
        for (Car car: inventory) {
            if (car.isType(typeSelected)) {
                carListView.getItems().add(car);
            }
        }
//            System.out.println("Type selected " + newValue);
        });
    }
    //What type of data structure will sort elements and prevent duplicates?
    //tree - sorting.
    //sets - prevent duplicates.
    //treeset - both. comparable..
    //hashset - use hash value to sort
    //map ?
}
