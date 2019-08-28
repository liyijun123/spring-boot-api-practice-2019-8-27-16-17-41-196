    
package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private static List<Employee> employeelist = new ArrayList<Employee>(){{
     add(new Employee(1,"张三",18,"女",10000));
        add(new Employee(2,"李四",19,"男",20000));
        add(new Employee(3,"王五",20,"女",20000));
        add(new Employee(4,"小明",21,"女",20000));
        add(new Employee(5,"小赵",22,"女",20000));
    }};
    private static List<Company> list = new ArrayList<Company>(){{
        add(new Company(1,"中原银行",1000,employeelist));
        add(new Company(2,"郑州银行",500,employeelist));
    }};

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies() {
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable int id) {
        Company comp = null;
        for(Company company:list){
            if(company.getId() == id){
                comp = company;
            }
        }
        return ResponseEntity.ok(comp);
    }
    @GetMapping("/{id}/employees")
    public ResponseEntity<List<Employee>> getEmployees(@PathVariable int id) {
        Company comp = null;
        for(Company company:list){
            if(company.getId() == id){
                comp = company;
            }
        }
        return ResponseEntity.ok(comp.getEmployeeList());
    }
    @GetMapping("/")
    public ResponseEntity<List<Company>> getCompaniesBypage(@RequestParam int page,@RequestParam int pageSize) {
        if(page != 0 && pageSize !=0){
            List<Company> companyList = new ArrayList<>();
            for(int i = page-1;i<pageSize;i++){
                companyList.add(list.get(i));
            }
            return ResponseEntity.ok(companyList);
        }
        return ResponseEntity.ok(list);
    }
    @PostMapping
    public ResponseEntity<List<Company>> addComplany(@RequestBody Company company) {
        list.add(company);
        return ResponseEntity.ok(list);
    }
    @PutMapping("/{id}")
    public ResponseEntity<List<Company>> updateComplany(@PathVariable int id,@RequestBody Company company) {
        Company com = null;
        for(Company company1:list){
            if(company1.getId() == id){
                com = company1;
            }
        }
        list.remove(com);
        list.add(company);
        return ResponseEntity.ok(list);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Company>> updateEmployee(@PathVariable int id) {
        Company com = null;
        for(Company company:list){
            if(company.getId() == id){
                com = company;
            }
        }
        list.remove(com);
        return ResponseEntity.ok(list);
    }
}