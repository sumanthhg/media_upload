
import java.io.IOException;
import java.nio.file.attribute.PosixFilePermission;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

@WebServlet("/MediaUploadController")
public class MediaUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MediaUploadController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String upload_img = "";

		if (ServletFileUpload.isMultipartContent(request)) {

			MediaUploadServices mediaUploadServices = new MediaUploadServices();
			StringBuffer out = new StringBuffer();
			Set<PosixFilePermission> perms = mediaUploadServices.getPermissions();
			try {
				List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(new ServletRequestContext(request));
				for (FileItem item : items) {

					if (item.getFieldName().equalsIgnoreCase("upload_img")) {
						upload_img = item.getString();
						System.err.println("----------------->"+upload_img);
					}
				}
				/*
				 * if (services.checkLessonFolderExists(lesson)) {
				 * 
				 * } else { services.createLessonFolder(lesson); }
				 */
				UUID uui = UUID.randomUUID();
				boolean flag = true;
				String uuID = uui.toString();
				for (FileItem item : items) {

					if (!item.isFormField()) {
						System.err.println(item.getName());

						System.err.println(uuID);
						System.err.println(item.getName());

						if (item.getName().toLowerCase().endsWith(".PNG".toLowerCase())) {
							out = mediaUploadServices.writeToFileForSlide(uuID, item, perms, ".png");
							System.err.println(out.toString());
						}
						if (item.getName().toLowerCase().endsWith(".GIF".toLowerCase())) {
							out = mediaUploadServices.writeToFileForSlide(uuID, item, perms, ".gif");
						}
						if (item.getName().toLowerCase().endsWith(".MP4".toLowerCase())) {
							out = mediaUploadServices.writeToFileForSlide(uuID, item, perms, ".mp4");
						}

					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
				// TODO: handle exception
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
