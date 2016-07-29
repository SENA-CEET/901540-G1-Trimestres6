/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploenviomail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author hernando
 */
public class EjemploEnvioMail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "hulkike33@gmail.com");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.password", "kike089810");
            //props.put("mail.transport.protocol", "smtp");
            props.put("mail.debug", "true");

            String userName = "hulkike33@gmail.com";
            String password = "kike089810";

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);
//            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(userName, password);
//                }
//
//            });

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hulkike33@gmail.com"));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress("hulkike33@gmail.com"));
            message.setSubject("Hola");
            message.setText(
                    "Mensajito con Java Mail" + "de los buenos." + "poque si");

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect(userName, password);
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
