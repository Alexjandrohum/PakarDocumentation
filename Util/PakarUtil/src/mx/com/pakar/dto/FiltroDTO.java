package mx.com.pakar.dto;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;

/**
 *
 * @author nicolas.canaan
 */
public class FiltroDTO implements FileFilter, Serializable {

    private String []tipos;

    public FiltroDTO(String []tipos ){
        this.tipos = tipos;
    }

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return false;
        }

        String extension = getExtension(f);
        if (extension != null) {
            for( String ext : tipos )
                if ( extension.equals( ext ) )
                    return true;
        }

        return false;
    }

    public static String getExtension( File f ) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

}
