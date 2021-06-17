package domain;

public class Auditori extends Espai {
	
	//ATRIBUTS
	private int capacitat;
	
	//CONSTRUCTORS
	public Auditori(Edifici edifici, int planta, String nom, int capacitat) {
		super(edifici, planta, nom);
		this.capacitat = capacitat > 0 ? capacitat : 1;
	}
	
	//METODES
	
	//TIPO DE ESPACIO
	@Override
	public String tipusEspai() {
		return "Auditori";
	}

	
	//INFORMACIÓN DEL EQUIPAMIENTO DEL ESPACIO
	@Override
	public String[] infoEquipaments() {
		String[] infoEquip = new String[1];
		infoEquip[0] = "L'aforament de l'auditori es de " + this.getCapacitat() + " persones"; 
		return infoEquip;
	}

	//GETTERS/SETTERS
	public int getCapacitat() {
		return capacitat;
	}

	public void setCapacitat(int capacitat) {
		this.capacitat = capacitat;
	}
}
