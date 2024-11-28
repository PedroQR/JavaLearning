


public class Aluno {
    
    private String nome;
    private String matricula;
    private String curso;

    public Aluno(String nome, String matricula, String curso){
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;

    }

    public String getNome(){
        return nome;
    }

    public String getMatricula(){
        return matricula;
    }
    
    public String getCurso(){
        return curso;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setMatricula(String matricula){
        this.matricula = matricula;
 
    }

    public void setCurso(String curso){
        this.curso = curso;
    }
}

    // Método toString para exibir uma representação textual do aluno
    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", curso='" + curso + '\'' +
                '}';
    }

    // Método principal para teste
    public static void main(String[] args) {
        Aluno aluno = new Aluno("João Silva", "20240001", "Engenharia de Software");
        System.out.println(aluno);

        aluno.setCurso("Ciência da Computação");
        System.out.println("Curso atualizado: " + aluno.getCurso());
    }
}
