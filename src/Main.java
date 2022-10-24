import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        int nbCandidats, nbDisqualifiés, nbPortes;
        double[] tabTempsManche1;
        double[] tabTempsManche2;
        double[] tabClassementFinal;

    }
    public static int saisirPortesRatees(int pfNbPortes){
        var scanner = new Scanner(System.in);
        int portesRatees = 0;
        while(portesRatees > pfNbPortes){
            System.out.println("Veuillez saisir le nombre de portes ratées pour ce joueur : ");
            portesRatees = scanner.nextInt();
        }
        return portesRatees;
    }

}
