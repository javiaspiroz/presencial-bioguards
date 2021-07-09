package application;

import java.time.LocalDate;
import java.time.LocalTime;

public class EntradaCalendario {
	
	private int Id;
	
	private String Title;
	
	private LocalDate StartDate;
	
	private LocalTime StartTime;

	private LocalDate EndDate;
	
	private LocalTime EndTime;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public LocalDate getStartDate() {
		return StartDate;
	}

	public void setStartDate(LocalDate startDate) {
		StartDate = startDate;
	}

	public LocalTime getStartTime() {
		return StartTime;
	}

	public void setStartTime(LocalTime startTime) {
		StartTime = startTime;
	}

	public LocalDate getEndDate() {
		return EndDate;
	}

	public void setEndDate(LocalDate endDate) {
		EndDate = endDate;
	}

	public LocalTime getEndTime() {
		return EndTime;
	}

	public void setEndTime(LocalTime endTime) {
		EndTime = endTime;
	}
}
