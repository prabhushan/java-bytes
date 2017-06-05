package com.drfirst.email.emailTest;

	import javax.script.ScriptEngine;
	import javax.script.ScriptEngineFactory;
	import javax.script.ScriptEngineManager;
	import javax.script.ScriptException;

import org.jruby.RubyHash;

import javax.script.ScriptContext;
	import java.io.BufferedReader;
	import java.io.FileNotFoundException;
	import java.io.FileReader;

	public class JRubyExample1 {

	    public static void main(String[] args) throws ScriptException, FileNotFoundException {
	        //list all available scripting engines
	        listScriptingEngines();
	        //get jruby engine
	        ScriptEngine jruby = new ScriptEngineManager().getEngineByName("jruby");
	        //process a ruby file
	        RubyHash obj = (RubyHash)jruby.eval(new BufferedReader(new FileReader("test.rb")));

	        System.out.println(obj.toString());
	        //call a method defined in the ruby source
	        jruby.put("number", 6);
	        jruby.put("title", "My Swing App");

	    }

	    public static void listScriptingEngines() {
	        ScriptEngineManager mgr = new ScriptEngineManager();
	        for (ScriptEngineFactory factory : mgr.getEngineFactories()) {
	            System.out.println("ScriptEngineFactory Info");
	            System.out.printf("\tScript Engine: %s (%s)\n", factory.getEngineName(), factory.getEngineVersion());
	            System.out.printf("\tLanguage: %s (%s)\n", factory.getLanguageName(), factory.getLanguageVersion());
	            for (String name : factory.getNames()) {
	                System.out.printf("\tEngine Alias: %s\n", name);
	            }
	        }
	    }
	
}
