package br.com.pdi.servico;

import br.com.pdi.cliente.Cliente;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 150)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column
    private double valor;
}
