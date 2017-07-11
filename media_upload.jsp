<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Image</title>
</head>
<body>

	<form method="POST" enctype="multipart/form-data" id="fileUploadForm">

		<div class="row">
			<div class="col-md-6">

				<div class="form-group">
					<label class="col-sm-4 control-label">Upload Media:</label>
					<div class="col-sm-5">
					<input id="upload_img" name='upload_img' type="hidden" value="Media Uploading" />
						<input id="upload_file" type="file" name="files" />


					</div>
				</div>

			</div>
			<div class="col-md-6">
				<input type="submit" style="float: right;" value="Upload"
					id="btnSubmit" /><br /> <br /> <br /> <input type="submit"
					style="float: right; display: none;"
					value="Remove Background Image" id="btnRemove" />
			</div>
		</div>



	</form>

</body>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<script type="text/javascript">
	$(document).ready(function() {
						console.log("ready!");

						$("#btnSubmit")
								.click(
										function(event) {
											//stop submit the form, we will post it manually.
											event.preventDefault();
											// Get form
											var form = $('#fileUploadForm')[0];
											var servlet = "/sample/MediaUploadController";
											// Create an FormData object
											var data = new FormData(form);
											// If you want to add an extra field for the FormData
											data
													.append("CustomField",
															"This is some extra data, testing");
											// disabled the submit button
											$("#btnSubmit").prop("disabled",
													true);
											var upload_file = $('#upload_file')
													.val();
											var file_ext = $('#upload_file')
													.val().split('.')[1];

											console.log(file_ext);
											if (upload_file != ''
													& file_ext == 'png'
													|| file_ext == 'PNG'
													|| file_ext == 'mp4'
													|| file_ext == 'MP4'
													|| file_ext == 'gif'
													|| file_ext == 'GIF') {
												$
														.ajax({
															type : "POST",
															enctype : 'multipart/form-data',
															url : servlet,
															data : data,
															processData : false,
															contentType : false,
															cache : false,
															timeout : 600000,
															success : function(
																	data) {
																$("#result")
																		.text(
																				data);
																console
																		.log(
																				"SUCCESS : ",
																				data);
																$("#btnSubmit")
																		.prop(
																				"disabled",
																				false);
																location
																		.reload();
																alert('Upload');
															},
															error : function(e) {
																$("#result")
																		.text(
																				e.responseText);
																console
																		.log(
																				"ERROR : ",
																				e);
																$("#btnSubmit")
																		.prop(
																				"disabled",
																				false);
															}
														});
											} else {
												alert('Please upload a png');
												$("#btnSubmit").prop(
														"disabled", false);
											}
										});
					});
</script>
</html>