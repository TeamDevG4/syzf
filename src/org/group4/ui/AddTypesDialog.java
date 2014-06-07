/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.group4.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.group4.util.FileUtil;

/**
 *
 * @author Kam
 */
public class AddTypesDialog extends JDialog{
    
    private JButton importFromFileButton, saveButton, addButton;
    private JTextField probNumField, newType;
    private JComboBox<String> combobox;
    private JLabel numLabel, typeLabel;
    private Container container;

    public AddTypesDialog(){
    	super(MainFrame.getInstance(), true);
        initComp();
        setSize(400,116);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    private void initComp(){
        container = getContentPane();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2));
        importFromFileButton = new JButton("从文件导入");
        saveButton = new JButton("保存");
        buttonPanel.add(importFromFileButton);
        buttonPanel.add(saveButton);
        container.add(buttonPanel, BorderLayout.NORTH);
        JPanel probPanel = new JPanel();
        probPanel.setLayout(new GridLayout(2,3));
        numLabel = new JLabel("题号：");
        probNumField = new JTextField();
        addButton = new JButton("添加");
        probPanel.add(numLabel);
        probPanel.add(probNumField);
        probPanel.add(addButton);
        typeLabel = new JLabel("选择或输入类型：");
        newType = new JTextField();
        combobox = new JComboBox<String>();
        probPanel.add(typeLabel);
        probPanel.add(newType);
        probPanel.add(combobox);
        container.add(probPanel, BorderLayout.CENTER);
        setActions();
    }
    
    private void setActions(){
        final Vector<String> types = FileUtil.getAllTypes("problemType.txt");
        for(int i = 0;i < types.size();i++){
            combobox.addItem(types.get(i));
        }
        combobox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    newType.setText((String)combobox.getSelectedItem());
                }
            }
        });
        final Map<String, Vector<String> > map = new HashMap<>();
        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(newType.getText().equals("") || probNumField.getText().equals(""))return;
                if(!map.containsKey(newType.getText())){
                    map.put(newType.getText(), new Vector<String>());
                }
                map.get(newType.getText()).add(probNumField.getText());
                if(!types.contains(newType.getText())){
                    types.add(newType.getText());
                    combobox.addItem(newType.getText());
                }
                probNumField.setText("");
            } 
        });
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                FileUtil.appendProbs(map);
                AddTypesDialog.this.dispose();
            }
        });
        importFromFileButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fc = new JFileChooser();
                    fc.showOpenDialog(AddTypesDialog.this);
                    File file = fc.getSelectedFile();
                    if(file == null)return;
                    final Vector<String> importedTypes = FileUtil.getAllTypes(file.getCanonicalPath());
                    for(int i = 0;i < importedTypes.size();i++){
                        if(!map.containsKey(importedTypes.get(i))){
                            map.put(importedTypes.get(i), new Vector<String>());
                        }
                        String probs[] = FileUtil.getProbsOfType(file.getCanonicalPath(), importedTypes.get(i));
                        for(int j = 0;j < probs.length;j++){
                            if(!map.get(importedTypes.get(i)).contains(probs[j])){
                                map.get(importedTypes.get(i)).add(probs[j]);
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(AddTypesDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
