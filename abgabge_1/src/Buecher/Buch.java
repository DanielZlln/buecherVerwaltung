package Buecher;

public class Buch {

	private int buchId;
	private int isbn;
	private String titel;
	private String vDatum;
	private String autor;
	private String genre;
	private int anzahl_vb;
	
	public Buch(int buchId, int isbn, 
			String titel, String vDatum, String autor,
			String genre ,int anzahl_vb) {
		this.buchId = buchId;
		this.isbn = isbn;
		this.titel = titel;
		this.vDatum = vDatum;
		this.autor = autor;
		this.genre = genre;
		this.anzahl_vb = anzahl_vb;
	}

	public int getBuchId() {
		return buchId;
	}

	public void setBuchId(int buchId) {
		this.buchId = buchId;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getvDatum() {
		return vDatum;
	}

	public void setvDatum(String vDatum) {
		this.vDatum = vDatum;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getAnzahl_vb() {
		return anzahl_vb;
	}

	public void setAnzahl_vb(int anzahl_vb) {
		this.anzahl_vb = anzahl_vb;
	}
	
	@Override
	public String toString() {
	    return "Verfuegbares Buch: " + titel + " mit der ISBN: "+ isbn + " BuchID: " + buchId + ". Noch " + anzahl_vb + " verfuegbar.";
	}
	
	
}
