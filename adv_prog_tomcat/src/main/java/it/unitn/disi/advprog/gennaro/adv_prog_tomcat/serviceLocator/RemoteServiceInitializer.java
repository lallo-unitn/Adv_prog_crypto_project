package it.unitn.disi.advprog.gennaro.adv_prog_tomcat.serviceLocator;

import it.unitn.disi.advprog.gennaro.adv_prog_tomcat.auxiliary.JndiName;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.managers.AdvisorChoiceManagerFacade;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.managers.StudentManagerFacade;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.security.Provider;
import java.util.HashMap;
import java.util.Properties;

public class RemoteServiceInitializer {
    private static RemoteServiceInitializer instance;
    private Context ctx;
    private StudentManagerFacade studentManagerFacade;
    private AdvisorChoiceManagerFacade advisorChoiceManagerFacade;

    private static HashMap<String, Object> cache = new HashMap<>();

    private RemoteServiceInitializer() {
        this.ctx = createInitialContext();
    }

    public static RemoteServiceInitializer getInstance() {
        if (instance == null) {
            instance = new RemoteServiceInitializer();
        }
        return instance;
    }

    private Context createInitialContext() {
        try {
            Properties jndiProperties = new Properties();
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
                    "org.jboss.naming.remote.client.InitialContextFactory");
            jndiProperties.put(Context.URL_PKG_PREFIXES,
                    "org.jboss.ejb.client.naming");
            jndiProperties.put(Context.PROVIDER_URL,
                    "http-remoting://localhost:8080");
            jndiProperties.put("jboss.naming.client.ejb.context", true);
            return new InitialContext(jndiProperties);
        } catch (NamingException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public synchronized Object getService(JndiName jndiName) {
        String jndiRaw = jndiName.getJndiName();
        if(!cache.containsKey(jndiRaw)) {
            try {
                Provider.Service service  = (Provider.Service) ctx.lookup(jndiRaw);
                cache.put(jndiRaw, service);
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return cache.get(jndiRaw);
    }
}
