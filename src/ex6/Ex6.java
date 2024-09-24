/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ex6;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Dell
 */
public class Ex6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Department dep = new Department();
        dep.setDepartmentid(2);
        dep.setName("HR");
        //persist(dep);
        
        Employee emp = new Employee();
        emp.setEmployeeid(4);
        emp.setName("Clark");
        emp.setJob("HR recruiter");
        emp.setSalary(36789);
        emp.setDepartmentid(dep);
        //persist(emp);
        
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex6PU");
        EntityManager em = emf.createEntityManager();

        // Fetch all employees
        List<Employee> employees = em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
        
        System.out.println("All employee (by ID)");
        System.out.println("---------------------------");
        for (Employee employee : employees) {
            System.out.println("ID: " + employee.getEmployeeid());
            System.out.println("Name: " + employee.getName());
            System.out.println("Job: " + employee.getJob());
            System.out.println("Salary: " + employee.getSalary());
            System.out.println("Department: " + employee.getDepartmentid().getName());
            System.out.println("---------------------------");
        }

        // Fetch all departments
        List<Department> departments = em.createNamedQuery("Department.findAll", Department.class).getResultList();
        
        System.out.println("All employee (by Department)");
        System.out.println("---------------------------");
        for (Department department : departments) {
            
            System.out.println("Department ID: " + department.getDepartmentid() + " Department Name: " + department.getName());
            System.out.println("---------------------------");
            for (Employee employee : department.getEmployeeCollection()) {
                System.out.println("ID: " + employee.getEmployeeid());
                System.out.println("Name: " + employee.getName());
                System.out.println("Job: " + employee.getJob());
                System.out.println("Salary: " + employee.getSalary());
                System.out.println("---------------------------");
            }
        }

        em.close();
        emf.close();
        
        
        
        
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex6PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
