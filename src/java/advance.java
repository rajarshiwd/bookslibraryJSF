/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author rajarshi
 */
@FacesComponent(createTag = true)
public class advance extends UIComponentBase {
    
    @Override
    public String getFamily() {
        return "";
    }
    
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        String value = (String) getAttributes().get("value");
        if (value != null) {
            ResponseWriter responseWriter = context.getResponseWriter();
            responseWriter.write(value.toUpperCase());
        }
    }
    
}
