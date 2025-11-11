package Filtros;

import WebServices.DataRegistro;
import WebServices.LogicaWS;
import WebServices.LogicaWS_Service;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter(filterName = "FiltroRegistroAccesos", urlPatterns = {"/*"})
public class FiltroRegistroAccesos implements Filter {
    LogicaWS_Service service;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        service = new LogicaWS_Service();
        LogicaWS ic = service.getLogicaWSPort();
        HttpServletRequest req = (HttpServletRequest) request;

        String ip = req.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }
        if (ip.contains(",")) {
            ip = ip.split(",")[0]; // tomar solo la primera IP
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "localhost"; // opcional, para pruebas locales
        }

        String url = req.getRequestURI();
        String userAgent = req.getHeader("User-Agent");

        String navegador = extraerNavegador(userAgent);
        String so = extraerSO(userAgent);
        
        DataRegistro data = new DataRegistro();
        data.setIP(ip);
        data.setURL(url);
        data.setNavegador(navegador);
        data.setSO(so);
        if(url.endsWith(".jsp")){
            ic.registrarSesion(data);
        }
        

        chain.doFilter(request, response);
    }

    // Métodos auxiliares para parsear User-Agent
    private String extraerNavegador(String userAgent) {
        if (userAgent == null) return "Desconocido";
        if (userAgent.contains("Edg")) return "Edge";
        if (userAgent.contains("OPR") || userAgent.contains("Opera")) return "Opera";
        if (userAgent.contains("Brave")) return "Brave";
        if (userAgent.contains("SamsungBrowser")) return "Samsung Internet";
        if (userAgent.contains("Firefox")) return "Firefox";
        if (userAgent.contains("Safari") && !userAgent.contains("Chrome")) return "Safari";

        if (userAgent.contains("Chrome")) return "Chrome";
        
        return "Desconocido";
    }

    private String extraerSO(String userAgent) {
        if (userAgent == null) return "Desconocido";
        if (userAgent.contains("Windows")) return "Windows";
        if (userAgent.contains("iPhone") || userAgent.contains("iPad")) return "iOS";
        if (userAgent.contains("Mac")) return "macOS";
        if (userAgent.contains("X11")) return "Unix";
        if (userAgent.contains("Android")) return "Android";
        if (userAgent.contains("Linux")) return "Linux";
        return "Desconocido";
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void destroy() {
        
    }
}