package controllers;

import  static spark.SparkBase.staticFileLocation;
import static spark.Spark.get;

import db.DBHelper;
import db.Seeds;
import models.Employee;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EmployeeController {

    public static void main(String[] args) {
        staticFileLocation("public");
        ManagerController managerController = new ManagerController();
        EngineerController engineerController = new EngineerController();
        DepartmentController departmentController = new DepartmentController();


        Seeds.seedData();
        get("/employees", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Employee> employees = DBHelper.getAll(Employee.class);
            model.put("template", "templates/employees/index.vtl");
            model.put("employees", employees);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }
}
