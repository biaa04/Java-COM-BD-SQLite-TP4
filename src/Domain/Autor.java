package Domain;

import DAO.AutorDAO;
import DAO.LivroDAO;
import Database.DatabaseFactory;
import Database.DatabaseSQLite;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class Autor {
    private int idAutor;
    private String nome;
    private String nacionalidade;
    private static ArrayList<Autor> autores = new ArrayList<Autor>();

    public Autor(){}

    public Autor(String nome, String nacionalidade) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
    }

    public void inserirAutor() {
        DatabaseSQLite database = DatabaseFactory.getDatabase("biblioteca");
        Connection connection = database.conectar();
        AutorDAO autorDAO = new AutorDAO();
        autorDAO.setConnection(connection);

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            System.out.println(autores.size());
            System.out.println("Digite o nome do autor " + (i + 1) + ":");
            String nome = scanner.nextLine();
            System.out.println("Digite a nacionalidade do autor " + (i + 1) + ":");
            String nacionalidade = scanner.nextLine();
            Autor autor = new Autor(nome, nacionalidade);
            autorDAO.inserir(autor);
            autores.add(autor);
        }
        database.desconectar(connection);
    }

    public void buscarAutor() {
        DatabaseSQLite database = DatabaseFactory.getDatabase("biblioteca");
        Connection connection = database.conectar();
        AutorDAO autorDAO = new AutorDAO();

        autorDAO.setConnection(connection);
        Scanner scanner = new Scanner(System.in);
        Autor[] autores = new Autor[5];
        System.out.println("Busque pelo nome ou nacionalidade: ");
        String busca = scanner.nextLine();

        autorDAO.listarBuscar(busca);
        database.desconectar(connection);
    }

    public void listar() {
        DatabaseSQLite database = DatabaseFactory.getDatabase("biblioteca");
        Connection connection = database.conectar();
        AutorDAO autorDAO = new AutorDAO();

        autorDAO.setConnection(connection);
        Scanner scanner = new Scanner(System.in);
        autorDAO.listar();
        database.desconectar(connection);
    }

    public void editar() {
        DatabaseSQLite database = DatabaseFactory.getDatabase("biblioteca");
        Connection connection = database.conectar();
        AutorDAO autorDAO = new AutorDAO();

        autorDAO.setConnection(connection);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o id do autor que você precisa editar");
        int idBusca = scanner.nextInt();
        scanner.nextLine();

        Autor a = autorDAO.retornarAutor(idBusca);

        System.out.println("Digite o nome do autor: ");
        String nome = scanner.nextLine();
        scanner.nextLine();
        a.setNome(nome);

        System.out.println("Digite a nacionalidade do autor: ");
        String nacionalidade = scanner.nextLine();
        a.setNacionalidade(nacionalidade);

        autorDAO.alterar(a);
        database.desconectar(connection);
    }

    public void remover(){
        DatabaseSQLite database = DatabaseFactory.getDatabase("biblioteca");
        Connection connection = database.conectar();
        AutorDAO autorDAO = new AutorDAO();
        autorDAO.setConnection(connection);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o id do autor que você deseja remover");
        int idRemover = scanner.nextInt();
        scanner.nextLine();
        autorDAO.remover(idRemover);
        database.desconectar(connection);
    }


    public int getIdAutor () {
        return idAutor;
    }
    public void setIdAutor (int idAutor) {
        this.idAutor = idAutor;
    }
    public String getNome () {
        return nome;
    }
    public void setNome (String nome){
        this.nome = nome;
    }
    public String getNacionalidade () {
        return nacionalidade;
    }
    public static ArrayList<Autor> getAutores () {
        return autores;
    }
    public static void setAutores (ArrayList < Autor > autores) {
        Autor.autores = autores;
    }
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

}
