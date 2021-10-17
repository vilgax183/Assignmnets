package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        while(true){
            System.out.println("""
                    Welcome to Backpack
                    1. Enter as instructor
                    2. Enter as student
                    3. Exit
                    """);
            int choice = Reader1.nextInt();
            if (choice == 1) {
                instructor();
            } else if (choice == 2) {
                student();
            } else if (choice == 3) {
                break;
            }
            else{
                System.out.println("please select a valid option");
            }
        }
    }

    public static void instructor(){
        System.out.println("INSTRUCTOR MENU");
        System.out.println("""
                1. Add class material
                2. Add assessments
                3. View lecture materials
                4. View assessments
                5. Grade assessments
                6. Close assessment
                7. View comments
                8. Add comments
                9. Logout""");
    }

    public static void student(){
        System.out.println("STUDENT MENU");
        System.out.println("""
                1. View lecture materials
                2. View assessments
                3. Submit assessment
                4. View grades
                5. View comments
                6. Add comments
                7. Logout """);
    }
}
class stuent{

}
class instructor{

}
class comment{

}
class assessment{

}
class material{

}
class Reader1 {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }


    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }
    static String nextLine() throws IOException {
        return reader.readLine();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}
