package com.kardex.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailConstants;
import org.apache.commons.mail.HtmlEmail;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kardex.model.EmployeeNotification;
import com.kardex.model.Menu;
import com.kardex.model.RequestOfCourses;
import com.kardex.model.User;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
public class Utils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);
	
	/**
	 * Constructor
	 */
	private Utils() {
		throw new IllegalStateException("kardex-buromc: Exception Utils");
	}
	
	public static Map<Long, String> readFile(String nameFile) {
		Map<Long, String> mapData = new HashMap<Long, String>();
		File archivo = null;
		BufferedReader br = null;
		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(nameFile);
			br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(archivo), "UTF-8"));
			// Lectura del fichero
			String linea;
			Long index = new Long(0);
			while ((linea = br.readLine()) != null) {
				index++;
				mapData.put(index, linea);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != br) {
					br.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return mapData;
	}
	
	/**
	 * @param cveA
	 * @param cveB
	 * @return
	 */
	public static String validateCve(String cveA, String cveB) {
		String log = "";
		boolean validate = true;
		if (cveA == null || cveB == null) {
			LOGGER.info("Passwords = null");
			return "One or both passwords are null";
		}
		StringBuilder retVal = new StringBuilder();
		if (cveA.isEmpty() || cveB.isEmpty()) {
			retVal.append("Empty fields <br>");
		}
		if (cveA.equals(cveB)) {
			log = "cveA = cveB";
			LOGGER.info(log);
			retVal.append("La contraseña<br>");
			if (cveA.length() < 8) {
				log = "**********" + " is length < 8";
				LOGGER.info(log);
				retVal.append("es demasiado corta, necesita tener al menos 8 caracteres<br>");
				validate = false;
			}
			if (!Constants.C_HAS_UPPER_CASE.matcher(cveA).find()) {
				log = "**********" + " <-- needs uppercase";
				LOGGER.info(log);
				retVal.append("necesita mayúsculas<br>");
				validate = false;
			}
			if (!Constants.C_HAS_LOWER_CASE.matcher(cveA).find()) {
				log = "**********" + " <-- needs lowercase";
				LOGGER.info(log);
				retVal.append("necesita una minúscula<br>");
				validate = false;
			}
			if (!Constants.C_HAS_NUMBER.matcher(cveA).find()) {
				log = "**********" + " <-- needs a number";
				LOGGER.info(log);
				retVal.append("necesita un número<br>");
				validate = false;
			}
			if (!Constants.C_HAS_SPECIAL_CHAR.matcher(cveA).find()) {
				log = "**********" + " <-- needs a specail character";
				LOGGER.info(log);
				retVal.append("necesita un carácter especial, es decir !,@,#, etc.<br>");
				validate = false;
			}
		} else {
			log = "cveA != cveB";
			LOGGER.info(log);
			retVal.append("Las contraseñas no coinciden<br>");
		}
		if (validate) {
			LOGGER.info("Password validates");
			retVal.setLength(0);
			retVal.append("ok");
		}
		return retVal.toString();
	}
	
	/**
	 * Creacion de Menu por empleado
	 * @param listMenu
	 * @param index
	 * @return
	 */
	public static String createMenu(List<Menu> listMenu, String index) {
		String resp = (null != index && index.equals("home") ? 
					Constants.C_MENU_HEADER.replaceAll("-HOME-", "class=\"active\"") : 
						Constants.C_MENU_HEADER.replaceAll("-HOME-", "")) + "\n";
		// Administrar
		List<Menu> listAdministrar = listMenu.stream().filter(x -> "Administrar".equals(x.getMenu())).collect(Collectors.toList());
		if(!listAdministrar.isEmpty()) {
			resp += Constants.C_MENU_CONTENT.replaceAll(Constants.C_MENU_TITLE, "Administrar") +
					"\n";
		}
		for (Iterator<Menu> iterator = listAdministrar.iterator(); iterator.hasNext();) {
			Menu menu = iterator.next();
			resp += menu.getHtml() + "\n";
		}
		if(!listAdministrar.isEmpty()) {
			resp += "                        </ul>\r\n" + 
					"					</li>" +
					"\n";
		}
		
		// Catalogo
		List<Menu> listExtras = listMenu.stream().filter(x -> "Catalogos".equals(x.getMenu())).collect(Collectors.toList());
		if(!listExtras.isEmpty()) {
			resp += Constants.C_MENU_CONTENT.replaceAll(Constants.C_MENU_TITLE, "Catalogos") +
					"\n";
		}
		for (Iterator<Menu> iterator = listExtras.iterator(); iterator.hasNext();) {
			Menu menu = iterator.next();
			resp += menu.getHtml() + "\n";
		}
		if(!listExtras.isEmpty()) {
			resp += "                        </ul>\r\n" + 
					"					</li>" +
					"\n";
		}
		
		// Plan de carrera
		List<Menu> listPlan = listMenu.stream().filter(x -> "Plan de carrera".equals(x.getMenu())).collect(Collectors.toList());
		if(!listPlan.isEmpty()) {
			resp += Constants.C_MENU_CONTENT.replaceAll(Constants.C_MENU_TITLE, "Plan de carrera") +
					"\n";
		}
		for (Iterator<Menu> iterator = listPlan.iterator(); iterator.hasNext();) {
			Menu menu = iterator.next();
			resp += menu.getHtml() + "\n";
		}
		if(!listPlan.isEmpty()) {
			resp += "                        </ul>\r\n" + 
					"					</li>" +
					"\n";
		}
		
		return resp + Constants.C_MENU_FOOTER;
	}
	
	public static String createHomeUsers(List<Menu> listMenu, String columnLarge) {
		String resp = "";
		// Recorrido -----
		String record = Constants.C_COL_HOME_SEPARATED.replaceAll("-HREF-", "javascript:record();");
		record = record.replaceAll("-TITLE-", "Demostración");
		record = record.replaceAll("-SUBTITLE-", "Recorrido");
		record = record.replaceAll("-COLOR-", "#04B45F");
		record = record.replaceAll("-COL-LG-", "col-lg-" + columnLarge);
		record = record.replaceAll("-IMAGE-", "../images/running.png");
		resp += record;
		// ---------------
		final String []colors = {"#3b96b2", "#3ab2b3", "#035070", "#0c5b59",
								"#0b758e", "#00809f", "#42b7a6", "#009b81"};
		Iterator<Menu> iter = listMenu.iterator();
		int count = 0;
		while(iter.hasNext()) {
			count++;
			if(count == 7) {
				count = 1;
			}
			Menu menu = iter.next();
			String col = Constants.C_COL_HOME_SEPARATED.replaceAll("-HREF-", menu.getHref());
			col = col.replaceAll("-TITLE-", menu.getTitle());
			col = col.replaceAll("-SUBTITLE-", menu.getMenu());
			col = col.replaceAll("-COLOR-", colors[count - 1]);
			col = col.replaceAll("-COL-LG-", "col-lg-" + columnLarge);
			col = col.replaceAll("-IMAGE-", menu.getImage());
			resp += col;
		}
		return resp;
	}
	
	public static String createPhoto(String sex) {
		String resp = "<img class=\"user-avatar rounded-circle\" src=\"-PHOTO-\" alt=\"Avatar\">";
		if(!sex.equals("")) {
			if(sex.equals("Hombre")) {
				resp = resp.replace("-PHOTO-", "../images/hombre.jpg");
			} else {
				resp = resp.replace("-PHOTO-", "../images/mujer.jpg");
			}
		} else {
			resp = resp.replace("-PHOTO-", "../images/admin.jpg");
		}
		return resp ;
	}
	
	public static String createNotification(Iterator<EmployeeNotification> iterNotification) {
		String resp = "								<p style=\"color: red;\">Tienes -COUNT- notificaciones</p>\n";
		// Recorrido -----
		int count = 0;
		int countColor = 0;
		while(iterNotification.hasNext()) {
			count++;
			countColor++;
			if(countColor == 9) {
				countColor = 1;
			}
			EmployeeNotification employeeNotification = iterNotification.next();
			resp += "								<div class=\"dropdown-divider\"></div>\n" +
					"								<a class=\"dropdown-item media bg-flat-color-bmc-" + countColor + "\" title=\"Descartar\">\r\n" + 
					"									<i class=\"fa fa-check\"></i>\r\n" + 
					"									" + employeeNotification.getValue() + "\r\n" + 
					"									<button type=\"button\" \r\n" + 
					"										class=\"btn btn-light btn-sm\" \r\n" + 
					"										style=\"position: absolute;right: 5px;border-top-width: 0px;padding-bottom: 0px;padding-top: 0px;\"\r\n" + 
					"										onclick=\"callNotification(" + employeeNotification.getId() + ");\">\r\n" + 
					"										<i class=\"fa fa-close\" style=\"margin: 1px -9px 2px 0px;\"></i>\r\n" +
					"									</button>\r\n" + 
					"								</a>\n";
		}
		resp = resp.replaceAll("-COUNT-", "" + count);
		return resp;
	}
	
	public static String convertColorHex(Random r) {
		final char[] hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] s = new char[7];
		int n = r.nextInt(0x1000000);

		s[0] = '#';
		for (int i = 1; i < 7; i++) {
			s[i] = hex[n & 0xf];
			n >>= 4;
		}
		return new String(s);
	}
	
	public static String getMailName(String email) {
		return email.replace("@", "_").replaceAll("\\.", "_");
	}
	
	/**
	 * Convertir cadena Base64 a arrego de bytes
	 * @param base64
	 * @return
	 */
	public static byte[] convertToByte(String base64) {
		return Base64.decodeBase64(base64);
	}
	
	/**
	 * @param data
	 * @param format
	 * @return
	 */
	public static Object formatDate(Object data, String format) {
		String date = new SimpleDateFormat(format).format(data);
		return date;
	}
	
	public static Date formatDate(String source, String format) {
		try {
			return new SimpleDateFormat(format).parse(source);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * @param action 	- suma (suma dias del dia actual)
	 * @param day		- resta (resta dias del dia actual)
	 * @return
	 */
	public static Date subtractDays(String action, int day) {
		Calendar dToday = new GregorianCalendar();
		if (action.equals("suma")) {
			dToday.add(Calendar.DATE, day); // se le suman
		}
		if (action.equals("resta")) {
			dToday.add(Calendar.DATE, -day); // se le restan
		}
		return dToday.getTime();
	}
	
	/**
	 * @param date		- fecha a la cual se le restaran los dias
	 * @param action 	- suma (suma dias de la fecha enviada)
	 * @param day		- resta (resta dias de la fecha enviada)
	 * @return
	 */
	public static Date subtractDays(Date date, String action, int day) {
		Calendar dToday = Calendar.getInstance();
		dToday.setTime(date);
		if (action.equals("suma")) {
			dToday.add(Calendar.DATE, day); // se le suman
		}
		if (action.equals("resta")) {
			dToday.add(Calendar.DATE, -day); // se le restan
		}
		return dToday.getTime();
	}
	
	public static String formatNumber(String data, String language, String country) {
		return NumberFormat.getCurrencyInstance(new Locale(language, country)).format(Double.parseDouble(data));
	}
	
	/**
	 * @param price
	 * @return
	 */
	public static String priceWithDecimal(Double price) {
		DecimalFormat formatter = new DecimalFormat("###,###,###.00");
		return formatter.format(price);
	}

	/**
	 * @param price
	 * @return
	 */
	public static String priceWithoutDecimal(Double price) {
		DecimalFormat formatter = new DecimalFormat("###,###,###.##");
		return formatter.format(price);
	}

	/**
	 * @param price
	 * @return
	 */
	public static String priceToString(Double price) {
		String toShow = priceWithoutDecimal(price);
		if (toShow.indexOf(".") > 0) {
			return priceWithDecimal(price);
		} else {
			return priceWithoutDecimal(price);
		}
	}
	
	private static String encodeFileToBase64Binary(String fileName) throws IOException {
	    File file = new File(fileName);
	    byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
	    String valueDocument = new String(encoded, StandardCharsets.UTF_8);
	    System.out.println("URI Obtenida: " + fileName);
	    return valueDocument;
	}
	
	/**
	 * Crear archivo -> Bytes a File
	 * @param contentBytes
	 * @param fileName
	 * @throws IOException
	 */
	public static void convertByteToFile(byte[] contentBytes, String fileName) throws IOException {
		FileUtils.writeByteArrayToFile(new File(fileName), contentBytes);
	}
	
	/**
	 * Crear archivo -> Base64 a File
	 * @param contentBase64
	 * @param fileName
	 * @throws IOException
	 */
	public static void convertBase64ToFile(String contentBase64, String fileName) throws IOException {
		FileUtils.writeByteArrayToFile(new File(fileName), convertToByte(contentBase64));
	}
	
	public static boolean createFolder(String folder) {
		File fileSystem = new File(folder);
		return fileSystem.mkdirs();
	}
	
	public static boolean createFolder(String folder, boolean permission) {
		File fileSystem = new File(folder);
		if(permission) {
			fileSystem.mkdirs();
			fileSystem.setExecutable(true, false);
			fileSystem.setReadable(true, false);
			fileSystem.setWritable(true, false);
		}
		return true;
	}
	
	public static void deleteFolder(String folder) throws IOException {
		File fileSystem = new File(folder);
		FileUtils.deleteDirectory(fileSystem);
	}
	
	public static void createFile(String file, String data, String encode) throws IOException {
		Files.write(Paths.get(file), data.getBytes(encode));
	}
	
	public static boolean createFileEmployee(String rootFile, String nameFile, String dataFile) throws Exception {
		// Separar nombre y contenido
		String []splitName = nameFile.split("____");
		String []splitData = dataFile.split("\\,");
		convertBase64ToFile(splitData[1], rootFile + File.separator + splitName[0]);
		return true;
	}
	
	public static Map<String, String> getDocEmployee(String nameFile) {
		Map<String, String> map = new HashMap<>();
		try {
			map.put("status", "Ok");
			nameFile = nameFile.replaceAll(Constants.C_BACKSLASH, "\\" + File.separator);
			String []splitName = nameFile.split("____");
			String base64Document = encodeFileToBase64Binary(splitName[0]);
			map.put("base64Document", "data:" + splitName[1] + ";base64,"+ base64Document);
		} catch (Exception e) {
			map.put("status", "Nok");
			map.put("error", "Error: " + e.getMessage());
		}
		return map;
	}
	
	public static String copyFileSystem(String email, String nameFile, String dataFile, String rootFileSystem, String folder) {
		String result = "";
		try {
			createFolder(rootFileSystem + File.separator + email + File.separator + folder);
			createFileEmployee(rootFileSystem + File.separator + email + File.separator + folder, nameFile, dataFile);
			result = "Ok";
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}
	
	public static Map<String, String> viewDocEmployee(String email, String nameFile, String rootFileSystem, String folder) {
		Map<String, String> map = new HashMap<>();
		try {
			String nameFinal = rootFileSystem + File.separator + getMailName(email) + File.separator + folder + File.separator + nameFile;
			String []splitName = nameFinal.split("____");
			//
			String base64Document = encodeFileToBase64Binary(splitName[0]);
			map.put("email", getMailName(email));
			map.put("namedocument", splitName[0]);
			map.put("typedocument", splitName[1]);
			map.put("base64Document", "data:" + splitName[1] + ";base64,"+ base64Document);
			map.put("nameOriginal", "" + nameFile.split("____")[0]);
			map.put("status", "Ok");
		} catch (Exception e) {
			map.put("status", "Nok");
			map.put("error", "Error: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * @param rootFile
	 * @return
	 * @throws Exception
	 */
	public static JSONArray readJson(String rootFile) throws Exception {
		JSONParser parser = new JSONParser();
		return (JSONArray) parser.parse(new InputStreamReader(new FileInputStream(rootFile), StandardCharsets.UTF_8));
	}
	
	/**
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static JSONArray readJson(InputStream in) throws Exception {
		JSONParser parser = new JSONParser();
		return (JSONArray) parser.parse(new InputStreamReader(in, StandardCharsets.UTF_8));
	}
	
	/**
	 * @param in
	 * @param key
	 * @param val
	 * @return
	 * @throws Exception
	 */
	public static JSONObject searchObjectJson(InputStream in, String key, String val) throws Exception {
		JSONArray jsonArray = readJson(in);
		Iterator<?> iterJson = jsonArray.iterator();
		while(iterJson.hasNext()) {
			JSONObject jsonObject = (JSONObject) iterJson.next();
			if(jsonObject.get(key) instanceof String && jsonObject.get(key).toString().equals(val)) {
				return jsonObject;
			}
			if(jsonObject.get(key) instanceof Long && Long.parseLong(jsonObject.get(key).toString()) == Long.parseLong(val)) {
				return jsonObject;
			}
		}
		return null;
	}
	
	/**
	 * @param in
	 * @return
	 */
	public static Map<Integer, String> getStates(InputStream in) {
		Map<Integer, String> stateMap = new HashMap<>();
		try {
			JSONArray companyList = readJson(in);
			//
			Iterator<?> iterJson = companyList.iterator();
			while(iterJson.hasNext()) {
				JSONObject stat = (JSONObject) iterJson.next();
				stateMap.put(Integer.parseInt(stat.get("id").toString()), stat.get("name").toString());
			}
		} catch (Exception e) {
			return null;
		}
		return stateMap;
	}
	
	/**
	 * @param in
	 * @param state
	 * @return
	 */
	public static Map<Integer, String> getCities(InputStream in, int state) {
		Map<Integer, String> stateMap = new HashMap<>();
		try {
			JSONArray companyList = readJson(in);
			//
			Iterator<?> iterJson = companyList.iterator();
			while(iterJson.hasNext()) {
				JSONObject stat = (JSONObject) iterJson.next();
				int stateId = Integer.parseInt(stat.get("state_id").toString());
				String name = stat.get("name").toString();
				if(stateId == state) {
					stateMap.put(Integer.parseInt(stat.get("id").toString()), name);
				}
			}
		} catch (Exception e) {
			return null;
		}
		return stateMap;
	}
	
	public static String[] removeRepeatArray(String []vecEmail) {
		int count = 0;
		for (int i = 0; i < vecEmail.length; i++) {
			for (int j = 0; j < vecEmail.length - 1; j++) {
				if (i != j) {
					// En este parte sustituimos el "==" por el "equals"
					if (!vecEmail[i].equals("") && vecEmail[i].equals(vecEmail[j])) {
						// eliminamos su valor
						vecEmail[i] = "";
						count++;
					}
				}
			}
		}
		String []vecEmailAux = new String[vecEmail.length - count];
		//
		int countAux = -1;
		for (int i = 0; i < vecEmail.length; i++) {
			if (vecEmail[i] != "") {
				countAux++;
				vecEmailAux[countAux] = vecEmail[i];
			}
		}
		return vecEmailAux;
	}
	
	/**
	 * Envio de correo
	 * @param host
	 * @param port
	 * @param inEmail
	 * @param key
	 * @param from
	 * @param subject
	 * @param debug
	 * @param msg
	 * @param to
	 * @return
	 */
	public static String sendEmail(String host, int port, String inEmail, String key, String from, String subject, boolean debug, String msg, String to, 
			boolean attachmentFile, String pathFile, String descriptionFile, String nameFile) {
		HtmlEmail email = new HtmlEmail();
		email.setCharset(EmailConstants.UTF_8);
		email.setHostName(host);
		email.setSmtpPort(port);
		email.setAuthenticator(new DefaultAuthenticator(inEmail, key));
		email.setStartTLSEnabled(true);
		try {
			email.setFrom(from);
			email.setSubject(subject);
			if (debug) {
				email.setDebug(true);
			}
			email.setHtmlMsg(msg);
			// Validar si son varios destinatarios
			String []toSplit = removeRepeatArray(to.trim().split("\\;"));
			if(toSplit.length > 0) {
				for (int i = 0; i < toSplit.length; i++) {
					email.addBcc(toSplit[i]);
				}
			} else {
				email.addBcc(to);
			}
			// Validar si se envia archivo
			if(attachmentFile) {
				// Create the attachment
				EmailAttachment attachment = new EmailAttachment();
				attachment.setPath(pathFile); // Path filesystem
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				attachment.setDescription(descriptionFile); // Name file
				attachment.setName(nameFile); // Title
				email.attach(attachment);
			}
			email.send();
		} catch (Exception e) {
			return e.getMessage();
		}
		return "";
	}
	
	/**
	 * Compara el usuario registrado en BD contra el usuario logeado
	 * </br>Si es ADMIN puede ver todos los registros
	 * </br>Si es USERS solo puede ver su registro
	 * @param user	- usuario a validar
	 * @param map	- mapa de los datos del usuario logeado
	 * @return
	 */
	public static boolean validateUserAdmin(User user, Map<String, String> map) {
		boolean resp = false;
		if(map.get("ROLE").equals("ADMIN")) {
			LOGGER.info("ALL: {}", user.getEmail());
			resp = true;
		}
		if(map.get("ROLE").equals("USERS")) {
			if(map.get("USERNAME").equals(user.getEmail())) {
				LOGGER.info("USERS: {}", user.getEmail());
				resp = true;
			}
		}
		return resp;
	}
	
	/**
	 * Ejecutar comando
	 * @param command
	 * @return
	 */
	public static String runtime(String command) {
		String console = "";
		System.out.println("\nRun: " + command);
		Runtime runtime = Runtime.getRuntime();
		try {
			Process p1 = runtime.exec(command);
			InputStream is = p1.getInputStream();
			int i = 0;
			while ((i = is.read()) != -1) {
				System.out.print("" + (char) i);
				console += "" + (char) i;
			}
			is.close();
			//
			InputStream isError = p1.getErrorStream();
			int iE = 0;
			boolean isErrorLog = false;
			while ((iE = isError.read()) != -1) {
				isErrorLog = true;
				System.out.print("" + (char) iE);
				console += "" + (char) iE;
			}
			if(isErrorLog) {
				console += "\n-ERROR-";
			}
			isError.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			console = e.getMessage();
		}
		return console;
	}
	
	public static String removeDoubleQuoteBeginningEndt(int index, String []array) {
		String value = new String();
		if(index == 0) { // Quitar "id por id al principio
			if(array[index].length() > 1) {
				value = array[index].substring(1, array[index].length());
			}
		} else if(index == array.length - 1) { // Quitar image" por image al final
			if(array[index].length() > 1) {
				value = array[index].substring(0, array[index].length() - 1);
			}
		} else {
			value = array[index];
		}
		return value;
	}
	
	public static List<Map<String, String>> createDynamicSearch(List<String> data) throws Exception {
		List<Map<String, String>> listMap = new LinkedList<>();
		try {
			Iterator<String> iter = data.iterator();
			while(iter.hasNext()) {
				Map<String, String> map = new TreeMap<>();
				String value = iter.next();
				String []splitData = value.split("\\|");
				if(splitData.length > 2) {
					// Excluir tablas
					if(!splitData[0].equals("mail_template") && !splitData[0].equals("temp_details") && !splitData[0].equals("employee_notification")) {
						String idTable = UUID.randomUUID().toString();
						// menu|html|/var/lib/mysql-files/ngonzalez_buromc_mx/menu__04794586296001995_3.csv
						String nameTable = splitData[0];
						String fieldTable = splitData[1];
						String rootFile = splitData[2];
						String headerHtml = Constants.C_SEARCH_THEAD_INIT.replaceAll("-DATATABLE-", idTable);
						String bodyHtml = Constants.C_SEARCH_BODY_INIT;
						Map<Long, String> dataFile = readFile(rootFile);
						for (long i = 1; i <= dataFile.size(); i++) {
							if(i < 2) {
								// header
								String []valueHeader = dataFile.get(i).split("\",\"");
								for (int vh = 0; vh < valueHeader.length; vh++) {
									valueHeader[vh] = removeDoubleQuoteBeginningEndt(vh, valueHeader);
									// Validar si es el Campo donde se encontro el dato
									if(valueHeader[vh].equals(fieldTable)) {
										headerHtml += "<th class=\"table-danger\">" + valueHeader[vh] + "</th>\n";
									} else {
										headerHtml += "<th>" + valueHeader[vh] + "</th>\n";
									}
								}
								headerHtml += Constants.C_SEARCH_THEAD_END;
							} else {
								// body
								String []valueBody = dataFile.get(i).split("\",\"");
								bodyHtml += "<tr id=\"" + UUID.randomUUID().toString() + i + "\">\n";
								for (int vb = 0; vb < valueBody.length; vb++) {
									valueBody[vb] = removeDoubleQuoteBeginningEndt(vb, valueBody);
									bodyHtml += "<td>" + valueBody[vb] + "</td>\n";
								}
								bodyHtml += "</tr>\n";
							}
						}
						bodyHtml += Constants.C_SEARCH_BODY_END;
						map.put("table", headerHtml + bodyHtml);
						map.put("fieldTable", fieldTable);
						map.put("idTable", idTable);
						//
						map.put("file", nameTable);
						listMap.add(map);
					}
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return listMap;
	}
	
	public static String jaxbObjectToXML(RequestOfCourses requestOfCourses) {
		String xmlString = "";
		try {
			JAXBContext context = JAXBContext.newInstance(RequestOfCourses.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
			StringWriter sw = new StringWriter();
			m.marshal(requestOfCourses, sw);
			xmlString = sw.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlString;
	}
	
	public static void createWord(String wordTemplatePath, String xmlDataPath, String wordOutputPath) throws FileNotFoundException, Docx4JException {
    	WordTemplateFiller wordTemplateFiller = new WordTemplateFiller();
        wordTemplateFiller.generateWord(wordTemplatePath, xmlDataPath, wordOutputPath);
	}
	
	public static String createWordRequestOfCourses(String rootWordTemplate, String folder, String valueXml, int id) throws IOException, Docx4JException {
		folder = folder + File.separator + "request_of_courses" + File.separator + id;
		createFolder(folder);
		String nameFileXml = "SolicitudCursos_id_" + id + ".xml";
		// Crear XML template
		createFile(folder + File.separator + nameFileXml, valueXml, "UTF-8");
		// Crear Word
		String nameFileWord = "SolicitudCursos_id_" + id + ".docx";
		createWord(rootWordTemplate, 
				folder + File.separator + nameFileXml,
				folder + File.separator + nameFileWord);
		// Mandar ruta de archivo
		return folder + File.separator + nameFileWord;
	}
}