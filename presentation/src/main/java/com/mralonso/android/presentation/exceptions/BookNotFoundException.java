package com.mralonso.android.presentation.exceptions;

public class BookNotFoundException extends Exception{

    private int name;

    public BookNotFoundException(int name) {
        this.name = name;
    }
}
