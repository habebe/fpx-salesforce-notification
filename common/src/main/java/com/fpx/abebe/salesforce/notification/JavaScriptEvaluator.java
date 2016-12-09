package com.fpx.abebe.salesforce.notification;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
@SuppressWarnings("restriction")
public class JavaScriptEvaluator 
{
	private final ScriptEngineManager manager = new ScriptEngineManager();
	private final ScriptEngine engine = manager.getEngineByName("JavaScript");
	public JavaScriptEvaluator()
	{
	}

	public Object evaluate(String expression) throws ScriptException
	{
		return engine.eval(expression);
	}

	public ScriptEngineManager getManager() {
		return manager;
	}

	public ScriptEngine getEngine() {
		return engine;
	}
}
