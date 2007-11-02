/*
 * Created on 01-Oct-2007
 * Copyright (C) 2007 by Andrea Vacondio.
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
package org.pdfsam.console.business.parser.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import jcmdline.BooleanParam;
import jcmdline.FileParam;
import jcmdline.Parameter;
import jcmdline.StringParam;

import org.pdfsam.console.business.parser.handlers.interfaces.AbstractCmdHandler;
/**
 * Handler for the mix command
 * @author Andrea Vacondio
 *
 */
public class MixCmdHandler extends AbstractCmdHandler {
	
	private  final String commandDescription = "Mix of two pdf documents.";
	
	/**
	 * Options for the mix handler
	 */
	private final List mixOptions = new ArrayList(Arrays.asList(new Parameter[] {
	            new FileParam("o",
	                    "pdf output file: if it doesn't exist it's created, if it exists it must be writeable",
	                    ((FileParam.DOESNT_EXIST) | (FileParam.EXISTS & FileParam.IS_FILE & FileParam.IS_WRITEABLE)),
	                    FileParam.REQUIRED, 
	                    FileParam.SINGLE_VALUED),
	            new FileParam("f1",
	                   "first input pdf file to split",
	                   FileParam.IS_FILE & FileParam.IS_READABLE,
	                   FileParam.REQUIRED, 
	                   FileParam.SINGLE_VALUED),
	            new FileParam("f2",
	                   "second input pdf file to split",
	                   FileParam.IS_FILE & FileParam.IS_READABLE,
	                   FileParam.REQUIRED, 
	                   FileParam.SINGLE_VALUED),
	            new BooleanParam("reversefirst", "reverse first input file"),
	            new BooleanParam("reversesecond", "reverse second input file")
	    }));  
	
	/**
	 * help text for the mix handler
	 */
	private final String mixHelpText = "Mix alternate two pdf files. "+
	"You must specify '-o /home/user/out.pdf' to set the output file.\n"+
    "You must specify '-f1 /home/user/infile1.pdf' option to set the first input file.\n" +
    "You must specify '-f2 /home/user/infile2.pdf' option to set the second input file.\n" +
    "'-reversefirst' reverse the first input file.\n"+
    "'-reversesecond' reverse the second input file.\n";

	/**
	 * example text for the mix handler
	 */
	private final String mixExample = "Example: java -jar pdfsam-console-VERSION.jar -o /tmp/outfile.pdf -f1 /tmp/1.pdf -f2 /tmp/2.pdf -reversesecond mix\n";
	
	/**
	 * Arguments for the mix handler
	 */
	private final List mixArguments = new ArrayList(Arrays.asList(new Parameter[] {
            new StringParam("command",   
                    "command to execute {[mix]}",
                    new String[] { "mix" },
                    StringParam.REQUIRED),
    }));
	

	
	public Collection getArguments() {
		return mixArguments;
	}

	public String getHelpExamples() {
		return mixExample;
	}

	public String getHelpMessage() {
		return mixHelpText;
	}

	public Collection getOptions() {
		return mixOptions;
	}
	
	public String getCommandDescription() {
		return commandDescription;
	}

}
