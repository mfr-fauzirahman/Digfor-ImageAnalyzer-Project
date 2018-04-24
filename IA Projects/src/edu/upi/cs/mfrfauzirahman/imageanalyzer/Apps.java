package edu.upi.cs.mfrfauzirahman.imageanalyzer;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifThumbnailDirectory;

import edu.upi.cs.mfrfauzirahman.imageanalyzer.utilities.ImageELA;
import edu.upi.cs.mfrfauzirahman.imageanalyzer.utilities.ImageMask;
import edu.upi.cs.mfrfauzirahman.imageanalyzer.utilities.ImageTools;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Apps extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	public File input = null;
	public File outputfile = null;
	public double fileSize = 0;
	
	public static int[] MASK_RGB = ImageTools.MaskColor("CYAN");
	
	public Image img = null;
	public Metadata metadata = null;
	public BufferedImage imgInput = null;
    private BufferedImage imgRecomp = null;
    private BufferedImage imgELA = null;
    private BufferedImage imgMask = null;
    private BufferedImage imgThumb = null;
	private ImageIcon imgIco = null;
	
	public boolean thumbExists = false;
	public int thumbWidth = 0;
	public int thumbHeight = 0;
	public int thumbLen = 0;
	
	public int imgWidth = 0;
	public final int labWidth = 1005;
	public int imgHeight = 0;
	public final int labHeight = 642;
	
	public static final long KILO_BYTES = 1024;
	public static final long MEGA_BYTES = 1024 * KILO_BYTES;
    
    
    /**
     * Creates new form Apps
     */
    public Apps() {
    	addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			clearTemp();
    		}
    	});
    	setPreferredSize(new Dimension(1280, 720));
    	setResizable(false);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	labValQ = new JLabel();
    	labValQ.setBounds(114, 17, 50, 14);
    	labValQ.setText("95%");
    	labValQ.setText("20%");
        TabPanel = new javax.swing.JTabbedPane();
        labInput = new javax.swing.JLabel();
        labInput.setHorizontalAlignment(SwingConstants.CENTER);
        TabConfig = new javax.swing.JTabbedPane();
        pConfELA = new javax.swing.JPanel();
        labQuality = new javax.swing.JLabel();
        labQuality.setBounds(10, 11, 94, 22);
        sliderQuality = new javax.swing.JSlider();
        sliderQuality.setMaximum(99);
        sliderQuality.setBounds(10, 44, 230, 22);
        sliderQuality.setMinimum(1);
        sliderQuality.addChangeListener(new ChangeListener() {
        	@Override
			public void stateChanged(ChangeEvent e) {
        		updQVal();
        	}
        });
        MenuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        mOpen = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Digital Image Forensics Tools");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setMinimumSize(new java.awt.Dimension(1024, 720));
        setName("frameMain");
        setSize(new java.awt.Dimension(1280, 720));

        TabPanel.addTab("Input", labInput);
        labELA = new javax.swing.JLabel();
        labELA.setHorizontalAlignment(SwingConstants.CENTER);
        TabPanel.addTab("Error Level Analysis", labELA);
        
        labComp = new JLabel("");
        labComp.setHorizontalAlignment(SwingConstants.CENTER);
        TabPanel.addTab("Re-compressed", null, labComp, null);
        labMask = new javax.swing.JLabel();
        labMask.setHorizontalAlignment(SwingConstants.CENTER);
        TabPanel.addTab("Masked Image", labMask);
        
        labThumbBig = new JLabel();
        labThumbBig.setHorizontalTextPosition(SwingConstants.CENTER);
        labThumbBig.setHorizontalAlignment(SwingConstants.CENTER);
        TabPanel.addTab("Thumbnail (Large)", null, labThumbBig, null);

        labQuality.setFont(new Font("Tahoma", Font.PLAIN, 14)); // NOI18N
        labQuality.setText("JPEG Quality");

        sliderQuality.setValue(95);
        
        JButton btnConfELA = new JButton("Apply");
        btnConfELA.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					applyConfigELA();
				} catch (ImageProcessingException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	}
        });
        btnConfELA.setBounds(10, 135, 59, 23);
        btnConfELA.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		try {
					applyConfigELA();
				} catch (ImageProcessingException | IOException e1) {
					e1.printStackTrace();
				}
        	}
        });
        
        JButton btnDefELA = new JButton("Default");
        btnDefELA.setBounds(173, 135, 67, 23);
        btnDefELA.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		defaultConfigELA();
        	}
        });

        TabConfig.addTab("Config (ELA)", pConfELA);
        
        labErrScale = new JLabel();
        labErrScale.setBounds(10, 69, 94, 22);
        labErrScale.setText("Error Scale");
        labErrScale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        sliderErrScale = new JSlider();
        sliderErrScale.setValue(20);
        sliderErrScale.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) {
        		updErrVal();
        	}
        });
        sliderErrScale.setBounds(10, 102, 230, 22);
        pConfELA.setLayout(null);
        pConfELA.add(labQuality);
        pConfELA.add(labValQ);
        pConfELA.add(sliderErrScale);
        pConfELA.add(sliderQuality);
        pConfELA.add(labErrScale);
        pConfELA.add(btnConfELA);
        pConfELA.add(btnDefELA);
        
        labValE = new JLabel();
        labValE.setText("20%");
        labValE.setBounds(114, 77, 50, 14);
        pConfELA.add(labValE);

        menuFile.setText("File");

        mOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mOpen.setText("Open Image");
        mOpen.setToolTipText("");
        mOpen.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                mOpenIMG(evt);
            }
        });
        menuFile.add(mOpen);

        MenuBar.add(menuFile);
        
        mntmSaveEla = new JMenuItem("Save ELA");
        menuFile.add(mntmSaveEla);
        
        mntmSaveMaskedImage = new JMenuItem("Save Masked Image");
        menuFile.add(mntmSaveMaskedImage);

        setJMenuBar(MenuBar);
        
        JTabbedPane TabAnalysis = new JTabbedPane(SwingConstants.TOP);
        
        TabThumb = new JTabbedPane(JTabbedPane.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(TabPanel, GroupLayout.PREFERRED_SIZE, 1010, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(TabThumb, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
        				.addComponent(TabConfig, 0, 0, Short.MAX_VALUE)
        				.addComponent(TabAnalysis, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(TabAnalysis, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(TabThumb, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(TabConfig, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
        		.addComponent(TabPanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        
        labThumbSmall = new JLabel("");
        labThumbSmall.setHorizontalAlignment(SwingConstants.CENTER);
        TabThumb.addTab("Thumbnail (Small)", null, labThumbSmall, null);
        
        pConfMask = new JPanel();
        pConfMask.setLayout(null);
        TabConfig.addTab("Config (Mask)", null, pConfMask, null);
        
        labMaskThresh = new JLabel();
        labMaskThresh.setText("Mask Threshold");
        labMaskThresh.setFont(new Font("Tahoma", Font.PLAIN, 14));
        labMaskThresh.setBounds(10, 11, 94, 22);
        pConfMask.add(labMaskThresh);
        
        labValT = new JLabel();
        labValT.setText("25");
        labValT.setBounds(114, 17, 126, 14);
        pConfMask.add(labValT);
        
        sliderThresh = new JSlider();
        sliderThresh.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) {
        		updTVal();
        	}
        });
        sliderThresh.setMaximum(255);
        sliderThresh.setValue(25);
        sliderThresh.setMinimum(1);
        sliderThresh.setBounds(10, 44, 230, 22);
        pConfMask.add(sliderThresh);
        
        lblMaskColor = new JLabel();
        lblMaskColor.setText("Mask Color");
        lblMaskColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMaskColor.setBounds(10, 69, 94, 22);
        pConfMask.add(lblMaskColor);
        
        btnConfMask = new JButton("Apply");
        btnConfMask.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		applyMask();
        	}
        });
        btnConfMask.setBounds(10, 133, 59, 23);
        pConfMask.add(btnConfMask);
        
        btnDefMask = new JButton("Default");
        btnDefMask.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		defMask();
        	}
        });
        btnDefMask.setBounds(173, 133, 67, 23);
        pConfMask.add(btnDefMask);
        
        comboBox = new JComboBox();
        comboBox.setBounds(10, 102, 230, 20);
        pConfMask.add(comboBox);
        
        JPanel pDetails = new JPanel();
        TabAnalysis.addTab("Details", null, pDetails, null);
        
        lblFileSize = new JLabel("File size");
        lblFileSize.setBounds(10, 31, 37, 14);
        
        lblResolution = new JLabel("Resolution");
        lblResolution.setBounds(10, 52, 50, 14);
        
        lblPath = new JLabel("Path");
        lblPath.setBounds(10, 121, 22, 14);
        
        labResoVal = new JLabel("-");
        labResoVal.setBounds(66, 52, 174, 14);
        
        labFileSizeVal = new JLabel("-");
        labFileSizeVal.setBounds(66, 31, 174, 15);
        
        labFileNameVal = new JLabel("-");
        labFileNameVal.setBounds(66, 11, 174, 14);
        
        txtaPath = new JTextArea();
        txtaPath.setWrapStyleWord(true);
        txtaPath.setFont(UIManager.getFont("Label.font"));
        txtaPath.setLineWrap(true);
        txtaPath.setEditable(false);
        txtaPath.setBackground(UIManager.getColor("Button.background"));
        txtaPath.setBounds(66, 119, 174, 109);
        
        labFile = new JLabel("File name");
        labFile.setBounds(10, 11, 45, 14);
        pDetails.setLayout(null);
        pDetails.add(lblResolution);
        pDetails.add(lblFileSize);
        pDetails.add(lblPath);
        pDetails.add(labResoVal);
        pDetails.add(labFileNameVal);
        pDetails.add(labFileSizeVal);
        pDetails.add(txtaPath);
        pDetails.add(labFile);
        
        lblThumbnail = new JLabel("Thumbnail");
        lblThumbnail.setBounds(10, 73, 50, 14);
        pDetails.add(lblThumbnail);
        
        labThumbStatus = new JLabel("-");
        labThumbStatus.setBounds(66, 73, 78, 14);
        pDetails.add(labThumbStatus);
        
        lblThumbSize = new JLabel("Th. Size");
        lblThumbSize.setBounds(10, 94, 50, 14);
        pDetails.add(lblThumbSize);
        
        labThumbBytes = new JLabel("-");
        labThumbBytes.setBounds(66, 94, 174, 14);
        pDetails.add(labThumbBytes);
        
        pStatistics = new JPanel();
        TabAnalysis.addTab("Statistics", null, pStatistics, null);
        
        lblMinChannel = new JLabel("Min - Max Channel (Red)");
        lblMinChannel.setBounds(10, 36, 146, 14);
        pStatistics.setLayout(null);
        pStatistics.add(lblMinChannel);
        
        labInMinR = new JLabel("0");
        labInMinR.setBounds(166, 36, 18, 14);
        pStatistics.add(labInMinR);
        
        labInMaxR = new JLabel("0");
        labInMaxR.setBounds(222, 36, 18, 14);
        pStatistics.add(labInMaxR);
        
        lblMaxChannelr = new JLabel("Min - Max Channel (Green)");
        lblMaxChannelr.setBounds(10, 61, 146, 14);
        pStatistics.add(lblMaxChannelr);
        
        lblMinMagnitude = new JLabel("Min - Max Channel (Blue)");
        lblMinMagnitude.setBounds(10, 86, 146, 14);
        pStatistics.add(lblMinMagnitude);
        
        labInMinB = new JLabel("0");
        labInMinB.setBounds(166, 86, 18, 14);
        pStatistics.add(labInMinB);
        
        lblInputImage = new JLabel("Input");
        lblInputImage.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblInputImage.setBounds(10, 11, 119, 14);
        pStatistics.add(lblInputImage);
        
        lblElaImage = new JLabel("ELA Image");
        lblElaImage.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblElaImage.setBounds(10, 138, 119, 14);
        pStatistics.add(lblElaImage);
        
        labElaMinG = new JLabel("0");
        labElaMinG.setBounds(166, 188, 18, 14);
        pStatistics.add(labElaMinG);
        
        labElaMinR = new JLabel("0");
        labElaMinR.setBounds(166, 163, 18, 14);
        pStatistics.add(labElaMinR);
        
        labElaMaxR = new JLabel("0");
        labElaMaxR.setBounds(222, 163, 18, 14);
        pStatistics.add(labElaMaxR);
        
        labElaMaxG = new JLabel("0");
        labElaMaxG.setBounds(222, 188, 18, 14);
        pStatistics.add(labElaMaxG);
        
        label_15 = new JLabel("-");
        label_15.setHorizontalAlignment(SwingConstants.CENTER);
        label_15.setBounds(194, 86, 18, 14);
        pStatistics.add(label_15);
        
        labElaMinB = new JLabel("0");
        labElaMinB.setBounds(166, 213, 18, 14);
        pStatistics.add(labElaMinB);
        
        labElaMaxB = new JLabel("0");
        labElaMaxB.setBounds(222, 213, 18, 14);
        pStatistics.add(labElaMaxB);
        
        label_1 = new JLabel("Min - Max Magnitude");
        label_1.setBounds(10, 111, 146, 14);
        pStatistics.add(label_1);
        
        labInMinMag = new JLabel("0");
        labInMinMag.setBounds(166, 111, 18, 14);
        pStatistics.add(labInMinMag);
        
        label_11 = new JLabel("-");
        label_11.setHorizontalAlignment(SwingConstants.CENTER);
        label_11.setBounds(194, 111, 18, 14);
        pStatistics.add(label_11);
        
        labInMaxMag = new JLabel("0");
        labInMaxMag.setBounds(222, 111, 18, 14);
        pStatistics.add(labInMaxMag);
        
        labElaMinMag = new JLabel("0");
        labElaMinMag.setBounds(166, 238, 18, 14);
        pStatistics.add(labElaMinMag);
        
        label_17 = new JLabel("-");
        label_17.setHorizontalAlignment(SwingConstants.CENTER);
        label_17.setBounds(194, 238, 18, 14);
        pStatistics.add(label_17);
        
        labElaMaxMag = new JLabel("0");
        labElaMaxMag.setBounds(222, 238, 18, 14);
        pStatistics.add(labElaMaxMag);
        
        label_19 = new JLabel("Min - Max Magnitude");
        label_19.setBounds(10, 238, 119, 14);
        pStatistics.add(label_19);
        
        label = new JLabel("-");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(194, 36, 18, 14);
        pStatistics.add(label);
        
        label_20 = new JLabel("-");
        label_20.setHorizontalAlignment(SwingConstants.CENTER);
        label_20.setBounds(194, 61, 18, 14);
        pStatistics.add(label_20);
        
        label_2 = new JLabel("Min - Max Channel (Red)");
        label_2.setBounds(10, 163, 146, 14);
        pStatistics.add(label_2);
        
        label_4 = new JLabel("Min - Max Channel (Green)");
        label_4.setBounds(10, 188, 146, 14);
        pStatistics.add(label_4);
        
        label_5 = new JLabel("Min - Max Channel (Blue)");
        label_5.setBounds(10, 213, 146, 14);
        pStatistics.add(label_5);
        
        labInMaxG = new JLabel("0");
        labInMaxG.setBounds(222, 61, 18, 14);
        pStatistics.add(labInMaxG);
        
        labInMaxB = new JLabel("0");
        labInMaxB.setBounds(222, 86, 18, 14);
        pStatistics.add(labInMaxB);
        
        labInMinG = new JLabel("0");
        labInMinG.setBounds(166, 61, 18, 14);
        pStatistics.add(labInMinG);
        
        label_3 = new JLabel("-");
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        label_3.setBounds(194, 213, 18, 14);
        pStatistics.add(label_3);
        
        label_7 = new JLabel("-");
        label_7.setHorizontalAlignment(SwingConstants.CENTER);
        label_7.setBounds(194, 188, 18, 14);
        pStatistics.add(label_7);
        
        label_9 = new JLabel("-");
        label_9.setHorizontalAlignment(SwingConstants.CENTER);
        label_9.setBounds(194, 163, 18, 14);
        pStatistics.add(label_9);
        getContentPane().setLayout(layout);
        
        spMetadata = new JScrollPane();
        spMetadata.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        TabPanel.addTab("Metadata Table", null, spMetadata, null);
        
        tMetadata = new JTable(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null},
        	},
        	new String[] {
        		"Directory", "Tag Name", "Value"
        	}
          ) {
        	@Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        });
        
        tMetadata.getColumnModel().getColumn(0).setPreferredWidth(75);
        tMetadata.getColumnModel().getColumn(0).setMaxWidth(100);
        tMetadata.getColumnModel().getColumn(1).setPreferredWidth(100);
        tMetadata.getColumnModel().getColumn(1).setMaxWidth(200);
        tMetadata.getColumnModel().getColumn(1).setMinWidth(200);
        //tMetadata.getColumnModel().getColumn(2).setCellRenderer(new WordWrapCellRenderer());
        
        spMetadata.setViewportView(tMetadata);
        
        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Windows (introduced in Java SE 6) is not available, s	tay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Apps.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Apps.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Apps.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Apps.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
			public void run() {
                new Apps().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JTabbedPane TabConfig;
    private javax.swing.JTabbedPane TabPanel;
    private javax.swing.JLabel labELA;
    private javax.swing.JLabel labInput;
    private javax.swing.JLabel labMask;
    private javax.swing.JLabel labQuality;
    private javax.swing.JMenuItem mOpen;
    private javax.swing.JMenu menuFile;
    private javax.swing.JPanel pConfELA;
    private javax.swing.JSlider sliderQuality;
    private JScrollPane spMetadata;
    private JTable tMetadata;
    private JMenuItem mntmSaveEla;
    private JMenuItem mntmSaveMaskedImage;
    private JLabel labValQ;
    private JLabel labComp;
    private JLabel labErrScale;
    private JSlider sliderErrScale;
    private JLabel lblFileSize;
    private JLabel lblResolution;
    private JLabel lblPath;
    private JLabel labResoVal;
    private JLabel labFileSizeVal;
    private JLabel labFileNameVal;
    private JTextArea txtaPath;
    private JLabel labThumbBig;
    private JPanel pStatistics;
    private JLabel labFile;
    private JLabel labValE;
    private JPanel pConfMask;
    private JLabel labMaskThresh;
    private JLabel labValT;
    private JSlider sliderThresh;
    private JLabel lblMaskColor;
    private JButton btnConfMask;
    private JButton btnDefMask;
    private JComboBox comboBox;
    private JTabbedPane TabThumb;
    private JLabel labThumbSmall;
    private JLabel lblThumbnail;
    private JLabel labThumbStatus;
    private JLabel lblThumbSize;
    private JLabel labThumbBytes;
    private JLabel lblMinChannel;
    private static  JLabel labInMinR;
    private static  JLabel labInMaxR;
    private JLabel lblMaxChannelr;
    private JLabel lblMinMagnitude;
    private static  JLabel labInMinB;
    private JLabel lblInputImage;
    private JLabel lblElaImage;
    private static  JLabel labElaMinG;
    private static  JLabel labElaMinR;
    private static  JLabel labElaMaxR;
    private static  JLabel labElaMaxG;
    private JLabel label_15;
    private static  JLabel labElaMinB;
    private static  JLabel labElaMaxB;
    private JLabel label_1;
    private static  JLabel labInMinMag;
    private JLabel label_11;
    private static  JLabel labInMaxMag;
    private static  JLabel labElaMinMag;
    private JLabel label_17;
    private static  JLabel labElaMaxMag;
    private JLabel label_19;
    private JLabel label;
    private JLabel label_20;
    private JLabel label_2;
    private JLabel label_4;
    private JLabel label_5;
    private static  JLabel labInMaxG;
    private static  JLabel labInMaxB;
    private static JLabel labInMinG;
    private JLabel label_3;
    private JLabel label_7;
    private JLabel label_9;
    
    /**
     * Application Methods
     */
    void mOpenIMG(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mOpenIMGActionPerformed
        //Create a file chooser
        final JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Image Files (JPG/JPEG/PNG)", "jpg", "jpeg", "png"));
        int result = fc.showOpenDialog(MenuBar);
        if (result == JFileChooser.APPROVE_OPTION) {
        	clearTemp();
        	
            input = fc.getSelectedFile();
            
            try {
                imgInput = ImageIO.read(input);
                applyConfigELA();                
            } catch (IOException | ImageProcessingException ex) {
                Logger.getLogger(Apps.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
            	DefaultTableModel tablemodel = null;
            	metadata = ImageMetadataReader.readMetadata(input);
                writeTable(metadata, tMetadata, tablemodel);
            } catch (IOException | ImageProcessingException ex) {
                Logger.getLogger(Apps.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
				FileUtils.copyFile(input, new File("data/input."+FilenameUtils.getExtension(input.getName())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            applyMask();
            chkThumb();
            setThumb();
            if(thumbExists == true) {
            	labThumbStatus.setText("Available");
            	labThumbBytes.setText(thumbLen + " bytes");
            }else {
            	labThumbStatus.setText("Not Available");
            	labThumbBytes.setText("-");
            }
            txtaPath.setText(input.getPath());
            labFileNameVal.setText(input.getName());
            labFileSizeVal.setText(formatSize(input.length()));
            labResoVal.setText(imgInput.getWidth() + " x " + imgInput.getHeight());
            
            callGC();
        }
    }//GEN-LAST:event_mOpenIMGActionPerformed
        
    /*
     *	Application methods
     */
    public void writeTable(Metadata metadata, JTable tMetadata, DefaultTableModel tmodel) throws ImageProcessingException, IOException {
    	tmodel = (DefaultTableModel) tMetadata.getModel();
    	tmodel.setRowCount(0);
    	TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tMetadata.getModel());
        tMetadata.setRowSorter(sorter);
        
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
            	tmodel.addRow(new Object[]{directory.getName(), tag.getTagName(), tag.getDescription()});
            }
        }
    }    
    
    public void runAnalysis(float compLevel, int errScale) throws IOException, ImageProcessingException{  
    	
        imgWidth = imgInput.getWidth();        
        imgHeight = imgInput.getHeight();
        double ratio = 0;
        
        if(imgWidth > labWidth && imgWidth > imgHeight) {
        	ratio = ((double) labWidth) / ((double) imgWidth);
        	imgWidth = (int) (imgWidth * ratio);
        	imgHeight = (int) (imgHeight * ratio);
        }else       	
        if(imgHeight > labHeight){
        	ratio = ((double) labHeight) / ((double) imgHeight);
        	imgWidth = (int) (imgWidth * ratio);
        	imgHeight = (int) (imgHeight * ratio);
        }
        
        img = imgInput.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
        imgIco = new ImageIcon(img);
        labInput.setIcon(imgIco);
        
        /*
         * if(compLevel != 1) {
        	imgProcess = ImageELA.GetCompressedImage(imgInput, compLevel);
        }else {imgProcess = imgInput;}
        */
        
        imgRecomp = ImageELA.GetCompressedImage(imgInput, compLevel);
        img = imgRecomp.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
        imgIco = new ImageIcon(img);
        labComp.setIcon(imgIco);
        outputfile = new File("data/recompressed.png");
        ImageIO.write(imgRecomp, "png", outputfile);
        
        imgELA = ImageELA.GetDifferenceImage(imgInput, imgRecomp, errScale);
        img = imgELA.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
        imgIco = new ImageIcon(img);
        labELA.setIcon(imgIco);
        outputfile = new File("data/ELA.png");
        ImageIO.write(imgELA, "png", outputfile);
        
        applyMask();
        
        callGC();
    }
    
    public void applyConfigELA() throws ImageProcessingException, IOException {
    	setTitle("Digital Image Forensics Tools (Status: Processing Error Level Analysis...)");
    	labELA.setIcon(null);
    	float compLevel = (float) sliderQuality.getValue() / 100;
    	int errLevel = sliderErrScale.getValue();
    	runAnalysis(compLevel, errLevel);
    	setTitle("Digital Image Forensics Tools (Completed: "+input.getName()+")");
    }
    
    public void defaultConfigELA() {
    	setTitle("Digital Image Forensics Tools (Status: Processing Error Level Analysis...)");
    	sliderQuality.setValue(95);
    	sliderErrScale.setValue(20);
    	try {
			runAnalysis(0.95f, 20);
		} catch (ImageProcessingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	setTitle("Digital Image Forensics Tools (Completed: "+input.getName()+")");
    }
    
    public void updQVal() {
    	sliderQuality.setToolTipText(String.valueOf(sliderQuality.getValue()));   	
    	labValQ.setText(String.valueOf(sliderQuality.getValue())+"%");
    }
    
    public void updErrVal() {
    	sliderErrScale.setToolTipText(String.valueOf(sliderErrScale.getValue()));   	
    	labValE.setText(String.valueOf(sliderErrScale.getValue())+"%");
    }
    
    public void runMask(int threshold) {
    	imgMask = ImageMask.MaskImages(imgInput, imgELA, MASK_RGB, threshold);
        img = imgMask.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
        imgIco = new ImageIcon(img);
        labMask.setIcon(imgIco);
        callGC();
    }
    
    public void applyMask() {
    	setTitle("Digital Image Forensics Tools (Status: Processing Image Mask...)");
    	int threshold = sliderThresh.getValue();
    	runMask(threshold);
    	setTitle("Digital Image Forensics Tools (Completed: "+input.getName()+")");    	
    }
    
    public void defMask() {
    	setTitle("Digital Image Forensics Tools (Status: Processing Image Mask...)");
    	sliderThresh.setValue(25);
    	runMask(25);
    	setTitle("Digital Image Forensics Tools (Completed: "+input.getName()+")");
    }
    
    public void updTVal() {
    	sliderThresh.setToolTipText(String.valueOf(sliderThresh.getValue()));   	
    	labValT.setText(String.valueOf(sliderThresh.getValue()));
    }
    
    public String formatSize(long bytes) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ITALY);
        
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(0);
        String format = bytes + " Bytes";
        if (bytes / MEGA_BYTES > 0) {
            format = nf.format(bytes) + " bytes (" + nf.format((double) bytes / MEGA_BYTES) + " MB)";
        } else if (bytes / KILO_BYTES > 0) {
            format = nf.format(bytes) + " bytes (" + nf.format((double) bytes / KILO_BYTES) + " KB)";
        } else {
            format = nf.format(bytes) + " Bytes";
        }

        return format;
    }
    
    public void chkThumb() {
    	thumbExists = false;
    	
    	if(metadata.getFirstDirectoryOfType(ExifThumbnailDirectory.class) != null &&
    			metadata.getFirstDirectoryOfType(ExifThumbnailDirectory.class).getInteger(ExifThumbnailDirectory.TAG_THUMBNAIL_OFFSET) != null &&
    			metadata.getFirstDirectoryOfType(ExifThumbnailDirectory.class).getInteger(ExifThumbnailDirectory.TAG_THUMBNAIL_LENGTH) != null &&
    			metadata.getFirstDirectoryOfType(ExifThumbnailDirectory.class).getInteger(ExifThumbnailDirectory.TAG_THUMBNAIL_OFFSET) != 0 &&
    			metadata.getFirstDirectoryOfType(ExifThumbnailDirectory.class).getInteger(ExifThumbnailDirectory.TAG_THUMBNAIL_LENGTH) != 0){
    		thumbExists = true;
    		thumbWidth = metadata.getFirstDirectoryOfType(ExifThumbnailDirectory.class).getInteger(ExifThumbnailDirectory.TAG_X_RESOLUTION);
    		thumbHeight = metadata.getFirstDirectoryOfType(ExifThumbnailDirectory.class).getInteger(ExifThumbnailDirectory.TAG_Y_RESOLUTION);
    		thumbLen = metadata.getFirstDirectoryOfType(ExifThumbnailDirectory.class).getInteger(ExifThumbnailDirectory.TAG_THUMBNAIL_LENGTH);
    	}
    }
    
    public void setThumb() {
    	if(thumbExists == true) {
    		try {
				imgThumb = ImageIO.read(new File("data/thumbnail.jpg"));
				img = imgThumb.getScaledInstance(imgThumb.getWidth(), imgThumb.getHeight(), Image.SCALE_SMOOTH);
		        imgIco = new ImageIcon(img);
		        labThumbSmall.setIcon(imgIco);        
		        
		        img = imgThumb.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
		        imgIco = new ImageIcon(img);
		        labThumbBig.setIcon(imgIco);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else {
    		labThumbBig.setIcon(null);
    		labThumbSmall.setIcon(null);
    		labThumbSmall.setText("Thumbnail metadata is not exists.");
    		labThumbBig.setText("Thumbnail comparison is not available.");
    	}
    }
    
    public void clearTemp(){
    	if(outputfile != null) {
    		File dir = outputfile.getParentFile();
        	for(File file: dir.listFiles()) 
        	    if (!file.isDirectory()) 
        	        file.delete();
    	}
    }

    public static void setInputStats(int[] minparams, int[] maxparams, int[] mag) {
    	labInMinR.setText(minparams[0]+"");
    	labInMinG.setText(minparams[1]+"");
    	labInMinB.setText(minparams[2]+"");
    	
    	labInMaxR.setText(maxparams[0]+"");
    	labInMaxG.setText(maxparams[1]+"");
    	labInMaxB.setText(maxparams[2]+"");
    	
    	labInMinMag.setText(mag[0]+"");
    	labInMaxMag.setText(mag[1]+"");
    }
    
    public void callGC() {
    	System.gc();
    }
}