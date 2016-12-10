package com.fpx.abebe.salesforce.dataservice;

import java.io.Console;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CLI
{
	private DataService dataService = new DataService();

	public boolean parse(String[] args) throws ParseException 
	{
		boolean status = true;
		Option helpOption = Option.builder("h")
				.longOpt("help")
				.required(false)
				.desc("shows this message")
				.build();

		Option propertyFileNameOption = Option.builder("p")
				.longOpt("property")
				.required(false)
				.desc("property file name.")
				.hasArg()
				.build();

		Option initializeOption = Option.builder("i")
				.longOpt("init_db")
				.required(false)
				.desc("initialize database.")
				.build();


		Options options = new Options();
		options.addOption(helpOption);
		options.addOption(propertyFileNameOption);
		options.addOption(initializeOption);
		CommandLineParser parser = new DefaultParser();
		CommandLine cmdLine = parser.parse(options, args);

		if (cmdLine.hasOption("help") == true) 
		{
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("Initialize", options);
			status = false;
		} 
		else
		{
			this.dataService.setInitializeDb(cmdLine.hasOption("init_db"));
			if(cmdLine.hasOption("property"))
			{
				String p = cmdLine.getOptionValue("property");
				this.dataService.setPropertyFileName(p);
			}
			else
			{
				dataService.getLogger().error("Property file is not given.");
				status = false;
			}
		}
		return status;
	}

	public boolean readUserName()
	{
		boolean status = false;
		Console console = System.console();
		if(console != null)
		{	
			this.dataService.getCredential().setUserName(console.readLine("[%s]","UserName"));
			status = true;
		}
		return status;
	}

	public boolean readPassword()
	{
		boolean status = false;
		Console console = System.console();
		char[] passwordArray = new char[32];
		if(console != null)
		{	
			passwordArray = console.readPassword("[%s]", "Password");
			this.dataService.getCredential().setPassword(new String(passwordArray));
			status = true;
		}
		return status;
	}

	public boolean initialize()
	{
		boolean status = false;

		try
		{
			status = this.dataService.initialize();	
			if(dataService.getCredential().getUserName() == null)
				status = this.readUserName();
			if(status && (dataService.getCredential().getPassword() == null))
				status = this.readPassword();
		}
		catch(Exception e)
		{
			this.dataService.getLogger().error("Error during initialization:" + e.getMessage());
		}
		return status;
	}

	public static void main(String[] args) throws ParseException, IOException, InterruptedException, java.text.ParseException
	{
		
		CLI cli = new CLI();
		if(cli.parse(args) && cli.initialize())
		{
			Thread thread = new Thread(cli.dataService,"Data Service");
			thread.start();
			thread.join();
			cli.dataService.cleanup();
		}
	}
}
