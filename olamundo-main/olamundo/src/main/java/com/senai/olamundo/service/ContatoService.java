package com.senai.olamundo.service;

import com.senai.olamundo.model.Contato;
import com.senai.olamundo.repository.ContatoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository){
        this.contatoRepository = contatoRepository;
    }

    public List<Contato> obterContatos() throws SQLException {
        return contatoRepository.obterContatos();
    }

    public Contato cadastrarContato(Contato contato) throws SQLException{
        contatoRepository.cadastrarContato(contato);
        return contato;
    }
}
