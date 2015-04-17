package com.innova4b.servicio;

import java.util.List;

import com.innova4b.modelo.Avion;
import com.innova4b.modelo.Billete;
import com.innova4b.modelo.Pasajero;

public interface BilleteServicio {
	public List<Billete> listarBilletes();
	public void insertarBillete(Billete billete, Pasajero pasajero);
}
