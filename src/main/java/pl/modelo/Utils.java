package pl.modelo;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import dl.Calendario;
import dl.Evento;

public class Utils {
	public static Calendario mapToCalendario(String jsonString) {
		System.out.println("AITOOOOOOR");
	    try (JsonReader reader = Json.createReader(new StringReader(jsonString))) {
	        JsonObject jsonCalendario = reader.readObject();
	        System.out.println("JSON completo: " + jsonCalendario.toString()); // Imprimir el JSON completo

	        Calendario calendario = new Calendario();

	        List<Evento> eventos = new ArrayList<>();
	        JsonArray jsonEventos = jsonCalendario.getJsonArray("items");
	        for (int i = 0; i < jsonEventos.size(); i++) {
	            JsonObject jsonEvento = jsonEventos.getJsonObject(i);
	            Evento evento = new Evento();
	            evento.setDtstart(parseDateTime(jsonEvento.getJsonObject("start").getString("dateTime")));
	            evento.setDtend(parseDateTime(jsonEvento.getJsonObject("end").getString("dateTime")));
	            eventos.add(evento);
	            System.out.println("Evento añadido.");
	        }
	        calendario.setEventos(eventos);
	        return calendario;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
    
    public static Date parseDateTime(String dateTimeString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

        try {
            return sdf.parse(dateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
 
}
