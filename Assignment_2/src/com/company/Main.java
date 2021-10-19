package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<instructor> Instructor_list=new ArrayList<>();
    static ArrayList<student> Student_list=new ArrayList<>();
    static ArrayList<assessment_interface> assessmentList=new ArrayList<>();
    static ArrayList<lecture> lectureMaterial=new ArrayList<>();
    static ArrayList<lecture> commentList=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Instructor_list.add(new instructor(" I0"));
        Instructor_list.add(new instructor(" I1"));
        Student_list.add(new student(" S0"));
        Student_list.add(new student(" S1"));
        Student_list.add(new student(" S2"));
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

    public static void instructor() throws IOException {
        System.out.println("Instructors: ");
        for(int i=0;i<Instructor_list.size();i++){
            System.out.println(i + "-"+ Instructor_list.get(i).return_instructor_id());
        }
        System.out.println("Select Id");
        int id_choice=Reader1.nextInt();
        String id=Instructor_list.get(id_choice).return_instructor_id();
        System.out.println("Welcome "+ id);
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

    public static void student() throws IOException{
        System.out.println("Students: ");
        for(int i=0;i<Student_list.size();i++){
            System.out.println(i+"-"+Student_list.get(i).return_student_id());
        }
        System.out.println("Select Id");
        int id_choice=Reader1.nextInt();
        String id=Student_list.get(id_choice).return_student_id();
        System.out.println("Welcome "+id);
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
class student{
    private String student_id;

    student(String Stu_id){
        this.student_id=Stu_id;
    }
    String return_student_id(){
        return student_id;
    }

}
class instructor{
    private String Instructor_id;

    instructor(String Ins_id){
        this.Instructor_id=Ins_id;
    }

    String return_instructor_id(){
        return this.Instructor_id;
    }

}
class quiz_class implements assessment_interface{
    private String question;
    private int Max_marks;
    private String Assign_type;
    private boolean checkstatus;
    public boolean submitStatus;
    private ArrayList<solution> solutionsList;

    quiz_class(String question){
        this.question=question;
        this.Assign_type="quiz";
        this.Max_marks=1;
        this.solutionsList=new ArrayList<solution>();
        this.submitStatus=false;
        this.checkstatus=false;
    }


    @Override
    public String returnQuestion() {
        return this.question;
    }

    @Override
    public boolean returnSubmitStatus() {
        return false;
    }

    @Override
    public boolean returncheckstatus() {
        return false;
    }

    @Override
    public void submit_status() {

    }

    @Override
    public void check_status() {

    }

    @Override
    public String returnResponse() {
        return null;
    }

    @Override
    public void Response() {

    }

    @Override
    public String returnType() {
        return "Assignmnet";
    }

    @Override
    public int returnMarks() {
        return this.Max_marks;
    }

    @Override
    public ArrayList<solution> returnSolutions() {
        return solutionsList;
    }
}

class assignment_class implements assessment_interface{
    private String question;
    private String type;
    private boolean submit_status;
    private boolean check_status;
    private String format;
    private int marks;
    private  ArrayList<solution> solution_list;

    assignment_class(String ques){
        this.check_status=false;
        this.submit_status=false;
        this.type="asignment";
        this.question=ques;
        this.marks=marks;
        this.solution_list=new ArrayList<solution>();
    }

    @Override
    public String returnQuestion() {
        return this.question;
    }

    @Override
    public boolean returnSubmitStatus() {
        return false;
    }

    @Override
    public boolean returncheckstatus() {
        return false;
    }

    @Override
    public void submit_status() {

    }

    @Override
    public void check_status() {

    }

    @Override
    public String returnResponse() {
        return null;
    }

    @Override
    public void Response() {

    }

    @Override
    public String returnType() {
        return "quiz";
    }

    @Override
    public int returnMarks() {
        return this.marks;
    }

    @Override
    public ArrayList<solution> returnSolutions(){
        return this.solution_list;
    }

}
class tws{
    private int number;
    private String topic;

    tws(String topic ,int number){
        this.number=number;
        this.topic=topic;
    }
}
class lectureSlides implements lecture{
    private String title;
    private ArrayList<tws> slidecontent=new ArrayList<>();
    private Date upload_date;
    private String uploader;

    lectureSlides(String title,ArrayList<tws> slidecontent,Date upload_date,String uploader)
    {
        this.slidecontent=slidecontent;
        this.title=title;
        this.uploader=uploader;
        this.upload_date=upload_date;
    }
    @Override
    public String title() {
        return this.title;
    }

    @Override
    public Date upload_date() {
        return this.upload_date;
    }

    @Override
    public String uploader() {
        return this.uploader;
    }

    public ArrayList<tws> return_content(){
        return slidecontent;
    }
}

class lectureVideos implements lecture{
    private String title;
    private Date upload_date;
    private String uploader;
    private String file_format;

    lectureVideos(String title,Date upload_date,String uploader,String file_format){
        this.title=title;
        this.upload_date=upload_date;
        this.uploader=uploader;
        this.file_format=file_format;
    }

    @Override
    public String title() {
        return this.title;
    }

    @Override
    public Date upload_date() {
        return this.upload_date;
    }

    @Override
    public String uploader() {
        return this.uploader;
    }

    public String return_file_format(){
        return file_format;
    }
}
class comment{

}
class solution{
    private int marks_scored;
    private student stu;
    private String Instructor_check;
    private boolean status;
    private assessment_interface asses;


    solution(assessment_interface asses,student stu){
        this.marks_scored=0;
        this.stu=stu;
        this.asses=asses;
        this.Instructor_check=" ";
        this.status=false;
    }

    public assessment_interface return_assessment(){
        return this.asses;
    }
    public int returnMarks(){
        return this.marks_scored;
    }
    public student return_student(){
        return this.stu;
    }
    public boolean return_status(){
        return this.status;
    }
    public String returnInstructor(){
        return this.Instructor_check;
    }
}
interface lecture{
    public String title();
    public Date upload_date();
    public String uploader();

}
interface assessment_interface{
    public String returnQuestion();
    public boolean returnSubmitStatus();
    public boolean returncheckstatus();
    public void submit_status();
    public void check_status();
    public String returnResponse();
    public void Response();
    public String returnType();
    public int returnMarks();
    public ArrayList<solution> returnSolutions();

}
interface user{

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
