package Mitglieder;

public class Mitglied {
	
	private int mitgliedsnummer;
	private String vorname;
	private String nachname;
	private String eMail;
	private String geburtsdatum;
	private String passwort;
	
	public Mitglied(int mitgliedsnummer, String vorname, String nachname, String eMail, String geburtsdatum, String passwort) {
		this.mitgliedsnummer = mitgliedsnummer;
		this.vorname = vorname;
		this.nachname = nachname;
		this.eMail = eMail;
		this.geburtsdatum = geburtsdatum;
		this.passwort = passwort;
	}
	
	public Mitglied () {
	}
	
	public int getMitgliedsnummer() {
		return mitgliedsnummer;
	}
	public void setMitgliedsnummer(int mitgliedsnummer) {
		this.mitgliedsnummer = mitgliedsnummer;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getGeburtsdatum() {
		return geburtsdatum;
	}
	public void setGeburtsdatum(String geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	
	@Override
	public String toString() {
	    return "Eingeloggt als: " + vorname + " "+ nachname;
	}
	
	
}
