package pl.modelo;

import javax.ws.rs.client.ClientBuilder;

public class Consumidor {

	public String funcion1(Producto p) {
		return ClientBuilder.newClient().target("http://localhost:8080/proyecto4/rest/proveedor/altaProducto").request()
				.post(Entity.entity(p, MediaType.APPLICATION_JSON)).readEntity(String.class); // Devuelve un Strig notificando si se ha dado de alta el producto (null) o no (mensaje errror)	
	}

	public Calendario funcion2() {
		return ClientBuilder.newClient().target("http://localhost:8080/proyecto4/rest/proveedor/leeFichero").request(MediaType.APPLICATION_JSON)
				.get(Carrito.class);
	}
}
