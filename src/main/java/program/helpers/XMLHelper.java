package program.helpers;

import program.Person;
import program.controller.PersonInterface;
import program.model.Model;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.io.File;

public class XMLHelper implements PersonInterface {

	private static  XMLHelper xmlHelper;

	private XMLHelper(){}

	public static XMLHelper getInstance(){
		if (xmlHelper == null){
			xmlHelper = new XMLHelper();
		}
		return xmlHelper;
	}
	Model model = Model.getInstance();

	private  int IforFile =0;



	@Override
	public void takePersonFromFile(String path) {//check
		File file = new File(path);

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
			Unmarshaller un = jaxbContext.createUnmarshaller();
			Person person;
			person = (Person) un.unmarshal(file);

			if (model.getData()[0]==null){
				model.getData()[0] = person;
			}else {
				model.getData()[IforFile + 1] = person;
				IforFile++;
			}
			showAllPersons();

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void savePersonToFile(String path, String params,String saveOrNot) {
		switch (saveOrNot.trim()){
			case "n":
				if(new File(path+".csv").delete()) {
					System.out.println("Файл удален");
				}
			case "y":
		}
		String[] param;
		param = params.split(",");
		Person person = new Person(Integer.parseInt(param[0]),param[1],param[2],Integer.parseInt(param[3]));
		try {

			JAXBContext context = JAXBContext.newInstance(Person.class);

			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			JAXBElement<Person> jaxbElement = new JAXBElement<>(new QName("","person"),Person.class,person);

			if (path.lastIndexOf(".csv")!=-1){
				String rev = new StringBuffer(path).reverse().toString();

				rev =rev.replaceFirst("vsc","lmx");
				path = new StringBuffer(rev).reverse().toString();
			}

			marshaller.marshal(jaxbElement,new File(path));


		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createFile(String path) {//check
		if (path.lastIndexOf(".xml")==-1) {
			path += ".xml";
		}
		File file = new File(path);
	}

	@Override
	public void deletePerson(String id) {

	}

	@Override
	public void createPerson(String params) {

	}

	@Override
	public void updatePerson(String id, String params) {

	}

	@Override
	public void showAllPersons() {

	}

	@Override
	public Person showOnePerson(String id) {
		return null;
	}
}
