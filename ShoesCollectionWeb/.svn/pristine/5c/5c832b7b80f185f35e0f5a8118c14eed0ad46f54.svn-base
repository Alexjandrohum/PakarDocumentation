PrimeFaces.locales['es'] = {
    closeText: 'Cerrar',
    prevText: 'Anterior',
    nextText: 'Siguiente',
    monthNames: ['Enero','Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
    monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun','Jul','Ago','Sep','Oct','Nov','Dic'],
    dayNames: ['Domingo','Lunes','Martes','Miercoles','Jueves','Viernes','Sábado'],
    dayNamesShort: ['Dom','Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
    dayNamesMin: ['D','L','M','I','J','V','S'],
    weekHeader: 'Semana',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    timeOnlyTitle: 'Solo hora',
    timeText: 'Tiempo',
    hourText: 'Hora',
    minuteText: 'Minuto',
    secondText: 'Segundo',
    currentText: 'Hoy',
    ampm: false,
    month: 'Mes',
    week: 'Semana',
    day: 'Dia',
    allDayText : 'Todo el dia',
    choose: 'Elegir'
};

function cambiaPantalla(){
    /*var target = $('#target')[0]; // Get DOM element from jQuery collection
    $('#button').click(function() {
        if (screenfull.enabled) {
            screenfull.request(target);
        }
    });*/
    
    
    location.href='catalogo_pantalla.jsf';
}

function cambiaCatalogo(){
    location.href='catalogo.jsf';
    cancelFullScreen();
}

function launchFullscreen(element) {
    if (element.requestFullScreen) {
        element.requestFullScreen();
    } else if (element.mozRequestFullScreen) {
        element.mozRequestFullScreen();
    } else if (element.webkitRequestFullScreen) {
        element.webkitRequestFullScreen();
    }
}

function cancelFullscreen() {
    if (document.cancelFullScreen) {
        document.cancelFullScreen();
    } else if (document.mozCancelFullScreen) {
        document.mozCancelFullScreen();
    } else if (document.webkitCancelFullScreen) {
        document.webkitCancelFullScreen();
    }
}

function pantalla(elemento) {
    var
    fullScreenApi = {
        supportsFullScreen: false,
        isFullScreen: function() {
            return false;
        },
        requestFullScreen: function() {},
        cancelFullScreen: function() {},
        fullScreenEventName: '',
        prefix: ''
    },
    browserPrefixes = 'webkit moz o ms khtml'.split(' ');
     
    // check for native support
    if (typeof document.cancelFullScreen != 'undefined') {
        fullScreenApi.supportsFullScreen = true;
    } else {
        // check for fullscreen support by vendor prefix
        for (var i = 0, il = browserPrefixes.length; i < il; i++ ) {
            fullScreenApi.prefix = browserPrefixes[i];
     
            if (typeof document[fullScreenApi.prefix + 'CancelFullScreen' ] != 'undefined' ) {
                fullScreenApi.supportsFullScreen = true;
     
                break;
            }
        }
    }
     
    // update methods to do something useful
    if (fullScreenApi.supportsFullScreen) {
        fullScreenApi.fullScreenEventName = fullScreenApi.prefix + 'fullscreenchange';
     
        fullScreenApi.isFullScreen = function() {
            switch (this.prefix) {
                case '':
                    return document.fullScreen;
                case 'webkit':
                    return document.webkitIsFullScreen;
                default:
                    return document[this.prefix + 'FullScreen'];
            }
        }
        fullScreenApi.requestFullScreen = function(el) {
            return (this.prefix === '') ? el.requestFullScreen() : el[this.prefix + 'RequestFullScreen']();
        }
        fullScreenApi.cancelFullScreen = function(el) {
            return (this.prefix === '') ? document.cancelFullScreen() : document[this.prefix + 'CancelFullScreen']();
        }
    }
     
    // jQuery plugin
    if (typeof jQuery != 'undefined') {
        jQuery.fn.requestFullScreen = function() {
     
            return this.each(function() {
                if (fullScreenApi.supportsFullScreen) {
                    fullScreenApi.requestFullScreen(this);
                }
            });
        };
    }
     
    // export api
    window.fullScreenApi = fullScreenApi;
    if (fullScreenApi.supportsFullScreen) {
        fullScreenApi.requestFullScreen(elemento);
    }
        
}

function menor(valor1, valor2){
    return valor1 < valor2;
}

function buscaCodigo(event,id) {
    if (event.which == 13 || event.keyCode == 13) {
        document.getElementById(id).click();
    }
    return false;
}

function accordionMenuSetting(obj,settings) {		
			
    this.menuSettings = settings;
    this.menuAnimate = animate;			
    var _this = this;
			
			function animate(obj,i){
				
				$.each(obj, function(j) {
					var otherDim = Math.round(  (  (_this.menuSettings.closeDim*obj.length)-(_this.menuSettings.openDim)  )/(obj.length-1)  );			
					var itemDim = otherDim;			
					if ( j == i ) {
						itemDim = _this.menuSettings.openDim;
					}
					if (typeof i == 'undefined') {
						if (_this.menuSettings.openItem == null) itemDim = _this.menuSettings.closeDim;
						else if (_this.menuSettings.openItem == j) itemDim = _this.menuSettings.openDim;
						else itemDim = otherDim;
					}
					
					if (_this.menuSettings.position == 'vertical')
						$(this).animate({'height':itemDim},_this.menuSettings.duration,_this.menuSettings.effect);
					else 
						$(this).animate({'width':itemDim},_this.menuSettings.duration,_this.menuSettings.effect);
						
					var title = $('span',this);
					
					title.stop(true,false);
					
					if (_this.menuSettings.fadeInTitle != null && title.length > 0) {
						if (itemDim == _this.menuSettings.openDim) {
							if (_this.menuSettings.fadeInTitle) title.animate({'opacity':0.7});
							else title.animate({'opacity':0});		
						} else {
							if (_this.menuSettings.fadeInTitle) title.animate({'opacity':0});
							else title.animate({'opacity':0.7});
						}
					}						
				});		
			
			}

			var $this = $('a',obj);
			
			_this.menuAnimate($this);
			
			var maxDim = _this.menuSettings.closeDim*$this.length + _this.menuSettings.border*$this.length + 10;
			
			if (_this.menuSettings.position == 'vertical') 
				$(obj).css({'width':_this.menuSettings.width+'px','height':maxDim+'px'});
			else 
				$(obj).css({'height':_this.menuSettings.height+'px','width':maxDim+'px'});		
			 
			
			$.each($this, function(i) {	
				
				ImgSrc = $('img',this).attr('src');
				$('img',this).hide();
				
				var borderBottomValue = 0;
				var borderRightValue = 'solid '+_this.menuSettings.border+'px '+_this.menuSettings.color;
				var aWidth = 'auto';			
				var aHeight = _this.menuSettings.height+'px';
				
				if (_this.menuSettings.position == 'vertical') {
					
					borderBottomValue = 'solid '+_this.menuSettings.border+'px '+_this.menuSettings.color;
					borderRightValue = 0;
					aWidth = _this.menuSettings.width+'px';				
					aHeight = 'auto';				
				}	
				
				
				if ( i == ($this.length-1)) {
					borderBottomValue = 0;
					borderRightValue = 0;
				} 
							
				$(this).css({
							'width': aWidth,
							'height': aHeight,
							'background-image':'url('+ImgSrc+')',
							'background-color':_this.menuSettings.color,
							'background-repeat':'no-repeat',
							'border-bottom': borderBottomValue,
							'border-right': borderRightValue						
							}).mouseenter(function() {
								$this.stop(true,false);
								_this.menuAnimate($this,i);
							});	
			
			});
			
			$(obj).mouseleave(function() {
				_this.menuAnimate($this);
			});
	

	}