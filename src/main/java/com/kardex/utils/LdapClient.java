package com.kardex.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import com.kardex.model.User;

public class LdapClient {

    public static void mainS(String[] args) {
         
    	System.out.println("run: " + new Date());
        LdapContext ldapContext = getLdapContext();
        SearchControls searchControls = getSearchControls();
        getUserInfo("aramirez", ldapContext, searchControls);
        System.out.println("done: " + new Date());
        
    	performAuthentication();
    }

    private static LdapContext getLdapContext() {
        LdapContext ctx = null;
        try {
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.SECURITY_AUTHENTICATION, "Simple");
            env.put(Context.SECURITY_PRINCIPAL, "Administrador de Desarrollo");//input user & password for access to ldap
            env.put(Context.SECURITY_CREDENTIALS, "Eslmqcerd2019k");
            env.put(Context.PROVIDER_URL, "ldap://buromc.mx:389");
            env.put(Context.REFERRAL, "follow");
            ctx = new InitialLdapContext(env, null);
            System.out.println("LDAP Connection: COMPLETE");
        } catch (NamingException nex) {
            System.out.println("LDAP Connection: FAILED");
            nex.printStackTrace();
        }
        return ctx;
    }

    private static User getUserInfo(String userName, LdapContext ctx, SearchControls searchControls) {
        System.out.println("*** " + userName + " ***");
        User user = null;
        try {
        	SearchControls searchControlsAux = new SearchControls();
        	searchControlsAux.setSearchScope(SearchControls.SUBTREE_SCOPE);
        	
            NamingEnumeration<SearchResult> answer = ctx.search("DC=buromc,DC=mx", "sAMAccountName=" + userName, searchControlsAux);
            if (answer.hasMore()) {
                Attributes attrs = answer.next().getAttributes();
                NamingEnumeration<? extends Attribute> i = attrs.getAll();
                while (i.hasMore())
                {
                    Attribute attribute = i.next();
                    System.out.println(attribute);
                }
                
                System.out.println(attrs.get("distinguishedName"));
                System.out.println(attrs.get("givenname"));
                System.out.println(attrs.get("sn"));
                System.out.println(attrs.get("userPrincipalName"));
                System.out.println(attrs.get("telephoneNumber"));
                // byte[] photo = (byte[])attrs.get("thumbnailPhoto").get();
                // savePhoto(userName, photo);
            } else {
                System.out.println("user not found.");
            }
            
            
            // TODOS los usuarios
            /*
            NamingEnumeration<SearchResult> answer = ctx.search("DC=buromc,DC=mx", "(userPrincipalName=*)", searchControlsAux);
            while (answer.hasMoreElements())
			{
				SearchResult result = (SearchResult) answer.next();
				Attributes attribs = result.getAttributes();
				if(attribs.get("userPrincipalName").get().toString().contains("buromc.mx")) {
					System.out.println(attribs.get("userPrincipalName"));
				}
				
			}
			*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    private static SearchControls getSearchControls() {
        SearchControls cons = new SearchControls();
        cons.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String[] attrIDs = {"distinguishedName", "givenname", "sn", "userPrincipalName", "telephoneNumber", "thumbnailPhoto"};
        cons.setReturningAttributes(attrIDs);
        return cons;
    }

    private static void savePhoto(String userName, byte[] photo) throws IOException {
        FileOutputStream os = new FileOutputStream("d:/" + userName + ".jpg");
        os.write(photo);
        os.flush();
        os.close();
    }
    
    public static boolean performAuthentication() {

        // service user
        String serviceUserDN = "Administrador de Desarrollo";
        String serviceUserPassword = "Eslmqcerd2019k";

        // user to authenticate
        String identifyingAttribute = "sAMAccountName";
        String identifier = "aramirez";
        String password = "Temporal876";
        String base = "DC=buromc,DC=mx";

        // LDAP connection info
        String ldap = "buromc.mx";
        int port = 389;
        String ldapUrl = "ldap://" + ldap + ":" + port;

        // first create the service context
        DirContext serviceCtx = null;
        try {
            // use the service user to authenticate
            Properties serviceEnv = new Properties();
            serviceEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            serviceEnv.put(Context.PROVIDER_URL, ldapUrl);
            serviceEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
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
                authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
                authEnv.put(Context.PROVIDER_URL, ldapUrl);
                authEnv.put(Context.SECURITY_PRINCIPAL, distinguishedName);
                authEnv.put(Context.SECURITY_CREDENTIALS, password);
                new InitialDirContext(authEnv);

                System.out.println("Authentication successful");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serviceCtx != null) {
                try {
                    serviceCtx.close();
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            }
        }
        System.err.println("Authentication failed");
        return false;
    }
}