/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toolsHouria;

import Entity.houria.Evenement;
import Entity.houria.Participation;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import static com.itextpdf.text.BaseColor.PINK;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import static com.itextpdf.text.pdf.BaseFont.CP1250;
import com.itextpdf.text.pdf.PdfWriter;
import static java.awt.Color.green;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Nader
 */
public class PDF {

    public void pdf(Evenement p) throws SQLException, FileNotFoundException, DocumentException, BadElementException, IOException {
        try {
            // System.out.println("Haouet------------------------------------->"+nom);

            // nextInt is normally exclusive of the top value,
            // so add 1 to make it inclusive
            int randomNum = ThreadLocalRandom.current().nextInt(1, 100 + 1);

            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\ASUS\\Desktop\\ticket/ticket" + randomNum + ".pdf"));
            document.open();
             Image img=Image.getInstance(p.getImage());
             img.setWidthPercentage(50);
           Paragraph adrr = new Paragraph(new Phrase("l adresse de l evenement : "+p.getLocal(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
             Paragraph par=new Paragraph(" Bienvenu MSR/MDMe chez Coccinelle  ", FontFactory.getFont(FontFactory.TIMES));
             par.setAlignment(Element.ALIGN_CENTER);
            document.add(par);
              document.add(new Paragraph("Veuillez IMPRIMER et présenter ce billet à l'entrée de l'événement\n" , FontFactory.getFont(FontFactory.TIMES)));

             document.add(img);

             document.add(adrr);
            document.add(new Paragraph("date debut de l evenement : "+p.getDate(), FontFactory.getFont(FontFactory.TIMES)));
            document.add(new Paragraph("date fin de l evenement : "+p.getDate_fin(), FontFactory.getFont(FontFactory.TIMES)));

          
            document.close();
            TrayNotification tray = new TrayNotification("Ticket", "Ticket de participation créée avec succés", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(3));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
