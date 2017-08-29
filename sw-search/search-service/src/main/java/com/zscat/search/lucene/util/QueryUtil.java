package com.zscat.search.lucene.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;

/**
 * Description:query util
 *
 * @author Jin
 * @create 2017-05-19
 **/
public class QueryUtil {

    private static String queryStringFilter(String query) {
        return query.replace("/", " ").replace("\\", " ");
    }


    public static Query query(String query, Analyzer analyzer, String... fields) throws ParseException {
        BooleanQuery.setMaxClauseCount(32768);
        query = queryStringFilter(query);  /*过滤非法字符*/
        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer);
        parser.setDefaultOperator(QueryParser.Operator.OR);
        return parser.parse(query);
    }

}
