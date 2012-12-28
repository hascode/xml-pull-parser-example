package com.hascode.tutorial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class ParserExample {
	private static final String urlString = "https://jira.atlassian.com/sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml?jqlQuery=&tempMax=20";
	private static final String ITEM_TITLE = "title";
	private static final String ITEM_LINK = "link";

	public static void main(final String[] args) throws XmlPullParserException,
			MalformedURLException, IOException {
		XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
		URL url = new URL(urlString);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream()));
		try {
			xpp.setInput(in);
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				xpp.next();
				if (ITEM_TITLE.equals(xpp.getName())) {
					System.out.print("Issue: " + xpp.nextText());
				}
				if (ITEM_LINK.equals(xpp.getName())) {
					System.out.print(" at " + xpp.nextText() + "\n");
				}
			}
		} finally {
			in.close();
		}
	}
}
