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

import ifsp.edu.source.DAL.DaoPessoa;
import ifsp.edu.source.DAL.DataBaseCom;
import ifsp.edu.source.Model.Pessoa;

@RestController
public class PessoaController {

    DataBaseCom database = new DataBaseCom();
    DaoPessoa cadPessoas = new DaoPessoa();

    @GetMapping(value = "/pessoa")
    public List<Pessoa> listar() {
        return cadPessoas.listar();
    }

    @GetMapping("/pessoa/{id}")
    public ResponseEntity<Pessoa> GetById(@PathVariable(value = "id") long id) {
        Pessoa pessoa = cadPessoas.findById(id);
        if (pessoa != null)
            return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/pessoa") // incluir
    public String Post(@Validated @RequestBody Pessoa pessoa) {
        cadPessoas.incluir(pessoa);
        return "Pessoa Cadastrada";
    }

    @PutMapping("/pessoa") // update
    public String Atualizar(@Validated @RequestBody Pessoa newPessoa) {
        cadPessoas.alterar(newPessoa);
        return "Pessoa atualizada";
    }

    @DeleteMapping("/pessoa/{id}") // delete
    public String Delete(@PathVariable(value = "id") long id) {
        cadPessoas.excluir(id);
        return "Exclusão realizada";
    }
}
