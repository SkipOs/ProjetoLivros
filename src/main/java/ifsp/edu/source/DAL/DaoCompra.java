package ifsp.edu.source.DAL;

import ifsp.edu.source.Model.Compra;

import java.util.ArrayList;
import java.util.List;

public class DaoCompra {

    private static List<Compra> compras = new ArrayList<>();
    private static long nextId = 1;

    public List<Compra> listar() {
        return compras;
    }

    public Compra searchCompraById(long id) {
        for (Compra compra : compras) {
            if (compra.getId() == id) {
                return compra;
            }
        }
        return null;
    }

    public boolean incluir(Compra compra) {
        // Verifica se é uma inclusão ou atualização
        if (compra.getId() == 0) {
            compra.setId(nextId++);
            compras.add(compra);
        } else {
            // Atualiza a compra existente
            int index = compras.indexOf(compra);
            if (index != -1) {
                compras.set(index, compra);
            }
        }
        return true;
    }

    public void excluir(Compra compra) {
        compras.remove(compra);
    }
}
