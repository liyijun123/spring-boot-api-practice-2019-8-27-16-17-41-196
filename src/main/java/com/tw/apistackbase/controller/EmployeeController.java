    
package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private static List<Employee> list = new ArrayList<Employee>(){{
        add(new Employee(1,"张三",18,"女",10000));
        add(new Employee(2,"李四",19,"男",20000));
        add(new Employee(3,"王五",20,"女",20000));
        add(new Employee(4,"小明",21,"女",20000));
        add(new Employee(5,"小赵",22,"女",20000));
    }};

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
        Employee comp = null;
        for(Employee employee:list){
            if(employee.getId() == id){
                comp = employee;
            }
        }
        return ResponseEntity.ok(comp);
    }
    @GetMapping("/")
    public ResponseEntity<List<Employee>> getEmployeesBypage(@RequestParam int page, @RequestParam int pageSize) {
            List<Employee> employeeList = new ArrayList<>();
            for(int i = page-1;i<pageSize;i++){
                employeeList.add(list.get(i));
            }
            return ResponseEntity.ok(employeeList);

    }
    @GetMapping("/gender")
    public ResponseEntity<List<Employee>> getEmployeesByGender(@RequestParam String gender) {
        List<Employee> employeeList = new ArrayList<>();
        for(Employee e:list){
            if(gender.equals(e.getGender())){
                employeeList.add(e);
            }
        }
        return ResponseEntity.ok(employeeList);
    }
    @PostMapping
    public ResponseEntity<List<Employee>> addEmployee(@RequestBody Employee employee) {
        list.add(employee);
        return ResponseEntity.ok(list);
    }
    @PutMapping("/{id}")
    public ResponseEntity<List<Employee>> updateEmployee(@PathVariable int id,@RequestBody Employee employee) {
        Employee emp = null;
        for(Employee employee1:list){
            if(employee1.getId() == id){
                emp = employee1;
            }
        }
        list.remove(emp);
        list.add(employee);
        return ResponseEntity.ok(list);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Employee>> updateEmployee(@PathVariable int id) {
        Employee com = null;
        for(Employee employee:list){
            if(employee.getId() == id){
                com = employee;
            }
        }
        list.remove(com);
        return ResponseEntity.ok(list);
    }
}