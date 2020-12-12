package com.kardex.utils;

import java.io.Serializable;
import java.security.Key;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class LdapUtils implements Serializable {
	
	private static final long serialVersionUID = -3204749002905910656L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LdapUtils.class);
	
	private LdapUtils() {
		throw new IllegalStateException("kardex-buromc: Exception LdapUtils");
	}
	
	public static LdapContext getLdapContext(String initial, String aut, String cn, String cred, String url, String ref) {
        LdapContext ctx = null;
        try {
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, initial);
            env.put(Context.SECURITY_AUTHENTICATION, aut);
            env.put(Context.SECURITY_PRINCIPAL, cn); //input user & password for access to ldap
            env.put(Context.SECURITY_CREDENTIALS, cred);
            env.put(Context.PROVIDER_URL, url);
            env.put(Context.REFERRAL, ref);
            ctx = new InitialLdapContext(env, null);
            LOGGER.info("LDAP Connection: COMPLETE");
        } catch (NamingException nex) {
        	LOGGER.info("LDAP Connection: FAILED - " + nex.getMessage());
            nex.printStackTrace();
        }
        return ctx;
    }
	
	public static SearchControls getSearchControls() {
        SearchControls cons = new SearchControls();
        cons.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String[] attrIDs = 
        	{
        		"displayName", 
        		"givenName", 
        		"objectCategory", 
        		"cn", 
        		"sn", 
        		"userPrincipalName", 
        		"distinguishedName", 
        		"sAMAccountName", 
        		"memberOf", 
        		"name", 
        		"telephoneNumber", 
        		"thumbnailPhoto"
        	};
        cons.setReturningAttributes(attrIDs);
        return cons;
    }
	
	public static Map<String, String> userInfo(String userName, String dc, LdapContext ctx, SearchControls searchControls) throws Exception {
		Map<String, String> map = new TreeMap<>();
		try {
        	SearchControls searchControlsAux = new SearchControls();
        	searchControlsAux.setSearchScope(SearchControls.SUBTREE_SCOPE);
        	
            NamingEnumeration<SearchResult> answer = ctx.search(dc, "sAMAccountName=" + userName, searchControlsAux);
            if (answer.hasMore()) {
                Attributes attrs = answer.next().getAttributes();
                map.put("displayName", 			 null != attrs.get("displayName")			 ? "" + attrs.get("displayName").get()		 : "");
                map.put("givenName",             null != attrs.get("givenName")              ? "" + attrs.get("givenName").get()         : "");
                map.put("objectCategory",        null != attrs.get("objectCategory")         ? "" + attrs.get("objectCategory").get()    : "");
                map.put("cn",                    null != attrs.get("cn")                     ? "" + attrs.get("cn").get()                : "");
                map.put("sn",                    null != attrs.get("sn")                     ? "" + attrs.get("sn").get()                : "");
                map.put("userPrincipalName",     null != attrs.get("userPrincipalName")      ? "" + attrs.get("userPrincipalName").get() : "");
                map.put("distinguishedName",     null != attrs.get("distinguishedName")      ? "" + attrs.get("distinguishedName").get() : "");
                map.put("sAMAccountName",        null != attrs.get("sAMAccountName")         ? "" + attrs.get("sAMAccountName").get()    : "");
                map.put("memberOf",              null != attrs.get("memberOf")               ? "" + attrs.get("memberOf").get()          : "");
                map.put("name",                  null != attrs.get("name")                   ? "" + attrs.get("name").get()              : "");
                map.put("telephoneNumber",       null != attrs.get("telephoneNumber")        ? "" + attrs.get("telephoneNumber").get()   : "");
            } else {
            	throw new Exception("Cuenta inexistente.");
            }
        } catch (Exception ex) {
        	throw new Exception(ex.getMessage());
        }
		return map;
	}
	
	public static List<Map<String, String>> userInfoAll(String dc, LdapContext ctx, SearchControls searchControls) throws Exception {
		List<Map<String, String>> listUser = new LinkedList<>();
		try {
        	SearchControls searchControlsAux = new SearchControls();
        	searchControlsAux.setSearchScope(SearchControls.SUBTREE_SCOPE);
        	
            NamingEnumeration<SearchResult> answer = ctx.search(dc, "(userPrincipalName=*)", searchControlsAux);
            while (answer.hasMoreElements()) {
				SearchResult result = (SearchResult) answer.next();
				Attributes attrs = result.getAttributes();
				Map<String, String> map = new TreeMap<>();
				map.put("userPrincipalName",     null != attrs.get("userPrincipalName")      ? "" + attrs.get("userPrincipalName").get() : "");
                map.put("sAMAccountName",        null != attrs.get("sAMAccountName")         ? "" + attrs.get("sAMAccountName").get()    : "");
                map.put("name",                  null != attrs.get("name")                   ? "" + attrs.get("name").get()              : "");
                listUser.add(map);
            }
        } catch (Exception ex) {
        	throw new Exception(ex.getMessage());
        }
		return listUser;
	}
	
	public static String validateUserCredentials(String initial, String aut, String cn, String cred, String url, 
			String sama, String account, String keyAccount, String dc) {
		String resp = "Ok";

        // service user
        String serviceUserDN = cn;
        String serviceUserPassword = cred;

        // user to authenticate
        String identifyingAttribute = sama;
        String identifier = account;
        String password = keyAccount;
        String base = dc;

        // first create the service context
        DirContext serviceCtx = null;
        try {
            // use the service user to authenticate
            Properties serviceEnv = new Properties();
            serviceEnv.put(Context.INITIAL_CONTEXT_FACTORY, initial);
            serviceEnv.put(Context.PROVIDER_URL, url);
            serviceEnv.put(Context.SECURITY_AUTHENTICATION, aut);
            serviceEnv.put(Context.SECURITY_PRINCIPAL, serviceUserDN);
            serviceEnv.put(Context.SECURITY_CREDENTIALS, serviceUserPassword);
            serviceCtx = new InitialDirContext(serviceEnv);

            // we don't need all attributes, just let it get the identifying one
            String[] attributeFilter = { identifyingAttribute };
            SearchControls sc = new SearchControls();
            sc.setReturningAttributes(attributeFilter);
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

            // use a search filter to find only the user we want to authenticate
            String searchFilter = "(" + identifyingAttribute + "=" + identifier + ")";
            NamingEnumeration<SearchResult> results = serviceCtx.search(base, searchFilter, sc);

            if (results.hasMore()) {
                // get the users DN (distinguishedName) from the result
                SearchResult result = results.next();
                String distinguishedName = result.getNameInNamespace();

                // attempt another authentication, now with the user
                Properties authEnv = new Properties();
                authEnv.put(Context.INITIAL_CONTEXT_FACTORY, initial);
                authEnv.put(Context.PROVIDER_URL, url);
                authEnv.put(Context.SECURITY_PRINCIPAL, distinguishedName);
                authEnv.put(Context.SECURITY_CREDENTIALS, password);
                new InitialDirContext(authEnv);

                System.out.println("Authentication successful");
                return "Ok";
            }
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            resp = e.getMessage();
        } finally {
            if (serviceCtx != null) {
                try {
                    serviceCtx.close();
                } catch (NamingException e) {
                	LOGGER.info(e.getMessage());
                    resp = e.getMessage();
                }
            }
        }
        LOGGER.info("Authentication failed {}", account + " - " + resp);
        return "Credenciales no validas ** "; // + resp;
    }
	
	/**
	 * @param Data
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String Data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(Constants.C_AES);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encVal);
		return encryptedValue;
	}

	/**
	 * @param encryptedData
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(Constants.C_AES);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(Constants.C_KEY_VALUE, Constants.C_AES);
		return key;
	}
}