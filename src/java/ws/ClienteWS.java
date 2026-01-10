package ws;

import com.google.gson.Gson;
import dominio.ClienteImp;
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
import pojo.Cliente;

@Path("cliente")
public class ClienteWS {
    
    @Path("obtener-todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> obtenerTodos() {
        return ClienteImp.obtenerTodos();
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrar(String json){
        Gson gson = new Gson();
        try{
            Cliente cliente = gson.fromJson(json, Cliente.class);
            return ClienteImp.registrar(cliente); 
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
            Cliente cliente = gson.fromJson(json, Cliente.class);
            return ClienteImp.editar(cliente);
        }catch (Exception e){
            throw new BadRequestException (e.getMessage());
        }
    }
    
    @Path("eliminar/{idCliente}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminar (@PathParam("idCliente") int idCliente){
        try{
            return ClienteImp.eliminar(idCliente);
        }catch (Exception e){
            throw new BadRequestException (e.getMessage());
        }
    }
    
    @Path("buscar/{busqueda}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> buscarCliente(@PathParam("busqueda") String busqueda){
        if(busqueda !=null){
            return ClienteImp.buscarCliente(busqueda);
        }
        throw new BadRequestException();
    }
}
