package dominio;

import dto.RSAutenticacionColab;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.apache.ibatis.session.SqlSession;
import modelo.mybatis.MybatisUtil;
import pojo.Colaborador;

public class AutenticacionImp {
    
    public static RSAutenticacionColab autenticacionColaborador(String numeroPersonal, String password){
        RSAutenticacionColab respuesta = new RSAutenticacionColab();
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD != null){
            try{
                HashMap<String,String> parametros = new LinkedHashMap<>();
                parametros.put("numeroPersonal",numeroPersonal);
                parametros.put("password",password);
                Colaborador colaborador= conexionBD.selectOne("autenticacion.colaborador",parametros);//idMapper.idOperacion
                if (colaborador != null){
                    //credenciales correctas
                    respuesta.setError(false);
                    respuesta.setMensaje("Credenciales corectas del colaborador: "+colaborador.getNombre());
                    respuesta.setColaborador(colaborador);
                }else{
                    //flujo alterno 2 credenciales incorrectas
                    respuesta.setError(true);
                    respuesta.setMensaje("Credenciales incorrectas. Verifica la información.");
                }
                conexionBD.close();
            }catch (Exception e){
                //flujo 3 error al ejecutar algo en la consukta
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            //flujo 1 no hay conexion a la base de datos
            respuesta.setError(true);
            respuesta.setMensaje("La conexion a la información no esta disponible en este momento.");
        }
        return respuesta;
    }
}
