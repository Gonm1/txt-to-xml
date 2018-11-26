/*
Tarea 2 Modelamiento de procesos e Informacion
Estudiante: Gonzalo Miranda Cabrera.
Seccion: 2
Profesor: Christian Vidal Castro.
Profesor de laboratorio: Jorge Elgueta Morales.
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Tarea {
	public static void main(String[] args){
		ArrayList<String> Lista = new ArrayList<String>();
		boolean flag = true;
		try {
			File Archivo = new File("Biblia.txt");

			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(Archivo), "UTF-8"));

			String str;

			while((str = in.readLine()) != null && flag){

				if(str.equalsIgnoreCase("HECHOS 12")){
					flag = false;
					Lista.add(str);
				}
			}
			while ((str = in.readLine()) != null) {
				if(str.equalsIgnoreCase("HECHOS 22")){
					break;
				}
				if(str.startsWith("0") || str.startsWith("1") || 
						str.startsWith("2") || str.startsWith("3") || 
						str.startsWith("4") || str.startsWith("5") || 
						str.startsWith("6") || str.startsWith("7") || 
						str.startsWith("8") || str.startsWith("9")){
					Lista.add(str);
				}
				if(str.endsWith("0") || str.endsWith("1") || 
						str.endsWith("2") || str.endsWith("3") || 
						str.endsWith("4") || str.endsWith("5") || 
						str.endsWith("6") || str.endsWith("7") || 
						str.endsWith("8") || str.endsWith("9")){
					Lista.add(str);
				}

			}

			in.close();
		}
		catch (UnsupportedEncodingException e)
		{
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		//-------------------------------------------------------------

		try{
			Document doc = new Document();
			Element Biblia = new Element("Biblia");
			doc.setRootElement(Biblia);
			Element Libro = new Element("Libro");
			Biblia.addContent(Libro);
			Element Nombre = new Element("Nombre");
			Nombre.addContent("HECHOS de los apostoles");
			Libro.addContent(Nombre);
			int q = 0;
			List<Element> list = (Libro.getChildren());
			
			for(int u = 0 ; u < Lista.size() ; u++){
				if(Lista.get(u).endsWith("0") || Lista.get(u).endsWith("1") || 
						Lista.get(u).endsWith("2") || Lista.get(u).endsWith("3") || 
						Lista.get(u).endsWith("4") || Lista.get(u).endsWith("5") || 
						Lista.get(u).endsWith("6") || Lista.get(u).endsWith("7") || 
						Lista.get(u).endsWith("8") || Lista.get(u).endsWith("9")){
					Libro.addContent(new Element("Capitulo").addContent(
							new Element("Nombre").addContent(Lista.get(u))));
					list = (Libro.getChildren());
					q++;
					
				}
				if(Lista.get(u).startsWith("0") || Lista.get(u).startsWith("1") || 
						Lista.get(u).startsWith("2") || Lista.get(u).startsWith("3") || 
						Lista.get(u).startsWith("4") || Lista.get(u).startsWith("5") || 
						Lista.get(u).startsWith("6") || Lista.get(u).startsWith("7") || 
						Lista.get(u).startsWith("8") || Lista.get(u).startsWith("9")){
					list.get(q).addContent(new Element("Versiculo").addContent(
							new Element("Nombre").addContent(
									"Versiculo " + (Lista.get(u).toCharArray())[0]
											+ (Lista.get(u).toCharArray())[1])));
					List aux = list.get(q).getChildren("Versiculo");
					if(aux != null){
						Element xx = (Element)aux.get(aux.size()-1);
						if(Lista.get(u).startsWith("0") || Lista.get(u).startsWith("1") || 
								Lista.get(u).startsWith("2") || Lista.get(u).startsWith("3") || 
								Lista.get(u).startsWith("4") || Lista.get(u).startsWith("5") || 
								Lista.get(u).startsWith("6") || Lista.get(u).startsWith("7") || 
								Lista.get(u).startsWith("8") || Lista.get(u).startsWith("9")){
							xx.addContent(new Element("Descripcion"));
							for(int i = 1 ; i < (Lista.get(u).split(" ")).length ; i++ ){
								xx.getChild("Descripcion").addContent((Lista.get(u).split(" ")[i]+" "));
							}
						}
					}
				}
			}
			System.out.println("Archivo XML listo.");
			XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
			xmlOutput.output(Biblia, new FileOutputStream(new File("BibliaXML.xml")));
			
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
}