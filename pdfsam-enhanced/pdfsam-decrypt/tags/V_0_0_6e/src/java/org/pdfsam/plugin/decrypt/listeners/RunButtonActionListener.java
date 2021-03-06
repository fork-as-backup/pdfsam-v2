/*
 * Created on 12-Nov-2009
 * Copyright (C) 2009 by Andrea Vacondio.
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
package org.pdfsam.plugin.decrypt.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.pdfsam.console.business.dto.commands.AbstractParsedCommand;
import org.pdfsam.console.business.dto.commands.DecryptParsedCommand;
import org.pdfsam.console.business.dto.commands.EncryptParsedCommand;
import org.pdfsam.guiclient.commons.business.SoundPlayer;
import org.pdfsam.guiclient.commons.business.WorkExecutor;
import org.pdfsam.guiclient.commons.business.WorkThread;
import org.pdfsam.guiclient.configuration.Configuration;
import org.pdfsam.guiclient.dto.PdfSelectionTableItem;
import org.pdfsam.guiclient.dto.StringItem;
import org.pdfsam.guiclient.utils.DialogUtility;
import org.pdfsam.i18n.GettextResource;
import org.pdfsam.plugin.decrypt.GUI.DecryptMainGUI;

public class RunButtonActionListener implements ActionListener {

	private static final Logger log = Logger.getLogger(RunButtonActionListener.class.getPackage().getName());

	private DecryptMainGUI panel;

	/**
	 * @param panel
	 */
	public RunButtonActionListener(DecryptMainGUI panel) {
		super();
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		if (WorkExecutor.getInstance().getRunningThreads() > 0 || panel.getSelectionPanel().isAdding()) {
			log
					.info(GettextResource.gettext(Configuration.getInstance().getI18nResourceBundle(),
							"Please wait while all files are processed.."));
			return;
		}
		final LinkedList<String> args = new LinkedList<String>();
		try {

			PdfSelectionTableItem[] items = panel.getSelectionPanel().getTableRows();
			if (items != null && items.length >= 1) {
				
				//overwrite confirmation 
				if(panel.getOverwriteCheckbox().isSelected() && Configuration.getInstance().isAskOverwriteConfirmation()){
					int dialogRet = DialogUtility.askForOverwriteConfirmation(panel);
					if (JOptionPane.NO_OPTION == dialogRet) {
						panel.getOverwriteCheckbox().setSelected(false);
					}else if (JOptionPane.CANCEL_OPTION == dialogRet) {
						return;
					}
				}
				
				args.addAll(getInputFilesArguments(items));
				
				args.add("-" + DecryptParsedCommand.O_ARG);
				if (panel.getDestinationTextField().getText() == null || panel.getDestinationTextField().getText().length() == 0) {
					String suggestedDir = Configuration.getInstance().getDefaultWorkingDirectory();
					if (suggestedDir != null) {
						int chosenOpt = DialogUtility.showConfirmOuputLocationDialog(panel, suggestedDir);
						if (JOptionPane.YES_OPTION == chosenOpt) {
							panel.getDestinationTextField().setText(suggestedDir);
						} else if (JOptionPane.CANCEL_OPTION == chosenOpt) {
							return;
						}
					}
				}
				args.add(panel.getDestinationTextField().getText());

				if (panel.getOverwriteCheckbox().isSelected()) {
					args.add("-" + DecryptParsedCommand.OVERWRITE_ARG);
				}
				if (panel.getOutputCompressedCheck().isSelected()) {
					args.add("-" + DecryptParsedCommand.COMPRESSED_ARG);
				}

				args.add("-" + EncryptParsedCommand.P_ARG);
				args.add(panel.getOutPrefixTextField().getText());
				args.add("-" + DecryptParsedCommand.PDFVERSION_ARG);
				args.add(((StringItem) panel.getVersionCombo().getSelectedItem()).getId());

				args.add(AbstractParsedCommand.COMMAND_DECRYPT);

				String[] myStringArray = args.toArray(new String[args.size()]);
				WorkExecutor.getInstance().execute(new WorkThread(myStringArray));
			} else {
				DialogUtility.showWarningNoDocsSelected(panel, DialogUtility.AT_LEAST_ONE_DOC);
			}
		} catch (Exception ex) {
			log.error(GettextResource.gettext(Configuration.getInstance().getI18nResourceBundle(), "Error: "), ex);
			SoundPlayer.getInstance().playErrorSound();
		}

	}
	
	/**
	 * @param items
	 * @return the list of the -f arguments
	 */
	private List<String> getInputFilesArguments(PdfSelectionTableItem[] items){
		List<String> retList = new LinkedList<String>();
		for (PdfSelectionTableItem item : items) {
			retList.add("-" + DecryptParsedCommand.F_ARG);
			String f = item.getInputFile().getAbsolutePath();
			if ((item.getPassword()) != null && (item.getPassword()).length() > 0) {
				log.debug(GettextResource.gettext(Configuration.getInstance().getI18nResourceBundle(),
						"Found a password for input file."));
				f += ":" + item.getPassword();
			}
			retList.add(f);
		}
		return retList;
	}

}
