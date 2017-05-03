package com.developer.paul.closableview;

/**
 * Created by Paul on 3/5/17.
 */

public class TestData {

    private String name;
    private String icon;

    public TestData(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
