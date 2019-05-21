package l4cs;

import org.dom4j.Document;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Document> patterns = new LinkedList<Document>();

    static void search(Document srcDoc, List<Document> patternDocList) {
        Matcher w = new Matcher();
        List<Document> ret = w.tryMatch(srcDoc, patternDocList);

        System.out.println("----Pattern----");
        for (Document patternDoc : patternDocList) {
            System.out.println(w.prettyPattern(patternDoc));
        }
        System.out.println("----Match Code----");
        for (Document c : ret) {
            System.out.println(c.getStringValue());
        }
        System.out.println("----Finish----");
    }

    static void search(Document srcDoc, Document patternDoc) {
        List<Document> patternDocList = new LinkedList<Document>();
        patternDocList.add(patternDoc);
        search(srcDoc, patternDocList);
    }


    // TODO (p4_6.xml) do folder.
    public static void main(String[] args) {
        System.out.println("-----Language For Code Search-----");
        System.out.flush();

        Scanner in = new Scanner(System.in);
        while (true) {
            String s = in.nextLine();
            String[] arr = s.split(" ");

            if (arr.length != 2) {
                System.err.println("Input Error!");
                continue;
            }
            String type = arr[0], arg = arr[1];
            if (type.equals("add")) {
                try {
                    Document patDoc = Util.readXML(arg);
                    patterns.add(patDoc);
                    System.out.println("Add pattern successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (type.equals("apply")) {
                try {
                    Document srcDoc = Util.readXML(arg);
                    search(srcDoc, patterns);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
