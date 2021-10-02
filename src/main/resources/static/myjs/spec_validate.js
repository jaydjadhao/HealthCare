$(document).ready(function() {
	//1. Hide Error Section
	$("#specCodeError").hide();
	$("#specNameError").hide();
	$("#specNoteError").hide();

	//2. Define Error Variable
	var specCodeError = false;
	var specNameError = false;
	var specNoteError = false;

	//3. Validate Function

	function validate_specCode() {
		var val = $("#specCode").val();
		var exp = /^[A-Z]{4,10}$/;
		if (val == '') {
			$("#specCodeError").show();
			$("#specCodeError").html("*<b>Code</b> should not empty!");
			$("#specCodeError").css('color', 'red');
			specCodeError = false;
		} else if (!exp.test(val)) {
			$("#specCodeError").show();
			$("#specCodeError").html("*<b>Code</b> must be 4-12 chars only!");
			$("#specCodeError").css('color', 'red');
			specCodeError = false;
		} else{
			var id = 0; //for register
			if($("#id").val() != undefined){ //for Edit Page
				specCodeError = true;
				id = $("#id").val();
			}
			$.ajax({
				url:'checkcode',
				data:{"code":val, "id": id},
				success:function(resTxt){
					if(resTxt!=''){
						$("#specCodeError").show();
						$("#specCodeError").html(resTxt);
						$("#specCodeError").css('color','red');
						specCodeError = false;
					}else{
						$("#specCodeError").hide();
						specCodeError = true;
					}
				}
			});
		}
		return specCodeError;
	}
	
	//4. Action Event
	$("#specCode").keyup(function(){
		$(this).val($(this).val().toUpperCase());
		validate_specCode();
	});
	
	//5. On Submit
	$("#specForm").submit(function(){
		validate_specCode();
		if(specCodeError) return true;
		else return false;
	});
});