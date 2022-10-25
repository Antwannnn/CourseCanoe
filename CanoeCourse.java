import java.util.Scanner;

public class CanoeCourse {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        int nbCandidats, nbDisqualifiés, nbPortes;
        double[] tabTempsManche1;
        double[] tabTempsManche2;
        double[] tabClassementFinal;

        nbCandidats = saisirNbCandidats();

        nbPortes = saisirNbPortes();

        disqualifier();




    }
    public static int saisirInt(int pfBorne){
        int value = 0;
        while(value > pfBorne){
            System.out.println("Veuillez saisir le nombre de portes ratées pour ce joueur : ");
            value = scanner.nextInt();
        }
        return value;
    }

    public static void saisirTab(double[] pfTab, int pfTailleTab, int indice){
        pfTab[indice] = 0;
        while(pfTab[indice] <= 0){
            pfTab[indice] = scanner.nextInt();
        }
    }
    public static int saisirNbPortes (

    ) {
        int NbPortes;
        System.out.println("Saisissez le nombre de portes de la course, il doit être compris entre 18 et 22 : ");
        NbPortes = scanner.nextInt();
        while (NbPortes < 18 || NbPortes > 22) {
            System.out.println("Veuillez saisir un nombre entre 1 et 49 compris");
            NbPortes = scanner.nextInt();
        }
        return NbPortes;
    }

    public static int saisirNbCandidats (){
        int NbCandidats;
        System.out.println("Saisissez le nombre de candidats à la course, il doit être compris entre 1 et 49 : ");
        NbCandidats = scanner.nextInt();
        while(NbCandidats<1 || NbCandidats >= 50){
            System.out.println("Veuillez saisir un nombre entre 1 et 49 compris");
            NbCandidats = scanner.nextInt();
        }
        return NbCandidats;
    }

    public static boolean disqualifier(double [] tabClassementFinal, int nbCandidats) {
        System.out.println("Ce joueur est-il disqualifié ? y ou n");
        String entree = "/";
        while (!entree.equals("y") || !entree.equals("n")) {
            entree = scanner.next();
        }
        if (entree.equals("y")){
            tabClassementFinal = new double[nbCandidats - 1];
            return true;
        }
        else if (entree.equals("n"))
            return false;
    }

    public static double tempsCompensé(int portesratées, int portestouchées, double tempsManche){
        double tempsFinal, tempsPenalite;
        int pénalitéPortesRatées = 50*1000*portesratées;
        int pénalitéPortesTouchées = 2*1000*portestouchées;

        tempsPenalite = pénalitéPortesTouchées + pénalitéPortesRatées;
        tempsFinal = tempsManche + tempsPenalite;

        return tempsFinal;
    }

}