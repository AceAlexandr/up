package com.company;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            // TODO code application logic here


            MakeRequest.makeRequest();

        }catch(FileNotFoundException ex){
            System.out.println("lol");
        }
    }
}
