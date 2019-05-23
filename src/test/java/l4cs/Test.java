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
    public void test_all() throws Exception {
        for (int i = 1; i <= 4; ++i) {
            Document srcDoc = Util.readXML(TEST_RES_DIR + SEP + i + SEP + "src.xml");

            for (int pNum = 1; ; ++pNum) {
                System.out.println("TestCase:" + i + "," + pNum);
                String patternXML = TEST_RES_DIR + SEP + i + SEP + "p" + pNum + ".xml";

                if (new File(patternXML).exists()) {
                    Main.search(srcDoc, Util.readXML(patternXML));
                } else {
                    break;
                }
            }
        }
    }

    @org.junit.Test
    public void test_single() throws Exception {
        int i = 4;
        int pNum = 7;

        System.out.println("TestCase:" + i + "," + pNum);

        Document srcDoc = Util.readXML(TEST_RES_DIR + SEP + 4 + SEP + "src.xml");
        String patternXML = TEST_RES_DIR + SEP + i + SEP + "p" + pNum + ".xml";

        Main.search(srcDoc, Util.readXML(patternXML));

    }
}
