package com.example.foodwastemanagementsystem.Controller;

import com.example.foodwastemanagementsystem.main.Item;
import com.example.foodwastemanagementsystem.main.MyItemListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ItemController {

    @FXML
    private AnchorPane root;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myItemListener.onClickListener(item);
    }

    private Item item;
    private MyItemListener myItemListener;

    public void setData(Item item, MyItemListener myItemListener) {
        this.item = item;
        this.myItemListener = myItemListener;
        nameLabel.setText(item.getName());
        priceLabel.setText("₱ " + item.getPrice());
        img.setImage(new Image(getClass().getResourceAsStream(item.getImgSrc())));
        root.setOnMouseClicked(e -> myItemListener.onClickListener(item));
    }
}