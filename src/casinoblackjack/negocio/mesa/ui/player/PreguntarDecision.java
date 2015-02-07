
	package casinoblackjack.negocio.mesa.ui.player;

	/*
	 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
	 *
	 * Redistribution and use in source and binary forms, with or without
	 * modification, are permitted provided that the following conditions
	 * are met:
	 *
	 *   - Redistributions of source code must retain the above copyright
	 *     notice, this list of conditions and the following disclaimer.
	 *
	 *   - Redistributions in binary form must reproduce the above copyright
	 *     notice, this list of conditions and the following disclaimer in the
	 *     documentation and/or other materials provided with the distribution.
	 *
	 *   - Neither the name of Oracle or the names of its
	 *     contributors may be used to endorse or promote products derived
	 *     from this software without specific prior written permission.
	 *
	 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
	 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
	 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
	 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
	 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
	 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
	 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
	 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
	 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
	 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
	 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
	 */
	 

	import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import casinoblackjack.negocio.jugador.Decision;

	import java.beans.*; //property change stuff
import java.awt.*;
import java.awt.event.*;
	 
	/* 1.4 example used by DialogDemo.java. */
	class PreguntarDecision extends JDialog
	                   implements ActionListener,
	                              PropertyChangeListener {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Decision decision = null;
	    private JComboBox comboBox;
	 
	    private JOptionPane optionPane;
	 
	    private String btnString1 = "Enter";
	    private String btnString2 = "Cancel";
	 
	    /**
	     * Returns null if the typed string was invalid;
	     * otherwise, returns the string as the user entered it.
	     */
	    public Decision getValidatedDecision() {
	        return decision;
	    }
	 
	    /** Creates the reusable dialog. */
	    public PreguntarDecision(Frame aFrame, Decision decisionSugerida) {
	    	
	        super(aFrame, true);
	        setLookAndFeel();
	        setTitle("Apostar");
	 
	        comboBox = new JComboBox();
	        comboBox.setModel(new javax.swing.DefaultComboBoxModel(new Decision[] { Decision.HIT, Decision.DOUBLE,Decision.SPLIT, Decision.STAND, Decision.SURRENDER }));
	        
	        //Create an array of the text and components to be displayed.
	        String msgString1 = "Decide que hacer con tus cartas:";
	        String msgString2 = "(La decision sugerida por tu estrategia es " + decisionSugerida
	                              + ")";
	        Object[] array = {msgString1, msgString2, comboBox};
	 
	        //Create an array specifying the number of dialog buttons
	        //and their text.
	        Object[] options = {btnString1, btnString2};
	 
	        //Create the JOptionPane.
	        optionPane = new JOptionPane(array,
	                                    JOptionPane.QUESTION_MESSAGE,
	                                    JOptionPane.YES_NO_OPTION,
	                                    null,
	                                    options,
	                                    options[0]);
	 
	        //Make this dialog display it.
	        setContentPane(optionPane);
	 
	        //Handle window closing correctly.
	        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	        addWindowListener(new WindowAdapter() {
	                public void windowClosing(WindowEvent we) {
	                /*
	                 * Instead of directly closing the window,
	                 * we're going to change the JOptionPane's
	                 * value property.
	                 */
	                    optionPane.setValue(new Integer(
	                                        JOptionPane.CLOSED_OPTION));
	            }
	        });
	 
	        //Ensure the text field always gets the first focus.
	        addComponentListener(new ComponentAdapter() {
	            public void componentShown(ComponentEvent ce) {
	                comboBox.requestFocusInWindow();
	            }
	        });
	 
	        //Register an event handler that puts the text into the option pane.
	        //comboBox.addComponentListener(this);
	 
	        //Register an event handler that reacts to option pane state changes.
	        optionPane.addPropertyChangeListener(this);
	    }
	 
	    private void setLookAndFeel() {
	    	try {
				UIManager.setLookAndFeel(
				        UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/** This method handles events for the text field. */
	    public void actionPerformed(ActionEvent e) {
	        optionPane.setValue(btnString1);
	    }
	 
	    /** This method reacts to state changes in the option pane. */
	    public void propertyChange(PropertyChangeEvent e) {
	        String prop = e.getPropertyName();
	 
	        if (isVisible()
	         && (e.getSource() == optionPane)
	         && (JOptionPane.VALUE_PROPERTY.equals(prop) ||
	             JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
	            Object value = optionPane.getValue();
	 
	            if (value == JOptionPane.UNINITIALIZED_VALUE) {
	                //ignore reset
	                return;
	            }
	 
	            //Reset the JOptionPane's value.
	            //If you don't do this, then if the user
	            //presses the same button next time, no
	            //property change event will be fired.
	            optionPane.setValue(
	                    JOptionPane.UNINITIALIZED_VALUE);
	 
	            if (btnString1.equals(value)) {
	                    decision = (Decision) comboBox.getSelectedItem();
	                if (decision != null) {
	                    //we're done; clear and dismiss the dialog
	                    clearAndHide();
	                } else {
	                    //text was invalid
	                    JOptionPane.showMessageDialog(
	                    		PreguntarDecision.this,
	                                    "Tuenes que apostar para poder jugar",
	                                    "Prueba otra vez",
	                                    JOptionPane.ERROR_MESSAGE);
	                    decision = Decision.STAND;
	                    comboBox.requestFocusInWindow();
	                }
	            } else { //user closed dialog or clicked cancel
//	                dd.setLabel("It's OK.  "
//	                         + "We won't force you to type "
//	                         + apuestaSugerida + ".");
	            	decision = Decision.STAND;
	                clearAndHide();
	            }
	        }
	    }
	 
	    /** This method clears the dialog and hides it. */
	    public void clearAndHide() {
	        //spunenr.setText(null);
	        setVisible(false);
	    }
	}

