package com.fpx.abebe.salesforce.restapi;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.fpx.abebe.salesforce.SalesForceClientConnection;
import com.fpx.abebe.salesforce.SalesForceService;
import com.fpx.abebe.salesforce.SalesForceURL;
import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.model.PushTopic;

public class RestService extends SalesForceService
{
	private Access access;
	public RestService(SalesForceClientConnection clientConnection,Access access)
	{
		super(clientConnection);
		this.setAccess(access);
	}

	public boolean post(PushTopic object) throws ClientProtocolException, IOException
	{
		String url = SalesForceURL.getSObjectURL(this.getAccess(),"PushTopic");
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(access.getOAuthHeader());
		JSONObject jsonObject = object.toJsonObject();
		StringEntity entity = new StringEntity(jsonObject.toString(0));
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		HttpResponse response = this.getClientConnection().getClient().execute(httpPost);
		boolean status = (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED);
		if(!status)
		{
			String message = EntityUtils.toString(response.getEntity());
			int code = response.getStatusLine().getStatusCode();
			httpPost.releaseConnection();
			throw new RuntimeException(
					String.format("Post Exception Code:%d Message:%s",
							code,message
							)
					);
		}
		else
			httpPost.releaseConnection();
		return status;
	}

	/*

	 public static void createAttachment() throws IOException {
                File f = new File("I:/CodeMenia/WorkSpace/CaptivateLog.txt");
                    InputStream is = new FileInputStream(f);
                    byte[] inbuff = new byte[(int)f.length()];        
                    is.read(inbuff);
                   String base64String = Base64.encodeBase64String(inbuff);

                System.out.println("\n_______________ Attachment INSERT _______________");

                String uri = baseUri + "/sobjects/Attachment/";
                try {

                    //create the JSON object containing the new Attachment details.
                    JSONObject Attachment = new JSONObject();
                    Attachment.put("Name", Name);
                    Attachment.put("Body", base64String);
                    Attachment.put("IsPrivate", "false");
                    Attachment.put("ParentId", "a069000000sldHB");

                    System.out.println("JSON for Attachment record to be inserted:\n" + Attachment.toString(1));

                    //Construct the objects needed for the request
                    HttpClient httpClient = HttpClientBuilder.create().build();

                    HttpPost httpPost = new HttpPost(uri);
                    httpPost.addHeader(oauthHeader);
                    httpPost.addHeader(prettyPrintHeader);
                    // The message we are going to post
                    StringEntity body = new StringEntity(Attachment.toString(1));
                    body.setContentType("application/json");
                    httpPost.setEntity(body);

                    //Make the request
                    HttpResponse response = httpClient.execute(httpPost);

                    //Process the results
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 201) {
                        String response_string = EntityUtils.toString(response.getEntity());
                        JSONObject json = new JSONObject(response_string);
                        // Store the retrieved Attachment id to use when we update the Attachment.
                        AttachmentId = json.getString("id");
                        System.out.println("New Attachment id from response: " + AttachmentId);
                    } else {
                        System.out.println("Insertion unsuccessful. Status code returned is " + response.getStatusLine().getReasonPhrase());
                    }
                } catch (JSONException e) {
                    System.out.println("Issue creating JSON or processing results");
                    e.printStackTrace();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (NullPointerException npe) {
                    npe.printStackTrace();
                }
            }

	 */


	public Access getAccess() {
		return access;
	}

	public void setAccess(Access access) {
		this.access = access;
	}
}
