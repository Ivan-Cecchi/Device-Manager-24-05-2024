package device.manager.controllers;

import device.manager.dtos.EmployeeDTO;
import device.manager.entities.Employee;
import device.manager.exceptions.BadRequestException;
import device.manager.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody @Validated EmployeeDTO employeeDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid data", validation.getAllErrors());
        }
        return employeeService.createEmployee(employeeDTO);
    }

    @GetMapping
    public Page<Employee> getEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "username") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return employeeService.getEmployees(pageable);
    }

    @GetMapping("{id}")
    public Employee getEmployee(@PathVariable UUID id) {
        return employeeService.getEmployee(id);
    }


    @PutMapping("{id}")
    public Employee updateEmployee(@PathVariable UUID id, @RequestBody @Validated EmployeeDTO employeeDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid data", validation.getAllErrors());
        }
        return employeeService.updateEmployee(id, employeeDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
    }


    @PutMapping("{id}/photo")
    public Employee updateEmployeePhoto(@PathVariable UUID id, @RequestParam("photo") MultipartFile photo) throws IOException {
        return employeeService.updateEmployeePhoto(id, photo);
    }

}
