package com.senai.olamundo.controller;

import com.senai.olamundo.OlamundoApplication;
import com.senai.olamundo.model.Contato;
import com.senai.olamundo.service.ContatoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/olamundo")
public class OlaMundoController {

    private final ContatoService contatoService;

    public OlaMundoController(ContatoService contatoService){
        this.contatoService = contatoService;
    }


    @GetMapping
    public List<Contato> olaMundo(){
        List<Contato> contatos = new ArrayList<>();
        try{
            contatos = contatoService.obterContatos();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return contatos;
    }

    @PostMapping
    public Contato postContato(
        @RequestBody Contato contato
    ) throws SQLException {

        try {
            contato = contatoService.cadastrarContato(contato);
        }catch (SQLException erro){
            erro.printStackTrace();
        }

        return contato;
    }

    @DeleteMapping("/{id}")
    public String deletarContato(@PathVariable int id) throws SQLException{
        contatoService.excluirContato(id);
        return "Contato Deletado";
    }

    @PutMapping("/{id}")
    public Contato atualizarContato(
            @PathVariable int id,
            @RequestBody Contato contato
    ) {
        SQLException exception = new SQLException();

        try {
            contato.setId(id);
            contatoService.atualizarContato(id, contato);

            return contato;

        }catch (SQLException error){
            throw new RuntimeException(exception.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Contato buscarUsuarioPorId(@PathVariable int id) {
        try {
            return contatoService.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
