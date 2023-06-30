package Exercises.FinalProject.Views;

import java.util.List;
import java.util.Map;

import Exercises.FinalProject.Notebook;
import Interface.MenuRender;

public class CatalogView {

    private Map<String, List<String>> _data;

    public CatalogView(Map<String, List<String>> data) {
        this._data = data;
    }

    public Notebook GetCheckedNotebook() {
        int MenuLineId = 1;
        var mr = new MenuRender(_data);

        var catalogBrowsing = true;
        while (catalogBrowsing) {
            MenuLineId = mr.StartRenderMenu(MenuLineId - 1);
        }

    }
}
