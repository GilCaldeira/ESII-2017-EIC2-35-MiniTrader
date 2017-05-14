package mt.server;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import mt.Order;

public class WriteXMLFile {
	
	private Document doc;
	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder; 
	private Order order;
	private Element rootElement;
	private Element elements; 
//	private Collection<Order> transitions = new ArrayList<Order>();
	private String buyOrSellOrder;
	
	public WriteXMLFile () throws ParserConfigurationException{
		this.docFactory = DocumentBuilderFactory.newInstance();
		this.docBuilder = docFactory.newDocumentBuilder();
		this.doc = docBuilder.newDocument();
		this.rootElement = doc.createElement("transitions");
		this.doc.appendChild(rootElement);
	}
	
	public void addOrder(Order order, String buyOrSellOrder){
		this.order=order;
		this.buyOrSellOrder=buyOrSellOrder;
		this.elements = doc.createElement("Order");
		this.rootElement.appendChild(elements);
//		transitions.add(order);
	}
	
	public void writeXML(){
		 try {
				
//				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//				Collection<Order> transitions = new ArrayList<Order>();
//				transitions.add(order);

		
				// root elements
//				Document doc = docBuilder.newDocument();
				
//				for (Order o : transitions) {
				
//				Element rootElement = doc.createElement("transitions");
//				doc.appendChild(rootElement);
				
				// elements
//				Element elements = doc.createElement("Order");
//				rootElement.appendChild(elements);
		
				// set attribute to staff element
				Attr attr = doc.createAttribute("id");
				attr.setValue("1");
				elements.setAttributeNode(attr);
		
				// BuyOrSellOrder
				Element first= doc.createElement("BuyOrSellOrder");
				first.appendChild(doc.createTextNode(buyOrSellOrder));
				elements.appendChild(first);
		
				// Stock
				Element stock = doc.createElement("Stock");
				stock.appendChild(doc.createTextNode(order.getStock()));
				elements.appendChild(stock);
		
				// NumberOfUnits
				Element numberOfUnits= doc.createElement("NumberOfUnits");
				numberOfUnits.appendChild(doc.createTextNode(String.valueOf(order.getNumberOfUnits())));
				elements.appendChild(numberOfUnits);
		
				// PricePerUnit
				Element pricePerUnit= doc.createElement("PricePerUnit");
				pricePerUnit.appendChild(doc.createTextNode(String.valueOf(order.getPricePerUnit())));
				elements.appendChild(pricePerUnit);
		
//				}
				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				
				StreamResult result = new StreamResult(new File("transitionsUS.xml"));
				transformer.transform(source, result);
		
				System.out.println("transitions saved in XML file!");
		
			  } catch (TransformerException tfe) {
				tfe.printStackTrace();
			  }
	}
	
	
	public static void main(String[] args) throws ParserConfigurationException {
		WriteXMLFile xml=new WriteXMLFile();
		
		Order o1 = new Order("", true, "10", 11, 100.0);
		xml.addOrder(o1, "buy");
		xml.writeXML();
		
		Order o2 = new Order("", true, "10", 11, 100.0);
		xml.addOrder(o2, "sell");
		xml.writeXML();
	}
	
}
