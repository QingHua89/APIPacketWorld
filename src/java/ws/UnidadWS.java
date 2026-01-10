package ws;

import com.google.gson.Gson;
import dominio.UnidadImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import pojo.Unidad;

@Path("unidad")
public class UnidadWS {
    
    @Path("obtener-todas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> obtenerTodas(){
        return UnidadImp.obtenerTodas();
    }
    
    @Path("nuevo")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta añadir(String json){
        Gson gson = new Gson();
        try{
            Unidad unidad = gson.fromJson(json, Unidad.class);
            return UnidadImp.añadir(unidad);
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
            Unidad unidad = gson.fromJson(json, Unidad.class);
            return UnidadImp.editar(unidad);
        }catch (Exception e){
            throw new BadRequestException (e.getMessage());
        }
    }
    
    @Path("buscar/{busqueda}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> buscarColaborador(@PathParam("busqueda") String busqueda){
        if(busqueda !=null){
            return UnidadImp.buscarUnidad(busqueda);
        }
        throw new BadRequestException();
    }
    
    @Path("dar-baja")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta darBaja(Unidad unidad, @QueryParam("motivo") String motivo) {
        if (unidad != null && motivo != null) {
            return UnidadImp.darBaja(unidad, motivo);
        }
        throw new BadRequestException();
    }
}
