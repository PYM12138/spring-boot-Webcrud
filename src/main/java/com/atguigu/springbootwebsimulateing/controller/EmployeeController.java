package com.atguigu.springbootwebsimulateing.controller;

import com.atguigu.springbootwebsimulateing.dao.DepartmentDao;
import com.atguigu.springbootwebsimulateing.dao.EmployeeDao;
import com.atguigu.springbootwebsimulateing.pojo.Department;
import com.atguigu.springbootwebsimulateing.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.DateUtils;
import org.thymeleaf.util.StringUtils;


import java.beans.PropertyEditorSupport;
import java.util.Collection;
import java.util.Date;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String Emps(Model model){
        //查询全部员工
        //本来要写service的，这边直接简化了
        Collection<Employee> emps = employeeDao.getAll();
        //获取到了数据，放到请求域 Model Map ModelAndView 都行
        model.addAttribute("emps", emps);

        return "emp/list";
    }
    @GetMapping("/emp")  //新增添加页面 /emp--get
    public String addEmpPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    @PostMapping("/emp") //添加员工用 /emp--post
    public String addEmp(Employee employee){
        //根据spring MVC ,表单传过来的值会自动匹配属性。我记得是这样
        System.out.println("employee.toString()==== = " + employee.toString());
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //添加数据需要回显,根据要查询的ID来来获取对应的数据
    @GetMapping("/emp/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("emp", employee);
        model.addAttribute("depts", departments);
        return "emp/add";
    }



    //提交的数据添加到之前的数据中


    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        System.out.println("employee.toString() =11111 "+ employee.toString());
        System.out.println("!");
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable Integer id){
        employeeDao.delete(id);
//        System.out.println("我是delete");
        return "redirect:/emps";
    }




}
