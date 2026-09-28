package com.cbe.service;

import com.cbe.dto.MatokeoDTO;
import com.cbe.dto.ProfileDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PdfService {

    public ByteArrayInputStream generateMatokeoPdf(ProfileDTO profile, List<MatokeoDTO> matokeo) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
            Paragraph title = new Paragraph("CHUO CHA CBE - MATOKEO YA MWANAFUNZI", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Date
            Font dateFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
            Paragraph date = new Paragraph(
                "Tarehe: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                dateFont
            );
            date.setAlignment(Element.ALIGN_RIGHT);
            document.add(date);
            document.add(Chunk.NEWLINE);

            // Student info
            Font infoFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            document.add(new Paragraph("Jina: " + profile.getJinaKamili(), infoFont));
            document.add(new Paragraph("Namba: " + profile.getNambaYaMwanafunzi(), infoFont));
            document.add(new Paragraph("Course: " + profile.getCourse(), infoFont));
            document.add(new Paragraph("Mwaka: " + profile.getMwakaWaKujiunga(), infoFont));
            document.add(Chunk.NEWLINE);

            // Table
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2, 4, 1, 1, 1, 2});

            String[] headers = {"Code", "Kozi", "Credits", "Alama", "Grade", "Semester"};
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.WHITE);

            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
                cell.setBackgroundColor(new BaseColor(102, 126, 234));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(8);
                table.addCell(cell);
            }

            Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
            for (MatokeoDTO m : matokeo) {
                table.addCell(new PdfPCell(new Phrase(m.getCode(), cellFont)));
                table.addCell(new PdfPCell(new Phrase(m.getJinaLaKozi(), cellFont)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(m.getCredits()), cellFont)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(m.getAlama()), cellFont)));
                table.addCell(new PdfPCell(new Phrase(m.getGrade(), cellFont)));
                table.addCell(new PdfPCell(new Phrase(m.getSemester(), cellFont)));
            }

            document.add(table);
            document.add(Chunk.NEWLINE);

            // Footer
            Paragraph footer = new Paragraph(
                "\n\n© 2026 Chuo cha CBE - Mfumo wa Matokeo",
                FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 9, BaseColor.GRAY)
            );
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            document.close();

        } catch (DocumentException e) {
            throw new RuntimeException("Imeshindwa kutengeneza PDF: " + e.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}