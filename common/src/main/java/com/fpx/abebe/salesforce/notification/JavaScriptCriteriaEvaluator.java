package com.fpx.abebe.salesforce.notification;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.script.ScriptException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpx.abebe.salesforce.model.NotificationCriteria;
import com.fpx.abebe.salesforce.model.Opportunity;
import com.fpx.abebe.salesforce.model.User;

@SuppressWarnings("restriction")
public class JavaScriptCriteriaEvaluator 
{
	private NotificationCriteria criteria;
	private JavaScriptEvaluator evaluator;
	private static String expressionEvaluationContent;
	private final static String expressionEvaluationFilename = "ExpressionEvaluation.js";
	static 
	{
		JavaScriptCriteriaEvaluator.loadExpressionEvaluationContent();
	}

	private static void loadExpressionEvaluationContent()
	{
		try
		{
			ClassLoader classLoader = JavaScriptCriteriaEvaluator.class.getClassLoader();
			InputStream stream = classLoader.getResourceAsStream(expressionEvaluationFilename);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
			String line;
			line = buffer.readLine();
			setExpressionEvaluationContent("");
			while(line != null)
			{
				setExpressionEvaluationContent(getExpressionEvaluationContent() + line + "\n");
				line = buffer.readLine();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			setExpressionEvaluationContent(null);
		}
		System.out.println("ExpressionEvalContent:" + getExpressionEvaluationContent());
	}

	public JavaScriptCriteriaEvaluator(NotificationCriteria criteria,JavaScriptEvaluator evaluator)
	{
		this.setCriteria(criteria);
		this.setEvaluator(evaluator);
	}

	public NotificationCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(NotificationCriteria criteria) {
		this.criteria = criteria;
	}


	public EvaluatorResult evaluate(Opportunity opportunity,User owner)
	{
		EvaluatorResult result = new EvaluatorResult();
		ObjectMapper mapper = new ObjectMapper();
		try {
			if(expressionEvaluationContent != null)
			{
				this.evaluator.evaluate(expressionEvaluationContent);
			}
			this.evaluator.evaluate(String.format("var Opportunity=%s;",mapper.writeValueAsString(opportunity)));
			this.evaluator.evaluate(String.format("var Owner=%s;",mapper.writeValueAsString(owner)));

			Object evaluationResult = this.evaluator.evaluate(this.getCriteria().getExpression());
			if(evaluationResult != null)
			{
				if(evaluationResult instanceof Boolean)
				{
					Boolean booleanResult = (Boolean)evaluationResult;
					result.setMessage(null);
					if(booleanResult)
					{
						result.setStatus(EvaluatorResultStatus.Match);
						result.setMessage("Match");
					}
					else
						result.setStatus(EvaluatorResultStatus.NoMatch);
				}
				else
				{
					result.setMessage("Criteria Expression '" + this.getCriteria().getExpression() + "' does not evaluate to a boolean value.");
					result.setStatus(EvaluatorResultStatus.ErrorInExpressionEvaluation);
				}
			}
			else
			{
				result.setMessage("Criteria Expression '" + this.getCriteria().getExpression() + "' evaluates to null.");
				result.setStatus(EvaluatorResultStatus.ErrorInExpressionEvaluation);

			}
		} 
		catch (JsonProcessingException e) 
		{
			result.setStatus(EvaluatorResultStatus.ErrorInExpressionEvaluation);
			result.setMessage("Object Error:" + e.getCause().getMessage());
		} 
		catch (ScriptException e) 
		{
			result.setStatus(EvaluatorResultStatus.ErrorInExpressionEvaluation);
			result.setMessage(e.getCause().getMessage());
		}
		return result;
	}

	public JavaScriptEvaluator getEvaluator() 
	{
		return evaluator;
	}

	public void setEvaluator(JavaScriptEvaluator evaluator) {
		this.evaluator = evaluator;
	}

	public static String getExpressionEvaluationContent() {
		return expressionEvaluationContent;
	}

	public static void setExpressionEvaluationContent(String expressionEvaluationContent) {
		JavaScriptCriteriaEvaluator.expressionEvaluationContent = expressionEvaluationContent;
	}
}
