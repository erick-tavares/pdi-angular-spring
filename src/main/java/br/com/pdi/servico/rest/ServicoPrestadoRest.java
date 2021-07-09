package br.com.pdi.servico.rest;

import br.com.pdi.cliente.entity.Cliente;
import br.com.pdi.cliente.service.ClienteService;
import br.com.pdi.servico.entity.ServicoPrestado;
import br.com.pdi.servico.entity.dto.ServicoPrestadoDTO;
import br.com.pdi.servico.service.ServicoPrestadoService;
import br.com.pdi.util.ConverterBigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoRest {

    private final ClienteService clienteService;
    private final ServicoPrestadoService servicoPrestadoService;
    private final ConverterBigDecimal converterBigDecimal;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar (@RequestBody @Valid ServicoPrestadoDTO dto){
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        int idCliente = dto.getIdCliente();

        Cliente cliente = clienteService.findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente"));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(converterBigDecimal.converter(dto.getPreco()));

        return servicoPrestadoService.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) int mes
            ){
        return servicoPrestadoService.buscarPorNomeEMes( "%" + nome + "%" , mes);
    }

}
