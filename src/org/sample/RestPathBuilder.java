package org.sample;

import java.util.LinkedHashMap;
import java.util.Map;

public class RestPathBuilder {
	
	private String contextPath="";
	private String basePath= "";
	private String datasetRid="";
	private  String branchId="";
	private  String methodPath="";
	private String mimeType="";
	private  Map<String,String> requestParams = new LinkedHashMap<String, String>();
	private Map<String,String> requestHeaders = new LinkedHashMap<String, String>();

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getDatasetRid() {
		return datasetRid;
	}

	public void setDatasetRid(String datasetRid) {
		this.datasetRid = datasetRid;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getMethodPath() {
		return methodPath;
	}

	public void setMethodPath(String methodPath) {
		this.methodPath = methodPath;
	}

	public Map<String, String> getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(Map<String, String> requestParams) {
		this.requestParams = requestParams;
	}

	public Map<String, String> getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(Map<String, String> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}
	
	public String buildUrl(){
		String url ="";
		url = contextPath+basePath+methodPath;
		if(requestParams!=null && requestParams.size()>0){
			int i=0;
			for(Map.Entry<String, String> param: getRequestParams().entrySet()){
				if(i==0){
					url+="?";
				}else{
					url+="&";
				}
				url+=param.getKey()+"="+param.getValue();
				i++;
			}
		}
		return url;
	}
	
	public static RestPathBuilder preparePathDataUrl(){
		RestPathBuilder builder = new RestPathBuilder();
		builder.setContextPath("https://xyz.com");
		builder.setBasePath("/api/");
		builder.setDatasetRid("b2e38837ffo9d");
		builder.setBranchId("branch");
		builder.setMethodPath("/method1/method2/"+ builder.getDatasetRid()+"/branches/"+builder.getBranchId()+"/xyz");

		Map<String,String> requestParams = new LinkedHashMap<String, String>();
		requestParams.put("includeColumnNames", "true");
		
		Map<String,String> requestHeaders = new LinkedHashMap<String, String>();
		requestHeaders.put("authHeader", "<token u need to replace>");
		builder.setRequestParams(requestParams);
		builder.setRequestHeaders(requestHeaders);
		builder.setMimeType("application/pdf");
		
		return builder;
	}

	public static RestPathBuilder dummmyPathDataUrl(){
		String myfile="sample.csv";// this u need not to have bcz you will get from url
		RestPathBuilder builder = new RestPathBuilder();
		builder.setContextPath("http://localhost:9090");
		builder.setBasePath("/cdn");
		builder.setDatasetRid("156");
		builder.setBranchId("master");
		builder.setMethodPath("/dataproxy/datasets/"+ builder.getDatasetRid()+"/branches/"+builder.getBranchId()+"/csv/"+myfile);

		Map<String,String> requestParams = new LinkedHashMap<String, String>();
		requestParams.put("includeColumnNames", "true");
		
		Map<String,String> requestHeaders = new LinkedHashMap<String, String>();
		requestHeaders.put("authHeader", "<token u need to replace>");
		builder.setRequestParams(requestParams);
		builder.setRequestHeaders(requestHeaders);
		builder.setMimeType("application/pdf");
		
		return builder;
	}


	
	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	

}
