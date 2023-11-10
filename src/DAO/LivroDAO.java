package DAO;
import Domain.Autor;
import Domain.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LivroDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Livro livro){


        String sql = "INSERT INTO livro(nome, genero, ano_lançamento, nome_autor) VALUES(?,?,?,?)";

        try {
            System.out.println("inserir");
            PreparedStatement stmt = connection.prepareStatement(sql);
            //stmt.setInt(1, livro.getIdLivro());
            stmt.setString(1, livro.getNome());
            stmt.setString(2, livro.getGenero());
            stmt.setInt(3, livro.getAno());
            stmt.setString(4, livro.getAutor());
            stmt.execute();
            return true;
        }
        catch (SQLException ex){

            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception no inserir do LivroDAo");
            return false;
        }

    }

    public boolean remover(int id){

        String sql = "DELETE FROM livro WHERE id_livro=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        }
        catch (SQLException ex){
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception no remover do LivroDAO");
            return false;
        }
    }

    public boolean alterar(Livro livro){

        String sql = "UPDATE livro SET nome=?, genero=?, ano_lançamento=?, nome_autor=? WHERE id_livro=?";

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, livro.getNome());
            stmt.setString(2, livro.getGenero());
            stmt.setInt(3, livro.getAno());
            stmt.setString(4, livro.getAutor());
            stmt.setInt(5, livro.getIdLivro());
            stmt.execute();
            return true;
        }
        catch (SQLException ex){

            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception no alterar do LivroDAO");
            return false;
        }
    }

    public List<Livro> listar() {
        String sql = "SELECT * FROM livro";
        List<Livro> listLivro = new ArrayList<>();
        try {
            System.out.println("ENtrou no listar");
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {

                Livro livro = new Livro();
                livro.setIdLivro(resultado.getInt("id_livro"));
                livro.setNome(resultado.getString("nome"));
                livro.setGenero(resultado.getString("genero"));
                livro.setAno(resultado.getInt("ano_lançamento"));
                livro.setAutor(resultado.getString("nome_autor"));
                listLivro.add(livro);

                System.out.println("-----------------------");
                System.out.println("ID: " + livro.getIdLivro());
                System.out.println("Nome: " + livro.getNome());
                System.out.println("Gênero: " + livro.getGenero());
                System.out.println("Ano de lançamento: " + livro.getAno());
                System.out.println("Nome do autor: " + livro.getAutor());
                System.out.println("-----------------------");
            }
        }

        catch (SQLException ex){
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception no listar do LIVRODAO");
        }
        return listLivro;
    }

    public List<Livro> listarBuscar(String var){
        String sql = "SELECT * FROM livro WHERE id_livro LIKE ? OR nome LIKE ? OR genero LIKE ? OR ano_lançamento LIKE ? OR nome_autor LIKE ?";
        List<Livro> listLivro = new ArrayList<>();

        try {
            System.out.println("ENtrou no listar");
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + var + "%");
            stmt.setString(2, "%" + var + "%");
            stmt.setString(3, "%" + var + "%");
            stmt.setString(4, "%" + var + "%");
            stmt.setString(5, "%" + var + "%");
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()){

                Livro livro = new Livro();
                livro.setIdLivro(resultado.getInt("id_livro"));
                livro.setNome(resultado.getString("nome"));
                livro.setGenero(resultado.getString("genero"));
                livro.setAno(resultado.getInt("ano_lançamento"));
                livro.setAutor(resultado.getString("nome_autor"));
                listLivro.add(livro);

                    System.out.println("-----------------------");
                    System.out.println("ID: " + livro.getIdLivro());
                    System.out.println("Nome: " + livro.getNome());
                    System.out.println("Gênero: " + livro.getGenero());
                    System.out.println("Ano de lançamento: " + livro.getAno());
                    System.out.println("Nome do autor: " + livro.getAutor());
                    System.out.println("-----------------------");
            }
        }
        catch (SQLException ex){
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception no listar do LivroDAO");
        }
        return listLivro;
    }

    public Livro retornarLivro(int idLivro){
        String sql = "SELECT * FROM livro WHERE id_livro = ? ";

        try {
            System.out.println("ENtrou no listar");
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idLivro);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()){

                Livro livro = new Livro();
                livro.setIdLivro(resultado.getInt("id_livro"));
                livro.setNome(resultado.getString("nome"));
                livro.setGenero(resultado.getString("genero"));
                livro.setAno(resultado.getInt("ano_lançamento"));
                livro.setAutor(resultado.getString("nome_autor"));
                return livro;
            }
        }
        catch (SQLException ex){
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception no listar do LivroDAO");
        }
        return null;
    }
}
