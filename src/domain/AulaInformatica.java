package domain;

public class AulaInformatica extends Espai {
	
	//ATRIBUTS
	private int equips;
	private boolean impressora;
	private boolean projector;
	private String user;
	private String password;
	
	//CONSTRUCTORS
	public AulaInformatica(Edifici edifici, int planta, String nom, int equips, boolean impressora, boolean projector, String user, String password) {
		super(edifici, planta, nom);
		this.equips = equips > 0 ? equips : 1;
		this.impressora = impressora;
		this.projector = projector;
		this.user = user != null ? user : "";
		this.password = password != null ? password : "";
	}

	//METODES
	
	//TIPO DE ESPACIO
	@Override
	public String tipusEspai() {
		return "Aula informatica";
	}

	//INFORMACIÓN DEL EQUIPAMIENTO DEL ESPACIO
	@Override
	public String[] infoEquipaments() {
		String[] infoEquip = new String[4];
		infoEquip[0] = "L'aula disposa de " + this.getEquips() + " ordinadors";
		infoEquip[1] = "L'accés als equips es: " + this.getUser() + " " + this.getPassword();
		if (this.isImpressora() == false) {
			infoEquip[2] = "[NO] hi ha impressora";
		} else {
			infoEquip[2] = "[Si] hi ha impressora";
		}
		if (this.isProjector() == false) {
			infoEquip[3] = "[NO] hi ha projector";
		} else {
			infoEquip[3] = "[Si] hi ha projector";
		}
		return infoEquip;
	}

	//GETTERS/SETTERS
	public int getEquips() {
		return equips;
	}

	public void setEquips(int equips) {
		this.equips = equips;
	}

	public boolean isImpressora() {
		return impressora;
	}

	public void setImpressora(boolean impressora) {
		this.impressora = impressora;
	}

	public boolean isProjector() {
		return projector;
	}

	public void setProjector(boolean projector) {
		this.projector = projector;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
