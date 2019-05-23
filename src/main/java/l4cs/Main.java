package l4cs;

import org.dom4j.Document;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Document> patterns = new LinkedList<Document>();
    static String localPath = null;

    static void search(Document srcDoc, List<Document> patternDocList) {
        Matcher w = new Matcher();
        List<Document> ret = w.tryMatch(srcDoc, patternDocList);

        System.out.println("----Pattern----");
        for (Document patternDoc : patternDocList) {
            System.out.println(w.prettyPattern(patternDoc));
        }
        System.out.println("----Match Code----");
        int matchCnt = 0;
        for (Document c : ret) {
            matchCnt += 1;
            System.out.println("Match #" + matchCnt);
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
            String[] arg = s.split(" ");

            String type = arg[0];

            if (type.equals("reset")) {
                localPath = null;
                patterns = new LinkedList<Document>();
                System.out.println("Reset successfully!");
                continue;
            }

            if (type.equals("clear")) {
                patterns = new LinkedList<Document>();
                System.out.println("Clear all patterns successfully!");
                continue;
            }

            if (type.equals("setpath")) {
                localPath = arg[1];
                System.out.println("Current path:" + localPath);
                continue;
            }

            if (type.equals("add")) {
                String filePath = (localPath == null) ? arg[1] : localPath + Util.SEP + arg[1];
                try {
                    Document patDoc = Util.readXML(filePath);
                    patterns.add(patDoc);
                    System.out.println("Add pattern successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (type.equals("search")) {
                String filePath = (localPath == null) ? arg[1] : localPath + Util.SEP + arg[1];
                try {
                    Document srcDoc = Util.readXML(filePath);
                    search(srcDoc, patterns);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
