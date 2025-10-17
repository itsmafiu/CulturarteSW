///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package Logica;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//import javax.swing.tree.DefaultMutableTreeNode;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// *
// * @author mafiu
// */
//public class ControladorTest {
//    
//    public ControladorTest() {
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
//    public static void setUpClass() throws Exception {
//    }
//
//    /**
//     * Test of añadirUsuario method, of class Controlador.
//     */
//    @Test
//    public void testAñadirUsuario_8args() {
//        System.out.println("a\u00f1adirUsuario");
//        String nick = "";
//        String nombre = "";
//        String apellido = "";
//        String correo = "";
//        LocalDate fecNac = null;
//        String imagen = "";
//        String contraseña = "";
//        String imagenWeb = "";
//        Controlador instance = new Controlador();
//        int expResult = 0;
//        int result = instance.añadirUsuario(nick, nombre, apellido, correo, fecNac, imagen, contraseña, imagenWeb);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of añadirUsuario method, of class Controlador.
//     */
//    @Test
//    public void testAñadirUsuario_11args() {
//        System.out.println("a\u00f1adirUsuario");
//        String nick = "";
//        String nombre = "";
//        String apellido = "";
//        String correo = "";
//        LocalDate fecNac = null;
//        String imagen = "";
//        String contraseña = "";
//        String direccion = "";
//        String bio = "";
//        String sitioWeb = "";
//        String imagenWeb = "";
//        Controlador instance = new Controlador();
//        int expResult = 0;
//        int result = instance.añadirUsuario(nick, nombre, apellido, correo, fecNac, imagen, contraseña, direccion, bio, sitioWeb, imagenWeb);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of altaCategoria method, of class Controlador.
//     */
//    @Test
//    public void testAltaCategoria_String() {
//        System.out.println("altaCategoria");
//        String nombreCat = "";
//        Controlador instance = new Controlador();
//        int expResult = 0;
//        int result = instance.altaCategoria(nombreCat);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of altaCategoria method, of class Controlador.
//     */
//    @Test
//    public void testAltaCategoria_String_String() {
//        System.out.println("altaCategoria");
//        String nombreCat = "";
//        String nombrePadreCat = "";
//        Controlador instance = new Controlador();
//        int expResult = 0;
//        int result = instance.altaCategoria(nombreCat, nombrePadreCat);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of cargarNodoRaizCategorias method, of class Controlador.
//     */
//    @Test
//    public void testCargarNodoRaizCategorias() {
//        System.out.println("cargarNodoRaizCategorias");
//        Controlador instance = new Controlador();
//        DefaultMutableTreeNode expResult = null;
//        DefaultMutableTreeNode result = instance.cargarNodoRaizCategorias();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of altaAporte method, of class Controlador.
//     */
//    @Test
//    public void testAltaAporte_5args() {
//        System.out.println("altaAporte");
//        String strmiColaborador = "";
//        String strmiPropuesta = "";
//        double $aporte = 0.0;
//        int cantidad = 0;
//        EnumRetorno retorno = null;
//        Controlador instance = new Controlador();
//        int expResult = 0;
//        int result = instance.altaAporte(strmiColaborador, strmiPropuesta, $aporte, cantidad, retorno);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of altaAporte method, of class Controlador.
//     */
//    @Test
//    public void testAltaAporte_6args() {
//        System.out.println("altaAporte");
//        String strmiColaborador = "";
//        String strmiPropuesta = "";
//        double $aporte = 0.0;
//        int cantidad = 0;
//        EnumRetorno retorno = null;
//        LocalDateTime fecAp = null;
//        Controlador instance = new Controlador();
//        int expResult = 0;
//        int result = instance.altaAporte(strmiColaborador, strmiPropuesta, $aporte, cantidad, retorno, fecAp);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUsuarios method, of class Controlador.
//     */
//    @Test
//    public void testGetUsuarios() {
//        System.out.println("getUsuarios");
//        Controlador instance = new Controlador();
//        List<String> expResult = null;
//        List<String> result = instance.getUsuarios();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUsuario method, of class Controlador.
//     */
//    @Test
//    public void testGetUsuario() {
//        System.out.println("getUsuario");
//        String nick = "";
//        Controlador instance = new Controlador();
//        Usuario expResult = null;
//        Usuario result = instance.getUsuario(nick);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getProponentes method, of class Controlador.
//     */
//    @Test
//    public void testGetProponentes() {
//        System.out.println("getProponentes");
//        Controlador instance = new Controlador();
//        List<Proponente> expResult = null;
//        List<Proponente> result = instance.getProponentes();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUsuariosColaboradores method, of class Controlador.
//     */
//    @Test
//    public void testGetUsuariosColaboradores() {
//        System.out.println("getUsuariosColaboradores");
//        Controlador instance = new Controlador();
//        List<Colaborador> expResult = null;
//        List<Colaborador> result = instance.getUsuariosColaboradores();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDataUsuarios method, of class Controlador.
//     */
//    @Test
//    public void testGetDataUsuarios() {
//        System.out.println("getDataUsuarios");
//        Controlador instance = new Controlador();
//        List<DataUsuario> expResult = null;
//        List<DataUsuario> result = instance.getDataUsuarios();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDataUsuarioWeb method, of class Controlador.
//     */
//    @Test
//    public void testGetDataUsuarioWeb() {
//        System.out.println("getDataUsuarioWeb");
//        String nick = "";
//        Controlador instance = new Controlador();
//        DataUsuario expResult = null;
//        DataUsuario result = instance.getDataUsuarioWeb(nick);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getColaboradores method, of class Controlador.
//     */
//    @Test
//    public void testGetColaboradores() {
//        System.out.println("getColaboradores");
//        Controlador instance = new Controlador();
//        List<String> expResult = null;
//        List<String> result = instance.getColaboradores();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPropuestas_Proponentes method, of class Controlador.
//     */
//    @Test
//    public void testGetPropuestas_Proponentes() {
//        System.out.println("getPropuestas_Proponentes");
//        Controlador instance = new Controlador();
//        List<String> expResult = null;
//        List<String> result = instance.getPropuestas_Proponentes();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUsuariosProponentes method, of class Controlador.
//     */
//    @Test
//    public void testGetUsuariosProponentes() {
//        System.out.println("getUsuariosProponentes");
//        Controlador instance = new Controlador();
//        List<String> expResult = null;
//        List<String> result = instance.getUsuariosProponentes();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getSeguidos method, of class Controlador.
//     */
//    @Test
//    public void testGetSeguidos() {
//        System.out.println("getSeguidos");
//        String seguidor = "";
//        Controlador instance = new Controlador();
//        List<String> expResult = null;
//        List<String> result = instance.getSeguidos(seguidor);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of seguirUsuario method, of class Controlador.
//     */
//    @Test
//    public void testSeguirUsuario() {
//        System.out.println("seguirUsuario");
//        String nick1 = "";
//        String nick2 = "";
//        Controlador instance = new Controlador();
//        int expResult = 0;
//        int result = instance.seguirUsuario(nick1, nick2);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of dejarSeguirUsuario method, of class Controlador.
//     */
//    @Test
//    public void testDejarSeguirUsuario() {
//        System.out.println("dejarSeguirUsuario");
//        String nick1 = "";
//        String nick2 = "";
//        Controlador instance = new Controlador();
//        int expResult = 0;
//        int result = instance.dejarSeguirUsuario(nick1, nick2);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of altaPropuesta method, of class Controlador.
//     */
//    @Test
//    public void testAltaPropuesta() {
//        System.out.println("altaPropuesta");
//        String nick = "";
//        String tipo = "";
//        String titulo = "";
//        String descripcion = "";
//        String lugar = "";
//        LocalDate fechaPrev = null;
//        String montoXentrada = "";
//        String montoNecesario = "";
//        EnumRetorno posibleRetorno = null;
//        LocalDate fechaActual = null;
//        String imagen = "";
//        Controlador instance = new Controlador();
//        int expResult = 0;
//        int result = instance.altaPropuesta(nick, tipo, titulo, descripcion, lugar, fechaPrev, montoXentrada, montoNecesario, posibleRetorno, fechaActual, imagen);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of modificarPropuesta method, of class Controlador.
//     */
//    @Test
//    public void testModificarPropuesta() {
//        System.out.println("modificarPropuesta");
//        String titulo = "";
//        String descripcion = "";
//        String lugar = "";
//        LocalDate fechaPrev = null;
//        String montoXentrada = "";
//        String montoNecesario = "";
//        String posibleRetorno = "";
//        String estado = "";
//        String imagen = "";
//        String categoria = "";
//        Controlador instance = new Controlador();
//        int expResult = 0;
//        int result = instance.modificarPropuesta(titulo, descripcion, lugar, fechaPrev, montoXentrada, montoNecesario, posibleRetorno, estado, imagen, categoria);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPropuestas method, of class Controlador.
//     */
//    @Test
//    public void testGetPropuestas() {
//        System.out.println("getPropuestas");
//        Controlador instance = new Controlador();
//        List<String> expResult = null;
//        List<String> result = instance.getPropuestas();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of consultaDePropuesta method, of class Controlador.
//     */
//    @Test
//    public void testConsultaDePropuesta() {
//        System.out.println("consultaDePropuesta");
//        String titulo = "";
//        Controlador instance = new Controlador();
//        DataPropuesta expResult = null;
//        DataPropuesta result = instance.consultaDePropuesta(titulo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDataPropuesta method, of class Controlador.
//     */
//    @Test
//    public void testGetDataPropuesta() {
//        System.out.println("getDataPropuesta");
//        String titulo_nick = "";
//        Controlador instance = new Controlador();
//        DataPropuesta expResult = null;
//        DataPropuesta result = instance.getDataPropuesta(titulo_nick);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDataPropuestaSimple method, of class Controlador.
//     */
//    @Test
//    public void testGetDataPropuestaSimple() {
//        System.out.println("getDataPropuestaSimple");
//        String titulo = "";
//        Controlador instance = new Controlador();
//        DataPropuestaSimple expResult = null;
//        DataPropuestaSimple result = instance.getDataPropuestaSimple(titulo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPropuestasPorCategoria method, of class Controlador.
//     */
//    @Test
//    public void testGetPropuestasPorCategoria() {
//        System.out.println("getPropuestasPorCategoria");
//        String Categoria = "";
//        Controlador instance = new Controlador();
//        List<DataPropuesta> expResult = null;
//        List<DataPropuesta> result = instance.getPropuestasPorCategoria(Categoria);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of consultaDeProponente method, of class Controlador.
//     */
//    @Test
//    public void testConsultaDeProponente() {
//        System.out.println("consultaDeProponente");
//        String NickName = "";
//        Controlador instance = new Controlador();
//        DataProponente expResult = null;
//        DataProponente result = instance.consultaDeProponente(NickName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of consultaDeColaborador method, of class Controlador.
//     */
//    @Test
//    public void testConsultaDeColaborador() {
//        System.out.println("consultaDeColaborador");
//        String NickName = "";
//        Controlador instance = new Controlador();
//        DataColaborador expResult = null;
//        DataColaborador result = instance.consultaDeColaborador(NickName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getSeguidores method, of class Controlador.
//     */
//    @Test
//    public void testGetSeguidores() {
//        System.out.println("getSeguidores");
//        Usuario seguido = null;
//        Controlador instance = new Controlador();
//        List<DataUsuario> expResult = null;
//        List<DataUsuario> result = instance.getSeguidores(seguido);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of consultaDeProponenteWeb method, of class Controlador.
//     */
//    @Test
//    public void testConsultaDeProponenteWeb() {
//        System.out.println("consultaDeProponenteWeb");
//        String NickName = "";
//        Controlador instance = new Controlador();
//        DataUsuario expResult = null;
//        DataUsuario result = instance.consultaDeProponenteWeb(NickName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of consultaDeColaboradorWeb method, of class Controlador.
//     */
//    @Test
//    public void testConsultaDeColaboradorWeb() {
//        System.out.println("consultaDeColaboradorWeb");
//        String NickName = "";
//        Controlador instance = new Controlador();
//        DataUsuario expResult = null;
//        DataUsuario result = instance.consultaDeColaboradorWeb(NickName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of cargarCategoriasWeb method, of class Controlador.
//     */
//    @Test
//    public void testCargarCategoriasWeb() {
//        System.out.println("cargarCategoriasWeb");
//        Controlador instance = new Controlador();
//        List<DataCategoria> expResult = null;
//        List<DataCategoria> result = instance.cargarCategoriasWeb();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEstados method, of class Controlador.
//     */
//    @Test
//    public void testGetEstados() {
//        System.out.println("getEstados");
//        Controlador instance = new Controlador();
//        List<String> expResult = null;
//        List<String> result = instance.getEstados();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPropXEstado method, of class Controlador.
//     */
//    @Test
//    public void testGetPropXEstado() {
//        System.out.println("getPropXEstado");
//        String estado = "";
//        Controlador instance = new Controlador();
//        List<String> expResult = null;
//        List<String> result = instance.getPropXEstado(estado);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPropuestasXColaborador method, of class Controlador.
//     */
//    @Test
//    public void testGetPropuestasXColaborador() {
//        System.out.println("getPropuestasXColaborador");
//        String colab = "";
//        Controlador instance = new Controlador();
//        List<String> expResult = null;
//        List<String> result = instance.getPropuestasXColaborador(colab);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDataAporte method, of class Controlador.
//     */
//    @Test
//    public void testGetDataAporte() {
//        System.out.println("getDataAporte");
//        String tituloNick = "";
//        String nick = "";
//        Controlador instance = new Controlador();
//        DataAporte expResult = null;
//        DataAporte result = instance.getDataAporte(tituloNick, nick);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of borrarAporte method, of class Controlador.
//     */
//    @Test
//    public void testBorrarAporte() {
//        System.out.println("borrarAporte");
//        String tituloNick = "";
//        String nick = "";
//        Controlador instance = new Controlador();
//        instance.borrarAporte(tituloNick, nick);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of existeTitulo method, of class Controlador.
//     */
//    @Test
//    public void testExisteTitulo() {
//        System.out.println("existeTitulo");
//        String titulo = "";
//        Controlador instance = new Controlador();
//        boolean expResult = false;
//        boolean result = instance.existeTitulo(titulo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of existeNick method, of class Controlador.
//     */
//    @Test
//    public void testExisteNick() {
//        System.out.println("existeNick");
//        String nick = "";
//        Controlador instance = new Controlador();
//        boolean expResult = false;
//        boolean result = instance.existeNick(nick);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of existeCorreo method, of class Controlador.
//     */
//    @Test
//    public void testExisteCorreo() {
//        System.out.println("existeCorreo");
//        String correo = "";
//        Controlador instance = new Controlador();
//        boolean expResult = false;
//        boolean result = instance.existeCorreo(correo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getColabsProp method, of class Controlador.
//     */
//    @Test
//    public void testGetColabsProp() {
//        System.out.println("getColabsProp");
//        String titulo = "";
//        Controlador instance = new Controlador();
//        List<String> expResult = null;
//        List<String> result = instance.getColabsProp(titulo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of seleccionaCategoria method, of class Controlador.
//     */
//    @Test
//    public void testSeleccionaCategoria() {
//        System.out.println("seleccionaCategoria");
//        String categoria = "";
//        Controlador instance = new Controlador();
//        boolean expResult = false;
//        boolean result = instance.seleccionaCategoria(categoria);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of verificarUsuario method, of class Controlador.
//     */
//    @Test
//    public void testVerificarUsuario() {
//        System.out.println("verificarUsuario");
//        String usuario = "";
//        String contraseña = "";
//        Controlador instance = new Controlador();
//        int expResult = 0;
//        int result = instance.verificarUsuario(usuario, contraseña);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUsuarioPorMail method, of class Controlador.
//     */
//    @Test
//    public void testGetUsuarioPorMail() {
//        System.out.println("getUsuarioPorMail");
//        String email = "";
//        Controlador instance = new Controlador();
//        String expResult = "";
//        String result = instance.getUsuarioPorMail(email);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of esFavorita method, of class Controlador.
//     */
//    @Test
//    public void testEsFavorita() {
//        System.out.println("esFavorita");
//        String titulo = "";
//        String nick = "";
//        Controlador instance = new Controlador();
//        boolean expResult = false;
//        boolean result = instance.esFavorita(titulo, nick);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of cambiarFavorita method, of class Controlador.
//     */
//    @Test
//    public void testCambiarFavorita() {
//        System.out.println("cambiarFavorita");
//        String titulo = "";
//        String nick = "";
//        Controlador instance = new Controlador();
//        boolean expResult = false;
//        boolean result = instance.cambiarFavorita(titulo, nick);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addComentario method, of class Controlador.
//     */
//    @Test
//    public void testAddComentario() {
//        System.out.println("addComentario");
//        String titulo = "";
//        String nick = "";
//        String comentario = "";
//        Controlador instance = new Controlador();
//        instance.addComentario(titulo, nick, comentario);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDataComentario method, of class Controlador.
//     */
//    @Test
//    public void testGetDataComentario() {
//        System.out.println("getDataComentario");
//        String titulo = "";
//        String nick = "";
//        Controlador instance = new Controlador();
//        DataComentario expResult = null;
//        DataComentario result = instance.getDataComentario(titulo, nick);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDataComentarios method, of class Controlador.
//     */
//    @Test
//    public void testGetDataComentarios() {
//        System.out.println("getDataComentarios");
//        String titulo = "";
//        Controlador instance = new Controlador();
//        List<DataComentario> expResult = null;
//        List<DataComentario> result = instance.getDataComentarios(titulo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of extenderFinanciacion method, of class Controlador.
//     */
//    @Test
//    public void testExtenderFinanciacion() {
//        System.out.println("extenderFinanciacion");
//        String titulo = "";
//        Controlador instance = new Controlador();
//        int expResult = 0;
//        int result = instance.extenderFinanciacion(titulo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of cancelarPropuesta method, of class Controlador.
//     */
//    @Test
//    public void testCancelarPropuesta() {
//        System.out.println("cancelarPropuesta");
//        String titulo = "";
//        Controlador instance = new Controlador();
//        int expResult = 0;
//        int result = instance.cancelarPropuesta(titulo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
////    @org.junit.jupiter.api.BeforeAll
////    public static void setUpClass() throws Exception {
////    }
////
////    @org.junit.jupiter.api.AfterAll
////    public static void tearDownClass() throws Exception {
////    }
////
////    @org.junit.jupiter.api.BeforeEach
////    public void setUp() throws Exception {
////    }
////
////    @org.junit.jupiter.api.AfterEach
////    public void tearDown() throws Exception {
////    }
//    
////    @BeforeAll
////    public static void setUpClass() {
////    }
////    
////    @AfterAll
////    public static void tearDownClass() {
////    }
////    
////    @BeforeEach
////    public void setUp() {
////    }
////    
////    @AfterEach
////    public void tearDown() {
////    }
//
//    /**
//     * Test of añadirUsuario method, of class Controlador.
//     */
//    
//    /////////////////////////////
//    //TODO COMENTADO PARA ABAJO//
//    /////////////////////////////
//    
////    @org.junit.jupiter.api.Test
////    public void testAñadirUsuario_7args() {
////        //System.out.println("a\u00f1adirUsuario");
////        String nick = "esteclaramentenoesta";
////        String nombre = "nahuel";
////        String apellido = "machado";
////        String correo = "esteclaramentenoesta@gmail.com";
////        LocalDate fecNac = null;
////        String imagen = "";
////        String contraseña = "1234";
////        String imagenWeb = "";
////        Controlador instance = new Controlador();
////        int expResult = 1;
////        int result = instance.añadirUsuario(nick, nombre, apellido, correo, fecNac, imagen, contraseña, imagenWeb);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////       // fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of añadirUsuario method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testAñadirUsuario_10args() {
////        System.out.println("a\u00f1adirUsuario");
////        String nick = "";
////        String nombre = "";
////        String apellido = "";
////        String correo = "";
////        LocalDate fecNac = null;
////        String imagen = "";
////        String contraseña = "";
////        String direccion = "";
////        String bio = "";
////        String sitioWeb = "";
////        Controlador instance = new Controlador();
////        int expResult = 0;
////        int result = instance.añadirUsuario(nick, nombre, apellido, correo, fecNac, imagen, contraseña, direccion, bio, sitioWeb);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of altaCategoria method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testAltaCategoria_String() {
////        System.out.println("altaCategoria");
////        String nombreCat = "";
////        Controlador instance = new Controlador();
////        int expResult = 0;
////        int result = instance.altaCategoria(nombreCat);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of altaCategoria method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testAltaCategoria_String_String() {
////        System.out.println("altaCategoria");
////        String nombreCat = "";
////        String nombrePadreCat = "";
////        Controlador instance = new Controlador();
////        int expResult = 0;
////        int result = instance.altaCategoria(nombreCat, nombrePadreCat);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of cargarNodoRaizCategorias method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testCargarNodoRaizCategorias() {
////        System.out.println("cargarNodoRaizCategorias");
////        Controlador instance = new Controlador();
////        DefaultMutableTreeNode expResult = null;
////        DefaultMutableTreeNode result = instance.cargarNodoRaizCategorias();
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of altaAporte method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testAltaAporte_5args() {
////        System.out.println("altaAporte");
////        String strmiColaborador = "";
////        String strmiPropuesta = "";
////        double $aporte = 0.0;
////        int cantidad = 0;
////        EnumRetorno retorno = null;
////        Controlador instance = new Controlador();
////        int expResult = 0;
////        int result = instance.altaAporte(strmiColaborador, strmiPropuesta, $aporte, cantidad, retorno);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of altaAporte method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testAltaAporte_6args() {
////        System.out.println("altaAporte");
////        String strmiColaborador = "";
////        String strmiPropuesta = "";
////        double $aporte = 0.0;
////        int cantidad = 0;
////        EnumRetorno retorno = null;
////        LocalDateTime fecAp = null;
////        Controlador instance = new Controlador();
////        int expResult = 0;
////        int result = instance.altaAporte(strmiColaborador, strmiPropuesta, $aporte, cantidad, retorno, fecAp);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getUsuarios method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testGetUsuarios() {
////        System.out.println("getUsuarios");
////        Controlador instance = new Controlador();
////        List<String> expResult = null;
////        List<String> result = instance.getUsuarios();
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getProponentes method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testGetProponentes() {
////        System.out.println("getProponentes");
////        Controlador instance = new Controlador();
////        List<Proponente> expResult = null;
////        List<Proponente> result = instance.getProponentes();
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getUsuariosColaboradores method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testGetUsuariosColaboradores() {
////        System.out.println("getUsuariosColaboradores");
////        Controlador instance = new Controlador();
////        List<Colaborador> expResult = null;
////        List<Colaborador> result = instance.getUsuariosColaboradores();
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getDataUsuarios method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testGetDataUsuarios() {
////        System.out.println("getDataUsuarios");
////        Controlador instance = new Controlador();
////        List<DataUsuario> expResult = null;
////        List<DataUsuario> result = instance.getDataUsuarios();
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getColaboradores method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testGetColaboradores() {
////        System.out.println("getColaboradores");
////        Controlador instance = new Controlador();
////        List<String> expResult = null;
////        List<String> result = instance.getColaboradores();
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getPropuestas_Proponentes method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testGetPropuestas_Proponentes() {
////        System.out.println("getPropuestas_Proponentes");
////        Controlador instance = new Controlador();
////        List<String> expResult = null;
////        List<String> result = instance.getPropuestas_Proponentes();
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getUsuariosProponentes method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testGetUsuariosProponentes() {
////        System.out.println("getUsuariosProponentes");
////        Controlador instance = new Controlador();
////        List<String> expResult = null;
////        List<String> result = instance.getUsuariosProponentes();
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getSeguidos method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testGetSeguidos() {
////        System.out.println("getSeguidos");
////        String seguidor = "";
////        Controlador instance = new Controlador();
////        List<String> expResult = null;
////        List<String> result = instance.getSeguidos(seguidor);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of seguirUsuario method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testSeguirUsuario() {
////        System.out.println("seguirUsuario");
////        String nick1 = "";
////        String nick2 = "";
////        Controlador instance = new Controlador();
////        int expResult = 0;
////        int result = instance.seguirUsuario(nick1, nick2);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of dejarSeguirUsuario method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testDejarSeguirUsuario() {
////        System.out.println("dejarSeguirUsuario");
////        String nick1 = "";
////        String nick2 = "";
////        Controlador instance = new Controlador();
////        int expResult = 0;
////        int result = instance.dejarSeguirUsuario(nick1, nick2);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of altaPropuesta method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testAltaPropuesta() {
////        System.out.println("altaPropuesta");
////        String nick = "";
////        String tipo = "";
////        String titulo = "";
////        String descripcion = "";
////        String lugar = "";
////        LocalDate fechaPrev = null;
////        String montoXentrada = "";
////        String montoNecesario = "";
////        EnumRetorno posibleRetorno = null;
////        LocalDate fechaActual = null;
////        String imagen = "";
////        Controlador instance = new Controlador();
////        int expResult = 0;
////        int result = instance.altaPropuesta(nick, tipo, titulo, descripcion, lugar, fechaPrev, montoXentrada, montoNecesario, posibleRetorno, fechaActual, imagen);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of modificarPropuesta method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testModificarPropuesta() {
////        System.out.println("modificarPropuesta");
////        String titulo = "";
////        String descripcion = "";
////        String lugar = "";
////        LocalDate fechaPrev = null;
////        String montoXentrada = "";
////        String montoNecesario = "";
////        String posibleRetorno = "";
////        String estado = "";
////        String imagen = "";
////        String categoria = "";
////        Controlador instance = new Controlador();
////        int expResult = 0;
////        int result = instance.modificarPropuesta(titulo, descripcion, lugar, fechaPrev, montoXentrada, montoNecesario, posibleRetorno, estado, imagen, categoria);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getPropuestas method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testGetPropuestas() {
////        System.out.println("getPropuestas");
////        Controlador instance = new Controlador();
////        List<String> expResult = null;
////        List<String> result = instance.getPropuestas();
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of consultaDePropuesta method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testConsultaDePropuesta() {
////        System.out.println("consultaDePropuesta");
////        String titulo = "";
////        Controlador instance = new Controlador();
////        DataPropuesta expResult = null;
////        DataPropuesta result = instance.consultaDePropuesta(titulo);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getDataPropuesta method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testGetDataPropuesta() {
////        System.out.println("getDataPropuesta");
////        String titulo_nick = "";
////        Controlador instance = new Controlador();
////        DataPropuesta expResult = null;
////        DataPropuesta result = instance.getDataPropuesta(titulo_nick);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getPropuestasPorCategoria method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testGetPropuestasPorCategoria() {
////        System.out.println("getPropuestasPorCategoria");
////        String Categoria = "";
////        Controlador instance = new Controlador();
////        List<DataPropuesta> expResult = null;
////        List<DataPropuesta> result = instance.getPropuestasPorCategoria(Categoria);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of consultaDeProponente method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testConsultaDeProponente() {
////        System.out.println("consultaDeProponente");
////        String NickName = "";
////        Controlador instance = new Controlador();
////        DataProponente expResult = null;
////        DataProponente result = instance.consultaDeProponente(NickName);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of consultaDeColaborador method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testConsultaDeColaborador() {
////        System.out.println("consultaDeColaborador");
////        String NickName = "";
////        Controlador instance = new Controlador();
////        DataColaborador expResult = null;
////        DataColaborador result = instance.consultaDeColaborador(NickName);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getEstados method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testGetEstados() {
////        System.out.println("getEstados");
////        Controlador instance = new Controlador();
////        List<String> expResult = null;
////        List<String> result = instance.getEstados();
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getPropXEstado method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testGetPropXEstado() {
////        System.out.println("getPropXEstado");
////        String estado = "";
////        Controlador instance = new Controlador();
////        List<String> expResult = null;
////        List<String> result = instance.getPropXEstado(estado);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getPropuestasXColaborador method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testGetPropuestasXColaborador() {
////        System.out.println("getPropuestasXColaborador");
////        String colab = "";
////        Controlador instance = new Controlador();
////        List<String> expResult = null;
////        List<String> result = instance.getPropuestasXColaborador(colab);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getDataAporte method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testGetDataAporte() {
////        System.out.println("getDataAporte");
////        String tituloNick = "";
////        String nick = "";
////        Controlador instance = new Controlador();
////        DataAporte expResult = null;
////        DataAporte result = instance.getDataAporte(tituloNick, nick);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of borrarAporte method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testBorrarAporte() {
////        System.out.println("borrarAporte");
////        String tituloNick = "";
////        String nick = "";
////        Controlador instance = new Controlador();
////        instance.borrarAporte(tituloNick, nick);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of existeTitulo method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testExisteTitulo() {
////        System.out.println("existeTitulo");
////        String titulo = "";
////        Controlador instance = new Controlador();
////        boolean expResult = false;
////        boolean result = instance.existeTitulo(titulo);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getColabsProp method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testGetColabsProp() {
////        System.out.println("getColabsProp");
////        String titulo = "";
////        Controlador instance = new Controlador();
////        List<String> expResult = null;
////        List<String> result = instance.getColabsProp(titulo);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of seleccionaCategoria method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testSeleccionaCategoria() {
////        System.out.println("seleccionaCategoria");
////        String categoria = "";
////        Controlador instance = new Controlador();
////        boolean expResult = false;
////        boolean result = instance.seleccionaCategoria(categoria);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of verificarUsuario method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testVerificarUsuario() {
////        System.out.println("verificarUsuario");
////        String usuario = "";
////        String contraseña = "";
////        Controlador instance = new Controlador();
////        int expResult = 0;
////        int result = instance.verificarUsuario(usuario, contraseña);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getUsuarioPorMail method, of class Controlador.
////     */
////    @org.junit.jupiter.api.Test
////    public void testGetUsuarioPorMail() {
////        System.out.println("getUsuarioPorMail");
////        String email = "";
////        Controlador instance = new Controlador();
////        String expResult = "";
////        String result = instance.getUsuarioPorMail(email);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
//    
//}
