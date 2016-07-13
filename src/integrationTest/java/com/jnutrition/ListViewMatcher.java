package com.jnutrition;


import com.google.common.base.Predicate;
import javafx.scene.Node;
import javafx.scene.control.Cell;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.testfx.api.FxAssert;
import org.testfx.service.finder.NodeFinder;

import java.util.Set;
import java.util.stream.Collectors;

class ListViewMatcher extends BaseMatcher<Node>{

    static Matcher<Node> hasCellThatContainsText(Object string){
        return new ListViewMatcher(string.toString());
    }

    private String searchString;
    private static final String SELECTOR_LIST_CELL = ".list-cell";

    private ListViewMatcher(String searchString){
        this.searchString = searchString;
    }

    @Override
    public boolean matches(Object item) {
        if(item == null || !(item instanceof ListView))
            return false;
        ListView view = (ListView) item;

        NodeFinder nodeFinder = FxAssert.assertContext().getNodeFinder();
        Set<Cell> cells = nodeFinder.from(view)
                .lookup(SELECTOR_LIST_CELL)
                .<Cell>queryAll()
                .stream()
                .filter(cell -> cell.getGraphic() != null)
                .collect(Collectors.toSet());


        for(Cell c : cells){
            boolean found = nodeFinder.from(c.getGraphic())
                    .lookup((Predicate<Node>) input -> input instanceof Labeled)
                    .<Labeled>queryAll()
                    .stream()
                    .anyMatch(labeled -> labeled.getText().contains(searchString));
            if(found)
                return true;
        }

        return false;
    }

    @Override
    public void describeTo(Description description) {

    }
}
