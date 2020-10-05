package mx.com.pakar.dao;

import org.apache.log4j.Logger;

/**
 *
 * @author nicolas.canaan
 */
public class DAO {

    protected Logger log;

    public DAO() {
        log = Logger.getLogger( this.getClass() );
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
