package domain;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public abstract class Espai implements Reservable {

	// ATRIBUTS
	public static final int MIDA_RESERVES = 1000;
	protected Edifici edifici;
	protected int planta;
	protected String nom;
	private Date[] reserves = new Date[MIDA_RESERVES];
	
	//ATRIBUTOS PARA LA IMPRESIÓN
	public static final int AMPLE_INFORME = 57;
	private static final String TITOL_INFORME = "Equipament";
	private static final String TITOL_CALENDARI = "Calendari de reserves";
	private static final String BORDER_CHAR = "-";
	private static final String DAY_RESERVED = "XX";
	private static final String MIDDLE_BORDER = "  |  ";
	private static final String LEFT_BORDER = "|  ";
	private static final String RIGHT_BORDER = "  |";
	private static final int COL1 = 25;
	private static final int COL2 = 21;
	private static final int DAY_WIDTH = 3;

	// CONSTRUCTOR
	public Espai(Edifici edifici, int planta, String nom) {
		this.edifici = (edifici==null)? new Edifici(null, null, 0) : edifici;
		this.planta = planta;
		this.nom = nom != null ? nom : "";
	}

	// METODES
	
	//IMPRIMIR CABECERA DEL ESPACIO
	public String capcaleraEspai() {
		
		int innerWidth = AMPLE_INFORME - LEFT_BORDER.length() - RIGHT_BORDER.length();
		
		String str = StringUtils.repeat(Espai.BORDER_CHAR, Espai.AMPLE_INFORME) + System.lineSeparator();
		str += Espai.LEFT_BORDER + StringUtils.rightPad(StringUtils.abbreviate(this.edifici.getAdreca(), Espai.COL1), Espai.COL1);
		str += Espai.MIDDLE_BORDER + StringUtils.leftPad(this.tipusEspai(), Espai.COL2) + Espai.RIGHT_BORDER + System.lineSeparator();
		str += Espai.LEFT_BORDER + StringUtils.rightPad("Telf: " + this.edifici.getTelefonFormat(), Espai.COL1);
		str += Espai.MIDDLE_BORDER + StringUtils.leftPad(this.nom, Espai.COL2) + Espai.RIGHT_BORDER + System.lineSeparator();
		str += Espai.LEFT_BORDER + StringUtils.rightPad(StringUtils.abbreviate("Mail: " + this.edifici.getMailContacte(), Espai.COL1), Espai.COL1);
		str += Espai.MIDDLE_BORDER + StringUtils.leftPad("Planta: " + this.planta, Espai.COL2) + Espai.RIGHT_BORDER + System.lineSeparator();
		str += StringUtils.repeat(Espai.BORDER_CHAR, Espai.AMPLE_INFORME) + System.lineSeparator();
		
		str += Espai.LEFT_BORDER + StringUtils.center(StringUtils.upperCase(Espai.TITOL_INFORME), innerWidth) + Espai.RIGHT_BORDER + System.lineSeparator();
		str += StringUtils.repeat(Espai.BORDER_CHAR, Espai.AMPLE_INFORME) + System.lineSeparator();
		
		String[] infoEquipament = this.infoEquipaments();
		if (infoEquipament != null) {
			for (int i = 0; i < infoEquipament.length; i++) {
				str += Espai.LEFT_BORDER + StringUtils.center(StringUtils.abbreviate(infoEquipament[i], innerWidth), innerWidth) + Espai.RIGHT_BORDER + System.lineSeparator();
			}
		}
		str += StringUtils.repeat(Espai.BORDER_CHAR, Espai.AMPLE_INFORME);
		return str;
	}

	public abstract String tipusEspai();

	public abstract String[] infoEquipaments();

	//IMPRIMIR CALENDARIO DEL ESPACIO
	public String calendariEspai(int mes, int any) {
		
		String calendari = "";
		Locale locale = new Locale("CA", "ES");
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM, yyyy", locale);
		int innerWidth = Espai.AMPLE_INFORME - Espai.LEFT_BORDER.length() - Espai.RIGHT_BORDER.length();

		Calendar cal = Calendar.getInstance(locale);
		cal.set(any, mes-1, 1);
		mes = cal.get(Calendar.MONTH);
		any = cal.get(Calendar.YEAR);
		
		//CABECERA CALENDARIO
		calendari = Espai.LEFT_BORDER + StringUtils.center(StringUtils.upperCase(Espai.TITOL_CALENDARI), innerWidth) + Espai.RIGHT_BORDER + System.lineSeparator();
		calendari += Espai.LEFT_BORDER + StringUtils.center(sdf.format(cal.getTime()), innerWidth) + Espai.RIGHT_BORDER + System.lineSeparator();
		calendari += StringUtils.repeat(Espai.BORDER_CHAR, Espai.AMPLE_INFORME) + System.lineSeparator();
		
		//INICIAMOS LOS DIAS EN LA CADENA DE TEXTO
		calendari += Espai.LEFT_BORDER + "DL." + Espai.MIDDLE_BORDER;
		calendari += "DT." + Espai.MIDDLE_BORDER;
		calendari += "DC." + Espai.MIDDLE_BORDER;
		calendari += "DJ." + Espai.MIDDLE_BORDER;
		calendari += "DV." + Espai.MIDDLE_BORDER;
		calendari += "DS." + Espai.MIDDLE_BORDER;
		calendari += "DG." + Espai.RIGHT_BORDER;
		calendari += System.lineSeparator();
		calendari += StringUtils.repeat(Espai.BORDER_CHAR, Espai.AMPLE_INFORME);
		
		//CALENDARIO
		int primerDiaMes = Math.floorMod(cal.get(Calendar.DAY_OF_WEEK) -2, 7);
		int diesMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int primeraSetmana = cal.get(Calendar.WEEK_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, diesMes);
		int setmanes = cal.get(Calendar.WEEK_OF_MONTH) - primeraSetmana + 1;
		
		//IMPRESIÓN DE LOS DIAS DEL MES
		int diaMes = 0;
		String[][] mensual = new String[setmanes][8];
		for (int i = 0; i < mensual.length; i++) {
			for (int j = 0; j < 7; j++) {
				String sDia = "";
				if (diaMes == 0 && j < primerDiaMes) {
					sDia = StringUtils.center(Espai.BORDER_CHAR, Espai.DAY_WIDTH);
				}
				if (diaMes == 0 && j == primerDiaMes) {
					diaMes = 1;
				}
				if (diaMes > 0 && diaMes <= diesMes) {
					Date fechaActual = new Date();
					cal.set(Calendar.DAY_OF_MONTH, diaMes);
					cal.set(Calendar.HOUR_OF_DAY, 0);
				    cal.set(Calendar.MINUTE, 0);
				    cal.set(Calendar.SECOND, 0);
				    cal.set(Calendar.MILLISECOND, 0);
					fechaActual = cal.getTime();
					int k = cercarReserva(fechaActual);
					if (k>= 0) {
						sDia = StringUtils.center(Espai.DAY_RESERVED + "", Espai.DAY_WIDTH);
						diaMes++;
					} else {
						sDia = StringUtils.center(diaMes + "", Espai.DAY_WIDTH);
						diaMes++;
					}
				} else {
					sDia = StringUtils.center(Espai.BORDER_CHAR, Espai.DAY_WIDTH);
				}
				mensual[i][j] = sDia + Espai.MIDDLE_BORDER;
			}
		}
		
		//AÑADIR AL STRING CALENDARIO
		for (int i = 0; i < mensual.length; i++) {
			calendari += System.lineSeparator();
			calendari += Espai.LEFT_BORDER;
			for (int j = 0; j < 7; j++) {
				calendari += mensual[i][j];
			}
		}
		calendari += System.lineSeparator();
		calendari += StringUtils.repeat(Espai.BORDER_CHAR, Espai.AMPLE_INFORME);
		return calendari;	
	}

	private int cercarReserva() {
		for (int i = 0; i < reserves.length; i++) {
			if (reserves[i]==null) {
				return i;
			}
		}
		return -1;
	}

	private int cercarReserva(Date dia) {
		for (int i = 0; i < reserves.length; i++) {
			if (reserves[i]!= null && reserves[i].equals(dia)) {
				return i;
			}
		}
		return -1;
	}

	//AÑADIR NUEVA RESERVA
	@Override
	public boolean afegirReserva(Date dia) {
		if (reserves.length <= MIDA_RESERVES) {
			int i = cercarReserva(dia);
			if (i >= 0) {
				return false;
			} else {
				int j = cercarReserva();
				if (j >= 0) {
					this.reserves[j] = dia;
					return true;
				}
			}			
		}
		return false;
	}

	//CONSULTAR UNA RESERVA
	@Override
	public boolean consultarReserva(Date dia) {
		int i = cercarReserva(dia);
		if (i >= 0) {
			return true;
		}
		return false;
	}

	//ANULAR UNA SERSERVA
	@Override
	public boolean anullarReserva(Date dia) {
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(dia);
		
		cal.add(Calendar.DAY_OF_MONTH, -2);
		
		if (cal.getTime().before(new Date()) && this.tipusEspai() != "Auditori") {
			int i = cercarReserva(dia);
			if (i >= 0) {
				this.reserves[i] = null;
				return true;
			}
		}
		return false;
		
	}

	// GETTERS/SETTERS
	public Edifici getEdifici() {
		return edifici;
	}

	public void setEdifici(Edifici edifici) {
		this.edifici = edifici;
	}

	public int getPlanta() {
		return planta;
	}

	public void setPlanta(int planta) {
		this.planta = planta;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date[] getReserves() {
		return reserves;
	}

	public void setReserves(Date[] reserves) {
		this.reserves = reserves;
	}

	public int getAMPLE_INFORME() {
		return AMPLE_INFORME;
	}

	public int getMIDA_RESERVES() {
		return MIDA_RESERVES;
	}
}
