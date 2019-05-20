import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

public class util {
    public static Document readXML(String fileName) {
        SAXReader reader = new SAXReader();
        File file = new File(fileName);
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
            System.err.println("can't read xml!");
        }
        return document;
    }
}
