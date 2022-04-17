package com.example.w22comp1011w13;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadJson {
    /**
     * The method will read the JSON file and create a dealership obj.
     */
    public static Dealership getDealershipFromJson(String fileName) {
        Gson gson = new Gson();
        //try with resources will autoclose any of the items in the ()
        try(
                FileReader fileReader = new FileReader(fileName);
                JsonReader jsonReader = new JsonReader(fileReader);
                ) {
            //parse through json file create dealership obj.
            return gson.fromJson(jsonReader,Dealership.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        Dealership dealership = getDealershipFromJson("carData.json");
//        System.out.println(dealership);
//    }

}
