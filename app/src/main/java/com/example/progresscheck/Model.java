package com.example.progresscheck;

public class Model {
    private int id;
    private String title;
    private  String body;
    private String date;
    private byte[] image;
    public Model(int id,String title,String body, String date,byte[] image){
        this.id=id;
        this.title=title;
        this.body=body;
        this.date=date;
        this.image=image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}