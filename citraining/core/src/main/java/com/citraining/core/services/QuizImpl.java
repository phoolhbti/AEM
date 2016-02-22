package com.citraining.core.services;

import java.util.ArrayList;
import java.util.HashMap;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Value;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component (immediate=true)
@Service
public class QuizImpl implements Quiz {
	
	@Reference
	  private ResourceResolverFactory resolverFactory;
	  String resourcePath = null;
	  private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	  
	  @Override
		public String getOptions(String url) {
		  try
		    {
		      this.resourcePath = url;
		      ResourceResolver resourceResolver = this.resolverFactory
		        .getAdministrativeResourceResolver(null);
		      Resource res = resourceResolver.getResource(this.resourcePath);
		      ValueMap readMap = res.getValueMap();
		      HashMap<String, Boolean> markFalse = new HashMap();
		       
		 
		      String[] JSONDataValues = { "checkAnswerText", 
		        "preventUnansweredText", "questionCountText", 
		        "nextQuestionText", "numberOfQuestions", 
		        "randomSortQuestions", "randomSortAnswers", 
		        "preventUnanswered", "perQuestionResponseMessaging", 
		        "completionResponseMessaging", "displayQuestionCount" };
		       
		 
		      markFalse.put("randomSortQuestions", Boolean.valueOf(false));
		      markFalse.put("randomSortAnswers", Boolean.valueOf(false));
		      markFalse.put("preventUnanswered", Boolean.valueOf(false));
		      markFalse.put("perQuestionResponseMessaging", Boolean.valueOf(true));
		      markFalse.put("completionResponseMessaging", Boolean.valueOf(false));
		      markFalse.put("displayQuestionCount", Boolean.valueOf(true));
		       
		      JSONObject options = new JSONObject();
		      ArrayList<String> tempData = new ArrayList();
		      LOGGER.info("Inside Option{}", JSONDataValues);
		      String value;
		      for (int i = 0; i < JSONDataValues.length; i++) {
		        if (readMap.containsKey(JSONDataValues[i]))
		        {
		          tempData.add(JSONDataValues[i]);
		          value = readMap.get(JSONDataValues[i]).toString();
		          LOGGER.info("Value:" + value);
		          if (!value.equals(null)) {
		            if (value.equals("true")) {
		              options.put(JSONDataValues[i], true);
		            } else {
		              options.put(JSONDataValues[i], value);
		            }
		          }
		        }
		      }
		      LOGGER.info("Map:" + markFalse);
		      LOGGER.info("ArrayList:" + tempData);
		      for (java.util.Map.Entry<String, Boolean> entry : markFalse.entrySet())
		      {
		        LOGGER.info("Checking:+" + (String)entry.getKey());
		        if (!tempData.contains(entry.getKey()))
		        {
		          LOGGER.info("Putting Key :" + (String)entry.getKey());
		          if (!((Boolean)entry.getValue()).booleanValue()) {
		            options.put((String)entry.getKey(), entry.getValue());
		          } else {
		            options.put((String)entry.getKey(), false);
		          }
		        }
		      }
		      LOGGER.info("Final Data Options" + options.toString());
		       
		      return options.toString();
		    }
		    catch (Exception e)
		    {
		      LOGGER.info("Exception " + e);
		    }
			return null;
		}
	@Override
	public String getData(String url) {
		LOGGER.info("Getting started");
	    try
	    {
	      this.resourcePath = url;
	      ResourceResolver resourceResolver = this.resolverFactory
	        .getAdministrativeResourceResolver(null);
	      Resource res = resourceResolver.getResource(this.resourcePath);
	      ValueMap readMap = res.getValueMap();
	      LOGGER.info("Data " + readMap.get("displayText"));
	      JSONObject info = new JSONObject();
	       
	      JSONArray questionsArray = new JSONArray();
	      JSONArray quesoptions = new JSONArray();
	      JSONObject finalJson = new JSONObject();
	      JSONObject ques = new JSONObject();
	      JSONObject quesop1 = new JSONObject();
	       
	      info.put("name", readMap.get("nameofquiz"));
	      info.put("main", readMap.get("displayText"));
	      info.put("results", readMap.get("nameofquiz"));
	      info.put("level1", readMap.get("level1"));
	      info.put("level2", readMap.get("level2"));
	      info.put("level3", readMap.get("level3"));
	      info.put("level4", readMap.get("level4"));
	      info.put("level5", readMap.get("level5"));
	      LOGGER.info("Info Data: " + info);
	       
	      LOGGER.info("Q Data: " + readMap.get("questionData"));
	       
	 
	      Node node = (Node)res.adaptTo(Node.class);
	      ArrayList<String> list = new ArrayList();
	      Property prop = node.getProperty("questionData");
	      if (prop.isMultiple())
	      {
	        Value[] values = prop.getValues();
	        for (Value value : values) {
	          list.add(value.getString());
	        }
	      }
	      else
	      {
	        list.add(prop.getString());
	      }
	      for (int i = 0; i < list.size(); i++)
	      {
	        ques = new JSONObject();
	        quesoptions = new JSONArray();
	        String text = (String)list.get(i);
	        LOGGER.info("Text:" + text);
	        String[] splitText = text.split("\\|");
	         
	        ques.put("q", splitText[0]);
	        LOGGER.info("q:" + splitText[0]);
	        String[] allOptions = splitText[1].split("\\,");
	        for (int j = 0; j < allOptions.length; j++)
	        {
	          quesop1 = new JSONObject();
	          String[] optionValue = allOptions[j].split("\\~");
	          quesop1.put("option", optionValue[0]);
	          if (optionValue[1].equals("true")) {
	            quesop1.put("correct", true);
	          } else {
	            quesop1.put("correct", false);
	          }
	          quesoptions.put(quesop1);
	        }
	        LOGGER.info("Options: " + quesoptions);
	        ques.put("a", quesoptions);
	        String correct = "<p><span>" + splitText[2] + "</span>" + 
	          splitText[3] + "</p>";
	        String incorrect = "<p><span>" + splitText[4] + "</span>" + 
	          splitText[5] + "</p>";
	        ques.put("correct", correct);
	        ques.put("incorrect", incorrect);
	         
	        questionsArray.put(ques);
	      }
	      finalJson.put("info", info);
	       
	      finalJson.put("questions", questionsArray);
	       
	       
	      LOGGER.info("Final JSON: " + finalJson);
	       
	      return finalJson.toString();
	    }
	    catch (Exception e)
	    {
	      LOGGER.info("Exception :" + e.getMessage());
	    }
		return null;
	}

	protected void bindResolverFactory(ResourceResolverFactory paramResourceResolverFactory)
	  {
	    this.resolverFactory = paramResourceResolverFactory;
	  }
	   
	  protected void unbindResolverFactory(ResourceResolverFactory paramResourceResolverFactory)
	  {
	    if (this.resolverFactory == paramResourceResolverFactory) {
	      this.resolverFactory = null;
	    }
	  }

}
