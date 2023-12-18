package com.example.moviesearch.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class SubmitFormRequest {
    public static enum Types {
        movie,
        series,
        episode
    }

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 3, max = 50)
    private String title;

    private Types type;

    @NotNull
    @PositiveOrZero(message = "Year cannot be negative")
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
