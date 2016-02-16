package com.company;
import javax.json.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.reflect.Type;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
/**
 * Created by Alexandr Shustov on 16.02.2016.
 */
public class Chats {
    private ArrayList<Chat> list;
    private ArrayList<Chat> result=new ArrayList<>();

    public ArrayList<Chat> getList(){
        return list;
    }
    public ArrayList<Chat> addMessage(Chat temp){
        for(Chat item : list){
            result.add(item);
        }
        result.add(temp);
        return result;
    }
    public void sortChat(){
        Collections.sort(result , new TimeCompare());
    }
    public ArrayList<Chat> deleteMessage(String id)throws FileNotFoundException {
        FileOutputStream log = new FileOutputStream("logfile.log" , true);
        PrintStream logger = new PrintStream(log);
        int count  = 0;
        for(Chat item : list){
            result.add(item);
        }
        for(Chat item : result){
            if(item.getId().equalsIgnoreCase(id)){
                result.remove(item);
                count ++;
            }
            logger.println("count of deleted messages" + count);

        }
        return result;
    }
    public ArrayList<Chat> searchAuthor(String author) throws NotFindChatException, FileNotFoundException {
        result.clear();
        FileOutputStream log = new FileOutputStream("logfile.log" , true);
        PrintStream logger = new PrintStream(log);
        int count  = 0;
        for(Chat item : list){
            if(item.getAuthor().equalsIgnoreCase(author)){
                result.add(item);
                count++;

            }
        }
        if(!result.isEmpty()){
            sortChat();
            logger.println("count of alignments" + count);
            return result;
        }
        else{
            throw new NotFindChatException();
        }
    }
    public ArrayList<Chat> searchWord(String word) throws NotFindChatException , FileNotFoundException{
        result.clear();
        FileOutputStream log = new FileOutputStream("logfile.log" , true);
        PrintStream logger = new PrintStream(log);
        int count  = 0;
        for(Chat item : list){
            StringTokenizer st = new StringTokenizer(item.getMessage() , " , .?!");
            while(st.hasMoreTokens()){
                if(st.nextToken().equalsIgnoreCase(word)){
                    result.add(item);
                    count ++;
                }
            }

        }
        if(!result.isEmpty()){
            sortChat();
            logger.println("count of alignments" + count);
            return result;
        }
        else{
            throw new NotFindChatException();
        }

    }
    public ArrayList<Chat> findTimeMessage(long firstTime , long lastTime) throws NotFindChatException , FileNotFoundException {
        result.clear();
        FileOutputStream log = new FileOutputStream("logfile.log" , true);
        PrintStream logger = new PrintStream(log);
        int count  = 0;
        for(Chat item : list){
            if(item.getTimestamp() >= firstTime && item.getTimestamp()<= lastTime ){
                result.add(item);
                count ++;

            }
        }
        if(!result.isEmpty()){
            sortChat();
            logger.println("count of alignments" + count);
            return result;
        }
        else{
            throw new NotFindChatException();
        }
    }
    public void readFile(String fileName) throws FileNotFoundException { //+
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        Type collectionType = new TypeToken<ArrayList<Chat>>(){}.getType();
        Gson gson = new Gson();
        list = gson.fromJson(reader , collectionType);

    }
    public void writeFile(String fileName) throws IOException{

            FileOutputStream fos = new FileOutputStream(fileName);
            PrintStream ps = new PrintStream(fos);
            if(result.size() !=0){
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                ps.println(gson.toJson(result));
            }




    }
    public ArrayList<Chat> findRegular(String regular) throws NotFindChatException , FileNotFoundException{
         result.clear();
        Pattern pattern = Pattern.compile(regular);
        FileOutputStream log = new FileOutputStream("logfile.log" , true);
        PrintStream logger = new PrintStream(log);
        int count  = 0;
        for(Chat item : list){
            Matcher matcher = pattern.matcher(item.getMessage());
            if(matcher.find()){
                result.add(item);
                logger.println("count of alignments" + count);
            }
        }
        if(!result.isEmpty()){
            sortChat();

            return result;
        }
        else{
            throw new NotFindChatException();
        }
    }

}
