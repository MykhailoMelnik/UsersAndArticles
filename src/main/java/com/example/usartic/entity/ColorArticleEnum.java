package com.example.usartic.entity;


public enum ColorArticleEnum {
    RED("red"), BLUE("blue"), GREEN("green"), BLACK("black");

    String color;

    ColorArticleEnum(String color) {
        this.color = color;
    }

    public String getColorValue() {
        return color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
