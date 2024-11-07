import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class DatasetTest {

    private Dataset dataset;

    @Before
    public void setUp() {
        dataset = new Dataset();
    }

    @Test
    public void testAddAndRetrievePessoa() {
        Pessoa pessoa = new Pessoa("João", LocalDate.of(1990, 5, 20), Pessoa.Genero.MASCULINO, 1.75f, 70, Pessoa.Hobby.ESPORTE, Pessoa.EstadoCivil.SOLTEIRO, Pessoa.Escolaridade.SUPERIOR);
        
        // Adiciona uma pessoa e verifica se foi adicionada com sucesso
        assertTrue(dataset.addPessoa(pessoa));
        assertEquals(pessoa, dataset.getPessoaByName("João"));
    }

    @Test
    public void testRemovePessoa() {
        Pessoa pessoa = new Pessoa("Maria", LocalDate.of(1985, 8, 15), Pessoa.Genero.FEMININO, 1.65f, 60, Pessoa.Hobby.ARTE, Pessoa.EstadoCivil.CASADO, Pessoa.Escolaridade.MEDIO);

        dataset.addPessoa(pessoa);
        // Remove a pessoa e verifica se foi removida
        assertTrue(dataset.removePessoa(pessoa));
        assertNull(dataset.getPessoaByName("Maria"));
    }

    @Test
    public void testRemovePessoaByName() {
        Pessoa pessoa = new Pessoa("Carlos", LocalDate.of(1992, 3, 10), Pessoa.Genero.MASCULINO, 1.82f, 75, Pessoa.Hobby.GAME, Pessoa.EstadoCivil.SOLTEIRO, Pessoa.Escolaridade.SUPERIOR);
        
        dataset.addPessoa(pessoa);
        // Remove a pessoa pelo nome e verifica se foi removida
        assertTrue(dataset.removePessoaByName("Carlos"));
        assertNull(dataset.getPessoaByName("Carlos"));
    }

    @Test
    public void testReplacePessoa() {
        Pessoa pessoa1 = new Pessoa("Ana", LocalDate.of(1987, 9, 5), Pessoa.Genero.FEMININO, 1.70f, 65, Pessoa.Hobby.CINEMA, Pessoa.EstadoCivil.CASADO, Pessoa.Escolaridade.SUPERIOR);
        Pessoa pessoa2 = new Pessoa("Ana", LocalDate.of(1987, 9, 5), Pessoa.Genero.FEMININO, 1.72f, 66, Pessoa.Hobby.MUSICA, Pessoa.EstadoCivil.SOLTEIRO, Pessoa.Escolaridade.SUPERIOR);
        
        dataset.addPessoa(pessoa1);
        // Substitui a pessoa e verifica a substituição
        assertTrue(dataset.replacePessoa(pessoa1, pessoa2));
        assertEquals(pessoa2, dataset.getPessoaByName("Ana"));
    }

    @Test
    public void testAlturaMetrics() {
        dataset.addPessoa(new Pessoa("Alice", LocalDate.of(1995, 1, 10), Pessoa.Genero.FEMININO, 1.60f, 55, Pessoa.Hobby.MUSICA, Pessoa.EstadoCivil.SOLTEIRO, Pessoa.Escolaridade.SUPERIOR));
        dataset.addPessoa(new Pessoa("Bob", LocalDate.of(1990, 5, 25), Pessoa.Genero.MASCULINO, 1.80f, 85, Pessoa.Hobby.GAME, Pessoa.EstadoCivil.SOLTEIRO, Pessoa.Escolaridade.MEDIO));

        // Verifica as métricas de altura
        assertEquals(1.80f, dataset.maxAltura(), 0.01);
        assertEquals(1.60f, dataset.minAltura(), 0.01);
        assertEquals(1.70f, dataset.avgAltura(), 0.01);
    }

    @Test
    public void testPesoMetrics() {
        dataset.addPessoa(new Pessoa("Alice", LocalDate.of(1995, 1, 10), Pessoa.Genero.FEMININO, 1.60f, 55, Pessoa.Hobby.MUSICA, Pessoa.EstadoCivil.SOLTEIRO, Pessoa.Escolaridade.SUPERIOR));
        dataset.addPessoa(new Pessoa("Bob", LocalDate.of(1990, 5, 25), Pessoa.Genero.MASCULINO, 1.80f, 85, Pessoa.Hobby.GAME, Pessoa.EstadoCivil.SOLTEIRO, Pessoa.Escolaridade.MEDIO));

        // Verifica as métricas de peso
        assertEquals(85, dataset.maxPeso());
        assertEquals(55, dataset.minPeso());
        assertEquals(70, dataset.avgPeso(), 0.01);
    }
}
