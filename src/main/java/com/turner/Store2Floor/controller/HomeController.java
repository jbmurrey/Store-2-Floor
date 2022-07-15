package com.turner.Store2Floor.controller;
import java.util.List;
import com.turner.Store2Floor.dao.ItemDao;
import com.turner.Store2Floor.dao.UserDao;
import com.turner.Store2Floor.model.Item;
import com.turner.Store2Floor.model.User;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class HomeController {
    @Path("/users")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> users(){
    	UserDao user = new UserDao();
    	return user.getAllUsers();
    }
    
    
    @Path("/items")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> items(){
    	ItemDao itemdao = new ItemDao();
    	return itemdao.getAllItems();
    }
    
    @Path("/item/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getItembyID(@PathParam("id") int id){
    	ItemDao itemdao = new ItemDao();
    	return itemdao.getItembyID(id);
    }
    
    @Path("/postItem")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Item postItem(Item item){
    	ItemDao itemdao = new ItemDao();
    	itemdao.addItem(item);
    	return item;
    }
    
    @Path("/deleteItembyID/{id}")
    @POST
    public boolean deleteItem(@PathParam("id")int id){
    	ItemDao itemdao = new ItemDao();
    	itemdao.deleteItem(id);
    	return itemdao.deleteItem(id);
    }
}
