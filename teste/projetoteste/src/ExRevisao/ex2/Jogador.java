package teste.projetoteste.src.ExRevisao.ex2;

public class Jogador {
    private String nome;
    private int id = 0 ;

    public Jogador (String nome){
        this.nome = nome;
        id = id + 1;
    }
    public String getNome(){
        return nome;
    }
}
