package ws;

import com.google.gson.Gson;
import dominio.AsociacionImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.AsociarEnvio;

@Path("asociacion-envios")
public class AsociacionWS {
    
    @Path("todas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AsociarEnvio> obtenerTodas(){
        return AsociacionImp.obtenerTodos();
    }
    
    @Path("asociar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta agregar(String json){
        Gson gson = new Gson();
        try{
            AsociarEnvio union= gson.fromJson(json, AsociarEnvio.class);
            return AsociacionImp.registrar(union);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
