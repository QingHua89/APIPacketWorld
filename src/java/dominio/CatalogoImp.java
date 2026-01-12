package dominio;

import java.util.List;
import modelo.mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Carrera;
import pojo.Cliente;
import pojo.Colaborador;
import pojo.EstadoEnvio;
import pojo.Facultad;
import pojo.Paquete;
import pojo.Rol;
import pojo.SucursalNombres;
import pojo.Unidad;

public class CatalogoImp {
    
    public static List<Rol> obtenerRoles(){
        List<Rol> roles = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD !=null){
            try{
                roles = conexionBD.selectList("catalogos.roles");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return roles;
    }
    
//    public static List<Facultad> obtenerfacultades(){
//        List<Facultad> facultades = null;
//        SqlSession conexionBD = MybatisUtil.getSession();
//        if (conexionBD != null){
//            try{
//                facultades = conexionBD.selectList("catalogos.obtener-facultades");
//                conexionBD.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        return facultades;
//    }
//    
//    public static List<Carrera> obtenerCarrerasFacultad (int idFacultad){
//        List<Carrera> carreras = null;
//        SqlSession conexionBD = MybatisUtil.getSession();
//        if (conexionBD !=null){
//            try{
//                carreras = conexionBD.selectList("catalogos.obtener-carrera-facultad", idFacultad);
//                conexionBD.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        return carreras;
//    }
    
    public static List<SucursalNombres> obtenerSucursales(){
        List<SucursalNombres> sucursales = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD !=null){
            try{
                sucursales = conexionBD.selectList("catalogos.sucursales");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sucursales;
    }
    
    public static List<Colaborador> obtenerConductores(){
        List<Colaborador> sucursales = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD !=null){
            try{
                sucursales = conexionBD.selectList("catalogos.conductores");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sucursales;
    }
    
    public static List<Unidad> obtenerUnidades(){
        List<Unidad> sucursales = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD !=null){
            try{
                sucursales = conexionBD.selectList("catalogos.unidades");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sucursales;
    }
    
    public static List<EstadoEnvio> obtenerEstadoEnvio(){
        List<EstadoEnvio> estados = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD !=null){
            try{
                estados = conexionBD.selectList("catalogos.estadoEnvio");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return estados;
    }
    
    public static List<Cliente> obtenerClientesSistemas(){
        List<Cliente> clientes = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD !=null){
            try{
                clientes = conexionBD.selectList("catalogos.clientes");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return clientes;
    }
    
    public static List<Paquete> obtenerPaquetes(){
        List<Paquete> paquetes = null;
        SqlSession conexionBD = MybatisUtil.getSession();
        if (conexionBD !=null){
            try{
                paquetes = conexionBD.selectList("catalogos.paquetes");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return paquetes;
    }
}
