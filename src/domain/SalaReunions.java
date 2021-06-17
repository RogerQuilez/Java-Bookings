package domain;

public class SalaReunions extends Espai {

	//ATRIBUTS
	private boolean aacc;
	private boolean wifi;
	
	//CONSTRUCTORS
	public SalaReunions(Edifici edifici, int planta, String nom, boolean aacc, boolean wifi) {
		super(edifici, planta, nom);
		this.aacc = aacc;
		this.wifi = wifi;
	}
	
	//METODES
	
	//TIPO DE ESPACIO
	@Override
	public String tipusEspai() {
		return "Sala de Reunions";
	}

	//INFORMACIÓN DEL EQUIPAMIENTO DEL ESPACIO
	@Override
	public String[] infoEquipaments() {
		String[] infoEquip = new String[2];
		
		infoEquip[0] = "Aire acondicionat? "; 
		if (this.isAacc() == true) {
			infoEquip[0] += "Si";
		} else {
			infoEquip[0] += "No";
		}
		
		infoEquip[1] = "Accés Wifi? "; 
		if (this.isWifi() == true) {
			infoEquip[1] += "Si";
		} else {
			infoEquip[1] += "No";
		}
		return infoEquip;
	}

	public boolean isAacc() {
		return aacc;
	}

	public void setAacc(boolean aacc) {
		this.aacc = aacc;
	}

	public boolean isWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

}
