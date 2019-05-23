package l4cs;

import org.dom4j.*;

import java.util.*;

public class Matcher {
    Map<String, List<Element>> UDFContent = null;

    private void addVariable(Element r) {
//        System.out.println("addVariable");
        for (Element v : r.elements()) {
            String name = v.getName();
            if (name.startsWith("v-")) {
                UDFContent.put(name, v.elements());
            }
        }
    }

    private Element initPattern(Document pat) {
        List<Element> patParts = pat.getRootElement().elements();
        Element patRoot = patParts.get(0);
        UDFContent = new HashMap<String, List<Element>>();
        if (patParts.size() > 1) {
            Element varEle = patParts.get(1);
            addVariable(varEle);
        }
        return patRoot;
    }


    public void prettyPatternWork(Element patEle) {
        String patName = patEle.getQName().getName();
        if (patName.equals("abs")) {
            patEle.setText("[ABS]");
        } else if (patName.startsWith("v-")) {
            if (UDFContent.containsKey(patName)) {
                String UDFString = "";
                boolean first = true, haveChoice = false;
                for (Element x : UDFContent.get(patName)) {
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
            if (patName.equals("annotation-folding")) {
                patEle.setText("[FOLD]");
            }
            for (Iterator i = patEle.elementIterator(); ((Iterator) i).hasNext(); ) {
                Element x = (Element) i.next();
                prettyPatternWork(x);
            }
        }
    }

    public String prettyPattern(Document d) {
        Document tmp = (Document)d.clone();
        Element tmpRoot = initPattern(tmp);
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

        if (bName.equals("annotation-folding")) {
            List<Element> subNodes = getAllSubNode(srcRoot);
            for (Element subNode : subNodes) {
                if (match(subNode, patRoot.elements().get(0))) {
                    return true;
                }
            }
            return false;
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

    public List<Document> tryMatch(Document src, List<Document> patList) {
        List<Element> candid = getAllSubNode(src.getRootElement());

        for (Document pat : patList) {
            Element patRoot = initPattern(pat);

            List<Element> newCandid = new LinkedList<Element>();
            int cnt = -1;
            for (Element c : candid) {
                cnt += 1;
                if (match(c, patRoot)) {
                    newCandid.add(c);
                }
            }
            candid = newCandid;
        }

        List<Document> ret = new LinkedList<Document>();
        for (Element c : candid) {
            ret.add(DocumentHelper.createDocument(c.createCopy()));
        }

        return ret;
    }


}
