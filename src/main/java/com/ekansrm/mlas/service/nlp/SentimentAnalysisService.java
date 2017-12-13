package com.ekansrm.mlas.service.nlp;

import org.apache.xmlrpc.XmlRpcException;

public interface SentimentAnalysisService {

  String predict(String text) throws XmlRpcException;

}
