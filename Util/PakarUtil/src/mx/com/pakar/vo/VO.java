package mx.com.pakar.vo;

import javax.faces.context.ExternalContext;
import mx.com.pakar.util.Util;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

/**
 *
 * @author nicolas.canaan
 */
public class VO {
    
    protected Logger log;
    //protected Log log;

    public VO() {
        log = Logger.getLogger( this.getClass() );
        //log = LogFactory.getLog( this.getClass() );
    }
    
    public void configuraLog(ExternalContext ec, String path){
        PropertyConfigurator.configure( Util.getDireccionReal(ec)+path );
    }
    
    public void error(Exception ex){
        log.error(ex.getMessage());
    }
    
    public void warning(Exception ex){
        log.warn(ex.getMessage());
    }
    
    public void fatal(Exception ex){
        log.fatal(ex.getMessage());
    }
    
}
