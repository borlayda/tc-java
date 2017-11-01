package com.total.commander.java.view;

import com.total.commander.java.model.Item;

import javax.swing.*;
import java.awt.*;

/**
 * Created by badmin on 2017.11.01..
 */
public class ItemRenderer extends JPanel implements ListCellRenderer<Item> {

    public ItemRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Item> list, Item item, int index, boolean isSelected, boolean cellHasFocus) {

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        JPanel main = new JPanel();
        main.setBorder(BorderFactory.createMatteBorder(
                1, 5, 1, 1, Color.blue));
        JLabel fileType = new JLabel("Type: " + (item.getType() == Item.Type.Directory ? "D" : "F"));
        JLabel filePerm = new JLabel("Permissions: " +item.getPermissions());
        JLabel fileName = new JLabel("Filename: " + item.getName());
        JLabel fileSize = new JLabel("Size: " + new Long(item.getSize() /1024).toString() + " KB");
        JLabel filePath = new JLabel("FullPath: "+item.getPath());

        JPanel topPart = new JPanel();
        topPart.setLayout(new BorderLayout());
        topPart.add(fileType, BorderLayout.WEST);
        topPart.add(filePerm, BorderLayout.EAST);
        JPanel middlePart = new JPanel();
        middlePart.setLayout(new BorderLayout());
        middlePart.add(fileName, BorderLayout.WEST);
        middlePart.add(fileSize, BorderLayout.EAST);
        JPanel bottomPart = new JPanel();
        bottomPart.setLayout(new BorderLayout());
        bottomPart.add(filePath, BorderLayout.WEST);

        main.setLayout(new BorderLayout());
        main.add(topPart, BorderLayout.NORTH);
        main.add(middlePart, BorderLayout.CENTER);
        main.add(bottomPart, BorderLayout.SOUTH);
        return main;
    }
}
