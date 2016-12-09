package com.fpx.abebe.salesforce.notification;

import java.io.Console;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

import com.fpx.abebe.salesforce.SalesForceClientConnection;
import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.authentication.Credential;
import com.fpx.abebe.salesforce.database.DataAccess;

public class CLI
{
	private String propertyFileName;
	private Properties properties = new Properties(); 
	private Credential credential = new Credential();
	private ArrayList<Command> commands = new ArrayList<Command>();
	private SalesForceClientConnection clientConnection = new SalesForceClientConnection();
	private Access access = null;
	private DataAccess dataAccess = new DataAccess();
	public final Logger logger = Logger.getLogger(CLI.class);
	public String getPropertyFileName() {
		return propertyFileName;
	}

	public void setPropertyFileName(String propertyFileName) {
		this.propertyFileName = propertyFileName;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public boolean parse(String[] args) throws ParseException {
		boolean status = false;
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
				.longOpt("initialize")
				.required(false)
				.desc("initialize database.")
				.build();

		Option justEvaluateOption = Option.builder("e")
				.longOpt("just_eval")
				.required(false)
				.desc("Just evaluate the current set of Ops.")
				.build();


		Options options = new Options();
		options.addOption(helpOption);
		options.addOption(propertyFileNameOption);
		options.addOption(initializeOption);
		options.addOption(justEvaluateOption);
		CommandLineParser parser = new DefaultParser();
		CommandLine cmdLine = parser.parse(options, args);

		if (cmdLine.hasOption("help") == true) 
		{
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("Initialize", options);
		} 
		else if(cmdLine.hasOption("property"))
		{
			String p = cmdLine.getOptionValue("property");
			this.setPropertyFileName(p);
			if(cmdLine.hasOption("just_eval"))
			{
				this.getCommands().add(new EvaluateNotificationCriteria(this));
			}
			else
			{
				this.getCommands().add(new AuthenticateCommand(this));
				if(cmdLine.hasOption("initialize") == true)
				{
					this.getCommands().add(new InitializeDatabaseCommand(this));
				}
			}
			status = true;
		}
		else
		{
			logger.error("Property file is not given.");
		}
		return status;
	}

	public boolean initialize() throws IOException
	{
		boolean status = true;
		FileInputStream inputStream = new FileInputStream(this.getPropertyFileName());
		this.properties.load(inputStream);
		this.credential.setUsingProperty(this.properties);
		return status;
	}

	public boolean readUserNamePassword()
	{
		boolean status = true;
		Console console = System.console();
		char[] passwordArray = new char[32];
		if(console != null)
		{	
			System.out.print("[UserName:]");
			this.credential.setUserName(console.readLine());
			passwordArray = console.readPassword("[%s]", "Password:");
			this.credential.setPassword(new String(passwordArray));
		}
		return status;
	}

	public boolean run()
	{
		boolean status = true;
		Iterator<Command> iterator = this.commands.iterator();
		while((status == true) && (iterator.hasNext()))
		{
			Command command = iterator.next();
			status = command.execute();
		}
		return status;
	}

	public ArrayList<Command> getCommands()
	{
		return commands;
	}

	public SalesForceClientConnection getClientConnection() 
	{
		return clientConnection;
	}

	public void setClientConnection(SalesForceClientConnection clientConnection)
	{
		this.clientConnection = clientConnection;
	}

	public Access getAccess() 
	{
		return access;
	}

	public void setAccess(Access access) 
	{
		this.access = access;
	}


	public static void main(String[] args) throws ParseException, IOException
	{
		CLI cli = new CLI();
		if(cli.parse(args) == true)
		{
			if(cli.initialize() == true)
			{
				if(cli.readUserNamePassword() == true)
				{
					cli.run();
				}
			}
		}
		cli.dataAccess.getSessionFactory().close();		
	}

	public DataAccess getDataAccess() {
		return dataAccess;
	}

	public void setDataAccess(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
	}
}
