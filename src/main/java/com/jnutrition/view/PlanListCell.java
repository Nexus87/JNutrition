package com.jnutrition.view;

import com.jnutrition.backend.PlanItem;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

class PlanListCell extends ListCell<PlanItem> {
    private final Label itemLabel = new Label();
    private final Label detailLabel = new Label();
    private final VBox layout = new VBox();

    PlanListCell(){
        super();
        detailLabel.setFont(Font.font(null, 8.0));
        detailLabel.setTextAlignment(TextAlignment.CENTER);
        itemLabel.setTextAlignment(TextAlignment.CENTER);
        layout.getChildren().addAll(itemLabel, detailLabel);
    }
    @Override
    protected void updateItem(PlanItem item, boolean empty) {
        super.updateItem(item, empty);
        if(empty) {
            setGraphic(null);
            return;
        }

        itemLabel.setText("" + item.getAmount() + item.getUnit() + " " + item.getName());
        detailLabel.setText(
                "KCal: " + item.getKcal() +
                " Protein: " + item.getProtein() +
                " Carbs: " + item.getCarbs() +
                " Fat: " + item.getFat()
        );

        setGraphic(layout);
    }
}