package com.scpakar.scpakarweb.dto;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author pablo.martinez
 */
public class LazyDataModelProducto extends LazyDataModel<ProductoDTO> implements Serializable {

    @SuppressWarnings("FieldMayBeFinal")
    private List<ProductoDTO> productosCatalogo;
    
    public LazyDataModelProducto() {
        this.productosCatalogo = new ArrayList<>();
    }

    public LazyDataModelProducto(List<ProductoDTO> productosCatalogo) {
        this.productosCatalogo = productosCatalogo;
    }

    @Override
    public List<ProductoDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
    List<ProductoDTO> data = new ArrayList<>();

        //rowCount
        int dataSize = productosCatalogo.size();
        this.setRowCount(dataSize);

        //paginate
        if (dataSize > pageSize) {
            try {
                data = new ArrayList<>(productosCatalogo.subList(first, first + pageSize));
            } catch (Exception e) {
                Util.registraError(e);
                try {
                    data = new ArrayList<>(productosCatalogo.subList(first, first + (dataSize % pageSize)));
                } catch (Exception ex) {
                    Util.registraError(ex);
                }
            }
        } else {
            data = new ArrayList<>(productosCatalogo);
        }
        return data;
    }

}