package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class Main  {
    static ArrayList<Hospital> Hospital_list =new ArrayList<>();
    static ArrayList<vaccine> Vaccine_name_list= new ArrayList<>();
    static ArrayList<Citizen> Citizen_list=new ArrayList<>();
    static ArrayList<slot>  Slot_list=new ArrayList<>();
    public static void main(String[] args) throws IOException, InterruptedException {
	System.out.println("CoWin Portal initialized");
	Reader1.init(System.in);
	int i=0;
	while(true){
        TimeUnit.SECONDS.sleep(1);
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
            System.out.println("Vaccine Name: ");
            String name=Reader1.next();
            int check=check_vaccine(name.toLowerCase());
            if(check==0) {
                System.out.println("Number of Doses: ");
                int doses = Reader1.nextInt();
                System.out.println("Gap between Doses: ");
                int gap = Reader1.nextInt();
                ADD_VACCINE(name.toLowerCase(), doses, gap);
            }
            else{
                System.out.println("vaccine already exist");
            }
        }
        if(entry==2){
            System.out.println("Hospital Name: ");
            String Hospital_name=Reader1.next();
            int check=check_hospital(Hospital_name.toLowerCase());
            if(check==0){
                System.out.println("PinCode: ");
                int pincode=Reader1.nextInt();
                ADD_HOSPITAL(Hospital_name,pincode);
            }
            else{
                System.out.println("Hospital already exists");
            }

        }
        if(entry==3){
            System.out.println("Citizen Name: ");
            String citizen_name=Reader1.next();
            System.out.println("Age: ");
            int citizen_age=Reader1.nextInt();
            System.out.println("Unique ID: ");
            long citizen_unique_ID= (long) Reader1.nextDouble();
            int check=CITIZEN_ID_CHECK(citizen_unique_ID);
            if(check==0){
                ADD_CITIZEN(citizen_name,citizen_age,citizen_unique_ID);
            }
            else{
                System.out.println("Citizen already registered");
            }
            if(citizen_age<18){
                System.out.println("Only above 18 are allowed");

            }
        }
        if(entry==4){
            System.out.println("Enter hospital ID: ");
            int Hospital_ID=Reader1.nextInt();
            int check=Hospital_by_ID(Hospital_ID);
            if(check==1) {
                System.out.println("Enter the slots to be added: ");
                int slots = Reader1.nextInt();
                for(int j=0;j<slots;j++) {
                    System.out.println("Enter day number");
                    int day = Reader1.nextInt();
                    System.out.println("Enter Quantity");
                    int quantity = Reader1.nextInt();
                    for(int k=0;k<Vaccine_name_list.size();k++){
                        System.out.println(k+"."+Vaccine_name_list.get(k).return_name());
                    }
                    int selection=Reader1.nextInt();
                    String vaccine=Vaccine_name_list.get(selection).return_name();
                    Add_Vaccine_slot(Hospital_ID,vaccine,slots,quantity,day);
                }
            }
            else{
                System.out.println("Hospital by this ID doesn't exist");
            }
        }
        if(entry==5) {
            System.out.print("Enter patient unique ID: ");
            long citID = Reader1.nextInt();
            if (CITIZEN_ID_CHECK(citID)==1) {
                int counter = slotoptions();
                if(counter == 1) {
                    searchByArea(citID);
                }
                if(counter == 2) {
                    searchByVaccine(citID);
                }
                else {
                    System.out.println("Exit");
                }
            }
        }
        if(entry==6){
            System.out.println("Enter Hospital ID: ");
            long id=Reader1.nextInt();
            int check=Hospital_by_ID(id);
            if(check==1){
                slots_by_hospital_id(id);
            }
        }
        if(entry==7){
           System.out.println("Enter Patient ID");
           long id=(long)Reader1.nextDouble();
           int check=CITIZEN_ID_CHECK(id);
           if(check==1){
                get_status_by_id(id);
           }
           else{
               System.out.println("Please recheck citizen id");
           }

        }
        if(entry>=8){
            break;
        }
        System.out.println("--------------------------------------------");
        i=i+1;
    }
    }
    public static int check_vaccine(String name){
        for(int i=0;i<Vaccine_name_list.size();i++){
            if(name.equals((Vaccine_name_list.get(i).return_name()))){
                return 1;
            }
        }
        return 0;
    }
    public static void ADD_VACCINE(String name,int doses,int gap){
        if(doses>=1){
            if(gap>=0){
                vaccine addition=new vaccine(name,doses,gap);
                Vaccine_name_list.add(addition);
                System.out.println("Vaccine Name: "+name+", Number of doses: "+doses+", Gap between doses "+gap);
            }
            else{
                System.out.println("gap cannot be less than 0");
            }
        }
        else{
            System.out.println("Enter at-least one dose");
        }
    }

    public static int check_hospital(String name){
        for(int i = 0; i< Hospital_list.size(); i++){
            if(name.equals((Hospital_list.get(i).Return_Hospital_name()))){
                return 1;
            }
        }
        return 0;
    }
    static int initial=100000;
    public static void ADD_HOSPITAL(String name,int pincode){
        if(String.valueOf(pincode).length()==6){
            Hospital add_hospital=new Hospital(name, pincode,initial);
            initial=initial+1;
            Hospital_list.add(add_hospital);
            System.out.println("Hospital Name: "+name+", PinCode: "+pincode+", Unique Id: "+ add_hospital.Return_Hospital_id());
        }
        else{
            System.out.println("Please recheck the PinCode");
        }
    }

    public static int CITIZEN_ID_CHECK(long ID){
        for(int i=0;i<Citizen_list.size();i++){
            if(ID==((Citizen_list.get(i).Return_unique_id()))){
                return 1;
            }
        }
        return 0;
    }
     public static void ADD_CITIZEN(String citizen_name,int Citizen_age, long Citizen_Id){
        if(String.valueOf(Citizen_Id).length()==12){
            Citizen add_citizen=new Citizen(citizen_name,Citizen_age,Citizen_Id);
            Citizen_list.add(add_citizen);
            System.out.println("Citzen Name: "+citizen_name+", Age: "+Citizen_age+", Unique ID: "+Citizen_Id);
        }
        else{
            System.out.println("Please recheck Unique ID");
        }
     }

     public static int Hospital_by_ID(long ID){
         for(int i = 0; i< Hospital_list.size(); i++){
             System.out.println(Hospital_list.get(i).Return_Hospital_id());
             if(ID==((Hospital_list.get(i).Return_Hospital_id()))){
                 return 1;
             }
         }
         return 0;
     }

     public static void Add_Vaccine_slot(long Hospital_id,String Vaccine,int slots, int quantity,int day){
        if(quantity>=0 && day>=0){
            slot slot_add=new slot(Hospital_id,quantity,day,Vaccine,slots);
            Slot_list.add(slot_add);
            System.out.println("Slot added by hospital "+Hospital_id+" for Day: "+day+", Available Quantity: "+quantity+" of "+Vaccine);

        }
     }

     public static int Check_Pincode(long pincode){
         for(int i = 0; i< Hospital_list.size(); i++){
             if(pincode== Hospital_list.get(i).Return_Pincode()){
                return 1;
             }
         }
         return 0;
     }

     public static boolean Hospital_search_by_pincode(long pincode,int duedate){
        String alpha=null;
        for(int i = 0; i< Hospital_list.size(); i++){
            if(pincode== Hospital_list.get(i).Return_Pincode()){
                if(duedate<=Slot_list.get(i).Return_Hospital_ID()) {
                    alpha=(Hospital_list.get(i).Return_Hospital_id() +" "+ Hospital_list.get(i).Return_Hospital_name());
                    System.out.println(alpha);
                }
            }
        }
        if(alpha!=null){
            return true;
        }
        return false;
     }
    public static boolean Hospital_search_by_name(String name,int duedate){
        String alpha=null;
        Long id;
        String hosname=null;
        for(int i = 0; i< Slot_list.size(); i++){
            if(name.equals(Slot_list.get(i).Return_Vaccine_name().toLowerCase(Locale.ROOT))) {
                if (duedate <= Slot_list.get(i).Return_day()) {
                    id=Slot_list.get(i).Return_Hospital_ID();
                    for(int j=0;j<Hospital_list.size();j++){
                        if(id==Hospital_list.get(j).Return_Hospital_id()){
                            hosname=Hospital_list.get(j).Return_Hospital_name();
                        }
                    }
                    alpha=(id+" "+hosname);
                    System.out.println(alpha);
                }
            }
        }
        if(alpha!=null){
            return true;
        }
        return false;
    }

     public static void slots_by_hospital_id(long id){
         for(int i=0;i<Slot_list.size();i++){
             if(id==Slot_list.get(i).Return_Hospital_ID()){
                 if(Slot_list.get(i).Return_quantity()<=0){
                     Slot_list.remove(Slot_list.get(i));
                 }
                 System.out.println("Day: " +Slot_list.get(i).Return_day()+" Vaccine: "+Slot_list.get(i).Return_Vaccine_name()+" Available quantity: "+ Slot_list.get(i).Return_quantity());
             }
         }
     }
     public static void get_status_by_id(long id){
        for(int i=0;i<Citizen_list.size();i++) {
            if (id == Citizen_list.get(i).Return_unique_id()) {
                if (Citizen_list.get(i).Return_stat().Return_status() == "REGISTERED") {
                    System.out.println(Citizen_list.get(i).Return_stat().Return_status());
                } else if(Citizen_list.get(i).Return_stat().Return_status() !="REGIATERED") {
                    System.out.println(Citizen_list.get(i).Return_stat().Return_status());
                    System.out.println("Vaccine Given: " + Citizen_list.get(i).Return_stat().Return_Vaccine_name());
                    System.out.println("Number of Doses given: " + Citizen_list.get(i).Return_stat().Return_doses());
                    System.out.println("Next dose due date: " + Citizen_list.get(i).Return_stat().Return_duedate());
                }
            }
        }
     }

}

class slot{
    long Hospital_ID;
    int quantity;
    int day;
    int slots;
    String Vaccine_name;
    slot(long Hospital_ID,int quantity,int day,String name,int slots){
        this.Hospital_ID=Hospital_ID;
        this.quantity=quantity;
        this.slots=slots;
        this.day=day;
        this.Vaccine_name=name;
    }
    int Return_slots(){
        return slots;
    }
    long Return_Hospital_ID(){
        return Hospital_ID;
    }
    int Return_quantity(){
        return quantity;
    }
    int Return_day(){
        return day;
    }
    String Return_Vaccine_name(){
        return Vaccine_name;
    }
    void addQuantity() {
        this.quantity--;
    }
}
class vaccine{
    int doses, gap;
    String name;

    vaccine(String name,int doses, int gap){
        this.name=name;
        this.doses=doses;
        this.gap=gap;
    }
    int return_gap(){
        return  gap;
    }
    int return_doses(){
        return doses;
    }
    String return_name(){
        return name;
    }
}
class Hospital{
    int pincode;
    String Hospital_name;
    long initiate;
    Hospital(String name,int pincode,int initial){
        this.Hospital_name=name;
        this.pincode=pincode;
        this.initiate=initial;
    }
    String Return_Hospital_name(){
        return Hospital_name;
    }
    long Return_Hospital_id(){
        return initiate;
    }
    long Return_Pincode(){
        return pincode;
    }
}
class Citizen{
    String citizen_name;
    int citizen_age;
    long citizen_id;
    status Stat;
    Citizen(String name,int age, long id){
        this.citizen_name=name;
        this.citizen_age=age;
        this.citizen_id=id;
        this.Stat=new status();
    }
    String Return_citizen_name(){
        return citizen_name;
    }
    int Return_citizen_age(){
        return citizen_age;
    }
    long Return_unique_id(){
        return citizen_id;
    }
    status Return_stat(){
        return Stat;
    }
}
class status{
    String Vaccine;
    int doses;
    String status;
    int timeleft;
    status(){
        this.Vaccine=null;
        this.doses=0;
        this.status="REGISTERED";
        this.timeleft=1;
    }
    String Return_Vaccine_name(){
        return Vaccine;
    }
    int Return_doses(){
        return doses;
    }
    int Return_duedate(){
        return timeleft;
    }
    String Return_status(){
        return status;
    }
    void change_status1() {
        this.status="PARTIALLY VACCINATED";
    }
    void change_dose() {
        this.doses=this.doses+1;
    }
    void change_status2() {
        this.status="FULLY VACCINATED";
    }
    void change_due_date(int due) {
        this.timeleft=++due;
    }
    void change_Vaccine_name(String vax) {
        this.Vaccine=vax;
    }
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
