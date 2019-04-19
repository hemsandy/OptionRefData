ReferenceDataService is Spring Boot RESTFul API for
retrieving AAPL OptionData stored in a CSV file and
returns the array of JSonObjects.
--------------------------------------------------------

1) Run ClientApplication.have which starts in built Apache server
   and starts the refdata service
2) Test client application for reading this data is added in equityOption project
	TestRefDataService.jav   
3) Write any client application to read the data as below   

	URL url = null;
		HttpURLConnection conn = null;
		String inline = "";
		String strURL = "http://localhost:8080/refdata/option";
		JsonObject jObj = null;
		try{
			url = new URL(strURL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			InputStream in = new BufferedInputStream(conn.getInputStream());
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while((line = reader.readLine()) != null){
				sb.append(line);
			}
			JsonParser parser = new JsonParser();
			Object obj = parser.parse(sb.toString());
			JsonArray jarray = (JsonArray)obj;
			for(int i=0; i < jarray.size(); i++){
				jObj = jarray.get(i).getAsJsonObject();
				System.out.println(jObj);
			}
		}catch(MalformedURLException e){
			
		}catch(IOException e){
			
		}