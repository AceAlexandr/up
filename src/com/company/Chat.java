package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Alexandr Shustov on 16.02.2016.
 */
public class Chat {
    private  String id;
    private  String message;
    private  String author;
    private  long timestamp;
    //write seters
    public Chat(String id , String message , String author , long timestamp){
        this.id = new String(id);
        this.message  = new String (message);
        this.author = new String(author);
        this.timestamp = timestamp;
    }
    public Chat(){
        id = new String();
        message  = new String();
        author = new String();
    }
    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTimestamp(long timestamp)  {

        this.timestamp = timestamp;
    }

    public String getMessage(){
        return message;
    }
    public String getAuthor(){
        return author;
    }
    public long getTimestamp(){

        return timestamp;
    }



}
