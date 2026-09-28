package com.cbe.service;

import com.cbe.dto.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class CsvService {

    public ByteArrayInputStream matokeoToCsv(List<MatokeoDTO> data) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out, true, StandardCharsets.UTF_8);

        writer.println("Jina,Code,Kozi,Credits,Alama,Grade,Mwaka,Semester");
        for (MatokeoDTO m : data) {
            writer.println(String.format("%s,%s,%s,%d,%d,%s,%d,%s",
                escape(m.getJinaKamili()),
                escape(m.getCode()),
                escape(m.getJinaLaKozi()),
                m.getCredits() != null ? m.getCredits() : 0,
                m.getAlama() != null ? m.getAlama() : 0,
                escape(m.getGrade()),
                m.getMwaka() != null ? m.getMwaka() : 0,
                escape(m.getSemester())
            ));
        }
        writer.flush();
        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream malipoToCsv(List<MalipoDTO> data) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out, true, StandardCharsets.UTF_8);

        writer.println("Jina,Namba,Aina,Kiasi,Tarehe,Risiti,Hali");
        for (MalipoDTO m : data) {
            writer.println(String.format("%s,%s,%s,%s,%s,%s,%s",
                escape(m.getJinaKamili()),
                escape(m.getNambaYaMwanafunzi()),
                escape(m.getAinaYaMalipo()),
                m.getKiasi(),
                m.getTareheYaMalipo(),
                escape(m.getRisiti()),
                m.getHali()
            ));
        }
        writer.flush();
        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream wanafunziToCsv(List<ProfileDTO> data) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out, true, StandardCharsets.UTF_8);

        writer.println("Jina,Namba,Username,Jinsia,Course,Mwaka,Role");
        for (ProfileDTO w : data) {
            writer.println(String.format("%s,%s,%s,%s,%s,%s,%s",
                escape(w.getJinaKamili()),
                escape(w.getNambaYaMwanafunzi()),
                escape(w.getUsername()),
                escape(w.getJinsia()),
                escape(w.getCourse()),
                w.getMwakaWaKujiunga() != null ? w.getMwakaWaKujiunga() : 0,
                escape(w.getRole())
            ));
        }
        writer.flush();
        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream mahudhurioToCsv(List<MahudhurioDTO> data) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out, true, StandardCharsets.UTF_8);

        writer.println("Jina,Namba,Code,Kozi,Tarehe,Hali");
        for (MahudhurioDTO m : data) {
            writer.println(String.format("%s,%s,%s,%s,%s,%s",
                escape(m.getJinaKamili()),
                escape(m.getNambaYaMwanafunzi()),
                escape(m.getCode()),
                escape(m.getJinaLaKozi()),
                m.getTarehe(),
                m.getHali()
            ));
        }
        writer.flush();
        return new ByteArrayInputStream(out.toByteArray());
    }

    private String escape(String s) {
        if (s == null) return "";
        if (s.contains(",") || s.contains("\"") || s.contains("\n")) {
            return "\"" + s.replace("\"", "\"\"") + "\"";
        }
        return s;
    }
}