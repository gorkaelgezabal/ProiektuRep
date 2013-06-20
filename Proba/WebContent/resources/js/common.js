
    function start() {  
        window['progress'] = setInterval(function() {  
        	var elem = document.getElementsByClassName("ui-progressbar-label");
        	var progValue = document.getElementsByClassName("ui-progressbar-value ui-widget-header ui-corner-all");
        	prog = elem[0].childNodes[0].nodeValue;
        	progValue[0].style.width = prog;
        	progValue[0].style.backgroundImage = 'url(http://www.primefaces.org/showcase/images/pbar-ani.gif)';

  
        }, 400);  
    }  
    
    function parseStart(){
    	var icon = document.getElementsByClassName("load")[0];
    	icon.style.visibility='visible';
    }
    
    function parseStop(){
    	var icon = document.getElementsByClassName("load")[0];
    	icon.style.visibility='hidden';
    }
  
    function cancel() {  
    	var elem = document.getElementsByClassName("animated");
        clearInterval(window['progress']);  
        elem.setValue(0);  
        startButton1.enable();  
    }  

    PrimeFaces.locales['eu'] = {
    	    closeText: 'Itxi',
    	    prevText: 'Aurrekoa',
    	    nextText: 'Hurrengoa',
    	    monthNames: ['Urtarrila','Otsaila', 'Martxoa', 'Apirila', 'Maiatza', 'Ekaina', 'Uztaila', 'Abuztua', 'Iraila', 'Urria', 'Azaroa', 'Abendua'],
    	    monthNamesShort: ['Urt', 'Ots', 'Mar', 'Api', 'Mai', 'Eka','Uzt','Abu','Ira','Urr','Aza','Abe'],
    	    dayNames: ['Igandea','Astelehena','Asteartea','Asteazkena','Osteguna','Ostirala','Larunabata'],
    	    dayNamesShort: ['Ig','Al', 'Ar', 'Az', 'Og', 'Ol', 'Lr'],
    	    dayNamesMin: ['Ig','Al', 'Ar', 'Az', 'Og', 'Ol', 'Lr'],
    	    weekHeader: 'Astea',
    	    firstDay: 1,
    	    isRTL: false,
    	    showMonthAfterYear: false,
    	    yearSuffix: '',
    	    timeOnlyTitle: 'Orain bakarrik',
    	    timeText: 'Denbora',
    	    hourText: 'Ordua',
    	    minuteText: 'Minutua',
    	    secondText: 'Segundoa',
    	    currentText: 'Gaurko data',
    	    ampm: false,
    	    month: 'Hilabetea',
    	    week: 'Astea',
    	    day: 'Eguna',
    	    allDayText : 'Egun osoa'
    	};