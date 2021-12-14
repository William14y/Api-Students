package br.com.listaDeAlunos.listagemalunos;

public class Alunos {


    private Integer id;
    private String nome;
    private Integer idade;

    public Alunos(final Integer id, final String nome, final Integer idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    public Integer getId() {
        return id;
    }
    public void setId(final Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(final Integer idade) {
        this.idade = idade;
    }
    
}
