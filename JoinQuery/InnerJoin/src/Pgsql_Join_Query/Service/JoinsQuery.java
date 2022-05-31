package Pgsql_Join_Query.Service;

import Pgsql_Join_Query.Controller.Controller;
import Pgsql_Join_Query.Model.Model;
import Pgsql_Join_Query.View.View;

import java.util.Scanner;

public class JoinsQuery implements InnerJoinsQueryInPgsql {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final Model MODEL = new Model();

    private static final View VIEW = new View();

    private static final Controller CONTROLLER = new Controller (MODEL, VIEW);

    public void pgsqlInnerQuery() {
        CONTROLLER.innerJoinsQuery();
    }

    public void pgsqlCustomizeInnerQuery() {
        assignId();
        CONTROLLER.customizeInnerJoinsQuery();
    }

    public void pgsqlLeftQuery() {
        CONTROLLER.leftJoinsQuery();
    }

    public void pgsqlRightQuery() {
        CONTROLLER.rightJoinsQuery();
    }

    public void pgsqlOuterQuery() {
        CONTROLLER.outerJoinsQuery();
    }

    public void displayPgsqlTable() {
        CONTROLLER.pgsqlTableDetails();
    }

    public void displayJoins() {
        System.out.println("\n");
        System.out.println("1.Display Table \t 2.Inner Join \t 3.Customize Search Inner Join \t 4.Left Join \t 5.Right Join  \t 6.OuterJoin\t ");
        System.out.print("press key :"+"\t");
        String joinsTypes = SCANNER.nextLine();

        String joins = "[1-6]";

        boolean joinsDetails = joinsTypes.matches(joins);

        if (joinsDetails) {
            switch (joinsTypes) {
            case "1" -> displayPgsqlTable();
            case "2" -> pgsqlInnerQuery();
            case "3" -> pgsqlCustomizeInnerQuery();
            case "4" -> pgsqlLeftQuery();
            case "5" -> pgsqlRightQuery();
            case "6" -> pgsqlOuterQuery();
            default -> System.out.println("----I--N--V--A--L--I--D----");
            }
        } else {
            System.out.println("Invalid Selection");
            displayJoins();
        }
    }


    public void assignId() {
        System.out.print("Enter Your UserId : \t");
        String studentId = SCANNER.nextLine();

        String studentIdPattern = "(?:[0-9]|[0-9]{2}|[0-9]{3})";

        boolean studentDetails = studentId.matches(studentIdPattern);

        if (studentDetails) {
            MODEL.setId(studentId);
        } else {
            System.out.println("-----StudentId not Found--------");
            assignId();
        }
    }

    public static void main(String[] args) {
        JoinsQuery joinsQuery = new JoinsQuery();

        joinsQuery.displayJoins();
    }
}