package br.com.listaDeAlunos.listagemalunos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.listaDeAlunos.listagemalunos.exceptions.AlunoNaoEncontradoException;

@ControllerAdvice
public class GenericControllerAdvice {

    Logger LOGGER = LoggerFactory.getLogger(GenericControllerAdvice.class);

    @ExceptionHandler({AlunoNaoEncontradoException.class})
    public ResponseEntity<String> Exception(final AlunoNaoEncontradoException alnException){
            final String StudentNotFound = "O aluno não foi encontrado";
            LOGGER.error(StudentNotFound);
            return new ResponseEntity<>(StudentNotFound, HttpStatus.NOT_FOUND);
    }   
}
