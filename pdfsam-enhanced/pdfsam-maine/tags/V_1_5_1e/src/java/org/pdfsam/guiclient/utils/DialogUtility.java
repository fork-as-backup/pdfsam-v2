/*
 * Created on 12-Oct-2007
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
package org.pdfsam.guiclient.utils;

import java.awt.Component;

import javax.swing.JOptionPane;

import org.pdfsam.guiclient.configuration.Configuration;
import org.pdfsam.i18n.GettextResource;

/**
 * Utility to show dialogs
 * @author Andrea Vacondio
 *
 */
public class DialogUtility {

	/**
	 * Shows a yes/no/cancel dialog to ask for change the ouput directory
	 * @param comp parent component
	 * @param suggestedDir suggested directory
	 * @return the dialog return value
	 */
	public static int showConfirmOuputLocationDialog(Component comp, String suggestedDir){

		return JOptionPane.showOptionDialog(comp,
			    GettextResource.gettext(Configuration.getInstance().getI18nResourceBundle(),"Output file location is not correct")+".\n"+GettextResource.gettext(Configuration.getInstance().getI18nResourceBundle(),"Would you like to change it to")+" "+suggestedDir+" ?",
			    GettextResource.gettext(Configuration.getInstance().getI18nResourceBundle(),"Output location error"),
			    JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE,null,
			    null,
			    null);
	}
}
