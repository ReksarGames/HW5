package controller;

import com.danit.springrest.model.Employer;
import com.danit.springrest.service.EmployerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employers")
public class EmployerRestControllerTest {

    private final EmployerService employerService;

    @Autowired
    public EmployerRestControllerTest(EmployerService employerService) {
        this.employerService = employerService;
    }

    @PostMapping("createEmployer")
    public ResponseEntity<Void> createEmployer(@RequestBody Employer newEmployer) {
        employerService.createEmployer(newEmployer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/getEmployer")
    public Page<Employer> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return employerService.getAllEmployers(pageRequest);
    }

    @PostMapping("/createManual")
    public ResponseEntity<Void> createEmployerManual(
            @RequestParam String employeeName,
            @RequestParam String employeeAddress) {
        employerService.createEmployerManual(employeeName, employeeAddress);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
