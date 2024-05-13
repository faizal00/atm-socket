import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientSide {
    public static void main(String[] args) {
        Socket sSocket;
        PrintStream output;
        BufferedReader pin, menu, tarik, setor, repeat;

        try {
            // Masuk ke Port
            sSocket = new Socket("127.0.0.1", 271);

            //Mengirim Data ke Server
            output = new PrintStream(sSocket.getOutputStream());
            pin = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Masukan Pin Anda : ");
            output.println(pin.readLine());

            // Mengambil Data dari Server
            InputStreamReader input = new InputStreamReader(sSocket.getInputStream());
            BufferedReader br = new BufferedReader(input);
            String checkPin = br.readLine();

            // Cek Pin
            if (checkPin.equals("true")) {
                String opt;
                do {
                    System.out.println("===== MENU ===== \n 1. Cek Saldo \n 2. Tarik Saldo \n 3. Setor Saldo");
                    menu = new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("Pilihan anda: ");
                    String pilihan = menu.readLine();
                    output.println(pilihan);

                    if (pilihan.equals("1")) {
                        System.out.println("Total saldo anda: " + Float.parseFloat(br.readLine()));
                    }else if (pilihan.equals("2")) {
                        if((br.readLine()).equals("true")) {
                            tarik = new BufferedReader(new InputStreamReader(System.in));
                            System.out.print("Nominal yang ingin ditarik: ");
                            output.println(tarik.readLine());

                            System.out.println(br.readLine());
                        } else {
                            System.out.println("Saldo anda : " + br.readLine() + ".\nSilahkan setor terlebih dahulu.");
                        }
                    }else if(pilihan.equals("3")) {
                        setor = new BufferedReader(new InputStreamReader(System.in));
                        System.out.print("Nominal yang ingin disetor: ");
                        output.println(setor.readLine());

                        System.out.println(br.readLine());
                    }
                    repeat = new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("Ingin melakukan transaksi lain? (y/n) ");
                    opt = repeat.readLine();
                    output.println(opt);

                } while (opt.equalsIgnoreCase("y"));
            }else{
                System.out.println("Pin Salah!");
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
