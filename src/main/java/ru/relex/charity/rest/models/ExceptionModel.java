package ru.relex.charity.rest.models;

public class ExceptionModel {
    private String error;

    public ExceptionModel(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
