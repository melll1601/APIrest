package com.senai.olamundo.repository;

import com.senai.olamundo.model.Contato;
import com.senai.olamundo.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContatoRepository {
    public List<Contato> obterContatos() throws SQLException {
        List<Contato> contatos = new ArrayList<>();

        String query = """
                SELECT id
                       ,nome
                       ,numero
                FROM contato
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                contatos.add(new Contato(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("numero")
                ));
            }
        }
        return contatos;
    }

    public void cadastrarContato(Contato contato) throws SQLException {

        String query = """
                INSERT INTO contato
                (nome, numero)
                VALUES
                (?, ?)
                """;

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){


            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getNumero());
            stmt.executeUpdate();

        }catch (SQLException error){
            error.printStackTrace();
        }
    }
}
