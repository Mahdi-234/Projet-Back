package com.Mahdi.hadiji.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Mahdi.hadiji.Entite.CodeUE;
import com.Mahdi.hadiji.Entite.Modalite;
import com.Mahdi.hadiji.Entite.TypeElement;

@RestController
@RequestMapping("/api/enums")
public class EnumController {

    @GetMapping("/codeUE")
    public CodeUE[] getCodeUEValues() {
        return CodeUE.values();
    }

    @GetMapping("/modaliteEval")
    public List<Modalite> getModaliteEvalValues() {
        return Arrays.asList(Modalite.values());
    }

    @GetMapping("/typeElement")
    public TypeElement[] getTypeElementValues() {
        return TypeElement.values();
    }
}
