package ifsp.edu.source.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ifsp.edu.source.DAL.DaoCompra;
import ifsp.edu.source.DAL.DaoItensCompra;
import ifsp.edu.source.DAL.DataBaseCom;
import ifsp.edu.source.Model.Compra;
import ifsp.edu.source.Model.ItensCompra;

@RestController
public class VendaController {

    DataBaseCom database = new DataBaseCom();
    DaoCompra cadCompras = new DaoCompra();
    DaoItensCompra cadItensCompra = new DaoItensCompra();

    @GetMapping(value = "/venda")
    public List<Compra> listarVendas() {
        // Implementar lógica para listar vendas
        return cadCompras.listar();
    }

    @GetMapping("/venda/{id}")
    public ResponseEntity<Compra> GetVendaById(@PathVariable(value = "id") long id) {
        Compra venda = cadCompras.findById(id);
        if (venda != null)
            return new ResponseEntity<Compra>(venda, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/venda") // incluir
    public String PostVenda(@Validated @RequestBody Compra venda) {
        // Implementar lógica para incluir venda
        cadCompras.incluir(venda);

        // Exemplo: Adicionar itens da venda ao banco
        for (ItensCompra item : venda.getItens()) {
            cadItensCompra.incluir(item);
        }

        return "Venda Realizada";
    }

    @PutMapping("/venda") // update
    public String AtualizarVenda(@Validated @RequestBody Compra newVenda) {
        // Implementar lógica para atualizar venda
        cadCompras.alterar(newVenda);
        return "Venda atualizada";
    }

    @DeleteMapping("/venda/{id}") // delete
    public String DeleteVenda(@PathVariable(value = "id") long id) {
        // Implementar lógica para excluir venda
        cadCompras.excluir(id);
        return "Exclusão realizada";
    }
}
