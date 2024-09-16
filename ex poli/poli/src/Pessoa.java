public class Pessoa{

    private String nome;
    private int matricula;
    private String email;

    public Pessoa(String nome, int matricula, String email ){
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public int getMatricula() {
        return matricula;
    }
    public String getNome() {
        return nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
}
