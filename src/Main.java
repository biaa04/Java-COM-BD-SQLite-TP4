// TP-4 -> Salete Yara Lopes e Beatriz Andrade

import Domain.Autor;
import Domain.Livro;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws SQLException {
        boolean continuar= true;

        while(continuar){
        System.out.println("---------------menu----------------");
        System.out.println("1) Adicionar 5 autores");
        System.out.println("2) Adicionar 5 livros");
        System.out.println("3) Busca");
        System.out.println("4) Listar");
        System.out.println("5) Editar");
        System.out.println("6) Remover");
        System.out.println("7) Sair");
        System.out.println("-----------------------------------");

        Autor autor = new Autor();
        Livro livro = new Livro();
        Scanner scanner = new Scanner(System.in);
        int opcao = Integer.parseInt(scanner.nextLine());

        switch (opcao) {
            case 1:
                autor.inserirAutor();
                break;

            case 2:
                livro.inserirLivro();
                break;

            case 3:
                System.out.println("1)buscar autor");
                System.out.println("2)buscar livro");

                int a = Integer.parseInt(scanner.nextLine());

                if (a == 1) {
                    autor.buscarAutor();
                } else {
                    livro.buscarLivro();
                }
                break;

            case 4:
                System.out.println("1)listar autores");
                System.out.println("2)listar livros");

                int b= Integer.parseInt(scanner.nextLine());

                if (b == 1) {
                    autor.listar();
                }
                else{
                    livro.listar();
                }
                break;

            case 5:
                System.out.println("1)editar autores");
                System.out.println("2)editar livros");

                int c= Integer.parseInt(scanner.nextLine());

                if (c == 1){
                    autor.editar();
                }
                else{
                    livro.editar();
                }
                break;

            case 6:
                System.out.println("1)remover autor");
                System.out.println("2)remover livro");

                int d= Integer.parseInt(scanner.nextLine());

                if (d == 1) {
                    autor.remover();
                } else {
                    livro.remover();
                }

                break;

            case 7:
                continuar = false;
                break;
            }
        }
    }


}