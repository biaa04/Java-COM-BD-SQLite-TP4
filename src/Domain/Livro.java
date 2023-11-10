package Domain;

import DAO.LivroDAO;
import Database.DatabaseFactory;
import Database.DatabaseSQLite;
import java.sql.Connection;
import java.util.Scanner;
import java.util.ArrayList;

public class Livro {
    private int idLivro;
    private String nome;
    private int ano;
    private String genero;
    private String autor;
    private static ArrayList<Livro> livros = new ArrayList<Livro>();

    public Livro(){}

    public Livro(String nome, String genero,int ano, String autor){
        this.nome = nome;
        this.ano = ano;
        this.genero = genero;
        this.autor = autor;
    }

    public void inserirLivro() {
        DatabaseSQLite database = DatabaseFactory.getDatabase("biblioteca");
        Connection connection = database.conectar();
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.setConnection(connection);

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            System.out.println(livros.size());
            System.out.println("Digite o nome do livro " + (i + 1) + ":");
            String nome = scanner.nextLine();
            System.out.println("Digite o gênero do livro " + (i + 1) + ":");
            String genero = scanner.nextLine();
            System.out.println("Digite o ano do livro " + (i + 1) + ":");
            int ano = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Digite o nome do autor " + (i + 1) + ":");
            String autor = scanner.nextLine();
            Livro livro = new Livro(nome, genero, ano, autor);
            livroDAO.inserir(livro);
            livros.add(livro);
        }
        database.desconectar(connection);
    }

    public void buscarLivro(){
        DatabaseSQLite database = DatabaseFactory.getDatabase("biblioteca");
        Connection connection = database.conectar();
        LivroDAO livroDAO = new LivroDAO();

        livroDAO.setConnection(connection);
        Scanner scanner = new Scanner(System.in);
        Livro[] livros = new Livro[5];
        System.out.println("Busque pelo nome, genero e ano de lançamento: ");
        String busca = scanner.nextLine();

        livroDAO.listarBuscar(busca);
        database.desconectar(connection);
    }

    public void listar(){
        DatabaseSQLite database = DatabaseFactory.getDatabase("biblioteca");
        Connection connection = database.conectar();
        LivroDAO livroDAO = new LivroDAO();

        livroDAO.setConnection(connection);
        livroDAO.listar();
        database.desconectar(connection);
    }

    public void editar(){
        DatabaseSQLite database = DatabaseFactory.getDatabase("biblioteca");
        Connection connection = database.conectar();
        LivroDAO livroDAO = new LivroDAO();

        livroDAO.setConnection(connection);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o id do livro que você deseja editar");
        int idBusca = scanner.nextInt();

            Livro l = livroDAO.retornarLivro(idBusca);

            System.out.println("Digite o nome do livro: ");
            String nome = scanner.nextLine();
            l.setNome(nome);

            System.out.println("Digite o gênero do livro: ");
            String genero = scanner.nextLine();
            l.setGenero(genero);

            System.out.println("Digite o ano do livro: ");
            int ano = scanner.nextInt();
            l.setAno(ano);
            scanner.nextLine();

            System.out.println("Digite o nome do autor: ");
            String autor = scanner.nextLine();
            l.setAutor(autor);

            livroDAO.alterar(l);
        database.desconectar(connection);
    }

    public void remover(){
        DatabaseSQLite database = DatabaseFactory.getDatabase("biblioteca");
        Connection connection = database.conectar();
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.setConnection(connection);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o id do livro que você deseja remover");
        int idRemover = scanner.nextInt();
        livroDAO.remover(idRemover);
        database.desconectar(connection);
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

}
