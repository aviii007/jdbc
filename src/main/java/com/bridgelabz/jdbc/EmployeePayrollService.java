package com.bridgelabz.jdbc;

/**
 * import arraylist class
 * import list class
 * import scanner class
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * create a class name as EmployeePayrollService
 */
public class EmployeePayrollService {

    /**
     * creating a enum class.
     * Enums can be thought of as classes which have a fixed set of constants (a variable that does not change).
     * The enum constants are static and final implicitly
     */
    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO
    }

    /**
     * create a list
     */
    public List<EmployeePayrollData> employeePayrollList;
    private EmployeePayrollDBService employeePayrollDBService;

    /**
     * create a default constructor name as EmployeePayrollService
     */
    public EmployeePayrollService() {
        employeePayrollDBService = EmployeePayrollDBService.getInstance();
    }

    /**
     * create a parameterized constructor name as EmployeePayrollService
     */
    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
        this();
        this.employeePayrollList = employeePayrollList;
    }

    /**
     * create a method name as readEmployeeData
     * @param consoleInputReader
     */
    public void readEmployeeData(Scanner consoleInputReader) {
        System.out.println("Enter employee ID : ");
        int id = Integer.parseInt(consoleInputReader.nextLine());
        System.out.println("Enter employee name : ");
        String name = consoleInputReader.nextLine();
        System.out.println("Enter employee salary : ");
        double salary = Double.parseDouble(consoleInputReader.nextLine());
        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
    }

    /**
     * create a method name as writeEmployeeData
     * @param ioService
     */
    public void writeEmployeeData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
            System.out.println("Employee Payroll Data to Console\n" + employeePayrollList);
        else if (ioService.equals(IOService.FILE_IO))
            new EmployeePayrollFileIOService().writeData(employeePayrollList);
    }
    /**
     * create a method name as printData,
     * this is parameterized method
     * @param ioService
     */
    public void printData(IOService ioService) {
        new EmployeePayrollFileIOService().printData();
    }

    /**
     * create  a method name as countEntries,the method type is long
     * @param ioService
     * @return enteries
     */
    public long countEntries(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            return new EmployeePayrollFileIOService().countEntries();
        return 0;
    }

    /**
     * create a method name as readEmployeepayrollData,this is parameterized method
     * create a list ,here EmployeePayrollData is class
     * @param ioService
     * @return read data
     */
    public List<EmployeePayrollData> readEmployeepayrollData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            return new EmployeePayrollFileIOService().readData();
        else if (ioService.equals(IOService.DB_IO))
            return new EmployeePayrollDBService().readData();
        else
            return null;
    }

    /**
     * create a method name as updateEmployeeSalary
     * @param name of employee
     * @param salary employee
     * @throws EmployeePayrollException
     * @throws NullPointerException
     */
    public void updateEmployeeSalary(String name, double salary) throws EmployeePayrollException {
        int result = employeePayrollDBService.updateEmployeeData(name, salary);
        if (result == 0)
            throw new EmployeePayrollException(Exception.DATA_NULL, "No data update is failed");
        EmployeePayrollData employeePayrollData = this.getEmployee_payroll_Data(name);
        if (employeePayrollData != null)
            employeePayrollData.salary = salary;
    }

    /**
     * create a parameterized method name as getEmployee_payroll_Data
     * @param name of employee
     * @return employeePayrollData
     */
    private EmployeePayrollData getEmployee_payroll_Data(String name) {
        EmployeePayrollData employeePayrollData;
        employeePayrollData = employeePayrollList.stream().filter(emp_Data -> emp_Data.name.equals(name)).findFirst()
                .orElse(null);
        return employeePayrollData;
    }

    /**
     * create a method name as checkEmployeePayrollInSyncWithDB,
     * this is parameterized method
     * @param name of employee
     * @return name of employee
     */
    public boolean checkEmployeePayrollInSyncWithDB(String name) {
        List<EmployeePayrollData> employeePayrollDataList = employeePayrollDBService.getEmployeePayrollData(name);
        return employeePayrollDataList.get(0).equals(getEmployee_payroll_Data(name));
    }


    /**
     * create a main method,all program execute in main method
     * @param args no arguments,its default.
     */
    public static void main(String[] args) {
        /**
         * create a list object name as  employeePayrollList
         * here EmployeePayrollData is a class.
         */
        List<EmployeePayrollData> employeePayrollList = new ArrayList<EmployeePayrollData>();
        /**
         * create a object for  EmployeePayrollService class ,object name as employeePayrollService
         */
        EmployeePayrollService employeePayrollService = new   EmployeePayrollService(employeePayrollList);
        /**
         * create a scanner class object name as object is consoleInputReader
         */
        Scanner consoleInputReader = new Scanner(System.in);
        /**
         * calling readEmployeeData method from employeePayrollService object
         */
        employeePayrollService.readEmployeeData(consoleInputReader);
        /**
         * calling writeEmployeeData method from employeePayrollService object
         */
        employeePayrollService.writeEmployeeData(IOService.CONSOLE_IO);
    }


}