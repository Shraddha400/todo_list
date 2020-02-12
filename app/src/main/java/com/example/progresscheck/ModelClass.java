package com.example.progresscheck;

import android.widget.Button;
import android.widget.ImageButton;

public class ModelClass {
    private int imageResource;
    private String title;
    private String body;
    private ImageButton imagebutton;
    private Button btn;

    public ModelClass(int imageResource, String title, String body) {
        this.imageResource = imageResource;
        this.title = title;
        this.body = body;
        this.imagebutton = imagebutton;
        this.btn = btn;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public ImageButton getImagebutton() {
        return imagebutton;
    }

    public Button getBtn() {
        return btn;
    }


}