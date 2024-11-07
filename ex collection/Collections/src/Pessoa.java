import java.time.LocalDate;
import java.util.Objects;

public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;
    private Genero genero; // Ensures the correct enum type is used    
    private float altura;
    private int peso;
    private Hobby hobby;
    private EstadoCivil estadoCivil;
    private Escolaridade escolaridade;

    public enum Genero { FEMININO, MASCULINO }
    public enum Hobby { ARTE, ESPORTE, CINEMA, LIVRO, MÚSICA, CULINÁRIA, GAME, NENHUM }
    public enum EstadoCivil { SOLTEIRO, CASADO, VIUVO, SEPARADO, DIVORCIADO }
    public enum Escolaridade { NENHUMA, FUNDAMENTAL, MEDIO, SUPERIOR, POS_GRADUACAO }

    public Pessoa(String nome, LocalDate dataNascimento, Genero genero, float altura, int peso, Hobby hobby, EstadoCivil estadoCivil, Escolaridade escolaridade) {        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.altura = altura;
        this.peso = peso;
        this.hobby = hobby;
        this.estadoCivil = estadoCivil;
        this.escolaridade = escolaridade;        
    }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }

    public float getAltura() { return altura; }
    public void setAltura(float altura) { this.altura = altura; }

    public int getPeso() { return peso; }
    public void setPeso(int peso) { this.peso = peso; }

    public Hobby getHobby() { return hobby; }
    public void setHobby(Hobby hobby) { this.hobby = hobby; }

   
    public EstadoCivil getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(EstadoCivil estadoCivil) { this.estadoCivil = estadoCivil; }

    public Escolaridade getEscolaridade() { return escolaridade; }
    public void setEscolaridade(Escolaridade escolaridade) {  this.escolaridade = escolaridade; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(nome, pessoa.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}

