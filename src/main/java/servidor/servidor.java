package servidor;

import algoritmos.hash;
import herramientas.generadorClave;
import herramientas.paquete;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.math.BigInteger;
import java.net.InetAddress;

public class servidor{
    public static void main(String[] args) throws IOException {
        //Esta será Alice...
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        hash hashear = new hash();

        InetAddress inetAddress = InetAddress.getLocalHost();

        System.out.println("Introduzca el numero primo:");
        BigInteger modulo=new BigInteger(br.readLine());

        System.out.println("Introduzca la raiz primitiva");
        BigInteger coeficiente=new BigInteger(br.readLine());

        System.out.println("Enter value for x less than "+modulo+":");
        BigInteger exponente=new BigInteger(br.readLine());

        generadorClave generador = new generadorClave(coeficiente,exponente,modulo);
        BigInteger R1 = generador.generarClave();

        System.out.println("IP Address:- " + inetAddress.getHostAddress());
        System.out.println("Host Name:- " + inetAddress.getHostName());

        System.out.println("Enter your message: ");
        String msg = br.readLine();

        String hashedMsg = hashear.generateHash256(msg);

        enviarInfo(R1, 4000, msg,hashedMsg);

    }

    public static void enviarInfo(BigInteger datos, int puerto, String message, String messageDigest)  throws IOException {

//        paquete pkt = new paquete(message, messageDigest, datos);
        ServerSocket ss = new ServerSocket(4000);
        String msgAsqueroso = message+messageDigest;
        Socket socket = ss.accept();
        OutputStream out = socket.getOutputStream();
        ObjectOutputStream oout = new ObjectOutputStream(out);
        oout.writeObject(datos);
        oout.writeObject(msgAsqueroso);
        oout.close();
    }
}