package ws;

import com.google.gson.Gson;
import dominio.AsignacionImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.AsignarUnidad;


@Path("asignacion")
public class AsignarWS {
    
    @Path("todas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AsignarUnidad> obtenerTodos(){
        return AsignacionImp.obtenerTodos();
    }
    
    @Path("asignar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta asignar(String json){
        Gson gson = new Gson();
        try{
            AsignarUnidad asignarUnidad = gson.fromJson(json, AsignarUnidad.class);
            return AsignacionImp.registrar(asignarUnidad);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("retirar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta editar (String json){
        Gson gson = new Gson();
        try{
            AsignarUnidad asignarUnidad = gson.fromJson(json, AsignarUnidad.class);
            return AsignacionImp.editar(asignarUnidad);
        }catch (Exception e){
            throw new BadRequestException (e.getMessage());
        }
    }
}
