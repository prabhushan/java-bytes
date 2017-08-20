import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.TokenFilterFactory;
import org.apache.lucene.analysis.util.TokenizerFactory;

/**
 *
 * @author Niraj
 */
public final class LuceneAnalyzer {

  private LuceneAnalyzer() {}

  public static List tokenizeString(String str) throws IOException {
	  Analyzer ana = CustomAnalyzer.builder()
			   .withTokenizer("classic")
			   .addTokenFilter("trim")
			   //.addTokenFilter("lowercase")
			   //.addTokenFilter("stop", "ignoreCase", "false", "words", "stopwords.txt", "format", "wordset")
			   .build();
    List result = new ArrayList<>();
    try {
      TokenStream stream  = ana.tokenStream(null, new StringReader(str));
      stream.reset();
      while (stream.incrementToken()) {
        result.add(stream.getAttribute(CharTermAttribute.class).toString());
      }
    } catch (IOException e) {
      // not thrown b/c we're using a string reader...
      throw new RuntimeException(e);
    }
    finally{
    	ana.close();
    }
    return result;
  }
  
  public static void main(String[] args) throws IOException {
	  String[] strArr = {"TAKE 1/2 TABLET BY MOUTH ONCE A  DAY","TAKE 5.5MLS BY MOUTH TWICE A DAY FOR 10 DAYS (DISCARD UNUSED AMOUNT)","take 2 tabs -- twice @ night"};
	  
	  System.out.println(TokenizerFactory.availableTokenizers());

	  System.out.println(TokenFilterFactory.availableTokenFilters());

	  for (int i = 0; i < strArr.length; i++) {
		  
		  System.out.println(LuceneAnalyzer.tokenizeString(strArr[i]));
		 

	}
  }
  
}