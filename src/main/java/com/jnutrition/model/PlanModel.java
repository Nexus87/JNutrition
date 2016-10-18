package com.jnutrition.model;

import com.jnutrition.repositories.PlanRepository;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.function.Function;

@Component
public class PlanModel implements InitializingBean{
    @Autowired
    private PlanRepository planRepository;

    PlanModel(Plan plan, PlanRepository planRepository){
        this.plan = plan;
        this.planRepository = planRepository;
    }
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
        kcal.set(sum(plan, PlanItemUtils::calculateKCal));
        carbs.set(sum(plan, PlanItemUtils::calculateCarbs));
        fat.set(sum(plan, PlanItemUtils::calculateFat));
        protein.set(sum(plan, PlanItemUtils::calculateProtein));

        ingredients.clear();
        ingredients.addAll(plan.getPlanItems());
        planRepository.savePlan(plan);
    }

    private BigDecimal sum(Plan plan, Function<PlanItem, BigDecimal> mapper) {
        return plan.getPlanItems().stream()
                .map(mapper)
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
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
        plan = planRepository.getPlanByName("Plan");
        updateData();
    }
}
