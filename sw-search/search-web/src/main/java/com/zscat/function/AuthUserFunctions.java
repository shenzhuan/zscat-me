package com.zscat.function;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zscat.conf.JbaseFunctionPackage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("auth")
public class AuthUserFunctions implements JbaseFunctionPackage {

}
