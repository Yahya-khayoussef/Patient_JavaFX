package org.example.product_javafx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;

public class ProductController {

    @FXML private TextField nameField;
    @FXML private TextField priceField;
    @FXML private ListView<Product> productListView;

    private ObservableList<Product> productList;

    @FXML
    public void initialize() {
        productList = FXCollections.observableArrayList();
        productListView.setItems(productList);
    }

    @FXML
    public void handleAddProduct() {
        String name = nameField.getText();
        String priceText = priceField.getText();

        if (name.isEmpty() || priceText.isEmpty()) {
            showAlert("Veuillez remplir tous les champs.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            Product product = new Product(name, price);
            productList.add(product);
            nameField.clear();
            priceField.clear();
        } catch (NumberFormatException e) {
            showAlert("Le prix doit être un nombre.");
        }
    }

    @FXML
    public void handleDeleteProduct() {
        Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            productList.remove(selectedProduct);
        } else {
            showAlert("Veuillez sélectionner un produit à supprimer.");
        }
    }

    @FXML
    public void handleEditProduct() {
        Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            showAlert("Veuillez sélectionner un produit à modifier.");
            return;
        }

        String newName = nameField.getText();
        String newPriceText = priceField.getText();

        if (newName.isEmpty() || newPriceText.isEmpty()) {
            showAlert("Veuillez remplir tous les champs pour la modification.");
            return;
        }

        try {
            double newPrice = Double.parseDouble(newPriceText);
            selectedProduct.setName(newName);
            selectedProduct.setPrice(newPrice);
            // Forcer la ListView à se rafraîchir (sinon l'affichage ne se met pas toujours à jour)
            productListView.refresh();

            nameField.clear();
            priceField.clear();
        } catch (NumberFormatException e) {
            showAlert("Le prix doit être un nombre.");
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur de saisie");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
