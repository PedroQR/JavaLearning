public class Main {
    public static void main(String[] args) {
        GestaoAlunos gestao = new GestaoAlunos();

        try {
            // Criar alunos
            Aluno aluno1 = new Aluno("João", "20230001", "Engenharia");
            Aluno aluno2 = new Aluno("Maria", "20230002", "Medicina");

            // Adicionar alunos
            gestao.adicionarAluno(aluno1);
            gestao.adicionarAluno(aluno2);

            // Criar turmas
            Turma turma1 = new Turma("T01", "Matemática", 1);
            Turma turma2 = new Turma("T02", "Biologia", 2);

            // Matricular alunos
            gestao.matricularEmTurma(aluno1, turma1);
            gestao.matricularEmTurma(aluno2, turma2);

            // Salvar e carregar alunos
            gestao.salvarAlunosEmArquivo("alunos.dat");
            gestao.carregarAlunosDeArquivo("alunos.dat");

            // Exibir turmas
            System.out.println(turma1);
            System.out.println(turma2);

        } catch (AlunoInvalidoException | SemVagasException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
