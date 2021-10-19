package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.locks.ReadWriteLock;

public class Main {
    static private ArrayList<instructor> Instructor_list = new ArrayList<>();
    static private ArrayList<student> Student_list = new ArrayList<>();
    static private ArrayList<assessment_interface> assessmentList = new ArrayList<>();
    static private ArrayList<lecture> lec_list = new ArrayList<>();
    static private ArrayList<lecture> commentList = new ArrayList<>();

    public static void startprofmenu() throws Exception {
        System.out.println("Instructors: ");
        for (int i = 0; i < Instructor_list.size(); i++) {
            System.out.println(i + "-" + Instructor_list.get(i).return_instructor_id());
        }
        System.out.println("Select Id");
        int id_choice = Reader1.nextInt();
        instructor prof = Instructor_list.get(id_choice);
        instructor(prof);
    }

    public static void instructor(instructor id_choice) throws Exception {
        System.out.println("Welcome " + id_choice.return_instructor_id());
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
        int choice = Reader1.nextInt();
        if (choice == 1) {
            System.out.println("""
                    1. Add lecture slides
                    2. Add lecture video""");
            int add_what = Reader1.nextInt();
            if (add_what == 1) {
                addslide(id_choice);
            }
            if (add_what == 2) {
                addvideo((id_choice));
            } else {
                System.out.println("wrong selection");
            }
        }
        if (choice == 2) {
            System.out.println("""
                    1. Add Assignmnet
                    2. Add quiz""");
            int add_what = Reader1.nextInt();
            if (add_what == 1) {
                addass(id_choice);
            }
            if (add_what == 2) {
                addqui(id_choice);
            } else {
                System.out.println("wrong selection");
            }
        }
        if (choice == 3) {
            view_lecture(id_choice);
        }
        if (choice == 4) {
            view_ass(id_choice);
        }
        if (choice == 5) {
            grade_ass(id_choice);
        }
        if(choice==6){
            close_ass(id_choice);
        }
    }

    public static void student() throws IOException {
        System.out.println("Students: ");
        for (int i = 0; i < Student_list.size(); i++) {
            System.out.println(i + "-" + Student_list.get(i).return_student_id());
        }
        System.out.println("Select Id");
        int id_choice = Reader1.nextInt();
        String id = Student_list.get(id_choice).return_student_id();
        System.out.println("Welcome " + id);
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

    public static void main(String[] args) throws Exception {
        Reader1.init(System.in);
        Instructor_list.add(new instructor(" I0"));
        Instructor_list.add(new instructor(" I1"));
        Student_list.add(new student(" S0"));
        Student_list.add(new student(" S1"));
        Student_list.add(new student(" S2"));
        while (true) {
            System.out.println("""
                    Welcome to Backpack
                    1. Enter as instructor
                    2. Enter as student
                    3. Exit
                    """);
            int choice = Integer.parseInt(Reader1.next());
            if (choice == 1) {
                startprofmenu();
            }
            if (choice == 2) {
                student();
            }
            if (choice == 3) {
                break;
            } else {
                System.out.println("please select a valid option");
            }
        }
    }

    public static void addslide(instructor id) throws Exception {
        System.out.println("Enter the topic of slides");
        String topic = Reader1.nextLine();
        System.out.println("Enter the number of slides");
        int number = Reader1.nextInt();
        System.out.println("Enter the content of slide");
        ArrayList<String> alpha = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            System.out.println("Content of slide " + (i + 1) + ": ");
            String content = Reader1.nextLine();
            alpha.add(content);
        }
        lec_list.add(new lectureSlides(id, topic, alpha));
        instructor(id);
    }

    public static void addvideo(instructor id) throws Exception {
        System.out.println("Enter the topic of video: ");
        String topic = Reader1.nextLine();
        System.out.println("Enter filename of video");
        String filename = Reader1.nextLine();
        System.out.println(filename.substring(filename.length() - 4));
        if ((filename.substring(filename.length() - 4).equals(".mp4"))) {
            lec_list.add(new lectureVideos(id, topic, filename));
        } else {
            System.out.println("enter correct video format");
            instructor(id);
        }
        instructor(id);
    }

    public static void view_lecture(instructor id) throws Exception {
        int i = 0;
        while (i < lec_list.size()) {
            lec_list.get(i).printit();
            i = i + 1;
        }
        instructor(id);
    }

    public static void addass(instructor id) throws Exception {
        System.out.println("Enter Problem Statement: ");
        String prob_stat = Reader1.nextLine();
        System.out.println("Enter maximum marks: ");
        int max_marks = Reader1.nextInt();
        assessmentList.add(new assignment_class(prob_stat, max_marks));
        instructor(id);
    }

    public static void addqui(instructor id) throws Exception {
        System.out.println("Enter quiz question: ");
        String ques_quiz = Reader1.nextLine();
        assessmentList.add(new quiz_class(ques_quiz));
        instructor(id);
    }

    public static void view_ass(instructor id) throws Exception {
        if (assessmentList.size() != 0) {
            for (int j = 0; j < assessmentList.size(); j++) {
                if (assessmentList.get(j).returnType() == "quiz") {
                    System.out.println("ID: " + j + "Question: " + assessmentList.get(j).returnQuestion());
                    System.out.println("----------------------");

                } else if (assessmentList.get(j).returnType() == "assignmnet") {
                    System.out.println("ID: " + j + "Assignment: " + assessmentList.get(j).returnQuestion() + " Max marks: " + assessmentList.get(j).returnMarks());
                    System.out.println("----------------------");

                }
            }
        } else {
            System.out.println("No pending assignment left");
        }
        instructor(id);
    }

    public static void grade_ass(instructor id) throws Exception {

        System.out.println("List of assessments:");
        for (int j = 0; j < assessmentList.size(); j++) {
            if (assessmentList.get(j).returnType() == "quiz") {
                System.out.println("ID: " + j + "Question: " + assessmentList.get(j).returnQuestion());
                System.out.println("-------------------------");

            } else if (assessmentList.get(j).returnType() == "assignmnet") {
                System.out.println("ID: " + j + "Assignment: " + assessmentList.get(j).returnQuestion() + " Max marks: " + assessmentList.get(j).returnMarks());
                System.out.println("-------------------------");

            }
        }
        grade_ass2(id);
        instructor(id);
    }

    public static void grade_ass2(instructor id) throws IOException {
        ArrayList<solution> submissions;
        ArrayList<student> sub_student = new ArrayList<>();
        System.out.println("Enter ID of assessment to view submissions: ");
        int ass_to_be = Reader1.nextInt();
        submissions = assessmentList.get(ass_to_be).returnStudentSolutions();
        System.out.println("Choose ID from these ungraded submissions");
        for (int i = 0; i < submissions.size(); i++) {
            System.out.println(i+". "+ submissions.get(i).return_student().return_student_id());
            sub_student.add(submissions.get(i).return_student());
        }
        int UngradedSub = Reader1.nextInt();
        System.out.print("Submission: " + sub_student.get(UngradedSub).return_sub(assessmentList.get(ass_to_be)));
        System.out.println("------------");

        System.out.println("Max Marks: " + assessmentList.get(ass_to_be).returnMarks());
        System.out.print("Max Scored: ");
        int marksScored = Reader1.nextInt();
        sub_student.get(UngradedSub).mark_sub(assessmentList.get(ass_to_be).returnStudentSolutions().get(UngradedSub),marksScored);

        assessmentList.get(ass_to_be).returnStudentSolutions().get(UngradedSub).done(marksScored, id.return_instructor_id());
    }

    public static void close_ass(instructor id) throws Exception {
        if (assessmentList.size() != 0) {
            for (int j = 0; j < assessmentList.size(); j++) {
                if (assessmentList.get(j).returnType() == "quiz") {
                    System.out.println("ID: " + j + "Question: " + assessmentList.get(j).returnQuestion());
                    System.out.println("----------------------");

                } else if (assessmentList.get(j).returnType() == "assignmnet") {
                    System.out.println("ID: " + j + "Assignment: " + assessmentList.get(j).returnQuestion() + " Max marks: " + assessmentList.get(j).returnMarks());
                    System.out.println("----------------------");

                }
            }
            System.out.println("Enter ID of assignmnet to be closed: ");
            int choice= Reader1.nextInt();
            assessmentList.remove(choice);
        } else {
            System.out.println("No assignment left");
        }
        instructor(id);
    }
}

class student{
    private String student_id;
    private assessment_interface stu_ass;
    private solution stu_sol;
    student(String Stu_id){
        this.student_id=Stu_id;
    }
    String return_student_id(){
        return student_id;
    }

    boolean check_stat(assessment_interface ass){
        this.stu_ass=ass;
        return this.stu_ass.returnSubmitStatus();
    }
    boolean sub_stat(assessment_interface ass){
        this.stu_ass=ass;
        return this.stu_ass.returncheckstatus();
    }

    String return_sub(assessment_interface ass){
        this.stu_ass=ass;
        return this.stu_ass.returnResponse();
    }

    int return_marks(solution stu_sub){
        this.stu_sol=stu_sub;
        return this.stu_sol.returnMarks();
    }

    void mark_sub(solution stu_sub,int marks){
        this.stu_sol=stu_sub;
        this.stu_sol.give_marks(marks);
    }

    String return_instructor(solution sub){
        this.stu_sol=sub;
        return this.stu_sol.returnInstructor();
    }

    void submit_status_fin(boolean stat,assessment_interface ass){
        this.stu_ass=ass;
        this.stu_ass.submit_status(stat);
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
    public void sub(solution soln) {
        this.solutionsList.add(soln);
    }

    @Override
    public boolean returnSubmitStatus() {
        return this.submitStatus;
    }

    @Override
    public boolean returncheckstatus() {
        return this.checkstatus;
    }

    @Override
    public void submit_status(boolean stat) {
        this.submitStatus=stat;
    }

    @Override
    public void check_status(boolean stat) {
        this.checkstatus=stat;
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
    public ArrayList<solution> returnStudentSolutions() {
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

    assignment_class(String ques,int marks){
        this.check_status=false;
        this.submit_status=false;
        this.type="assignment";
        this.question=ques;
        this.marks=marks;
        this.solution_list=new ArrayList<solution>();
    }

    @Override
    public String returnQuestion() {
        return this.question;
    }

    @Override
    public void sub(solution soln) {
        this.solution_list.add(soln);
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
    public void submit_status(boolean stat) {
        this.submit_status=stat;
    }

    @Override
    public void check_status(boolean stat) {
        this.check_status=stat;
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
    public ArrayList<solution> returnStudentSolutions() {
        return solution_list;
    }




}
class lectureSlides implements lecture{
    private instructor lecturer;
    private String title;
    private ArrayList<String> slidecontent=new ArrayList<>();
    private Date upload_date;


    lectureSlides(instructor lecturer,String title,ArrayList<String> slidecontent)
    {
        this.lecturer=lecturer;
        this.slidecontent=slidecontent;
        this.title=title;
        this.upload_date=new Date();
    }
    void information(){
        System.out.println ("Title: " + this.title + "\n");
        for(int i = 0; i < this.slidecontent.size(); i++) {
            System.out.println("Slide " + Integer.toString(i+1) + ": " + this.slidecontent.get(i));
        }
        System.out.println("Number of slides: " + Integer.toString(this.slidecontent.size()));
        System.out.println("Date of upload: " +this.upload_date);
        System.out.println("Uploaded by: " +this.lecturer.return_instructor_id()+"\n");

    }
    @Override
    public void printit(){
        this.information();
    }
}

class lectureVideos implements lecture{
    private String title;
    private instructor lecturer;
    private Date upload_date;
    private String file_format;

    lectureVideos(instructor lecturer,String title,String file_format){
        this.title=title;
        this.lecturer=lecturer;
        this.upload_date=new Date();
        this.file_format=file_format;
    }
    void showall() {

        System.out.println("Title of video: " + this.title );
        System.out.println("Video file: " + this.file_format );
        System.out.println("Date of upload: " +this.upload_date );
        System.out.println("Uploaded by: " +this.lecturer.return_instructor_id());

    }

    @Override
    public void printit() {
        this.showall();
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
    public void give_marks(int marks){
        this.marks_scored=marks;
    }

    void done(int mark,String check_ins){
        this.marks_scored=mark;
        this.Instructor_check=check_ins;
        this.return_assessment().check_status(true);
    }
}
interface lecture{
    public void printit();
}
interface assessment_interface{
    public String returnQuestion();
    public  void sub(solution soln);
    public boolean returnSubmitStatus();
    public boolean returncheckstatus();
    public void submit_status(boolean stat);
    public void check_status(boolean stat);
    public String returnResponse();
    public void Response();
    public String returnType();
    public int returnMarks();
    public ArrayList<solution> returnStudentSolutions();

}
interface user{
    public String comments();
    public void logout();
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