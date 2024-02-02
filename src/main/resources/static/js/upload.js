//const avatar = document.getElementById("avatar");
//const textArea = document.getElementById("textAreaExample");

/*
const uploadImage = async (event) => {
    const file = event.target.files[0];
    const base64 = await convertBase64(file);
    //avatar.src = base64;
    //textArea.innerText = base64;
    uploadFile(file.name, base64);
};

input.addEventListener("change", (e) => {
    uploadImage(e);
});
*/

var maxFileSize = 14649000;

const convertBase64 = (file) => {
    return new Promise((resolve, reject) => {
        const fileReader = new FileReader();
        fileReader.readAsDataURL(file);

        fileReader.onload = () => {
            resolve(fileReader.result);
        };

        fileReader.onerror = (error) => {
            reject(error);
        };
    });
};

function postUpload(fileName, fileContent, input)
{
    var endpoint = baseURL + "/upload/base64";

    var jEntity = {
                fileName: fileName,
                base64Content: fileContent
        };

    $.ajax({
        url: endpoint,
        type: 'POST',
        dataType: 'text',
        contentType: "application/json",
        data: JSON.stringify(jEntity),
        success: function(responseData, status, xhr){
			    //console.log(responseData);
                openModal('S','modalPopup','File caricato correttamente',true,false,false,true,null,null,null);
                input.value = '';
        },
        error: function (jqXhr, textStatus, errorMessage) {
                //console.log(errorMessage);
                openModal('E','modalPopup','Errore nel caricamento del file o file troppo grande<br>(Dimensione massima: 14.649Mb)',true,false,false,true,null,null,null);
        }
    });
}

const uploadFile = async (idFileInput, idDescriptionDiv) => {
    const input = document.getElementById(idFileInput);
    if(input.files.length > 0) {
        const file = input.files.item(0);
        const base64 = await convertBase64(file);
        var fileSize = file.size;
        if(fileSize > maxFileSize) {
            openModal('W','modalPopup','Il file è troppo grande per poter essere caricato.<br>(Dimensione massima: 14.649Mb)',true,false,false,true,null,null,null);
            input.value = '';
        } else {
			//avatar.src = base64;
			//textArea.innerText = base64;
            postUpload(file.name, base64, input);
        }
    } else {
        openModal('W','modalPopup','Selezionare il file da caricare',true,false,false,true,null,null,null);
    }
    
    if(idDescriptionDiv!=null) {
        var descriptionDiv = document.getElementById(idDescriptionDiv);
        descriptionDiv.innerHTML = "";
    }
}

function showFileSize() {
    var input = document.getElementById("fileInput");
    var file = input.files[0];
    var fileDetails = document.getElementById("fileDetails");
    if(file!=null && file!==undefined) {
        if(file.size > maxFileSize) {
            fileDetails.classList.add('text-danger');
        } else if(fileDetails.classList.contains('text-danger')) {
            fileDetails.classList.remove('text-danger');
        }
        fileDetails.innerText = "Dimensione file: " + file.size.toLocaleString() + " bytes";
    } else {
        fileDetails.innerText = "";
    }
}