package AUT.utilities;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PDFReader {

    public static boolean isTextPresentOnPage(String filePath, int pageNumber, String textToFind) throws IOException {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            if (pageNumber < 1 || pageNumber > document.getNumberOfPages()) {
                throw new IllegalArgumentException("Page number out of range.");
            }
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(pageNumber);
            stripper.setEndPage(pageNumber);

            String pageText = stripper.getText(document);
            return pageText.contains(textToFind);
        }
    }

    public static Map<Integer, List<String>> searchTermsInPages(String filePath, List<String> terms, int startPage) throws IOException {
        Map<Integer, List<String>> foundTermsByPage = new LinkedHashMap<>();

        try (PDDocument document = PDDocument.load(new File(filePath))) {
            int totalPages = document.getNumberOfPages();
//            if (startPage < 1 || endPage > totalPages) {
//                throw new IllegalArgumentException("Page range is out of bounds. Total pages: " + totalPages);
//            }

            PDFTextStripper stripper = new PDFTextStripper();

            for (int page = startPage; page <= totalPages; page++) {
                stripper.setStartPage(page);
                stripper.setEndPage(page);
                String pageText = stripper.getText(document);

                String normalizedText = pageText.replaceAll("\\s+", " ").trim();

                List<String> foundTerms = new ArrayList<>();
                for (String term : terms) {
                    if (normalizedText.toLowerCase().contains(term.toLowerCase())) {
                        foundTerms.add(term);
                    }
                }


                if (!foundTerms.isEmpty()) {
                    foundTermsByPage.put(page, foundTerms);
                }
            }
        }

        return foundTermsByPage;
    }

}
