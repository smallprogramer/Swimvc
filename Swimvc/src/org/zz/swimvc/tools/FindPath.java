package org.zz.swimvc.tools;



public class FindPath {

	private String path;
	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String FindXmlPath(){
		String path = this.getClass().getClassLoader().getResource("xml").getPath();
		return path;
	}
	
	
	public String FindPath(String PathName){
		
		String path = this.getClass().getClassLoader().getResource("xml").getPath();	
	
		path = path.substring(0,path.lastIndexOf("/"));		
		path = path.substring(0, path.lastIndexOf("/")+1);
	
		PathName = PathName.replace(".", "/");
		
		String lastPath = path + PathName;
		
		return lastPath;
		
		
		
	}
	
}
