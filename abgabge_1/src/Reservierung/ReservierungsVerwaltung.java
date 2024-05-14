package Reservierung;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.time.LocalDate;


public class ReservierungsVerwaltung {

	private Collection <Reservierung> reservierungen = new ArrayList<>();
	
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

}








