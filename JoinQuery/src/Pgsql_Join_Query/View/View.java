package Pgsql_Join_Query.View;

import Pgsql_Join_Query.Dao.DataBaseConnection;
import Pgsql_Join_Query.Service.JoinsQuery;

public class View {

    public void displayStudentDetails() {
        try {
            final DataBaseConnection dataBaseConnection = new DataBaseConnection();
            dataBaseConnection.dbConnection();
            dataBaseConnection.studentDetails();
            dataBaseConnection.studentMarksDetails();

            final JoinsQuery joinsQuery = new JoinsQuery();
            joinsQuery.displayJoins();
        } catch (Exception e) {
            System.out.println("Not connected");
        }
    }

    public void innerJoin() {
        final DataBaseConnection dataBaseConnection = new DataBaseConnection();
        dataBaseConnection.joinInnerQuery();

        final JoinsQuery joinsQuery = new JoinsQuery();
        joinsQuery.displayJoins();
    }

    public void customizeInnerJoin(final String Id) {
        final DataBaseConnection dataBaseConnection = new DataBaseConnection();
        dataBaseConnection.customizeInnerQuery(Id);

        final JoinsQuery joinsQuery = new JoinsQuery();
        joinsQuery.displayJoins();
    }

    public void leftJoin() {
        final DataBaseConnection dataBaseConnection = new DataBaseConnection();
        dataBaseConnection.joinLeftQuery();

        final JoinsQuery joinsQuery = new JoinsQuery();
        joinsQuery.displayJoins();
    }

    public void rightJoin() {
        final DataBaseConnection dataBaseConnection = new DataBaseConnection();
        dataBaseConnection.joinRightQuery();

        final JoinsQuery joinsQuery = new JoinsQuery();
        joinsQuery.displayJoins();
    }

    public void outerJoin() {
        final DataBaseConnection dataBaseConnection = new DataBaseConnection();
        dataBaseConnection.joinOuterQuery();

        final JoinsQuery joinsQuery = new JoinsQuery();
        joinsQuery.displayJoins();
    }
}