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
import ifsp.edu.source.DAL.DataBaseCom;
import ifsp.edu.source.Model.Compra;

@RestController
public class CompraController {

    DataBaseCom database = new DataBaseCom();
    DaoCompra cadCompras = new DaoCompra();

    @GetMapping(value = "/compra")
    public List<Compra> listar() {
        return cadCompras.listar();
    }

    @GetMapping("/compra/{id}")
    public ResponseEntity<Compra> GetById(@PathVariable(value = "id") long id) {
        Compra compra = cadCompras.findById(id);
        if (compra != null)
            return new ResponseEntity<Compra>(compra, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/compra") // incluir
    public String Post(@Validated @RequestBody Compra compra) {
        cadCompras.incluir(compra);
        return "Compra Cadastrada";
    }

    @PutMapping("/compra") // update
    public String Atualizar(@Validated @RequestBody Compra newCompra) {
        cadCompras.alterar(newCompra);
        return "Compra atualizada";
    }

    @DeleteMapping("/compra/{id}") // delete
    public String Delete(@PathVariable(value = "id") long id) {
        cadCompras.excluir(id);
        return "Exclusão realizada";
    }
}
