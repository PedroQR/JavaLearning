import java.util.ArrayList;
import java.util.List;

public class Turma {
    private String codigo;
    private String disciplina;
    private int vagasDisponiveis;
    private List<Aluno> alunosMatriculados;

    public Turma(String codigo, String disciplina, int vagasDisponiveis) {
        this.codigo = codigo;
        this.disciplina = disciplina;
        this.vagasDisponiveis = vagasDisponiveis;
        this.alunosMatriculados = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(int vagasDisponiveis) {
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public void matricularAluno(Aluno aluno) throws SemVagasException {
        if (vagasDisponiveis <= 0) {
            throw new SemVagasException("Não há vagas disponíveis na turma " + codigo);
        }
        alunosMatriculados.add(aluno);
        vagasDisponiveis--;
    }

    @Override
    public String toString() {
        return String.format("Turma: %s, Disciplina: %s, Vagas: %d, Alunos: %s",
                codigo, disciplina, vagasDisponiveis, alunosMatriculados);
    }
}
