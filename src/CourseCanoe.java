import java.util.Scanner;

public class CourseCanoe {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        int nbCandidats, nbDisqualifiés, nbPortes;
        double[] tabTempsManche1;
        double[] tabTempsManche2;
        double[] tabClassementFinal;

        int nbPortesRateesManche1;
        int nbPortesRateesManche2;
        int nbPortesToucheesManche1;
        int nbPortesToucheesManche2;

        double tempsCompensesManche1;
        double tempsCompensesManche2;

        double moyenneTempsCompenses;

        nbCandidats = saisirNbCandidats();

        nbPortes = saisirNbPortes();

        for(int i = 1; i <= nbCandidats; i++){

            tabTempsManche1 = new double[nbCandidats];
            tabTempsManche2 = new double[nbCandidats];
            tabClassementFinal = new double[nbCandidats];

            System.out.println("Informations pour le " + i + " ème participant");
            System.out.println("-----------------------------------------------");

            boolean disqualifié = saisirDisqualification(tabClassementFinal, nbCandidats);

            if(!disqualifié){

                saisirTabTemps(tabTempsManche1, nbCandidats, "Veuillez saisir le temps pour le " + i + "ème participant pour la manche 1: ", i);
                nbPortesRateesManche1 = saisirInt(nbPortes, "Veuillez saisir le nombre de portes ratées à la manche 1 pour le " + i + "ème participant : ");
                nbPortesToucheesManche1 = saisirInt(nbPortes, "Veuillez saisir le nombre de portes touchées à la manche 1 pour le " + i + "ème participant : ");
                tempsCompensesManche1 = tempsCompensé(nbPortesToucheesManche1, nbPortesRateesManche1, tabTempsManche1[i]);

                saisirTabTemps(tabTempsManche2, nbCandidats, "Veuillez saisir le temps pour le " + i + "ème participant pour la manche 2: ", i);
                nbPortesRateesManche2 = saisirInt(nbPortes, "Veuillez saisir le nombre de portes ratées à la manche 2 pour le " + i + "ème participant : ");
                nbPortesToucheesManche2 = saisirInt(nbPortes, "Veuillez saisir le nombre de portes touchées à la manche 2 pour le " + i + "ème participant : ");
                tempsCompensesManche2 = tempsCompensé(nbPortesToucheesManche2, nbPortesRateesManche2, tabTempsManche1[i]);

                moyenneTempsCompenses = (tempsCompensesManche1 + tempsCompensesManche2) / 2;
                tabClassementFinal[i] = moyenneTempsCompenses;
            }

        }


    }
    public static int saisirInt(int pfBorne, String pfMessage){
        int value = 0;
        while(value > pfBorne){
            System.out.print(pfMessage);
            value = scanner.nextInt();
        }
        return value;
    }

    public static void saisirTabTemps(double[] pfTab, int pfTailleTab, String pfMessage, int pfIndice){
        pfTab[pfIndice] = 0;
        System.out.println();
        while(pfTab[pfIndice] <= 0){
            System.out.print(pfMessage);
            pfTab[pfIndice] = scanner.nextDouble();
        }
    }
    public static int saisirNbPortes (

    ) {
        int NbPortes;
        System.out.print("Saisissez le nombre de portes de la course, il doit être compris entre 18 et 22 : ");
        NbPortes = scanner.nextInt();
        while (NbPortes < 18 || NbPortes > 22) {
            System.out.print("Veuillez saisir un nombre entre 18 et 22 compris: ");
            NbPortes = scanner.nextInt();
        }
        return NbPortes;
    }

    public static int saisirNbCandidats (){
        int NbCandidats;
        System.out.print("Saisissez le nombre de candidats à la course, il doit être compris entre 1 et 49 : ");
        NbCandidats = scanner.nextInt();
        while(NbCandidats<1 || NbCandidats >= 50){
            System.out.print("Veuillez saisir un nombre entre 1 et 49 compris: ");
            NbCandidats = scanner.nextInt();
        }
        return NbCandidats;
    }

    public static boolean saisirDisqualification(double [] tabClassementFinal, int nbCandidats) {
        System.out.print("Ce joueur est-il disqualifié ? true ou false: ");
        boolean value;
        value = scanner.nextBoolean();

        if(value)
            nbCandidats--;

        return value;
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
