package cc.sitec;


import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

/**
 * Created by keeley on 2017/5/31.
 */
public class Demo {
    public static void main(String[] args) throws DocumentException {
        String path = Demo.class.getClassLoader().getResource("imsmanifest.xml").getPath();
        parse(path).forEach(System.out::println);
    }

    private static List<Scorm>  parse(String path) throws DocumentException {
        File file = new File(path);
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(file);

        Map<String ,Scorm> scorms = new HashMap<>();

        doc.getRootElement().element("organizations").elements("organization").forEach(
                ele -> scorms.putAll(parseItem((Element)ele, null)));

        doc.getRootElement().element("resources").elements("resource").forEach(obj -> {
            Element element = (Element) obj;
            String identifier = filterAttribute(element, "identifier");
            String type = filterAttribute(element, "type");
            String href = filterAttribute(element, "href");
            String scormtype = filterAttribute(element, "scormtype");

            Scorm scorm = scorms.get(identifier);
            scorm.setType(type);
            scorm.setHref(href);
            scorm.setScormtype(scormtype);
        });
        List<Scorm> result = new ArrayList<>(scorms.values());
        result.sort( (a,b) -> a.getSequence() - b.getSequence() );
        return result;
    }
    private static Map<String ,Scorm> parseItem(Element ele, String parentId) {
        Map<String ,Scorm> scorms = new HashMap<>();
        final int[] index = {0};
        ele.elements("item").forEach(ele2-> {
            Element item = (Element) ele2;

            Scorm scorm = new Scorm();
            scorm.setSequence(index[0]++);
            String identifier = filterAttribute(item, "identifier");
            String title = Optional.ofNullable(item.element("title")).map(x -> x.getText()).orElse(null);
            String identifierref = filterAttribute(item, "identifierref");
            String isvisible = filterAttribute(item, "isvisible");

            scorm.setTitle(title);
            scorm.setIdentifier(identifier);
            scorm.setIsvisible(isvisible);
            scorm.setIdentifierref(identifierref);
            scorm.setParentId(parentId);
            if(identifierref==null) identifierref = identifier;
            scorms.put(identifierref,scorm);
            scorms.putAll(parseItem(item, identifier));
        });
        return scorms;
    }

    private static String filterAttribute(Element element,String attributeName) {
        return Optional.ofNullable(element.attribute(attributeName)).map(Attribute::getValue).orElse(null);
    }
}
