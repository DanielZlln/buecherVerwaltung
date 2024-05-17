package Test;

import java.time.LocalDate;
import java.util.Scanner;

import Buecher.Buch;
import Buecher.BuchVerwaltung;
import Mitglieder.Mitglied;
import Mitglieder.MitgliederVerwaltung;
import reservierung.Reservierung;
import reservierung.ReservierungsVerwaltung;

import java.util.Collection;
//import java.util.InputMismatchException;
//import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;

public class Test {

	public static MitgliederVerwaltung mitv = new MitgliederVerwaltung();
	public static BuchVerwaltung buchv = new BuchVerwaltung();
	public static ReservierungsVerwaltung resv = new ReservierungsVerwaltung();

	public static Collection<Reservierung> reservierungen = resv.getAllreservations();
	public static Collection<Buch> resBuecher = buchv.getAllBooks();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		boolean eingeloggt = false;
		boolean weiter = true;

		LocalDate aktDatum = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String aktuellesDatum = aktDatum.format(formatter);

		mitv.readMitgliederFromCSV();
		buchv.readBuecherFromCSV();
		resv.readReservierungFromCSV();

		while (weiter) {

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
					if (log.getMail().equalsIgnoreCase(mitgliedMail)) {
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

				String eingabe = null;
				String eingabeCheck = sc.next();

				try {
					if (eingabeCheck instanceof String) {
						eingabe = eingabeCheck;
					}
				} catch (Exception e) {
					System.out.println("Eingabe kein Buchstabe!");
					break;
				}

				switch (eingabe.toLowerCase()) {

				case "a": // Reservieren eines Buches

					int vIsbn = 0;

					System.out.println("Sie haben 'Buch reservieren' gewaehlt!");
					
					buchv.showAllBooks();

					System.out.println("Welches Buch moechten Sie reservieren? Bitte dafuer die Buch ID eingeben:");

					int eBuchId = sc.nextInt();
					
					vIsbn = buchv.updateAnzahl(eBuchId, vIsbn, "loeschen"); 

					reservierung = new Reservierung(mitgliedNummer, vIsbn, aktuellesDatum);

					resv.getAllreservations().add(reservierung);

					System.out.println("Buch mit der ISBN: " + vIsbn + " wurde reserviert!");
					System.out.println("---------------------------------------------------------------------------");

					break;

				case "b": // Anzeigen meiner Reservierungen

					System.out.println("Sie haben 'Meine Reservierung anzeigen' gewaehlt!");
					// Wenn keine reserviert sind dann soll der print nicht angezeigt werden
					System.out.println("Folgende Buecher sind aktuell reserviert von Ihnen.");
					
					resv.allReservation(mitgliedNummer);
					
					System.out.println("zweite");

					allRes(mitgliedNummer);

					System.out.println("---------------------------------------------------------------------------");

					break;

				case "c": // Stornieren einer Reservierung

					System.out.println("Sie haben 'Reservierung stornieren' gewaehlt!");
					// Wenn keine reserviert sind dann soll der print nicht angezeigt werden
					System.out.println("Folgende Buecher sind aktuell reserviert von Ihnen.");

					allRes(mitgliedNummer);

					System.out.println("Welches Buch wollen Sie stornieren? Geben Sie dafür die Nummer ein (1,2,3 usw.).");
					int nummer = sc.nextInt();
					 
					Reservierung reservierungLoeschen = resv.deleteRes(nummer); 

					resv.removeBook(reservierungLoeschen);
					
					//buchung = "hinzufuegen";

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

		}
		while (eingeloggt == true)
			;

		if (weiter) {
			System.out.println("Starte das Programm erneut...");
		}

		sc.close();
	}

	public static void allRes(int nummer) {
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

}
