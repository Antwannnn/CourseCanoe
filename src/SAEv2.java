import java.util.Scanner;

public class SAEv2 {
    // déclaration du scanner pour toute la classe
    private static Scanner scanner = new Scanner(System.in);

    /** Rôle : première fonction a s'écuter et on y retrouve la plupart des fonctions
     * @param args IN : convention
     */
    public static void main(String[] args){

        int nbCandidats, nbCandidatsRestants, nbPortes;
        double[] tabTempsManche1;
        double[] tabTempsManche2;
        double[] tabClassementFinal = new double[0];

        int[] numeroDeBrassard;

        int nbPortesRateesManche1, nbPortesRateesManche2, nbPortesToucheesManche1, nbPortesToucheesManche2;

        double tempsCompensesManche1, tempsCompensesManche2, moyenneTempsCompenses;


        nbCandidats = saisirNbCandidats();

        tabTempsManche1 = new double[nbCandidats];
        tabTempsManche2 = new double[nbCandidats];
        tabClassementFinal = new double[nbCandidats];
        numeroDeBrassard = new int[nbCandidats];
        nbCandidatsRestants = 0;

        nbPortes = saisirNbPortes();

        for(int i = 0; i < nbCandidats; i++){

            System.out.println("Informations pour le " + (i+1) + " ème participant");
            System.out.println("-----------------------------------------------");

            boolean disqualifié = saisirDisqualification();

            if(!disqualifié){
                saisirTabTemps(tabTempsManche1, "Veuillez saisir le temps pour le " + (i+1) + "ème participant pour la manche 1: ", (i));
                nbPortesRateesManche1 = saisirInt(nbPortes, "Veuillez saisir le nombre de portes ratées à la manche 1 pour le " + (i+1) + "ème participant : ");
                nbPortesToucheesManche1 = saisirInt(nbPortes, "Veuillez saisir le nombre de portes touchées à la manche 1 pour le " + (i+1) + "ème participant : ");
                while (nbPortesToucheesManche1 + nbPortesRateesManche1 > nbPortes) {
                    nbPortesToucheesManche1 = saisirInt(nbPortes, "Veuillez saisir le nombre de portes touchées à la manche 1 pour le " + (i+1) + "ème participant : ");
                }
                tempsCompensesManche1 = tempsCompensé(nbPortesToucheesManche1, nbPortesRateesManche1, tabTempsManche1[i]);

                saisirTabTemps(tabTempsManche2, "Veuillez saisir le temps pour le " + (i+1) + "ème participant pour la manche 2: ", (i));
                nbPortesRateesManche2 = saisirInt(nbPortes, "Veuillez saisir le nombre de portes ratées à la manche 2 pour le " + (i+1) + "ème participant : ");
                nbPortesToucheesManche2 = saisirInt(nbPortes, "Veuillez saisir le nombre de portes touchées à la manche 2 pour le " + (i+1) + "ème participant : ");
                while (nbPortesToucheesManche2 + nbPortesRateesManche2 > nbPortes) {
                    nbPortesToucheesManche2 = saisirInt(nbPortes, "Veuillez saisir le nombre de portes touchées à la manche 1 pour le " + (i+1) + "ème participant : ");
                }
                tempsCompensesManche2 = tempsCompensé(nbPortesToucheesManche2, nbPortesRateesManche2, tabTempsManche2[i]);

                numeroDeBrassard[i] = i + 1;

                moyenneTempsCompenses = (tempsCompensesManche1 + tempsCompensesManche2) / 2;
                tabClassementFinal[nbCandidatsRestants] = moyenneTempsCompenses;

                System.out.println(afficherTempsConverti(tabClassementFinal[i], i));
                nbCandidatsRestants++;

            }


        }
        podium(nbCandidatsRestants, tabClassementFinal, numeroDeBrassard);


    }
    /** Rôle : permet de saisir un entier qui est inférieur à la borne et supérieur à 0
     * @param pfBorne IN : borne que la valeur saisi doit dépasser
     * @param pfMessage IN : message à afficher pour faire saisir la valeur
     * @return la valeur saisie
     */
    public static int saisirInt(int pfBorne, String pfMessage){
        // on met value à -1 pour pouvoir entrer dans la boucle while
        int value = -1;

        // tant que la valeur saisie est supérieur à la borne (entrée en paramètre) et inférieur à 0 
        // alors on affiche le message et on demande de saisir une valeur
        while(value > pfBorne || value < 0){
            System.out.print(pfMessage);
            value = scanner.nextInt();
        }

        // on renvoi la valeur
        return value;
    }

    /** Rôle : permet de saisir un temps de course qui doit donc être supérieur à 0
     * @param pfTab IN : permet de remplir un tableau de temps (manche 1 et manche 2)
     * @param pfMessage IN : message à afficher
     * @param pfIndice IN : indice pour modifier le tableau
     */
    public static void saisirTabTemps(double[] pfTab, String pfMessage, int pfIndice){
        // on initialise la valeur à modifier du tableau à 0 pour rentrer dans la boucle while
        pfTab[pfIndice] = 0;

        // tant que la valeur qu'on fait saisir est inférieur ou égal à 0 (car un score ne peut pas être négatif ou nul)
        // alors on affiche le message et on lui fait saisir une valeur
        while(pfTab[pfIndice] <= 0){
            System.out.print(pfMessage);
            pfTab[pfIndice] = scanner.nextDouble();
        }
    }

    /** Rôle : permet de saisir le nombre de portes qui doit être compris entre 18 et 22
     * @return le nombre de portes
     */
    public static int saisirNbPortes () {
        // initialisation
        int NbPortes;

        // on affiche le message pour faire comprendre à l'utilisateur de rentrer le nombre de portes
        System.out.print("Saisissez le nombre de portes de la course, il doit être compris entre 18 et 22 : ");
        // on fait rentrer à l'utilisateur le nombre de porte
        NbPortes = scanner.nextInt();

        // tant que le nombre de portes est inférieur à 18 ou supérieur à 22 
        // alors on lui affiche de nouveau le message et on lui fait re saisir le nombre de portes
        while (NbPortes < 18 || NbPortes > 22) {
            System.out.print("Veuillez saisir un nombre entre 18 et 22 compris: ");
            NbPortes = scanner.nextInt();
        }

        // on renvoi le nombre de portes
        return NbPortes;
    }

    /** Rôle : permet de saisir le nombre de canditat qui doit être compris entre 1 et 49
     * @return le nombre de candidats
     */
    public static int saisirNbCandidats (){
        // initialisation
        int NbCandidats;

        // on affiche le message pour faire comprendre à l'utilisateur de rentrer le nombre de candidats
        System.out.print("Saisissez le nombre de candidats à la course, il doit être compris entre 1 et 49 : ");
        // on fait rentrer à l'utilisateur le nombre de candidats
        NbCandidats = scanner.nextInt();

        // tant que le nombre de candidat est inférieur ou égal à 0 ou 50 
        // alors on lui affiche de nouveau le message et on lui fait re saisir le nombre de candidat
        while(NbCandidats<1 || NbCandidats >= 50){
            System.out.print("Veuillez saisir un nombre entre 1 et 49 compris: ");
            NbCandidats = scanner.nextInt();
        }

        // on renvoi le nombre de candidats
        return NbCandidats;
    }

    /** Rôle : permet de voir si un candidat est disqualifié ou non
     * @return true si le candidat est disqualifié et false sinon
     */
    public static boolean saisirDisqualification() {
        // on affiche ce message pour faire comprendre à l'utilisateur de rentrer "true" ou "false" pour savoir si un candidat est disqualifé
        System.out.print("Ce joueur est-il disqualifié ? true ou false: ");
        boolean value;
        value = scanner.nextBoolean();

        // on renvoi la valeur
        return value;
    }

    /** Rôle : permet de calculer le score d'un candidat en rajoutant les pénalités 
     * @param portesratées IN : nombre de portes ratées
     * @param portestouchées IN : nombre de portes touchées
     * @param tempsManche IN : le score actuel du candidat (avec de rajoter les pénalités)
     * @return le temps compensé (le temps + les pénalités)
     */
    public static double tempsCompensé(int portesratées, int portestouchées, double tempsManche){
        // initialisation
        double tempsFinal, tempsPenalite;

        // calcul des pénalités pour chaque portes ratées
        int pénalitéPortesRatées = 50*1000*portesratées;
        // calcul des pénalités pour chaque portes touchées
        int pénalitéPortesTouchées = 2*1000*portestouchées;

        // somme des pénalités des portes touchées et ratées
        tempsPenalite = pénalitéPortesTouchées + pénalitéPortesRatées;

        // somme du score final (score de départ + pénalités)
        tempsFinal = tempsManche + tempsPenalite;

        // on renvoi le score final
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
            System.out.println("Il n'y a malheureusement personne sur le podium");
        }

        // affichage
        // si il y a éxactement 1 premier
        if (nbPremier == 1) {
            System.out.println("Nous avons en première position avec un temps de " + premier + " millisecondes le brassard n°" + pfBrassard[indicePremier[0]]);
            System.out.println(afficherTempsConverti(premier, 1));
        }
        // si il y a plusieurs premiers
        else if (nbPremier >= 2) {
            System.out.println("Nous avons " + nbPremier + " ex æquo  pour la première position avec un temps de " + premier + " millisecondes qui sont les brassard n° ");
            for (i = 0; i < nbPremier; i++) {
                System.out.println(pfBrassard[indicePremier[i]]);
            }
            System.out.println(afficherTempsConverti(premier, 1));
        }

        // si il y a éxactement 1 deuxième
        if (nbDeuxieme == 1) {
            System.out.println("Nous avons en deuxième position avec un temps de " + deuxieme + " millisecondes le brassard n°" + pfBrassard[indiceDeuxieme[0]]);
            System.out.println(afficherTempsConverti(deuxieme, 2));
        }
        // si il y a plusieurs deuxième
        else if (nbDeuxieme >= 2) {
            System.out.println("Nous avons " + nbDeuxieme + " ex æquo  pour la deuxième position avec un temps de " + deuxieme + " millisecondes qui les brassard n°");
            for (i = 0; i < nbDeuxieme; i++) {
                System.out.println(pfBrassard[indiceDeuxieme[i]]);
            }
            System.out.println(afficherTempsConverti(deuxieme, 2));
        }

        // si il y a éxactement 1 troisième
        if (nbTroisieme == 1){
            System.out.println("Nous avons en troisième position un temps de " + troisieme + " millisecondes le brassard n°" + pfBrassard[indiceTroisieme[0]]);
            System.out.println(afficherTempsConverti(troisieme, 3));
        }
        // si il y a plusieurs troisième
        else if (nbTroisieme >= 2) {
            System.out.println("Nous avons " + nbTroisieme + " ex æquo  pour la troisième position avec un temps de " + troisieme + " millisecondes qui sont les brassard n°");
            for (i = 0; i < nbTroisieme; i++) {
                System.out.println(pfBrassard[indiceTroisieme[i]]);
            }
            System.out.println(afficherTempsConverti(troisieme, 3));
        }
    }

    /** Rôle : permet d'afficher le temps converti des candidats qui sont sur le podium
     * @param pfTemps IN : score du candidat
     * @param indice IN : indice du joueur (place sur le podium)
     * @return le temps converti
     */
    public static String afficherTempsConverti(double pfTemps, int pfIndice){
        // initialisation du message, des minutes et des secondes
        String message = "";
        int min;
        double secondes;


        min =0;
        System.out.println("Le " + pfIndice +" ème joueur a réalisé un temps de " +pfTemps+ " millisecondes, c'est-à-dire un temps de "+pfTemps/1000+" secondes.");
        secondes = pfTemps/1000;
        while(secondes > 60){
            min += 1;
            secondes -= 60;

        }
        message = "Cela correspond à un temps de "+min+" minutes et "+secondes+ " secondes.";
        min =0;
        return message;
    }

}
