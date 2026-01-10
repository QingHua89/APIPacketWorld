package dominio;

import dto.Respuesta;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import modelo.mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Unidad;

public class UnidadImp {
    public static List<Unidad> obtenerTodas(){
        List<Unidad> unidades = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                unidades = conexionBD.selectList("unidad.obtener-todas");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return unidades;
    }
    
    public static Respuesta añadir(Unidad unidad){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                int filasAfectadas = conexionBD.insert("unidad.nuevo", unidad);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Registro de la unidad: "+unidad.getModelo()+", guardado correctamente.");
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
    
    public static Respuesta editar(Unidad unidad){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                int filasAfectadas = conexionBD.update("unidad.editar", unidad);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Modificaciones en la unidad: "+unidad.getModelo()+", guardada correctamente.");
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

    public static List<Unidad> buscarUnidad (String busqueda){
        List<Unidad> unidades = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                unidades = conexionBD.selectList("unidad.buscar", busqueda);
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return unidades;
    }

    public static Respuesta darBaja(Unidad unidad, String motivo) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null) {
            try {
                if (unidad.getEstatus() != null && unidad.getEstatus().equalsIgnoreCase("Activa")) {
                    unidad.setEstatus("Inactiva");
                    unidad.setMotivoBaja(motivo);
                    int filasAfectadas = conexionBD.update("unidad.baja", unidad);
                    if (filasAfectadas > 0) {
                        conexionBD.commit();
                        respuesta.setError(false);
                        respuesta.setMensaje("La unidad: " + unidad.getModelo() + " ha sido dada de baja.");
                    } else {
                        respuesta.setError(true);
                        respuesta.setMensaje("No se pudo actualizar la base de datos.");
                    }
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("La unidad no puede darse de baja porque su estatus actual es: " + unidad.getEstatus());
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
    
}
