package de.uit.campus;

/** Frei erfundene Beispieldaten, damit die App gefüllt ist. */
final class DemoData {

    private DemoData() {
    }

    static final String UNI_SHORT = "UIT";
    static final String UNI_SLOGAN = "Digital studieren. Überall.";
    static final String STUDENT_FIRST_NAME = "Tobias";
    static final String STUDENT_LAST_NAME = "Feldberg";
    static final String PROGRAM = "Wirtschaftsinformatik (B.Sc.)";
    static final String SEMESTER = "5. Fachsemester · WiSe 2026/27";

    static final int ECTS_DONE = 96;
    static final int ECTS_TOTAL = 180;
    static final String GRADE_AVERAGE = "1,9";

    // Stundenplan: Tag, Uhrzeit, Veranstaltung, Ort / Format
    static final String[][] SCHEDULE = {
            {"Montag", "09:00 – 10:30", "Datenbanken II", "Live-Vorlesung · Raum Online 3"},
            {"Montag", "14:00 – 15:30", "IT-Projektmanagement", "Webinar"},
            {"Dienstag", "18:00 – 19:30", "Statistik für Informatiker", "Live-Vorlesung · Raum Online 1"},
            {"Mittwoch", "10:00 – 11:30", "Softwarearchitektur", "Übung · Breakout-Gruppen"},
            {"Donnerstag", "17:00 – 18:30", "Geschäftsprozessmanagement", "Live-Vorlesung · Raum Online 2"},
            {"Freitag", "ganztägig", "Selbststudium & Lernmodule", "Lernplattform"},
    };

    // Noten: Modul, Note, ECTS
    static final String[][] GRADES = {
            {"Grundlagen der Programmierung", "1,7", "6"},
            {"Mathematik I", "2,3", "6"},
            {"Einführung in die BWL", "1,3", "5"},
            {"Datenbanken I", "1,7", "6"},
            {"Rechnernetze", "2,0", "5"},
            {"Webentwicklung", "1,0", "6"},
            {"Datenbanken II", "–", "6"},
    };

    // News: Datum, Titel, Text
    static final String[][] NEWS = {
            {"28.10.2026", "Neue Lernplattform ist online",
                    "Ab sofort findest du alle Kursmaterialien, Videos und Quizze an einem Ort. Die alte Plattform bleibt bis Jahresende lesbar."},
            {"21.10.2026", "Prüfungsanmeldung geöffnet",
                    "Die Anmeldung für die Prüfungen im Februar ist bis zum 15.11. möglich."},
            {"14.10.2026", "Virtueller Campus-Tag am 2. Dezember",
                    "Lerne Lehrende und Kommiliton:innen kennen – mit Workshops, Vorträgen und Online-Networking."},
            {"07.10.2026", "Bibliothek erweitert E-Book-Angebot",
                    "Über 5.000 neue Fachbücher stehen dir ab sofort digital zur Verfügung."},
    };

    // Termine: Datum, Beschreibung
    static final String[][] EVENTS = {
            {"04.11.", "Online-Sprechstunde Studienberatung"},
            {"15.11.", "Anmeldeschluss Prüfungen"},
            {"02.12.", "Virtueller Campus-Tag"},
            {"22.12. – 06.01.", "Vorlesungsfreie Zeit"},
    };

    // Profil: Bezeichnung, Wert
    static final String[][] PROFILE_STUDY = {
            {"Studienmodell", "Fernstudium, berufsbegleitend"},
            {"Semester", "5. Fachsemester"},
            {"Studienbeginn", "WiSe 2024/25"},
            {"Matrikelnr.", "UIT-204781"},
    };

    static final String[][] PROFILE_CONTACT = {
            {"E-Mail", "tobias.feldberg@uit.example"},
            {"Telefon", "0170 0000000"},
            {"Ort", "Neustadt"},
    };

    // Kontakte: Titel, Details
    static final String[][] CONTACTS = {
            {"Studienservice", "Mo – Fr, 9 – 17 Uhr\nservice@uit.example\n01234 567890"},
            {"Prüfungsamt", "Di & Do, 10 – 14 Uhr\npruefungsamt@uit.example"},
            {"IT-Helpdesk", "Mo – Sa, 8 – 20 Uhr\nhelpdesk@uit.example\n01234 567899"},
            {"Bibliothek", "Digital rund um die Uhr\nbibliothek@uit.example"},
            {"Adresse", "UIT\nAm Innovationsring 21\n12345 Neustadt"},
    };
}
