import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UserInterface_N {

	private JFrame frame;
	private JTextField textDBName;
	private JTextField textDBURL;
	private JTextField textProjectPath;
	private JTextField textDBKeyPath;
	private JTextField textIntranetID;
	private JTextField textRestFilePath;
	private JTextField textExpirationYear;
	private JTextField textCSVFile;
	private JTextField textXMLRootTag;
	private JTextField textJsonPath;
	private JTextField textDocName;
	private JTextField textDocType;
	private JTextField textXMLKeyID;
	private JTextField textSource;
	private JTextField textSaveFileFolder;
	private JTextField textJARFileLocation;
	private BufferedReader xml;
	private BufferedReader area_1;
	private BufferedReader area_2;
	private BufferedReader br;
	private int entryCount;
	private int x;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UserInterface_N window = new UserInterface_N();
				window.frame.setVisible(true);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		});
	}

	/**
	 * Create the application.
	 *
	 * @wbp.parser.entryPoint
	 */
	public UserInterface_N() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().setForeground(new Color(153, 0, 0));
		frame.setBounds(50, 100, 846, 589);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		ArrayList<String> XMLPath = new ArrayList<>();
		ArrayList<String> labels = new ArrayList<>();
		String[] searchKey = new String[11];
		searchKey[0] = "!!intranetaddress!!";
		searchKey[1] = "!!CSV_FILENAME!!";
		searchKey[2] = "!!XSLT_FILENAME!!";
		searchKey[3] = "!!JSON_FILEPATH!!";
		searchKey[4] = "!!DB_URL!!";
		searchKey[5] = "!!DB Name!!";
		searchKey[6] = "!!REST_FILE_PATH!!";
		searchKey[7] = "!!KEY_ FULL_PATH!!";
		searchKey[8] = "!!XML_CODE!!";
		searchKey[9] = "!!XSLT_TITLE!!";
		searchKey[10] = "!!XSLT_CONTENT!!";

		String[] JSONKey = new String[6];
		JSONKey[0] = "name";
		JSONKey[1] = "label";
		JSONKey[2] = "hint";
		JSONKey[3] = "type";
		JSONKey[4] = "contentTemplate";
		JSONKey[5] = "isEncrypted";

		String JSONStatics[][][] = new String[3][2][2];
		JSONStatics[0][0][0] = "type";
		JSONStatics[0][1][0] = "doctype";
		JSONStatics[0][0][1] = "enum";
		JSONStatics[1][0][0] = "expirationyear";
		JSONStatics[1][1][0] = "expirationyear";
		JSONStatics[1][0][1] = "Numeric";
		JSONStatics[2][0][0] = "source";
		JSONStatics[2][1][0] = "source";
		JSONStatics[2][0][1] = "String";

		JLabel lblSaveFileLocation = new JLabel("Save file folder:");
		lblSaveFileLocation.setForeground(new Color(153, 0, 0));
		lblSaveFileLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSaveFileLocation.setBounds(24, 61, 105, 14);
		frame.getContentPane().add(lblSaveFileLocation);

		textSaveFileFolder = new JTextField();
		textSaveFileFolder.setColumns(10);
		textSaveFileFolder.setBounds(130, 55, 220, 20);
		frame.getContentPane().add(textSaveFileFolder);

		JLabel lblNewLabel = new JLabel("DBArchiver File Generation Tool");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(0, 0, 204));

		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setBounds(339, 11, 262, 14);

		JLabel lblDBName = new JLabel("DB Name:");
		lblDBName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDBName.setForeground(new Color(153, 0, 0));
		lblDBName.setBounds(24, 84, 89, 14);
		frame.getContentPane().add(lblDBName);

		JLabel lblDBUrl = new JLabel("DB URL:");
		lblDBUrl.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDBUrl.setForeground(new Color(153, 0, 0));
		lblDBUrl.setBounds(24, 112, 89, 14);
		frame.getContentPane().add(lblDBUrl);

		textDBURL = new JTextField();
		textDBURL.setBounds(130, 106, 220, 20);
		frame.getContentPane().add(textDBURL);
		textDBURL.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Project Path:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(new Color(153, 0, 0));
		lblNewLabel_1.setBounds(24, 137, 89, 14);
		frame.getContentPane().add(lblNewLabel_1);

		textDBName = new JTextField();
		textDBName.setBounds(130, 78, 220, 20);
		frame.getContentPane().add(textDBName);
		textDBName.setColumns(10);

		textProjectPath = new JTextField();
		textProjectPath.setBounds(130, 131, 220, 20);
		frame.getContentPane().add(textProjectPath);
		textProjectPath.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(53, 168, -6, -5);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("DB Key Path:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setForeground(new Color(153, 0, 0));
		lblNewLabel_3.setBounds(24, 162, 89, 14);
		frame.getContentPane().add(lblNewLabel_3);

		textDBKeyPath = new JTextField();
		textDBKeyPath.setBounds(130, 156, 220, 20);
		frame.getContentPane().add(textDBKeyPath);
		textDBKeyPath.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Intranet ID:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setForeground(new Color(153, 0, 0));
		lblNewLabel_4.setBounds(24, 187, 89, 14);
		frame.getContentPane().add(lblNewLabel_4);

		textIntranetID = new JTextField();
		textIntranetID.setBounds(129, 181, 221, 20);
		frame.getContentPane().add(textIntranetID);
		textIntranetID.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Rest File Path:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setForeground(new Color(153, 0, 0));
		lblNewLabel_5.setBounds(24, 212, 89, 14);
		frame.getContentPane().add(lblNewLabel_5);

		textRestFilePath = new JTextField();
		textRestFilePath.setBounds(130, 206, 220, 20);
		frame.getContentPane().add(textRestFilePath);
		textRestFilePath.setColumns(10);

		JLabel lblExpirationYear = new JLabel("Expiration Year:");
		lblExpirationYear.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblExpirationYear.setForeground(new Color(153, 0, 0));
		lblExpirationYear.setBounds(24, 238, 89, 14);
		frame.getContentPane().add(lblExpirationYear);

		textExpirationYear = new JTextField();
		textExpirationYear.setBounds(130, 232, 220, 20);
		frame.getContentPane().add(textExpirationYear);
		textExpirationYear.setColumns(10);

		JLabel lblCsvFileName = new JLabel("CSV File Name:");
		lblCsvFileName.setForeground(new Color(153, 0, 0));
		lblCsvFileName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCsvFileName.setBounds(411, 84, 133, 14);
		frame.getContentPane().add(lblCsvFileName);

		textCSVFile = new JTextField();
		textCSVFile.setColumns(10);
		textCSVFile.setBounds(554, 82, 220, 20);
		frame.getContentPane().add(textCSVFile);

		JLabel lblXmlPath = new JLabel("XML Root Tag:");
		lblXmlPath.setForeground(new Color(153, 0, 0));
		lblXmlPath.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblXmlPath.setBounds(411, 112, 133, 14);
		frame.getContentPane().add(lblXmlPath);

		textXMLRootTag = new JTextField();
		textXMLRootTag.setColumns(10);
		textXMLRootTag.setBounds(554, 106, 220, 20);
		frame.getContentPane().add(textXMLRootTag);

		JLabel lblJsonFilePath = new JLabel("JSON File Path:");
		lblJsonFilePath.setForeground(new Color(153, 0, 0));
		lblJsonFilePath.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblJsonFilePath.setBounds(411, 137, 133, 14);
		frame.getContentPane().add(lblJsonFilePath);

		textJsonPath = new JTextField();
		textJsonPath.setColumns(10);
		textJsonPath.setBounds(554, 131, 220, 20);
		frame.getContentPane().add(textJsonPath);

		JLabel lblDocName = new JLabel("DOC Name:");
		lblDocName.setForeground(new Color(153, 0, 0));
		lblDocName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDocName.setBounds(411, 162, 133, 14);
		frame.getContentPane().add(lblDocName);

		textDocName = new JTextField();
		textDocName.setColumns(10);
		textDocName.setBounds(554, 156, 220, 20);
		frame.getContentPane().add(textDocName);

		JLabel lblDocType = new JLabel("DOC Type:");
		lblDocType.setForeground(new Color(153, 0, 0));
		lblDocType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDocType.setBounds(411, 187, 133, 14);
		frame.getContentPane().add(lblDocType);

		textDocType = new JTextField();
		textDocType.setColumns(10);
		textDocType.setBounds(553, 181, 221, 20);
		frame.getContentPane().add(textDocType);

		JLabel lblXmlInitId = new JLabel("XML Init ID Key:");
		lblXmlInitId.setForeground(new Color(153, 0, 0));
		lblXmlInitId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblXmlInitId.setBounds(411, 212, 133, 14);
		frame.getContentPane().add(lblXmlInitId);

		textXMLKeyID = new JTextField();
		textXMLKeyID.setColumns(10);
		textXMLKeyID.setBounds(554, 206, 220, 20);
		frame.getContentPane().add(textXMLKeyID);

		JLabel lblSource = new JLabel("Source:");
		lblSource.setForeground(new Color(153, 0, 0));
		lblSource.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSource.setBounds(411, 238, 133, 14);
		frame.getContentPane().add(lblSource);

		textSource = new JTextField();
		textSource.setColumns(10);
		textSource.setBounds(554, 232, 220, 20);
		frame.getContentPane().add(textSource);

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setBounds(23, 266, 632, 247);
		frame.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Config File Template", null, panel, null);
		panel.setLayout(null);

		TextArea textArea = new TextArea();
		textArea.setBackground(new Color(220, 220, 220));
		textArea.setBounds(10, 10, 607, 199);
		panel.add(textArea);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("XSLT Template", null, panel_1, null);
		panel_1.setLayout(null);

		TextArea textArea_1 = new TextArea();
		textArea_1.setBackground(new Color(245, 222, 179));
		textArea_1.setBounds(10, 10, 607, 199);
		panel_1.add(textArea_1);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("DocType Parameters", null, panel_2, null);
		panel_2.setLayout(null);

		TextArea textArea_2 = new TextArea();
		textArea_2.setBackground(new Color(250, 250, 210));
		textArea_2.setBounds(10, 10, 607, 199);
		panel_2.add(textArea_2);

		JButton btnSavetotext = new JButton("Save Text Fields");
		btnSavetotext.setBounds(24, 9, 152, 23);
		frame.getContentPane().add(btnSavetotext);

		JButton btnAddDirectory = new JButton("Add Directory");
		btnAddDirectory.setBounds(665, 288, 157, 23);
		frame.getContentPane().add(btnAddDirectory);

		JButton btnReadPrevious = new JButton("Read Saved Record");
		btnReadPrevious.setBounds(665, 335, 157, 23);
		frame.getContentPane().add(btnReadPrevious);

		JButton btnExecute = new JButton("Execute");
		btnExecute.setBounds(665, 379, 157, 23);
		frame.getContentPane().add(btnExecute);

		JLabel lblUploaderJarLocation = new JLabel("Uploader JAR location:");
		lblUploaderJarLocation.setForeground(new Color(153, 0, 0));
		lblUploaderJarLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUploaderJarLocation.setBounds(411, 62, 133, 14);
		frame.getContentPane().add(lblUploaderJarLocation);

		textJARFileLocation = new JTextField();
		textJARFileLocation.setColumns(10);
		textJARFileLocation.setBounds(554, 59, 220, 20);
		frame.getContentPane().add(textJARFileLocation);

		btnAddDirectory.addActionListener(arg1 -> {
			JFileChooser f = new JFileChooser(new File("C:\\dbarchiver"));
			f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			f.showSaveDialog(null);

			// System.out.println(f.getCurrentDirectory());
			// System.out.println(f.getSelectedFile());
			textSaveFileFolder.setText(f.getSelectedFile().toString());
		});
		// Read previously saves content from file
		btnReadPrevious.addActionListener(arg1 -> {
			try {
				String str = null;
				int i = 0;
				br = new BufferedReader(
						new FileReader(textSaveFileFolder.getText() + "\\UserInterfaceLastSaved.txt"));
				while ((str = br.readLine()) != null) {
					if (i == 1) {
						textDBName.setText(str.toString());
					} else if (i == 2) {
						textDBURL.setText(str.toString());
					} else if (i == 3) {
						textProjectPath.setText(str.toString());
					} else if (i == 4) {
						textDBKeyPath.setText(str.toString());
					} else if (i == 5) {
						textIntranetID.setText(str.toString());
					} else if (i == 6) {
						textRestFilePath.setText(str.toString());
					} else if (i == 7) {
						textExpirationYear.setText(str.toString());
					} else if (i == 8) {
						textCSVFile.setText(str.toString());
					} else if (i == 9) {
						textXMLRootTag.setText(str.toString());
					} else if (i == 10) {
						textJsonPath.setText(str.toString());
					} else if (i == 11) {
						textDocName.setText(str.toString());
					} else if (i == 12) {
						textDocType.setText(str.toString());
					} else if (i == 13) {
						textXMLKeyID.setText(str.toString());
					} else if (i == 14) {
						textSource.setText(str.toString());
					} else if (i == 15) {
						textJARFileLocation.setText(str.toString());
					}
					i++;
				}

				/*
				 * CSVReader reader = new CSVReader(new FileReader(textSaveFileFolder.getText()
				 * + "\\DoctypeTable.csv"), '\t'); String[] nextLine; while ((nextLine =
				 * reader.readNext()) != null) { if (nextLine != null) { // Verifying the read
				 * data here System.out.println(Arrays.toString(nextLine)); } }
				 */
				String strLine;
				FileInputStream in = new FileInputStream(textSaveFileFolder.getText() + "\\XML_Config.txt");
				xml = new BufferedReader(new InputStreamReader(in));
				while ((strLine = xml.readLine()) != null) {
					textArea.append(strLine + "\n");
				}

				String strLine2;
				FileInputStream in2 = new FileInputStream(textSaveFileFolder.getText() + "\\XSLT_Config.txt");
				area_1 = new BufferedReader(new InputStreamReader(in2));
				while ((strLine2 = area_1.readLine()) != null) {
					textArea_1.append(strLine2 + "\n");
				}

				String strLine3;
				FileInputStream in3 = new FileInputStream(textSaveFileFolder.getText() + "\\DoctypeTable.csv");
				area_2 = new BufferedReader(new InputStreamReader(in3));
				while ((strLine3 = area_2.readLine()) != null) {
					textArea_2.append(strLine3 + "\n");
				}

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
	            return;				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}

		});

		btnExecute.addActionListener(arg3 -> {

			File file = new File(textSaveFileFolder.getText() + "\\XML_Config.txt");
			Scanner sc;
			try {
				sc = new Scanner(file);
				sc.useDelimiter("\\Z");
				// System.out.println(sc.next());

				sc.next();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			String txt = textArea.getText();

			textArea.setText(txt.replaceAll(searchKey[0], textIntranetID.getText()));
			txt = textArea.getText();
			textArea.setText(txt.replaceAll(searchKey[1], textCSVFile.getText()));
			txt = textArea.getText();
			textArea.setText(txt.replaceAll(searchKey[2], textDocName.getText() + ".xslt"));
			txt = textArea.getText();
			textArea.setText(txt.replaceAll(searchKey[3], textJsonPath.getText()));
			txt = textArea.getText();
			textArea.setText(txt.replaceAll(searchKey[4], textDBURL.getText()));
			txt = textArea.getText();
			textArea.setText(txt.replaceAll(searchKey[5], textDBName.getText()));
			txt = textArea.getText();
			textArea.setText(txt.replaceAll(searchKey[6], textRestFilePath.getText()));
			txt = textArea.getText();
			textArea.setText(txt.replaceAll(searchKey[7], textDBKeyPath.getText()));
			txt = textArea.getText();
			// Creating XML for config xml starts here
			BufferedReader csvreader = null;
			try {
				csvreader = new BufferedReader(new FileReader(textSaveFileFolder.getText() + "\\DoctypeTable.csv"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			StringBuilder xml = new StringBuilder();
			String lineBreak = System.getProperty("line.separator");

			// List<String> headers = new ArrayList<>();
			String XMLTag = "";
			entryCount = 0;
			xml.append("<" + textXMLRootTag.getText() + ">");
			xml.append(lineBreak);
			xml.append("<sequencenr>{sequencenr}</sequencenr>");
			xml.append(lineBreak);
			xml.append("<expirationyear>" + textExpirationYear.getText() + "</expirationyear>");
			xml.append(lineBreak);
			xml.append("<doctype>" + textDocType.getText() + "</doctype>");
			xml.append(lineBreak);
			xml.append("<source>" + textSource.getText() + "</source>");
			xml.append(lineBreak);

			String line;
			try {
				while ((line = csvreader.readLine()) != null) {
					StringTokenizer tokenizer = new StringTokenizer(line, ",");
					XMLTag = tokenizer.nextToken().toLowerCase();
					{
						xml.append("\t<" + XMLTag + ">{");
						xml.append(XMLTag);
						xml.append("}</" + XMLTag + ">");
						xml.append(lineBreak);
						entryCount++;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			xml.append("</" + textXMLRootTag.getText() + ">");

			// System.out.println(xml.toString());
			textArea.setText(txt.replaceAll(searchKey[8], xml.toString()));
			// End of XML Creation and insertion

			// Save Config file to disk Starts here
			txt = textArea.getText();
			try {
				String filetowork = textSaveFileFolder.getText() + "\\" + textDocName.getText() + "_config.xml";
				// Delete existing file
				File filetodelete = new File(filetowork);
				filetodelete.delete();

				FileOutputStream fos = new FileOutputStream(filetowork);
				// System.out.println(txt);
				byte[] b = txt.getBytes();
				fos.write(b);
				fos.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			try {
				csvreader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			// Finished Config XML file creation
			// Create JSON Starts here

			try {
				csvreader = new BufferedReader(new FileReader(textSaveFileFolder.getText() + "\\DoctypeTable.csv"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			StringBuilder json = new StringBuilder();
			System.getProperty("line.separator");
			// List<String> headers = new ArrayList<>();
			String jsonTag = "";
			// String jsonTag2 = "";
			// int count = 0;
			entryCount = 0;
			json.append("{");
			json.append(lineBreak);
			json.append("\t\"attachments\": [],");
			json.append(lineBreak);
			json.append("\t\"attachmentFullTextFieldName\": \"\",");
			json.append(lineBreak);
			json.append("\t\"fields\":[");
			json.append(lineBreak);

			json.append("\t{");
			json.append(lineBreak);
			json.append("\t" + '"' + JSONKey[0] + '"' + ": " + '"' + "id" + '"' + ",");
			json.append(lineBreak);
			json.append("\t" + '"' + JSONKey[4] + '"' + ": " + '"');
			json.append("{" + textXMLRootTag.getText() + "/doctype}-" + textXMLRootTag.getText() + "/"
					+ textXMLKeyID.getText() + "}-{" + textXMLRootTag.getText() + "/sequencenr" + "}" + '"' + ",");
			json.append(lineBreak);
			json.append("\t" + '"' + JSONKey[1] + '"' + ": " + '"' + " " + '"' + ",");
			json.append(lineBreak);
			json.append("\t" + '"' + JSONKey[2] + '"' + ": " + '"' + " " + '"' + ",");
			json.append(lineBreak);
			json.append("\t" + '"' + JSONKey[3] + '"' + ": " + '"' + " " + '"' + ",");
			json.append(lineBreak);
			json.append("\t" + '"' + JSONKey[5] + '"' + ": " + false + "");
			json.append(lineBreak);
			json.append("\t},");
			json.append(lineBreak);

			int count = 0;

			try {
				while ((line = csvreader.readLine()) != null) {
					StringTokenizer jsontokenizer = new StringTokenizer(line, ",");
					jsonTag = jsontokenizer.nextToken().toLowerCase();
					x = 0;
					{
						json.append("\t{");
						json.append(lineBreak);
						json.append("\t" + '"' + JSONKey[0] + '"' + ": " + '"');
						json.append(jsonTag + '"' + ",");
						json.append(lineBreak);

						json.append("\t" + '"' + JSONKey[4] + '"' + ": " + '"');
						XMLPath.add(textXMLRootTag.getText() + "/" + jsonTag);
						// json.append(XMLPath.get(x).toString());
						// json.append(
						// "{" + XMLPath.get(x).toString().replace("[", "").replace("]", "") + "}" + '"'
						// + ",");
						// System.out.println(XMLPath.get(x));
						json.append("{" + textXMLRootTag.getText() + "/" + jsonTag + "}" + '"' + ",");
						json.append(lineBreak);

						json.append("\t" + '"' + JSONKey[1] + '"' + ": " + '"');
						jsonTag = jsontokenizer.nextToken();
						labels.add(jsonTag);
						json.append(jsonTag + '"' + ",");
						json.append(lineBreak);

						json.append("\t" + '"' + JSONKey[2] + '"' + ": " + '"');
						jsonTag = jsontokenizer.nextToken();
						json.append(jsonTag + '"' + ",");
						json.append(lineBreak);

						json.append("\t" + '"' + JSONKey[3] + '"' + ": " + '"');
						jsonTag = jsontokenizer.nextToken();
						json.append(jsonTag + '"' + ",");
						json.append(lineBreak);

						Boolean isEncry = false;
						String data = "";
						json.append("\t" + '"' + JSONKey[5] + '"' + ": ");
						count = 0;
						while (jsontokenizer.hasMoreTokens()) {
							data = jsontokenizer.nextToken();
							System.out.println(data);
							if (count == 1 && data.contains("X")) {
								isEncry = true;
								// System.out.println("1111");
							}
							count++;
						}

						json.append(isEncry);
						json.append(lineBreak);
						json.append("\t},");
						json.append(lineBreak);
						entryCount++;
						// System.out.println(XMLPath.get(x));
						x++;

					}

					// System.out.println(XMLPath.toString() + " " + x);
				}

				int x;
				for (x = 0; x < JSONStatics.length; x++) {
					json.append("\t{");
					json.append(lineBreak);
					json.append("\t" + '"' + JSONKey[0] + '"' + ": " + '"' + JSONStatics[x][0][0] + '"' + ",");
					json.append(lineBreak);
					json.append("\t" + '"' + JSONKey[4] + '"' + ": " + '"');
					json.append("{" + textXMLRootTag.getText() + "/" + JSONStatics[x][1][0] + "}" + '"' + ",");
					json.append(lineBreak);
					json.append("\t" + '"' + JSONKey[1] + '"' + ": " + '"' + " " + '"' + ",");
					json.append(lineBreak);
					json.append("\t" + '"' + JSONKey[2] + '"' + ": " + '"' + " " + '"' + ",");
					json.append(lineBreak);
					json.append("\t" + '"' + JSONKey[3] + '"' + ": " + '"' + JSONStatics[x][0][1] + '"' + ",");
					json.append(lineBreak);
					json.append("\t" + '"' + JSONKey[5] + '"' + ": " + false);
					json.append(lineBreak);
					// Closing statment
					if (x == 2) {
						json.append("\t}");
					} else {
						json.append("\t},");
						json.append(lineBreak);
					}
				}
				// last line
				json.append(lineBreak);
				json.append("\t]");
				json.append(lineBreak);
				json.append("}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// JOptionPane.showMessageDialog(null, e.getMessage());
			}

			// System.out.println(json.toString());

			// Done JSON File Creation
			// Wite to disk
			BufferedWriter jsonwriter = null;
			// BufferedWriter XML = null;
			// BufferedWriter XSLT = null;
			try {
				jsonwriter = new BufferedWriter(new FileWriter(textJsonPath.getText()));
				jsonwriter.write(json.toString());
				jsonwriter.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			// Create XSLT File

			StringBuilder xsltres = new StringBuilder();
			String xsltTag = "";
			int x = 0;

			Iterator<String> iter = labels.iterator();
			while (iter.hasNext()) {
				xsltres.append("\t" + "<td style=\"width:420px;\"><b>"
						+ iter.next().toString().replace("[", "").replace("]", "") + ":</b></td>");
				xsltres.append(lineBreak);
				xsltres.append("\t" + xsltTag
						+ "<td style=\"color:#000000;padding-left:10px;padding-bottom:8px;\"><xsl:copy-of select=\""
						+ XMLPath.get(x).toString() + "\"/></td>\n" + "</tr>");
				xsltres.append(lineBreak);
				x++;
			}

			xsltres.append("\t" + "<td style=\"width:420px;\"><b>Expiration Year:</b></td>");
			xsltres.append(lineBreak);
			xsltres.append("\t" + xsltTag
					+ "<td style=\"color:#000000;padding-left:10px;padding-bottom:8px;\"><xsl:copy-of select=\""
					+ textXMLRootTag.getText() + "/" + "expirationyear\"/></td>\n" + "</tr>");
			xsltres.append(lineBreak);
			xsltres.append("\t" + "<td style=\"width:420px;\"><b>Document type:</b></td>");
			xsltres.append(lineBreak);
			xsltres.append("\t" + xsltTag
					+ "<td style=\"color:#000000;padding-left:10px;padding-bottom:8px;\"><xsl:copy-of select=\""
					+ textXMLRootTag.getText() + "/" + "doctype\"/></td>\n" + "</tr>");
			txt = textArea_1.getText();
			textArea_1.setText(txt.replaceAll(searchKey[10], xsltres.toString()));

			// System.out.println(textArea_1.toString());

			BufferedWriter xsltwriter = null;
			try {
				xsltwriter = new BufferedWriter(
						new FileWriter(textSaveFileFolder.getText() + "\\" + textDocName.getText() + ".xslt"));
				xsltwriter.write(textArea_1.toString());
				xsltwriter.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			// Done createing XSLT File

			// Create Groupings

			// Done creating Groupings

			// Create Uploader batchfile
			String docName = "";
			docName = textDocName.getText();
			BufferedWriter uploaderbatch = null;
			try {
				uploaderbatch = new BufferedWriter(
						new FileWriter(textSaveFileFolder.getText() + "\\" + docName + ".bat"));
				uploaderbatch.write("@echo off");
				uploaderbatch.newLine();
				uploaderbatch.write(textJARFileLocation.getText() + " -e " + textSaveFileFolder.getText() + "\\"
						+ docName + ".xml");
				uploaderbatch.newLine();
				uploaderbatch.write("pause");
				uploaderbatch.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			// Done Creating uploader batchfile

			// Create XSLT uploader batchfile
			BufferedWriter xsltbatch = null;
			try {
				xsltbatch = new BufferedWriter(
						new FileWriter(textSaveFileFolder.getText() + "\\" + docName + "_xslt.bat"));
				xsltbatch.write("@echo off");
				xsltbatch.newLine();
				xsltbatch.write(textJARFileLocation.getText() + " -s " + textSaveFileFolder.getText() + "\\" + docName
						+ ".xml " + docName + ".xslt " + docName + ".xslt");
				xsltbatch.newLine();
				xsltbatch.write("pause");
				xsltbatch.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			// Done creating XML Uploader Bathfile

			JOptionPane.showMessageDialog(frame, "Execution completed.");
		});
		////////////////////////////////////////////////////////////////
		btnSavetotext.addActionListener(arg1 -> {

			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(
						new FileWriter(textSaveFileFolder.getText() + "\\UserInterfaceLastSaved.txt"));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			try {
				writer.write(textSaveFileFolder.getText());
				writer.newLine();
				writer.write(textDBName.getText());
				writer.newLine();
				writer.write(textDBURL.getText());
				writer.newLine();
				writer.write(textProjectPath.getText());
				writer.newLine();
				writer.write(textDBKeyPath.getText());
				writer.newLine();
				writer.write(textIntranetID.getText());
				writer.newLine();
				writer.write(textRestFilePath.getText());
				writer.newLine();
				writer.write(textExpirationYear.getText());
				writer.newLine();
				writer.write(textCSVFile.getText());
				writer.newLine();
				writer.write(textXMLRootTag.getText());
				writer.newLine();
				writer.write(textJsonPath.getText());
				writer.newLine();
				writer.write(textDocName.getText());
				writer.newLine();
				writer.write(textDocType.getText());
				writer.newLine();
				writer.write(textXMLKeyID.getText());
				writer.newLine();
				writer.write(textSource.getText());
				writer.newLine();
				writer.write(textJARFileLocation.getText());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			// do stuff
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			JOptionPane.showMessageDialog(frame, "Save completed.");
		});

	}

}
