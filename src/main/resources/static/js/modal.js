    var modal = null;
    
    var modalTitle = document.getElementById("modal-title");
    var modalTitleGroup = document.getElementById("modal-title-group");
    var modalIcon = document.getElementById("modal-icon");

    var closeButton = document.getElementById("closeModal");
    
    var messaggio = document.getElementById("testoModale");

    var bottoneOk = document.getElementById("okModal");
    var bottoneAnnulla = document.getElementById("annullaModal");
    var bottoneChiudi = document.getElementById("chiudiModal");
    var bottoneX = document.getElementById("closeModal");

    function openModal(type, id, messaggioModale, ok, annulla, chiudi, x, okFunction, annullaFunction, chiudiFunction) 
    {
        document.activeElement.blur();

        modal = document.getElementById(id);
        messaggio.innerHTML = messaggioModale;
        modal.style.display = "block";

        bottoneOk.onclick = function() {
            if(okFunction!=null && okFunction instanceof Function) {
                okFunction();
            }
            closeModal();
        }
        bottoneAnnulla.onclick = function() {
            if(annullaFunction!=null && annullaFunction instanceof Function) {
                annullaFunction();
            }
            closeModal();
        }
        bottoneChiudi.onclick = function() {
            if(chiudiFunction!=null && chiudiFunction instanceof Function) {
                chiudiFunction();
            }
            closeModal();
        }

        if(ok) {
            bottoneOk.style.display = "inline-block";
        } else {
            bottoneOk.style.display = "none";
        }
        if(annulla) {
            bottoneAnnulla.style.display = "inline-block";
        } else {
            bottoneAnnulla.style.display = "none";
        }
        if(chiudi) {
            bottoneChiudi.style.display = "inline-block";
        } else {
            bottoneChiudi.style.display = "none";
        }
        if(x) {
            bottoneX.style.display = "inline-block";
        } else {
            bottoneX.style.display = "none";
        }

        
        if(type=='S') { //Success
            modalTitle.innerHTML = "Eseguito";
            modalTitle.className = "text-success";
            modalTitleGroup.className = "text-success";
            modalIcon.className = "bi bi-check-square-fill";
        } else if(type=='C') { //Confirm
            modalTitle.innerHTML = "Conferma";
            modalTitle.className = "text-primary";
            modalTitleGroup.className = "text-primary";
            modalIcon.className = "bi bi-question-square-fill";
        } else if(type=='I') { //Info
            modalTitle.innerHTML = "Info";
            modalTitle.className = "text-primary";
            modalTitleGroup.className = "text-primary";
            modalIcon.className = "bi bi-info-circle-fill";
        } else if(type=='W') { //Warning
            modalTitle.innerHTML = "Attenzione";
            modalTitle.className = "text-warning";
            modalTitleGroup.className = "text-warning";
            modalIcon.className = "bi bi-exclamation-diamond-fill";
        } else if(type=='E') { //Error
            modalTitle.innerHTML = "Errore";
            modalTitle.className = "text-danger";
            modalTitleGroup.className = "text-danger";
            modalIcon.className = "bi bi-slash-circle-fill";
        } else {
            modalTitle.innerHTML = "Messaggio";
            modalTitle.className = "text-info";
            modalTitleGroup.className = "text-info";
            modalIcon.className = "bi bi-chat-fill";
        }
    }

    function closeModal() {
        modal.style.display = "none";
    }


    // When the user clicks the button, open the modal 
    //btn.onclick = function() {
    //    modal.style.display = "block";
    //}
    
    // When the user clicks on <span> (x), close the modal
    closeButton.onclick = function() {
        modal.style.display = "none";
    }
    
    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
        //modal.style.display = "none";
        }
    }