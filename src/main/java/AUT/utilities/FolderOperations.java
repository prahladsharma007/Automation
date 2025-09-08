package AUT.utilities;

import java.io.File;

public class FolderOperations {

    public static void performFolderOperations(String folderPath){
        try{
            File folder = new File(folderPath);

            if (folder.exists()) {
                clearFolderContents(folder);
            } else {
                createFolder(folder);
            }
        }catch(Exception e){
            System.out.println("Failed while performing folder operation: "+e);
        }

    }

    private static void clearFolderContents(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    clearFolderContents(file);
                } else {
                    file.delete();
                }
            }
        }
    }

    private static void createFolder(File folder) throws Exception {
        if (!folder.mkdirs()) {
            throw new Exception("Unable to create the folder: " + folder.getAbsolutePath());
        }
    }
}
