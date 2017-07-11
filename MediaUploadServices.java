import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

import org.apache.tomcat.util.http.fileupload.FileItem;

public class MediaUploadServices {
	
	public Set<PosixFilePermission> getPermissions () {
		Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
		// add owners permission
		perms.add(PosixFilePermission.OWNER_READ);
		perms.add(PosixFilePermission.OWNER_WRITE);
		perms.add(PosixFilePermission.OWNER_EXECUTE);
		// add group permissions
		perms.add(PosixFilePermission.GROUP_READ);
		perms.add(PosixFilePermission.GROUP_WRITE);
		perms.add(PosixFilePermission.GROUP_EXECUTE);
		// add others permissions
		perms.add(PosixFilePermission.OTHERS_READ);
		perms.add(PosixFilePermission.OTHERS_WRITE);
		perms.add(PosixFilePermission.OTHERS_EXECUTE);
		return perms;
	}
	
	public StringBuffer writeToFileForSlide(String uuID, FileItem item, Set<PosixFilePermission> perms,  String extension) throws Exception {
		StringBuffer out = new StringBuffer();
		String filePath = "C:\\Users\\Divith-iSTAR\\Desktop\\New folder\\";
		File file = new File(filePath, uuID + extension);
		System.err.println(file.getAbsolutePath());
		
		file.setExecutable(true, false);
		file.setReadable(true, false);
		file.setWritable(true, false);
		file.createNewFile();
		item.write(file);
		//need to give permission in linux os to write file.
		/*if(mediaUploadServices.getAnyPath("server_type").equalsIgnoreCase("linux")){
			Files.setPosixFilePermissions(file.toPath(), perms);
		}*/
		System.out.println("UPLOADED" + item.getContentType());
		System.err.println(file.getAbsolutePath());
		out.append(""+filePath+"" + file.getName());
		System.out.println("inside>>>>>>>>> "+out.toString());
		return out;
	}

}
