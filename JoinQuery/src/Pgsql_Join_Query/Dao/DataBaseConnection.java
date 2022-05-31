package Pgsql_Join_Query.Dao;

import java.sql.*;

public class DataBaseConnection {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String query;
    public void dbConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1212");

            statement = connection.createStatement();
        } catch (Exception exception) {
            System.out.println("Exception");
        }
    }

    public void studentDetails(){
        try {
            retrieveStudentDetails();
        } catch (Exception exception) {
            System.out.println("exception");
        }
    }

    public void studentMarksDetails() {
        try {
            retrieveStudentMarkDetails();
        } catch (Exception exception) {
            System.out.println("Exception");
        }
    }

    public void retrieveStudentDetails() throws Exception {
        System.out.println("-----------------STUDENT DETAILS---------------------------");
        ResultSet studentDetails = statement.executeQuery("select * from StudentDetails ORDER BY student_Id");

        ResultSetMetaData resultSetMetaData = studentDetails.getMetaData();

        int numberOfColumns = resultSetMetaData.getColumnCount();

        for(int i = 1; i<=numberOfColumns; i++) {
            System.out.print(resultSetMetaData.getColumnName(i)+"\t");
        }
        System.out.println("\n");
        while(studentDetails.next()) {
            for (int i = 1; i <= numberOfColumns; i++) {
                System.out.print(studentDetails.getString(i) + "\t");
            }
            System.out.println("\n");
        }
        studentDetails.close();
    }

    public void retrieveStudentMarkDetails() throws Exception {
        System.out.println("-----------------STUDENT MARK DETAILS-----------------------");
        ResultSet studentMarkDetails = statement.executeQuery("Select * from studentMarksDetails ORDER BY student_Id");

        ResultSetMetaData studentMarksDetailsMetaData = studentMarkDetails.getMetaData();

        int countColumns = studentMarksDetailsMetaData.getColumnCount();

        for(int i = 1; i<=countColumns; i++) {
            System.out.print(studentMarksDetailsMetaData.getColumnName(i)+"\t");
        }
        System.out.println("\n");
        while(studentMarkDetails.next()) {
            for (int i = 1; i <= countColumns; i++) {
                System.out.print(studentMarkDetails.getString(i) + "\t");
            }
            System.out.println("\n");
        }
        studentMarkDetails.close();
    }

    public void retrieveDetails(final String query) {
        try {
            resultSet = statement.executeQuery(query);

            while (resultSet.next() ) {
                System.out.print(resultSet.getString("Student_Id")+"\t");
                System.out.print(resultSet.getString("Student_Name")+"\t");
                System.out.println(resultSet.getString("Mark"));
            }
        } catch (Exception exception) {
            System.out.println("Exception");
        }
    }

    public void joinInnerQuery() {
        try {
            dbConnection();
            query = "select StudentDetails.Student_Id,StudentDetails.Student_Name,sum(studentMarksDetails.Mark) as Mark from StudentDetails inner join studentMarksDetails on StudentDetails.Student_Id = StudentMarksDetails.Student_ID Group by StudentDetails.Student_ID ORDER BY student_Id";

            retrieveDetails(query);
        } catch (Exception exception) {
            System.out.println("Exception");
        }
    }

    public void customizeInnerQuery(final String id) {
        try {
            dbConnection();
            query = "select StudentDetails.Student_Id,StudentDetails.Student_Name,sum(studentMarksDetails.Mark) as Mark from StudentDetails inner join studentMarksDetails on StudentDetails.Student_Id = StudentMarksDetails.Student_ID where StudentDetails.Student_Id ='"+ id +"'Group by StudentDetails.Student_ID";

            retrieveDetails(query);
        } catch (Exception exception) {
            System.out.println("Exception");
        }
    }

    public void joinLeftQuery() {
        try {
            dbConnection();
            query = "select StudentDetails.Student_Id,StudentDetails.Student_Name,sum(studentMarksDetails.Mark) as Mark from StudentDetails left join studentMarksDetails on StudentDetails.Student_Id = StudentMarksDetails.Student_ID Group by StudentDetails.Student_ID ORDER BY student_Id";

            retrieveDetails(query);
        } catch (Exception exception) {
            System.out.println("Exception");
        }
    }

    public void joinRightQuery() {
        try {
            dbConnection();
            query = "select StudentDetails.Student_Id,StudentDetails.Student_Name,sum(studentMarksDetails.Mark) as Mark from StudentDetails right join studentMarksDetails on StudentDetails.Student_Id = StudentMarksDetails.Student_ID Group by StudentDetails.Student_ID ORDER BY student_Id";

            retrieveDetails(query);
        } catch (Exception exception) {
            System.out.println("Exception");
        }
    }

    public void joinOuterQuery() {
        try {
            dbConnection();
            query = "select StudentDetails.Student_Id,StudentDetails.Student_Name,sum(studentMarksDetails.Mark) as Mark from StudentDetails full outer join studentMarksDetails on StudentDetails.Student_Id = StudentMarksDetails.Student_ID Group by StudentDetails.Student_ID ORDER BY student_Id";

            retrieveDetails(query);
        } catch (Exception exception) {
            System.out.println("Exception");
        }
    }
}