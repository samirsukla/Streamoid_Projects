package com.textsearch.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.textsearch.utility.GetSystemDate;

public class SendStatusReport {
	static String statusReportLocation = "target/surefire-reports/custom-emailable-report.html";
	static String[] sendCC;
	static GetSystemDate getDate;
	static final String FROM = "test-automation@streamoid.com";

	String[] sendTo = new String[] { "samir@streamoid.com" };
	String[] sendCCPass = new String[] { "naveen@streamoid.com" };
	 String[] sendCCFail = new String[] { "prathaban@streamoid.com","prashant.gupta@streamoid.com","krupa@streamoid.com",
			 "deepika@streamoid.com","naveen@streamoid.com" };

	@Test
	public void sendMail() throws IOException {

		getDate = new GetSystemDate();
		String[] currDate = getDate.getPresentDate();
		
		String todaysDate = currDate[0];
		String todaysDay = currDate[1];
		

		boolean testStatus = sendMailStatus();
		int failCount = sendFailCount();
		// System.out.println("Test Status is " + testStatus);
		if (testStatus == true && failCount == 0) {
			sendCC = sendCCPass;
		} else {
			sendCC = sendCCFail;

		}
		
		if(todaysDay.equals("Monday")) {
			sendCC = sendCCFail;
		}

		StringWriter writer = new StringWriter();
		IOUtils.copy(new FileInputStream(new File(statusReportLocation)), writer, "ISO-8859-1");
		String HTMLBODY = writer.toString();

		String SUBJECT = "Text Search Sanity Report on " + todaysDate;

		try {
			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
					.withRegion(Regions.US_EAST_1).build();

			SendEmailRequest request = new SendEmailRequest()
					.withDestination(new Destination().withToAddresses(sendTo).withCcAddresses(sendCC))
					.withMessage(new Message()
							.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(HTMLBODY)))
							.withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
					.withSource(FROM);

			client.sendEmail(request);
			System.out.println("Email sent!");
		} catch (Exception ex) {
			System.out.println("The email was not sent. Error message: " + ex.getMessage());
		}

	}

	public boolean sendMailStatus() throws IOException {
		File file = new File(statusReportLocation);
		Document doc = Jsoup.parse(file, "UTF-8");
		Element body = doc.body();
		Element table = body.select("table").get(0);
		Element tbody = table.select("tbody").get(0);
		Elements rows = tbody.select("tr");
		Element row = rows.get(1);
		Elements cols = row.select("td");
		int skippedValue = Integer.parseInt(cols.get(3).text());
		// System.out.println("No of Tests Skipped : "+skippedValue);
		int failedValue = Integer.parseInt(cols.get(4).text());
		// System.out.println("No of Tests Failed : "+failedValue);

		if (skippedValue == 0 && failedValue == 0) {
			return true;

		} else {
			return false;
		}

	}

	public int sendFailCount() throws IOException {
		int failCount = 0;
		File file = new File(statusReportLocation);
		Document doc = Jsoup.parse(file, "UTF-8");
		Element body = doc.body();
		Element table = body.select("table").get(2);
		Elements rows = table.select("tr");
		int rowCount = rows.size();
		// System.out.println(rowCount);
		for (int i = 1; i < rowCount; i++) {
			Element row = rows.get(i);
			Elements cols = row.select("td");
			int colValue = Integer.parseInt(cols.get(1).text());
			if (colValue != 0) {
				failCount++;
			}

		}
		return failCount;

	}

}
