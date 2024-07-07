package com.example.foodwastemanagementsystem.Controller;

import com.example.foodwastemanagementsystem.main.SelectedItem;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class RecorderController {

    @FXML
    private TableView<SelectedItem> cartTable;

    @FXML
    private TableColumn<SelectedItem, String> itemNameColumn;

    @FXML
    private TableColumn<SelectedItem, Integer> quantityColumn;

    @FXML
    private TableColumn<SelectedItem, String> categoryColumn;

    @FXML
    public void updateItem(ActionEvent event) {
        // Get the selected item from the TableView
        SelectedItem selectedItem = cartTable.getSelectionModel().getSelectedItem();
        doUpdate(selectedItem);
    }

    private void doUpdate(SelectedItem selectedItem) {
        if (selectedItem != null) {
            // Prompt the user for the new quantity
            TextInputDialog dialog = new TextInputDialog(String.valueOf(selectedItem.getQuantity()));
            dialog.setTitle("Update Quantity");
            dialog.setHeaderText("Enter the new quantity for " + selectedItem.getItem().getName() + ":");
            dialog.setContentText("Quantity:");

            Optional<String> result = dialog.showAndWait();

            // If the user entered a new quantity, update the quantity of the selected item
            result.ifPresent(quantity -> {
                try {
                    int newQuantity = Integer.parseInt(quantity);
                    selectedItem.setQuantity(newQuantity);

                    // Refresh the TableView to show the updated quantity
                    cartTable.refresh();

                    // Update the quantity in the text file
                    try {
                        java.nio.file.Path path = java.nio.file.Paths.get("src/main/resources/com/example/foodwastemanagementsystem/values/selectedItems.txt");
                        List<String> lines = java.nio.file.Files.readAllLines(path, StandardCharsets.UTF_8);

                        ListIterator<String> iterator = lines.listIterator();
                        while (iterator.hasNext()) {
                            String line = iterator.next();
                            if (line.contains("Name: " + selectedItem.getItemName())) {
                                // The next line is the category line
                                if (iterator.hasNext()) {
                                    iterator.next();
                                    iterator.set("Category: " + selectedItem.getCategory());
                                }
                                // The line after that is the quantity line
                                if (iterator.hasNext()) {
                                    iterator.next();
                                    iterator.set("Quantity: " + newQuantity);
                                }
                                break; // exit the loop after updating the category and quantity
                            }
                        }

                        java.nio.file.Files.write(path, lines, StandardCharsets.UTF_8);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (NumberFormatException e) {
                    // Show an error message if the entered quantity is not a valid integer
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Quantity");
                    alert.setHeaderText("The entered quantity is not a valid number.");
                    alert.setContentText("Please enter a valid number for the quantity.");
                    alert.showAndWait();
                }
            });
        }
    }

    @FXML
    public void deleteItem(ActionEvent event) {
        // Get only the single selected item
        SelectedItem singleItem = cartTable.getSelectionModel().getSelectedItem();

        // Remove the selected item from the cartTable
        cartTable.getItems().remove(singleItem);

        // Remove the selected item from the selectedItems list in the MarketController class
        MarketController.selectedItems.remove(singleItem);

        // Delete the selected item from the text file
        try {
            java.nio.file.Path path = java.nio.file.Paths.get("src/main/resources/com/example/foodwastemanagementsystem/values/selectedItems.txt");
            List<String> lines = java.nio.file.Files.readAllLines(path, StandardCharsets.UTF_8);

            ListIterator<String> iterator = lines.listIterator();
            while (iterator.hasNext()) {
                String line = iterator.next();
                if (line.contains("Name: " + singleItem.getItemName())) {
                    // Remove the current line and the next 3 lines
                    iterator.remove(); // remove the current line
                    for (int i = 0; i < 3; i++) {
                        if (iterator.hasNext()) {
                            iterator.next();
                            iterator.remove();
                        }
                    }
                    break; // exit the loop after removing the lines
                }
            }

            java.nio.file.Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearItem(ActionEvent event){
        cartTable.getItems().clear();
        MarketController.clearSelectedItemsList();
        try {
            File selectedItemsFile = new File("src/main/resources/com/example/foodwastemanagementsystem/values/selectedItems.txt");
            if (selectedItemsFile.exists()) {
                selectedItemsFile.delete(); // Delete the existing file
            }
            selectedItemsFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void handleMouseClick(MouseEvent event) {
        SelectedItem selectedItem = cartTable.getSelectionModel().getSelectedItem();
        System.out.println("Selected item: " + selectedItem);
    }

    @FXML
    public void initialize() {
        // Set up the columns in the table
        categoryColumn.setCellValueFactory(new PropertyValueFactory<SelectedItem, String>("category"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<SelectedItem, String>("itemName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<SelectedItem, Integer>("quantity"));

        // New column for Price
        TableColumn<SelectedItem, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<SelectedItem, Double>("price"));
        cartTable.getColumns().add(priceColumn);

        TableColumn<SelectedItem, Double> totalColumn = new TableColumn<>("Total");
        totalColumn.setCellValueFactory(cellData -> {
            SelectedItem selectedItem = cellData.getValue();
            double total = selectedItem.getQuantity() * selectedItem.getPrice();
            total = Math.round(total * 100.0) / 100.0;
            return new ReadOnlyObjectWrapper<>(total);
        });
        cartTable.getColumns().add(totalColumn);
        cartTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        // Convert ArrayList to ObservableList
        ObservableList<SelectedItem> observableList = FXCollections.observableArrayList(MarketController.selectedItems);

        // Load data
        cartTable.setItems(observableList);

        cartTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Checking double click
                SelectedItem selectedItem = cartTable.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    doUpdate(selectedItem);
                }
            }
        });
    }

    @FXML
    public void checkout(ActionEvent event) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dtStr = format.format(new Date());

        try {
            // Ensure the directory exists
            File directory = new File("receipts");
            if (!directory.exists())
                directory.mkdir();

            // Create and write to the receipt file
            File file = new File(directory, "receipt-" + dtStr + ".txt");
            FileWriter writer = new FileWriter(file);
            StringBuilder sb = new StringBuilder();

            // Add header with proper spacing
            sb.append(String.format("%-30s%-20s%-20s%-10s%-10s%n", "Name", "Quantity", "Category", "Price", "Total"));

            // Add each item with proper spacing
            for (SelectedItem item : cartTable.getItems()) {
                sb.append(String.format("%-30s%-20d%-20s%-10.2f%-10.2f%n",
                        item.getItemName(),
                        item.getQuantity(),
                        item.getCategory(),
                        item.getPrice(),
                        item.getQuantity() * item.getPrice()));
            }

            writer.write(sb.toString());
            writer.flush();
            writer.close();

            double totalPrice = 0.0;
            for (SelectedItem item : cartTable.getItems()) {
                totalPrice += item.getQuantity() * item.getPrice();
            }

            // Show confirmation dialog
            // JOptionPane.showMessageDialog(null, "Receipt saved! " + dtStr);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Checkout");
            alert.setHeaderText("Total Price");
            alert.setContentText("The total price of all items in the cart is: " + totalPrice);
            alert.showAndWait();

            // Clear cart table items
            cartTable.getItems().clear();

            // Clear the contents of selectedItems.txt file
            File selectedItemsFile = new File("src/main/resources/com/example/foodwastemanagementsystem/values/selectedItems.txt");
            if (selectedItemsFile.exists()) {
                selectedItemsFile.delete(); // Delete the existing file
            }

            // Create a new selectedItems.txt file
            selectedItemsFile.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
        MarketController.clearSelectedItemsList();
    }

    public void home(ActionEvent event) throws IOException {
        changeScene(event, "/com/example/foodwastemanagementsystem/views/home.fxml");
    }

    @FXML
    public void market(ActionEvent event) throws IOException {
        changeScene(event, "/com/example/foodwastemanagementsystem/views/market.fxml");
    }

    private void changeScene(ActionEvent event, String fxmlFile) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlFile));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}