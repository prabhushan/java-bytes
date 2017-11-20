package com.prabhu.searcher;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;

// RC-26942
public class LowerCaseAnalyzer extends Analyzer {
	private Version version;

	public LowerCaseAnalyzer(Version version) {
		this.version = version;
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		Tokenizer source = new StandardTokenizer();
		  TokenStream filter = new LowerCaseFilter(source);
		  return new TokenStreamComponents(source, filter);
	}

}