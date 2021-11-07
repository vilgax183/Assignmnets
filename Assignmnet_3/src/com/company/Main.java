package com.company;

import javax.xml.parsers.SAXParser;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<matrix> matrix_list=new ArrayList<>();
    public static void main(String[] args) throws Exception {
        Reader1.init(System.in);
        menu();

    }
    public static void menu() throws Exception{
        while(true) {
            System.out.println("""
                    1. Enter a matrix
                    2. Create a matrix of certain type
                    3. Change the elements of matrix
                    4. Display matrix type of chosen matrix
                    5. Perform arithmetic operation
                    6. perform element wise operation
                    7. Transpose matrix
                    8. Inverse matrix
                    9. Compute mean
                    10. Compute determinant
                    11. Compute A+A^T
                    12. Compute eigen vectors and values
                    13. Solve linear equations
                    14. show all matrix""");
            System.out.println("Select an option");
            int selection = Reader1.nextInt();
            if (selection == 1) {
                add_matrix();
            }
            if(selection==2){

            }
            if(selection==15){

            }
            if (selection == 4) {
                show_mat_list();
                System.out.println("Enter choice");
                int choice = Reader1.nextInt();
                type(matrix_list.get(choice - 1));
            }
            if (selection == 5) {
                arithmeticOps();
            }
        }
    }
    public static void add_matrix() throws IOException {
        int row,column;
        System.out.println("Enter the number of rows and columns");
        row=Reader1.nextInt();
        column=Reader1.nextInt();
        float[][] mat=new float[row][column];
        System.out.println("Enter the elements of matrix");
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                mat[i][j]= (float) Reader1.nextDouble();
            }
        }
        boolean flag_singular=false;
        boolean flag_upper=true;
        boolean flag_ones=true;
        boolean flag_null=true;
        boolean flag_lower=true;
        boolean flag_diagonal=true;
        boolean flag_scalar=true;
        boolean flag_identity=true;
        boolean flag_symmetric=true;
        boolean flag_skewSymmetric=true;
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(mat[i][j]!=0){
                    flag_null=false;
                    break;
                }
            }
            if (!flag_null) {
                break;
            }
        }
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(mat[i][j]!=1){
                    flag_ones=false;
                    break;
                }
            }
            if(!flag_ones){
                break;
            }
        }
        if(row==column && flag_null==false && flag_ones==false){
            if(row==1 && column==1){
                singleton matrix=new singleton(mat);
                matrix_list.add(matrix);
            }
            if(determinant(mat,row,row)==0){
                singular matrix=new singular(row,column,mat);
                matrix_list.add(matrix);
                flag_singular=true;
            }
            else{
                for(int i=0;i<row;i++){
                    for(int j=0;j<column;j++){
                        if(i>j){
                            if(mat[i][j]!=0){
                                flag_upper=false;
                                break;
                            }
                        }
                    }
                    if(!flag_upper){
                        break;
                    }
                }
                for(int i=0;i<row;i++){
                    for(int j=0;j<column;j++){
                        if(i<j){
                            if(mat[i][j]!=0){
                                flag_lower=false;
                                break;
                            }
                        }
                    }
                    if(!flag_lower){
                        break;
                    }
                }
                for(int i=0;i<row;i++){
                    for(int j=0;j<column;j++){
                        if(i!=j){
                            if(mat[i][j]!=0){
                                flag_diagonal=false;
                                break;
                            }
                        }
                    }
                    if(!flag_diagonal){
                        break;
                    }
                }
                if(flag_diagonal==true){
                    for(int i=0;i<row-1;i++){
                        for(int j=0;j<column-1;j++){
                            if(i==j){
                                if(mat[i][j]!=mat[i+1][j+1]){
                                    flag_scalar=false;
                                    break;
                                }
                            }
                        }
                        if(!flag_scalar){
                            break;
                        }
                    }
                    if(flag_scalar==true) {
                        for (int i = 0; i < row; i++) {
                            for (int j = 0; j < column; j++) {
                                if (i == j) {
                                    if (mat[i][j] != 1) {
                                        flag_identity = false;
                                        break;
                                    }
                                }
                            }
                            if (!flag_identity) {
                                break;
                            }
                        }
                    }
                }
                float[][] temp=trans(mat);
                for(int i=0;i<row;i++){
                    for(int j=0;j<column;j++){
                        if(mat[i][j]!=temp[i][j]){
                            flag_symmetric=false;
                            break;
                        }
                    }
                    if(!flag_symmetric){
                        break;
                    }
                }
                for(int i=0;i<row;i++){
                    for(int j=0;j<column;j++){
                        if(mat[i][j]!=-temp[i][j]){
                            flag_skewSymmetric=false;
                            break;
                        }
                    }
                    if(!flag_skewSymmetric){
                        break;
                    }
                }
            }
        }
        else if(row!=column && flag_null==false &&flag_ones==false){
            flag_upper=false;
            flag_lower=false;
             flag_diagonal=false;
             flag_scalar=false;
             flag_identity=false;
            flag_symmetric=false;
             flag_skewSymmetric=false;
            if(row==1){
                column_matrix matrix=new column_matrix(column,mat);
                matrix_list.add(matrix);
            }
            if(column==1){
                row_matrix matrix=new row_matrix(row,mat);
                matrix_list.add(matrix);
            }
            else{
                rectangular_matrix matrix=new rectangular_matrix(row,column,mat);
                matrix_list.add(matrix);
            }
        }
        if(flag_null==true){
            null_matrix matrix=new null_matrix(row,column);
            matrix_list.add(matrix);
        }
        if(flag_ones==true){
            ones_matrix matrix=new ones_matrix(row,column);
            matrix_list.add(matrix);
        }
        else if(flag_singular==false) {
            if (flag_identity == true) {
                identity matrix = new identity(row);
                matrix_list.add(matrix);
            }
            if (flag_identity == false && flag_scalar == true) {
                scalar matrix = new scalar(row, mat[1][1]);
                matrix_list.add(matrix);
            }
            if ((flag_identity == false && flag_scalar == false && flag_diagonal == true)|| flag_upper==true && flag_lower==true) {
                ArrayList<Float> alpha = new ArrayList<>();
                for (int p = 0; p < row; p++) {
                    for (int q = 0; q < column; q++) {
                        if (p == q) {
                            alpha.add(mat[p][q]);
                        }
                    }
                }
                diagonal matrix = new diagonal(row, alpha);
                matrix_list.add(matrix);

            }
            if(flag_upper==true && flag_lower==false){
                upper_triangular matrix=new upper_triangular(row,column,mat);
                matrix_list.add(matrix);
            }
            if(flag_upper==false && flag_lower==true){
                lower_triangular matrix=new lower_triangular(row,column,mat);
                matrix_list.add(matrix);
            }
            if(flag_symmetric==true && flag_skewSymmetric==false){
                symmetric matrix=new symmetric(row,column,mat);
                matrix_list.add(matrix);
            }
            if(flag_symmetric==false && flag_skewSymmetric==true){
                skew_symmetric matrix=new skew_symmetric(row,column,mat);
                matrix_list.add(matrix);

            }
        }
        System.out.println(flag_singular);
        System.out.println(flag_upper);
        System.out.println(flag_ones);
        System.out.println(flag_null);
        System.out.println(flag_lower);
        System.out.println(flag_diagonal);
        System.out.println(flag_scalar);
        System.out.println(flag_identity);
        System.out.println(flag_symmetric);
        System.out.println(flag_skewSymmetric);
    }
    static void getCofactor(float mat[][], float temp[][],
                            int p, int q, int n)
    {
        int i = 0, j = 0;

        // Looping for each element
        // of the matrix
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // Copying into temporary matrix
                // only those element which are
                // not in given row and column
                if (row != p && col != q) {
                    temp[i][j++] = mat[row][col];
                    // Row is filled, so increase
                    // row index and reset col index
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    /* Recursive function for finding determinant
    of matrix. n is current dimension of mat[][]. */
    static float determinant(float mat[][], int n,int N)
    {
        int D = 0; // Initialize result

        // Base case : if matrix
        // contains single element
        if (n == 1)
            return mat[0][0];

        // To store cofactors
        float temp[][] = new float[N][N];

        // To store sign multiplier
        int sign = 1;

        // Iterate for each element of first row
        for (int f = 0; f < n; f++) {
            // Getting Cofactor of mat[0][f]
            getCofactor(mat, temp, 0, f, n);
            D += sign * mat[0][f]
                    * determinant(temp, n - 1,N);

            // terms are to be added
            // with alternate sign
            sign = -sign;
        }

        return D;
    }


    public static void arithmeticOps() throws IOException {
        System.out.println("What operation do you want to perform");
        System.out.println("""
                1. addition
                2. subtraction
                3. multiplication
                4.division""");
        int choice=Reader1.nextInt();
        if(choice==1){
           show_mat_list();
            float first_mat[][];
            float second_mat[][];
            int in1=Reader1.nextInt();
            int in2=Reader1.nextInt();
            if(matrix_list.get(in1).getRow()==matrix_list.get(in2).getRow() && matrix_list.get(in1).getColumn()==matrix_list.get(in2).getColumn()){
                first_mat=matrix_list.get(in1).getMat();
                second_mat=matrix_list.get(in2).getMat();
                int rows=first_mat.length;
                int columns=first_mat[0].length;
                float c[][] = new float[rows][columns];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        c[i][j] = first_mat[i][j] + second_mat[i][j];
                    }
                }
                System.out.println("The sum of the two matrices is");
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        System.out.print(c[i][j] + " ");
                    }
                    System.out.println(" ");
                }
            }
            else{
                System.out.println("Cannot be added");
                return;
            }
        }
        if(choice==2){
            float first_mat[][];
            float second_mat[][];
            int in1=Reader1.nextInt();
            int in2=Reader1.nextInt();
            if(matrix_list.get(in1).getRow()==matrix_list.get(in2).getRow() && matrix_list.get(in1).getColumn()==matrix_list.get(in2).getColumn()){
                first_mat=matrix_list.get(in1).getMat();
                second_mat=matrix_list.get(in2).getMat();
                int rows=first_mat.length;
                int columns=first_mat[0].length;
                float c[][] = new float[rows][columns];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        c[i][j] = first_mat[i][j] - second_mat[i][j];
                    }
                }
                System.out.println("The difference of the two matrices is");
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        System.out.print(c[i][j] + " ");
                    }
                    System.out.println(" ");
                }
            }
            else{
                System.out.println("Cannot be Subtracted");
                return;
            }
        }
        if(choice==3){
            float first_mat[][];
            float second_mat[][];
            int in1=Reader1.nextInt();
            int in2=Reader1.nextInt();
            if(matrix_list.get(in1).getColumn()==matrix_list.get(in2).getRow()) {
                first_mat=matrix_list.get(in1).getMat();
                second_mat=matrix_list.get(in2).getMat();
                int rows=matrix_list.get(in1).getRow();
                int col=matrix_list.get(in2).getColumn();
                int columns=matrix_list.get(in1).getColumn();
                float[][] temp=new float[rows][col];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < col; j++) {
                        for (int k = 0; k < columns; k++)
                            temp[i][j] += first_mat[i][k] * second_mat[k][j];
                    }
                }


                System.out.println("\nResultant Matrix:");
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < col; j++)
                        System.out.print(temp[i][j] + " ");
                    System.out.println();
                }
            }
            else{
                System.out.println("Cannot be multiplied");
                return;
            }
        }
        if (choice == 4) {
//            float first_mat[][];
//            float second_mat[][];
//            int in1 = Reader1.nextInt();
//            int in2 = Reader1.nextInt();
//            if (matrix_list.get(in2).getType().equals("Singular Matrix")) {
//                System.out.println("Singular matrix provided");
//                return;
//            }
//            if (matrix_list.get(in1).getColumn() == matrix_list.get(in2).getRow() && matrix_list.get(in2).getRow() == matrix_list.get(in2).getColumn()) {
//                first_mat=matrix_list.get(in1).getMat();
//                second_mat=matrix_list.get(in2).getMat();
//                int rows=matrix_list.get(in1).getRow();
//                int col=matrix_list.get(in2).getColumn();
//                float[][] temp1 = new float[rows][col];
//                temp1 = inverse(temp, columns);
//                int mat3[][] = new int[rows][columns];
//
//
//                for (int i = 0; i < rows; i++) {
//                    for (int j = 0; j < columns; j++) {
//                        for (int k = 0; k < columns; k++)
//                            mat3[i][j] += mat[i][k] * temp1[k][j];
//                    }
//                }
//
//
//                System.out.println("\nResultant Matrix:");
//                for (int i = 0; i < rows; i++) {
//                    for (int j = 0; j < columns; j++)
//                        System.out.print(mat3[i][j] + " ");
//                    System.out.println();
//                }
//
//            }
//
      }
    }
    static float[][] trans(float[][] mat)
    {
        int row, col;
        float[][] trans = new float[3][3];
        for(row=0;row<3;row++) {
            for (col = 0; col < 3; col++) {
                trans[row][col] = mat[col][row];
            }
        }
        return trans;
    }
    public static String type(matrix mat){
        return "a";
    }

    public static void show_mat_list(){
        for(int p=0;p<matrix_list.size();p++) {
            System.out.print(p+1+". \n");
            for (int i = 0; i < matrix_list.get(p).getRow(); i++) {
                for (int j = 0; j < matrix_list.get(p).getColumn(); j++) {
                    System.out.print(matrix_list.get(p).getMat()[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

}
abstract class matrix {
    protected int row, column;
    matrix(int row,int column){
        this.row=row;
        this.column=column;
    }

    abstract public int getRow() ;

    abstract public int getColumn();

    abstract public void cofactor(float[][] mat,float[][] temp,int p,int q,int n);

    public abstract float getdet(float[][] mat, int n, int N);

    abstract public float[][] transpose();

    abstract public float rowMean();

    abstract public float columnMean();

    abstract public float mean();

    abstract public float mea();

    abstract public float[][] getMat();

    String getType() {
        return null;
    }
}

class rectangular_matrix extends matrix{
    protected float[][] mat;
    rectangular_matrix( int row, int column,float[][] mat) {
        super( row, column);
        this.mat=mat;
    }

    @Override
    public int getRow() {
        return row;
    }


    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public void cofactor(float[][] mat, float[][] temp, int p, int q, int n) {

    }

    @Override
    public float getdet(float[][] mat,int n,int N) {
        System.out.println("Determinant not possible");
        return 0;
    }


    @Override
    public float[][] transpose() {
        return null;
    }

    @Override
    public float rowMean() {
        return 0;
    }

    @Override
    public float columnMean() {
        return 0;
    }

    @Override
    public float mean() {
        return 0;
    }

    @Override
    public float mea() {
        return 0;
    }


    @Override
    public float[][] getMat() {
        return mat;
    }

    @Override
    String getType() {
        return ("Rectangular Matrix");
    }
}
class row_matrix extends rectangular_matrix {

    row_matrix( int column,float[][] mat) {
        super(1,column,mat);
    }
    String getType(){
        return "Row Matrix";
    }
}
class column_matrix extends rectangular_matrix {

    column_matrix(int row,float[][] mat) {
        super(row, 1,mat);
    }
    String getType(){
        return "Column Matrix";
    }
}
class square_matrix extends  matrix{
    protected float[][] mat;
    square_matrix(int row, int column,float[][] mat) {
        super(row, column);
        this.mat=mat;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public void cofactor(float[][] mat, float[][] temp, int p, int q, int n) {
        int i = 0, j = 0;

        // Looping for each element
        // of the matrix
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // Copying into temporary matrix
                // only those element which are
                // not in given row and column
                if (row != p && col != q) {
                    temp[i][j++] = mat[row][col];
                    // Row is filled, so increase
                    // row index and reset col index
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    @Override
    public float getdet(float[][] mat, int n, int N) {
        int D = 0; // Initialize result

        // Base case : if matrix
        // contains single element
        if (n == 1)
            return mat[0][0];

        // To store cofactors
        float temp[][] = new float[N][N];

        // To store sign multiplier
        int sign = 1;

        // Iterate for each element of first row
        for (int f = 0; f < n; f++) {
            // Getting Cofactor of mat[0][f]
            this.cofactor(mat, temp, 0, f, n);
            D += sign * mat[0][f]
                    * getdet(temp, n - 1,N);

            // terms are to be added
            // with alternate sign
            sign = -sign;
        }

        return D;
    }

    @Override
    public float[][] transpose() {
        return new float[0][];
    }

    @Override
    public float rowMean() {
        return 0;
    }

    @Override
    public float columnMean() {
        return 0;
    }

    @Override
    public float mean() {
        return 0;
    }

    @Override
    public float mea() {
        return 0;
    }


    @Override
    public float[][] getMat() {
        return mat;
    }

    @Override
    String getType() {
        return "Square Matrix";
    }
}
class upper_triangular extends square_matrix{
    upper_triangular( int row, int column,float[][] mat) {
        super( row, column,mat);
    }

    @Override
    String getType() {
        return "Upper Triangular Matrix";
    }

    @Override
    public float getdet(float[][] mat, int n, int N) {
        float det=1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j) {
                    det = det * mat[i][j];
                }
            }
        }
        return det;
    }
}
class lower_triangular extends square_matrix{

    lower_triangular( int row, int column,float[][] mat) {
        super( row, column,mat);
    }
    @Override
    String getType(){
        return "Lower Triangular Matrix";
    }

    @Override
    public float getdet(float[][] mat, int n, int N) {
        float det=1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j) {
                    det = det * mat[i][j];
                }
            }
        }
        return det;
    }
}
class singular extends square_matrix{

    singular( int row, int column,float[][] mat) {
        super( row, column,mat);
    }
    String getType(){
        return "Singular Matrix";
    }

    @Override
    public float getdet(float[][] mat, int n, int N) {
        return 0;
    }
}
class singleton extends square_matrix{

    singleton( float[][] mat) {
        super(1,1, mat);
    }
    String getType(){
        return "Singleton Matrix";
    }

    @Override
    public float getdet(float[][] mat, int n, int N) {
        return mat[0][0];
    }
}
class diagonal extends square_matrix{
    private ArrayList<Float> alpha=new ArrayList<>();
    diagonal( int row,ArrayList<Float> alpha) {
        super( row, row,null);
        this.alpha=alpha;
    }
    String getType(){
        return "Diagonal Matrix";
    }

    @Override
    public float getdet(float[][] mat, int n, int N) {
        float det=1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j) {
                    det = det * mat[i][j];
                }
            }
        }
        return det;
    }
}
class scalar extends diagonal{
    private float scal;
    scalar(int row,float scal) {
        super( row,null);
        this.scal=scal;
    }
    String getType(){
        return "Scalar Matrix";
    }

    @Override
    public float getdet(float[][] mat, int n, int N) {
        float det=1;
        for(int i=0;i<n;i++){
            det=det*mat[0][0];
        }
        return det;
    }
}
class identity extends scalar{

    identity( int row) {
        super(row,1);
    }

    String getType(){
        return "Identity matrix";
    }

    @Override
    public float getdet(float[][] mat, int n, int N) {
        return 1;
    }
}
class symmetric extends square_matrix{

    symmetric( int row, int column,float[][] mat) {
        super( row, column,mat);
    }
    String getType(){
        return "Symmetric Matrix";
    }
}
class skew_symmetric extends square_matrix{

    skew_symmetric( int row, int column,float[][] mat) {
        super( row, column,mat);
    }
    String getType(){
        return "Skew Symmetric Matrix";
    }
}
class ones_matrix extends matrix{
    ones_matrix( int row,int column) {
        super( row, column);

    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public void cofactor(float[][] mat, float[][] temp, int p, int q, int n) {

    }



    @Override
    public float getdet(float[][] mat, int n, int N) {
        return 0;
    }

    @Override
    public float[][] transpose() {
        return new float[0][];
    }


    @Override
    public float rowMean() {
        return 0;
    }

    @Override
    public float columnMean() {
        return 0;
    }

    @Override
    public float mean() {
        return 0;
    }

    @Override
    public float mea() {
        return 0;
    }


    @Override
    public float[][] getMat() {
        return null;
    }

    @Override
    String getType() {
        return "Ones Matrix";
    }
}
class null_matrix extends matrix{
    null_matrix( int row,int column) {
        super(row,column);
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public void cofactor(float[][] mat, float[][] temp, int p, int q, int n) {

    }

    @Override
    public float getdet(float[][] mat, int n, int N) {
        return 0;
    }

    @Override
    public float[][] transpose() {
        return new float[0][];
    }

    @Override
    public float rowMean() {
        return 0;
    }

    @Override
    public float columnMean() {
        return 0;
    }

    @Override
    public float mean() {
        return 0;
    }

    @Override
    public float mea() {
        return 0;
    }


    @Override
    public float[][] getMat() {
        return null;
    }

    @Override
    String getType() {
        return "Null Matrix";
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