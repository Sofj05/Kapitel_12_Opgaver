import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    /* Opgave 1 - Introduktion til undtagelser og undtagelseshåndtering

        --- Teori ---
        - Undtagelser bruges til at håndtere fejltilstande i programmer på en kontrolleret måde.
        - Fordele ved undtagelseshåndtering inkluderer forbedret kodekvalitet og robusthed.

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

            // Her prøver jeg at bevist at tilgå et element, der ikke findes, for at finde fejl
            System.out.println("\nForsøger at hente element nr. 5... ");
            int value = tal[5]; // Her vil der ske en fejl da indeks 5 ikke findes

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\nFejl: Du prøvede at tilgå et indeks, der ikke findes i arrayet.");
            System.out.println("Teknisk besked: " + e.getMessage());
        }

        System.out.println("\n---------------------------------");
        System.out.println("Programmet kører videre her!"); */



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
        Her tvinger java ikke programmøren til at løse med try-catch eller throws.
        Og her kan programmet stadig crashe, hvis fejlen ikke håndteres.

        Det kan ofte skyldes:
        - Division med nul (ArithmeticException)
        - Brug af objekter, der ikke er oprettet (NullPointerException)
        - Forkert typekonvertering (ClassCastException)
        - Indeks uden for arrayets grænser (ArrayIndexOutOfBoundsException)
        - Forkert format på tekst til tal (NumberFormatException)

        --- Eksempel: Undtagelsestyper og deklaration ---


        // Metoden lover, at den kan kaste IOException
        public static void readFile (String filename) throws IOException {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                System.out.println("Indhold af filen:");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }

        public static void main (String[]args){
            String filename = "src/data.txt"; // Deklaration af variabel

            try {
                readFile(filename); // Her kan IOException opstå
            } catch (IOException e) {
                System.out.println("Fejl: Kunne ikke læse filen '" + filename + "'.");
                System.out.println("Teknisk besked: " + e.getMessage());
                System.out.println("Tip: Sørg for, at filen findes i projektmappen, eller prøv med et andet filnavn.");
            }

            System.out.println("Programmet kører videre her, selvom filen mangler!");
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

}


