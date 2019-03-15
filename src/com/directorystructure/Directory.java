package com.directorystructure;
/**
 * Created by pratiksha.rahangdale on 8/1/2016.
 */
public class Directory {

    private int id;
    private int parentId;
    private String name;
    private String type;
    private int size;
    private String classification;
    private int checksum;

    public Directory(int id, int parentId, String name, String type, int size, String classification, int checksum) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.type = type;
        this.size = size;
        this.classification = classification;
        this.checksum = checksum;
    }

    public int getId() {
        return this.id;
    }

    public int getParentId() {
        return this.parentId;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getClassification() {
        return this.classification;
    }

    public String toString() {
        return "name = " + name + ", type = " + type + ", size = " + size + ", classification = " + classification + " , checksum = " + checksum + "";
    }
}