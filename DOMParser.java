/**
 * 测试JAVA如何解析xml
 */

/**
 * @author Iver3oN Zhang
 * @date 2016年3月15日
 * @email grepzwb@qq.com
 * DOMParser.java
 * Impossible is nothing
 */
 import java.io.File; 
 import java.io.IOException; 
 import javax.xml.parsers.DocumentBuilder; 
 import javax.xml.parsers.DocumentBuilderFactory; 
 import javax.xml.parsers.ParserConfigurationException; 
 import org.w3c.dom.Document; 
 import org.w3c.dom.Element; 
 import org.w3c.dom.Node; 
 import org.w3c.dom.NodeList; 
 import org.xml.sax.SAXException; 

 public class DOMParser { 
   DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance(); 
   //Load and parse XML file into DOM 
   public Document parse(String filePath) { 
      Document document = null; 
      try { 
         //DOM parser instance 
         DocumentBuilder builder = builderFactory.newDocumentBuilder(); 
         //parse an XML file into a DOM tree 
         document = builder.parse(new File(filePath)); 
      } catch (ParserConfigurationException e) { 
         e.printStackTrace();  
      } catch (SAXException e) { 
         e.printStackTrace(); 
      } catch (IOException e) { 
         e.printStackTrace(); 
      } 
      return document; 
   } 
	
   public static void main(String[] args) { 
         DOMParser parser = new DOMParser(); 
         Document document = parser.parse("books.xml"); 
         //get root element 
         Element rootElement = document.getDocumentElement(); 
         System.out.println(rootElement.getNodeName());
         //traverse child elements 
         NodeList nodes = rootElement.getChildNodes(); 
         for (int i=0; i < nodes.getLength(); i++) 
         { 
            Node node = nodes.item(i); 
            if (node.getNodeType() == Node.ELEMENT_NODE) {   
               Element child = (Element) node; 
               //process child element 
               String title = child.getNodeName();
               System.out.println(title);
            } 
         } 

         NodeList nodeList = rootElement.getElementsByTagName("book"); 
         if(nodeList != null) 
         { 
            for (int i = 0 ; i < nodeList.getLength(); i++) 
            { 
               Element element = (Element)nodeList.item(i); 
               String id = element.getAttribute("id"); 
               NodeList node = element.getChildNodes();
               for(int j=0;j<node.getLength();j++){
            	   if(node.item(j).getNodeName().equals("title")){
            		   System.out.println(node.item(j).getTextContent());
            	   }
               } 
            } 
         } 
   } 
 }
