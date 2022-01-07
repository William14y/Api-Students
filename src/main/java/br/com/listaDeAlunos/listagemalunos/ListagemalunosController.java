package br.com.listaDeAlunos.listagemalunos;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.listaDeAlunos.listagemalunos.exceptions.AlunoService;

import java.util.List;



@RestController
@RequestMapping("/listagemalunos")
public class ListagemalunosController {

    AlunoService service = new AlunoService();

     @GetMapping()
     public List<Alunos> findAll(@RequestParam(required = false) String nome, Integer id) {
         if(nome!=null) {
             return service.findStudent(nome);
         }
         if(id!=null){
             return service.findStudentID(id);
         }
         return service.Estudantes(nome);
     }
     @PostMapping
     public ResponseEntity<Integer> add(@RequestBody Alunos nome){
         service.addStudents(nome);
         return new ResponseEntity<>(HttpStatus.CREATED);
     }
     @PutMapping
     public ResponseEntity<Object> update(@RequestBody Alunos student){
         service.updateStudent(student);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
     @DeleteMapping("{id}")
     public ResponseEntity delete(@PathVariable("id") Integer id) {
         return service.withdrawStudent(id);
     }




    }
    

