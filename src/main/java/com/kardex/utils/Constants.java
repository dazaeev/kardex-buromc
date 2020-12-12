package com.kardex.utils;

import java.util.regex.Pattern;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
public class Constants {

	private Constants() {
		throw new IllegalStateException("kardex-buromc: Exception Constants");
	}
	
	public static final String C_LOGIN = "/login";
	
	// Cve de Acceso
	public static final Pattern C_HAS_UPPER_CASE 	= Pattern.compile("[A-Z]");
	public static final Pattern C_HAS_LOWER_CASE 	= Pattern.compile("[a-z]");
	public static final Pattern C_HAS_NUMBER		= Pattern.compile("\\d");
	public static final Pattern C_HAS_SPECIAL_CHAR = Pattern.compile("[^a-zA-Z0-9 ]");
	
	// Menu
	public static final String C_MENU_TITLE = "-TITLE-";
	public static final String C_MENU_HEADER = 
			"                <ul class=\"nav navbar-nav\">\n" + 
			"                    <li -HOME->\n" + 
			"                        <a href=\"/home\"> <i class=\"menu-icon fa fa-dashboard\"></i>Dashboard</a>\n" + 
			"                    </li>\n" + 
			"                    <h2 class=\"menu-title\" style=\"color: #00999d;\">Opciones</h2><!-- /.menu-title -->";
	public static final String C_MENU_CONTENT = "					<li class=\"menu-item-has-children dropdown\">\r\n" + 
			"						<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\"> <i class=\"menu-icon fa fa-laptop\"></i>-TITLE-</a>\r\n" + 
			"						<ul class=\"sub-menu children dropdown-menu\">";
	public static final String C_MENU_FOOTER = "                </ul>";
	
	public static final String C_BACKSLASH = "-BACKSLASH-";
	
	public static final String C_VERTICAL_BAR = "|";
	
	// Dashboard home-local
	public static final String C_COL_HOME_SEPARATED = "						<!--.col-->\n" + 
			"			            <div class=\"col-sm-5 -COL-LG-\">\n" + 
			"			                <div class=\"card text-white\" style=\"background-color:-COLOR-;\">\n" + 
			"			                    <div class=\"card-body pb-0\">\n" + 
			"			                        <div class=\"dropdown float-right\">\n" +
			"			                            <button class=\"btn bg-transparent dropdown-toggle theme-toggle text-light\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\">\n" + 
			"											<img style=\"height: 25px; width: 25px\" src=\"-IMAGE-\" alt=\"Avatar\">" + 
			"			                            </button>\n" + 
			"			                            <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">\n" + 
			"			                                <div class=\"dropdown-menu-content\">\n" + 
			"			                                    <a class=\"dropdown-item\" href=\"-HREF-\">Comenzar</a>\n" + 
			"			                                </div>\n" + 
			"			                            </div>\n" + 
			"			                        </div>\n" + 
			"			                        <h4 class=\"mb-0\">\n" + 
			"			                            <span class=\"count\">-TITLE-</span>\n" + 
			"			                        </h4>\n" + 
			"			                        <p class=\"text-light\">-SUBTITLE-</p>\n" + 
			"			                    </div>\n" + 
			"			                </div>\n" + 
			"			            </div>\n" + 
			"			            <!--/.col-->\n" + 
			"						";
	
	// Search
	public static final String C_SEARCH_THEAD_INIT = "<table id=\"-DATATABLE-\" class=\"table mb-0\">\n" + 
			"	<thead>\n" + 
			"		<tr>\n" + 
			"			";
	public static final String C_SEARCH_THEAD_END = "\n" + 
			"		</tr>\n" + 
			"	</thead>";
	public static final String C_SEARCH_BODY_INIT = "\n" + 
			"	<tbody>\n" + 
			"		";
	public static final String C_SEARCH_BODY_END = "\n" + 
			"	</tbody>\n" + 
			"</table>";
	
	// Key AES
	public static final String C_AES = "AES";
	public static final byte[] C_KEY_VALUE = new byte[] { 'B', 'u', 'r', 'o', 'M', 'c', 
			'k', 'a', 'r', 'd', 'e', 'x', 'D', 'a', 'z', 'a' };
}