package Pgsql_Join_Query.Controller;

import Pgsql_Join_Query.Model.Model;
import Pgsql_Join_Query.View.View;

public class Controller {
    private final Model model;
    private final View view;

    public Controller(Model model,View view) {
        this.model = model;
        this.view = view;
    }

    public void pgsqlTableDetails() {
        view.displayStudentDetails();
    }

    public void innerJoinsQuery() {
        view.innerJoin();
    }

    public void customizeInnerJoinsQuery() {
        view.customizeInnerJoin(model.getId());
    }

    public void leftJoinsQuery() {
        view.leftJoin();
    }

    public void rightJoinsQuery() {
        view.rightJoin();
    }

    public void outerJoinsQuery() {
        view.outerJoin();
    }
}