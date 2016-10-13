package com.jnutrition.backend;

import com.jnutrition.model.Ingredient;
import com.jnutrition.model.Unit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLUnitRepository implements UnitRepository{

    private final Map<String, ObservableList<Unit>> unitMap = new HashMap<>();

    @XmlRootElement(name = "Units")
    private static class Units{
        @XmlRootElement
        private static class Unit{
            @XmlElement(name = "Name")
            public String name;
            @XmlElement(name = "Gram")
            public double gram;
            @XmlElement(name = "Ingredient")
            public String ingredient;

            com.jnutrition.model.Unit toUnit(){
                return new com.jnutrition.model.Unit(name, gram);
            }
        }

        @XmlElement(name = "Unit")
        public List<Unit> Units;
    }

    public XMLUnitRepository(InputStream inputStream){
        Units l = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Units.class);
            Unmarshaller um = context.createUnmarshaller();
            l = (Units) um.unmarshal(inputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        sortUnits(l);
    }

    private void sortUnits(Units l) {
        l.Units.forEach(this::insertIntoMap);
    }

    private void insertIntoMap(Units.Unit unit) {
        if(!unitMap.containsKey(unit.ingredient))
            unitMap.put(unit.ingredient, FXCollections.observableArrayList(Unit.GRAM));

        unitMap.get(unit.ingredient).add(unit.toUnit());
    }

    @Override
    public ObservableList<Unit> getUnitForIngredient(Ingredient ingredient) {
        return getUnitForIngredient(ingredient.getName());
    }

    @Override
    public ObservableList<Unit> getUnitForIngredient(String ingredient) {
        if(unitMap.containsKey(ingredient))
            return unitMap.get(ingredient);
        return FXCollections.observableArrayList(Unit.GRAM);
    }
}
