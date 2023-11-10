package Database;

public class DatabaseFactory {

    public static DatabaseSQLite getDatabase(String nome){
        if (nome.equals("biblioteca")){
            System.out.println(" DF");
            return new DatabaseSQLite();
        }else {
            return null;
        }
    }
}
