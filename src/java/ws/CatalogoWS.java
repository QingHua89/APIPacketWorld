package ws;

import dominio.CatalogoImp;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Cliente;
import pojo.Colaborador;
import pojo.EstadoEnvio;
import pojo.Rol;
import pojo.SucursalNombres;
import pojo.Unidad;

@Path("catalogos")
public class CatalogoWS {
    
    @Path("roles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rol> obtenerRoles(){
        return CatalogoImp.obtenerRoles();
    }
    
//    @Path("obtener-factuldades")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Facultad>  obtenerFacultades(){
//        return CatalogoImp.obtenerfacultades();
//    }
//    
//    @Path("obtener-carreras-facultad/{idFacultad}")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Carrera> obtenerCarrerasFacultad(@PathParam("idFacultad") Integer idFacultad){
//        if(idFacultad != null && idFacultad>0){
//            return CatalogoImp.obtenerCarrerasFacultad(idFacultad);
//        }
//        throw new BadRequestException();
//    }
    
    @Path("sucursales")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SucursalNombres> obtenerSucursales(){
        return CatalogoImp.obtenerSucursales();
    }
    
    @Path("conductores")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerConductores(){
        return CatalogoImp.obtenerConductores();
    }
    
    @Path("unidades")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> obtenerUnidades(){
        return CatalogoImp.obtenerUnidades();
    }
    
    @Path("estadoEnvio")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstadoEnvio> obtenerEstadoEnvio(){
        return CatalogoImp.obtenerEstadoEnvio();
    }
    
    @Path("clientes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> obtenerClientes(){
        return CatalogoImp.obtenerClientesSistemas();
    }
}
