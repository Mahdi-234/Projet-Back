package com.Mahdi.hadiji.Service;

import com.Mahdi.hadiji.Entite.Grade;
import com.Mahdi.hadiji.Repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<String> getAllGradeNames() {
        List<Grade> grades = gradeRepository.findAll();
        return grades.stream()
                     .map(Grade::getGrade)  // Extraire uniquement le nom du grade
                     .collect(Collectors.toList());
    }
    
    
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();  // Assuming you are using JpaRepository to fetch all grades
    }
    public List<Grade> getAllGrade() {
        return gradeRepository.findAll();
    }

    
}