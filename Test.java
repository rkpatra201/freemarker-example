package com.wavemaker.studio.core.data.model.masker;

import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws Exception {
        Configuration cfg = new Configuration();
        try {
            //Load template from source folder
//            File f = new File("template.ftl");
//            System.out.println(f.getAbsolutePath());
//            f.createNewFile();
            Template template = cfg.getTemplate("template.ftl");

            // Build the data-model
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("message", "Hello World!");

            //List parsing
            List<String> countries = new ArrayList<String>();
            countries.add("India");
            countries.add("United States");
            countries.add("Germany");
            countries.add("France");

            data.put("countries", countries);

            ColumnMasker masker = new LastNOffsetMasker();
            data.put("masker",masker);
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(masker));

            // Console output
            Writer out = new OutputStreamWriter(System.out);
            template.process(data, out);
            out.flush();

            // File output
            Writer file = new FileWriter(new File("\\usr\\local\\content\\result.txt"));
            template.process(data, file);
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
