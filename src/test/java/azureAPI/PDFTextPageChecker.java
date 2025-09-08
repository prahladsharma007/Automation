//package azureAPI;
//
//import AUT.constants.CommonConstants;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.text.PDFTextStripper;
//
//import java.io.File;
//import java.io.IOException;
//
//public class PDFTextPageChecker {
//
//    public static void main(String[] args) {
//        String filePath = CommonConstants.getTestdataFolderpath()+"\\sample.pdf";
//        int pageNumber = 2; // 1-based page number
//        String expectedText = "Milestone";
//
//        try {
//            boolean textFound = isTextPresentOnPage(filePath, pageNumber, expectedText);
//            if (textFound) {
//                System.out.println("✅ Text found on page " + pageNumber);
//            } else {
//                System.out.println("❌ Text NOT found on page " + pageNumber);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static boolean isTextPresentOnPage(String filePath, int pageNumber, String textToFind) throws IOException {
//        try (PDDocument document = PDDocument.load(new File(filePath))) {
//            if (pageNumber < 1 || pageNumber > document.getNumberOfPages()) {
//                throw new IllegalArgumentException("Page number out of range.");
//            }
//
//            PDFTextStripper stripper = new PDFTextStripper();
//            stripper.setStartPage(pageNumber);
//            stripper.setEndPage(pageNumber);
//
//            String pageText = stripper.getText(document);
//            return pageText.contains(textToFind);
//        }
//    }
//}
//
