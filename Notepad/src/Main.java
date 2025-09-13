import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        //Main window
        JFrame frame = new JFrame("Notepad");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the text area
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane);

        // Create the menu bar
        JMenuBar menu_bar = new JMenuBar();
        JMenu file_menu = new JMenu("File");
        JMenu edit_menu = new JMenu("Edit") ;
        JMenu help_menu = new JMenu("Help");

        // Create menu items
        JMenuItem open_item = new JMenuItem("Open");
        JMenuItem save_item = new JMenuItem("Save");
        JMenuItem exit_item = new JMenuItem("Exit");
        JMenuItem cut_item = new JMenuItem("Cut");
        JMenuItem copy_item = new JMenuItem("Copy");
        JMenuItem paste_item = new JMenuItem("Paste");
        JMenuItem about = new JMenuItem("About");

        // Add menu items

        //file
        file_menu.add(open_item);
        file_menu.add(save_item);
        file_menu.add(exit_item);
        menu_bar.add(file_menu);

        //edit
        edit_menu.add(cut_item);
        edit_menu.add(copy_item);
        edit_menu.add(paste_item);
        menu_bar.add(edit_menu);

        //about
        menu_bar.add(help_menu);
        help_menu.add(about);


        frame.setJMenuBar(menu_bar);

        //Copy
        copy_item.addActionListener(e -> textArea.copy());

        //paste
        paste_item.addActionListener(e -> textArea.paste());

        //cut
        cut_item.addActionListener(e -> textArea.cut());

        //About with dialog
        about.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                    "Name: Sachin Vignesh \n" +
                            "ID: 16763"
            );
        });



        // File chooser for open/save
        JFileChooser fileChooser = new JFileChooser();

        // Open file
        open_item.addActionListener(e -> {
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    textArea.read(reader, null);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error opening file");
                }
            }
        });


        // Save file
        save_item.addActionListener(e -> {
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    textArea.write(writer);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving file");
                }
            }
        });

        // Exit
        exit_item.addActionListener(e -> frame.dispose());

        // Show the window
        frame.setVisible(true);
    }
}