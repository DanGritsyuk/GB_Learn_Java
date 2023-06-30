package Exercises.FinalProject;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Exercises.Exercise;
import Exercises.FinalProject.Views.CatalogView;
import Exercises.FinalProject.Views.FilterMenuView;

public class Exercise12 extends Exercise {
    public Exercise12(String description) {
        super(description);
    }

    @Override
    public boolean Solution() {
        Catalog _catalog = new Catalog();

        var filter = new Filter();
        var filtMenu = new FilterMenuView();
        Map<String, List<Notebook>> notebooksData;

        var openedCatalog = true;
        while (openedCatalog) {
            filter = filtMenu.CheckCriteries();
            if (filter.GetCriteries().size() > 0) {
                var filteredList = _catalog.SetNotebooks().GetNotebooks(filter);
                TreeMap<String, List<Notebook>> filteredMap = new TreeMap<>();
                filteredMap.put("Найдено " + filteredList.size() + " ноутбуков", filteredList);
                notebooksData = filteredMap;
            } else {
                notebooksData = _catalog.GetNotebooksData();
            }
            var catalogViewer = new CatalogView(notebooksData);
        }

        return false;
    }
}