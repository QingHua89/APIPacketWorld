package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.HistorialCambios;

public class HistorialImp {
    
    public static List<HistorialCambios> obtenerTodo(){
        List<HistorialCambios> cambios = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                cambios = conexionBD.selectList("historial.historial-completo");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return cambios;
    }
    
    public static Respuesta registrar(HistorialCambios cambios){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                int filasAfectadas = conexionBD.insert("historial.registrar", cambios);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Registro el estado correctamente.");
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
    
    public static Respuesta actualizacionEstado(HistorialCambios cambios){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                int filasAfectadas = conexionBD.update("historial.actualizar-envio", cambios);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Se actualizo el estado correctamente.");
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
}
