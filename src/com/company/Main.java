package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main  {

    public static void main(String[] args) throws IOException{
	System.out.println("CoWin Portal initialized");
	Reader1.init(System.in);
	int i=0;
	while(true){
	    System.out.println("---------------------------------");
	    System.out.println("""
                1. Add Vaccine
                2. Register Hospital
                3. Register citizen
                4. Add Slot for Vaccination
                5. Book Slot for Vaccination
                6. List all slots for a hospital
                7. Checkout Vaccination Status
                8. Exit""");
        System.out.println("---------------------------------\n");
        int entry=Reader1.nextInt();
        if(entry==1){

        }
        if(entry==2){

        }
        if(entry==3){

        }
        if(entry==4){

        }
        if(entry==5){

        }
        if(entry==6){

        }
        if(entry==7){

        }
        else{
            break;
        }
    }
	i=i+1;

    }
}
class slot{

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
