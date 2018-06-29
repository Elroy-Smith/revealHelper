package pl.coderslab;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import com.ibm.icu.impl.ICUService.Key;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;

public class Main {

	protected Shell shell;
	private Text textBuild;
	private Text textSource;
	private Browser browser_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	private String[] filter(String[] arr, String filterRegex) {
		List<String> result = new ArrayList<>();
		for(String fileName:arr) {
			if(fileName.matches(filterRegex)) {
				result.add(fileName);
			}
		}
		String[] resultArray = new String[result.size()];
		resultArray = result.toArray(resultArray);
		return resultArray;
	}
	
	private String readAllLinesFromFile(Path path) {
		
		StringBuilder sb = new StringBuilder();
		try {
			for(String line:Files.readAllLines(path)) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	private String[] readAllFiles(String[] fileList, String folder) {
		String[] result = new String[fileList.length];
		int index = 0;
		for(String fileName:fileList) {
			Path path = Paths.get(folder, fileName);
			result[index++] = readAllLinesFromFile(path);
		}
		
		return result;
	}
	
	
	private int countWord(String[] array, String word) {
		int count = 0;
		int fromIndex = 0;
		for(String text:array) {
			fromIndex = text.indexOf(word, fromIndex);
			while (fromIndex != -1) {
				count++;
				fromIndex = text.indexOf(word, fromIndex + 1);
			}
		}
		return count;
	}
	
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(978, 901);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());
		
		textBuild = new Text(shell, SWT.BORDER);
		textBuild.setText("C:\\Reveal Helper\\build\\index.html");
		textBuild.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.keyCode == 0xd) {
					System.out.println("wciœniêto enter");
					browser_1.setUrl(textBuild.getText());
				}
			}
		});
		FormData fd_txtCrevealHelperbuildindexhtml = new FormData();
		fd_txtCrevealHelperbuildindexhtml.left = new FormAttachment(0, 10);
		fd_txtCrevealHelperbuildindexhtml.right = new FormAttachment(0, 956);
		textBuild.setLayoutData(fd_txtCrevealHelperbuildindexhtml);
		
		Label lblPlikWynikowyZ = new Label(shell, SWT.NONE);
		fd_txtCrevealHelperbuildindexhtml.top = new FormAttachment(lblPlikWynikowyZ, 6);
		FormData fd_lblPlikWynikowyZ = new FormData();
		fd_lblPlikWynikowyZ.left = new FormAttachment(0, 10);
		fd_lblPlikWynikowyZ.top = new FormAttachment(0, 10);
		lblPlikWynikowyZ.setLayoutData(fd_lblPlikWynikowyZ);
		lblPlikWynikowyZ.setText("Plik wynikowy z Reveal'a (wynikowy plik html z prezentacja)");
		
		Label lblPlikrdowyZ = new Label(shell, SWT.NONE);
		FormData fd_lblPlikrdowyZ = new FormData();
		fd_lblPlikrdowyZ.top = new FormAttachment(textBuild, 15);
		fd_lblPlikrdowyZ.left = new FormAttachment(0, 10);
		lblPlikrdowyZ.setLayoutData(fd_lblPlikrdowyZ);
		lblPlikrdowyZ.setText("Folder z plikami \u017Ar\u00F3d\u0142owymi z Reveal'a na jego podstawie otrzymujemy wynikowy html");
		
		textSource = new Text(shell, SWT.BORDER);
		textSource.setText("C:\\Reveal Helper\\warsztat\\Warsztat");
		textSource.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//String[] directories = new File("dir").list();
				if(e.keyCode == 0xd) {
					System.out.println("wciœniêto enter");
					String folderPath = textSource.getText();
					System.out.println(folderPath);
					String[] directories = new File(folderPath).list();
					directories = filter(directories, ".*[.]html");
					Arrays.sort(directories);
					String[] allFiles = readAllFiles(directories, textSource.getText());
					
					System.out.println(Arrays.toString(directories));
					System.out.println(countWord(allFiles, "<section"));
					System.out.println(Arrays.toString(allFiles));
					
				}

			}
		});
		FormData fd_txtCrevealHelperwarsztatwarsztat = new FormData();
		fd_txtCrevealHelperwarsztatwarsztat.right = new FormAttachment(100, -10);
		fd_txtCrevealHelperwarsztatwarsztat.left = new FormAttachment(0, 10);
		fd_txtCrevealHelperwarsztatwarsztat.top = new FormAttachment(lblPlikrdowyZ, 6);
		textSource.setLayoutData(fd_txtCrevealHelperwarsztatwarsztat);
		
		browser_1 = new Browser(shell, SWT.NONE);
		browser_1.addLocationListener(new LocationAdapter() {
			@Override
			public void changed(LocationEvent event) {
				System.out.println(event);
			}
		});
		FormData fd_browser_1 = new FormData();
		fd_browser_1.top = new FormAttachment(textSource, 12);
		fd_browser_1.left = new FormAttachment(0, 10);
		fd_browser_1.right = new FormAttachment(100, -10);
		browser_1.setLayoutData(fd_browser_1);
		
		StyledText styledText = new StyledText(shell, SWT.BORDER);
		fd_browser_1.bottom = new FormAttachment(styledText, -6);
		FormData fd_styledText = new FormData();
		fd_styledText.bottom = new FormAttachment(100, -10);
		fd_styledText.top = new FormAttachment(0, 547);
		fd_styledText.right = new FormAttachment(100, -10);
		fd_styledText.left = new FormAttachment(0, 10);
		styledText.setLayoutData(fd_styledText);

	}
}
