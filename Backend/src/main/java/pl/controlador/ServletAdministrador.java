package pl.controlador;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dl.Calendario;
import dl.Evento;

@WebServlet("/ServletAdministrador")
public class ServletAdministrador extends HttpServlet implements Serializable{

    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonBody = request.getParameter("jsonInput");
        try {
            // Deserializar el JSON a un objeto Calendario
                Calendario calendario = new Calendario();
                calendario.setEventos(pl.modelo.Utils.mapToCalendario(jsonBody));
                
                response.getWriter().println("Eventos recibidos y procesados correctamente.");

                // Procesar los eventos
                for (Evento evento : calendario.getEventos()) {
                    // Aquí puedes hacer lo que necesites con cada evento
                    // Por ejemplo, imprimir sus propiedades
                	response.getWriter().println("Evento:" + evento.getSummary());
                	response.getWriter().println("Inicio: " + evento.getDtstart());
                	response.getWriter().println("Fin: " + evento.getDtend());
                }

            // Enviar alguna respuesta si es necesario
            
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Error al procesar el JSON: " + e.getMessage());
        }
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.getWriter().println("Método no accesible por GET y no entiendo nada");
    }
}
