package l4cs;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class main {

    public static Document readXML(String fileName) {
        SAXReader reader = new SAXReader();
        File file = new File(fileName);
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
            System.out.println("can't read xml!");
        }
        return document;
    }

    public static void main(String[] args) {

        Document srcDoc = readXML("/Users/luyaoren/LanguageForCodeSearch/resources/test/test3.xml");
        Element srcEle = srcDoc.getRootElement();

        List<Document> patternDocs = new LinkedList<Document>();
        patternDocs.add(readXML("/Users/luyaoren/LanguageForCodeSearch/resources/test/pattern3.xml"));
        patternDocs.add(readXML("/Users/luyaoren/LanguageForCodeSearch/resources/test/pattern3_2.xml"));
        patternDocs.add(readXML("/Users/luyaoren/LanguageForCodeSearch/resources/test/pattern3_3.xml"));
        patternDocs.add(readXML("/Users/luyaoren/LanguageForCodeSearch/resources/test/pattern3_4.xml"));

        worker w = new worker();
        for (Document patternDoc : patternDocs) {
            Element patEle = patternDoc.getRootElement().elements().get(0);
            System.out.println("----Pattern----");
            System.out.println(patEle.getStringValue());

            w.tryMatch(srcEle, patEle);

            System.out.println("------------Finish------------");
        }

    }
}
