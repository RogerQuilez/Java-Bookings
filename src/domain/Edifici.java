package domain;

public class Edifici {

	// ATRIBUTS
	private String adreca;
	private String mailContacte;
	private int telefon;

	// CONSTRUCTORS
	public Edifici(String adreca, String mailContacte, int telefon) {
		this.adreca = adreca != null ? adreca : "";
		this.mailContacte = mailContacte != null ? mailContacte : "";
		this.telefon = validarTelefon(telefon) == true ? telefon : 999999999;
	}

	// METODES
	
	//FORMATEAR TELEFONO
	public String getTelefonFormat() {
		String telefonString = String.valueOf(this.getTelefon());
		return telefonString.replaceFirst("(\\d{3})(\\d{2})(\\d{2})(\\d{2})", "$1 $2 $3 $4");
	}

	//VALIDAR TELEFONO
	private boolean validarTelefon(int telefon) {
		char telChar = String.valueOf(telefon).charAt(0);
		if (String.valueOf(telefon).length() == 9 && (telChar == '6' || telChar == '9')) {
			return true;
		}
		return false;
	}

	// GETTERS/SETTERS
	public String getAdreca() {
		return adreca;
	}

	public void setAdreca(String adreca) {
		this.adreca = adreca;
	}

	public String getMailContacte() {
		return mailContacte;
	}

	public void setMailContacte(String mailContacte) {
		this.mailContacte = mailContacte;
	}

	public int getTelefon() {
		return telefon;
	}

	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}

}
