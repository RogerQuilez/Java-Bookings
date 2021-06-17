package domain;

import java.util.Date;

public interface Reservable {
	
	//INTERFAZ
	boolean afegirReserva(Date dia);
	boolean consultarReserva(Date dia);
	boolean anullarReserva(Date dia);
}
