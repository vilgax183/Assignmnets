package com.company;

import javax.xml.parsers.SAXParser;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
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
                    14. Use a Singleton matrix as a scalar matrix
                    15. show all matrix""");
            System.out.println("Select an option");
            int selection = Reader1.nextInt();
            if (selection == 1) {
                add_matrix();
            }
            if(selection==2){
                createMat();
            }
            if(selection==3){
                changeEl();
            }
            if(selection==4){
                display_all_types();
            }
            if(selection==15){
                show_all();
            }
            if (selection == 5) {
                arithmeticOps();
            }
            if(selection==6){
                element_wise();
            }
            if(selection==7){
                transpose();
            }
            if(selection==8){
                inverse();
            }
            if(selection==9){
                compute_mean();
            }
            if(selection==10){
                deter();
            }
            if(selection==11){
                atnt();
            }
            if(selection==12){
                eigen();
            }
            if(selection==13){
                linear_eq();
            }
            if(selection==14){
                singlee_to_scalar();
            }
        }
    }

    public static void add_matrix() throws IOException {
        int row, column;
        System.out.println("Enter the number of rows and columns");
        row = Reader1.nextInt();
        column = Reader1.nextInt();
        float[][] mat = new float[row][column];
        System.out.println("Enter the elements of matrix");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                mat[i][j] = (float) Reader1.nextDouble();
            }
        }
         boolean flag_row=false;
         boolean flag_col=false;
         boolean flag_singleton=false;
         boolean flag_square=false;
         boolean flag_rectangle=false;
         boolean flag_singular = false;
         boolean flag_upper = true;
         boolean flag_ones = true;
         boolean flag_null = true;
         boolean flag_lower = true;
         boolean flag_diagonal = true;
         boolean flag_scalar = true;
         boolean flag_identity = true;
         boolean flag_symmetric = true;
         boolean flag_skewSymmetric = true;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (mat[i][j] != 0) {
                    flag_null = false;
                    break;
                }
            }
            if (!flag_null) {
                break;
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (mat[i][j] != 1) {
                    flag_ones = false;
                    break;
                }
            }
            if (!flag_ones) {
                break;
            }
        }
        if(flag_null ==false && flag_ones==false && row==column){
            if (row == 1 && column == 1) {
                flag_singleton=true;
                System.out.println("singleton");
                singleton matrix = new singleton(mat[0][0]);
                matrix_list.add(matrix);
            }
            if (determinant(mat, row, row) == 0) {
                System.out.println("singular");
                singular matrix = new singular(row, column, mat);
                matrix_list.add(matrix);
                flag_singular = true;
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (i > j) {
                        if (mat[i][j] != 0) {
                            flag_upper = false;
                            break;
                        }
                    }
                }
                if (!flag_upper) {
                    break;
                }
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (i < j) {
                        if (mat[i][j] != 0) {
                            flag_lower = false;
                            break;
                        }
                    }
                }
                if (!flag_lower) {
                    break;
                }
            }
            for (int i = 0; i < row; i++) {
                if(mat.length>1) {
                    for (int j = 0; j < column; j++) {
                        if (i != j) {
                            if (mat[i][j] != 0) {
                                flag_diagonal = false;
                                break;
                            }
                        }

                    }

                    if (!flag_diagonal) {
                        break;
                    }
                }
            }
            if(flag_diagonal==false){
                flag_scalar=false;
                flag_identity=false;
            }
            if (flag_diagonal == true) {
                for (int i = 0; i < row - 1; i++) {
                    for (int j = 0; j < column - 1; j++) {
                        if (i == j) {
                            if (mat[i][j] != mat[i + 1][j + 1] || mat[i][j]==0) {
                                flag_scalar = false;
                                break;
                            }
                        }
                        if(i!=j){
                            if(mat[i][j]!=0){
                                flag_scalar=false;
                                break;
                            }
                        }
                    }
                    if (!flag_scalar) {
                        break;
                    }
                }
                if(flag_scalar==false){
                    flag_identity=false;
                }
                if (flag_scalar == true) {
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < column; j++) {
                            if (i == j) {
                                if (mat[i][j] != 1) {
                                    flag_identity = false;
                                    break;
                                }
                            }
                            if(i!=j){
                                if(mat[i][j]!=0){
                                    flag_identity=false;
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
            float[][] temp = trans(mat);
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (mat[i][j] != temp[i][j]) {
                        flag_symmetric = false;
                        break;
                    }
                }
                if (!flag_symmetric) {
                    break;
                }
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (mat[i][j] != -temp[i][j]) {
                        flag_skewSymmetric = false;
                        break;
                    }
                }
                if (!flag_skewSymmetric) {
                    break;
                }
            }
        }
        if(flag_null==true){
            System.out.println("null");
            null_matrix matrix=new null_matrix(row,column);
            matrix_list.add(matrix);
        }
        if(flag_ones==true){
            System.out.println("ones");
            ones_matrix matrix=new ones_matrix(row,column);
            matrix_list.add(matrix);
        }
         if(row!=column && flag_null==false &&flag_ones==false) {
             flag_rectangle = true;
             flag_upper = false;
             flag_lower = false;
             flag_diagonal = false;
             flag_scalar = false;
             flag_identity = false;
             flag_symmetric = false;
             flag_skewSymmetric = false;

             if (column == 1) {
                 System.out.println("column");
                 flag_col=true;
                 column_matrix matrix = new column_matrix(row, mat);
                 matrix_list.add(matrix);
             }
             if (row == 1) {
                 flag_row=true;
                 System.out.println("row");
                 row_matrix matrix = new row_matrix(column, mat);
                 matrix_list.add(matrix);
             } else if(flag_col==false && flag_row==false) {
                 rectangular_matrix matrix = new rectangular_matrix(row, column, mat);
                 System.out.println("rectangular");
                 matrix_list.add(matrix);
             }
         }

         if(flag_singular==false && flag_rectangle==false && flag_ones==false && flag_null==false &&flag_singleton==false) {
             if (flag_identity == true) {
                 System.out.println("identity");
                 identity matrix = new identity(row);
                 matrix_list.add(matrix);
             }
             if (flag_identity == false && flag_scalar == true) {
                 System.out.println("scalar");
                 scalar matrix = new scalar(row, mat[1][1]);
                 matrix_list.add(matrix);
             }
             if ((flag_identity == false && flag_scalar == false && flag_diagonal == true) && flag_upper == true && flag_lower == true) {
                 ArrayList<Float> alpha = new ArrayList<>();
                 for (int p = 0; p < row; p++) {
                     for (int q = 0; q < column; q++) {
                         if (p == q) {
                             alpha.add(mat[p][q]);
                         }
                     }
                 }
                 System.out.println("diagonal");
                 diagonal matrix = new diagonal(row, alpha);
                 matrix_list.add(matrix);

             }
             if (flag_upper == true && flag_lower == false) {
                 System.out.println("upper");
                 upper_triangular matrix = new upper_triangular(row, column, mat);
                 matrix_list.add(matrix);
             }
             if (flag_upper == false && flag_lower == true) {
                 System.out.println("lower");
                 lower_triangular matrix = new lower_triangular(row, column, mat);
                 matrix_list.add(matrix);
             }
             if (flag_symmetric == true && flag_skewSymmetric == false) {
                 System.out.println("symmetric");
                 symmetric matrix = new symmetric(row, column, mat);
                 matrix_list.add(matrix);
             }
             if (flag_symmetric == false && flag_skewSymmetric == true) {
                 System.out.println("skew");
                 skew_symmetric matrix = new skew_symmetric(row, column, mat);
                 matrix_list.add(matrix);
             }
         }
        if(flag_rectangle==false && flag_diagonal==false && flag_ones==false &&flag_lower==false && flag_null==false &&flag_skewSymmetric==false &&flag_singular==false) {
             System.out.println("Square");
             flag_square=true;
             square_matrix matrix = new square_matrix(row, column, mat);
             matrix_list.add(matrix);
         }
        ArrayList<String> alpha=new ArrayList<>();
        if(flag_singleton==true){
            alpha.add("Singular");
        }
        if(flag_col==true){
            alpha.add("Column");
        }
        if(flag_diagonal==true){
            alpha.add("Diagonal");
        }
        if(flag_identity==true){
            alpha.add("Identity");
        }
        if(flag_null==true){
            alpha.add("Null");
        }
        if(flag_ones==true){
            alpha.add("Ones");
        }
        if(flag_lower==true){
            alpha.add("Lower Triangular");
        }
        if (flag_upper == true) {
            alpha.add("Upper Triangular");
        }
        if (flag_row==true) {
            alpha.add("Row");
        }
        if (flag_scalar == true) {
            alpha.add("Scalar");
        }
        if(flag_square==true){
            alpha.add("Square");
        }
        if(flag_skewSymmetric==true){
            alpha.add("Skew Symmetric");
        }
        if (flag_symmetric == true) {
            alpha.add("Symmetric");
        }
        if(flag_rectangle==true){
            alpha.add("Rectangle ");
        }
        if(flag_singleton==true){
            alpha.add("Singleton");
        }
        matrix_list.get(matrix_list.size()-1).set_types(alpha);

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
            if(matrix_list.get(in1-1).getRow()==matrix_list.get(in2-1).getRow() && matrix_list.get(in1-1).getColumn()==matrix_list.get(in2-1).getColumn()){
                first_mat=matrix_list.get(in1-1).getMat();
                second_mat=matrix_list.get(in2-1).getMat();
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
            show_mat_list();
            float first_mat[][];
            float second_mat[][];
            int in1=Reader1.nextInt();
            int in2=Reader1.nextInt();
            if(matrix_list.get(in1-1).getRow()==matrix_list.get(in2-1).getRow() && matrix_list.get(in1-1).getColumn()==matrix_list.get(in2-1).getColumn()){
                first_mat=matrix_list.get(in1-1).getMat();
                second_mat=matrix_list.get(in2-1).getMat();
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
            show_mat_list();
            float first_mat[][];
            float second_mat[][];
            int in1=Reader1.nextInt();
            int in2=Reader1.nextInt();
            if(matrix_list.get(in1-1).getType().equals("Scalar Matrix")){
                second_mat=matrix_list.get(in2-1).getMat();
                float p=matrix_list.get(in1-1).getMat()[0][0];
                int rows=matrix_list.get(in2-1).getRow();
                int col=matrix_list.get(in2-1).getColumn();
                float[][] temp = new float[rows][col];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < col; j++) {
                            temp[i][j] =  p* second_mat[i][j];
                    }
                }
                System.out.println("\nResultant Matrix:");
                System.out.println(rows);
                System.out.println(col);
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < col; j++)
                        System.out.print(temp[i][j] + " ");
                    System.out.println();
                }
            }
            if(matrix_list.get(in2-1).getType().equals("Scalar Matrix")){
                second_mat=matrix_list.get(in1-1).getMat();
                float p=matrix_list.get(in2-1).getMat()[0][0];
                int rows=matrix_list.get(in1-1).getRow();
                System.out.println(rows);
                int col=matrix_list.get(in1-1).getColumn();
                float[][] temp = new float[rows][col];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < col; j++) {
                            temp[i][j] =  p* second_mat[i][j];
                    }
                }
                System.out.println(matrix_list.get(in1-1).getType());
                System.out.println(rows);
                System.out.println(col);
                System.out.println("\nResultant Matrix:");
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < col; j++)
                        System.out.print(temp[i][j] + " ");
                    System.out.println();
                }
            }
            else if(matrix_list.get(in1-1).getColumn()==matrix_list.get(in2-1).getRow()) {
                first_mat=matrix_list.get(in1-1).getMat();
                second_mat=matrix_list.get(in2-1).getMat();
                int rows=matrix_list.get(in1-1).getRow();
                int col=matrix_list.get(in2-1).getColumn();
                if(matrix_list.get(in1-1).getType().equals("Null Matrix") || matrix_list.get(in2-1).getType().equals("Null Matrix")){
                    System.out.println("Null matrix of the same order is printed");
                }
                else {
                    int columns = matrix_list.get(in1-1).getColumn();
                    float[][] temp = new float[rows][col];
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
            }
            else{
                System.out.println("Cannot be multiplied");
                return;
            }
        }
        if (choice == 4) {
            float first_mat[][];
            float second_mat[][];
            int in1 = Reader1.nextInt();
            int in2 = Reader1.nextInt();
            if (matrix_list.get(in2).getType().equals("Singular Matrix")) {
                System.out.println("Singular matrix provided");
                return;
            }
            if (matrix_list.get(in1).getColumn() == matrix_list.get(in2).getRow() && matrix_list.get(in2).getRow() == matrix_list.get(in2).getColumn()) {
                first_mat=matrix_list.get(in1).getMat();
                second_mat=matrix_list.get(in2).getMat();
                int rows=matrix_list.get(in1).getRow();
                int col=matrix_list.get(in2).getColumn();
                int mat3[][] = new int[rows][col];
                float[][] temp=matrix_list.get(in2-1).inverse();

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < col; j++) {
                        for (int k = 0; k < col; k++)
                            mat3[i][j] += first_mat[i][k] * temp[k][j];
                    }
                }


                System.out.println("\nResultant Matrix:");
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < col; j++)
                        System.out.print(mat3[i][j] + " ");
                    System.out.println();
                }

            }

      }
    }
    public static void transpose() throws IOException {
        show_mat_list();
        System.out.println("Choose the matrix to find transpose of ");
        int choice =Reader1.nextInt();
        int m=matrix_list.get(choice-1).getRow();
        int n=matrix_list.get(choice-1).getColumn();
        float[][] temp=new float[matrix_list.get(choice-1).getColumn()][matrix_list.get(choice-1).getRow()];
        temp=matrix_list.get(choice-1).transpose(matrix_list.get(choice-1).getMat());
        System.out.println("Transpose of entered matrix:-");

        for (int c = 0 ; c < n ; c++ )
        {
            for (int d = 0 ; d < m ; d++ )
                System.out.print(temp[c][d]+"\t");

            System.out.print("\n");
        }
    }
    static float[][] trans(float[][] mat)
    {
        int m=mat.length;
        int n=mat[0].length;
        float[][] trans = new float[n][m];
        for (int c = 0 ; c < m ; c++ )
        {
            for (int d = 0 ; d < n ; d++ )
                trans[d][c] = mat[c][d];
        }
        return trans;
    }

    public static void display_all_types() throws IOException {
        show_mat_list();
        System.out.println("Choose a matrix ");
        int choice=Reader1.nextInt();
        matrix_list.get(choice-1).get_types();


    }
    public static void compute_mean() throws IOException {
        show_mat_list();
        System.out.println("Select a matrix for further calculations");
        int choice =Reader1.nextInt();
        float[][] mat=matrix_list.get(choice-1).getMat();
        matrix_list.get(choice-1).rowMean(mat);
        matrix_list.get(choice-1).columnMean(mat);
        matrix_list.get(choice-1).mean(mat);
    }

    public static void deter() throws IOException {
        show_mat_list();
        System.out.println("Select a matrix for further calculations");
        int choice =Reader1.nextInt();
        float[][] mat=matrix_list.get(choice-1).getMat();
        int a=matrix_list.get(choice-1).getRow();
        int b=matrix_list.get(choice-1).getColumn();

        float det=matrix_list.get(choice-1).getdet(mat,a,b);
        System.out.println(det);
    }
    public static void atnt() throws IOException {
        show_mat_list();
        System.out.println("Select a matrix for further calculations");
        int choice=Reader1.nextInt();
        float[][] mat=matrix_list.get(choice-1).getMat();
        if(matrix_list.get(choice-1).getRow()!=matrix_list.get(choice-1).getColumn()){
            System.out.println("Operation cannot be performed");
        }
        else{
            int rows=mat.length;
            int columns=mat[0].length;
            float[][] temp=matrix_list.get(choice-1).transpose(mat);
            float[][] answer=new float[rows][rows];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    answer[i][j] = temp[i][j] + mat[i][j];
                }
            }
            System.out.println("The sum of the two matrices is");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    System.out.print(answer[i][j] + " ");
                }
                System.out.println(" ");
            }
        }
    }
    public static void show_mat_list(){
        System.out.println(matrix_list.size());
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

    public static void singlee_to_scalar() throws IOException {
        System.out.println("Do you want to convert singleton matrix to scalar? ");
        String ans=Reader1.nextLine();
        if(ans.toLowerCase().equals("yes")){
            show_mat_list();
            System.out.println("Choose a singleton matrix");
            int choice=Reader1.nextInt();
            if(matrix_list.get(choice-1).getType().equals("Singleton Matrix")){
                float alpha=matrix_list.get(choice-1).getMat()[0][0];
                scalar matrix=new scalar(1,alpha);
                matrix_list.add(matrix);
                matrix_list.remove(matrix_list.get(choice-1));
            }
            else{
                System.out.println("Select a singleton matrix");
            }
        }
    }

    public static void inverse() throws IOException {
        show_mat_list();
        System.out.println("Select a matrix for further calculations");
        int choice=Reader1.nextInt();
        float[][] temp;
        temp=matrix_list.get(choice-1).inverse();
        System.out.println("\nInverse of matrix is:");
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < 2; ++j)
                    System.out.print(temp[i][j]+" ");
                System.out.print("\n");
            }

    }
    public static void createMat() throws IOException {
        System.out.println("Choose a type of matrix you want to create");
        System.out.println("""
                1. Rectangular Matrix
                2. Row Matrix
                3. Column Matrix
                4. Square Matrix
                5. Symmetric Matrix
                6. Skew-symmetric Matrix
                7. Upper-triangular Matrix
                8. Lower-triangular Matrix
                9. Singular Matrix
                10. Diagonal Matrix
                11. Scalar Matrix
                12. Identity Matrix
                13. Singleton Matrix
                14. Ones Matrix
                15. Null Matrix
                """);
        int choice =Reader1.nextInt();
        if(choice==1){
            System.out.println("Enter the dimensions");
            int row=Reader1.nextInt();
            int col=Reader1.nextInt();
            float[][] matrix=new float[row][col];
            int p=row*col;
            int start=0;
            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    matrix[i][j]=start;
                }
                start=start+1;
            }
            new rectangular_matrix(row,col,matrix);
        }
        if(choice==2){
            System.out.println("Enter the dimensions");
            int col=Reader1.nextInt();
            float[][] matrix=new float[1][col];
            int p=1*col;
            int start=0;
            for(int i=0;i<1;i++){
                for(int j=0;j<col;j++){
                    matrix[i][j]=start;
                }
                start=start+1;
            }
            new row_matrix(col,matrix);
        }
        if(choice==3){
            System.out.println("Enter the dimensions");
            int col=Reader1.nextInt();
            float[][] matrix=new float[col][1];
            int p=1*col;
            int start=0;
            for(int i=0;i<col;i++){
                for(int j=0;j<1;j++){
                    matrix[i][j]=start;
                }
                start=start+1;
            }
            new row_matrix(col,matrix);
        }
        if(choice==4){
            System.out.println("Enter the dimensions");
            int row=Reader1.nextInt();
            int col=row;
            float[][] matrix=new float[row][col];
            int p=row*col;
            int start=0;
            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    matrix[i][j]=start;
                }
                start=start+1;
            }
            new rectangular_matrix(row,col,matrix);
        }
        if(choice==5){
            System.out.println("Enter the rows");
            int rows=Reader1.nextInt();
            if(rows==2){
                float[][] temp=new float[][]{{1,2},{2,2}};
                new symmetric(rows,rows,temp);
            }
            if(rows==3){
                float[][] temp=new float[][]{{1,2,3},{2,4,5},{3,5,8}};
                new symmetric(rows,rows,temp);
            }
        }
        if(choice==6){
            System.out.println("Enter the rows");
            int rows=Reader1.nextInt();
            if(rows==2){
                float[][] temp=new float[][]{{0,-9},{0,0}};
                new skew_symmetric(rows,rows,temp);
            }
            if(rows==3){
                float[][] temp=new float[][]{{0,1,-2},{-1,0,3},{2,-3,0}};
                new skew_symmetric(rows,rows,temp);
            }
        }
        if(choice==7){
            System.out.println("Enter the dimension");
            int rows=Reader1.nextInt();
            if(rows==2){
                float[][] temp=new float[][]{{1,2},{0,3}};
                new lower_triangular(rows, rows,temp);
            }
            if(rows==3)
            {
                float[][] temp=new float[][]{{1,2,3},{0,3,2},{0,0,1}};
                new lower_triangular(rows, rows,temp);
            }
        }
        if(choice==8){
            System.out.println("Enter the dimension");
            int rows=Reader1.nextInt();
            if(rows==2){
                float[][] temp=new float[][]{{1,0},{2,3}};
                new lower_triangular(rows, rows,temp);
            }
            if(rows==3)
            {
                float[][] temp=new float[][]{{1,0,0},{2,3,0},{6,5,1}};
                new lower_triangular(rows, rows,temp);
            }
        }
        if(choice==9){
            System.out.println("Enter the number of rows");
            int rows=Reader1.nextInt();
            float[][] temp=new float[rows][rows];
            if(rows==1){
                temp[0][0]=0;
                new singular(rows,rows,temp);
            }
            if(rows==2){
                temp[0][0]=2;
                temp[0][1]=3;
                temp[1][0]=3;
                temp[1][1]=2;
                new singular(rows,rows,temp);
            }
            if(rows==3){
                temp= new float[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
                new singular(rows,rows,temp);
            }
        }
        if(choice==10){
            System.out.println("Enter number of rows");
            int row=Reader1.nextInt();
            ArrayList<Float> temp = new ArrayList<>();
            float start =1;
            for(int i=0;i<row;i++){
                temp.set(i, start);
                start=start+1;
            }
            new diagonal(row,temp);
        }
        if(choice==11){
            System.out.println("Enter number of rows");
            int row=Reader1.nextInt();
            new scalar(row,5);
        }
        if(choice==12){
            System.out.println("Enter number of rows");
            int row=Reader1.nextInt();
            new identity(row);
        }
        if(choice==13){
            System.out.println("Enter the value");
            new singleton(3);
        }
        if(choice==14){
            System.out.println("Enter the dimensions");
            int row=Reader1.nextInt();
            int col=Reader1.nextInt();
            new ones_matrix(row,col);
        }
        if(choice==15){
            System.out.println("Enter the dimensions");
            int row=Reader1.nextInt();
            int col=Reader1.nextInt();
            new null_matrix(row,col);

        }

    }

    public static void changeEl() throws IOException {
        show_mat_list();
        System.out.println("Select a matrix");
        int choice=Reader1.nextInt();
        if(matrix_list.get(choice-1).getType().equals("Null Matrix") ||matrix_list.get(choice-1).getType().equals("Ones Matrix") || matrix_list.get(choice-1).getType().equals("Identity matrix") ){
            System.out.println("No change can be done on this type of matrix");
        }

        else {
            System.out.println("How many values do you want to change");
            int number=Reader1.nextInt();
            for (int i = 0; i < number; i++) {
                System.out.println("Enter the row of the change");
                int row = Reader1.nextInt();
                System.out.println("Enter the column of the change");
                int col = Reader1.nextInt();
                System.out.println("Enter the value change");
                int change = Reader1.nextInt();
                matrix_list.get(choice - 1).getMat()[row][col] = change;
            }
            int rows = matrix_list.get((choice - 1)).getRow();
            int col = matrix_list.get((choice - 1)).getColumn();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < col; j++) {
                    System.out.print(matrix_list.get(choice - 1).getMat()[i][j] + " ");
                }
                System.out.println(" ");
            }
        }
    }

    public static void linear_eq() throws IOException {
        show_mat_list();
        System.out.println("Select two matrices for further calculation");
        int first=Reader1.nextInt();
        int second=Reader1.nextInt();
        System.out.println(matrix_list.get(first-1).getColumn());
        System.out.println(matrix_list.get(second-1).getRow());
        if(matrix_list.get(first-1).getColumn()!=matrix_list.get(second-1).getRow() || matrix_list.get(first-1).inverse()==null ){
            System.out.println("Cannot be calculated");
        }
        else{
            int rows=matrix_list.get(first-1).getRow();
            int columns=matrix_list.get(first-1).getColumn();
            float[][] temp=new float[rows][columns];
            float[][] inverted=matrix_list.get(first-1).inverse();

            int col=matrix_list.get(second-1).getColumn();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < col; j++) {
                    for (int k = 0; k < columns; k++)
                        temp[i][j] += inverted[i][k] * matrix_list.get(second-1).getMat()[k][j];
                }
            }
            System.out.println("\nResultant Matrix:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < col; j++)
                    System.out.print(temp[i][j] + " ");
                System.out.println();
            }

        }
    }

    public static void show_all() throws IOException {
        System.out.println("Select a type");
        System.out.println("""
                1. Rectangular Matrix
                2. Row Matrix
                3. Column Matrix
                4. Square Matrix
                5. Symmetric Matrix
                6. Skew-symmetric Matrix
                7. Upper-triangular Matrix
                8. Lower-triangular Matrix
                9. Singular Matrix
                10. Diagonal Matrix
                11. Scalar Matrix
                12. Identity Matrix
                13. Singleton Matrix
                14. Ones Matrix
                15. Null Matrix""");
        int choice=Reader1.nextInt();
        String[] predefined={"Rectangular",
                "Row",
                "Column",
                "Square",
                "Symmetric",
                "Skew Symmetric",
                "Upper Triangular",
                "Lower Triangular",
                "Singular",
                "Diagonal",
                "Scalar",
                "Identity",
                "Singleton",
                "Ones",
                "Null"};
            for(int i=0;i<matrix_list.size();i++){
                ArrayList<String> temp=matrix_list.get(i).return_types();
                for(int j=0;j<temp.size();j++){
                    if(temp.get(j).equals(predefined[choice-1])){
                        for (int k = 0; k < matrix_list.get(i).getRow(); k++) {
                            for (int p = 0; p < matrix_list.get(i).getColumn(); p++) {
                                System.out.print(matrix_list.get(i).getMat()[k][p] + " ");
                            }
                            System.out.println();
                        }
                }
            }
        }
    }

    public static void eigen() throws IOException {
        show_mat_list();
        int input=Reader1.nextInt();
        if(matrix_list.get(input-1).getRow()!=2 && matrix_list.get(input-1).getColumn()!=2){
            System.out.println("Eigen value cannot be calculated");
        }
        else {
            float[][] temp = matrix_list.get(input - 1).getMat();
            float a = temp[0][0];
            float b = temp[0][1];
            float c = temp[1][0];
            float d = temp[1][1];
            float to = (((a + d) * (a + d)) - 4 * 1 * (a * d - b * c));
            if (to < 0) {
                System.out.println("cannot be calculated");
            }
            else {
                float[][] answer=new float[2][2];
                float ans = square(to);
                float l1 = ((a + d) + ans) / 2;
                float l2 = ((a + d) - ans) / 2;
                System.out.println(l1);
                System.out.println(l2);
            }
        }
    }
    public static float square(float number){
        float t;

        float squareroot = number / 2;

        do {
            t = squareroot;
            squareroot = (t + (number / t)) / 2;
        } while ((t - squareroot) != 0);

        return squareroot;
    }

    public static void element_wise() throws IOException {
        show_mat_list();
        System.out.println("Select 2 matrices for further calculations");
        float first_mat[][];
        float second_mat[][];
        int in1=Reader1.nextInt();
        int in2=Reader1.nextInt();
        if(matrix_list.get(in1-1).getRow()==matrix_list.get(in2-1).getRow() && matrix_list.get(in1-1).getColumn() ==matrix_list.get(in2-1).getColumn()){
            first_mat=matrix_list.get(in1-1).getMat();
            second_mat=matrix_list.get(in2-1).getMat();
            int rows=matrix_list.get(in1-1).getRow();
            int col=matrix_list.get(in2-1).getColumn();
            float[][] temp=new float[rows][col];
            System.out.println("""
                    Choose option
                    1. Bit-wise multiplication
                    2. Bit-wise division""");
            int choice=Reader1.nextInt();
            if(choice==1){
                for(int i=0;i<rows;i++){
                    for(int j=0;j<col;j++){
                        temp[i][j]=first_mat[i][j]*second_mat[i][j];
                    }
                }
                System.out.println("Bit-Wise multiplication:  ");
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < col; j++) {
                        System.out.print(temp[i][j] + " ");
                    }
                    System.out.println(" ");
                }
            }
            if(choice==2){
                for(int i=0;i<rows;i++){
                    for(int j=0;j<col;j++){
                        temp[i][j]=first_mat[i][j]/second_mat[i][j];
                    }
                }
                System.out.println("Bit-Wise division:  ");
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < col; j++) {
                        System.out.print(temp[i][j] + " ");
                    }
                    System.out.println(" ");
                }
            }
        }

    }

}

abstract class matrix {
    protected ArrayList<String> list;
    protected int row, column;
    protected static int inx=0;
    protected int index;
    matrix(int row,int column){
        this.list=null;
        this.index=inx;
        inx++;
        this.row=row;
        this.column=column;
    }

    abstract public int getRow() ;

    abstract public int getColumn();

    abstract public void cofactor(float[][] mat,float[][] temp,int p,int q,int n);

    public abstract float getdet(float[][] mat, int n, int N);

    public abstract float[][] transpose(float[][] mat);

    abstract public void rowMean(float[][] mat);

    abstract public void columnMean(float[][] mat);

    abstract public void mean(float[][] mat);

    abstract public float[][] getMat();

    abstract public float[][] inverse();

    String getType() {
        return null;
    }

    public void set_types(ArrayList<String> list){
        this.list=list;
    }
    public void get_types(){
        System.out.println(list);
    }
    public ArrayList<String> return_types(){
        return list;
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
    public float[][] transpose(float[][] mat) {
        int m=mat.length;
        System.out.println(m);
        int n=mat[0].length;
        System.out.println(n);
        float[][] trans = new float[n][m];
        for (int c = 0 ; c < m ; c++ )
        {
            for (int d = 0 ; d < n ; d++ )
                trans[d][c] = mat[c][d];
        }
        return trans;
    }

    @Override
    public void rowMean(float[][] mat) {
        int rows=mat.length;
        int cols=mat[0].length;
        for (int i = 0; i < rows; i++) {
            int rSum = 0;
            for (int j = 0; j < cols; j++) {
                rSum += mat[i][j];
            }
            System.out.println("Row " + (i + 1) + " sum = " + rSum);
        }
    }

    @Override
    public void columnMean(float[][] mat ) {
        int rows=mat.length;
        int cols=mat[0].length;
        for (int i = 0; i < cols; i++) {
            int cSum = 0;
            for (int j = 0; j < rows; j++) {
                cSum += mat[j][i];
            }
            System.out.println("Column " + (i + 1) + " sum = " + cSum);
        }
    }

    @Override
    public void mean(float[][] mat) {
        int rows=mat.length;
        int cols=mat[0].length;
        int sum = 0;
        for (int i = 0; i < rows; i++) {
            int rSum = 0;
            for (int j = 0; j < cols; j++) {
                sum += mat[i][j];
            }
        }
        System.out.println("Sum of all elements = " + sum);
    }


    @Override
    public float[][] getMat() {
        return mat;
    }



    @Override
    public float[][] inverse() {
        System.out.println("Inverse cannot be calculated");
        return null;
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
    public float[][] transpose(float[][] mat) {
        int m=mat.length;
        int n=mat[0].length;
        float[][] trans = new float[m][n];
        for (int c = 0 ; c < m ; c++ )
        {
            for (int d = 0 ; d < n ; d++ )
                trans[d][c] = mat[c][d];
        }
        return trans;
    }

    @Override
    public void rowMean(float[][] mat) {
        int rows=mat.length;
        int cols=mat[0].length;
        for (int i = 0; i < rows; i++) {
            int rSum = 0;
            for (int j = 0; j < cols; j++) {
                rSum += mat[i][j];
            }
            System.out.println("Row " + (i + 1) + " sum = " + rSum);
        }
    }

    @Override
    public void columnMean(float[][] mat ) {
        int rows=mat.length;
        int cols=mat[0].length;
        for (int i = 0; i < cols; i++) {
            int cSum = 0;
            for (int j = 0; j < rows; j++) {
                cSum += mat[j][i];
            }
            System.out.println("Column " + (i + 1) + " sum = " + cSum);
        }
    }

    @Override
    public void mean(float[][] mat) {
        int rows=mat.length;
        int cols=mat[0].length;
        int sum = 0;
        for (int i = 0; i < rows; i++) {
            int rSum = 0;
            for (int j = 0; j < cols; j++) {
                sum += mat[i][j];
            }
        }
        System.out.println("Sum of all elements = " + sum);
    }



    @Override
    public float[][] getMat() {
        return mat;
    }



    @Override
    public float[][] inverse()  {
        int n=this.row;
        float[][] A=new float[n][n];
        if (this.row == 2) {
            int i, j;
            float det, temp;
            det = (this.mat[0][0] *this.mat[1][1]) - (this.mat[0][1] * this.mat[1][0]);
            A[0][0] = this.mat[1][1]/det;
            A[1][1] = this.mat[0][0]/det;
            A[0][1] = -this.mat[0][1]/det;
            A[1][0] = -this.mat[1][0]/det;
//            System.out.println("\nInverse of matrix is:");
//            for (i = 0; i < 2; ++i) {
//                for (j = 0; j < 2; ++j)
//                    System.out.print((A[i][j] / det) + " ");
//                System.out.print("\n");
//            }

        }
        if(n==3){
            int i, j;
            float det = 0;
            for(i = 0; i < 3; i++)
                det = det + (this.mat[0][i] * (this.mat[1][(i+1)%3] * this.mat[2][(i+2)%3] - this.mat[1][(i+2)%3] * this.mat[2][(i+1)%3]));
            for(i = 0; i < 3; ++i) {
                for(j = 0; j < 3; ++j)
                    A[i][j]=((((this.mat[(j+1)%3][(i+1)%3] * this.mat[(j+2)%3][(i+2)%3]) - (this.mat[(j+1)%3][(i+2)%3] * this.mat[(j+2)%3][(i+1)%3]))/ det));
            }
        }
        return  A;
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

    @Override
    public float[][] transpose(float[][] mat) {
        return super.transpose(mat);
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
    private float val;
    singleton( float val) {
        super(1,1, null);
        this.val=val;
    }
    String getType(){
        return "Singleton Matrix";
    }

    @Override
    public float[][] getMat() {
        float[][] temp=new float[1][1];
        temp[0][0]=this.val;
        return temp;

    }

    @Override
    public float getdet(float[][] mat, int n, int N) {
        return mat[0][0];
    }

    @Override
    public void columnMean(float[][] mat) {
        System.out.println(mat[0][0]);
    }
    public void rowMean(float[][] mat) {
        System.out.println(mat[0][0]);
    }
    public void mean(float[][] mat) {
        System.out.println(mat[0][0]);
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

    @Override
    public float[][] getMat() {
        float[][] temp=new float[row][row];
        for(int i=0;i<row;i++){
            for(int j=0;j<row;j++){
                if(i==j)
                temp[i][j]= this.alpha.get(i);
            }
        }
        return temp;
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

    @Override
    public float[][] getMat() {
        float[][] temp=new float[row][row];
        for(int i=0;i<row;i++){
            for(int j=0;j<row;j++){
                if(i==j)
                    temp[i][j]= this.scal;
            }
        }
        return temp;
    }

    @Override
    public float[][] inverse() {
        int n=this.row;
        float[][] temp=new float[n][n];
        for(int i=0;i<row;i++){
            for(int j=0;j<row;j++){
                if(i==j){
                    temp[i][j]=1/this.scal;
                }
            }
        }
        return temp;
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

    @Override
    public float[][] getMat() {
        float[][] temp=new float[row][row];
        for(int i=0;i<row;i++){
            for(int j=0;j<row;j++){
                if(i==j)
                    temp[i][j]=1;
            }
        }
        return temp;
    }

    @Override
    public float[][] inverse() {
        return this.mat;
    }
}
class symmetric extends square_matrix{

    symmetric( int row, int column,float[][] mat) {
        super( row, column,mat);
    }
    String getType(){
        return "Symmetric Matrix";
    }

    @Override
    public float getdet(float[][] mat, int n, int N) {
        return super.getdet(mat, n, N);
    }
}
class skew_symmetric extends square_matrix{

    skew_symmetric( int row, int column,float[][] mat) {
        super( row, column,mat);
    }
    String getType(){
        return "Skew Symmetric Matrix";
    }

    @Override
    public float getdet(float[][] mat, int n, int N) {
        return super.getdet(mat, n, N);
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
    public void columnMean(float[][] mat) {
        System.out.println(1);
    }
    public void rowMean(float[][] mat) {
        System.out.println(1);
    }
    public void mean(float[][] mat) {
        System.out.println(1);
    }



    @Override
    public float getdet(float[][] mat, int n, int N) {
        return 0;
    }

    @Override
    public float[][] transpose(float[][] mat) {
        return mat;
    }

    @Override
    public float[][] getMat() {
        if(row==2){
            float[][] temp={{1,1},{1,1}};
            return temp;
        }
        if(row==3){
            float[][] temp={{1,1,1},{1,1,1},{1,1,1}};
            return temp;
        }
        return null;
    }



    @Override
    public float[][] inverse() {

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
    public float[][] transpose(float[][] mat) {
        float[][] temp={{0,0,0},{0,0,0},{0,0,0}};
        return temp;
    }

    @Override
    public void rowMean(float[][] mat) {
        System.out.println(0);
    }

    @Override
    public void columnMean(float[][] mat ) {
        System.out.println(0);
    }

    @Override
    public void mean(float[][] mat) {
        System.out.println(0);
    }


    @Override
    public float[][] getMat() {
        if(row==2){
            float[][] temp={{0,0},{0,0}};
            return temp;
        }
        if(row==3){
            float[][] temp={{0,0,0},{0,0,0},{0,0,0}};
            return temp;
        }
        return null;
    }



    @Override
    public float[][] inverse() {
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