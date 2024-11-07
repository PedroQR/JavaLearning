import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Dataset {
    // Atributos
    private List<Pessoa> pessoas = new ArrayList<>();
    public static final int MAX_PESSOAS = 1000;

    // Métodos de acesso
    public boolean addPessoa(Pessoa pessoa) {
        if (pessoas.size() < MAX_PESSOAS) {
            return pessoas.add(pessoa);
        }
        return false;
    }

    public boolean removePessoa(Pessoa pessoa) {
        return pessoas.remove(pessoa);
    }

    public boolean removePessoaByName(String nome) {
        return pessoas.removeIf(pessoa -> pessoa.getNome().equalsIgnoreCase(nome));
    }

    public boolean replacePessoa(Pessoa oldPessoa, Pessoa newPessoa) {
        int index = pessoas.indexOf(oldPessoa);
        if (index >= 0) {
            pessoas.set(index, newPessoa);
            return true;
        }
        return false;
    }

    public Pessoa getPessoaByName(String nome) {
        return pessoas.stream()
                      .filter(pessoa -> pessoa.getNome().equalsIgnoreCase(nome))
                      .findFirst()
                      .orElse(null);
    }

    public List<Pessoa> getAll() {
        List<Pessoa> sortedList = new ArrayList<>(pessoas);
        sortedList.sort(Comparator.comparing(Pessoa::getNome));
        return sortedList;
    }

    // Métodos de comportamento
    public int size() {
        return pessoas.size();
    }

    public float maxAltura() {
        return pessoas.stream()
                      .map(Pessoa::getAltura)
                      .max(Float::compare)
                      .orElse(0f);
    }

    public float minAltura() {
        return pessoas.stream()
                      .map(Pessoa::getAltura)
                      .min(Float::compare)
                      .orElse(0f);
    }

    public double avgAltura() {
        return pessoas.stream()
                      .mapToDouble(Pessoa::getAltura)
                      .average()
                      .orElse(0);
    }

    public int maxPeso() {
        return pessoas.stream()
                      .map(Pessoa::getPeso)
                      .max(Integer::compare)
                      .orElse(0);
    }

    public int minPeso() {
        return pessoas.stream()
                      .map(Pessoa::getPeso)
                      .min(Integer::compare)
                      .orElse(0);
    }

    public double avgPeso() {
        return pessoas.stream()
                      .mapToInt(Pessoa::getPeso)
                      .average()
                      .orElse(0);
    }
}
