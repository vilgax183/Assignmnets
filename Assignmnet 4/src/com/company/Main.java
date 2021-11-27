package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	    System.out.println("Library Open");

    }
}
class generic<title,ISBN,BarCode>{
    private ArrayList<title> title_list;
    private ArrayList<ISBN> isbn_list;
    private ArrayList<BarCode> barCode_list;

    public void add_title(title t){
        title_list.add(t);
    }

    public void add_isbn(ISBN i){
        isbn_list.add(i);
    }

    public void add_barcode(BarCode b){
        barCode_list.add(b);
    }

    public ArrayList<title> getTitle_list() {
        return title_list;
    }

    public ArrayList<ISBN> getIsbn_list() {
        return isbn_list;
    }

    public ArrayList<BarCode> getBarCode_list() {
        return barCode_list;
    }
}
