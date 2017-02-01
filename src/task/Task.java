package task;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Task implements Serializable
{
    public String id;
    public String title;
    public String desc;
    public boolean done;
    
    /* configurações de acesso ao banco */
    private static String host = "localhost";
    private static String port = "3306";
    private static String db_name = "db_trab_05";
    private static String db_user = "root";
    private static String db_pass = "root";
    
    /* instanciar tarefa apenas com o ID */
    public Task(String id)
    {
        this.id = id;
    }
    
    /* instanciar tarefa com todos os atributos */
    public Task(String id, String title, String desc, boolean done)
    {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.done = done;
    }
    
    /* insere uma nova tarefa no banco de dados */
    public void create()
    {
        try {
            //Conecta ao banco
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db_name+"", db_user, db_pass);
 
            //Executa a inserção
            java.sql.Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO tasks (id,title,description,done) VALUES ("
                    + this.id + ",'"
                    + this.title + "','"
                    + this.desc + "',"
                    + this.done + ")");
 
            System.out.println("inserido tarefa: " + this.id);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("erro ao inserir tarefa: " + this.id);
            System.out.println(e);
        }
    }
    
    /* altera a tarefa no banco de dados */
    public void update()
    {
        try {
            //Conecta ao banco
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db_name+"", db_user, db_pass);
 
            //Executa a alteração
            java.sql.Statement st = conn.createStatement();
            st.executeUpdate("UPDATE tasks SET "
                    + "title='" + this.title + "',"
                    + "description='" + this.desc + "',"
                    + "done=" + this.done + " "
                    + "WHERE id='" + this.id + "'");
 
            System.out.println("alterado tarefa: " + this.id);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("erro ao alterar tarefa: " + this.id);
            System.out.println(e);
        }
    }
    
    /* excluí a tarefa do banco de dados */
    public void destroy()
    {
        try {
            //Conecta ao banco
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db_name+"", db_user, db_pass);
 
            //Executa a exclusão
            java.sql.Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM tasks WHERE id='" + this.id + "'");
 
            System.out.println("excluído tarefa: " + this.id);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("erro ao excluir tarefa: " + this.id);
            System.out.println(e);
        }
    }
    
    public static ResultSet listAllTasks()
    {
        ResultSet rs = null;
        
        try {
            //Conecta ao banco
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db_name+"", db_user, db_pass);
 
            //Executa a exclusão
            java.sql.Statement st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM tasks");
 
            System.out.println("listar todas as tarefas");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("erro ao listar todas as tarefas");
            System.out.println(e);
        }
        
        return rs;
    }
}
