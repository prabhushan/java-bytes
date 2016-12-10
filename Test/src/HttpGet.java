import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGet {

	private static final String FILE_PATH = "filePath";
	private static final String FILE_PROCESSING_STATUS = "fileProcessingStatus";
	private static final String REPORT_TYPE = "reportType";
	private static final String JOB_ID = "loaderJobId";

	public static void main(String[] args) throws Exception {
		if (args != null && args.length < 5) {
			System.out.println("Missing parameters. Expected 5, Received " + args.length);
			return;
		}
		String queryParameters = constructQueryParams(args[1], args[2], args[3], args[4]);
		sendGet(args[0], queryParameters);
	}

	private static String constructQueryParams(String filePath, String fileProcessingStatus, String reportType, String jobId) {
		StringBuilder payload = new StringBuilder();
		payload.append(FILE_PATH).append("=").append(filePath).append("&");
		payload.append(FILE_PROCESSING_STATUS).append("=").append(fileProcessingStatus).append("&");
		payload.append(REPORT_TYPE).append("=").append(reportType).append("&");
		payload.append(JOB_ID).append("=").append(jobId);
		return payload.toString();
	}

	private static void sendGet(String url, String queryParameters) throws Exception {

		URL obj = new URL(url + "?" + queryParameters);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add request header
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("GET parameters : " + queryParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println("Response: " + response.toString());

	}
}
