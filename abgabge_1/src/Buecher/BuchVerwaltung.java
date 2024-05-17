package Buecher;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
//import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

//import Mitglieder.Mitglied;

public class BuchVerwaltung {

	private Collection <Buch> buecher = new ArrayList<>();
	    
	    public Collection <Buch> getAllBooks() {
	    	return buecher;
	    }
		
	    public boolean addBooks(Buch buch) {
	    	
	    	if (buch != null) {
	    		
	    		return this.buecher.add(buch);
	    		
	    	} return false;
	    }
	    
	    public boolean removeBook(Buch buch) {
	    	
	    	if (buch != null) {
	    		
	    		return this.buecher.remove(buch);
	    		
	    	} return false;
	    }
	    
	    public Collection <Buch> returnBuch(String fileName) {
	    	return buecher;
	    }
		
	    public void readBuecherFromCSV() {
	    	
	    	FileReader bookReader = null;
	
	    	
	    	try {
	    		bookReader = new FileReader("buecher.csv");
	    	} catch (FileNotFoundException e) {
	    		System.out.println("Datei nicht gefunden!");
	    	}
	    	
	    	Scanner sc = new Scanner(bookReader);
	    	sc.useDelimiter(";|\n");
	    	
			while (sc.hasNext()) {
				
				int buchId = sc.nextInt();
				int isbn = sc.nextInt();
				String titel =  sc.next();
				String vDatum = sc.next();
				String autor = sc.next();
				String genre =  sc.next();
				int anzahl_vb = sc.nextInt();
				
				Buch buch = null;
				
				buch = new Buch(buchId, isbn, titel, vDatum, autor, genre, anzahl_vb);
	
				this.buecher.add(buch);
				
			}	
	    	
	    	sc.close();
	    	
	    	try {
	    		bookReader.close();
	    	} catch (IOException e) {
	    		System.err.println("Problem mit der Datei");
	    	}
	    }
	
	    public void updateBuecherInCsv() {
	        try (FileWriter writer = new FileWriter("buecher.csv")) {
	            for (Buch buch : buecher) {
	                writer.write(buch.getBuchId() + ";");
	                writer.write(buch.getIsbn() + ";");
	                writer.write(buch.getTitel() + ";");
	                writer.write(buch.getvDatum() + ";");
	                writer.write(buch.getAutor() + ";");
	                writer.write(buch.getGenre() + ";");
	                writer.write(String.valueOf(buch.getAnzahl_vb()));
	                
	                writer.write("\n");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void showAllBooks() {
			for (Buch buch : buecher) {
				if (buch.getAnzahl_vb() >= 1) {
					String buchDetails = buch.toString();
					System.out.println(buchDetails);
				} else {
					System.out.println("Aktuell nicht verfuegbar:");
					System.out.println(buch.getTitel());
				}
			}
	    }
	    
	    public int updateAnzahl(int id, int isbn, String buchung) {
	    	
			for (Buch buch : buecher) {
				if (buch.getBuchId() == id) {
					isbn = buch.getIsbn();
					int anzBuecher = buch.getAnzahl_vb();

		            if (buchung.equals("loeschen")) {
		                anzBuecher = anzBuecher - 1;
		            } else {
		                anzBuecher = anzBuecher + 1;
		            }

					buch.setAnzahl_vb(anzBuecher);
				}
			}
			
			return isbn;
	    }
	    
//		for (Buch buch : buchv.returnBuch("buecher.csv")) {
//		if (buch.getIsbn() == isbn) {
//			int anzBuecher = buch.getAnzahl_vb();
//
//			anzBuecher = anzBuecher + 1;
//
//			buch.setAnzahl_vb(anzBuecher);
//		}
//	}
	    
	    
	    
	}
	
	
	
	
	
	
