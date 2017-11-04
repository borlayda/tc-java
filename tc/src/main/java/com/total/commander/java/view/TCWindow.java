package com.total.commander.java.view;

import com.total.commander.java.controller.FileController;
import com.total.commander.java.model.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by badmin on 2017.08.28..
 */
public class TCWindow extends JFrame {

    private JPanel main;
    private JSplitPane listPane;
    private JSplitPane bottomPane;
    private JTextField commandLine;
    private FileController fileController;
    private File directory1;
    private File directory2;

    public TCWindow(String path1, String path2) {
        super("Total Commander");
        this.initWindow(path1, path2);
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openDirectory = new JMenuItem("Open directory");
        fileMenu.add(openDirectory);
        JMenu createMenu = new JMenu("Create");
        JMenuItem createFile = new JMenuItem("Create file");
        JMenuItem createDirectory = new JMenuItem("Create directory");
        createFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog((Component) e.getSource());
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        selectedFile.createNewFile();
                    } catch(IOException err) {
                        JOptionPane.showMessageDialog((TCWindow)e.getSource(),
                                "Can't create file!",
                                "File creation error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        createDirectory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog((Component) e.getSource());
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    selectedFile.mkdirs();
                }
            }
        });
        createMenu.add(createFile);
        createMenu.add(createDirectory);
        menuBar.add(fileMenu);
        menuBar.add(createMenu);
        this.setJMenuBar(menuBar);
    }

    private void initDirList(File directory) {
        ScrollPane scrollPane = new ScrollPane();
        ArrayList<Item> listItems = new ArrayList<>();
        String name = "..";
        Item.Type subfileType = Item.Type.Directory;
        String permissions = (directory.canRead() ? "r" : "-") + (directory.canWrite() ? "w" : "-") + (directory.canExecute() ? "x" : "-");
        String sha = "Unknown";
        long size = directory.length();
        String path = directory.getAbsolutePath();
        listItems.add(new Item(name, subfileType, permissions, sha, size, path));
        for (String child : directory.list()) {
            File subfile = new File(directory.getPath()+File.separator+child);
            name = subfile.getName();
            subfileType = subfile.isDirectory() ? Item.Type.Directory : Item.Type.File;
            permissions = (subfile.canRead() ? "r" : "-") + (subfile.canWrite() ? "w" : "-") + (subfile.canExecute() ? "x" : "-");
            sha = "Unknown";
            size = subfile.length();
            path = subfile.getAbsolutePath();
            listItems.add(new Item(name, subfileType, permissions, sha, size, path));
        }
        ElementList elementList = new ElementList(listItems);
        JList<Item> paneContentList = new JList<>(elementList);
        paneContentList.setCellRenderer(new ItemRenderer());
        paneContentList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    Item item = (Item)list.getSelectedValue();
                    ArrayList<Item> listItems = new ArrayList<>();
                    File subdir = new File(item.getPath());
                    File parent = subdir.getParentFile();
                    if (parent != null) {
                        String name = "..";
                        Item.Type subfileType = Item.Type.Directory;
                        String permissions = (parent.canRead() ? "r" : "-") + (parent.canWrite() ? "w" : "-") + (parent.canExecute() ? "x" : "-");
                        String sha = "Unknown";
                        long size = parent.length();
                        String path = parent.getAbsolutePath();
                        listItems.add(new Item(name, subfileType, permissions, sha, size, path));
                    }
                    if (subdir.isDirectory()) {
                        for (String child : subdir.list()) {
                            File subfile = new File(subdir.getPath() + File.separator + child);
                            String name = subfile.getName();
                            Item.Type subfileType = subfile.isDirectory() ? Item.Type.Directory : Item.Type.File;
                            String permissions = (subfile.canRead() ? "r" : "-") + (subfile.canWrite() ? "w" : "-") + (subfile.canExecute() ? "x" : "-");
                            String sha = "Unknown";
                            long size = subfile.length();
                            String path = subfile.getAbsolutePath();
                            listItems.add(new Item(name, subfileType, permissions, sha, size, path));
                        }
                        ElementList elementList = new ElementList(listItems);
                        list.setModel(elementList);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "This is not a directory! ("+subdir.getName()+")",
                                "Not a directory",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        scrollPane.add(paneContentList);
        this.listPane.add(scrollPane);
    }

    private void initWindow(String path1, String path2){
        this.directory1 = new File(path1);
        this.directory2 = new File(path2);
        this.initMenu();
        this.main = new JPanel();
        this.main.setLayout(new BorderLayout(0, 0));
        this.listPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        this.initDirList(directory1);
        this.initDirList(directory2);
        this.listPane.setDividerLocation(500);
        this.main.add(this.listPane, BorderLayout.CENTER);
        this.add(main);
    }

}
