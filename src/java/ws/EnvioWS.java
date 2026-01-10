package ws;

import com.google.gson.Gson;
import dominio.ColaboradorImp;
import dominio.EnvioImp;
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
import pojo.Colaborador;
import pojo.Envio;

@Path("envios")
public class EnvioWS {
    
    @Path("todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envio> obtenerTodos() {
        return EnvioImp.obtenerTodo();
    }
    
    @Path("nuevo")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrar(String json){
        Gson gson = new Gson();
        try{
            Envio envio = gson.fromJson(json, Envio.class);
            return EnvioImp.registrarEnvio(envio); 
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
            Envio envio = gson.fromJson(json, Envio.class);
            return EnvioImp.editarEnvio(envio);
        }catch (Exception e){
            throw new BadRequestException (e.getMessage());
        }
    }
    
    @Path("buscar/{busqueda}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envio> buscarColaborador(@PathParam("busqueda") String busqueda){
        if(busqueda !=null){
            return EnvioImp.buscarGuia(busqueda);
        }
        throw new BadRequestException();
    }
}
