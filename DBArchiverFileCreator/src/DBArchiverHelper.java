import java.awt.Color;
import java.awt.Desktop;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class DBArchiverHelper {

    private final String lineBreak = System.getProperty("line.separator");
    private final String[] JSONKey = new String[] { "name", "label", "hint", "type", "contentTemplate", "isEncrypted" };
    private final String[] searchKey = new String[] { "!!intranetaddress!!", "!!CSV_FILENAME!!", "!!XSLT_FILENAME!!", "!!JSON_FILEPATH!!", "!!DB_URL!!",
        "!!DB Name!!", "!!REST_FILE_PATH!!", "!!KEY_ FULL_PATH!!", "!!XML_CODE!!", "!!XSLT_TITLE!!", "!!XSLT_CONTENT!!" };
    private final String csvHeader = "technicalname,label,hint,fieldtype,order,isencrypted,searchfor,display";

    private JFrame frame;
    private JTextField textDBName;
    private JTextField textDBURL;
    private JTextField textProjectPath;
    private JTextField textDBKeyPath;
    private JTextField textIntranetID;
    private JTextField textRestFilePath;
    private JTextField textDocTypeFilename;
    private JTextField textCSVFile;
    private JTextField textXMLRootTag;
    private JTextField textJsonPath;
    private JTextField textDocName;
    private JTextField textDocType;
    private JTextField textXMLKeyID;
    private JTextField textSource;
    private JTextField textSaveFileFolder;
    private JTextField textJARFileLocation; 
    private JTextField chckbx_allSheets;
    private TextArea textArea_0 = new TextArea();
    private TextArea textArea_1 = new TextArea();
    private TextArea textArea_2 = new TextArea();
    private int i = 0;
    private int j = 0;
    private JTextField textExpirationYear;
    private JTextField textexcelfilepath;
    private JTextField textexcelsheetnr;
	private XSSFWorkbook wb;
	private DocTypeCsvParser parser;
	private DocTypeCsvParser parser2;
	private JTextField textRowNr;
	private JTextField textColumnName;
	private BufferedReader area_1;
	private BufferedReader area_2;
	private JTextField textAllSheets;
	private JTextField textSeqNo;
	private String shortName="";
	private String docType="";
	private String sheetName="";
	public String loaded="";
	public String xlsDoctype = "";
	/**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                DBArchiverHelper window = new DBArchiverHelper();
                window.frame.setVisible(true);
            } catch (Exception e) {
               JOptionPane.showMessageDialog(null, e.getMessage());
               return;
            }
        });
    }

    /**
     * Create the application.
     *
     * @wbp.parser.entryPoint
     */
    public DBArchiverHelper() {

        initialize();

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame = new JFrame();
        frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 11));
        frame.getContentPane().setForeground(new Color(153, 0, 0));
        frame.setBounds(50, 100, 997, 658);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        createFields();
        createButtons();
        // readCSV();

    }

    private void createFields() {

        // TODO Auto-generated method stub
        JLabel lblUploaderJarLocation = new JLabel("Uploader JAR location:");
        lblUploaderJarLocation.setForeground(new Color(153, 0, 0));
        lblUploaderJarLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblUploaderJarLocation.setBounds(411, 62, 133, 14);
        frame.getContentPane().add(lblUploaderJarLocation);

        JLabel lblSaveFileLocation = new JLabel("Save file folder:");
        lblSaveFileLocation.setForeground(new Color(153, 0, 0));
        lblSaveFileLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblSaveFileLocation.setBounds(24, 61, 105, 14);
        frame.getContentPane().add(lblSaveFileLocation);

        textSaveFileFolder = new JTextField();
        textSaveFileFolder.setColumns(10);
        textSaveFileFolder.setBounds(130, 55, 262, 20);
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
        textDBURL.setBounds(130, 106, 262, 20);
        frame.getContentPane().add(textDBURL);
        textDBURL.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Project Path:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_1.setForeground(new Color(153, 0, 0));
        lblNewLabel_1.setBounds(24, 137, 89, 14);
        frame.getContentPane().add(lblNewLabel_1);

        textDBName = new JTextField();
        textDBName.setBounds(130, 78, 262, 20);
        frame.getContentPane().add(textDBName);
        textDBName.setColumns(10);

        textProjectPath = new JTextField();
        textProjectPath.setBounds(130, 131, 262, 20);
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
        textDBKeyPath.setBounds(130, 156, 262, 20);
        frame.getContentPane().add(textDBKeyPath);
        textDBKeyPath.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Intranet ID:");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_4.setForeground(new Color(153, 0, 0));
        lblNewLabel_4.setBounds(24, 187, 89, 14);
        frame.getContentPane().add(lblNewLabel_4);

        textIntranetID = new JTextField();
        textIntranetID.setBounds(129, 181, 263, 20);
        frame.getContentPane().add(textIntranetID);
        textIntranetID.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("LogFile Path:");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_5.setForeground(new Color(153, 0, 0));
        lblNewLabel_5.setBounds(24, 212, 89, 14);
        frame.getContentPane().add(lblNewLabel_5);

        textRestFilePath = new JTextField();
        textRestFilePath.setBounds(130, 206, 262, 20);
        frame.getContentPane().add(textRestFilePath);
        textRestFilePath.setColumns(10);

        JLabel lblExpirationYear = new JLabel("Doctype FileName:");
        lblExpirationYear.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblExpirationYear.setForeground(new Color(153, 0, 0));
        lblExpirationYear.setBounds(24, 238, 105, 14);
        frame.getContentPane().add(lblExpirationYear);

        textDocTypeFilename = new JTextField();
        textDocTypeFilename.setBounds(130, 232, 262, 20);
        frame.getContentPane().add(textDocTypeFilename);
        textDocTypeFilename.setColumns(10);

        JLabel lblCsvFileName = new JLabel("CSV File Name (source):");
        lblCsvFileName.setForeground(new Color(153, 0, 0));
        lblCsvFileName.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblCsvFileName.setBounds(411, 84, 133, 14);
        frame.getContentPane().add(lblCsvFileName);

        textCSVFile = new JTextField();
        textCSVFile.setColumns(10);
        textCSVFile.setBounds(554, 82, 378, 20);
        frame.getContentPane().add(textCSVFile);

        JLabel lblXmlPath = new JLabel("XML Root Tag:");
        lblXmlPath.setForeground(new Color(153, 0, 0));
        lblXmlPath.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblXmlPath.setBounds(411, 112, 133, 14);
        frame.getContentPane().add(lblXmlPath);

        textXMLRootTag = new JTextField();
        textXMLRootTag.setColumns(10);
        textXMLRootTag.setBounds(554, 106, 378, 20);
        frame.getContentPane().add(textXMLRootTag);

        JLabel lblJsonFilePath = new JLabel("JSON File Path:");
        lblJsonFilePath.setForeground(new Color(153, 0, 0));
        lblJsonFilePath.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblJsonFilePath.setBounds(411, 137, 133, 14);
        frame.getContentPane().add(lblJsonFilePath);

        textJsonPath = new JTextField();
        textJsonPath.setColumns(10);
        textJsonPath.setBounds(554, 131, 378, 20);
        frame.getContentPane().add(textJsonPath);

        JLabel lblDocName = new JLabel("DOC Name:");
        lblDocName.setForeground(new Color(153, 0, 0));
        lblDocName.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblDocName.setBounds(411, 162, 133, 14);
        frame.getContentPane().add(lblDocName);

        textDocName = new JTextField();
        textDocName.setColumns(10);
        textDocName.setBounds(554, 156, 378, 20);
        frame.getContentPane().add(textDocName);

        JLabel lblDocType = new JLabel("DOC Type:");
        lblDocType.setForeground(new Color(153, 0, 0));
        lblDocType.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblDocType.setBounds(411, 187, 133, 14);
        frame.getContentPane().add(lblDocType);

        textDocType = new JTextField();
        textDocType.setColumns(10);
        textDocType.setBounds(553, 181, 379, 20);
        frame.getContentPane().add(textDocType);

        JLabel lblXmlInitId = new JLabel("XML Init ID Key:");
        lblXmlInitId.setForeground(new Color(153, 0, 0));
        lblXmlInitId.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblXmlInitId.setBounds(411, 212, 133, 14);
        frame.getContentPane().add(lblXmlInitId);

        textXMLKeyID = new JTextField();
        textXMLKeyID.setColumns(10);
        textXMLKeyID.setBounds(554, 206, 378, 20);
        frame.getContentPane().add(textXMLKeyID);

        JLabel lblSource = new JLabel("Source:");
        lblSource.setForeground(new Color(153, 0, 0));
        lblSource.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblSource.setBounds(24, 292, 133, 14);
        frame.getContentPane().add(lblSource);

        textSource = new JTextField();
        textSource.setColumns(10);
        textSource.setBounds(130, 289, 262, 20);
        frame.getContentPane().add(textSource);

        JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
        tabbedPane.setBounds(23, 361, 632, 247);
        frame.getContentPane().add(tabbedPane);

        JPanel panel = new JPanel();
        tabbedPane.addTab("Config File Template", null, panel, null);
        panel.setLayout(null);

        textArea_0.setBackground(new Color(220, 220, 220));
        textArea_0.setBounds(10, 10, 607, 199);
        panel.add(textArea_0);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("XSLT Template", null, panel_1, null);
        panel_1.setLayout(null);

        textArea_1.setBackground(new Color(245, 222, 179));
        textArea_1.setBounds(10, 10, 607, 199);
        panel_1.add(textArea_1);

        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("DocType Parameters", null, panel_2, null);
        panel_2.setLayout(null);

        textArea_2.setBackground(new Color(250, 250, 210));
        textArea_2.setBounds(10, 10, 607, 199);
        panel_2.add(textArea_2);

        textJARFileLocation = new JTextField();
        textJARFileLocation.setColumns(10);
        textJARFileLocation.setBounds(554, 59, 378, 20);
        frame.getContentPane().add(textJARFileLocation);
        
        JLabel label = new JLabel("Expiration Year:");
        label.setForeground(new Color(153, 0, 0));
        label.setFont(new Font("Tahoma", Font.BOLD, 11));
        label.setBounds(24, 269, 89, 14);
        frame.getContentPane().add(label);

        textExpirationYear = new JTextField();
        textExpirationYear.setColumns(10);
        textExpirationYear.setBounds(130, 263, 262, 20);
        frame.getContentPane().add(textExpirationYear);

        JLabel lbl_ExcelFile = new JLabel("Excel FilePath:");
        lbl_ExcelFile.setForeground(new Color(153, 0, 0));
        lbl_ExcelFile.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_ExcelFile.setBounds(411, 238, 133, 14);
        frame.getContentPane().add(lbl_ExcelFile);

        textexcelfilepath = new JTextField();
        textexcelfilepath.setColumns(10);
        textexcelfilepath.setBounds(554, 235, 378, 20);
        frame.getContentPane().add(textexcelfilepath);

        JLabel lbl_ExcelSheetNumber = new JLabel("Excel Sheet Nr:");
        lbl_ExcelSheetNumber.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_ExcelSheetNumber.setForeground(new Color(153, 0, 0));
        lbl_ExcelSheetNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_ExcelSheetNumber.setBounds(411, 269, 133, 14);
        frame.getContentPane().add(lbl_ExcelSheetNumber);

        textexcelsheetnr = new JTextField();
        textexcelsheetnr.setColumns(10);
        textexcelsheetnr.setBounds(554, 261, 378, 20);
        frame.getContentPane().add(textexcelsheetnr);
        
        JLabel lbl_ExcelRowNr = new JLabel("Start Row Nr:");
        lbl_ExcelRowNr.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_ExcelRowNr.setForeground(new Color(153, 0, 0));
        lbl_ExcelRowNr.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_ExcelRowNr.setBounds(593, 295, 75, 14);
        frame.getContentPane().add(lbl_ExcelRowNr);
        
        textRowNr = new JTextField();
        textRowNr.setColumns(10);
        textRowNr.setBounds(678, 289, 33, 20);
        frame.getContentPane().add(textRowNr);
        
        JLabel lbl_ExcelColumn = new JLabel("Start Column Nr:");
        lbl_ExcelColumn.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_ExcelColumn.setForeground(new Color(153, 0, 0));
        lbl_ExcelColumn.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_ExcelColumn.setBounds(411, 295, 105, 14);
        frame.getContentPane().add(lbl_ExcelColumn);
        
        textColumnName = new JTextField();
        textColumnName.setColumns(10);
        textColumnName.setBounds(554, 289, 27, 20);
        frame.getContentPane().add(textColumnName);
        
        JLabel lbl_AllSheets = new JLabel("Pocess All Sheets (type in Y)");
        lbl_AllSheets.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_AllSheets.setForeground(new Color(153, 0, 0));
        lbl_AllSheets.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_AllSheets.setBounds(721, 295, 168, 14);
        frame.getContentPane().add(lbl_AllSheets);
        
        JLabel lbl_SkipSeq = new JLabel("Skip SeqNo (type in Y)");
        lbl_SkipSeq.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_SkipSeq.setForeground(new Color(153, 0, 0));
        lbl_SkipSeq.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_SkipSeq.setBounds(721, 320, 158, 14);
        frame.getContentPane().add(lbl_SkipSeq);
        
        textAllSheets = new JTextField();
        textAllSheets.setColumns(10);
        textAllSheets.setBounds(899, 289, 33, 20);
        frame.getContentPane().add(textAllSheets);
        
        textSeqNo = new JTextField();
        textSeqNo.setColumns(10);
        textSeqNo.setBounds(899, 317, 33, 20);
        frame.getContentPane().add(textSeqNo);

    }

   // @SuppressWarnings("deprecation")
	//@SuppressWarnings("deprecation")
	private boolean checkIfRowIsEmpty(Row row) {
        if (row == null) {
            return true;
        }
        if (row.getLastCellNum() <= 0) {
            return true;
        }
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellTypeEnum() != CellType.BLANK && StringUtils.isNotBlank(cell.toString())) {
                return false;
            }
        }
        return true;
    }

    private void createCSVfromXSLTTemplate() throws IOException {
    	String s3 = new String(textRowNr.getText());
    	String xlsDoctype = "";
    	if (StringUtils.isEmpty(s3))
    	{
    		 JOptionPane.showMessageDialog(null, "No fields are populated");
    		 
    	}
    	int rownr = Integer.parseInt(s3);
    	int colnr = Integer.parseInt(textColumnName.getText());

        StringBuilder csv_temp = new StringBuilder();
        new DataFormatter();
        String excelFilePath = textexcelfilepath.getText();
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        wb = new XSSFWorkbook(inputStream);
        wb.sheetIterator().forEachRemaining(sheet -> {
        	
        });
        // int sheetindex = Integer.parseInt(textexcelsheetnr.getText());
        XSSFSheet sheet = wb.getSheet(textexcelsheetnr.getText());
        Row row = null;
        String str = new String();
        for (int i = rownr-1 ; i < sheet.getLastRowNum() + 1; i++) {
            row = sheet.getRow(i);

            if (!checkIfRowIsEmpty(row)) {

                String rowString = new String();
                for (int j = colnr-1; j < 12; j++) {

                    if (row.getCell(j) == null) {
                        rowString = rowString + ",";
                    } else {
                        rowString = rowString + row.getCell(j) + ",";
                    }

                }

                str = str + rowString.substring(0, rowString.length() - 1) + lineBreak;
            }
        }
        csv_temp.append(str);
        // cell = sheet.getRow(29).getCell(org.apache.poi.ss.util.CellReference.convertColStringToIndex("B"));
        BufferedWriter csvwriter = null;
        // BufferedWriter XML = null;
        // BufferedWriter XSLT = null;
        try {
            csvwriter = new BufferedWriter(new FileWriter(textSaveFileFolder.getText() + "\\" + textDocName.getText() + ".csv"));
            csvwriter.write(csvHeader);
            csvwriter.write(lineBreak);
            csvwriter.write(csv_temp.toString());
            csvwriter.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e.getMessage());
           return;
        }

    }

    private void createButtons() {
        // TODO Auto-generated method stub
        JButton btnSavetotext = new JButton("Save Text Fields");
        btnSavetotext.setBounds(24, 9, 152, 23);
        frame.getContentPane().add(btnSavetotext);
        btnSavetotext.addActionListener(arg1 -> {
            savetodisk();

        });

        JButton btnAddDirectory = new JButton("Add Directory");
        btnAddDirectory.setBounds(665, 447, 157, 23);
        frame.getContentPane().add(btnAddDirectory);
        btnAddDirectory.addActionListener(arg1 -> {
            addDirectory();
        });

        JButton btnReadPrevious = new JButton("Read Saved Record");
        btnReadPrevious.setBounds(665, 494, 157, 23);
        frame.getContentPane().add(btnReadPrevious);
        btnReadPrevious.addActionListener(arg1 -> {
            try {
                readPreviouslySaved();
                readXMTemplateFile();
                readXSLTemplateFile();
                //readCSVTemplateFile();
            } catch (Throwable e) {
                // TODO Auto-generated catch block
               JOptionPane.showMessageDialog(null, e.getMessage());
               
            }

        });

        JButton btnExecute = new JButton("Execute");
        btnExecute.setBounds(665, 538, 157, 23);
        frame.getContentPane().add(btnExecute);

        JButton btnHelp = new JButton("Help");
        btnHelp.addActionListener(e -> {

            try {
                Desktop.getDesktop().browse(new URI(
                    "https://w3-connections.ibm.com/wikis/home?lang=en-us#!/wiki/Wbe26ed794d20_4478_ac7d_ab76d5354873/page/Running%20DBA%20Uploader"));
            } catch (IOException e1) {
                // TODO Auto-generated catch block
               JOptionPane.showMessageDialog(null, e1.getMessage());
               
            } catch (URISyntaxException e1) {
                // TODO Auto-generated catch block
               JOptionPane.showMessageDialog(null, e1.getMessage());
               
            }

        });
        btnHelp.setBounds(665, 585, 157, 23);
        frame.getContentPane().add(btnHelp);
        
        JButton btn_fromDefault = new JButton("Load last settings");
        btn_fromDefault.setBounds(665, 406, 157, 23);
        frame.getContentPane().add(btn_fromDefault);
        btn_fromDefault.addActionListener(arg1 -> {
        	
        	try {
        		loaded = "pressed";
                readPreviouslySaved();
                readXMTemplateFile();
                readXSLTemplateFile();
                //readCSVTemplateFile();
            } catch (Throwable e) {
                // TODO Auto-generated catch block
               JOptionPane.showMessageDialog(null, e.getMessage());
               
            }
        	
        });


        
        btnExecute.addActionListener(arg3 -> {

            try {
                createCSVfromXSLTTemplate();
            } catch (IOException e3) {
                // TODO Auto-generated catch block
            	JOptionPane.showMessageDialog(null, e3.getMessage());
            }

            try {
                createXML();
            } catch (IOException e2) {
                // TODO Auto-generated catch block
            	JOptionPane.showMessageDialog(null, e2.getMessage());
            }
            try {
                createJSON();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
               JOptionPane.showMessageDialog(null, e1.getMessage());
               
            }
            try {
                createXSLT();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
               JOptionPane.showMessageDialog(null, e1.getMessage());
               
            }
            try {
                createGroup();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
               JOptionPane.showMessageDialog(null, e1.getMessage());
               
            }
            createBatchFiles();

        });
    }

    //@SuppressWarnings("resource")
    private void createBatchFiles() {
        String docName = "";
        docName = textDocName.getText();
        BufferedWriter uploaderbatch = null;
        try {
            uploaderbatch = new BufferedWriter(new FileWriter(textSaveFileFolder.getText() + "\\" + docName + ".bat"));
            uploaderbatch
                .write(textJARFileLocation.getText() + "\\dbarchiver.bat -e " + textSaveFileFolder.getText() + "\\" + docName + "_config.xml");
            uploaderbatch.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e.getMessage());
          
        }
        BufferedWriter xsltbatch = null;
        try {
            xsltbatch = new BufferedWriter(new FileWriter(textSaveFileFolder.getText() + "\\" + docName + "_xslt.bat"));
            xsltbatch.write(textJARFileLocation.getText() + "\\dbarchiver.bat -s " + textSaveFileFolder.getText() + "\\" + docName + "_config.xml "
                + docName + ".xslt " + docName + ".xslt");
            xsltbatch.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e.getMessage());
          
        }
        // Done creating XML Uploader Bathfile

        JOptionPane.showMessageDialog(frame, "Execution completed.");
       

    }

    //@SuppressWarnings("unused")
    private int countRecords(Path path, String header) throws IOException {
        // CSVParser csvParser = new CSVParser(path,
        // CSVFormat.DEFAULT.withHeader("Student Name",
        // "Fees").withIgnoreHeaderCase().withTrim());
        try (DocTypeCsvParser parser = new DocTypeCsvParser(path)) {
            int count = 0;
            for (CSVRecord record : parser) {
                if (record.get(header).toLowerCase().contains("x")) {
                    count++;
                }
            }
            return count;
        }
    }

    //@SuppressWarnings("resource")
    private void createGroup() throws IOException {
        Path path = Paths.get(textSaveFileFolder.getText() + "\\" + textDocTypeFilename.getText());
        StringBuilder group = new StringBuilder();

        group.append(",");
        group.append(lineBreak);
        group.append("{");
        group.append(lineBreak);
        group.append("\"name\": ");
        group.append('"' + textDocType.getText() + '"' + ",");
        group.append(lineBreak);
        group.append("\"hitFields\": [");
        group.append(lineBreak);
        j = 1;

        int count = countRecords(path, "display");
        System.out.println(count);
        try (DocTypeCsvParser parser = new DocTypeCsvParser(path)) {
            for (CSVRecord record : parser) {
                // System.out.println(record);
                if (record.get("display").toLowerCase().contains("x")) {

                    if (j == count) {
                        group.append('"' + record.get("technicalname") + '"');
                        group.append(lineBreak);
                    } else {
                        group.append('"' + record.get("technicalname") + '"' + ",");
                        group.append(lineBreak);
                    }
                    j++;
                }
            }
        }

        group.append("],");
        group.append(lineBreak);
        group.append("\"searchFields\": [");
        group.append(lineBreak);
        group.append('"' + "type" + '"' + ",");
        group.append(lineBreak);
        j = 1;
        count = countRecords(path, "searchfor");

        try (DocTypeCsvParser parser = new DocTypeCsvParser(path)) {
            for (CSVRecord record : parser) {
                // System.out.println(record);
                if (record.get("searchfor").toLowerCase().contains("x")) {

                    if (j == count) {
                        group.append('"' + record.get("technicalname") + '"');
                        group.append(lineBreak);
                    } else {
                        group.append('"' + record.get("technicalname") + '"' + ",");
                        group.append(lineBreak);
                    }
                    j++;
                }
            }
        }
        group.append("],");
        group.append(lineBreak);
        group.append("\"fields\": []");
        group.append(lineBreak);
        group.append("}");
        group.append(lineBreak);
        group.append("]");
        group.append(lineBreak);
        group.append("}");

        BufferedWriter groupwriter = null;
        // BufferedWriter XML = null;
        // BufferedWriter XSLT = null;
        try {
            groupwriter = new BufferedWriter(new FileWriter(textSaveFileFolder.getText() + "\\" + textDocName.getText() + "group.txt"));
            groupwriter.write(group.toString());
            groupwriter.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e.getMessage());
          
        }

    }

    @SuppressWarnings("unused")
	private void readCSVTemplateFile() {
        // TODO Auto-generated method stub
        String strLine3;
        FileInputStream in3 = null;
        try {
            in3 = new FileInputStream(textSaveFileFolder.getText() + "\\" + textDocTypeFilename.getText());
            textArea_2.setText("");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e.getMessage()+"_1"); 
          return;
        }
       area_2 = new BufferedReader(new InputStreamReader(in3));
        try {
            while ((strLine3 = area_2.readLine()) != null) {
                textArea_2.append(strLine3 + "\n");

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e.getMessage()+"_2");
          
        }

    }

    private void readXSLTemplateFile() {
        // TODO Auto-generated method stub
        String strLine2;
        FileInputStream in2 = null;
        try {
            in2 = new FileInputStream(textSaveFileFolder.getText() + "\\XSLT_Config.txt");
            textArea_1.setText("");
        } catch (FileNotFoundException e) {
           JOptionPane.showMessageDialog(null, e.getMessage());
          
        }
       area_1 = new BufferedReader(new InputStreamReader(in2));
        try {
            while ((strLine2 = area_1.readLine()) != null) {
                textArea_1.append(strLine2 + "\n");
            }
        } catch (IOException e) {
           JOptionPane.showMessageDialog(null, e.getMessage());
          
        }
    }

    //@SuppressWarnings("resource")
    private void createXSLT() throws IOException {
        Path path = Paths.get(textSaveFileFolder.getText() + "\\" + textDocTypeFilename.getText());
        parser = new DocTypeCsvParser(path);
        parser.getHeaderMap();

        StringBuilder xsltres = new StringBuilder();

        for (CSVRecord record : parser) {
            if (!record.get("label").contains("label")) {
                xsltres.append("\t" + "<tr><td style=\"width:220px;\"><b>" + record.get("label") + ":</b></td>");
                xsltres.append(lineBreak);
                xsltres.append("\t" + "<td style=\"color:#000000;padding-left:10px;padding-bottom:8px;\"><xsl:copy-of select=\""
                    + textXMLRootTag.getText() + "/" + record.get("technicalname") + "\"/></td>\n" + "</tr>");
                xsltres.append(lineBreak);
            }
        }

        xsltres.append("\t" + "<tr><td style=\"width:220px;\"><b>Expiration Year:</b></td>");
        xsltres.append(lineBreak);
        xsltres.append("\t" + "<td style=\"color:#000000;padding-left:10px;padding-bottom:8px;\"><xsl:copy-of select=\"" + textXMLRootTag.getText()
            + "/" + "expirationyear\"/></td>\n" + "</tr>");
        xsltres.append(lineBreak);
        xsltres.append("\t" + "<tr><td style=\"width:220px;\"><b>Document type:</b></td>");
        xsltres.append(lineBreak);
        xsltres.append("\t" + "<td style=\"color:#000000;padding-left:10px;padding-bottom:8px;\"><xsl:copy-of select=\"" + textXMLRootTag.getText()
            + "/" + "doctype\"/></td>\n" + "</tr>");

        String txt = textArea_1.getText();
        textArea_1.setText(txt.replaceAll(searchKey[10], xsltres.toString()));
        txt = textArea_1.getText();
        textArea_1.setText(txt.replaceAll(searchKey[9], textDocType.getText()));

        txt = textArea_1.getText();
        // System.out.println(txt);
        String filetowork = textSaveFileFolder.getText() + "\\" + textDocName.getText() + ".xslt";
        // Delete existing file
        File filetodelete = new File(filetowork);
        filetodelete.delete();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filetowork);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e1.getMessage());
           
        }

        fos = new FileOutputStream(filetowork);
        // System.out.println(txt);
        byte[] b = txt.getBytes();
        try {
            fos.write(b);
        } catch (IOException e) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e.getMessage());
           
        }
        try {
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e.getMessage());
          
        }
        
    }

    @SuppressWarnings("resource")
    private void createJSON() throws IOException {
        Path path = Paths.get(textSaveFileFolder.getText() + "\\" + textDocTypeFilename.getText());

        DocTypeCsvParser parser = new DocTypeCsvParser(path);
        parser.getHeaderMap();

        String JSONStatics[][][][] = new String[3][2][2][2];
        JSONStatics[0][0][0][0] = "type";
        JSONStatics[0][1][0][0] = "doctype";
        JSONStatics[0][0][1][0] = "enum";
        JSONStatics[0][0][0][1] = "Document type";
        JSONStatics[1][0][0][0] = "expirationyear";
        JSONStatics[1][1][0][0] = "expirationyear";
        JSONStatics[1][0][0][1] = "Expiration year";
        JSONStatics[1][0][1][0] = "Numeric";
        JSONStatics[2][0][0][0] = "source";
        JSONStatics[2][1][0][0] = "source";
        JSONStatics[2][0][1][0] = "String";
        JSONStatics[2][0][0][1] = "Source";

        StringBuilder json = new StringBuilder();

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
        // json.append("{" + textXMLRootTag.getText() + "/doctype}-{" +
        // textXMLRootTag.getText() + "/"
        // + textXMLKeyID.getText() + "}-{" + textXMLRootTag.getText() + "/sequencenr" +
        // "}" + '"' + ",");
        json.append("{" + textXMLRootTag.getText() + "/id}" + '"' + ",");
        // + textXMLKeyID.getText())
        json.append(lineBreak);
        json.append("\t" + '"' + JSONKey[1] + '"' + ": " + '"' + "ID" + '"' + ",");
        json.append(lineBreak);
        json.append("\t" + '"' + JSONKey[2] + '"' + ": " + '"' + "Unique id" + '"' + ",");
        json.append(lineBreak);
        json.append("\t" + '"' + JSONKey[3] + '"' + ": " + "\"String\"" + ",");
        json.append(lineBreak);
        json.append("\t" + '"' + JSONKey[5] + '"' + ": " + false + "");
        json.append(lineBreak);
        json.append("\t},");
        json.append(lineBreak);

        // TODO Auto-generated method stub

        for (CSVRecord record : parser) {
            if (record.get("display").toLowerCase().contains("x") || record.get("searchfor").toLowerCase().contains("x")) {

                json.append("\t{");
                json.append(lineBreak);
                json.append("\t" + '"' + JSONKey[0] + '"' + ": " + '"');
                json.append(record.get("technicalname") + '"' + ",");
                json.append(lineBreak);

                json.append("\t" + '"' + JSONKey[4] + '"' + ": " + '"');
                json.append("{" + textXMLRootTag.getText() + "/" + record.get("technicalname") + "}" + '"' + ",");
                json.append(lineBreak);

                json.append("\t" + '"' + JSONKey[1] + '"' + ": " + '"');
                json.append(record.get("label") + '"' + ",");
                json.append(lineBreak);

                json.append("\t" + '"' + JSONKey[2] + '"' + ": " + '"');
                json.append(record.get("hint") + '"' + ",");
                json.append(lineBreak);

                json.append("\t" + '"' + JSONKey[3] + '"' + ": " + '"');
                json.append(record.get("fieldtype") + '"' + ",");
                json.append(lineBreak);

                Boolean isEncry = false;

                json.append("\t" + '"' + JSONKey[5] + '"' + ": ");
                if (record.get("isencrypted").toLowerCase().contains("x")) {
                    isEncry = true;
                }
                json.append(isEncry);
                json.append(lineBreak);
                json.append("\t},");
                json.append(lineBreak);
            }

        }
        int x;
        for (x = 0; x < JSONStatics.length; x++) {
            json.append("\t{");
            json.append(lineBreak);
            json.append("\t" + '"' + JSONKey[0] + '"' + ": " + '"' + JSONStatics[x][0][0][0] + '"' + ",");
            json.append(lineBreak);
            json.append("\t" + '"' + JSONKey[4] + '"' + ": " + '"');
            json.append("{" + textXMLRootTag.getText() + "/" + JSONStatics[x][1][0][0] + "}" + '"' + ",");
            json.append(lineBreak);
            json.append("\t" + '"' + JSONKey[1] + '"' + ": " + '"' + JSONStatics[x][0][0][1] + '"' + ",");
            json.append(lineBreak);
            json.append("\t" + '"' + JSONKey[2] + '"' + ": " + '"' + " " + '"' + ",");
            json.append(lineBreak);
            json.append("\t" + '"' + JSONKey[3] + '"' + ": " + '"' + JSONStatics[x][0][1][0] + '"' + ",");
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

        BufferedWriter jsonwriter = null;
        try {
            jsonwriter = new BufferedWriter(new FileWriter(textSaveFileFolder.getText() + "\\" + textDocName.getText() + ".json"));
            jsonwriter.write(json.toString());
            jsonwriter.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e.getMessage());
           return;
        }

    }

    private void readXMTemplateFile() {

        String strLine;
        FileInputStream in = null;
        try {
            in = new FileInputStream(textSaveFileFolder.getText() + "\\XML_Config.txt");
            textArea_0.setText("");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e.getMessage());
           
        }
        BufferedReader xml = new BufferedReader(new InputStreamReader(in));
        try {
            while ((strLine = xml.readLine()) != null) {
                textArea_0.append(strLine + "\n");
                // System.out.println(textArea_0.toString());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e.getMessage());
           
        }
    }

    private void readPreviouslySaved() throws Throwable {
        // TODO Auto-generated method stub
        String str = null;
        String path = "";
        i = 0;        
        if (loaded == "pressed") {
        	path = "C:\\dbarchiver\\UserInterfaceLastSaved.txt";
        } else
        {      	 
        	path = textSaveFileFolder.getText() + "\\UserInterfaceLastSaved.txt";
        }
    		   
        BufferedReader br = new BufferedReader(new FileReader(path));
        while ((str = br.readLine()) != null) {
        	if (i == 0 )  {
        		textSaveFileFolder.setText(str.toString()); 
        	} else if (i == 1) {            	
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
            	textDocTypeFilename.setText(str.toString());     
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
            } else if (i == 16) {
            	textexcelfilepath.setText(str.toString()); 
            } else if (i == 17) {
            	 textexcelsheetnr.setText(str.toString());         
            } else if (i == 18) {
            	textExpirationYear.setText(str.toString());         
            } else if (i == 19) {
            	textColumnName.setText(str.toString());      	
            } else if (i == 20) {
            	textRowNr.setText(str.toString());  
            } else if (i == 21) {
            	textAllSheets.setText(str.toString());	    
            } else if (i == 22) {
            	textSeqNo.setText(str.toString());
            } 
            i++;
        }
        br.close();

    }

   // @SuppressWarnings("resource")
    private void savetodisk() {
        // TODO Auto-generated method stub
        BufferedWriter writer = null;
        BufferedWriter backup = null;
        try {
            writer = new BufferedWriter(new FileWriter(textSaveFileFolder.getText() + "\\UserInterfaceLastSaved.txt"));
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
            writer.write(textDocTypeFilename.getText());
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
            writer.newLine();
            writer.write(textexcelfilepath.getText());
            writer.newLine();
            writer.write(textexcelsheetnr.getText());
            writer.newLine();
            writer.write(textExpirationYear.getText());
            writer.newLine();
            writer.write(textColumnName.getText());
            writer.newLine();
            writer.write(textRowNr.getText());
            writer.newLine();
            writer.write(textAllSheets.getText());
            writer.newLine();
            writer.write(textSeqNo.getText());  
           // writer.newLine();
           // writer.write(textSaveFileFolder.getText());   
            
            backup = new BufferedWriter(new FileWriter("C:\\dbarchiver\\UserInterfaceLastSaved.txt"));
            backup.write(textSaveFileFolder.getText());
            backup.newLine();
            backup.write(textDBName.getText());
            backup.newLine();
            backup.write(textDBURL.getText());
            backup.newLine();
            backup.write(textProjectPath.getText());
            backup.newLine();
            backup.write(textDBKeyPath.getText());
            backup.newLine();
            backup.write(textIntranetID.getText());
            backup.newLine();
            backup.write(textRestFilePath.getText());
            backup.newLine();
            backup.write(textDocTypeFilename.getText());
            backup.newLine();
            backup.write(textCSVFile.getText());
            backup.newLine();
            backup.write(textXMLRootTag.getText());
            backup.newLine();
            backup.write(textJsonPath.getText());
            backup.newLine();
            backup.write(textDocName.getText());
            backup.newLine();
            backup.write(textDocType.getText());
            backup.newLine();
            backup.write(textXMLKeyID.getText());
            backup.newLine();
            backup.write(textSource.getText());
            backup.newLine();
            backup.write(textJARFileLocation.getText());
            backup.newLine();
            backup.write(textexcelfilepath.getText());
            backup.newLine();
            backup.write(textexcelsheetnr.getText());
            backup.newLine();
            backup.write(textExpirationYear.getText());
            backup.newLine();
            backup.write(textColumnName.getText());
            backup.newLine();
            backup.write(textRowNr.getText());
            backup.newLine();
            backup.write(textAllSheets.getText());
            backup.newLine();
            backup.write(textSeqNo.getText());  
           // backup.newLine();
            //backup.write(textSaveFileFolder.getText());  
           
        } catch (IOException e1) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e1.getMessage());
           return;
        }
        try {
            writer.close();
            backup.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e.getMessage());
           return;
        }
        JOptionPane.showMessageDialog(frame, "Save completed.");
        return;
        
    }

    private void addDirectory() {
        // TODO Auto-generated method stub
        JFileChooser f = new JFileChooser(new File("C:\\dbarchiver"));
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.showSaveDialog(null);
        textSaveFileFolder.setText(f.getSelectedFile().toString());
    }

    private void createXML() throws IOException {
        Path path = Paths.get(textSaveFileFolder.getText() + "\\" + textDocTypeFilename.getText());
        parser2 = new DocTypeCsvParser(path);
        parser2.getHeaderMap();

        String txt = textArea_0.getText();

        textArea_0.setText(txt.replaceAll(searchKey[0], textIntranetID.getText()));
        txt = textArea_0.getText();
        textArea_0.setText(txt.replaceAll(searchKey[1], textCSVFile.getText()));
        txt = textArea_0.getText();
        textArea_0.setText(txt.replaceAll(searchKey[2], textDocName.getText() + ".xslt"));
        txt = textArea_0.getText();
        textArea_0.setText(txt.replaceAll(searchKey[3], textJsonPath.getText()));
        txt = textArea_0.getText();
        textArea_0.setText(txt.replaceAll(searchKey[4], textDBURL.getText()));
        txt = textArea_0.getText();
        textArea_0.setText(txt.replaceAll(searchKey[5], textDBName.getText()));
        txt = textArea_0.getText();
        textArea_0.setText(txt.replaceAll(searchKey[6], textRestFilePath.getText()));
        txt = textArea_0.getText();
        textArea_0.setText(txt.replaceAll(searchKey[7], textDBKeyPath.getText()));
        txt = textArea_0.getText();

        StringBuilder xml = new StringBuilder();
        // String lineBreak = System.getProperty("line.separator");

        String XMLTag = "";

        xml.append("<" + textXMLRootTag.getText() + ">");
        xml.append(lineBreak);
        if (!textSeqNo.getText().equals("Y"))
        {
            xml.append("\t" + "<sequencenr>{sequencenr}</sequencenr>");
             xml.append(lineBreak);
             xml.append("\t" + "<id>" + textDocType.getText() + "-{" + textXMLKeyID.getText() + "}-{sequencenr}</id>");
            xml.append(lineBreak);
            String expyear;
            if (textExpirationYear.getText().equals("")) {
                expyear = "{expirationyear}";
            } else {
                expyear = textExpirationYear.getText();
            }
            xml.append("\t" + "<expirationyear>" + expyear + "</expirationyear>");
            xml.append(lineBreak);
        }      		

        
        xml.append("\t" + "<doctype>" + textDocType.getText() + "</doctype>");
        xml.append(lineBreak);
        xml.append("\t" + "<source>" + textSource.getText() + "</source>");
        xml.append(lineBreak);

        txt = textArea_0.getText();

        for (CSVRecord record : parser2) {

            XMLTag = record.get("technicalname");
            if (record.getRecordNumber() == 1) {
            } else {

                xml.append("\t<" + XMLTag + ">{");
                xml.append(XMLTag);
                xml.append("}</" + XMLTag + ">");
                xml.append(lineBreak);
            }
        }
        xml.append("</" + textXMLRootTag.getText() + ">");

        textArea_0.setText(txt.replaceAll(searchKey[8], xml.toString()));

        txt = textArea_0.getText();

        String filetowork = textSaveFileFolder.getText() + "\\" + textDocName.getText() + "_config.xml";
        // Delete existing file
        File filetodelete = new File(filetowork);
        filetodelete.delete();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filetowork);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e1.getMessage());
           
        }

        try {
            fos = new FileOutputStream(filetowork);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e.getMessage());
           
        }
        // System.out.println(txt);
        byte[] b = txt.getBytes();
        try {
            fos.write(b);
        } catch (IOException e) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e.getMessage());
           
        }
        try {
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, e.getMessage());
          
        }

    }
}
