package com.example.moviesearch.model;

public class SubmitFormRequest {
    public static enum Types {
        movie,
        series,
        episode
    }

    private String title;
    private Types type;
    private int year;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public Types getType() {
        return type;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }
}
