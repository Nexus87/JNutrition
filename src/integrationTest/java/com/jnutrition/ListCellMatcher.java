package com.jnutrition;

import com.google.common.base.Predicate;
import javafx.scene.Node;
import javafx.scene.control.Cell;
import javafx.scene.control.Labeled;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.testfx.api.FxAssert;
import org.testfx.service.finder.NodeFinder;

public class ListCellMatcher extends BaseMatcher<Node> {
    private String searchString;

    public static Matcher<Node> cellContainsText(String text){
        return new ListCellMatcher(text);
    }

    private ListCellMatcher(String searchString){
        this.searchString = searchString;
    }
    @Override
    public boolean matches(Object item) {
        if(item == null || !(item instanceof Cell))
            return false;

        Cell c = (Cell) item;

        NodeFinder nodeFinder = FxAssert.assertContext().getNodeFinder();
        return nodeFinder.from(c.getGraphic())
                .lookup((Predicate<Node>) input -> input instanceof Labeled)
                .<Labeled>queryAll()
                .stream()
                .anyMatch(labeled -> labeled.getText().contains(searchString));
    }

    @Override
    public void describeTo(Description description) {

    }
}
