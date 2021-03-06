/*
 * Created on 18-Oct-2007
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
package org.pdfsam.console.business.parser.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jcmdline.CmdLineHandler;
import jcmdline.HelpCmdLineHandler;
import jcmdline.Parameter;
import jcmdline.StringParam;
import jcmdline.VersionCmdLineHandler;

import org.pdfsam.console.business.ConsoleServicesFacade;
import org.pdfsam.console.business.dto.commands.AbstractParsedCommand;
import org.pdfsam.console.business.parser.handlers.interfaces.CmdHandler;
/**
 * Default handler 
 * @author Andrea Vacondio
 *
 */
public class DefaultCmdHandler implements CmdHandler {

	private VersionCmdLineHandler commandLineHandler = null;
	
	private static final String commandDescription = "merge, split, mix or encrypt pdf files.";
	
	 /**
     * The default arguments 
     */
	private final List concatArguments = new ArrayList(Arrays.asList(new Parameter[] {
            new StringParam("command",   
                    "command to execute {[concat], [split], [encrypt], [mix], [unpack]}",
                    new String[] { AbstractParsedCommand.COMMAND_CONCAT, AbstractParsedCommand.COMMAND_SPLIT, AbstractParsedCommand.COMMAND_ECRYPT, AbstractParsedCommand.COMMAND_MIX, AbstractParsedCommand.COMMAND_UNPACK },
                    StringParam.REQUIRED)
    }));
	
	/**
	 * default help text 
	 */
    private static final String helpText = COMMAND+" -h [command] for commands help. ";
    
	public Collection getArguments() {
		return concatArguments;
	}

	public String getCommandDescription() {
		return commandDescription;
	}

	public String getHelpExamples() {
		return "";
	}

	public String getHelpMessage() {
		return helpText;
	}

	public Collection getOptions() {
		return Collections.EMPTY_LIST;
	}

	public CmdLineHandler getCommandLineHandler() {
		if(commandLineHandler == null){ 
			commandLineHandler = new VersionCmdLineHandler(ConsoleServicesFacade.CREATOR,new HelpCmdLineHandler(getHelpMessage(),ConsoleServicesFacade.LICENSE,"",COMMAND,getCommandDescription(),getOptions(),getArguments()));
			commandLineHandler.setDieOnParseError(false);
		}
		return commandLineHandler;
	}

}
