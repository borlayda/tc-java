package com.total.commander.java.view;

import javax.swing.*;
import com.total.commander.java.model.Item;

import java.util.List;

/**
 * Created by badmin on 2017.11.01..
 */
public class ElementList extends AbstractListModel {

    private List<Item> items;

    public ElementList(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
        this.fireContentsChanged(items, 0, items.size()-1);
    }

    public void removeItem(Item item) {
        items.remove(item);
        this.fireContentsChanged(items, 0, items.size()-1);
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public Object getElementAt(int index) {
        return items.get(index);
    }
}
