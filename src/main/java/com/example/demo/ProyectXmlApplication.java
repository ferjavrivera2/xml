package com.example.demo;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

@SpringBootApplication
public class ProyectXmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectXmlApplication.class, args);
		try {
            String filePath = "src/main/java/NewFile.xml";
            //  1. Cree un objeto Archivo y asigne el archivo XML
            File file = new File(filePath);
            //  2. Cree un objeto DocumentBuilderFactory para crear un objeto DocumentBuilder
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //  3. Cree un objeto DocumentBuilder para convertir archivos XML en objetos Document
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //  4. Cree un objeto de documento y analice el archivo XML
            Document document = documentBuilder.parse(file);             //  Modificar el primer alumno
            //  5. Obtenga el primer nodo de estudiante
            Node student = document.getElementsByTagName("student").item(0);
            //  Obtenga todos los atributos del nodo de estudiante
            NamedNodeMap namedNodeMap = student.getAttributes();
            //  Obtenga el ID de atributo que define al estudiante
            Node id = namedNodeMap.getNamedItem("id");
            //  Restablecer el valor del id del atributo
            id.setTextContent("student11");
            //  6. Obtenga el primer alumno de nodo secundario directo de los alumnos de nodo raíz
            student = document.getElementsByTagName("student").item(0); 
            Element studentElement = (Element) student;
            //  7. Obtenga el nombre del nodo secundario directo y el sexo del estudiante del nodo.
            Node name = studentElement.getElementsByTagName("name").item(0);
            Node sex = studentElement.getElementsByTagName("sex").item(0);
            Element nameElement = (Element) name;
            Element sexElement = (Element) sex;
            //  8. Establecer valores para el nodo
            nameElement.setTextContent("TomTom");
            sexElement.setTextContent("FemaleFemale");
            //  9. Cree un objeto TransformerFactory
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            //  10. Crea un objeto Transformer
            Transformer transformer = transformerFactory.newTransformer();
            //  11. Cree un objeto DOMSource
            DOMSource domSource = new DOMSource(document);
            //  12. Crear objeto StreamResult
            StreamResult reStreamResult = new StreamResult(file);
            transformer.transform(domSource, reStreamResult);

            //  Resultados de la prueba de salida
            StreamResult consoleResult = new StreamResult(System.out);     
            transformer.transform(domSource, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
