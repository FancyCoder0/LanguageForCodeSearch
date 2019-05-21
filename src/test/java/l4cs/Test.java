package l4cs;

import org.dom4j.Document;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public final static String HOME = System.getProperty("user.dir");
    public final static char SEP = File.separatorChar;
    public final static String TEST_RES_DIR = HOME + SEP + "resources" + SEP + "test";

    @org.junit.Test
    public void test_main() {
        for (int i = 1; i <= 4; ++i) {
            Document srcDoc = Util.readXML(TEST_RES_DIR + SEP + i + SEP + "src.xml");

            List<Document> patternDocs = new LinkedList<Document>();
            for (int pNum = 1; ; ++pNum) {
                String patternXML = TEST_RES_DIR + SEP + i + SEP + "p" + pNum + ".xml";
                if (new File(patternXML).exists()) {
                    patternDocs.add(Util.readXML(patternXML));
                } else {
                    break;
                }
            }

            for (Document patternDoc : patternDocs) {
                Main.search(srcDoc, patternDoc);
            }
        }
    }
}
