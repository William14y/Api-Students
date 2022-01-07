package br.com.listaDeAlunos.listagemalunos.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.listaDeAlunos.listagemalunos.Alunos;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

public class AlunoService {
   private List<Alunos> students;

   public AlunoService() {
       this.students = new ArrayList<>();
   }

   public void addStudents(Alunos student){
       if(student.getId()==null) {
           student.setId(students.size()+1);
       }
       students.add(student);
       new ResponseEntity<>("O aluno "+student+" foi matriculado com sucesso!", HttpStatus.CREATED);
   }
   public List<Alunos> Estudantes (String nome) {
       return students;
   }
   public List<Alunos> findStudent(String nome) {
       List<Alunos> findName = students.stream()
                                       .filter(std -> std.getNome().contains(nome))
                                       .collect(Collectors.toList());
    if(findName.isEmpty()) {
        throw new AlunoNaoEncontradoException();
        }
        return findName;
   }
   public List<Alunos> findStudentID(Integer id) {
       List<Alunos> findId = students.stream()
                                     .filter(std -> std.getId().equals(id))
                                     .collect(Collectors.toList());
        if(findId.isEmpty()){
            throw new AlunoNaoEncontradoException();
        }
        return findId;
   }

   public void updateStudent(@RequestBody Alunos student) {
        students.stream()
                .filter(std -> std.getId().equals(student.getId()))
                .forEach(std -> std.setNome(student.getNome()));
        students.stream()
                .filter(std -> std.getId().equals(student.getId()))
                .forEach(std -> std.setIdade(student.getIdade()));
                      
   }
   public ResponseEntity withdrawStudent(Integer id) {
       students.removeIf(std -> std.getId().equals(id));
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

}
