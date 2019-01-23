package program.controller;

import program.Person;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.File;

public class XMLHelper {

	private static  XMLHelper xmlHelper;

	private XMLHelper(){}

	public static XMLHelper getInstance(){
		if (xmlHelper == null){
			xmlHelper = new XMLHelper();
		}
		return xmlHelper;
	}

	public void saveXML(String filePath, String paramLine, String saveOrNot){
		switch (saveOrNot.trim()){
			case "n":
				if(new File(filePath+".csv").delete()) {
					System.out.println("Файл удален");
				}
			case "y":
		}
		String[] params;
		params = paramLine.split(",");
		Person person = new Person(Integer.parseInt(params[0]),params[1],params[2],Integer.parseInt(params[3]));
		try {

			JAXBContext context = JAXBContext.newInstance(Person.class);

			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			JAXBElement<Person> jaxbElement = new JAXBElement<>(new QName("","person"),Person.class,person);

			if (filePath.lastIndexOf(".csv")!=-1){
				String rev = new StringBuffer(filePath).reverse().toString();

				rev =rev.replaceFirst("vsc","lmx");
				filePath = new StringBuffer(rev).reverse().toString();
			}

			marshaller.marshal(jaxbElement,new File(filePath));


		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
