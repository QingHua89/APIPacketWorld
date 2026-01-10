package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;

public class ColaboradorImp {
    public static List<Colaborador> obtenerTodos(){
        List<Colaborador> colaboradores = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                colaboradores = conexionBD.selectList("colaborador.obtener-todos");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
    
    public static Respuesta registrar(Colaborador colaborador){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                int filasAfectadas = conexionBD.insert("colaborador.registrar", colaborador);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Registro del colaborador "+colaborador.getNombre()+", guardado correctamente.");
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
    public static Respuesta editar(Colaborador colaborador){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                int filasAfectadas = conexionBD.update("colaborador.editar", colaborador);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Modificaciones en la información del colaborador "+colaborador.getNombre()+", guardado correctamente.");
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
    
    public static Respuesta eliminar(int idColaborador){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                int filasAfectadas = conexionBD.delete("colaborador.eliminar",idColaborador);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("La información del profesor se elimino correctamente.");
                }else{
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, la información no pudo ser eliminada.");
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
    
    public static Respuesta guardarFoto(int idColaborador, byte[] fotografia){
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                Colaborador colaborador = new Colaborador();
                colaborador.setIdColaborador(idColaborador);
                colaborador.setFotografia(fotografia);
                int filasAfectadas = conexionBD.update("colaborador.guardar-foto", colaborador);
                conexionBD.commit();
                if (filasAfectadas > 0){
                  respuesta.setError(false);
                  respuesta.setMensaje("Foto subida correctamente");
                }else{
                    respuesta.setMensaje("Lo sentimos, no se pudo subir la fotografia del colaborador.");
                }
                conexionBD.close();
            }catch (Exception e){
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setMensaje("Lo sentimos, por el momento no hay conexion al almacenamiento");
        }
        return respuesta;
    }
    
    public static Colaborador obtenerFoto (int idColaborador){
        Colaborador colaborador = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD!=null){
            try{
                colaborador=conexionBD.selectOne("colaborador.obtener-foto", idColaborador);
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return colaborador;
    }
    public static List<Colaborador> buscarColaborador (String busqueda){
        List<Colaborador> colaborador = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                colaborador = conexionBD.selectList("colaborador.buscar", busqueda);
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return colaborador;
    }
    
}
