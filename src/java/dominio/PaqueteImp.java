package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Paquete;

public class PaqueteImp {
    public static List<Paquete> obtenerPaquetes(){
        List<Paquete> paquetes = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD !=null){
            try{
                paquetes = conexionBD.selectList("paquete.paquetes");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return paquetes;
    }
    
    public static Respuesta registrar(Paquete paquete){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                int filasAfectadas = conexionBD.insert("paquete.nuevo", paquete);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Se registro el paquete correctamente.");
                }else{
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, la información no pudo ser guardada.");
                }
                conexionBD.close();
            }catch (Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("Lo sentimos, por el momento no hay conexión al amacenamiento de la información.");
        }
        return respuesta;
    }
    
    public static Respuesta editar(Paquete paquete){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                int filasAfectadas = conexionBD.update("paquete.editar", paquete);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Modificaciones en la información del paquete guardado correctamente.");
                }else{
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, la información no pudo ser modificada.");
                }
                conexionBD.close();
            }catch (Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("Lo sentimos, por el momento no hay conexión al amacenamiento de la información.");
        }
        return respuesta;
    }
    
    public static Respuesta quitarEnvio(Paquete paquete) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null) {
            try {
                if (paquete.getGuia() != null ) {
                    paquete.setGuia(null);
                    int filasAfectadas = conexionBD.update("paquete.eliminar-envio", paquete);
                    if (filasAfectadas > 0) {
                        conexionBD.commit();
                        respuesta.setError(false);
                        respuesta.setMensaje("El paquete ya no pertenece a un envio");
                    } else {
                        respuesta.setError(true);
                        respuesta.setMensaje("No se pudo actualizar la base de datos.");
                    }
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("El envio no puede desvincularse.");
                }
            } catch (Exception e) {
                respuesta.setError(true);
                respuesta.setMensaje("Ocurrió un error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("Error de conexión al almacenamiento de datos.");
        }
        return respuesta;
    }
    
    public static Respuesta agregarEnvio(Paquete paquete) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null) {
            try {
                if (paquete.getGuia() != null && paquete.getIdPaquete() > 0) {
                    int filasAfectadas = conexionBD.update("paquete.agregar-envio", paquete);
                    if (filasAfectadas > 0) {
                        conexionBD.commit();
                        respuesta.setError(false);
                        respuesta.setMensaje("Guía asignada correctamente al paquete.");
                    } else {
                        respuesta.setError(true);
                        respuesta.setMensaje("No se encontró el paquete para actualizar.");
                    }
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("Datos insuficientes (ID o Guía faltante).");
                }
            } catch (Exception e) {
                respuesta.setError(true);
                respuesta.setMensaje("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return respuesta;
    }
    
    public static List<Paquete> buscarPaquete (String busqueda){
        List<Paquete> paquete = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                paquete = conexionBD.selectList("paquete.buscar", busqueda);
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return paquete;
    }
}
