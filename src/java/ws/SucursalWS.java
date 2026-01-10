package ws;

import com.google.gson.Gson;
import dominio.SucursalImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Sucursal;

@Path("sucursal")
public class SucursalWS {
    @Path("obtener-todas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursal> obtenerTodas(){
        return SucursalImp.obtenerTodas();
    }
    
    @Path("nuevo")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta añadir(String json){
        Gson gson = new Gson();
        try{
            Sucursal sucursal = gson.fromJson(json, Sucursal.class);
            return SucursalImp.añadir(sucursal);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta editar (String json){
        Gson gson = new Gson();
        try{
            Sucursal sucursal = gson.fromJson(json, Sucursal.class);
            return SucursalImp.editar(sucursal);
        }catch (Exception e){
            throw new BadRequestException (e.getMessage());
        }
    }
    
    @Path("baja")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta darBaja(String json) {
        Gson gson = new Gson();
        try{
            Sucursal sucursal = gson.fromJson(json, Sucursal.class);
            return SucursalImp.darBaja(sucursal);
        }catch (Exception e){
            throw new BadRequestException (e.getMessage());
        }
    }
    
    
}
