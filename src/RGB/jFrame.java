/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RGB;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

/**
 *
 * @author oziel
 */
public class jFrame extends JFrame{
        JPanel panel = new JPanel();
        JSlider blue = new JSlider();
        JSlider red = new JSlider();
        JSlider green = new JSlider();
        JLabel texto1 = new JLabel();
        JLabel texto2 = new JLabel();
        JLabel texto3 = new JLabel();
        JButton boton = new JButton();
    public jFrame(){
        setSize(400,400);
        inicializarComponentes();
        setVisible(true);
    }
    public void inicializarComponentes(){
        Color color = new Color(red.getValue(),green.getValue(),blue.getValue());
        panel.setBackground(color);
        blue.setMajorTickSpacing(100);
        blue.setMaximum(255);
        blue.setToolTipText("");
        blue.setValue(125);
        red.setMaximum(255);
        red.setToolTipText("");
        red.setValue(125);
        green.setMaximum(255);
        green.setToolTipText("");
        green.setValue(125);
        panel.setBounds(20, 20, 360, 180);
        blue.setBounds(50, 200, 200, 50);
        red.setBounds(50, 250, 200, 50);
        green.setBounds(50, 300, 200, 50);
        blue.addChangeListener(new ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                blueStateChanged(evt);
            }
        });
        red.addChangeListener(new ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                redStateChanged(evt);
            }
        });
        green.addChangeListener(new ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                greenStateChanged(evt);
            }
        });
        boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(panel);
        add(blue);
        add(red);
        add(green);
        add(texto1);
        add(texto2);
        add(texto3);
    }
    private void blueStateChanged(javax.swing.event.ChangeEvent evt) {                                  
        Color color = new Color(red.getValue(),green.getValue(),blue.getValue());
        panel.setBackground(color);
        add(panel);
    }                                 

    private void redStateChanged(javax.swing.event.ChangeEvent evt) {                                 
        Color color = new Color(red.getValue(),green.getValue(),blue.getValue());
        panel.setBackground(color);
    }                                

    private void greenStateChanged(javax.swing.event.ChangeEvent evt) {                                   
        Color color = new Color(red.getValue(),green.getValue(),blue.getValue());
        panel.setBackground(color);
    }                                  

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        red.setValue(0);
        blue.setValue(0);
        green.setValue(0);
    } 
}
