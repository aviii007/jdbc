package com.bridgelabz.jdbc;

/**
 * @author : Ashvini Kanojia
 * @Since :18-04-2022
 * @purpose : Jdbc
 */

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class DBDemo {
    public static void main(String[] args) {
        System.out.println("Welcome to the employee-payroll mysql");

        String jdbcURL = "jdbc:mysql://localhost:3306/employeepayroll?useSSL=false";

        /**
         * The default username for the mysql database is root.
         */
        String userName = "root";

        /**
         * It is the password given by the user at the time of installing the mysql
         * database
         */
        String password = "ashvini26007";
        Connection connection;

        /**
         * try and catch block to handle the exceptions
         */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find driver in classpath");
        }
        listDrivers();
        try {
            System.out.println("Connecting to database: " + jdbcURL);
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection successful: " + connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * created method listDrivers() to List the drivers being loaded
     */
    private static void listDrivers() {

        /**
         * Retrieving the list of all the Drivers
         */
        Enumeration<Driver> driverList = DriverManager.getDrivers();

        /**
         * Printing the list
         */
        while (driverList.hasMoreElements()) {
            Driver driverClass = (Driver) driverList.nextElement();
            System.out.println("   " + driverClass.getClass().getName());
        }
    }
}