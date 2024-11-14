//Java program to demonstrate keyPressed, 
// keyReleased and keyTyped method 
import java.awt.*; 
import java.awt.event.*; 

public class KeyListenerExample extends Frame implements KeyListener { 

	private TextField textField; 
	private Label displayLabel; 

	// Constructor 
	public KeyListenerExample() { 
		// Set frame properties 
		setTitle("Typed Text Display"); 
		setSize(400, 200); 
		setLayout(new FlowLayout()); 

		// Create and add a TextField for text input 
		textField = new TextField(20); 
		textField.addKeyListener(this); 
		add(textField); 

		// Create and add a Label to display typed text 
		displayLabel = new Label("Typed Text: "); 
		add(displayLabel); 

		// Ensure the frame can receive key events 
		setFocusable(true); 
		setFocusTraversalKeysEnabled(false); 
		
		// Make the frame visible 
		setVisible(true); 
	} 

	// Implement the keyPressed method 
	@Override
	public void keyPressed(KeyEvent e) { 
		int keyCode = e.getKeyCode(); 
		System.out.println("Key Pressed: " + KeyEvent.getKeyText(keyCode)); 
	} 

	// Implement the keyReleased method 
	@Override
	public void keyReleased(KeyEvent e) { 
		int keyCode = e.getKeyCode(); 
		System.out.println("Key Released: " + KeyEvent.getKeyText(keyCode)); 
	} 

	// Implement the keyTyped method 
	@Override
	public void keyTyped(KeyEvent e) { 
		char keyChar = e.getKeyChar(); 
		System.out.println("Key Typed: " + keyChar); 
		displayLabel.setText("Typed Text: " + textField.getText() + keyChar); 
	} 

	public static void main(String[] args) { 
		new KeyListenerExample(); 
	} 
} 
