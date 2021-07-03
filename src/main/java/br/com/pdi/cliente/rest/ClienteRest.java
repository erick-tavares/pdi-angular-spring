package br.com.pdi.cliente.rest;

import br.com.pdi.cliente.entity.Cliente;
import br.com.pdi.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("*")
public class ClienteRest {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar (@RequestBody @Validated Cliente cliente){
        return clienteService.save(cliente);
    }

    @GetMapping("{id}")
    public Optional<Cliente> buscarPorId(@PathVariable int id){
        return clienteService.findById(id);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable int id){
        clienteService.deleteById(id);
    }

    @PutMapping("{id}")
    public Cliente atualizar (@PathVariable @Validated int id, @RequestBody @Validated Cliente clienteAtualizado){
        return clienteService.update(id, clienteAtualizado);
    }

    @GetMapping
    public List<Cliente> buscarTodos (){
        return clienteService.findAll();
    }

}
