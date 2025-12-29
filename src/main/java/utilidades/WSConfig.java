package utilidades;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class WSConfig {

    private static final String CONFIG_PATH =
            System.getProperty("user.home")
            + "/CulturarteWebConfig/config.properties";

    private static Properties properties = new Properties();

    static {
        try (InputStream is = new FileInputStream(CONFIG_PATH)) {
            properties.load(is);
        } catch (Exception e) {
            throw new RuntimeException(
                "No se pudo cargar la configuración del WS desde " + CONFIG_PATH,
                e
            );
        }
    }

    public static String getCentralHost() {
        return properties.getProperty("central.host");
    }

    public static String getWsPort() {
        return properties.getProperty("central.ws.port");
    }

    public static String getWsContext() {
        return properties.getProperty("central.ws.context");
    }

    public static String getBaseUrl() {
        return "http://"
                + getCentralHost()
                + ":"
                + getWsPort()
                + getWsContext();
    }
    public static String getWsdlUrl() {
        return getBaseUrl() + "?wsdl";
    }
    
        public static String getBasePhotosUrl() {
        return "http://" + getCentralHost(); //Lo demas se lo pido al Servidor Central
    }
}


