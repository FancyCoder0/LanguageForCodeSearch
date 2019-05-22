package l4cs;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

public class Util {
    public final static char SEP = File.separatorChar;

    public static Document readXML(String fileName) throws Exception {
        SAXReader reader = new SAXReader();
        File file = new File(fileName);
        Document document = reader.read(file);
        return document;
    }
}
