/*
 * Created on 04-Jul-2006
 * Copyright (C) 2006 by Andrea Vacondio.
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License as published by the Free Software Foundation; 
 * either version 2 of the License.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program; 
 * if not, write to the Free Software Foundation, Inc., 
 *  59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package it.pdfsam.plugin.coverfooter.listener;


import it.pdfsam.panels.LogPanel;
import it.pdfsam.plugin.coverfooter.GUI.CoverFooterMainGUI;
import it.pdfsam.plugin.coverfooter.component.JCoverFooterTable;
import it.pdfsam.plugin.coverfooter.model.CoverFooterTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Listener class used by remove_button
 * 
 * @author Andrea Vacondio
 * @see it.pdfsam.plugin.coverfooter.GUI.CoverFooterMainGUI
 */
public class RemoveActionListener implements ActionListener{
    /**
     * reference to the merge table
     */
    private JCoverFooterTable merge_table;
    /**
     * reference to the main GUI
     */
    private CoverFooterMainGUI main_gui;

    public RemoveActionListener(JCoverFooterTable merge_table, CoverFooterMainGUI main_gui) {
        super();
        this.main_gui = main_gui;
        this.merge_table = merge_table;
        
    }
    
    /**
     * if the button is pressed it removes items from the table
     */
    public void actionPerformed(ActionEvent e) {
        if ((main_gui == null) || (merge_table == null)) return;
        int[] selected_row = merge_table.getSelectedRows();
        if ( selected_row != null){
            if ( selected_row.length > 0){
                try{
                    ((CoverFooterTableModel) merge_table.getModel()).deleteRows(selected_row);
                }
                catch (IndexOutOfBoundsException ioobe){
                    main_gui.fireLogPropertyChanged("Error: "+ioobe.getMessage(), LogPanel.LOG_ERROR); 
                }
            }
        }
        
    }

}
