package dominio;

import pojo.AsociarEnvio;
import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class AsociacionImp {
    public static List<AsociarEnvio> obtenerTodos(){
        List<AsociarEnvio> asociaciones = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                asociaciones = conexionBD.selectList("asociacion.todas");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return asociaciones;
    }
    
    public static Respuesta registrar(AsociarEnvio asociacion){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                int filasAfectadas = conexionBD.insert("asociacion.asociar", asociacion);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Asociacion guardado correctamente.");
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
