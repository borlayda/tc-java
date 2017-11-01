package com.total.commander.java.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by badmin on 2017.08.28..
 */
public class FileController {

    private File file = null;

    // ---------------------------------------------------------------------------------------------------------
    public FileController() {

    }

    public FileController(File file){
        this.file = file;
    }

    public FileController(String path){
        this.file = new File(path);
    }

    // ---------------------------------------------------------------------------------------------------------
    public void changeFile(File file) {
        this.file = file;
    }

    public void changeFile(String path) {
        this.changeFile(new File(path));
    }

    // ---------------------------------------------------------------------------------------------------------

    public boolean isDirectory() {
        return this.file.isDirectory();
    }

    public boolean isFile() {
        return this.file.isFile();
    }

    // ---------------------------------------------------------------------------------------------------------

    public List<String> list() {
        if (this.file.isDirectory()) {
            List<String> directoryContent = new ArrayList();
            directoryContent.add("..");
            directoryContent.addAll(Arrays.asList(this.file.list()));
            return directoryContent;
        }
        return new ArrayList<String>();
    }

    public List<String> list(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            List<String> directoryContent = new ArrayList();
            directoryContent.add("..");
            directoryContent.addAll(Arrays.asList(file.list()));
            return directoryContent;
        }
        return new ArrayList<String>();
    }

    public List<String> list(File file) {
        if (file.isDirectory()) {
            List<String> directoryContent = new ArrayList();
            directoryContent.add("..");
            directoryContent.addAll(Arrays.asList(file.list()));
            return directoryContent;
        }
        return new ArrayList<String>();
    }

    // ---------------------------------------------------------------------------------------------------------

    public File newDirectory(String path) {
        if (this.file.isFile()) {
            return null;
        }
        File newDir = new File(this.file.getAbsolutePath() + File.pathSeparator+ path);
        newDir.mkdirs();
        return newDir;
    }

    public File newDirectory(File file) {
        if (file.isFile()) {
            return null;
        }
        file.mkdirs();
        return file;
    }

    // ---------------------------------------------------------------------------------------------------------

}
