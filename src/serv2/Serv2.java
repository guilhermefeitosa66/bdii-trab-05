package serv2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import task.Request;

public class Serv2 
{
    public static void main(String[] args)
    {
        try {
            ServerSocket server = new ServerSocket(3001);
            
            System.out.println("Servidor [2] iniciado...");
            System.out.println("------------------------");
            
            while (true) 
            {
                // o método accept() bloqueia a execução até que
                // o servidor receba um pedido de conexão
                Socket client = server.accept();
                System.out.println("serv1 conectado: " + client.getInetAddress().getHostAddress());

                /* in para receber o objeto Request enviado pelo Serv1 */
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                
                /* receber objeto do Serv1 */
                System.out.println("recebendo objeto do serv1...");
                Request r = (Request) in.readObject();

                if (r.operation.equals("create"))
                    r.task.create();

                if (r.operation.equals("update"))
                    r.task.update();

                if (r.operation.equals("destroy"))
                    r.task.destroy();

                in.close();
                client.close();
                System.out.println("Requisição do Serv1 completada!");
                System.out.println("------------------------");
            }
        } catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }    
}
