package com.Mahdi.hadiji.Controller;

import com.Mahdi.hadiji.Entite.Grade;
import com.Mahdi.hadiji.Service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@CrossOrigin(origins = "http://localhost:3000")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping
    public List<String> getAllGradeNames() {
        return gradeService.getAllGradeNames();  // Renvoyer uniquement les noms des grades
    }
    
    @GetMapping("/all")
    public List<Grade> getAllGrade() {
        return gradeService.getAllGrade();
    }
}