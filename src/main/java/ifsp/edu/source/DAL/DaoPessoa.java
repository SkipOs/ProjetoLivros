package ifsp.edu.source.DAL;

import ifsp.edu.source.Model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class DaoPessoa {

    private static List<Pessoa> pessoas = new ArrayList<>();
    private static long nextId = 1;

    public List<Pessoa> listar() {
        return pessoas;
    }

    public Pessoa searchById(long id) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getId() == id) {
                return pessoa;
            }
        }
        return null;
    }

    public Pessoa incluir(Pessoa pessoa) {
        pessoa.setId(nextId++);
        pessoas.add(pessoa);
        return pessoa;
    }

    public void excluir(Pessoa pessoa) {
        pessoas.remove(pessoa);
    }
}
