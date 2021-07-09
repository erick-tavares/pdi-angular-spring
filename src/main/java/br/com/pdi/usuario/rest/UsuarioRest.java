package br.com.pdi.usuario.rest;

import br.com.pdi.usuario.entity.Usuario;
import br.com.pdi.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioRest {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario){
        usuarioService.salvar(usuario);
    }
}
