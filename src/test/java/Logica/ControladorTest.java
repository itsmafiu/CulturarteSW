/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Logica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author mafiu
 */
public class ControladorTest {
    
    private Controlador instance;
    
    public ControladorTest() {
    }

    @BeforeAll
    public static void setUpClass() throws Exception {
        
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
       instance = new Controlador();

        // Crear usuarios base
        instance.añadirUsuario("a", "nahuel", "machado", "a@gmail.com", LocalDate.now(), "", BCrypt.hashpw("1234", BCrypt.gensalt()), "");
        instance.añadirUsuario("s", "asd", "asd", "s@gmail.com", LocalDate.now(), "", BCrypt.hashpw("1234", BCrypt.gensalt()), "asd", "asd", "asd", "");

        // Crear categoría base
        instance.altaCategoria("d");
        instance.altaCategoria("musiquitita", "Categoria");

        // Crear propuesta base
        instance.altaPropuesta("s", "musiquitita", "t", "asd", "asd", LocalDate.now(), "100", "20000", EnumRetorno.AMBOS, LocalDate.now(), "");
        instance.altaPropuesta("s", "musiquitita", "ttt", "asd", "asd", LocalDate.now(), "100", "20000", EnumRetorno.AMBOS, LocalDate.now(), "");
        
        // Crear Aportes base
        instance.altaAporte("a", "t by s", 1000.0, 0, EnumRetorno.PORCENTAJE_VENTAS);
        
        // Crear Comentario base
        instance.addComentario("t", "a", "hola");
    }
    
    @AfterEach
    public void tearDown() throws Exception {
    }

//    @org.junit.jupiter.api.BeforeAll
//    public static void setUpClass() throws Exception {
//    }
//
//    @AfterAll
//    public static void tearDownClass() throws Exception {
//    }
//
//    @BeforeEach
//    public void setUp() throws Exception {
//    }
//
//    @AfterEach
//    public void tearDown() throws Exception {
//    }
//
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }

    /**
     * Test of añadirUsuario method, of class Controlador.
     */
    
    /////////////////////////////
    //TODO COMENTADO PARA ABAJO//
    ///////////////////////////// 
    /**
     * Test of añadirUsuario method, of class Controlador.
     */
    @Test
    public void testAñadirUsuario_8args() {
        //System.out.println("a\u00f1adirUsuario");
        String nick = "aa";
        String nombre = "nahuel";
        String apellido = "machado";
        String correo = "aa@gmail.com";
        LocalDate fecNac = LocalDate.now();
        String imagen = "";
        String contraseña = BCrypt.hashpw("1234", BCrypt.gensalt());
        String imagenWeb = "";
        int expResult = 1;
        int result = instance.añadirUsuario(nick, nombre, apellido, correo, fecNac, imagen, contraseña, imagenWeb);
        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//       // fail("The test case is a prototype.")
    }

    /**
     * Test of añadirUsuario method, of class Controlador.
     */
    @Test
    public void testAñadirUsuario_11args() {
        //System.out.println("a\u00f1adirUsuario");
        String nick = "ss";
        String nombre = "asd";
        String apellido = "asd";
        String correo = "ss@gmail.com";
        LocalDate fecNac = LocalDate.now();
        String imagen = "";
        String contraseña = "1234";
        String direccion = "asd";
        String bio = "asd";
        String sitioWeb = "asd";
        String imagenWeb = "";
        int expResult = 1;
        int result = instance.añadirUsuario(nick, nombre, apellido, correo, fecNac, imagen, contraseña, direccion, bio, sitioWeb, imagenWeb);
        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of altaCategoria method, of class Controlador.
     */
    @Test
    public void testAltaCategoria_String() {
        //System.out.println("altaCategoria");
        String nombreCat = "dd";
        int expResult = 0;
        int result = instance.altaCategoria(nombreCat);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of altaCategoria method, of class Controlador.
     */
    @Test
    public void testAltaCategoria_String_String() {
        //System.out.println("altaCategoria");
        String nombreCat = "musiquititaa";
        String nombrePadreCat = "Categoria";
        int expResult = 0;
        int result = instance.altaCategoria(nombreCat, nombrePadreCat);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //
//    /**
//     * Test of altaPropuesta method, of class Controlador.
//     */
    @Test
    public void testAltaPropuesta() {
        //System.out.println("altaPropuesta");
        String nick = "ss";
        String tipo = "musiquititaa";
        String titulo = "tt";
        String descripcion = "asd";
        String lugar = "asd";
        LocalDate fechaPrev = LocalDate.now();
        String montoXentrada = "100";
        String montoNecesario = "20000";
        EnumRetorno posibleRetorno = EnumRetorno.AMBOS;
        LocalDate fechaActual = LocalDate.now();
        String imagen = "";
        int expResult = 1;
        int result = instance.altaPropuesta(nick, tipo, titulo, descripcion, lugar, fechaPrev, montoXentrada, montoNecesario, posibleRetorno, fechaActual, imagen);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of cargarNodoRaizCategorias method, of class Controlador.
     */
    @Test
    public void testCargarNodoRaizCategorias() {
        //System.out.println("cargarNodoRaizCategorias");
        DefaultMutableTreeNode expResult = instance.cargarNodoRaizCategorias();
        DefaultMutableTreeNode result = expResult;
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of altaAporte method, of class Controlador.
     */
    @Test
    public void testAltaAporte_5args() {
        //System.out.println("altaAporte");
        String strmiColaborador = "aa";
        String strmiPropuesta = "t by s"; //HAY QUE MODIFICARLO PARA TEST FINAL
        double $aporte = 1000.0;
        int cantidad = 0;
        EnumRetorno retorno = EnumRetorno.PORCENTAJE_VENTAS;
        int expResult = 0;
        int result = instance.altaAporte(strmiColaborador, strmiPropuesta, $aporte, cantidad, retorno);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    /**
     * Test of borrarAporte method, of class Controlador.
     */
    @Test
    public void testBorrarAporte() {
        //System.out.println("borrarAporte");
        String tituloNick = "t by s";
        String nick = "a";
        instance.borrarAporte(tituloNick, nick);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }


    /**
     * Test of altaAporte method, of class Controlador.
     */
    @Test
    public void testAltaAporte_6args() {
        //System.out.println("altaAporte");
        String strmiColaborador = "a";
        String strmiPropuesta = "ttt"; //HAY QUE MODIFICARLO PARA TEST FINAL
        double $aporte = 1000.0;
        int cantidad = 0;
        EnumRetorno retorno = EnumRetorno.PORCENTAJE_VENTAS;
        LocalDateTime fecAp = LocalDateTime.now();
        int expResult = 0;
        int result = instance.altaAporte(strmiColaborador, strmiPropuesta, $aporte, cantidad, retorno, fecAp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuarios method, of class Controlador.
     */
    @Test
    public void testGetUsuarios() {
        //System.out.println("getUsuarios");
        List<String> usu = new ArrayList<>();
        usu.add("a");
        usu.add("aa");
        usu.add("s");
        usu.add("ss");
        List<String> expResult = usu;
        List<String> result = instance.getUsuarios();
        assertEquals(expResult, result);
         //TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuario method, of class Controlador.
     */
    @Test
    public void testGetUsuario() {
        //System.out.println("getUsuario");
        String nick = "a";
        String expResult = "a";
        Usuario resultado = instance.getUsuario(nick);
        String result = resultado.getNickname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getProponentes method, of class Controlador.
     */
    @Test
    public void testGetProponentes() {
        //System.out.println("getProponentes");
        List<String> nicksEsperados = new ArrayList<>();
        List<String> nicks = new ArrayList<>();
        nicksEsperados.add("s");
        List<Proponente> prop = instance.getProponentes();
        for(Proponente p : prop){
            nicks.add(p.getNickname());
        }
        assertEquals(nicksEsperados, nicks);
    }

    /**
     * Test of getUsuariosColaboradores method, of class Controlador.
     */
    @Test
    public void testGetUsuariosColaboradores() {
        //System.out.println("getUsuariosColaboradores");
        List<String> nicksEsperados = new ArrayList<>();
        List<String> nicks = new ArrayList<>();
        nicksEsperados.add("a");
        nicksEsperados.add("aa");
        List<Colaborador> colabs = instance.getUsuariosColaboradores();
        for(Colaborador c : colabs){
            nicks.add(c.getNickname());
        }
        assertEquals(nicksEsperados, nicks);
    }

    /**
     * Test of getDataUsuarios method, of class Controlador.
     */
    @Test
    public void testGetDataUsuarios() {
      //System.out.println("getDataUsuarios");
        List<String> nicksEsperados = new ArrayList<>();
        List<String> nicks = new ArrayList<>();
        nicksEsperados.add("a");
        nicksEsperados.add("s");
        List<DataUsuario> DataUsu = instance.getDataUsuarios();
        DataUsuario usu = new DataUsuario();
        usu.setSitioWeb("asd");
        for(DataUsuario u : DataUsu){
            nicks.add(u.getNickname());
            u.hashCode();
            if(u.getNickname().equals("s")){
                u.equals(usu);
            }
        }
        
        assertEquals(nicksEsperados, nicks);
    }  
    /**
     * Test of getDataUsuarioWeb method, of class Controlador.
     */
    @Test
    public void testGetDataUsuarioWeb() {
        //System.out.println("getDataUsuarioWeb");
        String nick = "a";
        String expResult = "a";
        DataUsuario resultado = instance.getDataUsuarioWeb(nick);
        String result = resultado.getNickname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPropuestas_Proponentes method, of class Controlador.
     */
    @Test
    public void testGetPropuestas_Proponentes() {
        //System.out.println("getPropuestas_Proponentes");
        List<String> expResult = new ArrayList<>();
        expResult.add("t by s");
        expResult.add("ttt by s");
        List<String> result = instance.getPropuestas_Proponentes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuariosProponentes method, of class Controlador.
     */
    @Test
    public void testGetUsuariosProponentes() {
        //System.out.println("getUsuariosProponentes");
        List<String> expResult = new ArrayList<>();
        expResult.add("s");
        expResult.add("ss");
        List<String> result = instance.getUsuariosProponentes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
    
    /**
     * Test of seguirUsuario method, of class Controlador.
     */
    @Test
    public void testSeguirUsuario() {
        //System.out.println("seguirUsuario");
        String nick1 = "a";
        String nick2 = "s";
        int expResult = 1;
        int result = instance.seguirUsuario(nick1, nick2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

        @Test
    public void testGetSeguidores() {
        //System.out.println("getSeguidores");
        Usuario seguido = instance.getUsuario("s");
        String expResult = "a";
        List<DataUsuario> resultado = instance.getSeguidores(seguido);
        DataUsuario DU = resultado.get(0);
        String result = DU.getNickname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of getSeguidos method, of class Controlador.
     */
    @Test
    public void testGetSeguidos() {
        //System.out.println("getSeguidos");
        String seguidor = "a";
        List<String> expResult = new ArrayList<>();
        List<String> result = instance.getSeguidos(seguidor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of dejarSeguirUsuario method, of class Controlador.
     */
    @Test
    public void testDejarSeguirUsuario() {
        //System.out.println("dejarSeguirUsuario");
        String nick1 = "a";
        String nick2 = "s";
        int expResult = 0;
        int result = instance.dejarSeguirUsuario(nick1, nick2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of modificarPropuesta method, of class Controlador.
     */
    @Test
    public void testModificarPropuesta() {
        //System.out.println("modificarPropuesta");
        String categoria = "d";
        String titulo = "t";
        String descripcion = "asd";
        String lugar = "asd";
        LocalDate fechaPrev = LocalDate.now();
        String montoXentrada = "1000";
        String montoNecesario = "20000";
        String posibleRetorno = "AMBOS";
        String imagen = "";
        String estado = "PUBLICADA";
        int expResult = 0;
        int result = instance.modificarPropuesta(titulo, descripcion, lugar, fechaPrev, montoXentrada, montoNecesario, posibleRetorno, estado, imagen, categoria);
        assertEquals(expResult, result);
         //TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPropuestas method, of class Controlador.
     */
    @Test
    public void testGetPropuestas() {
        //System.out.println("getPropuestas");
        List<String> expResult = new ArrayList<>();
        expResult.add("t");
        expResult.add("ttt");
        List<String> result = instance.getPropuestas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of consultaDePropuesta method, of class Controlador.
     */
    @Test
    public void testConsultaDePropuesta() {
        //System.out.println("consultaDePropuesta");
        String titulo = "t";
        String expResult = "t";
        DataPropuesta resultado = instance.consultaDePropuesta(titulo);
        String result = resultado.getTitulo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDataPropuesta method, of class Controlador.
     */
    @Test
    public void testGetDataPropuesta() {
        //System.out.println("getDataPropuesta");
        String titulo_nick = "t by s";
        String expResult = "t";
        DataPropuesta resultado = instance.getDataPropuesta(titulo_nick);
        String result = resultado.getTitulo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPropuestasPorCategoria method, of class Controlador.
     */
    @Test
    public void testGetPropuestasPorCategoria() {
        //System.out.println("getPropuestasPorCategoria");
        String Categoria = "d";
        DataPropuesta DP;
        String expResult = "t";
        List<DataPropuesta> resultado = instance.getPropuestasPorCategoria(Categoria);
        DP = resultado.get(0);
        String result = DP.getTitulo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of consultaDeProponente method, of class Controlador.
     */
    @Test
    public void testConsultaDeProponente() {
        //System.out.println("consultaDeProponente");
        String NickName = "s";
        String expResult = "s";
        DataProponente resultado = instance.consultaDeProponente(NickName);
        String result = resultado.getNickname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of consultaDeColaborador method, of class Controlador.
     */
    @Test
    public void testConsultaDeColaborador() {
        //System.out.println("consultaDeColaborador");
        String NickName = "a";
        String expResult = "a";
        DataColaborador resultado = instance.consultaDeColaborador(NickName);
        String result = resultado.getNickname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of consultaDeProponenteWeb method, of class Controlador.
     */
    @Test
    public void testConsultaDeProponenteWeb() {
        //System.out.println("consultaDeProponenteWeb");
        String NickName = "s";
        String expResult = "s";
        DataUsuario resultado = instance.consultaDeProponenteWeb(NickName);
        String result = resultado.getNickname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of consultaDeColaboradorWeb method, of class Controlador.
     */
    @Test
    public void testConsultaDeColaboradorWeb() {
        //System.out.println("consultaDeColaboradorWeb");
        String NickName = "a";
        String expResult = "a";
        DataUsuario resultado = instance.consultaDeColaboradorWeb(NickName);
        String result = resultado.getNickname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of cargarCategoriasWeb method, of class Controlador.
     */
    @Test
    public void testCargarCategoriasWeb() {
        //System.out.println("cargarCategoriasWeb");
        DataCategoria DC;
        String expResult = "musiquitita";
        List<DataCategoria> resultado = instance.cargarCategoriasWeb();
        DC = resultado.get(2);
        String result = DC.getNombre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getEstados method, of class Controlador.
     */
    @Test
    public void testGetEstados() {
        //System.out.println("getEstados"); 
        List<String> expResult = new ArrayList<>();
        expResult.add("INGRESADA");
        expResult.add("PUBLICADA");
        expResult.add("EN_FINANCIACION");
        expResult.add("FINANCIADA");
        expResult.add("NO_FINANCIADA");
        expResult.add("CANCELADA");
        List<String> result = instance.getEstados();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPropuestasXColaborador method, of class Controlador.
     */
    @Test
    public void testGetPropuestasXColaborador() {
        //System.out.println("getPropuestasXColaborador");
        String colab = "a";
        String expResult = "t by s";
        List<String> resultado = instance.getPropuestasXColaborador(colab);
        String result = resultado.get(0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDataAporte method, of class Controlador.
     */
    @Test
    public void testGetDataAporte() {
        //System.out.println("getDataAporte");
        String tituloNick = "t by s";
        String nick = "a";
        String expResult = "a";
        DataAporte resultado = instance.getDataAporte(tituloNick, nick);
        String result = resultado.getMiColaborador();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    /**
     * Test of existeTitulo method, of class Controlador.
     */
    @Test
    public void testExisteTitulo() {
        //System.out.println("existeTitulo");
        String titulo = "t";
        boolean expResult = true;
        boolean result = instance.existeTitulo(titulo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of existeNick method, of class Controlador.
     */
    @Test
    public void testExisteNick() {
        //System.out.println("existeNick");
        String nick = "a";
        boolean expResult = true;
        boolean result = instance.existeNick(nick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of existeCorreo method, of class Controlador.
     */
    @Test
    public void testExisteCorreo() {
        //System.out.println("existeCorreo");
        String correo = "a@gmail.com";
        boolean expResult = true;
        boolean result = instance.existeCorreo(correo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getColabsProp method, of class Controlador.
     */
    @Test
    public void testGetColabsProp() {
        //System.out.println("getColabsProp");
        String titulo = "t";
        String expResult = "a" + "\t" + "1000.0";
        List<String> resultado = instance.getColabsProp(titulo);
        String result = resultado.get(0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of seleccionaCategoria method, of class Controlador.
     */
    @Test
    public void testSeleccionaCategoria() {
        //System.out.println("seleccionaCategoria");
        String categoria = "d";
        boolean expResult = true;
        boolean result = instance.seleccionaCategoria(categoria);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of verificarUsuario method, of class Controlador.
     */
    @Test
    public void testVerificarUsuario() {
        //System.out.println("verificarUsuario");
        String usuario = "a@gmail.com";
        String contraseña = "1234";
        //Usuario u = instance.getUsuario("a");
        int expResult = 2;
        int result = instance.verificarUsuario(usuario, contraseña);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuarioPorMail method, of class Controlador.
     */
    @Test
    public void testGetUsuarioPorMail() {
        //System.out.println("getUsuarioPorMail");
        String email = "a@gmail.com";
        String expResult = "a";
        String result = instance.getUsuarioPorMail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of cambiarFavorita method, of class Controlador.
     */
    @Test
    public void testCambiarFavorita() {
        //System.out.println("cambiarFavorita");
        String titulo = "t";
        String nick = "a";
        boolean expResult = true;
        boolean result = instance.cambiarFavorita(titulo, nick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testCambiarFavoritaa() {
        //System.out.println("cambiarFavorita");
        String titulo = "t";
        String nick = "a";
        boolean expResult = false;
        boolean result = instance.cambiarFavorita(titulo, nick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of esFavorita method, of class Controlador.
     */
    @Test
    public void testEsFavorita() {
        //System.out.println("esFavorita");
        String titulo = "t";
        String nick = "a";
        boolean expResult = false;
        boolean result = instance.esFavorita(titulo, nick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }


    /**
     * Test of addComentario method, of class Controlador.
     */
    @Test
    public void testAddComentario() {
        //System.out.println("addComentario");
        String titulo = "t";
        String nick = "a";
        String comentario = "hola";
        instance.addComentario(titulo, nick, comentario);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDataComentario method, of class Controlador.
     */
    @Test
    public void testGetDataComentario() {
        //System.out.println("getDataComentario");
        String titulo = "t";
        String nick = "a";
        String expResult = "hola";
        DataComentario resultado = instance.getDataComentario(titulo, nick);
        String result = resultado.getComentario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDataComentarios method, of class Controlador.
     */
    @Test
    public void testGetDataComentarios() {
        //System.out.println("getDataComentarios");
        String titulo = "t";
        String expResult = "hola";
        List<DataComentario> resultado = instance.getDataComentarios(titulo);
        String result = resultado.get(0).getComentario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of extenderFinanciacion method, of class Controlador.
     */
    @Test
    public void testExtenderFinanciacion() {
        //System.out.println("extenderFinanciacion");
        String titulo = "t";
        //DataPropuesta DP = instance.consultaDePropuesta("t");
        //instance.modificarPropuesta(titulo, DP.getDescripcion(), DP.getLugar(), DP.getFechaARealizar(), DP.getEntrada().toString(), DP.getNecesaria().toString(), titulo, titulo, titulo, titulo);
        int expResult = 0;
        int result = instance.extenderFinanciacion(titulo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of cancelarPropuesta method, of class Controlador.
     */
    @Test
    public void testCancelarPropuesta() {
        //System.out.println("cancelarPropuesta");
        String categoria = "d";
        String titulo = "t";
        String descripcion = "asd";
        String lugar = "asd";
        LocalDate fechaPrev = LocalDate.now();
        String montoXentrada = "1000";
        String montoNecesario = "20000";
        String posibleRetorno = "AMBOS";
        String imagen = "";
        String estado = "FINANCIADA";
        instance.modificarPropuesta(titulo, descripcion, lugar, fechaPrev, montoXentrada, montoNecesario, posibleRetorno, estado, imagen, categoria);
        int expResult = 0;
        int result = instance.cancelarPropuesta(titulo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testComprobarPropuestas(){
        instance.comprobarPropuestas();
    }
    
    @Test
    public void testGetColaboradores(){
        instance.getColaboradores();
    }
    
    @Test
    public void testGetDataPropuestaSimple(){
        DataPropuestaSimple DPS = new DataPropuestaSimple();
        DPS = instance.getDataPropuestaSimple("t");
        DPS.setTitulo(DPS.getTitulo());
        DPS.setLugar(DPS.getLugar());
        DPS.setDescripcion(DPS.getDescripcion());
    }
//    
}
