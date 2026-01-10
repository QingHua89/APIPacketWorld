package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Sucursal;

public class SucursalImp {
    public static List<Sucursal> obtenerTodas(){
        List<Sucursal> sucursales = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                sucursales = conexionBD.selectList("sucursal.obtener-todas");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sucursales;
    }
    public static Respuesta añadir(Sucursal sucursal){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                int filasAfectadas = conexionBD.insert("sucursal.nuevo", sucursal);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Registro de la sucursal: "+sucursal.getSucursalNombre()+", guardado correctamente.");
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
    
    public static Respuesta editar(Sucursal sucursal){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                int filasAfectadas = conexionBD.update("sucursal.editar", sucursal);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Modificaciones en la información de la sucursal "+sucursal.getSucursalNombre()+", guardado correctamente.");
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
    
    public static Respuesta darBaja(Sucursal sucursal) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null) {
            try {
                if (sucursal.getEstatus() != null && sucursal.getEstatus().equalsIgnoreCase("Activa")) {
                    sucursal.setEstatus("Inactiva");
                    int filasAfectadas = conexionBD.update("sucursal.baja", sucursal);
                    if (filasAfectadas > 0) {
                        conexionBD.commit();
                        respuesta.setError(false);
                        respuesta.setMensaje("La Sucursal: " + sucursal.getSucursalNombre()+ " ha sido dada de baja.");
                    } else {
                        respuesta.setError(true);
                        respuesta.setMensaje("No se pudo actualizar la base de datos.");
                    }
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("La Sucursal no puede darse de baja porque su estatus actual es: " + sucursal.getEstatus());
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
