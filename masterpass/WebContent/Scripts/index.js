// Index page specific js functions 
$(function(){
	$("#shippingSuppressionErrorMessage").hide();
	$("#rewardsProgramErrorMessage").hide();
	$("#privateLabelErrorMessage").hide();
	$('#shippingProfileErrorMessage').hide();
	$('#authLevelErrorMessage').hide();
	$('#walletSelectorErrorMessage').hide();
	
	xmlVersionDropdown = document.getElementById('xmlVersionDropdown');
	if(xmlVersionDropdown){
		xmlVersionDropdown.onchange = function(){
			var xmlVersionNumber = $("#xmlVersionDropdown").val();
			
			xmlVersion(xmlVersionNumber);
		}
	};
	
$(function(){
	shippingSuppressionDropdown = document.getElementById('shippingSuppressionDropdown');
	
	if(shippingSuppressionDropdown){
		shippingSuppressionDropdown.onchange = function(){
			var shippingSuppressionValue = $("#shippingSuppressionDropdown").val();
			
			shippingSuppression(shippingSuppressionValue);
		};
	}
});

function shippingSuppression(shipping){
	
	switch(shipping){
		case 'true':
			$('#shippingProfileDropdown').attr('disabled', 'disabled');
			break;
		case 'false':
			$('#shippingProfileDropdown').removeAttr('disabled', 'disabled');
			break;
		
	}
}	

});

function xmlVersion(xmlVersionNumber){
	
	switch(xmlVersionNumber){
		case 'v1':
			/* Error Messages */
			$("#shippingSuppressionErrorMessage").show();
			$("#rewardsProgramErrorMessage").show();
			$("#privateLabelErrorMessage").show();			
			$('#shippingProfileErrorMessage').show();
			$('#authLevelErrorMessage').show();
			$('#walletSelectorErrorMessage').show();
			
			/* Disable redirect options */
			$('#shippingSuppressionDropdown').attr('disabled', 'disabled');
			$('#rewardsDropdown').attr('disabled', 'disabled');
			$('#privateLabelText').attr('disabled', 'disabled');
			$('#shippingProfileDropdown').attr('disabled', 'disabled');
			$('#authenticationCheckBox').attr('disabled', 'disabled');
			$('#walletSelectorDropdown').attr('disabled', 'disabled');
			break;
		case 'v2':
			/* Error Messages */
			$("#shippingSuppressionErrorMessage").hide();
			$("#rewardsProgramErrorMessage").show();
			$("#privateLabelErrorMessage").show();
			$('#shippingProfileErrorMessage').show();
			$('#authLevelErrorMessage').show();
			$('#walletSelectorErrorMessage').show();
			
			/* Disable redirect options */
			$('#shippingSuppressionDropdown').removeAttr('disabled', 'disabled');
			$('#rewardsDropdown').attr('disabled', 'disabled');
			$('#privateLabelText').attr('disabled', 'disabled');
			$('#shippingProfileDropdown').attr('disabled', 'disabled');
			$('#authenticationCheckBox').attr('disabled', 'disabled');
			$('#walletSelectorDropdown').attr('disabled', 'disabled');
			break;
		case 'v3':
			/* Error Messages */
			$("#shippingSuppressionErrorMessage").hide();
			$("#rewardsProgramErrorMessage").show();
			$("#privateLabelErrorMessage").hide();
			$('#shippingProfileErrorMessage').show();
			$('#authLevelErrorMessage').hide();
			$('#walletSelectorErrorMessage').show();
			
			/* Disable redirect options */
			$('#shippingSuppressionDropdown').removeAttr('disabled', 'disabled');
			$('#rewardsDropdown').attr('disabled', 'disabled');
			$('#privateLabelText').removeAttr('disabled', 'disabled');
			$('#shippingProfileDropdown').attr('disabled', 'disabled');
			$('#authenticationCheckBox').removeAttr('disabled', 'disabled');
			$('#walletSelectorDropdown').attr('disabled', 'disabled');
			break;
		case 'v4':
			/* Error Messages */
			$("#shippingSuppressionErrorMessage").hide();
			$("#rewardsProgramErrorMessage").hide();
			$("#privateLabelErrorMessage").hide();
			$('#shippingProfileErrorMessage').hide();
			$('#authLevelErrorMessage').hide();
			$('#walletSelectorErrorMessage').show();
			
			/* Disable redirect options */
			$('#shippingSuppressionDropdown').removeAttr('disabled', 'disabled');
			$('#rewardsDropdown').removeAttr('disabled', 'disabled');
			$('#privateLabelText').removeAttr('disabled', 'disabled');
			$('#shippingProfileDropdown').removeAttr('disabled', 'disabled');
			$('#authenticationCheckBox').removeAttr('disabled', 'disabled');
			$('#walletSelectorDropdown').attr('disabled', 'disabled');
			break;
		case 'v5':
			/* Error Messages */
			$("#shippingSuppressionErrorMessage").hide();
			$("#rewardsProgramErrorMessage").hide();
			$("#privateLabelErrorMessage").hide();
			$('#shippingProfileErrorMessage').hide();
			$('#authLevelErrorMessage').hide();
			$('#walletSelectorErrorMessage').hide();
			
			/* Disable redirect options */
			$('#shippingSuppressionDropdown').removeAttr('disabled', 'disabled');
			$('#rewardsDropdown').removeAttr('disabled', 'disabled');
			$('#privateLabelText').removeAttr('disabled', 'disabled');
			$('#shippingProfileDropdown').removeAttr('disabled', 'disabled');
			$('#authentication').removeAttr('disabled', 'disabled');
			$('#walletSelectorDropdown').removeAttr('disabled', 'disabled');
			break;
	}
};