import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide {
    public static void main(String[] args) {
        Socket sSocket;
        ServerSocket sServer;
        DataInputStream input;
        float saldo;
        String pinUser = "";
        String pinBase = "123456";
        String menu = "";
        String opt;

        try {
            // Membuka Socket
            sServer = new ServerSocket(271);
            System.out.println("Server Siap");

            // Client Masuk
            sSocket = sServer.accept();
            System.out.println("Client Masuk");

            // Deklarasi Balasan
            PrintWriter reply = new PrintWriter(sSocket.getOutputStream());

            // Deklarasi Input
            input = new DataInputStream(sSocket.getInputStream());

            // ================================

            // Cek Pin
            pinUser=input.readLine();
            if (pinUser.equals(pinBase)) {
                reply.println(true);
                reply.flush();

                // Masuk ke ATM
                saldo=100000;
                System.out.println(saldo);
                do {
                    menu = input.readLine();
                    // Cek Saldo
                    if (menu.equals("1")) { 
                        System.out.println("Saldo Client : "+saldo);
                        reply.println(saldo);
                        reply.flush();
                    // Tarik Saldo
                    }else if (menu.equals("2")) {
                        // Saldo Tidak Boleh 0
                        if (saldo > 0) {    
                            reply.println(true);
                            reply.flush();
                            
                            String inputTarik = input.readLine();
                            float parseTarik = Float.parseFloat(inputTarik);
                            System.out.println("Penarikan : " + parseTarik);

                            saldo -= parseTarik;

                            // Tampilkan Sisa Saldo ke Client
                            reply.println("Sisa Saldo Anda : "+ saldo);
                            reply.flush();
                        // Saldo 0
                        }else{
                            reply.println(false);
                            reply.flush();

                            reply.println(saldo);
                            reply.flush();
                        }
                    // Setor Saldo
                    }else if (menu.equals("3")) {
                            String inputSetor = input.readLine();
                            float praseSetor = Float.parseFloat(inputSetor);
                            System.out.println("Setor : " + praseSetor);

                            saldo += praseSetor;

                            // Tampilkan Saldo Akhir
                            reply.println("Sisa Saldo Anda : "+ saldo);
                            reply.flush();
                    }
                    opt = input.readLine();
                } while (opt.equals("y"));
            // Pin Salah
            } else {
                System.out.println("Pin Salah!");
                reply.println(false);
                reply.flush();
            }
            input.close();
            sServer.close();
            sSocket.close();

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
