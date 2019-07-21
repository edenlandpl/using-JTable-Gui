/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 *
 * @author adrian
 */
public class HelpInfo extends JDialog {
    private static final long serialVersionUID = 1L;
	private JEditorPane editorPane = null;
	private URL opisUrl = null;
	
	/**
	 * Konstruktor bezparametrowy klasy <code>HelpWindow</code>
	 */
	public HelpInfo() {
		this.setTitle("Pomoc - aplikacja testowa");
		this.setModal(false);
		this.setResizable(true);
		this.setSize(800,600);
	
		this.addWindowListener	(new WindowAdapter(){ // obsluga zdarzenia okna
			public void windowClosing(WindowEvent e){  // obsluga wcisniecia przycisku zamkniecia okna
				setVisible(false);				// ukrycie obiektu
			}	// koniec metody windowClosing
		});	// koniec obslugi zdarzenia okna
		
		// pobranie rozmiarow aplikacji
		Dimension dialogSize = getSize();		
		// pobranie rozdzielczosci pulpitu
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  
		if(dialogSize.height > screenSize.height) 
			dialogSize.height = screenSize.height;
		if(dialogSize.width > screenSize.width)
			dialogSize.height = screenSize.width;
			
		// rozmieszczenie aplikacji na srodku ekranu
		setLocation((screenSize.width-dialogSize.width)/2,   
						(screenSize.height-dialogSize.height)/2);
		
		this.setLayout(new BorderLayout());

		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		//opisUrl = NewClass.class.getResource(
				//"/pomoc/index.html");
		
		// Zaladowanie strony opisUrl do edytora editorPane
		setURLPage();
		
		// obsluga zdarzenia hyperlink
		editorPane.addHyperlinkListener(new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent event) {
				if(event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					try {
						editorPane.setPage(event.getURL());
					}
					catch(IOException e) {
						editorPane.setText("Exception: "+e);
					}
				}
			}
		}); 
	
		this.add(new JScrollPane(editorPane), BorderLayout.CENTER);
	}
	/** 
	 *	Prywatna metoda pobierajaca strone opisowa w formacie html
	 */
	private void setURLPage() {
		try {
			File file = new File("C:\\Projekty\\Java\\Projekty\\Gui\\pomoc\\index.html");
			editorPane.setPage(file.toURI().toURL());
		}
		catch(Exception e) {
			editorPane.setText("Nie mozna otworzy plikow z pomoca " + opisUrl);
		} 
	}
}
