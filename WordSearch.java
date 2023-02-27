//Programmer: David Rukashaza-Hancock
//Assignment: Word Search
//Extra Credit: word cross 

import java.util.*;
import java.io.*;

public class WordSearch {

   private static int sizeOfWordSearch = 20;
        // word search 2 dimensional array
   private static String[][] wordSearch = new String[sizeOfWordSearch][sizeOfWordSearch];
        // word search with answers right
   private static String[][] wordSearchKey = new String[sizeOfWordSearch][sizeOfWordSearch];
        // RANDOM # GENERATOR FOR LETTERS Will Fill Empty spots
   private static Random random = new Random();
   private static Scanner input = new Scanner(System.in);
        // words to collect
   private static String currentWord = "";
   private static String firstWord = "";
   private static String secondWord = "";
   private static String thirdWord = "";
   private static String fourthWord = "";
   private static String fiveWord = "";

   private static int positionH = 0;
   private static int positionV = 0;
   private static int tempV = 0;
   private static int tempH = 0;
   private static ArrayList<String> words = new ArrayList<String>();
   private static boolean placement= true;
   
   // for displaying a smaller word search
   private static int minH = 100;
   private static int maxH = 0;
   private static int minV = 100;
   private static int maxV = 0;

   
   public static void main(String[] args) {
   
      menu();
   } // end of main

   public static void menu() {
      String menu="";
      while(!menu.equals("g")&&!menu.equals("p")&&!menu.equals("s")&&!menu.equals("q")){ 
         System.out.println("this program will allow you to make your own word search puzzle");
         System.out.println("please selct and option: ");
         System.out.println("generate a new word search(g)");
         System.out.println("print your word search(p)");
         System.out.println("show the solution to your word search(s)");
         System.out.println("quit program(q)");
         menu = input.next();
      } // end while
   
      if(menu.equals("g")){
      
         words.clear();
         wordSearch = new String[sizeOfWordSearch][sizeOfWordSearch];
         wordSearchKey = new String[sizeOfWordSearch][sizeOfWordSearch];
      
         for(int i = 0; i < 5; i++) {////-------------------------------------------------------------------------------------------------------------
            System.out.print("Please choose a word that is less than 10 letters \t");
            
            
            if(i == 0){ //1st word
               word1();
            }//end of word 1
            
            else if (i == 1){ //2nd word
               word2() ; 
            } // end of word 2
            
            else if (i == 2) { //3rd word
               word3();    
            }//end of word 3
            
            else if (i == 3){ //4th word
               word4();
            } // end of word 4
            
            else if (i == 4){ //5th word
               word5();     
            }
         
         
         // setting display range for wordsearch
            for(int x = 0; x < sizeOfWordSearch; x++) {
               for(int y = 0; y < sizeOfWordSearch; y++) {
                  if(wordSearch[x][y] != null) {
                     if( y > maxH) { maxH = y; }
                     if( y < minH) { minH = y; }
                     if( x > maxV) { maxV = x; }
                     if( x < minV) { minV = x; }
                  }
                  
                  
               }
            }
         
            
         }
         maxH = maxH + 1;
         maxV = maxV + 1;
         //minH = minH - 1;
         //minV = minV - 1;
            
         // for-loop to add random letters
         for(int i = minV; i < maxV; i++) {
            for(int j = minH; j < maxH; j++) {
               int number = random.nextInt(26);
               char letter = (char) (number + 97);
               String sLetter = "" + letter;
               if(wordSearch[i][j] == null) {
                  wordSearch[i][j] = sLetter;
                  wordSearchKey[i][j] = "|";
               }
            } // end of for-loop j
         } // end of for-loop i
      }
      
      else if(menu.equals("p")){
         System.out.println("WORD SEARCH:");
           // for loop to print wordsearch
         for(int i = minV; i < maxV; i++) {
            for(int j = minH; j < maxH; j ++) {
               System.out.print(" " + wordSearch[i][j] + " ");
            } // end of for-loop j
            System.out.println();
         } // end of for-loop i
      }
      
      else if(menu.equals("s")){
         System.out.println("WORD SEARCH ANSWER KEY:");
           // for loop to print wordsearch key
         for(int i = minV; i < maxV; i++) {
            for(int j = minH; j < maxH; j ++) {
               System.out.print(" " + wordSearchKey[i][j] + " ");
            } // end of for-loop j
            System.out.println();
         } // end of for-loop i
      }
      
      else if(menu.equals("q")){
         System.out.println("thank you for playing.");
         System.exit(0);
      }
      menu();
   } // end menu
   
   
   private static void word1(){
      firstWord = input.next();
      currentWord = firstWord;
      words.add(firstWord);
            
            
      positionV = sizeOfWordSearch/2;
      positionH = positionV - currentWord.length()/2;
             
      for(int j = 0; j < currentWord.length(); j++) {
         String character = "" + currentWord.charAt(j);
         wordSearch[positionV][positionH + j] = character.toLowerCase(); // adds word to wordSearch lower case
         wordSearchKey[positionV][positionH + j] = character.toUpperCase(); // adds the word all caps
      }//end of for
   } // end word1

   public static void word2(){
      secondWord = input.next();
      currentWord = secondWord;
      words.add(secondWord);
   
      placement = false;
      for(int x = 0; x < words.get(words.size() - 2).length(); x++) {
         for(int y = 0; y < currentWord.length(); y++) { // goes through second word
            if(words.get(words.size() - 2).charAt(x) == currentWord.charAt(y)) {
               if(placement == false) {
                  placement = true;
                  positionV = positionV - y;
                  positionH = positionH + x;
                  for(int j = 0; j < currentWord.length(); j++) { // places on left side
                     String character = "" + currentWord.charAt(j);
                     wordSearch[positionV + j][positionH] = character; // adds word to wordSearch lower case
                     wordSearchKey[positionV + j][positionH] = character.toUpperCase(); // adds the word all caps
                  } // end of for
               } // end of if false
            } // end of if matching letter
         } // end of for-loop y
      } // end of  for-loop x
   
      if(placement == false) { // default position if no letters match
      
         positionV =  positionV - 2;
         positionH =  positionH - 2;
         for(int j = 0; j < currentWord.length(); j++) { // places on left side
            String character = "" + currentWord.charAt(j);
            wordSearch[positionV + j][positionH] = character; // adds word to wordSearch lower case
            wordSearchKey[positionV + j][positionH] = character.toUpperCase(); // adds the word all caps
         }
      } // end of if false
   }

   public static void word3(){
      thirdWord = input.next();
      currentWord = thirdWord;
      words.add(thirdWord);   
      placement = false; 
      for(int x = 0; x < words.get(words.size() - 2).length(); x++) {
         for(int y = 0; y < currentWord.length(); y++) { // goes through second word
            if(words.get(words.size() - 2).charAt(x) == currentWord.charAt(y)) {
               if(placement == false) {
                  placement = true;
                  tempV = positionV + x;
                  tempH = positionH + y;
                  for( int i = 0; i < currentWord.length(); i++) {
                     String character = "" + currentWord.charAt(i);
                     
                     if(tempV + i < 0 || tempV + i > 19 || tempH - i < 0 || tempH - i > 19){
                        placement = false;
                     }
                     
                     
                     else { 
                        if(wordSearch[tempV][tempH - i] != null && !wordSearch[tempV][tempH - i].equals(character)) {
                           placement = false;
                        }//end of if
                     }//end of else
                                          
                  }
                  if(placement) {
                     positionV = positionV + x;
                     positionH = positionH + y;
                     for( int i = 0; i < currentWord.length(); i++) { // places on left side
                        String character = "" + currentWord.charAt(i);
                        wordSearch[positionV][positionH - i] = character; // adds word to wordSearch lower case
                        wordSearchKey[positionV][positionH - i] = character.toUpperCase(); // adds the word all caps
                     } // end of for 
                  }//end of if   
               } // end of if
            } // end of if
         } // end of y
      } // end of x   
      if(placement == false) { // default position if no match is found
         positionV = positionV + 4;
         positionH = positionV + 4;
         for( int i = 0; i < currentWord.length(); i++) { // places on left side
            String character = "" + currentWord.charAt(i);
            wordSearch[positionV][positionH - (currentWord.length() - 1) - i]  = character; // adds word to wordSearch lower case
            wordSearchKey[positionV][positionH - (currentWord.length() - 1) - i] = character.toUpperCase(); // adds the word all caps
         } // end for
      } // end if  
   } // end of word3

   public static void word4(){
      fourthWord = input.next();
      currentWord = fourthWord;
      words.add(fourthWord);
    
    
      
   
     
      placement = false;
      for(int x = 0; x < words.get(words.size() - 2).length(); x++) {
         for(int y = 0; y < currentWord.length(); y++) { // goes through second word
            if(words.get(words.size() - 2).charAt(x) == currentWord.charAt(y)) {
               if(placement == false) {
                  placement = true;
                  tempV = positionV + y;
                  tempH = positionH - x;
                     
                  for(int i = 0; i < currentWord.length(); i++) {
                     String character = "" + currentWord.charAt(i);
                     
                     
                     if(tempV + i < 0 || tempV + i > 19 || tempH - i < 0 || tempH - i > 19){
                        placement = false;
                     }
                     
                     
                     
                     else {
                        if(wordSearch[tempV - i][tempH] != null && !wordSearch[tempV - i][tempH].equals(character)) {
                           placement = false;
                        }//end of if
                     }//end of else
                  }
                     
                  if(placement == true) {
                     positionV = positionV + y;
                     positionH = positionH - x;
                     for(int i = 0; i < currentWord.length(); i++) { // places on left side
                        String character = "" + currentWord.charAt(i);
                        wordSearch[positionV - i][positionH] = character; // adds word to wordSearch lower case
                        wordSearchKey[positionV - i][positionH] = character.toUpperCase(); // adds the word all caps
                     }
                  }             
               } // end of if
            }
         } // end of y
      } // end of x 
   
      if(placement == false) {
         
         positionV = 14;
         positionH = 14;
         
         for(int i = 0; i < currentWord.length(); i++) { // places on left side
            String character = "" + currentWord.charAt(i);
            wordSearch[positionV - i][positionH] = character; // adds word to wordSearch lower case
            wordSearchKey[positionV - i][positionH] = character.toUpperCase(); // adds the word all caps
         }
      }
     
     
     
     
     
     
   } // end of word4
   
   public static void word5(){
      fiveWord = input.next();
      currentWord = fiveWord;
      words.add(fiveWord);
   
   
    /*for(int j = 0; j < currentWord.length(); j++) {
       String character = "" + currentWord.charAt(j);
       
       wordSearch[j][14 - j] = character.toLowerCase(); // adds word to wordSearch lower case
       wordSearchKey[j][14 - j] = character.toUpperCase(); // adds the word all caps
    
    }
   */
   
   
   
   
      placement = false;
      for(int x = 0; x < words.get(words.size() - 2).length(); x++) {
         for(int y = 0; y < currentWord.length(); y++) { // goes through second word
            if(words.get(words.size() - 2).charAt(x) == currentWord.charAt(y)) {
               if(placement == false) {
                  placement = true;
                  tempV = positionV + y;
                  tempH = positionH - x;
                     
                  for(int i = 0; i < currentWord.length(); i++) {
                     String character = "" + currentWord.charAt(i);
                     
                     if(tempV + i < 0 || tempV + i > 19 || tempH - i < 0 || tempH - i > 19){
                        placement = false;
                     }
                     
                     else {
                     
                        if(wordSearch[tempV + i][tempH - i] != null && !wordSearch[tempV + i][tempH - i].equals(character)) {
                        
                           placement = false;
                        }
                     }//end of else
                     
                     
                  }
                     
                  if(placement == true) {
                     positionV = 4;
                     positionH = 15;
                     for(int i = 0; i < currentWord.length(); i++) { // places on left side
                        String character = "" + currentWord.charAt(i);
                        wordSearch[positionV + i][positionH - i] = character; // adds word to wordSearch lower case
                        wordSearchKey[positionV + i][positionH - i] = character.toUpperCase(); // adds the word all caps
                     }
                  }             
               } // end of if
            }
         } // end of y
      } // end of x 
   
      if(placement == false) {
         positionV = 0;
         positionH = 19;
         while(wordSearch[positionV][positionH] == null) {
            positionV++;
            positionH--;
         }
         
         positionV -= currentWord.length() + 1;
         positionH += currentWord.length() + 1;
      
         for(int i = 0; i < currentWord.length(); i++) { // places on left side
            
            placement = false;
                     
         
            
            String character = "" + currentWord.charAt(i);
            wordSearch[positionV + i][positionH - i] = character; // adds word to wordSearch lower case
            wordSearchKey[positionV + i][positionH - i] = character.toUpperCase(); // adds the word all caps
            
         } // end of for
      } // end of if
   
   
   
   } // end of word5
} // end of class
