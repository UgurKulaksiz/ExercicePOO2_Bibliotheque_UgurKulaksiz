package bibliotheque.utilitaires;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Utilitaire {

    private static Scanner sc = new Scanner(System.in);

    public static int choixListe(List liste) {
        affListe(liste);
        return choixElt(liste);
    }

    public static void affListe(List liste) {
        int i = 1;
        for (Object o : liste) {
            System.out.println((i++) + ". " + o);
        }
    }

    public static int choixElt(List liste) {
        int choix;
        do {
            System.out.println("Choix : ");
            choix = sc.nextInt();
            sc.skip("\n");
        } while (choix < 1 || choix > liste.size());

        return choix;
    }

    public static LocalDate lecDate() {
        String[] jma = sc.nextLine().split(" ");
        int j = Integer.parseInt(jma[0]);
        int m = Integer.parseInt(jma[1]);
        int a = Integer.parseInt(jma[2]);

        return LocalDate.of(a, m, j);
    }

    public static LocalTime lecTime() {
        String[] jma = sc.nextLine().split(" ");
        int j = Integer.parseInt(jma[0]);
        int m = Integer.parseInt(jma[1]);
        int a = Integer.parseInt(jma[2]);

        return LocalTime.of(a, m, j);
    }

    public static String getDateFrench(LocalDate d){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MM yyyy");
        return dtf.format(d);
    }

    public static String modifyIfNotBlank(String label, String oldValue){
        System.out.println(label+" : "+oldValue);
        System.out.println("Nouvelle valeur (enter si pas de changement) : ");
        String newValue = sc.nextLine();
        if(newValue.isBlank()) return oldValue;
        return newValue;
    }

}
