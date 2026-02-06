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
    public String postContato(
        @RequestBody Contato contato
    ) throws SQLException {
        contatoService.cadastrarContato(contato);
        return "Usuário salvo com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deletarContato(@PathVariable int id) throws SQLException{
        contatoService.excluirContato(id);
        return "Contato Deletado";
    }

    @PutMapping("/{id}")
    public String atualizarContato(
            @PathVariable int id,
            @RequestBody Contato contato
    ) throws SQLException {

        contato.setId(id);
        contatoService.atualizarContato(contato);

        return "Usuário atualizado com sucesso";
    }
}
