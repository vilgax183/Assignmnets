package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
            if(selection==4){
                show_mat_list();
                System.out.println("Enter choice");
                int choice=Reader1.nextInt();
                type(matrix_list.get(choice-1));
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
                mat[i][j]=Reader1.nextInt();
            }
        }
        if(row==column){
            if(determinant(mat,row,row)==0){
                singular matrix=new singular(row,column,mat);
                matrix_list.add(matrix);
            }
            else{
                boolean flag_upper=true;
                boolean flag_lower=true;
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
                                flag_upper=false;
                            }
                        }
                    }
                }
                if()
            }
        }
    }
    public static String type(matrix mat){

    }
    public static float determinant(float[][] mat, int dim, int dim_fin){
        int D = 0; // Initialize result

        // Base case : if matrix
        // contains single element
        if (dim == 1)
            return mat[0][0];

        // To store cofactors
        float temp[][] = new float[dim_fin][dim_fin];

        // To store sign multiplier
        int sign = 1;

        // Iterate for each element of first row
        for (int f = 0; f < dim; f++) {
            // Getting Cofactor of mat[0][f]
            getCofactor(mat, temp, 0, f, dim);
            D += sign * mat[0][f]
                    * determinant(temp, dim - 1,dim_fin);

            // terms are to be added
            // with alternate sign
            sign = -sign;
        }

        return D;

    }
    static void getCofactor(float[][] mat, float[][] temp,
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
    private int row, column;
    matrix(int row,int column){
        this.row=row;
        this.column=column;
    }
    abstract public int getRow() ;

    abstract public int getColumn();

    abstract public float getdet();

    abstract public matrix transpose();

    abstract public float rowMean();

    abstract public float columnMean();

    abstract public float mean();

    abstract public matrix inverse();

    abstract public int[][] getMat();

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
        return 0;
    }


    @Override
    public int getColumn() {
        return 0;
    }

    @Override
    public float getdet() {
        return 0;
    }

    @Override
    public matrix transpose() {
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
    public matrix inverse() {
        return null;
    }

    @Override
    public int[][] getMat() {
        return new int[0][];
    }

    @Override
    String getType() {
        return ("Rectangular Matrix");
    }
}
class row_matrix extends rectangular_matrix {

    row_matrix( int row, int column,float[][] mat) {
        super(row, column,mat);
    }
    String getType(){
        return "Row Matrix";
    }
}
class column_matrix extends rectangular_matrix {

    column_matrix(int row, int column,float[][] mat) {
        super(row, column,mat);
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
        return 0;
    }

    @Override
    public int getColumn() {
        return 0;
    }

    @Override
    public float getdet() {
        return 0;
    }

    @Override
    public matrix transpose() {
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
    public matrix inverse() {
        return null;
    }

    @Override
    public int[][] getMat() {
        return new int[0][];
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
}
class lower_triangular extends square_matrix{

    lower_triangular( int row, int column,float[][] mat) {
        super( row, column,mat);
    }
    @Override
    String getType(){
        return "Lower Triangular Matrix";
    }
}
class singular extends square_matrix{

    singular( int row, int column,float[][] mat) {
        super( row, column,mat);
    }
    String getType(){
        return "Singular Matrix";
    }
}
class diagonal extends square_matrix{

    diagonal( int row, int column,float[][] mat) {
        super( row, column,mat);
    }
    String getType(){
        return "Diagonal Matrix";
    }
}
class scalar extends diagonal{

    scalar(int row, int column,float[][] mat) {
        super( row, column,mat);
    }
    String getType(){
        return "Scalar Matrix";
    }
}
class identity extends diagonal{

    identity( int row, int column,float[][] mat) {
        super( row, column,mat);
    }

    String getType(){
        return "Identity matrix";
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
    protected float[][] mat;
    ones_matrix( int row, int column,float[][] mat) {
        super( row, column);
        this.mat=mat;
    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public int getColumn() {
        return 0;
    }

    @Override
    public float getdet() {
        return 0;
    }

    @Override
    public matrix transpose() {
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
    public matrix inverse() {
        return null;
    }

    @Override
    public int[][] getMat() {
        return new int[0][];
    }

    @Override
    String getType() {
        return "Ones Matrix";
    }
}
class null_matrix extends matrix{
    protected float[][] mat;
    null_matrix( int row, int column,float[][] mat) {
        super(row, column);
        this.mat=mat;
    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public int getColumn() {
        return 0;
    }

    @Override
    public float getdet() {
        return 0;
    }

    @Override
    public matrix transpose() {
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
    public matrix inverse() {
        return null;
    }

    @Override
    public int[][] getMat() {
        return new int[0][];
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