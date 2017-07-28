/*
 * To change this license header, choose License Headers in Project Properties.


 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookquery;

import javax.jws.WebService;

/**
 *
 * @author rajarshi
 */
@WebService(serviceName = "QueryBooks", portName = "QueryBooksPort", endpointInterface = "bookquery.QueryBooks", targetNamespace = "http://BookQuery/", wsdlLocation = "WEB-INF/wsdl/WebServiceReferences/localhost_8080/BookWebService/QueryBooks.wsdl")
public class WebServiceReferences {

    public java.util.List<net.java.dev.jaxb.array.StringArray> getBooks(java.lang.String arg0, java.lang.String arg1) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public java.lang.String hello(java.lang.String name) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
