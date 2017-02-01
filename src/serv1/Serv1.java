package serv1;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import task.Request;

public class Serv1 
{
    public static void main(String[] args)
    {
        try {
            ServerSocket server = new ServerSocket(3000);
            System.out.println("Servidor [1] iniciado...");
            System.out.println("------------------------");
            
            while (true) 
            {
                // o método accept() bloqueia a execução até que
                // o servidor receba um pedido de conexão
                Socket client = server.accept();
                Socket clientForServ2 = new Socket("127.0.0.1", 3001);
                System.out.println("cliente " + client.getInetAddress().getHostAddress() + " conectado.");

                /* in para receber o objeto Request enviado pelo cliente */
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                
                System.out.println("recebendo objeto do client...");
                Request r = (Request) in.readObject();

                if (r.operation.equals("create"))
                    r.task.create();

                if (r.operation.equals("update"))
                    r.task.update();

                if (r.operation.equals("destroy"))
                    r.task.destroy();

                /* out para enviar o objeto Request ao servidor 2 */
                System.out.println("enviando operação ao serv2...");
                System.out.println("");
                ObjectOutputStream out = new ObjectOutputStream(clientForServ2.getOutputStream());
                out.flush();
                out.writeObject(r); /* enviar o objeto request para o servidor 2 */
                out.close();
                clientForServ2.close();
                
                in.close();
                client.close();
                System.out.println("requisição de " + client.getInetAddress().getHostAddress() + " completada!");
                System.out.println("------------------------");
            }
        } catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
