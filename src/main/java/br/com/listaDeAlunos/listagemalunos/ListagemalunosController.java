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
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
@RestController
@RequestMapping("/listagemalunos")
public class ListagemalunosController {

    
    private final List<Alunos> alunosLista;

    public ListagemalunosController() {
        this.alunosLista = new ArrayList<>();
        

    }

    @GetMapping
    public List<Alunos> findAllStd(@RequestParam(required = false) String nome,@RequestParam(required = false) Integer idade) {
        if(nome != null) {
            return alunosLista.stream()
                            .filter(std-> std.getNome().contains(nome))
                            .collect(Collectors.toList());


        }
        if(idade != null) {
            return alunosLista.stream()
                              .filter(std -> std.getIdade()
                              .toString()
                              .contains(idade.toString()))
                              .collect(Collectors.toList());
                              

        }
        return alunosLista;
    }


    @GetMapping("/{id}")
    public Alunos findById(@PathVariable("id") Integer id) {
        return this.alunosLista.stream()
                                .filter(std -> std.getId().equals(id))
                                .findFirst()
                                .orElse(null);
        
    }


    @PostMapping
    public ResponseEntity<Integer> addStudent(@RequestBody final Alunos alunos) {
        if(alunos.getId() == null) {

            alunos.setId(alunosLista.size() + 1);

        }
        alunosLista.add(alunos);
        return new ResponseEntity<>(alunos.getId(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity uptadeStudents(@RequestBody final Alunos alunos) {

        alunosLista.stream()
                .filter(std -> std.getId().equals(alunos.getId()))
                .forEach(std -> std.setNome(alunos.getNome()));

        alunosLista.stream()
                    .filter(std -> std.getId().equals(alunos.getId()))
                    .forEach(std -> std.setIdade(alunos.getIdade()));

               return new ResponseEntity(HttpStatus.NO_CONTENT);     
                
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {

        alunosLista.removeIf(std -> std.getId().equals(id));

        return new ResponseEntity(HttpStatus.NO_CONTENT);  

    }
    
}
