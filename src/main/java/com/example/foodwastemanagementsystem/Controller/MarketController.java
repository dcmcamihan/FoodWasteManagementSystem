package com.example.foodwastemanagementsystem.Controller;

import com.example.foodwastemanagementsystem.main.Item;
import com.example.foodwastemanagementsystem.main.Main;
import com.example.foodwastemanagementsystem.main.MyItemListener;
import com.example.foodwastemanagementsystem.main.SelectedItem;
import com.example.foodwastemanagementsystem.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class MarketController implements Initializable {
    @FXML
    private VBox chosenItemCard;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private GridPane grid;

    @FXML
    private ComboBox<Integer> cmbQuantity;

    private Image image;

    private Item chosenItem;
    public static List<SelectedItem> selectedItems = new ArrayList<>();
    private String currentCategory;

    @FXML
    private Label cartCounter;

    public void loadVeggies() {
        currentCategory = "vegetables";
        loadItems(getItemsByCategory("vegetables"));
    }

    public void loadFruits() {
        currentCategory = "fruits";
        loadItems(getItemsByCategory("fruits"));
    }

    public void loadDrinks() {
        currentCategory = "drinks";
        loadItems(getItemsByCategory("drinks"));
    }

    public void loadSpices() {
        currentCategory = "spices";
        loadItems(getItemsByCategory("spices"));
    }

    public void loadOthers() {
        currentCategory = "others";
        loadItems(getItemsByCategory("others"));
    }

    private List<Item> getItemsByCategory(String category) {
        switch (category.toLowerCase()) {
            case "fruits":
                return new ArrayList<Item>(getFruitsData());
            case "vegetables":
                return new ArrayList<Item>(getVegetablesData());
            case "drinks":
                return new ArrayList<Item>(getDrinksData());
            case "spices":
                return new ArrayList<Item>(getSpicesData());
            case "others":
                return new ArrayList<Item>(getOthersData());
            default:
                return new ArrayList<Item>();
        }
    }

    private List<Other> getOthersData() {
        List<Other> others = new ArrayList<>();

        try {
            URL url = getClass().getResource("/com/example/foodwastemanagementsystem/values/others.txt");
            Path path = Paths.get(url.toURI());

            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i += 5) {
                Other other = new Other();
                String name = lines.get(i).split(": ")[1];
                String imgSrc = lines.get(i + 1).split(": ")[1];
                double price = Double.parseDouble(lines.get(i + 2).split(": ")[1]);
                String color = lines.get(i + 3).split(": ")[1];

                other.setName(name);
                other.setImgSrc(imgSrc);
                other.setPrice(price);
                other.setColor(color);

                others.add(other);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return others;
    }

    private List<Spice> getSpicesData() {
        List<Spice> spices = new ArrayList<>();

        try {
            URL url = getClass().getResource("/com/example/foodwastemanagementsystem/values/spices.txt");
            Path path = Paths.get(url.toURI());

            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i += 5) {
                Spice spice = new Spice();
                String name = lines.get(i).split(": ")[1];
                String imgSrc = lines.get(i + 1).split(": ")[1];
                double price = Double.parseDouble(lines.get(i + 2).split(": ")[1]);
                String color = lines.get(i + 3).split(": ")[1];

                spice.setName(name);
                spice.setImgSrc(imgSrc);
                spice.setPrice(price);
                spice.setColor(color);

                spices.add(spice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return spices;
    }

    private List<Drink> getDrinksData() {
        List<Drink> drinks = new ArrayList<>();

        try {
            URL url = getClass().getResource("/com/example/foodwastemanagementsystem/values/drinks.txt");
            Path path = Paths.get(url.toURI());

            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i += 5) {
                Drink drink = new Drink();
                String name = lines.get(i).split(": ")[1];
                String imgSrc = lines.get(i + 1).split(": ")[1];
                double price = Double.parseDouble(lines.get(i + 2).split(": ")[1]);
                String color = lines.get(i + 3).split(": ")[1];

                drink.setName(name);
                drink.setImgSrc(imgSrc);
                drink.setPrice(price);
                drink.setColor(color);

                drinks.add(drink);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return drinks;
    }

    private List<Vegetable> getVegetablesData() {
        List<Vegetable> vegetables = new ArrayList<>();

        try {
            URL url = getClass().getResource("/com/example/foodwastemanagementsystem/values/vegetables.txt");
            Path path = Paths.get(url.toURI());

            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i += 5) {
                Vegetable vegetable = new Vegetable();
                String name = lines.get(i).split(": ")[1];
                String imgSrc = lines.get(i + 1).split(": ")[1];
                double price = Double.parseDouble(lines.get(i + 2).split(": ")[1]);
                String color = lines.get(i + 3).split(": ")[1];

                vegetable.setName(name);
                vegetable.setImgSrc(imgSrc);
                vegetable.setPrice(price);
                vegetable.setColor(color);

                vegetables.add(vegetable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vegetables;
    }

    private List<Fruit> getFruitsData() {
        List<Fruit> fruits = new ArrayList<>();

        try {
            URL url = getClass().getResource("/com/example/foodwastemanagementsystem/values/fruits.txt");
            Path path = Paths.get(url.toURI());

            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i += 5) {
                Fruit fruit = new Fruit();
                String name = lines.get(i).split(": ")[1];
                String imgSrc = lines.get(i + 1).split(": ")[1];
                double price = Double.parseDouble(lines.get(i + 2).split(": ")[1]);
                String color = lines.get(i + 3).split(": ")[1];

                fruit.setName(name);
                fruit.setImgSrc(imgSrc);
                fruit.setPrice(price);
                fruit.setColor(color);

                fruits.add(fruit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fruits;
    }

    public void loadItems(List<Item> items) {
        grid.getChildren().clear();
        MyItemListener itemListener = null;

        if (items.size() > 0) {
            setChosenItem(items.get(0));
            itemListener = new MyItemListener() {
                @Override
                public void onClickListener(Item item) {
                    setChosenItem(item);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < items.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/foodwastemanagementsystem/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(items.get(i), itemListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setChosenItem(Item item) {
        nameLabel.setText(item.getName());
        priceLabel.setText(Main.CURRENCY + item.getPrice());
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc())));
        imageView.setImage(image);
        chosenItem = item;
        chosenItemCard.setStyle("-fx-background-color: #" + item.getColor() + ";\n" +
                "    -fx-background-radius: 30;");
    }

    public static void clearSelectedItemsList() {
        selectedItems.clear();
    }

    @FXML
    public void onAddToListClicked() {
        // Create a new SelectedItem and set its item
        SelectedItem selectedItem = new SelectedItem();
        selectedItem.setItem(chosenItem);

        // Get the selected value from the cmbPiece ComboBox, defaulting to 1 if no value is selected
        int quantity = (cmbQuantity.getValue() != null) ? cmbQuantity.getValue() : 1;

        // Set the quantity of the SelectedItem
        selectedItem.setQuantity(quantity);
        selectedItem.setCategory(currentCategory);

        // Add the SelectedItem to the list and print the list
        addItemToList(selectedItem);

        // Update the cart counter
        cartCounter.setText(String.valueOf(selectedItems.size()));
        saveSelectedItemsToFile();
    }

    private void addItemToList(SelectedItem newItem) {
        // Check if the item already exists in the list
        Optional<SelectedItem> existingItemOptional = selectedItems.stream()
                .filter(item -> item.getItem().getName().equals(newItem.getItem().getName()))
                .findFirst();

        if (existingItemOptional.isPresent()) {
            // If the item exists, add the quantity of the new item to the existing item
            SelectedItem existingItem = existingItemOptional.get();
            existingItem.setQuantity(existingItem.getQuantity() + newItem.getQuantity());
        } else {
            // If the item does not exist, add it to the list
            selectedItems.add(newItem);
        }
    }

    public void saveSelectedItemsToFile() {
        if (selectedItems.isEmpty()) {
            System.out.println("No items to write to file.");
            return;
        }

        try {
            File file = new File("./src/main/resources/com/example/foodwastemanagementsystem/values/selectedItems.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, false); // Set append to false
            for (SelectedItem item : selectedItems) {
                writer.write("Name: " + item.getItem().getName() + "\n");
                writer.write("Category: " + item.getCategory() + "\n");
                writer.write("Quantity: " + item.getQuantity() + "\n");
                writer.write("-------------------\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
            e.printStackTrace();
        }

        loadSelectedItemsFromFile();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadVeggies();

        // Define the list of values
        ObservableList<Integer> quantities =
                FXCollections.observableArrayList(
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10
                );

        ObservableList<String> units =
                FXCollections.observableArrayList(
                        "Kg", "g", "L", "ml", "Piece"
                );

        // Set the list of values to the ComboBox
        cmbQuantity.setItems(quantities);
        cmbQuantity.setValue(1);

        loadSelectedItemsFromFile();
    }

    public void loadSelectedItemsFromFile() {
        File file = new File("./src/main/resources/com/example/foodwastemanagementsystem/values/selectedItems.txt");
        if (!file.exists()) {
            System.out.println("No items to load from file.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            cartCounter.setText("");
            selectedItems.clear();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ")) {
                    String itemName = line.split(": ")[1];
                    Item item;
                    String category = reader.readLine().split(": ")[1];
                    switch (category.toLowerCase()) {
                        case "fruits":
                            item = getFruitByName(itemName);
                            break;
                        case "vegetables":
                            item = getVegetableByName(itemName);
                            break;
                        case "drinks":
                            item = getDrinkByName(itemName);
                            break;
                        case "spices":
                            item = getSpiceByName(itemName);
                            break;
                        case "others":
                            item = getOtherByName(itemName);
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid category: " + category);
                    }

                    SelectedItem selectedItem = new SelectedItem();
                    selectedItem.setItem(item);
                    selectedItem.setQuantity(Integer.parseInt(reader.readLine().split(": ")[1]));
                    selectedItem.setCategory(category);
                    selectedItem.setPrice(item.getPrice());
                    selectedItems.add(selectedItem);
                    reader.readLine(); // Skip the separator line
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file: " + e.getMessage());
            e.printStackTrace();
        }

        // Update the cart counter
        cartCounter.setText(String.valueOf(selectedItems.size()));
    }

    private Fruit getFruitByName(String name) throws IOException {
        return getFruitsData().stream()
                .filter(fruit -> fruit.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Fruit not found: " + name));
    }

    private Vegetable getVegetableByName(String name) throws IOException {
        return getVegetablesData().stream()
                .filter(vegetable -> vegetable.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Vegetable not found: " + name));
    }

    private Drink getDrinkByName(String name) throws IOException {
        return getDrinksData().stream()
                .filter(drink -> drink.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Drink not found: " + name));
    }

    private Spice getSpiceByName(String name) throws IOException {
        return getSpicesData().stream()
                .filter(spice -> spice.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Spice not found: " + name));

    }

    private Other getOtherByName(String name) throws IOException {
        return getOthersData().stream()
                .filter(other -> other.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Other not found: " + name));

    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void home(ActionEvent event) throws IOException {
        changeScene(event, "/com/example/foodwastemanagementsystem/views/home.fxml");
    }

    @FXML
    public void record(ActionEvent event) throws IOException {
        changeScene(event, "/com/example/foodwastemanagementsystem/views/records.fxml");
    }


    private void changeScene(ActionEvent event, String fxmlFile) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlFile));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        // Get the stage and close it
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

