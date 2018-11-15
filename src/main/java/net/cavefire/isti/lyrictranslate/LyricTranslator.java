package net.cavefire.isti.lyrictranslate;

import java.io.*;

public class LyricTranslator {

    private Main main;

    LyricTranslator(Main main){
        this.main = main;
    }

    public void translateFile() {
        String[] languages = {"fr","la", "zu", "ru", "zh", "la", "de"};
        String text = readInputFile();

        text = main.translator.translate(text, "auto", "en");

        String inputLang = main.translator.getStartingLanguage();

        String lastLang = inputLang;
        for (String language : languages) {
            text = main.translator.translate(text, lastLang, language);
            lastLang = language;
        }

        writeOutputFile(text);
    }

    private void writeOutputFile(String text) {
        try {
            File outputFile = new File("files/output.txt");
            outputFile.getParentFile().mkdirs();

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile.getAbsolutePath()));
            writer.write(text);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readInputFile() {

        File inputFile = new File("files/input.txt");
        inputFile.getParentFile().mkdirs();

        FileReader inputFileReader;
        try {
            inputFileReader = new FileReader(inputFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        BufferedReader bufferedReader = new BufferedReader(inputFileReader);
        String line;
        StringBuilder builder = new StringBuilder();

        try {
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line + "\n\r");
            }
        } catch (IOException e) {
            System.out.println("Input/Output Exception!");
        }
        return builder.toString();
    }
}
