import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.awt.SystemColor.text;

public class Main {

    /* Opgave 1 - Introduktion til undtagelser og undtagelseshåndtering

    --- Teori ---
    Undtagelser bruges til at håndtere fejltilstande i programmer på en kontrolleret måde.
    Fordele ved undtagelseshåndtering inkluderer forbedret kodekvalitet og robusthed.

    --- Hvad er en undtagelse? ---
    En undtagelse er når der opstår en uventet hændelse når programmet prøver at udskrive et output
    I en tekst-I/O programmering vil fejl ofte kunne være:
    - Filen kan ikke findes
    - Filen er beskyttet
    - Hvis der opstår fejl under læsning/skrivning

    --- Løsning ---
    Man vil ofte kunne løse disse fejl ved brug af 'try-catch' blokke.

    --- Eksempel: Håndterer en ArrayIndexOutOfBoundsException ---

    public static void main(String[] args) {

    try {

        // Her laver jeg et array med 3 elementer
        int[] tal = {10, 20, 30};

        System.out.println("\nArrayet har 3 elementer (Indeks 0,1 og 2). ");

        // Her prøver jeg bevist at tilgå et element, der ikke findes, for at finde fejl
        System.out.println("\nForsøger at hente element nr. 5... ");
        int value = tal[5]; // Her vil der ske en fejl da indeks 5 ikke findes

    } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("\nFejl: Du prøvede at tilgå et indeks, der ikke findes i arrayet.");
        System.out.println("Teknisk besked: " + e.getMessage());
    }

        System.out.println("\n---------------------------------");
        System.out.println("Programmet kører videre her!");
        }
    }
    */


    /* Opgave 2 - Undtagelsestyper og deklaration

    --- Teori ---
    Fejl (Error) er fatal og normalt uoprettelige, mens undtagelser (Exception) kan håndteres.
    Kontrollerede undtagelser skal deklareres eller håndteres, mens ukontrollerede undtagelser ikke kræver det.

    --- Hvad er en kontrollerede undtagelse? ---
    Det er en fejl der tvinger en programmør til at specificere hvordan fejlen skal findes/fanges.
    Og derefter behandles inden programmet kan kører.

    Det kan ofte skyldes:
    - Filer (læse/skrive, fil findes ikke eller ingen adgang)
    - Netværk (server utilgængelig, forkert URL)
    - Databaseforbindelser (forkert login, manglende adgang)
    - Tråde (fx InterruptedException)

    --- Hvad er en ukontrollerede undtagelse? ---
    Det er en fejl, der opstår under programmets kørsel
    Her tvinger java ikke programmøren til at løse fejlen med try-catch eller throws (can fix, not need to fix).
    Hvor programmet stadig crashe, hvis fejlen ikke håndteres.

    Det kan ofte skyldes:
    - Division med nul (ArithmeticException)
    - Brug af objekter, der ikke er oprettet (NullPointerException)
    - Forkert typekonvertering (ClassCastException)
    - Indeks uden for arrayets grænser (ArrayIndexOutOfBoundsException)
    - Forkert format på tekst til tal (NumberFormatException)

    --- Eksempel: Undtagelsestyper og deklaration ---

    // Metoden lover, at den kan kaste IOException
    public static void readFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("Indhold af filen:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) {
        String filename = "data.txt"; // Deklaration af variabel

        try {
            readFile(filename); // Her kan IOException opstå

        } catch (IOException e) {
            System.out.println("Fejl: Kunne ikke læse filen '" + filename + "'.");
            System.out.println("Teknisk besked: " + e.getMessage());
            System.out.println("Tip: Sørg for, at filen findes i projektmappen, eller prøv med et andet filnavn.");
        }

        System.out.println("\n---------------------------------------------------");
        System.out.println("Programmet kører videre her, selvom filen mangler!");
        }
    }

    */

    /* Opgave 3 - Try-Catch blokke og undtagelsesudbredelse

    --- Teori ---
    En undtagelse kan udbredes gennem metodekald, hvis den ikke håndteres i den oprindelige metode.
    Try-catch blokke bruges til at fange og håndtere undtagelser.

    --- Hvordan udbedres en undtagelse igennem metodekald? ---
    I programmering (fx Java) kan en metode “kaste” en undtagelse, som så kan håndteres af den metode, der kalder den.
    Dette giver fleksibilitet, fordi det er op til den kaldende metode at beslutte, hvordan fejlen skal håndteres
    Dette kaldes ofte “boble op” (exception bubbling).

    Det kan oftest løses ved:
    - At erklære undtagelsen med throws i metodesignaturen
    - At kalde metoden inden for en try-catch blok
    - At håndtere fejlen i den kaldende metode med en venlig fejlbesked
    - At lade metoden fokusere på sin kerneopgave og lade fejlhåndtering ske ét sted

    --- Eksempel: Demonstration af undtagelsesudbredelse gennem flere metode ---

    // Metode 1: Læser første linje fra en fil og kaster IOException
    public static String readFirstLine(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.readLine();
        }
    }

    // Metode 2: Kalder readFirstLine og sender IOException videre
    public static String getFileContent(String filename) throws IOException {
        return readFirstLine(filename); // exception “bubbles up”
    }

    public static void main(String[] args) {
        String filename = "src/data.txt"; // Filnavn

        try {
            String line = getFileContent(filename); // exception håndteres her
            System.out.println("Første linje: " + line);

     } catch (IOException e) {
        System.out.println("Fejl: Kunne ikke læse filen '" + filename + "'.");
        System.out.println("Detaljer: " + e.getMessage());
     }

     System.out.println("\nProgrammet fortsætter her!");
     */

     /* Opgave 4 - Finally klausul og information fra undtagelsesobjekter

     --- Teori ---
     Finally blokken udføres altid, uanset om der opstår en undtagelse eller ej.
     Undtagelsesobjekter kan give detaljeret information om undtagelsen.

     --- Hvad er formålet med finally blokken? ---
     Formålet med finally blokken er at garantere, at en bestem kodeblok bliver udført.
     Også selvom der skulle opstå en udtagelse(fejl) i den tilhørende try-blok, eller kørere fejlfrit.

     Dens brug:
     - Oprydningsopgaver der lukker filer
     - Frigive ressourcer for at forhindre hukommelseslækage

     --- Eksempel: Brug finally blokken for at sikre at en ressource altid frigives ---

     public static void main(String[] args) {
        BufferedReader reader = null;
        String filename = "src/data.txt"; // Korrekt relativ sti

     // Tjek om filen findes, og opret den hvis ikke
        File file = new File(filename);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("Hej verden!");
                writer.newLine();
                writer.write("Dette er en testfil.");
                System.out.println("Filen blev oprettet, da den ikke fandtes.");

            } catch (IOException e) {
                System.out.println("Kunne ikke oprette filen: " + e.getMessage());
                return; // Stop programmet hvis filen ikke kan oprettes
            }
        }

     // Læs filen med finally for at frigive ressourcen
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            System.out.println("Første linje: " + line);

        } catch (IOException e) {
            System.out.println("Fejl ved læsning af filen: " + e.getMessage());

        } finally {
            try {
                if (reader != null) {
                    reader.close();
                    System.out.println("Filen blev lukket korrekt.");
                }
            } catch (IOException e) {
                System.out.println("Kunne ikke lukke filen: " + e.getMessage());
            }
        }

        System.out.println("Programmet fortsætter her!");
    }
    */

    /* Opgave 5 - Brugerdefinerede Undtagelsesklasser

    --- Teori ---
    Brugerdefinerede undtagelser kan oprettes ved at extends Exception klassen.
    De bruges til at repræsentere specifikke fejltilstande i applikationen.

    --- Hvorfor opretter man brugerdefinerede undtagelser? ---
    For at specificere fejl, som standard-undtagelser ikke dækker
    For at gøre koden mere læsbar og forståelig
    For at skille forskellige fejltyper ad (fx “ulovligt brugernavn” vs. “negativ saldo”)
    For at tvinge kaldende metoder til at håndtere specifikke situationer
    For at give mere meningsfulde fejlbeskeder til brugeren

    --- Hvordan opretter man brugerdefinerede undtagelser? ---
    Brugerdefinerede undtagelser oprettes, når man vil håndtere fejl, som de indbyggede undtagelser ikke dækker.
    Man laver sin egen klasse, der arver fra Exception eller RuntimeException, og tilføjer en konstruktør med en fejlbesked.
    De bruges til at gøre koden mere overskuelig og til at give mere meningsfulde fejlbeskeder.
    Man kaster undtagelsen med throw, og metoden skal deklarere den med throws, hvis det er en kontrolleret undtagelse.

    --- Eksempel: Brugerdefineret undtagelsesklasse og dens brug ---

    // Metode 1: Læser første linje fra en fil og kaster brugerdefineret exception, hvis filen er tom
    public static String readFirstLine(String filename) throws IOException, emptyFileException {

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        // 'try-with-ressorces' er her med til at sikre, at filen automatisk lukkes, når jeg er færdige

            String line = reader.readLine();
            // Her læser programmet den første linje i filen

            if (line == null || line.trim().isEmpty()) {
            // Viser at filen er tom (fx at linjen = null eller linjen indeholder mellemrum)

                throw new emptyFileException("Filen '" + filename + "' er tom!");
                // Her kaster min brugerdefinerede exception en specifik fejlbesked ud i output
            }

            return line;
            // Hvis filen ikke er tom, så retunere den, den første linje
        }
        // BufferReader lukkes automatik her
    }

    // Metode 2: Kalder readFirstLine og sender exceptions videre
    public static String getFileContent(String filename) throws IOException, emptyFileException {

        // Her gør metoden ikke andet end at kalde readFirstLine'en
        // Hvis der her opstår en fejl, så sendes den videre til main()
        return readFirstLine(filename); // exception “bubbles up”
    }

    public static void main(String[] args) {

        String filename = "src/emptyDocument"; // Husk at filen skal findes i projektmappen
        // Her angives stien til filen som skal læses
        // Her skal der altid sørges for at der finden en fil med det brugte navn i min src-mappe

        try {
            String line = getFileContent(filename);
            // Her forsøger programmet at læse filens første linje

            System.out.println("Første linje: " + line);
            // Hvis alt går godt, så udskrives den første linje til konsollen

        } catch (emptyFileException e) {
        // Hvis filen er tom, så fanger min brugerdefinerede fejlen her

            System.out.println("Fejl: " + e.getMessage());
            // Eksempel på output: "Fejl: Filen 'src/emptyDocument.txt' er tom!"

        } catch (IOException e) {
        // Hvis filen ikke kan findes eller læses, så vil det blive fanget som en almindelig IOException

            System.out.println("Fejl: Kunne ikke læse filen '" + filename + "'.");
            System.out.println("Detaljer: " + e.getMessage());
            // Eksempel på output: "Fejl: Kunne ikke læse filen 'src/emptyDocument.txt'."
            // "Detaljer: src\emptyDocument.txt (No such file or directory)"
        }

        System.out.println("\n---------------------------");
        System.out.println("Programmet fortsætter her!");
        // Denne sout linje kører altid, uanset om der opstår en fejl eller ej
    }

    // Brugerdefineret undtagelsesklasse
    // Klassen repræsentere mine egne typer fejl som: "filen er tom"
    private static class emptyFileException extends Exception {
        public emptyFileException(String message) {
        // Konstruktøren modtager fejlbeskeden

            super(message);
            // Kalder superklassens (Exception) konstruktør, og så bliver beskeden hentet med getMessage()
            }
        }
    }

    */

    /* Opgave 6 - Filhåndtering og tekst I/O

    --- Teori ---
    Filhåndtering indebærer oprettelse, læsning, skrivning og manipulation af filer og mapper.
    PrintWriter og Scanner klasserne bruges til at skrive og læse tekstdata til og fra filer.

    --- Hvordan bruger man File klassen til fil- og mappemanipulation? ---
    - File file = new File("sti/til/fil.txt"); -> Repræsentere en fil eller mappe, men opretter ikke filen automatisk.
    - exists() -> Tjekker om filen/mappen findes
    - createNewFile() -> Opretter en fil, hvis den ikke findes
    - mkdir() / mkdirs() -> Opretter en mappe (evt. en hel sti)
    - getName(), getAbsolutePath(), length(), isFile() og isDirectory() -> Har info om filen/mappen
    - delete() -> Sletter filen/mappen (mappen skal dog være tom)
    - list() -> returnerer indholdet af en mappe udskriver navnene som et array
    - Bemærk: File manipulerer er kun struktur/metadata og ikke selve filens indhold. Filen indhold er mere FileReader/BufferedReader


    --- Eksempel: Brug af menu - Oprette filer - Skrive data - Læse data - Håndtere undtagelser ---

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Fil Menu ---");
            System.out.println("1. Opret ny fil");
            System.out.println("2. Skriv indput til fil");
            System.out.println("3. Læs indput fra fil");
            System.out.println("4. Afslut "); // Her håndteres undtagelserne
            System.out.println("Vælg én af mulighederne: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": // Opret fil
                    createFile();
                    break;

                case "2":
                    ; // Skriver indput i fil
                    writeFile();
                    break;

                case "3": // Læser indput i fil
                    readFile();
                    break;

                case "4":
                    System.out.println("Programmet afsluttes...");
                    return;

                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    // Opretter en fil
    private static void createFile() {
        System.out.print("Indtast filnavn: ");
        String filename = scanner.nextLine();

        File file = new File(filename);

        try {
            if (file.createNewFile()) {
                System.out.println("Filen '" + filename + "' blev oprettet.");
            } else {
                System.out.println("Filen '" + filename + "' findes allerede.");
            }
        } catch (IOException e) {
            System.out.println("Fejl: Kunne ikke oprette filen.");
            System.out.println("Detaljer: " + e.getMessage());
        }
    }

    // Skriver data til fil
    private static void writeFile() {
        System.out.println("Indtast filnavn: ");
        String filename = scanner.nextLine();

        System.out.println("Skriv tekst der skal skrives i filen: ");
        String text = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(text);
            writer.newLine();
            System.out.println("Teksten er skrevet til filen.");

        } catch (IOException e) {
            System.out.println("Fejl: Kunne ikke skrive til filen.");
            System.out.println("Detaljer: " + e.getMessage());
        }
    }

    // Læser første linje fra fil
    private static void readFile() {
        System.out.print("Indtast filnavn: ");
        String filename = scanner.nextLine();

        try {
            String line = readFirstLine(filename);
            System.out.println("Første linje: " + line);
        } catch (EmptyFileException e) {
            System.out.println("Fejl: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Fejl: Kunne ikke læse filen '" + filename + "'.");
            System.out.println("Detaljer: " + e.getMessage());
        }
    }

    // Metode til at læse første linje og kaste brugerdefineret undtagelse, hvis filen er tom
    private static String readFirstLine(String filename) throws IOException, EmptyFileException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            if (line == null || line.trim().isEmpty()) {
                throw new EmptyFileException("Filen '" + filename + "' er tom!");
            }
            return line;
        }
    }

    // Brugerdefineret exception
    private static class EmptyFileException extends Exception {
        public EmptyFileException(String message) {
            super(message);
            }
        }
    }

    */

    /* Opgave 7 - Avanceret filmanipulation med menu

    --- Eksempel: Opret fil, Opret mappe, Skriv indput til fil, Læs indput fra fil, Rediger eksisterende indput i fil, Slet fil og Slet mappe ---

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> folders = new ArrayList<>(); // Liste med oprettede mapper

    public static void main(String[] args) {
        while (true) {
            printMenu(); // Vis kun handlingerne
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createFolder();
                    break;

                case "2":
                    createFileInFolder();
                    break;

                case "3":
                    writeFileInFolder();
                    break;

                case "4":
                    readFileInFolder();
                    break;

                case "5":
                    editFileInFolder();
                    break;

                case "6":
                    deleteFileInFolder();
                    break;

                case "7":
                    deleteFolder();
                    break;

                case "8":
                    System.out.println("Programmet afsluttes...");
                    return;

                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    // --- Print kun menu ---
    private static void printMenu() {
        System.out.println("\n--- Fil Menu ---");
        System.out.println("\n1. Opret ny mappe");
        System.out.println("2. Opret ny fil i mappe");
        System.out.println("3. Skriv data til fil i mappe");
        System.out.println("4. Læs data fra fil i mappe");
        System.out.println("5. Rediger fil i mappe");
        System.out.println("6. Slet fil i mappe");
        System.out.println("7. Slet mappe");
        System.out.println("8. Afslut");
        System.out.print("\nVælg én af mulighederne: ");
    }

    // --- Opret ny mappe ---
    private static void createFolder() {
        System.out.println("\n-------------------------------------------------------------------");
        System.out.print("Indtast Mappe-navn: ");
        String folderName = scanner.nextLine();
        File folder = new File(folderName);

        if (folder.exists()) {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Mappen findes allerede.");
        } else if (folder.mkdirs()) {
            folders.add(folderName); // Gemmer mappen i listen
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Mappen '" + folderName + "' blev oprettet.");
        } else {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Fejl: Kunne ikke oprette mappen.");
        }
    }

    // --- Vælg mappe ved at skrive navnet ---
    private static String chooseFolderByName() {
        if (folders.isEmpty()) {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Ingen mapper er oprettet endnu.");
            return null;
        }

        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("Eksisterende mapper:");
        for (String f : folders) {
            System.out.println("- " + f);
        }

        System.out.println("\n-------------------------------------------------------------------");
        System.out.print("Skriv navnet på mappen: ");
        String folderName = scanner.nextLine().trim();

        if (folders.contains(folderName)) {
            return folderName;
        } else {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Mappen findes ikke.");
            return null;
        }
    }

    // --- Vælg fil i mappe ---
    private static String chooseFile(String folder) {
        File dir = new File(folder);
        String[] files = dir.list();
        if (files == null || files.length == 0) {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Ingen filer i mappen.");
            return null;
        }

        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("Filer i mappen '" + folder + "':");
        for (String f : files) {
            System.out.println("- " + f);
        }

        System.out.println("\n-------------------------------------------------------------------");
        System.out.print("Skriv filnavnet: ");
        String filename = scanner.nextLine().trim();

        File file = new File(folder, filename);
        if (file.exists()) {
            return filename;
        } else {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Filen findes ikke.");
            return null;
        }
    }

    // --- Opret fil i valgt mappe ---
    private static void createFileInFolder() {
        String folder = chooseFolderByName();
        if (folder == null) return;

        System.out.println("\n-------------------------------------------------------------------");
        System.out.print("Indtast filnavn: ");
        String filename = scanner.nextLine();
        File file = new File(folder, filename);

        try {
            if (file.createNewFile()) {
                System.out.println("\n-------------------------------------------------------------------");
                System.out.println("Filen '" + filename + "' blev oprettet i mappen '" + folder + "'.");
            } else {
                System.out.println("\n-------------------------------------------------------------------");
                System.out.println("Filen findes allerede i denne mappe.");
            }
        } catch (IOException e) {
            System.out.println("Fejl: Kunne ikke oprette filen.");
            System.out.println("Detaljer: " + e.getMessage());
        }
    }

    // --- Skriv data til fil i mappe ---
    private static void writeFileInFolder() {
        String folder = chooseFolderByName();
        if (folder == null) return;
        String filename = chooseFile(folder);
        if (filename == null) return;

        System.out.println("\n-------------------------------------------------------------------");
        System.out.print("Indtast tekst: ");
        String text = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(folder, filename), true))) {
            writer.write(text);
            writer.newLine();
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Teksten er skrevet til filen.");
        } catch (IOException e) {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Fejl: Kunne ikke skrive til filen.");
            System.out.println("Detaljer: " + e.getMessage());
        }
    }

    // --- Læs data fra fil i mappe ---
    private static void readFileInFolder() {
        String folder = chooseFolderByName();
        if (folder == null) return;
        String filename = chooseFile(folder);
        if (filename == null) return;

        try {
            String line = readFirstLine(new File(folder, filename).getPath());
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Første linje: " + line);

        } catch (EmptyFileException e) {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Fejl: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Fejl: Kunne ikke læse filen.");
            System.out.println("Detaljer: " + e.getMessage());
        }
    }

    // --- Rediger fil i mappe ---
    private static void editFileInFolder() {
        String folder = chooseFolderByName();
        if (folder == null) return;
        String filename = chooseFile(folder);
        if (filename == null) return;

        System.out.println("\n-------------------------------------------------------------------");
        System.out.print("Indtast ny tekst (overskriver eksisterende indhold): ");
        String text = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(folder, filename)))) {
            writer.write(text);
            writer.newLine();
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Filen er blevet opdateret.");

        } catch (IOException e) {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Fejl: Kunne ikke redigere filen.");
            System.out.println("Detaljer: " + e.getMessage());
        }
    }

    // --- Slet fil i mappe ---
    private static void deleteFileInFolder() {
        String folder = chooseFolderByName();
        if (folder == null) return;
        String filename = chooseFile(folder);
        if (filename == null) return;

        File file = new File(folder, filename);
        if (!file.exists()) {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Filen findes ikke.");

        } else if (file.delete()) {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Filen '" + filename + "' blev slettet fra mappen '" + folder + "'.");

        } else {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Fejl: Kunne ikke slette filen.");
        }
    }

    // --- Slet mappe ---
    private static void deleteFolder() {
        String folder = chooseFolderByName();
        if (folder == null) return;

        File dir = new File(folder);
        String[] files = dir.list();

        if (files != null && files.length > 0) {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Pas på, der er filer i mappen:");
            for (String f : files) {
                System.out.println("- " + f);
            }

            System.out.println("\n-------------------------------------------------------------------");
            System.out.print("Ønsker du stadig at slette mappen? (ja/nej): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            if (!answer.equals("ja")) {
                System.out.println("\n-------------------------------------------------------------------");
                System.out.println("Mappen blev ikke slettet.");
                return;
            }
        }

        // Forsøg at slette mappen
        if (dir.delete()) {
            folders.remove(folder); // Fjern fra liste
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Mappen '" + folder + "' blev slettet.");
        } else {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Fejl: Kunne ikke slette mappen. Sørg for, at den er tom.");
        }
    }

    // --- Læs første linje fra fil med brugerdefineret exception ---
    private static String readFirstLine(String filename) throws IOException, EmptyFileException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            if (line == null || line.trim().isEmpty()) {
                System.out.println("\n-------------------------------------------------------------------");
                throw new EmptyFileException("Filen '" + filename + "' er tom!");
            }
            return line;
        }
    }

    // --- Brugerdefineret exception ---
    private static class EmptyFileException extends Exception {
        public EmptyFileException(String message) {
            super(message);
            }
        }
    }
    */
}