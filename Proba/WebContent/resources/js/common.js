
    function start() {  
        window['progress'] = setInterval(function() {  
        	var elem = document.getElementsByClassName("ui-progressbar-label");
        	var progValue = document.getElementsByClassName("ui-progressbar-value ui-widget-header ui-corner-all");
        	prog = elem[0].childNodes[0].nodeValue;
        	value = prog
//        	alert(progValue);
        	progValue[0].style.width = prog;

  
        }, 400);  
    }  
  
    function cancel() {  
    	var elem = document.getElementsByClassName("animated");
        clearInterval(window['progress']);  
        elem.setValue(0);  
        startButton1.enable();  
    }  
 