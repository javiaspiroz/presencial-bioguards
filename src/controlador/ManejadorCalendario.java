package controlador;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import com.calendarfx.view.CalendarView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import application.EntradaCalendario;
import javafx.application.Platform;
import javafx.scene.control.Control;

public class ManejadorCalendario {

	private Vector<EntradaCalendario> entradas;
	private String identificador;
	private int idIndex = 1;
	private CalendarSource calendarSource;
	
	public Control CrearCalendario(String id, boolean readOnly) {
		identificador = id;
		idIndex = 1;
		
		Calendar calendar = new Calendar("Calendario");
		calendar.setReadOnly(readOnly);
		calendarSource = new CalendarSource("Calendarios");
		calendarSource.getCalendars().add(calendar);
		
		CalendarView calendarView = new CalendarView();
		calendarView.getCalendarSources().clear();
		calendarView.getCalendarSources().add(calendarSource);
		calendarView.setShowAddCalendarButton(false);
		calendarView.setRequestedTime(LocalTime.now());
         
        entradas = desserializarJsonAArrayCalendario(identificador);
        for(EntradaCalendario entradaCalendario : entradas) {
        	Entry entry = new Entry(entradaCalendario.getTitle());
        	entry.setId(Integer.toString(idIndex++));
        	Interval interval = new Interval(entradaCalendario.getStartDate(), entradaCalendario.getStartTime(), 
        									 entradaCalendario.getEndDate(), entradaCalendario.getEndTime());
        	entry.setInterval(interval);
        	calendar.addEntry(entry);
        }
		calendar.addEventHandler(evt -> calendarEvent(evt));
		
		Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                    while (true) {
                            Platform.runLater(() -> {
                                    calendarView.setToday(LocalDate.now());
                                    calendarView.setTime(LocalTime.now());
                            });
                            try {
                                    sleep(10000); // update every 10 seconds
                            } catch (InterruptedException e) {
                                    e.printStackTrace();
                            }
                    }
            };
		};
        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
        return calendarView;
	}
	
	private static Vector<EntradaCalendario> desserializarJsonAArrayCalendario(String identificador) {
		Vector<EntradaCalendario> listaEntry = new Vector<EntradaCalendario>();
		File file = new File("ficheros_Calendario\\Calendario_" + identificador + ".json");
		if (file.exists()) {
			try (Reader reader = new FileReader(file)) {
				Gson gson = new Gson();
				Type tipoLista = new TypeToken<Vector<EntradaCalendario>>(){}.getType();
				listaEntry = gson.fromJson(reader, tipoLista);
			} catch (IOException e) {
				FuncionesAuxiliares.getAlertaError("Error al cargar los datos", "No se ha podido cargar los datos del calendario");
			}	
		}
		return listaEntry;
	}
	
	private static boolean serializarArrayAJsonCalendario (Vector <EntradaCalendario> listaEntry, String identificador) {		
		boolean guardado = false;	
		Gson gson =new GsonBuilder().setPrettyPrinting().create();
		try{	
			FileWriter writer = new FileWriter ("ficheros_Calendario\\Calendario_" + identificador + ".json"); 	
			gson.toJson(listaEntry, writer);
			writer.close();
			guardado = true;
		} catch (Exception e) {
			FuncionesAuxiliares.getAlertaError("Error al guardar los datos", "No se ha podido guardar los datos de calendario.");
		}
		return guardado;
	}
	
	private void calendarEvent(CalendarEvent evt) {
		Entry entry = evt.getEntry();		if (evt.isEntryAdded()) {
			EntradaCalendario entradaCalendario = entryToEntradaCalendario(entry);
	        entradas.add(entradaCalendario);
		}
		else if (evt.isEntryRemoved()) {
			eliminarEntrada(entry);
		}
		else {
			// Modificación u otra cosa.
			actualizarEntrada(entry);
		}
		serializarArrayAJsonCalendario(entradas, identificador);
	}
	
	private void actualizarEntrada(Entry entry) {
		for(EntradaCalendario entradaCalendario : entradas) {
			if (entradaCalendario.getId() == Integer.parseInt(entry.getId())) {
				entradaCalendario.setTitle(entry.getTitle());
		        entradaCalendario.setStartDate(entry.getStartDate());
		        entradaCalendario.setStartTime(entry.getStartTime());
		        entradaCalendario.setEndDate(entry.getEndDate());
		        entradaCalendario.setEndTime(entry.getEndTime());
				break;
			}
		}
	}
	
	private void eliminarEntrada(Entry entry) {
		for(EntradaCalendario entradaCalendario : entradas) {
			if (entradaCalendario.getId() == Integer.parseInt(entry.getId())) {
				entradas.remove(entradaCalendario);
				break;
			}
		}
	}
	
	private EntradaCalendario entryToEntradaCalendario(Entry entry) {
        EntradaCalendario entradaCalendario = new EntradaCalendario();
        entradaCalendario.setId(idIndex++);
        entradaCalendario.setTitle(entry.getTitle());
        entradaCalendario.setStartDate(entry.getStartDate());
        entradaCalendario.setStartTime(entry.getStartTime());
        entradaCalendario.setEndDate(entry.getEndDate());
        entradaCalendario.setEndTime(entry.getEndTime());
        return entradaCalendario;
	}
}