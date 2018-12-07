package com.services.utilities;

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
import com.services.utilities.GetSystemDate;

public class SendStatusReportTarget {
	static String statusReportLocation = "target/surefire-reports/emailable-report.html";
	static String[] sendCC;
	static GetSystemDate getDate;
	static final String FROM = "test-automation@streamoid.com";
	String[] sendTo = new String[] {"samir@streamoid.com", "naveen@streamoid.com", "murtaza.ali@streamoid.com"};
	String[] sendCCPass = new String[] { "kinshuk@streamoid.com", "hemang@streamoid.com" };
	String[] sendCCFail = new String[] { "sar@streamoid.com", "prathaban@streamoid.com","kinshuk@streamoid.com", "hemang@streamoid.com"};

	@Test
	public void sendMail() throws IOException {

		getDate = new GetSystemDate();
		String todaysDate = getDate.getPresentDate();
		boolean testStatus = sendMailStatus();
		if( testStatus == true){
			sendCC =sendCCPass;
		}
		else {
			sendCC = sendCCFail;
		
		}
		StringWriter writer = new StringWriter();
		IOUtils.copy(new FileInputStream(new File(statusReportLocation)), writer, "ISO-8859-1");
		String HTMLBODY = writer.toString();

		String SUBJECT = "Similar & Outfitter Service Status Report - Target on " + todaysDate;

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
		Elements rows = table.select("tr");
		Element row = rows.get(4);
		Elements cols = row.select("th");
		int skippedValue = Integer.parseInt(cols.get(2).text());
		//System.out.println("No of Tests Skipped : "+skippedValue);
		int failedValue = Integer.parseInt(cols.get(3).text());
		//System.out.println("No of Tests Failed : "+failedValue);
		
		if(skippedValue == 0 && failedValue == 0) {
			return true;
			
		}
		else {
			return false;
		}
		
		
		
	}

}
