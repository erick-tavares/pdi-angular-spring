package br.com.pdi.servico.service;

import br.com.pdi.cliente.entity.Cliente;
import br.com.pdi.servico.entity.ServicoPrestado;
import br.com.pdi.servico.repository.ServicoPrestadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoPrestadoService {

    @Autowired
    private ServicoPrestadoRepository servicoPrestadoRepository;

    public ServicoPrestado save(ServicoPrestado servicoPrestado){
      //  servicoPrestado.setData(new LocalDate());
        return servicoPrestadoRepository.save(servicoPrestado);
    }

    public Optional<ServicoPrestado> findById (int id){
        return servicoPrestadoRepository.findById(id);
    }

    public void deleteById (int id){
        servicoPrestadoRepository.deleteById(id);
    }

    public ServicoPrestado update(int id, ServicoPrestado servicoPrestado){
        Optional<ServicoPrestado> servicoOptional = servicoPrestadoRepository.findById(id);
        if (servicoOptional.isPresent()){
            ServicoPrestado servicoAtual = servicoOptional.get();
//            servicoAtual.setCpf(servicoOptional.getCpf());
//            servicoAtual.setNome(servicoOptional.getNome());

            servicoPrestadoRepository.save(servicoAtual);

            return servicoAtual;
        }
        throw new IllegalArgumentException(String.format("Id %s não encontrado", id));
    }

    public List<ServicoPrestado> findAll(){
        return servicoPrestadoRepository.findAll();
    }

    public List<ServicoPrestado> buscarPorNomeEMes(String nome, int mes) {
        return servicoPrestadoRepository.findByNomeClienteAndMes(nome, mes);
    }
}
