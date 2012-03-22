package no.ntnu.fp.g20.model;

import java.util.ArrayList;

import no.ntnu.fp.g20.database.DBAppointment;

public class Week {
	
	private ArrayList<Appointment> listOfAppointments;
	private Appointment app;
	private int week;
	private Appointment[][] appsInThisWeek;

	public Week(){
		
		appsInThisWeek = new Appointment[12][7];
		listOfAppointments = DBAppointment.getAllAppointments();
	}
	
	public Appointment[][] getAppointmentsInWeek(int week) {
		Appointment a;
		for (int i = 0; i < listOfAppointments.size(); i++) {
			a = listOfAppointments.get(i);
			if (a.getStartTime().WEEK_OF_YEAR == week) {
				appsInThisWeek[a.getStartTime().HOUR_OF_DAY][a.getStartTime().DAY_OF_WEEK] = a;
			}
		}
	}

		
		week = app.getStartTime().WEEK_OF_YEAR;

}
