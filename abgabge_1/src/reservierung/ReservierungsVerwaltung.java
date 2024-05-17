package reservierung;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import Buecher.Buch;
import Buecher.BuchVerwaltung;
import Mitglieder.Mitglied;

import java.time.LocalDate;


public class ReservierungsVerwaltung {

	private Collection <Reservierung> reservierungen = new ArrayList<>();
	
	
	// Checken
	public static BuchVerwaltung buchv = new BuchVerwaltung();
	public static Collection<Buch> resBuecher = buchv.getAllBooks();
	//
	
	
	
    public Collection <Reservierung> getAllreservations() {
    	return reservierungen;
    }
	
    public boolean addBooks(Reservierung reservierung) {
    	
    	if (reservierung != null) {
    		
    		return this.reservierungen.add(reservierung);
    		
    	} return false;
    }
    
    public boolean removeBook(Reservierung reservierung) {
    	
    	if (reservierung != null) {
    		
    		return this.reservierungen.remove(reservierung);
    		
    	} return false;
    }
	
    public void readReservierungFromCSV() {
    	
    	FileReader reservationReader = null;
    	
    	LocalDate date = LocalDate.now();

    	try {
    		reservationReader = new FileReader("reservierungen.csv");
    	} catch (FileNotFoundException e) {
    		System.out.println("Datei nicht gefunden!");
    	}
    	
    	Scanner sc = new Scanner(reservationReader);
    	sc.useDelimiter(";|\n");
    	
		while (sc.hasNext()) {

	        int mitgliedsnummer = sc.nextInt();
	        if (mitgliedsnummer != 0) {
	            int isbn = sc.nextInt();
	            String reservierungsdatumStr = sc.next();

	            LocalDate reservierungsdatum = LocalDate.parse(reservierungsdatumStr);

	            boolean isAfter = reservierungsdatum.isAfter(date);

	            if (isAfter) {
	                Reservierung reservierung = new Reservierung(mitgliedsnummer, isbn, reservierungsdatumStr);
	                this.reservierungen.add(reservierung);
	            }
	        }  else {
				break;
			}
		}	
    	
    	sc.close();
    	
    	try {
    		reservationReader.close();
    	} catch (IOException e) {
    		System.err.println("Problem mit der Datei");
    	}
    }
    
    public void writeReservierungInCSV(Collection<Reservierung> reservierungen) {
    	
    	PrintWriter reservationWriter = null;
    	LocalDate resDatum = LocalDate.now().plusDays(3);
    	
    	try {
    		reservationWriter = new PrintWriter("reservierungen.csv");
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}

        for (Reservierung reservierung : reservierungen) {
        	if (reservierung != null) {
        		reservationWriter.write(reservierung.getMitgliedsnummer() + ";" +
						reservierung.getIsbn() + ";" +
						resDatum.toString());
        		reservationWriter.write("\n");
        	} else {
        		break;
        	}
        	
            
        }
    	
    	reservationWriter.close();
    }
    
	public void allReservation(int nummer) {
		int countNum = 1;
		for (Reservierung resBenutzer : reservierungen) {
			int resIsbn = resBenutzer.getIsbn();

			if (resBenutzer.getMitgliedsnummer() == nummer) {
				for (Buch resBuch : resBuecher) {
					if (resBuch.getIsbn() == resIsbn) {
						System.out.println("Nummer: " + countNum);
						System.out.println("Titel: " + resBuch.getTitel());
						System.out.println("Author: " + resBuch.getAutor());
						System.out.println("ISBN: " + resBenutzer.getIsbn());
						System.out.println("Reserviert bis: " + resBenutzer.getReservierungsdatum());

						countNum++;
					}
				}
			}

		}
	}
	
	public Reservierung deleteRes(int nummer) {
		
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
				
				for (Buch buch : resBuecher) {
					System.out.println("schleife");
					if (buch.getIsbn() == isbn) {
						int anzBuecher = buch.getAnzahl_vb();
			
						anzBuecher = anzBuecher + 1;
			
						buch.setAnzahl_vb(anzBuecher);
						
						System.out.println(buch.getAnzahl_vb() + "test");
					}
				}
				
				counter++;
				break;

			} else if (counter < nummer) {
				counter++;
			}

			else {
				System.out.println("Keine Bücher an dieser Stelle vorhanden!");
				System.out.println("---------------------------------------------------------------------------");
			}
		}
		
		return reservierungLoeschen;

	}
}








