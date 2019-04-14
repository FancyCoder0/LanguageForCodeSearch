
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class main {
    public final static String HOME = System.getProperty("user.dir");
    public final static char SEP = File.separatorChar;
    public final static String TEST_RES_DIR = HOME + SEP + "resources" + SEP + "test";
    
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

//        Document srcDoc = readXML(TEST_RES_DIR + SEP + "test3.xml");
        Document srcDoc = readXML(TEST_RES_DIR + SEP + "4.xml");

        Element srcEle = srcDoc.getRootElement();

        List<Document> patternDocs = new LinkedList<Document>();
//        patternDocs.add(readXML(TEST_RES_DIR + SEP + "pattern3.xml"));
//        patternDocs.add(readXML(TEST_RES_DIR + SEP + "pattern3_2.xml"));
//        patternDocs.add(readXML(TEST_RES_DIR + SEP + "pattern3_3.xml"));
//        patternDocs.add(readXML(TEST_RES_DIR + SEP + "pattern3_4.xml"));

        patternDocs.add(readXML(TEST_RES_DIR + SEP + "p4.xml"));
        patternDocs.add(readXML(TEST_RES_DIR + SEP + "p4_2.xml"));
        patternDocs.add(readXML(TEST_RES_DIR + SEP + "p4_3.xml"));


        for (Document patternDoc : patternDocs) {
            List<Element> input = patternDoc.getRootElement().elements();

            Element patEle = input.get(0);

            matcher w = new matcher();

            if (input.size() > 1) {
                Element varEle = input.get(1);
                w.addVariable(varEle);
            }

            System.out.println("----Pattern----");
            System.out.println(patEle.getStringValue().replace(" ", "").replace("\n", ""));

            w.tryMatch(srcEle, patEle);

            System.out.println("------------Finish------------");
        }

    }
}
