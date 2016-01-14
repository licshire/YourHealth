package com.asiainfo.uuom.yourhealth.bean;

/**
 * Created by uuom on 16-1-12.
 */
public class ClassBean {

    private int id;
    private String name;

    public ClassBean() {
    }

    public ClassBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
