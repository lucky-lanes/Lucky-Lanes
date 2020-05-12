package main.formObjects;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.VerticalAlignment;
import be.quodlibet.boxable.line.LineStyle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.*;
import be.quodlibet.boxable.*;
//import main.java.Report;

/**
 * Class for generating PDF reports on bowlers
 */
public class Document_Creator {
    /**
     * Method that generates a pdf report of an athlete based off of the string passed into the method.
     *
     * @param string String containing information about the bowler. Information is delimited by "|"
     * @param multipleDocs Boolean value for if multiple docs are being generated. If false, it will open the pdf after it is made.
     *                     If true, it will not open the pdf after it is made
     * @throws IOException
     */
    public void createPDF(String string, boolean multipleDocs) throws IOException {
        String[] split = string.split("\\|");
       
        //Creating PDF document object 
        PDDocument document = new PDDocument();
                
        //Adding page
        PDPage page1 = new PDPage();
        document.addPage(page1);

        //Adding text
        PDPageContentStream contentStream = new PDPageContentStream(document, page1);
        contentStream.beginText();
        
        //Setting up table
        float tableWidth = page1.getMediaBox().getWidth() - (2 * 50);
        float tableHeight2 = page1.getMediaBox().getHeight() - (2 * 50);
        BaseTable table = new BaseTable(750, tableHeight2, 70, tableWidth, 50, document, page1, true, true);

        //HEADER
        // Row<PDPage> headerRow = table.createRow(50);
        Row<PDPage> row = table.createRow(50);
        Cell<PDPage> cell = row.createCell(100, "Lucky Lanes");
        
        //Cell<PDPage> cell = headerRow.createCell(100, "Lucky Lanes");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(24);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setTopBorderStyle(new LineStyle(Color.BLACK, 10));
        //table.addHeaderRow(headerRow);

        //Info from the Demographics tab
        // Row<PDPage> row = table.createRow(20);
        row = table.createRow(20);
        cell = row.createCell(100, "Bowler Information");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(15);
        row = table.createRow(20);
        cell = row.createCell(50, "Name");
        cell.setFontSize(12);
        cell = row.createCell(50, split[1]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Date");
        cell.setFontSize(12);
        cell = row.createCell(50, split[2]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Date of Birth");
        cell.setFontSize(12);
        cell = row.createCell(50, split[3]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Address");
        cell.setFontSize(12);
        cell = row.createCell(50, split[4]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "City");
        cell.setFontSize(12);
        cell = row.createCell(50, split[5]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "State");
        cell.setFontSize(12);
        cell = row.createCell(50, split[6]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Zip Code");
        cell.setFontSize(12);
        cell = row.createCell(50, split[7]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Phone");
        cell.setFontSize(12);
        cell = row.createCell(50, split[8]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "School");
        cell.setFontSize(12);
        cell = row.createCell(50, split[9]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Height");
        cell.setFontSize(12);
        cell = row.createCell(50, split[10]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Weight");
        cell.setFontSize(12);
        cell = row.createCell(50, split[11]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Age");
        cell.setFontSize(12);
        cell = row.createCell(50, split[12]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Sex");
        cell.setFontSize(12);
        cell = row.createCell(50, split[13]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Hand Dominance");
        cell.setFontSize(12);
        cell = row.createCell(50, split[14]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Leg Dominance");
        cell.setFontSize(12);
        cell = row.createCell(50, split[15]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Primary Sport");
        cell.setFontSize(12);
        cell = row.createCell(50, split[16]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Primary Position");
        cell.setFontSize(12);
        cell = row.createCell(50, split[17]);
        cell.setFontSize(12);

        //Info from the FMS Score Sheet tab
        row = table.createRow(20);
        cell = row.createCell(100, "Functional Movement Screen");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(15);
        row = table.createRow(20);
        cell = row.createCell(35, "Test");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(12);
        cell = row.createCell(5, " ");
        cell.setFontSize(12);
        cell = row.createCell(20, "Raw Score");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(12);
        cell = row.createCell(20, "Final Score");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(12);
        cell = row.createCell(20, "Comments");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(35, "Deep Squat: ");
        cell.setFontSize(12);
        cell = row.createCell(5, " ");
        cell.setFontSize(12);
        cell = row.createCell(20, split[19]);
        cell.setFontSize(12);
        cell = row.createCell(20, split[20]);
        cell.setFontSize(12);
        cell = row.createCell(20, split[21]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(35, "Hurdle Step: ");
        cell.setFontSize(12);
        cell = row.createCell(5, "L");
        cell.setFontSize(12);
        cell = row.createCell(20, split[22]);
        cell.setFontSize(12);
        cell = row.createCell(20, split[23]);
        cell.setFontSize(12);
        cell = row.createCell(20, split[24]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(35, " ");
        cell.setFontSize(12);
        cell = row.createCell(5, "R");
        cell.setFontSize(12);
        cell = row.createCell(20, split[25]);
        cell.setFontSize(12);
        cell = row.createCell(20, " ");
        cell = row.createCell(20, " ");
        row = table.createRow(20);
        cell = row.createCell(35, "Inline Lunge: ");
        cell.setFontSize(12);
        cell = row.createCell(5, "L");
        cell.setFontSize(12);
        cell = row.createCell(20, split[26]);
        cell.setFontSize(12);
        cell = row.createCell(20, split[27]);
        cell.setFontSize(12);
        cell = row.createCell(20, split[28]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(35, " ");
        cell.setFontSize(12);
        cell = row.createCell(5, "R");
        cell.setFontSize(12);
        cell = row.createCell(20, split[29]);
        cell.setFontSize(12);
        cell = row.createCell(20, " ");
        cell = row.createCell(20, " ");
        row = table.createRow(20);
        cell = row.createCell(35, "Shoulder Mobility: ");
        cell.setFontSize(12);
        cell = row.createCell(5, "L");
        cell.setFontSize(12);
        cell = row.createCell(20, split[30]);
        cell.setFontSize(12);
        cell = row.createCell(20, split[31]);
        cell.setFontSize(12);
        cell = row.createCell(20, split[32]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(35, " ");
        cell.setFontSize(12);
        cell = row.createCell(5, "R");
        cell.setFontSize(12);
        cell = row.createCell(20, split[33]);
        cell.setFontSize(12);
        cell = row.createCell(20, " ");
        cell = row.createCell(20, " ");
        row = table.createRow(20);
        cell = row.createCell(35, "Shoulder Clearing Test: ");
        cell.setFontSize(12);
        cell = row.createCell(5, "L");
        cell.setFontSize(12);
        cell = row.createCell(20, split[34]);
        cell.setFontSize(12);
        cell = row.createCell(20, " ");
        cell.setFontSize(12);
        cell = row.createCell(20, split[35]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(35, " ");
        cell.setFontSize(12);
        cell = row.createCell(5, "R");
        cell.setFontSize(12);
        cell = row.createCell(20, split[36]);
        cell.setFontSize(12);
        cell = row.createCell(20, " ");
        cell = row.createCell(20, " ");
        row = table.createRow(20);
        cell = row.createCell(35, "Active Straight-leg Raise: ");
        cell.setFontSize(12);
        cell = row.createCell(5, "L");
        cell.setFontSize(12);
        cell = row.createCell(20, split[37]);
        cell.setFontSize(12);
        cell = row.createCell(20, split[38]);
        cell.setFontSize(12);
        cell = row.createCell(20, split[39]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(35, " ");
        cell.setFontSize(12);
        cell = row.createCell(5, "R");
        cell.setFontSize(12);
        cell = row.createCell(20, split[40]);
        cell.setFontSize(12);
        cell = row.createCell(20, " ");
        cell = row.createCell(20, " ");
        row = table.createRow(20);
        cell = row.createCell(35, "Trunk Stability Pushup: ");
        cell.setFontSize(12);
        cell = row.createCell(5, " ");
        cell.setFontSize(12);
        cell = row.createCell(20, split[41]);
        cell.setFontSize(12);
        cell = row.createCell(20, split[42]);
        cell.setFontSize(12);
        cell = row.createCell(20, split[43]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(35, "Extension Clearing Test: ");
        cell.setFontSize(12);
        cell = row.createCell(5, " ");
        cell.setFontSize(12);
        cell = row.createCell(20, split[44]);
        cell.setFontSize(12);
        cell = row.createCell(20, " ");
        cell.setFontSize(12);
        cell = row.createCell(20, split[45]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(35, "Rotary Stability: ");
        cell.setFontSize(12);
        cell = row.createCell(5, "L");
        cell.setFontSize(12);
        cell = row.createCell(20, split[46]);
        cell.setFontSize(12);
        cell = row.createCell(20, split[47]);
        cell.setFontSize(12);
        cell = row.createCell(20, split[48]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(35, " ");
        cell.setFontSize(12);
        cell = row.createCell(5, "R");
        cell.setFontSize(12);
        cell = row.createCell(20, split[49]);
        cell.setFontSize(12);
        cell = row.createCell(20, " ");
        cell = row.createCell(20, " ");
        row = table.createRow(20);
        cell = row.createCell(35, "Flexion Clearing Test: ");
        cell.setFontSize(12);
        cell = row.createCell(5, " ");
        cell.setFontSize(12);
        cell = row.createCell(20, split[50]);
        cell.setFontSize(12);
        cell = row.createCell(20, " ");
        cell = row.createCell(20, split[51]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(35, "Total: ");
        cell.setFontSize(12);
        cell = row.createCell(5, " ");
        cell.setFontSize(12);
        cell = row.createCell(60, split[52]);
        cell.setFontSize(12);

        //Info from the Y-Balance Test tab
        row = table.createRow(20);
        cell = row.createCell(100, "Y-Balance");
        cell.setFontSize(15);
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        row = table.createRow(20);
        cell = row.createCell(70, "Left Posteromedial 1 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[54]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Left Posteromedial 2 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[55]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Left Posteromedial 3 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[56]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Posteromedial 1 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[57]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Posteromedial 2 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[58]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Posteromedial 3 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[59]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Left Posterolateral 1 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[60]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Left Posterolateral 2 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[61]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Left Posterolateral 3 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[62]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Posterolateral 1 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[63]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Posterolateral 2 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[64]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Posterolateral 3 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[65]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Left Anterior 1 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[66]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Left Anterior 2 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[67]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Left Anterior 3 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[68]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Anterior 1 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[69]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Anterior 2 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[70]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Anterior 3 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[71]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Limb Length ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[72]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Anterior Mean ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[73]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Left Anterior Mean ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[74]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Left Posteromedial Mean");
        cell.setFontSize(12);
        cell = row.createCell(30, split[75]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Posteromedial Mean ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[76]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Left Posterolateral Mean ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[77]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Posterolateral Mean ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[78]);
        cell.setFontSize(12);

        //Info from the Fitness Testing Data Sheet tab
        row = table.createRow(20);
        cell = row.createCell(100, "Fitness Test");
        cell.setFontSize(15);
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        row = table.createRow(20);
        cell = row.createCell(100, "Vitals");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Age ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[80]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Resting Heart Rate ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[81]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Resting Heart Rate 1 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[82]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Resting Heart Rate 2 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[83]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Height ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[84]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Body Weight ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[85]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Body Mass Index (BMI) ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[86]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Peak Flow ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[87]);
        cell.setFontSize(12);
        row = table.createRow(20);
        
        //Information from the Fitness Testing Data Sheet tab, under the Anthropometrics section
        cell = row.createCell(100, "Anthropometrics");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Anterior 1 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[88]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Anterior 2 ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[89]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Anterior Average ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[90]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Waist Circumference ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[91]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Hip Circumference ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[92]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Mid Thigh Circumference ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[93]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Flexed Arm Circumference ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[94]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Hamstring CSA ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[95]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Quadriceps CSA ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[96]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Total Thigh CSA ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[97]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Bicep Circumference ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[98]);
        cell.setFontSize(12);
        row = table.createRow(20);
        
        //Information from the Fitness Testing Data Sheet tab, under the Skinfold section
        cell = row.createCell(100, "Skinfold ");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Tricep Skinfold ");
        cell.setFontSize(12);
        cell = row.createCell(30, split[99]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Subscapular Skinfold");
        cell.setFontSize(12);
        cell = row.createCell(30, split[100]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Abdominal Skinfold");
        cell.setFontSize(12);
        cell = row.createCell(30, split[101]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Suprailiac Skinfold");
        cell.setFontSize(12);
        cell = row.createCell(30, split[102]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Thigh Skinfold");
        cell.setFontSize(12);
        cell = row.createCell(30, split[103]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Pectoral Skinfold");
        cell.setFontSize(12);
        cell = row.createCell(30, split[104]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Wallsit");
        cell.setFontSize(12);
        cell = row.createCell(30, split[105]);
        cell.setFontSize(12);
        row = table.createRow(20);
        
        //Information from the Fitness Testing Data Sheet tab, under the Sit & Reach section
        cell = row.createCell(100, "Sit & Reach");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Start Distance");
        cell.setFontSize(12);
        cell = row.createCell(30, split[106]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Ending Distance 1");
        cell.setFontSize(12);
        cell = row.createCell(30, split[107]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Ending Distance 2");
        cell.setFontSize(12);
        cell = row.createCell(30, split[108]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Ending Distance 3");
        cell.setFontSize(12);
        cell = row.createCell(30, split[109]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Final Distance");
        cell.setFontSize(12);
        cell = row.createCell(30, split[110]);
        cell.setFontSize(12);
        row = table.createRow(20);
        
        //Information from the Fitness Testing Data Sheet tab, under the Muscle Strength & Endurance & Power
        cell = row.createCell(100, "Muscle & Strength");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Hand Grip 1");
        cell.setFontSize(12);
        cell = row.createCell(30, split[111]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Hand Grip 2");
        cell.setFontSize(12);
        cell = row.createCell(30, split[112]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Hand Grip 3");
        cell.setFontSize(12);
        cell = row.createCell(30, split[113]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Left Hand Grip 1");
        cell.setFontSize(12);
        cell = row.createCell(30, split[114]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Left Hand Grip 2");
        cell.setFontSize(12);
        cell = row.createCell(30, split[115]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Left Hand Grip 3");
        cell.setFontSize(12);
        cell = row.createCell(30, split[116]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Prone Time");
        cell.setFontSize(12);
        cell = row.createCell(30, split[117]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Knee Extension Force 1");
        cell.setFontSize(12);
        cell = row.createCell(30, split[118]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Right Knee Extension Force 2");
        cell.setFontSize(12);
        cell = row.createCell(30, split[119]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Left Knee Extension Force 1");
        cell.setFontSize(12);
        cell = row.createCell(30, split[120]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Left Knee Extension Force 2");
        cell.setFontSize(12);
        cell = row.createCell(30, split[121]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "J H 1");
        cell.setFontSize(12);
        cell = row.createCell(30, split[122]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "J H 2");
        cell.setFontSize(12);
        cell = row.createCell(30, split[123]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Medical Pass 1");
        cell.setFontSize(12);
        cell = row.createCell(30, split[124]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Medical Pass 2");
        cell.setFontSize(12);
        cell = row.createCell(30, split[125]);
        cell.setFontSize(12);
        row = table.createRow(20);
        //Information from the Fitness Testing Data Sheet tab, under the Estimated Aerobic Capacity section
        cell = row.createCell(100, "Aerobic Capacity");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "VO2 Max");
        cell.setFontSize(12);
        cell = row.createCell(30, split[126]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Post Heart Rate");
        cell.setFontSize(12);
        cell = row.createCell(30, split[127]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Post VO2 Max");
        cell.setFontSize(12);
        cell = row.createCell(30, split[128]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Age Rating");
        cell.setFontSize(12);
        cell = row.createCell(30, split[129]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Rockwell Heart Rate");
        cell.setFontSize(12);
        cell = row.createCell(30, split[130]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Walk Time");
        cell.setFontSize(12);
        cell = row.createCell(30, split[131]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Rockwell VO2 Max");
        cell.setFontSize(12);
        cell = row.createCell(30, split[132]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Walk Distance");
        cell.setFontSize(12);
        cell = row.createCell(30, split[133]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Walk VO2 Max");
        cell.setFontSize(12);
        cell = row.createCell(30, split[134]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "ACSM Percentile");
        cell.setFontSize(12);
        cell = row.createCell(30, split[135]);
        cell.setFontSize(12);
        row = table.createRow(20);

        //Information from the Medical Survey tab
        cell = row.createCell(100, "Par-Q and You");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Question 1 Answer");
        cell.setFontSize(12);
        cell = row.createCell(30, split[137]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Question 2 Answer");
        cell.setFontSize(12);
        cell = row.createCell(30, split[138]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Question 3 Answer");
        cell.setFontSize(12);
        cell = row.createCell(30, split[139]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Question 4 Answer");
        cell.setFontSize(12);
        cell = row.createCell(30, split[140]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Question 5 Answer");
        cell.setFontSize(12);
        cell = row.createCell(30, split[141]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Question 6 Answer");
        cell.setFontSize(12);
        cell = row.createCell(30, split[142]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(70, "Question 7 Answer");
        cell.setFontSize(12);
        cell = row.createCell(30, split[143]);
        cell.setFontSize(12);
        
        //Info from the IBSSN tab
        row = table.createRow(20);
        cell = row.createCell(100, "IBSSN Values and Scores");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(15);
        row = table.createRow(20);
        cell = row.createCell(50, "Test");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(12);
        cell = row.createCell(25, "Raw Score");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(12);
        cell = row.createCell(25, "Final IBSSN Score");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Target Accuracy: ");
        cell.setFontSize(12);
        cell = row.createCell(25, split[145]);
        cell.setFontSize(12);
        cell = row.createCell(25, split[146]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Pocket Percentage: ");
        cell.setFontSize(12);
        cell = row.createCell(25, split[147] + "%");
        cell.setFontSize(12);
        cell = row.createCell(25, split[148]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Single Pin Spare Percentage: ");
        cell.setFontSize(12);
        cell = row.createCell(25, split[149] + "%");
        cell.setFontSize(12);
        cell = row.createCell(25, split[150]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Multiple Pin Spare Percentage: ");
        cell.setFontSize(12);
        cell = row.createCell(25, split[151] + "%");
        cell.setFontSize(12);
        cell = row.createCell(25, split[152]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "How Quickly to Pocket: ");
        cell.setFontSize(12);
        cell = row.createCell(25, split[153] + " shots");
        cell.setFontSize(12);
        cell = row.createCell(25, split[154]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "How Quickly to Pocket - Adjusted: ");
        cell.setFontSize(12);
        cell = row.createCell(25, split[155] + " shots");
        cell.setFontSize(12);
        cell = row.createCell(25, split[156]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Ball Speed Consistency: ");
        cell.setFontSize(12);
        cell = row.createCell(25, split[157]);
        cell.setFontSize(12);
        cell = row.createCell(25, split[158]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Entry Angle: ");
        cell.setFontSize(12);
        cell = row.createCell(25, split[159]);
        cell.setFontSize(12);
        cell = row.createCell(25, split[160]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Ball Speed at Release: ");
        cell.setFontSize(12);
        cell = row.createCell(25, split[161]);
        cell.setFontSize(12);
        cell = row.createCell(25, split[162]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(50, "Rev Rate: ");
        cell.setFontSize(12);
        cell = row.createCell(25, split[163]);
        cell.setFontSize(12);
        cell = row.createCell(25, split[164]);
        cell.setFontSize(12);
        row = table.createRow(20);
        cell = row.createCell(80, "Final IBSSN Score");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setFontSize(12);
        cell = row.createCell(20, split[165]);
        cell.setFontSize(12);
     
        table.draw();

        contentStream.endText();
        contentStream.close();
        
        //Saving the document
        String pdfName = System.getProperty("user.dir") + "/Output Files/" + split[1] + "_Bowler.pdf";
        document.save(new File(pdfName));
        
        if(!multipleDocs) {
        	handle(pdfName);
        }

        System.out.println("PDF created");

        //Closing the document  
        document.close();
    }
    
    /**
     * Opens up file. Takes file path to the form.
     *
     * @param fileName File name of the file to be opened
     */
    public void handle(String fileName) {
        Desktop desktop = Desktop.getDesktop();
        File file = new File(fileName);
        try {
            desktop.open(file);
            System.out.println(fileName);
        } catch (IOException ex) {
            System.out.println("failed");
        }
    }
}
