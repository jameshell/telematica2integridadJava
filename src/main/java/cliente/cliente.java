package cliente;

import algoritmos.hash;
import herramientas.generadorClave;
import herramientas.paquete;

import java.io.*;
import java.net.Socket;
import java.math.BigInteger;

public class cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Este será Bob...
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        hash hashear = new hash();

        System.out.println("Introduzca el numero primo:");
        BigInteger modulo=new BigInteger(br.readLine());

//        System.out.println("Introduzca la raiz primitiva");
//        BigInteger coeficiente=new BigInteger(br.readLine());

        System.out.println("Enter value for x less than "+modulo+":");
        BigInteger exponente=new BigInteger(br.readLine());

        paquete pkt = recibirInfo("localhost",4000, "recibido.txt");
        BigInteger R1 = pkt.getClaveDelRSA();

        generadorClave generador = new generadorClave(R1,exponente,modulo);
        BigInteger k2 = generador.generarClave();

        System.out.println("Llave calculada desde el lado de Bob: "+k2);
        System.out.println("Mensaje de Alicia: "+pkt.getMensajeOriginal());
        System.out.println("SHA256 de Alicia: " +pkt.getMensajeSHA());
        System.out.println("Mensaje de Alicia con SHA256 de Bob: "+hashear.generateHash256(pkt.getMensajeOriginal()));
    }

    public static paquete recibirInfo(String direccion, int puerto, String nombre) throws IOException, ClassNotFoundException {
        Socket socket =new Socket(direccion, puerto);
        InputStream input = socket.getInputStream();
        ObjectInputStream oin = new ObjectInputStream(input);
        BigInteger R1 = (BigInteger) oin.readObject();
        String mensaje = (String) oin.readObject();
//        String str = R1.toString();
//        FileWriter writer = new FileWriter(nombre);
//        writer.write(str);
//        System.out.println("|Archivo Creado Exitosamente!");
//        System.out.println("|Nombre: "+nombre);
//        System.out.println("|Ubicacion: "+System.getProperty("user.dir"));
        input.close();
//        writer.close();
        System.out.println("Datos recibidos del servidor: "+R1);
        String mensajeOriginal = mensaje.substring(0,4);
        String mensajeHash = mensaje.substring(4);
        paquete pkt = new paquete(mensajeOriginal, mensajeHash,R1);

        return pkt;
    }
}