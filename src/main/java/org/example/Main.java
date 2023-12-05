package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws RuntimeException {
        try {
            // Connection DB local db pig happy, now pig sad
            // Modification in main branch
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Horbath\\Desktop\\Ejericicios\\PruebJuniorJAVA\\src\\main\\resources\\db\\dbtemplate.db");
            // Lee el archive JSON
            //JsonParser = new JsonParser();
            JsonElement rootElement = JsonParser.parseReader(new FileReader("C:\\Users\\Horbath\\Desktop\\Ejericicios\\PruebJuniorJAVA\\src\\main\\resources\\ExampleJson.json"));

            if (rootElement.isJsonArray()) {
                JsonArray jsonArray = rootElement.getAsJsonArray();

                for (JsonElement jsonElement : jsonArray) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();

                    String id = jsonObject.get("id").getAsString();
                    int age = jsonObject.get("age").getAsInt();
                    String name = jsonObject.get("name").getAsString();
                    String phone = jsonObject.get("phone").getAsString();
                    String address = jsonObject.get("address").getAsString();
                    String city = jsonObject.get("city").getAsString();
                    String state = jsonObject.get("state").getAsString();

                    String insertQuery = "INSERT INTO users (id, age, name, phone, address, city, state) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)";

                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, id);
                    preparedStatement.setInt(2, age);
                    preparedStatement.setString(3, name);
                    preparedStatement.setString(4, phone);
                    preparedStatement.setString(5, address);
                    preparedStatement.setString(6, city);
                    preparedStatement.setString(7, state);
                    preparedStatement.executeUpdate();
                    System.out.println("ID: " + id);
                    System.out.println("Age: " + age);
                    System.out.println("Name: " + name);
                    System.out.println("Phone: " + phone);
                    System.out.println("Address: " + address);
                    System.out.println("City: " + city);
                    System.out.println("State: " + state);
                    System.out.println();
                }
            } else {
                System.out.println("El archivo JSON no contiene una lista de objetos.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}