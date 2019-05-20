import org.dom4j.Document;
import org.junit.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class test {
    public final static String HOME = System.getProperty("user.dir");
    public final static char SEP = File.separatorChar;
    public final static String TEST_RES_DIR = HOME + SEP + "resources" + SEP + "test";

    @Test
    public void test_main() {
        for (int i = 1; i <= 4; ++i) {
            Document srcDoc = util.readXML(TEST_RES_DIR + SEP + i + SEP + "src.xml");

            List<Document> patternDocs = new LinkedList<Document>();
            for (int pNum = 1; ; ++pNum) {
                String patternXML = TEST_RES_DIR + SEP + i + SEP + "p" + pNum + ".xml";
                if (new File(patternXML).exists()) {
                    patternDocs.add(util.readXML(patternXML));
                } else {
                    break;
                }
            }

            for (Document patternDoc : patternDocs) {
                main.search(srcDoc, patternDoc);
            }
        }
    }
}
