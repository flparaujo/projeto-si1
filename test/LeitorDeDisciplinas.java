import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import models.Disciplina;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LeitorDeDisciplinas {
	private static Map<String, Disciplina> listaDeDisciplina = new HashMap<String, Disciplina>();

	private static void populaMapas() {
		Map<String, Disciplina> disciplinaPorId = new HashMap<String, Disciplina>();
		try {
			Document doc = criaParserXml();
			NodeList nList = doc.getElementsByTagName("cadeira");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					criaDisciplina(disciplinaPorId, nNode);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void criaDisciplina(Map<String, Disciplina> disciplinaPorId, Node nNode) {
		List<Disciplina> requisitos = new ArrayList<Disciplina>();
		Element cadeiraXml = (Element) nNode;
		NodeList preRequisitos = cadeiraXml.getElementsByTagName("id");
		for (int i = 0; i < preRequisitos.getLength(); i++) {
			requisitos.add(disciplinaPorId.get(Integer.parseInt(preRequisitos.item(i).getTextContent())));
		}
		Disciplina criandoDisciplina = new Disciplina(cadeiraXml.getAttribute("nome"),
				Integer.parseInt(cadeiraXml.getElementsByTagName("creditos").item(0).getTextContent()),
				requisitos,
				Integer.parseInt(cadeiraXml.getElementsByTagName("dificuldade").item(0).getTextContent()),
				Integer.parseInt(cadeiraXml.getElementsByTagName("periodo").item(0).getTextContent()));
		String idCadeira = cadeiraXml.getAttribute("id");
		disciplinaPorId.put(idCadeira, criandoDisciplina);
		listaDeDisciplina.put(criandoDisciplina.getNome(), criandoDisciplina);
	}

	private static Document criaParserXml()
			throws ParserConfigurationException, SAXException, IOException {
		File fXmlFile = new File("test/disciplinas-do-curso.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		return doc;
	}

	public static List<Disciplina> getListaDeCadeiras() {
		if (listaDeDisciplina.isEmpty()) {
			populaMapas();
		}
		List<Disciplina> lista = new ArrayList<Disciplina>();
		for(Disciplina c: listaDeDisciplina.values()){
			lista.add(c);
		}
		return lista;
	}
}