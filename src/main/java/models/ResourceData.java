package models;

public class ResourceData {
    private int id;
    private int year;
    private String color;
    private String pantone_value;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPantone_value() {
        return pantone_value;
    }

    public void setPantone_value(String pantone_value) {
        this.pantone_value = pantone_value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}