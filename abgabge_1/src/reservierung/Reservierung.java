package reservierung;


public class Reservierung {
	
	private int mitgliedsnummer;
	private int isbn;
	private String reservierungsdatum;
	
	public Reservierung(int mitgliedsnummer, int isbn, String aktDatum) {
		this.mitgliedsnummer = mitgliedsnummer;
		this.isbn = isbn;
		this.reservierungsdatum = aktDatum;
	}

	public int getMitgliedsnummer() {
		return mitgliedsnummer;
	}

	public void setMitgliedsnummer(int mitgliedsnummer) {
		this.mitgliedsnummer = mitgliedsnummer;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getReservierungsdatum() {
		return reservierungsdatum;
	}

	public void setReservierungsdatum(String reservierungsdatum) {
		this.reservierungsdatum = reservierungsdatum;
	}
	
	@Override
	public String toString() {
	    return "Reservierte Buecher:" + isbn + " von: "+ mitgliedsnummer + ". Reserviert bis: " + reservierungsdatum;
	}
}
