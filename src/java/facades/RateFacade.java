/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.DailyRate;
import entity.Rate;
import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import openshift_deploy.DeploymentConfiguration;

/**
 *
 * @author butwhole
 */
public class RateFacade extends DefaultHandler {

    DailyRate currency;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

//    public static void inputRates() {
//        EntityManager em = emf.createEntityManager();
//        try {
//            
//            em.getTransaction().begin();
//            user.setPassword(PasswordStorage.createHash(user.getPassword()));
//            Role userRole = em.find(Role.class, "User");
//            user.AddRole(userRole);
//            em.persist(user);
//            em.getTransaction().commit();
//        } catch (PasswordStorage.CannotPerformOperationException ex) {
//            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            em.close();
//        }
//    }
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Document (Sax-event)");
        EntityManager em = emf.createEntityManager();
        try {

            em.getTransaction().begin();
            Query q = em.createQuery("DELETE FROM DailyRate c WHERE c.currentDate = :d");
            
            q.setParameter("d", new Date()).executeUpdate();
            em.getTransaction().commit();

            currency = new DailyRate();
            currency.setDate(new Date());
        } finally {
            em.close();
        }

        currency = new DailyRate();
        currency.setDate(new Date());

    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End Document (Sax-event)");
        EntityManager em = emf.createEntityManager();
        try {

            em.getTransaction().begin();
            em.persist(currency);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("currency")) {
            Rate r;
            double value;
            if (attributes.getValue(2).equals("-")) {
                value = 0;
            } else {
                value = Double.parseDouble(attributes.getValue(2));
            }

            r = new Rate(attributes.getValue(0), attributes.getValue(1), value);
            currency.addRate(r);

        }

    }

    public void getRates() {
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(new RateFacade());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));

        } catch (SAXException | IOException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        RateFacade rf = new RateFacade();
        rf.getRates();
    }
}
