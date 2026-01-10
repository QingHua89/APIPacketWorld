package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;

public class ClienteImp {
    public static List<Cliente> obtenerTodos(){
        List<Cliente> clientes = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                clientes = conexionBD.selectList("cliente.obtener-todos");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return clientes;
    }
    
    public static Respuesta registrar(Cliente cliente){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                int filasAfectadas = conexionBD.insert("cliente.registrar", cliente);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Registro del cliente "+cliente.getNombre()+", guardado correctamente.");
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
    public static Respuesta editar(Cliente cliente){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                int filasAfectadas = conexionBD.update("cliente.editar", cliente);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Modificaciones en la información del cliente "+cliente.getNombre()+", guardado correctamente.");
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
    
    public static Respuesta eliminar(int idCliente){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD != null){
            try{
                int filasAfectadas = conexionBD.delete("cliente.eliminar",idCliente);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("La información del cliente se elimino correctamente.");
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
    
    public static List<Cliente> buscarCliente (String busqueda){
        List<Cliente> cliente = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                cliente = conexionBD.selectList("cliente.buscar", busqueda);
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return cliente;
    }
}
