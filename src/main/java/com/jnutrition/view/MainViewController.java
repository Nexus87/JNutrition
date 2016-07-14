package com.jnutrition.view;

import com.jnutrition.backend.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;

public class MainViewController {
	@FXML
	private ListView<Ingredient> ingredientView;
    @FXML
    private TextField filterBox;
    @FXML
    private ListView<PlanItem> planList;
    @FXML
    private Label kcalLabel;
    @FXML
    private Label proteinLabel;
    @FXML
    private Label carbsLabel;
    @FXML
    private Label fatLabel;

	private final ObservableList<Ingredient> ingredientList = FXCollections.observableArrayList();
    private IngredientRepository repository;
    private final PlanModel model = new PlanModel();
    private UnitRepository unitRepository;

    public void initialize(){
        FilteredList<Ingredient> filteredList = new FilteredList<Ingredient>(ingredientList, ingredient -> true);

        ingredientView.setCellFactory(p -> new IngredientCell());

        kcalLabel.textProperty().bind(model.kcalProperty().asString());
        proteinLabel.textProperty().bind(model.proteinProperty().asString());
        carbsLabel.textProperty().bind(model.carbsProperty().asString());
        fatLabel.textProperty().bind(model.fatProperty().asString());

        planList.setCellFactory(param -> new PlanListCell());

        filterBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(ingredient -> {
                if(newValue == null || newValue.isEmpty())
                    return true;

                return ingredient.getName().equals(newValue);
            });
        });

        ingredientView.setItems(filteredList);
        planList.setItems(model.getReadOnlyList());
    }

	public void setupController(IngredientRepository repository, UnitRepository unitRepository){
        this.repository = repository;
        this.unitRepository = unitRepository;
		ingredientList.addAll(repository.getAllIngredients());

        //FIXME this handler should be bound the list cell instead of the list itself. Otherwise the handler will act even if no cell was clicked.
        ingredientView.setOnMouseClicked(e -> {
            if(e.getClickCount() != 2)
                return;

            listDoubleClickHandler();
        });
	}

    private void listDoubleClickHandler(){
        Ingredient i = ingredientView.getSelectionModel().getSelectedItem();
        if(i == null)
            return;
        Pair<Double, Unit> result = showUnitDialog(i).orElse(null);

        if(result == null)
            return;

        model.addIngredient(result.getKey(), result.getValue(), i);

    }

    private Optional<Pair<Double, Unit>> showUnitDialog(Ingredient ingredient) {
        Dialog<Pair<Double, Unit>> unitDialog = new Dialog<>();
        unitDialog.setTitle("Chose amount and unit");

        unitDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();

        TextField amountField = new TextField();
        ComboBox<Unit> unitField = new ComboBox<>();

        amountField.setId("amountField");
        unitField.setId("unitField");
        unitField.setItems(unitRepository.getUnitForIngredient(ingredient));

        unitField.setValue(Unit.GRAM);
        gridPane.add(new Label("Amount:"), 0, 0);
        gridPane.add(amountField, 1, 0);
        gridPane.add(unitField, 2, 0);

        unitDialog.getDialogPane().setContent(gridPane);

        unitDialog.setResultConverter( button ->{
            if(button == ButtonType.CANCEL)
                return null;
            return new Pair<>(Double.parseDouble(amountField.getText()), unitField.getValue());
        });

        return unitDialog.showAndWait();
    }
}
