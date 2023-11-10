package DAO;

import Domain.Autor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutorDAO {
    private static Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Autor Autor) {


        String sql = "INSERT INTO autor(nome, nacionalidade) VALUES(?,?)";

        try {
            System.out.println("inserir");
            PreparedStatement stmt = connection.prepareStatement(sql);
            //stmt.setInt(1, Autor.getIdAutor());
            stmt.setString(1, Autor.getNome());
            stmt.setString(2, Autor.getNacionalidade());
            stmt.execute();
            return true;

        } catch (SQLException ex) {

            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception no inserir do AutorDAo");
            return false;
        }

    }

    public boolean remover(int id) {

        String sql = "DELETE FROM autor WHERE id_autor=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception no remover do AutorDAO");
            return false;

        }
    }

    public boolean alterar(Autor autor) {

        String sql = "UPDATE autor SET nome=?, nacionalidade=? WHERE id_autor=?";

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setInt(3, autor.getIdAutor());
            stmt.execute();
            return true;
        }
        catch (SQLException ex) {

            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception no alterar do AutorDAO");
            return false;
        }

    }

    public List<Autor> listar() {
        String sql = "SELECT * FROM autor";
        List<Autor> listAutor = new ArrayList<>();

        try {
            System.out.println("ENtrou no listar");
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {

                Autor autor = new Autor();
                autor.setIdAutor(resultado.getInt("id_autor"));
                autor.setNome(resultado.getString("nome"));
                autor.setNacionalidade(resultado.getString("nacionalidade"));
                listAutor.add(autor);

                System.out.println("-----------------------");
                System.out.println("ID: " + autor.getIdAutor());
                System.out.println("Nome: " + autor.getNome());
                System.out.println("Nacionalidade: " + autor.getNacionalidade());
                System.out.println("-----------------------");
            }
        }

        catch (SQLException ex) {
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception no listar do AutorDAO");
        }
        return listAutor;
    }

    public List<Autor> listarBuscar(String var) {
        String sql = "SELECT * FROM autor WHERE nome LIKE ? OR nacionalidade LIKE ?";
        List<Autor> listAutor = new ArrayList<>();

        try {
            System.out.println("ENtrou no listar");
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + var + "%");
            stmt.setString(2, "%" + var + "%");
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {

                Autor autor = new Autor();
                autor.setIdAutor(resultado.getInt("id_autor"));
                autor.setNome(resultado.getString("nome"));
                autor.setNacionalidade(resultado.getString("nacionalidade"));
                listAutor.add(autor);

                    System.out.println("-----------------------");
                    System.out.println("ID: " + autor.getIdAutor());
                    System.out.println("Nome: " + autor.getNome());
                    System.out.println("Nacionalidade: " + autor.getNacionalidade());
                    System.out.println("-----------------------");
            }
        }

        catch (SQLException ex) {
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception no listar do AutorDAO");
        }
        return listAutor;
    }

    public Autor retornarAutor(int idAutor){
        String sql = "SELECT * FROM autor WHERE id_autor = ? ";

        try {
            System.out.println("ENtrou no listar");
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idAutor);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()){

                Autor autor = new Autor();
                autor.setIdAutor(resultado.getInt("id_autor"));
                autor.setNome(resultado.getString("nome"));
                autor.setNacionalidade(resultado.getString("nacionalidade"));
                return autor;
            }
        }
        catch (SQLException ex){
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception no listar do AutorDAO");
        }
        return null;
    }
}



