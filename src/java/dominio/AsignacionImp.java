package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.AsignarUnidad;

public class AsignacionImp {
    public static List<AsignarUnidad> obtenerTodos(){
        List<AsignarUnidad> asignaciones = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                asignaciones = conexionBD.selectList("asignacion.todas-asignaciones");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return asignaciones;
    }
    
    public static Respuesta registrar(AsignarUnidad asignarUnidad){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                int filasAfectadas = conexionBD.insert("asignacion.asignar", asignarUnidad);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Asignacion guardada correctamente.");
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
    
    public static Respuesta editar(AsignarUnidad asignarUnidad){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                int filasAfectadas = conexionBD.update("asignacion.retirar", asignarUnidad);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Se ha desasignado el conductor con la unidad correctamente.");
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
}
