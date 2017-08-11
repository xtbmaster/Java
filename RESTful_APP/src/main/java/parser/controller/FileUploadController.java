package parser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import parser.model.ParsedValues;
import parser.service.TextServiceIF;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main class which is responsible for processing client url requests
 */
@RestController
public class FileUploadController {

	@Autowired
	private FileProcessor fileProcessor;

	@Autowired
	private TextServiceIF textServiceIF;

	/**
	 * Displays all value - amount pairs which are already stored in a database
	 * @return http
	 * @throws SQLException
	 */
	@RequestMapping(value = "/words", method = RequestMethod.GET)
	public ResponseEntity<List<ParsedValues>> allFiles() throws SQLException {
		List<ParsedValues> files = textServiceIF.getAllFiles();
		if (files.isEmpty()) {
			return new ResponseEntity<List<ParsedValues>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ParsedValues>>(files, HttpStatus.OK);
	}

	/**
	 * Uploads files from the form, parses it and stores into a database
	 * @param files
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	@RequestMapping(value = "/count", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadMultipleFiles(@RequestParam("files") List<MultipartFile> files)
			throws IOException, SQLException {

		final List<File> convertedFiles = fileProcessor.convertMultipartFiles(files);
		final ExecutorService exec = Executors.newFixedThreadPool(3); //limiting processing threads

		exec.submit(new Runnable() {
			@Override
			public void run() {
				for (File target : convertedFiles) {
					try (BufferedReader br = new BufferedReader(new FileReader(target))) {
						while (br.readLine() != null) {
							Map<String, Integer> countedValues = fileProcessor.countValues(target);
							for (Map.Entry entry : countedValues.entrySet()) {
								String key = (String) entry.getKey();
								int value = (int) entry.getValue();
								ParsedValues parsedValues = new ParsedValues();

								if (textServiceIF.checkValue(key) != null) {
									parsedValues = textServiceIF.checkValue(key);
									int newCount = parsedValues.getCount() + value;
									parsedValues.setCount(newCount);
									textServiceIF.editParsedFile(parsedValues);

								} else {
									parsedValues.setValue(key);
									parsedValues.setCount(value);
									textServiceIF.addValue(parsedValues);
								}
							}
						}
						br.close();

					} catch (SQLException | IOException e) {
						e.printStackTrace();
					} finally {
						exec.shutdown();
					}
				}
			}
		});
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<Void>(httpHeaders, HttpStatus.OK);
	}
}
