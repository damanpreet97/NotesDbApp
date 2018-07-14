package com.example.delllatitude.lec14hwnotesusingpreferences.models;

public class NoteData {
    private String title, message, time;
    private boolean status;

//    public NoteData() {
//    }

    public NoteData(String title, String message, String time, boolean status) {
        this.title = title;
        this.message = message;
        this.time = time;
        this.status = status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public boolean isStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}
