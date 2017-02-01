package tests;

import static client.TaskJFrame.host_serv1;
import static client.TaskJFrame.port_serv1;
import java.io.ObjectOutputStream;
import java.net.Socket;
import task.Request;
import task.Task;

public class Test 
{
    public static void main(String[] args)
    {
        Task t1 = new Task("1", "titulo 01", "desc 01", false);
        Task t2 = new Task("2", "titulo 02", "desc 02", false);
        Task t3 = new Task("3", "titulo 03", "desc 03", false);
        Task t4 = new Task("4", "titulo 04", "desc 04", false);
        
        t1.create();
        t2.create();
        t3.create();
        System.out.println("--------------------------------");
        t2.done = true;
        t2.update();
        System.out.println("--------------------------------");
        t1.destroy();
        t2.destroy();
        t3.destroy();
        System.out.println("--------------------------------");
        
        Request r = new Request("create", t4);
        
        try {
            Socket client = new Socket(host_serv1, port_serv1);
            /* out para enviar o objeto Request ao Serv1 */
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            out.flush();
            System.out.println("enviado t4 para criar pelo serv1");
            out.writeObject(r); /* enviar o objeto request para o Serv1 */
            out.close();
            client.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
