package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class zeitmessung {
    static int DURCHLAUFE = 16000;
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\flori\\IdeaProjects\\AlgorithmenundDatenstrukturen\\src\\dictionary\\dtengl.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> inputList = new ArrayList<>();
        int i = 0;
        while (scanner.hasNextLine() && i < DURCHLAUFE) {
            String zeile = scanner.nextLine();
            String[] worte = zeile.split(" ");
            if (worte.length == 2) {
                inputList.add(new String[]{worte[0], worte[1]});
            } else {
                System.out.println("Error Row: " + zeile);
            }
            i++;
        }
        Dictionary testDic = new SortedArrayDictionary();
        System.out.println("SortedArrayDictionary => 8000 Werte einlesen: " + einfuegenMessung(testDic, 8000, inputList) + "ms");
        System.out.println("SortedArrayDictionary => 8000 erlolgreiche suche: " + erfolgreicheSuche(testDic, 8000, inputList) + "ms");
        System.out.println("SortedArrayDictionary => 8000 unerfolgreiche suche: " + unerfolgreicheSuche(testDic, 8000, inputList) + "ms");
        System.out.println("--");
        testDic = new SortedArrayDictionary();
        System.out.println("SortedArrayDictionary => 16000 Werte einlesen: " + einfuegenMessung(testDic, 16000, inputList) + "ms");
        System.out.println("SortedArrayDictionary => 16000 erlolgreiche suche: " + erfolgreicheSuche(testDic, 16000, inputList) + "ms");
        System.out.println("SortedArrayDictionary => 16000 unerfolgreiche suche: " + unerfolgreicheSuche(testDic, 16000, inputList) + "ms");
        System.out.println("--");
        testDic = new HashDictionary(3);
        System.out.println("HashDictionary => 8000 Werte einlesen: " + einfuegenMessung(testDic, 8000, inputList) + "ms");
        System.out.println("HashDictionary => 8000 erlolgreiche suche: " + erfolgreicheSuche(testDic, 8000, inputList) + "ms");
        System.out.println("HashDictionary => 8000 unerfolgreiche suche: " + unerfolgreicheSuche(testDic, 8000, inputList) + "ms");
        System.out.println("--");
        testDic = new HashDictionary(3);
        System.out.println("HashDictionary => 16000 Werte einlesen: " + einfuegenMessung(testDic, 16000, inputList) + "ms");
        System.out.println("HashDictionary => 16000 erlolgreiche suche: " + erfolgreicheSuche(testDic, 16000, inputList) + "ms");
        System.out.println("HashDictionary => 16000 unerfolgreiche suche: " + unerfolgreicheSuche(testDic, 16000, inputList) + "ms");
        System.out.println("--");
        testDic = new BinaryTreeDictionary();
        System.out.println("BinaryTreeDictionary => 8000 Werte einlesen: " + einfuegenMessung(testDic, 8000, inputList) + "ms");
        System.out.println("BinaryTreeDictionary => 8000 erlolgreiche suche: " + erfolgreicheSuche(testDic, 8000, inputList) + "ms");
        System.out.println("BinaryTreeDictionary => 8000 unerfolgreiche suche: " + unerfolgreicheSuche(testDic, 8000, inputList) + "ms");
        System.out.println("--");
        testDic = new BinaryTreeDictionary();
        System.out.println("BinaryTreeDictionary => 16000 Werte einlesen: " + einfuegenMessung(testDic, 16000, inputList) + "ms");
        System.out.println("BinaryTreeDictionary => 16000 erlolgreiche suche: " + erfolgreicheSuche(testDic, 16000, inputList) + "ms");
        System.out.println("BinaryTreeDictionary => 16000 unerfolgreiche suche: " + unerfolgreicheSuche(testDic, 16000, inputList) + "ms");
    }

    public static long einfuegenMessung(Dictionary dic, int elemente, List<String[]> werte) {
        long pre = System.currentTimeMillis();
        int i = 0;
        for (String[] entry : werte) {
            if (i >= elemente) {
                break;
            }
            dic.insert(entry[0], entry[1]);
            i++;
        }
        return System.currentTimeMillis() - pre;
    }

    public static long erfolgreicheSuche(Dictionary dic, int elemente, List<String[]> werte) {
        long pre = System.currentTimeMillis();
        int i = 0;
        for (String[] entry : werte) {
            if (i >= elemente) {
                break;
            }
            dic.search(entry[0]);
            i++;
        }
        return System.currentTimeMillis() - pre;
    }

    public static long unerfolgreicheSuche(Dictionary dic, int elemente, List<String[]> werte) {
        long pre = System.currentTimeMillis();
        int i = 0;
        for (String[] entry : werte) {
            if (i >= elemente) {
                break;
            }
            dic.search(entry[0]);
            i++;
        }
        return System.currentTimeMillis() - pre;
    }
}
