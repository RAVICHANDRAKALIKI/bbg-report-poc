package dev.ravi.bbgreport;

import java.sql.Date;

public class Employee {

    private int emp_id;
    private String first_name;
    private String last_name;
    private String gender;
    private Date birth_date;
    private Date hire_date;

    public Employee(int emp_id) {
        this.emp_id = emp_id;
    }

    public Employee(int emp_id, String first_name, String last_name, String gender, Date birth_date, Date hire_date) {
        this.emp_id = emp_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.birth_date = birth_date;
        this.hire_date = hire_date;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }
}
