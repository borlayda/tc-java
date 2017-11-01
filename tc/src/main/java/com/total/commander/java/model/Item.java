package com.total.commander.java.model;

import java.util.HashMap;

/**
 * Created by badmin on 2017.11.01..
 */
public class Item {

    public enum Type {
        Directory, File
    }

    private String name;
    private Type type;
    private String permissions;
    private String sha;
    private long size;
    private String path;

    public Item(String name, Type type, String permissions, String sha, long size, String path){
        this.name = name;
        this.type = type;
        this.permissions = permissions;
        this.sha = sha;
        this.size = size;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return this.name + " " + (this.type == Type.Directory ? "D" : "F") + " " + this.permissions + " " +  this.sha + " " + new Long(this.size).toString();
    }
}
