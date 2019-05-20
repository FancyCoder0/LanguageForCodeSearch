
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.*;
import java.util.List;

public class main {

    static void search(Document srcDoc, Document patternDoc) {
        List<Element> input = patternDoc.getRootElement().elements();

        Element patEle = input.get(0);

        matcher w = new matcher();

        if (input.size() > 1) {
            Element varEle = input.get(1);
            w.addVariable(varEle);
        }

        System.out.println("----Pattern----");

        System.out.println(w.prettyPattern(patternDoc));

        Element srcEle = srcDoc.getRootElement();

        w.tryMatch(srcEle, patEle);

        System.out.println("------------Finish------------");
    }



    // TODO (p4_6.xml) do folder.
    public static void main(String[] args) {
        String srcFile = args[0];
        String patternFile = args[1];
        Document srcDoc = util.readXML(srcFile);
        Document patternDoc = util.readXML(patternFile);
        main.search(srcDoc, patternDoc);
    }
}
