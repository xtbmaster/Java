package parser.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * This is an auxillary class to process raw file data
 */
@Component
class FileProcessor {

    /**
     * Converts raw files from the upload form into a more convenient list
     * @param raw files
     * @return list of uploaded files
     * @throws IOException
     */
    List<File> convertMultipartFiles(List<MultipartFile> files) throws IOException {
        List<File> convertedFiles = new ArrayList<>();
        FileOutputStream fos = null;
        for (MultipartFile file : files) {
            File processedFile = new File(file.getOriginalFilename());
            try {
                processedFile.createNewFile();
                fos = new FileOutputStream(processedFile);
                fos.write(file.getBytes());
                convertedFiles.add(processedFile);
            }catch (IOException ignore){}
        }
        if (fos != null) {
            fos.close();
        }
        return convertedFiles;
    }

    /**
     * Counts values in a file line
     * @param file
     * @return map with value -> amount
     * @throws IOException
     */
    Map countValues(File file) throws IOException {
        Map<String, Integer> countedValues;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line;
            countedValues = new HashMap<>();
            while ((line = in.readLine()) != null) {
                List<String> list = Arrays.asList(line.split(", "));

                Set<String> valueSet = new HashSet(list);
                for (String target : valueSet) {
                    int count = Collections.frequency(list, target);
                    countedValues.put(target, count);
                }
            }
            in.close();
        }
        return countedValues;
    }

}
