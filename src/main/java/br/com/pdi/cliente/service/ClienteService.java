package br.com.pdi.cliente.service;

import br.com.pdi.cliente.entity.Cliente;
import br.com.pdi.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente save(Cliente cliente){
        cliente.setDataCadastro(new Date());
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> findById (int id){
        return clienteRepository.findById(id);
    }

    public void deleteById (int id){
        clienteRepository.deleteById(id);
    }

    public Cliente update(int id, Cliente cliente){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
         if (clienteOptional.isPresent()){
             Cliente clienteAtual = clienteOptional.get();
             clienteAtual.setCpf(cliente.getCpf());
             clienteAtual.setNome(cliente.getNome());

             clienteRepository.save(clienteAtual);

             return clienteAtual;
         }
            throw new IllegalArgumentException(String.format("Id %s não encontrado", id));
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }
}
