package utilidades;

import java.net.InetSocketAddress;
import java.net.Socket;

public class ConexionCentral {

    public static boolean hayConexion(String host, int puerto, int timeoutMs) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, puerto), timeoutMs);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}