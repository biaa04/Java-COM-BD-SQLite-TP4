package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseSQLite {

    public Connection connection;

    public Connection conectar(){
        try {
            //Drive utilizado para conectar o código ao banco
            Class.forName("org.sqlite.JDBC");
            System.out.println("1");
            this.connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            System.out.println("2");
            return this.connection;

        }catch (SQLException | ClassNotFoundException ex){
            Logger.getLogger(DatabaseSQLite.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro na função conectar na classe DataSQLite");
            return null;

        }
    }

    public void desconectar(Connection connection){
        try {
            connection.close();
        }catch (SQLException ex){
            Logger.getLogger(DatabaseSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
