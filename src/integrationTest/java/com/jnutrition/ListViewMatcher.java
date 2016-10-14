package com.jnutrition;

import javafx.scene.Node;
import javafx.scene.control.Cell;
import javafx.scene.control.ListCell;
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

    private final String searchString;
    private static final String SELECTOR_LIST_CELL = ".list-cell";

    private ListViewMatcher(String searchString){
        this.searchString = searchString;
    }

    @Override
    public boolean matches(Object item) {
        if(item == null || !(item instanceof ListCell))
            return false;
        ListCell view = (ListCell) item;

        NodeFinder nodeFinder = FxAssert.assertContext().getNodeFinder();
        Set<Cell> cells = nodeFinder.from(view)
                .lookup(SELECTOR_LIST_CELL)
                .<Cell>queryAll()
                .stream()
                .filter(cell -> cell.getGraphic() != null)
                .collect(Collectors.toSet());

        Matcher<Node> matcher = ListCellMatcher.cellContainsText(searchString);
        for(Cell c : cells){
            boolean found = matcher.matches(c);
            if(found)
                return true;
        }

        return false;
    }

    @Override
    public void describeTo(Description description) {

    }
}
