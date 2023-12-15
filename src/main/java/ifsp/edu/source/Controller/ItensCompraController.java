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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifsp.edu.source.DAL.DaoItensCompra;
import ifsp.edu.source.Model.ItensCompra;

@RestController
@RequestMapping("/itens_compra")
public class ItensCompraController {

    private DaoItensCompra cadItensCompra = new DaoItensCompra();

    @GetMapping
    public List<ItensCompra> listar() {
        return cadItensCompra.listar();
    }

    @GetMapping("/{compraId}")
    public ResponseEntity<ItensCompra> getByIds(@PathVariable(value = "compraId") long compraId){
        ItensCompra itemCompra = cadItensCompra.findByIds(compraId);
        if (itemCompra != null)
            return new ResponseEntity<>(itemCompra, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public String postItensCompra(@Validated @RequestBody ItensCompra itensCompra) {
        cadItensCompra.incluir(itensCompra);
        return "Item Cadastrado";
    }

    @PutMapping
    public String putItensCompra(@Validated @RequestBody ItensCompra newItensCompra) {
        cadItensCompra.alterar(newItensCompra);
        return "Item Atualizado";
    }

    @DeleteMapping("/{compraId}")
    public String deleteItensCompra(@PathVariable(value = "compraId") long compraId,
                                    @PathVariable(value = "produtoId") long produtoId) {
        cadItensCompra.excluir(compraId);
        return "Exclusão realizada";
    }
}
