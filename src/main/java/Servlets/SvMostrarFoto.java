package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/SvMostrarFoto")
public class SvMostrarFoto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getParameter("path");

        File file = null;

        // Si no se especificó o el archivo no existe, usamos una imagen por defecto
        if (path == null || path.isEmpty()) {
            file = new File(getServletContext().getRealPath("/fotos/default.jpg"));
        } else {
            file = new File(path);
            if (!file.exists() || file.isDirectory()) {
                file = new File(getServletContext().getRealPath("/fotos/default.jpg"));
            }
        }

        // Verificamos que la imagen por defecto exista (por si acaso)
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontró la imagen ni la imagen por defecto");
            return;
        }

        // Detectamos tipo MIME automáticamente
        String mimeType = getServletContext().getMimeType(file.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        response.setContentLengthLong(file.length());

        // Enviamos la imagen al cliente
        try (FileInputStream in = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}