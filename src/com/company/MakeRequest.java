package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Alexandr Shustov  on 16.02.2016.
 */

public class MakeRequest {
    public static final int MAX_STR = 140;
    public static void makeRequest() throws FileNotFoundException {
        Chats chats = new Chats();
        chats.readFile("log.txt");
        FileOutputStream log = new FileOutputStream("logfile.log");
        PrintStream logger = new PrintStream(log);
        try {
            Scanner in = new Scanner(System.in);

            System.out.println("1- search by author"
                    + "  2-search by keyword "
                    + "  3-regular expression search "
                    + "  4-search by period"
                    + "  5-delete a message"
                    + "  6-add message");

            System.out.println("enter the number of the request");
            int a = in.nextInt();

            switch (a) {
                case 1: {
                    try {
                        logger.println("\nRequest via author. Files are created.\r \n");
                        in = new Scanner(System.in);
                        System.out.println("Enter author");
                        String str = in.nextLine();
                        if(str.length() < MAX_STR) {
                            chats.searchAuthor(str);
                            chats.writeFile("1.json");
                        }
                    } catch (NotFindChatException e) {
                        logger.println("Not Found");
                    }catch (FileNotFoundException  e) {
                        logger.println("Not Found");
                    } catch (IOException e) {
                        logger.println("Error in file");
                    }

                }
                break;
                case 2: {
                    try {
                        logger.println("\nRequest via keyword. Files are created.\r \n");
                        System.out.println("Enter keyword");
                        in = new Scanner(System.in);
                        String str = in.nextLine();
                        if(str.length() < MAX_STR) {
                            chats.searchWord(str);
                            chats.writeFile("2.json");
                        }
                    } catch (NotFindChatException e) {
                        logger.println("Not Found");
                    }catch (FileNotFoundException  e) {
                        logger.println("Not Found");
                    } catch (IOException e) {
                        logger.println("Error in file");
                    }
                    break;
                }

                case 3: {
                    try {
                        logger.println("\nRequest via regular search. Files are created.\r \n");
                        in = new Scanner(System.in);
                        System.out.println("Enter regular expression");
                        String str = in.nextLine();
                        if(str.length() < MAX_STR) {
                            chats.findRegular(str);
                            chats.writeFile("3.json");
                        }
                    } catch (NotFindChatException e) {
                        logger.println("Not Found");
                    }catch (FileNotFoundException  e) {
                        logger.println("Not Found");
                    } catch (IOException e) {
                        logger.println("Error in file");
                    }
                    break;
                }
                case 4: {
                    in = new Scanner(System.in);

                    try {
                        logger.println("\nRequest via period. Files are created.\r \n");
                        System.out.println("Enter period");

                        long count1 = in.nextLong();
                        long count2 = in.nextLong();
                        chats.findTimeMessage(count1, count2);
                        chats.writeFile("4.json");
                    } catch (NotFindChatException e) {
                        logger.println("Not Found");
                    }
                    catch (FileNotFoundException  e) {
                        logger.println("Not Found");
                    }catch (IOException e) {
                        logger.println("Error in file");
                    }

                    break;

                }
                case 5:{
                    in = new Scanner(System.in);
                    try {
                        logger.println("\nRequest via deleting message. Files are created.\r \n");
                        System.out.println("Enter id");


                        chats.deleteMessage(in.nextLine());
                        chats.writeFile("5.json");
                    } catch (FileNotFoundException  e) {
                        logger.println("Not Found");
                    } catch (IOException e) {
                        logger.println("Error in file");
                    }
                    break;

                }
                case 6:{
                    in = new Scanner(System.in);
                    try {
                        logger.println("\nRequest via adding message. Files are created.\r \n");
                        System.out.println("Enter id , author , message , timestamp");
                        Chat ch = new Chat(in.nextLine() , in.nextLine() , in.nextLine() , in.nextLong());

                        chats.addMessage(ch);
                        chats.writeFile("6.json");
                    } catch (FileNotFoundException  e) {
                        logger.println("Not Found");
                    } catch (IOException e) {
                        logger.println("Error in file");
                    }
                    break;

                }
                default:
                    logger.println("\nError.\r \n");
                    break;
            }
        }catch(Exception e){
            logger.println("Error");
        }
    }
}

