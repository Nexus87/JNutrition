package com.jnutrition.model;

import com.jnutrition.DAO.PlanDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

@Component
public class PlanModel implements InitializingBean{
    @Autowired
    private PlanDAO planDAO;
    private final ObservableList<PlanItem> ingredients = FXCollections.observableArrayList();
    private final ObservableList<PlanItem> readOnly = FXCollections.unmodifiableObservableList(ingredients);
    private Plan plan;

    public SimpleObjectProperty<BigDecimal> kcalProperty() {
        return kcal;
    }

    public SimpleObjectProperty<BigDecimal> proteinProperty() {
        return protein;
    }

    public SimpleObjectProperty<BigDecimal> carbsProperty() {
        return carbs;
    }

    public SimpleObjectProperty<BigDecimal> fatProperty() {
        return fat;
    }

    private final SimpleObjectProperty<BigDecimal> kcal = new SimpleObjectProperty<>(new BigDecimal(0));
    private final SimpleObjectProperty<BigDecimal> protein = new SimpleObjectProperty<>(new BigDecimal(0));
    private final SimpleObjectProperty<BigDecimal> carbs = new SimpleObjectProperty<>(new BigDecimal(0));
    private final SimpleObjectProperty<BigDecimal> fat = new SimpleObjectProperty<>(new BigDecimal(0));

    public void addIngredient(double amount, Unit unit, Ingredient ingredient) {
        PlanItem item = new PlanItem(ingredient, amount, unit);
        plan.getPlanItems().add(item);
        updateData();
    }

    private void updateData(){
        BigDecimal currentKcal = BigDecimal.ZERO;
        BigDecimal currentProtein = BigDecimal.ZERO;
        BigDecimal currentCarbs = BigDecimal.ZERO;
        BigDecimal currentFat = BigDecimal.ZERO;

        for (PlanItem item :
                plan.getPlanItems()) {


            currentKcal = currentKcal.add(PlanItemUtils.calculateKCal(item)).setScale(2, RoundingMode.HALF_UP);
            currentProtein = currentProtein.add(PlanItemUtils.calculateProtein(item)).setScale(2, RoundingMode.HALF_UP);
            currentCarbs = currentCarbs.add(PlanItemUtils.calculateCarbs(item)).setScale(2, RoundingMode.HALF_UP);
            currentFat = currentFat.add(PlanItemUtils.calculateFat(item)).setScale(2, RoundingMode.HALF_UP);
        }

        kcal.set(currentKcal);
        protein.set(currentProtein);
        carbs.set(currentCarbs);
        fat.set(currentFat);
        ingredients.clear();
        ingredients.addAll(plan.getPlanItems());
        planDAO.updatePlan(plan);
    }



    public ObservableList<PlanItem> getReadOnlyList() {
        return readOnly;
    }

    BigDecimal getKcal() {
        return kcal.get();
    }

    BigDecimal getProtein() {
        return protein.get();
    }

    BigDecimal getCarbs() {
        return carbs.get();
    }

    BigDecimal getFat() {
        return fat.get();
    }

    public void removeItem(int index) {
        if(ingredients.size() <= index)
            return;

        PlanItem item;
        Iterator<PlanItem> iterator = plan.getPlanItems().iterator();
        for(int i = 0; i < index; i++)
            iterator.next();

        item = iterator.next();

        plan.getPlanItems().remove(item);
        updateData();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        plan = planDAO.loadPlan("Plan");
    }
}
