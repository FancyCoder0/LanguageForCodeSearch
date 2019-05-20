import org.dom4j.*;

import java.util.*;

public class matcher {
    Map<String, List<Element>> UDFContent = new HashMap<String, List<Element>>();

    public void addVariable(Element r) {
//        System.out.println("addVariable");
        for (Element v : r.elements()) {
            String name = v.getName();
            if (name.startsWith("v-")) {
                UDFContent.put(name, v.elements());
            }
        }
    }

    public void prettyPatternWork(Element patEle) {
        if (patEle.getQName().getName().equals("abs")) {
            patEle.setText("[ABS]");
        } else if (patEle.getQName().getName().startsWith("v-")) {
            String UDFName = patEle.getQName().getName();
            if (UDFContent.containsKey(UDFName)) {
                String UDFString = "";
                boolean first = true, haveChoice = false;
                for (Element x : UDFContent.get(UDFName)) {
                    prettyPatternWork(x); // (TODO) could be opt.
                    if (first) {
                        first = false;
                    } else {
                        haveChoice = true;
                        UDFString += "|";
                    }
                    UDFString += x.getStringValue();
                }
                if (haveChoice) {
                    UDFString = "(" + UDFString + ")";
                }
                patEle.setText(UDFString);
            }
        } else {
            for (Iterator i = patEle.elementIterator(); ((Iterator) i).hasNext(); ) {
                Element x = (Element) i.next();
                prettyPatternWork(x);
            }
        }
    }

    public String prettyPattern(Document d) {
        Document tmp = (Document)d.clone();
        Element tmpRoot = tmp.getRootElement().elements().get(0);
        prettyPatternWork(tmpRoot);
        return tmpRoot.getStringValue().replace("\n", "").replaceAll(" +"," ");
    }

    public Boolean match(Element srcRoot, Element patRoot) {
        QName a = srcRoot.getQName(), b = patRoot.getQName();
        String aName = a.getName(), bName = b.getName();

//        if (bName.equals("abs")) {
//            return true;
//        }
        if (bName.equals("udv")) {
            return match(srcRoot, patRoot.elements().get(0));
        }
        if (UDFContent.containsKey(bName)) {
            for (Element x : UDFContent.get(bName)) {
                if (match(srcRoot, x)) {
                    return true;
                }
            }
            return false;
        }

        if (aName != bName) {
            return false;
        }

        //if (srcRoot.isTextOnly()) {
        if (patRoot.elements().size() == 0) {
            if (patRoot.elements().size() != 0) {
                return false;
            }
            if (!srcRoot.getStringValue().equals(patRoot.getStringValue())) {
//                System.out.println(srcRoot.getStringValue());
//                System.out.println("-----");
//                System.out.println(patRoot.getStringValue());
                return false;
            }
        }

        // compare on subtree
        List<Element> srcSon = srcRoot.elements(), patSon = patRoot.elements();


        int patInd = 0;
        Boolean couldSkip = false;

        for (int i = 0; i < srcSon.size(); ++i) {
            // use greedy algorithm, match the first one
            while ((patInd < patSon.size()) && (patSon.get(patInd).getQName().getName().equals("abs"))) {
                patInd += 1;
                couldSkip = true;
            }

            if ((patInd < patSon.size()) && match(srcSon.get(i), patSon.get(patInd))) {
                patInd += 1;
                couldSkip = false;
            } else if (!couldSkip) {
                return false;
            }
        }

        while ((patInd < patSon.size()) && (patSon.get(patInd).getQName().getName().equals("abs"))) {
            patInd += 1;
            couldSkip = true;
        }
        if (patInd < patSon.size()) {
            return false;
        }

//        System.out.println("back");
        return true;
    }


    public void tour(Element ele, List<Element> l) {
        l.add(ele);
        for (Element child : ele.elements()) {
            tour(child, l);
        }
    }
    public List<Element> getAllSubNode(Element ele) {
        List<Element> allSubNode = new LinkedList<Element>();
        tour(ele, allSubNode);
        return allSubNode;
    }

    public void tryMatch(Element srcRoot, Element patRoot) {
        List<Element> candid = getAllSubNode(srcRoot);
        // System.out.println(candid.size());

        int cnt = -1;
        for (Element c : candid) {
            cnt += 1;

            // System.out.println(cnt + ":" + c.getQName().getName());

            if (match(c, patRoot)) {
//                System.out.println("Successful Match!");
//                System.out.println("----Pattern----");
//                System.out.println(patRoot.getStringValue());
//                System.out.println(cnt + ":" + c.getQName().getName());

                System.out.println("----Match Code----");
                System.out.println(c.getStringValue());
            }
        }


    }


}
