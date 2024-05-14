package Mitglieder;

import java.io.FileNotFoundException;
//import Mitglieder.Mitglied;
import java.io.FileReader;
import java.io.IOException;
//import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class MitgliederVerwaltung {
	
    private Collection <Mitglied> mitglieder = new ArrayList<>();
    
    public Collection <Mitglied> getAllUser() {
    	return mitglieder;
    }
    
	public boolean login(String email, String password) {
		for (Mitglied mitglied : mitglieder) {
			if (mitglied.getMail().equals(email)) {
				if (mitglied.getPasswort().equals(password)) {
					return true;
					
				}

			}

		}
		return false;
	}
	
    public boolean addMitglied(Mitglied mitglied) {
    	
    	if (mitglied != null) {
    		
    		return this.mitglieder.add(mitglied);
    		
    	} return false;
    }
    
    public boolean removeMitglied(Mitglied mitglied) {
    	
    	if (mitglied != null) {
    		
    		return this.mitglieder.remove(mitglied);
    		
    	} return false;
    }
    
    public Collection <Mitglied> returnMitglied(String fileName) {
    	return mitglieder;
    }
	
    public void readMitgliederFromCSV() {
    	
    	FileReader mitgliedReader = null;

    	
    	try {
    		mitgliedReader = new FileReader("mitglieder.csv");
    	} catch (FileNotFoundException e) {
    		System.out.println("Datei nicht gefunden!");
    	}
    	
    	Scanner sc = new Scanner(mitgliedReader);
    	sc.useDelimiter(";|\n");
    	
		while (sc.hasNext()) {
			
			int mitgliedsnummer = sc.nextInt();
			String vorname =  sc.next();
			String nachname = sc.next();
			String eMail = sc.next();
			String geburtsdatum =  sc.next();
			String passwort = sc.next();
			
			Mitglied mitglied = null;
			
			mitglied = new Mitglied(mitgliedsnummer, vorname, nachname, eMail, geburtsdatum, passwort);

			this.mitglieder.add(mitglied);
			
		}	
    	
    	sc.close();
    	
    	try {
    		mitgliedReader.close();
    	} catch (IOException e) {
    		System.err.println("Problem mit der Datei");
    	}
    }
    
}
