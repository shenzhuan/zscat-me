package com.zscat.search.lucene.util;

import com.zscat.search.model.IndexObject;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.*;
import org.apache.lucene.search.highlight.Highlighter;
import java.io.StringReader;

/**
 * Description:sss
 *
 * @author Jin
 * @create 2017-05-18
 **/
public class DocumentUtil {

    public static Document IndexObject2Document(IndexObject indexObject) {
        Document doc = new Document();
        doc.add(new StoredField("id", indexObject.getId()));
        doc.add(new TextField("title",indexObject.getTitle(), Field.Store.YES));
        doc.add(new TextField("summary",indexObject.getKeywords(), Field.Store.YES));
        doc.add(new TextField("descripton",indexObject.getDescripton(), Field.Store.YES));
        doc.add(new StoredField("postDate", indexObject.getPostDate()));
        doc.add(new StoredField("url", indexObject.getUrl()));
        return doc;  
    }  
  
    public static  IndexObject document2IndexObject(Analyzer analyzer, Highlighter highlighter, Document doc,float score) throws Exception {
        IndexObject indexObject = new IndexObject();
        indexObject.setId(Long.parseLong(doc.get("id")));
        indexObject.setTitle(stringFormatHighlighterOut(analyzer, highlighter,doc,"title"));
        indexObject.setKeywords(stringFormatHighlighterOut(analyzer, highlighter,doc,"summary"));
        indexObject.setDescripton(stringFormatHighlighterOut(analyzer, highlighter,doc,"descripton"));
        indexObject.setPostDate(doc.get("postDate"));
        indexObject.setUrl(doc.get("url"));
        indexObject.setScore(score);
        return indexObject;
    }


    /*关键字加亮*/
    private static String stringFormatHighlighterOut(Analyzer analyzer, Highlighter highlighter, Document document, String field) throws Exception{
        String fieldValue = document.get(field);
        if(fieldValue!=null){
            TokenStream tokenStream=analyzer.tokenStream(field, new StringReader(fieldValue));
            return highlighter.getBestFragment(tokenStream, fieldValue);
        }
        return null;
    }
}