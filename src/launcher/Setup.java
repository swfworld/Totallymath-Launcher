package launcher;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import javax.swing.*;

import launcher.DownloadAgent;
import launcher.ZipExtractor;
import launcher.Main;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Setup {
	private JTextField panel1txt;
	private JTextField introtxt;
	private JTextField textField;
	public void startPane(JFrame frame) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel.add(toolBar, BorderLayout.SOUTH);
		
		Component glue = Box.createGlue();
		toolBar.add(glue);
		
		JButton btnNext = new JButton("Next");
		toolBar.add(btnNext);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.exit(1);
		    }
		});
		toolBar.add(btnCancel);
		
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_2);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut);
		
		introtxt=new JTextField();
		introtxt.setEditable(false);
		introtxt.setText("Welcome to the JavaTM Setup. Click next to continue.");
		introtxt.setHorizontalAlignment(JTextField.CENTER);
		panel.add(introtxt, BorderLayout.CENTER);
		introtxt.setColumns(10);
		btnNext.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        frame.getContentPane().remove(panel);
		        licensePane(frame);
		        frame.pack();
		    }
		});
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.pack();
		}

	public void licensePane(JFrame frame) {
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		JButton btnNext = new JButton("Agree and Continue");
		btnNext.setEnabled(false);
		JButton btnNewButton = new JButton("Read License");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try { 
					String url = "https://www.gnu.org/licenses/gpl-3.0.md";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				}
				catch (java.io.IOException e) {
					System.out.println(e.getMessage());
					System.exit(1);
				}
				btnNext.setEnabled(true);
			}
		});
		
		btnNext.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        frame.getContentPane().remove(panel);
		        installPane(frame);
		        frame.pack();
		    }
		});
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel.add(toolBar, BorderLayout.SOUTH);
		
		Component glue = Box.createGlue();
		toolBar.add(glue);
		
		toolBar.add(btnNext);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.exit(1);
		    }
		});
		toolBar.add(btnCancel);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_2);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut);
		
		JEditorPane maintxt = new JTextPane();
		maintxt.setEditable(false);
		
		maintxt.setText("This software is licensed under the GNU GPL v3 License and is for educational purposes ONLY."+System.getProperty("line.separator")+"Read the License to continue");
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(toolBar, BorderLayout.SOUTH);
		panel.add(btnNewButton, BorderLayout.EAST);
		panel.add(maintxt, BorderLayout.CENTER);
		btnCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.exit(1);
		    }
		});
		frame.pack();
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void installPane(JFrame frame) {
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		JButton btnNext = new JButton("Continue");
		btnNext.setEnabled(false);
		
		btnNext.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        frame.getContentPane().remove(panel);
		        installPane(frame);
		        frame.pack();
		    }
		});
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel.add(toolBar, BorderLayout.SOUTH);
		
		Component glue = Box.createGlue();
		toolBar.add(glue);
		
		toolBar.add(btnNext);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.exit(1);
		    }
		});
		toolBar.add(btnCancel);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_2);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(toolBar, BorderLayout.SOUTH);
		
		JLayeredPane layeredPane = new JLayeredPane();
		panel.add(layeredPane, BorderLayout.CENTER);
		btnCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        boolean exist=createDirectories(textField.getText());
		        if(exist==true) {
		        	finishPane(frame);
		        	frame.pack();
		        }
		        else{
		        	System.exit(1);
		        }
		    }
		});
		
		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane, BorderLayout.NORTH);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		JButton btnNewButton_1 = new JButton("Install");
		splitPane.setRightComponent(btnNewButton_1);
		
		JToolBar toolBar_1 = new JToolBar();
		splitPane.setLeftComponent(toolBar_1);
		
		JLabel lblEnterActivationKey = new JLabel("Enter Activation Key:");
		toolBar_1.add(lblEnterActivationKey);
		
		textField = new JTextField();
		toolBar_1.add(textField);
		textField.setColumns(10);
		btnCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.exit(1);
		    }
		});
		frame.pack();
	}
	public void finishPane(JFrame frame) {
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		JButton btnNext = new JButton("Exit and Launch JavaTM");
		
		btnNext.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
					Main.restartApplication();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
					System.exit(1);
				}
		    }
		});
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel.add(toolBar, BorderLayout.SOUTH);
		
		Component glue = Box.createGlue();
		toolBar.add(glue);
		
		toolBar.add(btnNext);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_1);
		
		JButton btnCancel = new JButton("Exit");
		btnCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.exit(1);
		    }
		});
		toolBar.add(btnCancel);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_2);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut);
		
		JEditorPane maintxt = new JTextPane();
		maintxt.setEditable(false);
		
		maintxt.setText("Install Finished.");
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(toolBar, BorderLayout.SOUTH);
		panel.add(maintxt, BorderLayout.CENTER);
		btnCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.exit(1);
		    }
		});
		frame.pack();
	}
	public Setup(){
		JFrame frame=new JFrame();
		frame.setTitle("JavaTM Setup");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1280, 720));
		frame.getContentPane().setLayout(new BorderLayout(0,0));
		frame.setVisible(true);
		startPane(frame);
	}
	public static boolean createDirectories(String activationKey){
		File working = new File(Main.dir);
		File zip = new File(Main.dir+"latest.zip");
		File isInstalled = new File(Main.dir+"isInstalled");
		File key=new File(Main.dir+"ac.key");
		if(!working.exists()) {
			working.mkdir();
			try {
				String url = "http://totallymath.gq/UNM/dataInf.php?key="+activationKey+"&file=Launcher.jar";
				DownloadAgent.downloadUsingNIO(url, Main.dir+"Launcher.jar");
				DownloadAgent.downloadUsingStream(url, Main.dir+"Launcher.jar");
				try {
					key.createNewFile();
					FileOutputStream write= new FileOutputStream(key);
					byte[] byteKey = activationKey.getBytes();
					write.write(byteKey);
					write.close();
					Main.runcommand("java -jar "+Main.dir+"Launcher.jar");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				System.out.println("Activation Failed.");
				System.exit(1);
				}
		}
		if(!isInstalled.exists()){
			if(!zip.exists()) {
				try {
					DownloadAgent.downloadUsingStream(Main.updateURL, Main.dir+"latest.zip");
					DownloadAgent.downloadUsingNIO(Main.updateURL, Main.dir+"latest.zip");
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
			try {
				ZipExtractor.unzip(Main.dir+"unpack.zip", Main.dir);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			try {
				isInstalled.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	public static void main(String args[]) {
		new Setup();
	}
}
