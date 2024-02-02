var config = {
	protocol: "http",
	ip: "localhost",
	port: "8080",
	appUrl: "/springboot-authentication",
	hierarchySource: ""
}

var currentLoggedUser = "";

var baseURL = config.protocol + "://" + config.ip + ":" + config.port + config.appUrl;

function checkHierarchySource(dbFunction, configFunction)
{
	var endpoint = baseURL + "/gerarchia/get-hierarchy-source";
	$.ajax({
	    url: endpoint,
	    type: 'GET',
	    success: function(responseData, status, xhr){
			var source = responseData;
			if(source=="DB"){
				if(dbFunction!=null){
					dbFunction();
				}
			} else if(source=="CONFIG") {
				if(configFunction!=null){
					configFunction();
				}
			}
	    },
	    error: function (jqXhr, textStatus, errorMessage) {
			//openModal('E','modalPopup','Errore nel recupero della modalità di acquisizione delle gerarchie',true,false,false,true,null,null,null);
			console.error('Errore nel recupero della modalità di acquisizione delle gerarchie');
	    }
	});
}

function redirectOnKeyButton(key, tymeleafTemplate) {
	window.addEventListener("keyup", (event) => {
		if (!event.isComposing && event.key === key) {
			window.location.href = baseURL + tymeleafTemplate;
		}
	});
}

function formatDate(strDateToFormat) 
{
	const dateToFormat = new Date(strDateToFormat);
	const padL = (nr, len = 2, chr = `0`) => `${nr}`.padStart(2, chr);
	var formattedDate = (
							`${
							dateToFormat.getFullYear()}-${
							padL(dateToFormat.getMonth()+1)}-${
							padL(dateToFormat.getDate())} ${
							padL(dateToFormat.getHours())}:${
							padL(dateToFormat.getMinutes())}:${
							padL(dateToFormat.getSeconds())}`
						);
	return formattedDate;
}

function getToday()
{
	var dataOggi = new Date();
	var anno = dataOggi.getFullYear();
	var mese = dataOggi.getMonth()+1;
	var giorno = dataOggi.getDate();
	if (giorno < 10) {
		giorno = '0'+giorno;
	}
	if (mese < 10) {
		mese = `0${mese}`;
	}
	var oggi = `${anno}-${mese}-${giorno}`
	return oggi;
}

function visibleHidden(id)
{
	var element = document.getElementById(id);
	var visibility = element.style.visibility;
	if(visibility == 'hidden') {
		element.style.visibility = 'visible';
	} else {
		element.style.visibility = 'hidden';
	}
	
}

function showHidePassword(view, pwd, ico) 
{
	var textField = document.getElementById(view);
	var pwdField = document.getElementById(pwd);
	var eyeIcon = document.getElementById(ico);

	if(textField.style.display == 'none') {
		textField.value = pwdField.value;
		textField.style.display = 'inline-block';
		pwdField.style.display = 'none';
		textField.onchange = function(){
			pwdField.value = textField.value;
		}
		eyeIcon.className = "bi bi-eye-slash-fill text-secondary";
	} else {
		textField.style.display = 'none';
		pwdField.style.display = 'inline-block';
		eyeIcon.className = "bi bi-eye-fill text-secondary";
	}
}

function sortByKey(array, key, asc) 
{
	return array.sort(function(a, b) {
		var x = a[key]; 
		var y = b[key];
		if(asc) {
			return ((x < y) ? -1 : ((x > y) ? 1 : 0));
		} else {
			return ((x > y) ? -1 : ((x < y) ? 1 : 0));
		}
	});
}

function resetSelect(idSelect) {
	var select = document.getElementById(idSelect);
	var options = select.options;
	for(let i in options) {
		options[i].selected = false;
	}
	return false;
}

function resetCheckboxGroup(groupName) {
	$("input:checkbox[name="+groupName+"]:checked").each(function(){
		$(this).prop("checked", false);
	});
}

function removeOptionsSelect(selectElement) {
	var i, l = selectElement.options.length - 1;
	for(i = l; i >= 0; i--) {
	   selectElement.remove(i);
	}
}

function logout() {
	var endpoint = baseURL + "/logout";
}

function getCurrentUser() {
	//var endpoint = baseURL + "/utente/get-current";
	var endpoint = baseURL + "/utente/get-full-extended-current";
	
	var headerDiv = document.getElementById("headerDiv");
	var userDiv = document.getElementById("currentUserDiv");
	var logoutDiv = document.getElementById("logoutDiv");
	var loginDiv = document.getElementById("loginDiv");

	$.ajax({
		url: endpoint,
		type: 'GET',
//		dataType: 'text',
	    contentType: "application/json",
//	    data: JSON.stringify(jEntity),
		success: function(responseData, status, xhr) {
			//console.log(responseData);
			currentLoggedUser = JSON.stringify(responseData);
			var userClassName = "card-body form-group col-md-11 bi bi-person-circle";
			var noUserClassName = "card-body form-group col-md-11";
			var logoutClassName = "card-body form-group col-md-1 bi bi-box-arrow-right text-dark"; //bi-box-arrow-right - bi-door-open-fill
			var loginClassName = "card-body form-group col-md-1 bi bi-box-arrow-in-right text-dark"; //bi-box-arrow-right - bi-door-open-fill
			if(responseData!=undefined && responseData!="anonymousUser") {
				userDiv.className = userClassName;
				logoutDiv.className = logoutClassName;
				
				let userInfo = "";
				if(responseData.nome != null) {
					userInfo = responseData.nome;
				}
				if(responseData.cognome != null) {
					userInfo += " " + responseData.cognome;
				}
				userInfo = userInfo.trim();
				
				if(userInfo=="") {
					userInfo = responseData.username;
				}
				
				userDiv.innerHTML = "<div class='d-inline' style='font-size: small;'><label style='margin: 0px;'>&nbsp;&nbsp;" + userInfo + "</label></div>";
				logoutDiv.innerHTML = "<div class='d-inline' style='font-size: small; font-weight: bold;'>&nbsp;Esci</div>";
			} else {
				//userDiv.innerHTML = "<div class='d-inline' style='font-size: small;'><label style='margin: 0px;'>&nbsp;</label></div>";
				userDiv.className = noUserClassName;
				loginDiv.className = loginClassName;
				userDiv.innerHTML = "<div class='d-inline' style='font-size: small;'><label style='margin: 0px;'>&nbsp;&nbsp;</label></div>";
				loginDiv.innerHTML = "<div class='d-inline' style='font-size: small; font-weight: bold;'>&nbsp;Accedi</div>";
			}
		},
		error: function (jqXhr, textStatus, errorMessage) {
			console.log(errorMessage);
			headerDiv.innerHTML = "<div style='padding: 0px;'>&nbsp;</div>";
		}
	});
}

function forceLogout() {
	var endpoint = baseURL + "/utente/force-logout";
	$.ajax({
		url: endpoint,
		type: 'POST',
		success: function(responseData, status, xhr) {
			alert('logged out');
		},
		error: function (jqXhr, textStatus, errorMessage) {
			alert('errore');
		}
	});
}

function replaceAll(src, toReplace, replaceWith) {
	return src.split(toReplace).join(replaceWith);
}

function getRandomArbitrary(min, max) {
	return Math.random() * (max - min) + min;
}

function getRandomInt(min, max) {
	min = Math.ceil(min);
	max = Math.floor(max);
	return Math.floor(Math.random() * (max - min + 1) + min);
}

//https://icons.getbootstrap.com/