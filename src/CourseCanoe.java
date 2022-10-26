import java.util.HashMap;
import java.util.Scanner;

public class CourseCanoe {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        int nbCandidats, nbDisqualifiés, nbPortes;
        double[] tabTempsManche1;
        double[] tabTempsManche2;
        double[] tabClassementFinal = {0};

        int[] numeroDeBrassard = {0};

        int nbPortesRateesManche1;
        int nbPortesRateesManche2;
        int nbPortesToucheesManche1;
        int nbPortesToucheesManche2;

        double tempsCompensesManche1;
        double tempsCompensesManche2;

        double moyenneTempsCompenses;

        nbCandidats = saisirNbCandidats();

        nbPortes = saisirNbPortes();

        for(int i = 0; i < nbCandidats; i++){

            tabTempsManche1 = new double[nbCandidats + 1];
            tabTempsManche2 = new double[nbCandidats + 1];
            tabClassementFinal = new double[nbCandidats + 1];

            numeroDeBrassard = new int[nbCandidats + 1];

            System.out.println("Informations pour le " + (i+1) + " ème participant");
            System.out.println("-----------------------------------------------");

            boolean disqualifié = saisirDisqualification(tabClassementFinal, nbCandidats);

            if(!disqualifié){

                saisirTabTemps(tabTempsManche1, nbCandidats, "Veuillez saisir le temps pour le " + (i+1) + "ème participant pour la manche 1: ", (i+1));
                nbPortesRateesManche1 = saisirInt(nbPortes, "Veuillez saisir le nombre de portes ratées à la manche 1 pour le " + (i+1) + "ème participant : ");
                nbPortesToucheesManche1 = saisirInt(nbPortes, "Veuillez saisir le nombre de portes touchées à la manche 1 pour le " + (i+1) + "ème participant : ");
                tempsCompensesManche1 = tempsCompensé(nbPortesToucheesManche1, nbPortesRateesManche1, tabTempsManche1[i]);

                saisirTabTemps(tabTempsManche2, nbCandidats, "Veuillez saisir le temps pour le " + (i+1) + "ème participant pour la manche 2: ", (i+1));
                nbPortesRateesManche2 = saisirInt(nbPortes, "Veuillez saisir le nombre de portes ratées à la manche 2 pour le " + (i+1) + "ème participant : ");
                nbPortesToucheesManche2 = saisirInt(nbPortes, "Veuillez saisir le nombre de portes touchées à la manche 2 pour le " + (i+1) + "ème participant : ");
                tempsCompensesManche2 = tempsCompensé(nbPortesToucheesManche2, nbPortesRateesManche2, tabTempsManche1[i]);

                numeroDeBrassard[i] = i;

                moyenneTempsCompenses = (tempsCompensesManche1 + tempsCompensesManche2) / 2;
                tabClassementFinal[i] = moyenneTempsCompenses;

            }

        }

        podium(nbCandidats, tabClassementFinal, numeroDeBrassard);


    }
    public static int saisirInt(int pfBorne, String pfMessage){
        int value = -1;
        while(value > pfBorne || value < 0){
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



    /** Rôle : permet de calculer le score du premier, du second (si il y en a) et du troisième (si il y en a)
     * @param pfNbCompetiteur IN : nombre de valeur à traiter
     * @param pfScore IN : tableau des scores
     * @param pfMin1 IN : score minimum à avoir (pour éviter les doublons)
     * @param pfMin2 IN : score minimum à avoir (pour éviter les doublons)
     * @return le nombre de premier ou deuxième ou troisième
     */
    public static double place(int pfNbCompetiteur, double [] pfScore, double pfMin1, double pfMin2) {
        // initialisation
        int i, plus;
        double placePod;
        plus = 0;


        // traitement

        // la valeur de base que l'on met dans placePod doit être > min1 et min2
        // tant que le score à la position plus est = au min1 ou min2 alors plus prend une valeur suplémentaire
        placePod = pfScore[plus];
        while (pfScore[plus] == pfMin1 || pfScore[plus] == pfMin2) {
            plus++;
            placePod = pfScore[plus];
        }
        // recherche du minimum
        for (i = 1; i < pfNbCompetiteur; i++) {
            if (pfScore[i] < placePod && pfScore[i] > pfMin1 && pfScore[i] > pfMin2) {
                placePod = pfScore[i];
            }
        }

        // on retourne le minimum
        return placePod;
    }

    /** Rôle : permet de calculer le nombre de premier, de second (si il y en a) et de troisième (si il y en a) et de rajouter les indices
     *         quand on les trouves pour les faire correspondre à un brassard
     * @param pfNbCompetiteur IN : nombre de valeur à traiter
     * @param pfScore IN : tableau des scores
     * @param pfPlacePod IN : place du podium
     * @param indice OUT : indice des premiers ou deuxième ou troisième
     * @return le nombre de premier ou deuxième ou troisième
     */
    public static int nombre_place(int pfNbCompetiteur, double [] pfScore, double pfPlacePod, int [] indice) {
        // initialisation
        int i, nombre;

        nombre = 0;

        // on parcours le tableau des scores pour regarder combien de personne on le même temps
        for (i = 0; i < pfNbCompetiteur; i++) {
            if (pfScore[i] == pfPlacePod) {
                indice[nombre] = i;
                nombre += 1;
            }
        }

        // on retourne le nombre de personnes qui ont le même temps
        return nombre;
    }

    /** Rôle : permet de donner le podium avec les 3 meilleurs temps de course
     * @param pfNbCompetiteur IN : nombre de valeur à traiter
     * @param pfScore IN : tableau des scores
     * @param pfBrassard IN : tableau des brassard
     * prec : tout les scores sont >0
     */
    public static void podium(int pfNbCompetiteur, double [] pfScore, int [] pfBrassard) {
        // initialisation
        int nbPremier, nbDeuxieme, nbTroisieme, i;
        double premier, deuxieme, troisieme;

        int [] indicePremier = new int[50];
        int [] indiceDeuxieme = new int[50];
        int [] indiceTroisieme = new int[50];

        // on déclare au moins toutes les variables à 0 mais si ça reste à 0 ça ne sera ni traité ni affiché
        nbPremier = 0;
        nbDeuxieme = 0;
        nbTroisieme = 0;
        premier = 0;
        deuxieme = 0;
        troisieme = 0;


        // traitement

        // si il y a au moins 1 compétiteur
        if (pfNbCompetiteur > 0) {
            // calcul du premier meilleur temps
            premier = place(pfNbCompetiteur, pfScore, 0, 0);
            // calcul du nombres de personnes ex æquo  en première position
            nbPremier = nombre_place(pfNbCompetiteur, pfScore, premier, indicePremier);

            // si il y a exactement 1 premier et que le nombre de compétiteur - le premier >= 1
            // pour avoir au moins un deuxième
            if (nbPremier == 1 && pfNbCompetiteur-nbPremier >= 1) {
                // calcul du deuxième meilleur temps
                deuxieme = place(pfNbCompetiteur, pfScore, premier, 0);
                // calcul du nombres de personnes éxécauts en deuxième position
                nbDeuxieme = nombre_place(pfNbCompetiteur, pfScore, deuxieme, indiceDeuxieme);

                // si il y a exactement 1 deuxième et que le nombre de compétiteur - le premier - le deuxième >= 1
                // pour avoir au moins un troisième
                if (nbDeuxieme == 1 && pfNbCompetiteur-nbPremier-nbDeuxieme >= 1) {
                    // calcul du troisième meilleur temps
                    troisieme = place(pfNbCompetiteur, pfScore, premier, deuxieme);
                    // calcul du nombres de personnes éxécauts en troisième position
                    nbTroisieme = nombre_place(pfNbCompetiteur, pfScore, troisieme, indiceTroisieme);
                }
            }
            // sinon si il y a éxactement de premier et que le nombre de compétiteur - les premeirs >= 1
            // pour avoir au moins un troisième
            else if (nbPremier == 2 && pfNbCompetiteur-nbPremier >= 1) {
                // calcul du troisième meilleur temps
                troisieme = place(pfNbCompetiteur, pfScore, premier, 0);
                // calcul du nombres de personnes éxécauts en troisième position
                nbTroisieme = nombre_place(pfNbCompetiteur, pfScore, troisieme, indiceTroisieme);
            }

        }
        // si il y a aucun compétiteur
        else {
            System.out.println("Il y a malheureusement personne sur le podium");
        }

        // affichage
        // si il y a éxactement 1 premier
        if (nbPremier == 1) {
            System.out.println("Nous avons en première position avec un temps de " + premier + "sec le brassard n°" + pfBrassard[indicePremier[0]]);
        }
        // si il y a plusieurs premiers
        else if (nbPremier >= 2) {
            System.out.println("Nous avons " + nbPremier + " ex æquo  pour la première position avec un temps de " + premier + "sec qui sont les brassard n°");
            for (i = 0; i < nbPremier; i++) {
                System.out.println(pfBrassard[indicePremier[i]]);
            }
        }

        // si il y a éxactement 1 deuxième
        if (nbDeuxieme == 1) {
            System.out.println("Nous avons en deuxième position avec un temps de " + deuxieme + "sec le brassard n°" + pfBrassard[indiceDeuxieme[0]]);
        }
        // si il y a plusieurs deuxième
        else if (nbDeuxieme >= 2) {
            System.out.println("Nous avons " + nbDeuxieme + " ex æquo  pour la deuxième position avec un temps de " + deuxieme + "sec qui les brassard n°");
            for (i = 0; i < nbDeuxieme; i++) {
                System.out.println(pfBrassard[indiceDeuxieme[i]]);
            }
        }

        // si il y a éxactement 1 troisième
        if (nbTroisieme == 1){
            System.out.println("Nous avons en troisième position un temps de " + troisieme + "sec le brassard n°" + pfBrassard[indiceTroisieme[0]]);
        }
        // si il y a plusieurs troisième
        else if (nbTroisieme >= 2) {
            System.out.println("Nous avons " + nbTroisieme + " ex æquo  pour la troisième position avec un temps de " + troisieme + "sec qui sont les brassard n°");
            for (i = 0; i < nbTroisieme; i++) {
                System.out.println(pfBrassard[indiceTroisieme[i]]);
            }
        }
    }

}