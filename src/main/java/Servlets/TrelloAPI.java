package Servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONObject;

public class TrelloAPI {
    
    private static final Properties props = new Properties();
    
    
    static {
        try (InputStream input = TrelloAPI.class.getClassLoader().getResourceAsStream("trello.properties")){
            if(input == null) {
                throw new RuntimeException("No se encontró el archivo trello.properties");
            }
            props.load(input);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static String get(String key){
        return props.getProperty(key);
    }
    
    private static final String KEY = TrelloAPI.get("trello.key");
    private static final String TOKEN = TrelloAPI.get("trello.token");
    private static final String BASE_URL = TrelloAPI.get("trello.api_base");
    //////////////////////////////////////////////////////////////////////
    
    public static JSONObject crearTablero(String nombre) throws IOException{
        String url = "https://api.trello.com/1/boards/?name=" + URLEncoder.encode(nombre, "UTF-8") + "&key=" + KEY + "&token=" + TOKEN;
        
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("POST");
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            return new JSONObject(sb.toString());
        }
    }
    
    public static JSONObject crearLista(String idTablero, String nombreLista) throws IOException{
        String url = "https://api.trello.com/1/lists?name=" + URLEncoder.encode(nombreLista, "UTF-8") + "&idBoard=" + idTablero + "&key=" + KEY + "&token=" + TOKEN;
        
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("POST");
        
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            return new JSONObject(sb.toString());
        }
        
        
    }
    
    public static JSONObject crearTarjeta(String idLista, String titulo, String descripcion) throws IOException{
        String url = "https://api.trello.com/1/cards?name=" 
                + URLEncoder.encode(titulo, "UTF-8") 
                + "&desc=" + URLEncoder.encode(descripcion, "UTF-8")
                + "&idList=" + idLista
                + "&key=" + KEY 
                + "&token=" + TOKEN;
        
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("POST");
        
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            return new JSONObject(sb.toString());
        }
        
        
    }
    
    public static void subirImagenATarjeta(String idTarjeta, File imagen) throws IOException{
        String boundary = Long.toHexString(System.currentTimeMillis());
        String CRLF = "\r\n";

        URL url = new URL("https://api.trello.com/1/cards/" + idTarjeta + "/attachments?key=" + KEY + "&token=" + TOKEN);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        try (OutputStream output = connection.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, "UTF-8"), true)) {

            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + imagen.getName() + "\"").append(CRLF);
            writer.append("Content-Type: image/jpeg").append(CRLF);
            writer.append(CRLF).flush();
            
            Files.copy(imagen.toPath(), output);
            output.flush();
            writer.append(CRLF).flush();

            writer.append("--" + boundary + "--").append(CRLF).flush();
        }

        int responseCode = connection.getResponseCode();
        System.out.println("Imagen subida, código: " + responseCode);
    }
    
    public static void eliminarTablero(String idTablero)throws IOException {
        String url = "https://api.trello.com/1/boards/" + idTablero 
                + "?key=" + KEY 
                + "&token=" + TOKEN;
        
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("DELETE");
        
        int responseCode = con.getResponseCode();
        if(responseCode == 200){
            System.out.println("Tablero eliminado correctamente.");
        }else{
            System.out.println("Error al eliminar tablero. Codigo: " + responseCode);
            
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
        }
    }
    
    ////////////////////////////////////////////////////////////
    
    public static String getTableroID(String nombreTablero) throws IOException {
        String url = "https://api.trello.com/1/members/me/boards?key=" + KEY + "&token=" + TOKEN;
        
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");
        
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))){
            String line;
            while ((line = br.readLine()) != null){
                response.append(line);
            }
        }
        
        JSONArray tableros = new JSONArray(response.toString());
        
        for(int i=0; i<tableros.length(); i++){
            JSONObject t = tableros.getJSONObject(i);
            if((t.getString("name")).equalsIgnoreCase(nombreTablero)){
                return t.getString("id");
            }
        }
        
        return null; //no lo encontró
    }
    
    public static String buscarListaPorIDTablero(String idTablero, String nombreLista) throws IOException {
        String url = "https://api.trello.com/1/boards/" + idTablero + "/lists?key=" + KEY + "&token=" + TOKEN;
        
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");
        
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))){
            String line;
            while ((line = br.readLine()) != null){
                response.append(line);
            }
        }
        
        
        JSONArray listas = new JSONArray(response.toString());
        
        for(int i=0; i<listas.length(); i++){
            JSONObject l = listas.getJSONObject(i);
            if((l.getString("name")).equalsIgnoreCase(nombreLista)){
                return l.getString("id");
            }
        }
        
        return null; //no lo encontró 
    }
}
