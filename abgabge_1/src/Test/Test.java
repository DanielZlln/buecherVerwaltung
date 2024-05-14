package Test;

import java.time.LocalDate;
import java.util.Scanner;

import Buecher.Buch;
import Buecher.BuchVerwaltung;
import Mitglieder.Mitglied;
import Mitglieder.MitgliederVerwaltung;
import Reservierung.Reservierung;
import Reservierung.ReservierungsVerwaltung;
import java.util.Collection; 
//import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;


public class Test {
	
	public static MitgliederVerwaltung mitv = new MitgliederVerwaltung();
	public static BuchVerwaltung buchv = new BuchVerwaltung();
	public static ReservierungsVerwaltung resv = new ReservierungsVerwaltung();
	
	public static Collection<Reservierung> reservierungen = resv.getAllreservations();
	public static Collection <Buch> resBuecher = buchv.getAllBooks();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		boolean eingeloggt = false;
		boolean weiter = true;
		
		LocalDate aktDatum = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String aktuellesDatum = aktDatum.format(formatter);;

		
		while (weiter) {
			
			mitv.readMitgliederFromCSV();
			buchv.readBuecherFromCSV();
			resv.readReservierungFromCSV();
			
			Mitglied mitglied = null;
			
			System.out.println("Bitte Anmeldeinformationen eingeben!");
			
			System.out.print("E-Mail Adresse: ");
	    	String mitgliedMail = sc.next();
	    	
	    	System.out.print("Passwort: ");
	    	String mitgliedPasswort = sc.next();
	    	
	    	int mitgliedNummer = 0;
	    	
	    	if (!mitv.login(mitgliedMail, mitgliedPasswort)) {
	    		System.out.println("Login fehlgeschlagen! Programm neu starten, falls ein Login gewünscht ist!");
	    		break;
	    	} else {
	    		for (Mitglied log : mitv.returnMitglied("mitglieder.csv")) {
	    			if(log.getMail().equalsIgnoreCase(mitgliedMail)) {
	    				mitglied = log;
	    				System.out.println("Login erfolgreich!");
	    				eingeloggt = true;
	    				mitgliedNummer = mitglied.getMitgliedsnummer();
	    			}
	    		}
	    	}
	    	
	    	System.out.println(mitglied);
	    	
	    	while (eingeloggt == true) {
	    		
	    		Reservierung reservierung = null;
	    		
	    		System.out.println("Waehlen Sie eine der folgenden Auswahlmoeglichkeiten:");
	    		System.err.println("a) Buch reservieren");
	    		System.err.println("b) Meine Reservierung anzeigen");
	    		System.err.println("c) Reservierung stornieren");
	    		System.err.println("d) Speichern");
	    		System.err.println("e) Abmelden");
	    		System.err.println("f) Programm beenden");
	    		System.out.println("---------------------------------------------------------------------------");
	    		
	    		String eingabe = sc.next();
	    		
	    		switch (eingabe.toLowerCase()) {
	    		
	    		case "a": // Reservieren eines Buches

	    			int vIsbn = 0;
	    			
	    			System.out.println("Sie haben 'Buch reservieren' gewaehlt!");

	    			for (Buch buch : buchv.returnBuch("buecher.csv")) {
	    				if(buch.getAnzahl_vb() >= 1) {
		    				String buchDetails = buch.toString();
		    				System.out.println(buchDetails);
	    				} else {
	    					System.out.println("Aktuell nicht verfuegbar:");
	    					System.out.println(buch.getTitel()); 
	    				}
	    			}
	    			
	    			System.out.println("Welches Buch moechten Sie reservieren? Bitte dafuer die Buch ID eingeben:");
	    			
	    			int eBuchId = sc.nextInt();
	    			
	    			for (Buch buch : buchv.returnBuch("buecher.csv")) {
	    				if(buch.getBuchId() == eBuchId) {
		    				vIsbn = buch.getIsbn();
		    				int anzBuecher = buch.getAnzahl_vb();
		    				
		    				anzBuecher = anzBuecher - 1;
		    				
		    				buch.setAnzahl_vb(anzBuecher);
	    				} 
	    			}
	    			
					reservierung = new Reservierung(mitgliedNummer, vIsbn, aktuellesDatum);
	    			
					resv.getAllreservations().add(reservierung);
	    			
	    			System.out.println("Buch mit der ISBN: " + vIsbn + " wurde reserviert!");
	    			System.out.println("---------------------------------------------------------------------------");
	    			
	    			break;
	    			
	    		case "b": // Anzeigen meiner Reservierungen
	    			
	    			
	    			System.out.println("Sie haben 'Meine Reservierung anzeigen' gewaehlt!");
	    			// Wenn keine reserviert sind dann soll der print nicht angezeigt werden
	    			System.out.println("Folgende Buecher sind aktuell reserviert von Ihnen.");

	    			allRes();
	    			
	    			System.out.println("---------------------------------------------------------------------------");

                    break;
                    
	    		case "c": // Stornieren einer Reservierung 
	    			
	    			System.out.println("Sie haben 'Reservierung stornieren' gewaehlt!");
	    			// Wenn keine reserviert sind dann soll der print nicht angezeigt werden
	    			System.out.println("Folgende Buecher sind aktuell reserviert von Ihnen.");

	    			allRes();
	    			
	    			System.out.println("Welches Buch wollen Sie stornieren? Geben Sie dafür die Nummer ein (1,2,3 usw.).");
	    			int nummer = sc.nextInt();
	    			int counter = 1;
	    			int isbn = 0;
	    			
	    			Reservierung reservierungLoeschen = null;
	    			
	    			for (Reservierung res : reservierungen) {
	    				if (counter == nummer) {
	    					
	    					System.out.println("Reservierung: " + nummer + " wird geloescht!");
	    					
	    					int mitgliedsnummer = res.getMitgliedsnummer();
	    	                isbn = res.getIsbn();
	    	                String reservierungsdatum = res.getReservierungsdatum();
	    					
	    	                System.out.println("Mitgliedsnummer: " + mitgliedsnummer);
	    	                System.out.println("ISBN: " + isbn);
	    	                System.out.println("Reservierungsdatum: " + reservierungsdatum);
	    	                
	    	                reservierungLoeschen = res;
	    	                
	    	                counter++; 

	    				} else {
	    					System.out.println("Keine Bücher an dieser Stelle vorhanden!");
	    					System.out.println("---------------------------------------------------------------------------");
	    					break;
	    				}
	    				
	    			}
	    			
	    			resv.removeBook(reservierungLoeschen);
	    			
	    			for (Buch buch : buchv.returnBuch("buecher.csv")) {
	    				if(buch.getIsbn() == isbn) {
		    				int anzBuecher = buch.getAnzahl_vb();
		    				
		    				anzBuecher = anzBuecher + 1;
		    				
		    				buch.setAnzahl_vb(anzBuecher);
	    				} 
	    			}
	    			
	    			break;
	    			
	    		case "d":
	    			
	    			System.out.println("Sie haben 'Speichern' gewaehlt!");
	    			
	    			resv.writeReservierungInCSV(reservierungen);
	    			
	    			buchv.updateBuecherInCsv();
	    			
	    			// Hier muss noch eine Auswahl ob alle oder nur Nutzer
	    			
	    			System.out.println("Ihre Auswahl wurde gespeichert!");
	    			System.out.println("---------------------------------------------------------------------------");
	    			
	    			break;
	    			
	    		case "e":
	    			
	    			System.out.println("Sie haben 'Abmelden' gewaehlt und wurden erfolgreich abgelmeldet");
	    			System.out.println("---------------------------------------------------------------------------");
	    			
	    			eingeloggt = false;
	    			
	    			break;
	    			
	    		case "f":
	    			
	    			System.out.println("Sie haben 'Programm beenden'!");
	    			System.out.println("---------------------------------------------------------------------------");
	    			
	    			eingeloggt = false;
	    			weiter = false;
	    			
	    		}
	    	}
	    	
		} while (eingeloggt == true);
		
		if (weiter) {
			System.out.println("Starte das Programm erneut...");
		}
		
		sc.close();
	}
	
	public static void allRes() {
		int countNum = 1;
		for (Reservierung resBenutzer : reservierungen) {
			int resIsbn = resBenutzer.getIsbn();
			
			for (Buch resBuch : resBuecher) {
				if (resBuch.getIsbn() == resIsbn) {
					System.out.println("Nummer: "+ countNum);
					System.out.println("Titel: " + resBuch.getTitel());
					System.out.println("Author: " + resBuch.getAutor());
					System.out.println("ISBN: " + resBenutzer.getIsbn());
					System.out.println("Reserviert am: " + resBenutzer.getReservierungsdatum());
					
					countNum++;
				}
			}
		}
	}


}


