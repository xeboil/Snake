import java.awt.Point;
import java.util.Random;
import java.util.Scanner;

public class Snake {
    public static void main(String[] args) {
        Random random = new Random();
        Point spielerPosition = generiereZufallsPosition(random);
        Point schlangePosition = generiereZufallsPosition(random);
        Point goldPosition = generiereZufallsPosition(random);
        Point tuerPosition = generiereZufallsPosition(random);


        while (spielerPosition.equals(schlangePosition) || spielerPosition.equals(goldPosition) || spielerPosition.equals(tuerPosition)) {
            spielerPosition = generiereZufallsPosition(random);
        }
        while (schlangePosition.equals(goldPosition) || schlangePosition.equals(tuerPosition)) {
            schlangePosition = generiereZufallsPosition(random);
        }
        while (goldPosition.equals(tuerPosition)) {
            goldPosition = generiereZufallsPosition(random);
        }

        boolean weiter = true;
        boolean goldEingesammelt = false;

        while (weiter) {
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 40; x++) {
                    Point p = new Point(x, y);
                    if (p.equals(spielerPosition))
                        System.out.print("P");
                    else if (p.equals(schlangePosition))
                        System.out.print("S");
                    else if (p.equals(goldPosition))
                        System.out.print("G");
                    else if (p.equals(tuerPosition))
                        System.out.print("T");
                    else
                        System.out.print(".");
                }
                System.out.println();
            }

            // Ereignisse
            if (schlangePosition.equals(spielerPosition)) {
                weiter = false;
                System.out.println("Zzz... Die Schlange hat dich.");
            }
            if (spielerPosition.equals(goldPosition)) {
                goldEingesammelt = true;
                goldPosition = new Point(-1, -1);
                System.out.println("Du hast das Gold eingesammelt!");
            }
            if (spielerPosition.equals(tuerPosition) && goldEingesammelt) {
                weiter = false;
                System.out.println("Gewonnen!");
            }

            bewegeSpieler(spielerPosition);
            bewegeSchlange(schlangePosition, spielerPosition);
        }
    }

    private static Point generiereZufallsPosition(Random random) {
        int x = random.nextInt(40); // Spielfeldbreite: 0 bis 39
        int y = random.nextInt(10); // Spielfeldhöhe: 0 bis 9
        return new Point(x, y);
    }

    private static void bewegeSchlange(Point schlangePosition, Point spielerPosition) {
        if (spielerPosition.x < schlangePosition.x)
            schlangePosition.x--;
        else if (spielerPosition.x > schlangePosition.x)
            schlangePosition.x++;
        if (spielerPosition.y < schlangePosition.y)
            schlangePosition.y--;
        else if (spielerPosition.y > schlangePosition.y)
            schlangePosition.y++;
    }

    private static void bewegeSpieler(Point spielerPosition) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Bewegung (w=hoch, a=links, s=runter, d=rechts):");
        String input = scan.next();

        // bewegen
        if (input.equals("w")) {
            if (spielerPosition.y > 0)
                spielerPosition.y--;
        } else if (input.equals("s")) {
            if (spielerPosition.y < 9)
                spielerPosition.y++;
        } else if (input.equals("a")) {
            if (spielerPosition.x > 0)
                spielerPosition.x--;
        } else if (input.equals("d")) {
            if (spielerPosition.x < 39)
                spielerPosition.x++;
        }
    }
}
