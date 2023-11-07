package dictionary;

import java.io.File;
import java.util.Scanner;
import java.util.regex.*;
import javax.swing.JFileChooser;
public class BenutzerEingabe {
    public static void main(String[] args) {
        Dictionary<String, String> dic = new SortedArrayDictionary();
        Scanner scanner = new Scanner(System.in);
        String temp;
        System.out.println("To exit: \"exit\"");
        while (true) {
            temp = scanner.nextLine();
            String[] worte = temp.split(" ");
            if (temp.equalsIgnoreCase("exit")) {
                System.out.println("exit...");
                break;
            } else if (worte[0].equalsIgnoreCase("create")) {
                create(worte, dic);
            } else if (worte[0].equalsIgnoreCase("r")) {
                dateiEinlesen(worte, dic);
            } else if (worte[0].equalsIgnoreCase("p")) {
                for (Dictionary.Entry<String, String> e : dic) {
                    System.out.println(e.getKey() + ": " + e.getValue());
                }
            } else if (worte[0].equalsIgnoreCase("s")) {
                if (worte.length != 2) {
                    System.out.println("Missing word.");
                    continue;
                }
                System.out.println(dic.search(worte[1]));
            } else if (worte[0].equalsIgnoreCase("i")) {
                if (worte.length != 3) {
                    System.out.println("Missing words.");
                    continue;
                }
                System.out.println(dic.insert(worte[1], worte[2]));
            } else if (worte[0].equalsIgnoreCase("d")) {
            if (worte.length != 2) {
                System.out.println("Missing word.");
                continue;
            }
            System.out.println(dic.remove(worte[1]));
        }
        }
    }

    public static void create(String[] woerter, Dictionary dictionary) {
        if (woerter.length == 1) {
            System.out.println("init SortedArrayDictionary.");
            dictionary = new SortedArrayDictionary();
        } else if (woerter.length == 2) {
            if (woerter[1].equalsIgnoreCase("BinaryTreeDictionary")) {
                System.out.println("init BinaryTreeDictionary.");
                dictionary = new BinaryTreeDictionary();
            } else if (woerter[1].equalsIgnoreCase("HashDictionary")) {
                System.out.println("init HashDictionary.");
                dictionary = new HashDictionary(3);
            } else if (woerter[1].equalsIgnoreCase("SortedArrayDictionary")) {
                System.out.println("init SortedArrayDictionary.");
                dictionary = new SortedArrayDictionary();
            } else {
                System.out.println("\"" + woerter[1] + "\" not an option. Chosse from: " +
                        "[BinaryTreeDictionary | HashDictionary | SortedArrayDictionary].");
            }
        }
    }

    private static void dateiEinlesen(String[] woerter, Dictionary dictionary) {
        Pattern pattern = Pattern.compile("\\[(\\d+)\\]");
        int n = -1;
        if (woerter.length >= 2)
            if (pattern.matcher(woerter[1]).find())
                n = Integer.parseInt(woerter[1].replace("[", "").replace("]", ""));
        try {
            File datei;
            if (woerter.length < 3) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("C:\\Users\\flori\\IdeaProjects\\AlgorithmenundDatenstrukturen\\src\\dictionary"));
                int result = fileChooser.showOpenDialog(null);
                if (result != 0) {
                    System.out.println("error with file handeling!");
                    return;
                }
                datei = fileChooser.getSelectedFile();
            } else {
                if (n != -1) {
                    datei = new File(woerter[2]);
                } else
                    datei = new File(woerter[1]);
            }
            Scanner scanner = new Scanner(datei);

            while (scanner.hasNextLine() && n != 0) {
                String zeile = scanner.nextLine();
                String[] worte = zeile.split(" ");
                if (worte.length == 2) {
                    dictionary.insert(worte[0], worte[1]);
                } else {
                    System.out.println("Error Row: " + zeile);
                }
                n--;
            }
            scanner.close();
            System.out.println("File read complet!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
