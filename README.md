# MFPE
# CDE-Project Real Estate Management System

## Modules::

* ### Authorization-Microservice :
  This module is used for doing the **Authentication** and **Authorization** part of our project. 
  This microsevice provides the endpoints for authentication and authorization

  * #### --Endpoints : 
    <table>
        <thead>
            <th>Method</th>
            <th>Endpoint Path</th>
            <th>Returns</th>
        </thead>
        <tbody>
            <tr>
                <td>POST</td>
                <td>/authenticate</td>
                <td>ResponseEntity of String type</td>
            </tr>
            <tr>
                <td>POST</td>
                <td>/authorize</td>
                <td>boolean</td>
            </tr>
            <tr>
                <td>POST</td>
                <td>/authorize-manager</td>
                <td>boolean</td>
            </tr>
          <tr>
              <td>POST</td>
              <td>/authorize-executive</td>
              <td>boolean</td>
            </tr>
          <tr>
              <td>POST</td>
              <td>/authorize-customer</td>
              <td>boolean</td>
            </tr>
        </tbody>
    </table>

  * #### --Dependencies on Other microsevices : **None**

  * #### --Application Properties Toggle :<br/>
      spring.application.name=authorization-service<br/>
      server.port=8084<br/>
      server.servlet.context-path=/auth<br/>
      User Database : H2(In-Memory)<br/>

* ### Customer-Microservice :
  This module is used implementing the **Customer Services** part after the registration of a customer. 
  This microsevice provides the endpoints for getting the customer related details and storing customer details.

  * #### --Endpoints : 
    <table>
        <thead>
            <th>Method</th>
            <th>Endpoint Path</th>
            <th>Returns</th>
        </thead>
        <tbody>
            <tr>
                <td>GET</td>
                <td>/getAllRequirements</td>
                <td>List of "Requirement"</td>
            </tr>
            <tr>
                <td>GET</td>
                <td>/getAllCustomers</td>
                <td>List of "Customer"</td>
            </tr>
            <tr>
                <td>POST</td>
                <td>/createCustomer</td>
                <td>ResponseEntity of String type</td>
            </tr>
          <tr>
              <td>GET</td>
              <td>/getCustomerDetails/{id}</td>
              <td>Customer</td>
            </tr>
          <tr>
              <td>GET</td>
              <td>/getProperties</td>
              <td>List of "Property"</td>
            </tr>
          <tr>
              <td>PUT</td>
              <td>/{customerId}/assignRequirements/{requirementId}</td>
              <td>ResponseEntity of String type</td>
            </tr>
        </tbody>
    </table>

  * #### --Dependencies on Other microsevices : **Authorization-Microservice**, **Property-MicroService**

  * #### --Application Properties Toggle :<br/>
      spring.application.name=customer-service<br/>
      server.port=8081<br/>
      server.servlet.context-path=/customer<br/>
      User Database : H2(In-Memory)<br/>


* ### Property-Microservice :
  This module is used implementing the **Property services**. 
  This microsevice provides the endpoints for getting the property related details and storing property details.

  * #### --Endpoints : 
    <table>
        <thead>
            <th>Method</th>
            <th>Endpoint Path</th>
            <th>Returns</th>
        </thead>
        <tbody>
            <tr>
                <td>POST</td>
                <td>/createProperty</td>
                <td>ResponseEntity of String type</td>
            </tr>
            <tr>
                <td>GET</td>
                <td>/getAllProperties</td>
                <td>List of "Property"</td>
            </tr>
            <tr>
                <td>GET</td>
                <td>/getAllPropertiesByType/{propertyType}</td>
                <td>List of "Property" of "propertyType"</td>
            </tr>
            <tr>
                <td>GET</td>
                <td>/getAllPropertiesByLocality/{locality}</td>
                <td>List of "Property" in "locality"</td>
            </tr>
        </tbody>
    </table>

  * #### --Dependencies on Other microsevices : **Authorization-Microservice**

  * #### --Application Properties Toggle : <br/>
      spring.application.name=property-service<br/>
      server.port=8082<br/>
      server.servlet.context-path=/property<br/>
      User Database : H2(In-Memory)<br/>

* ### Manager-Microservice :
  This module is used implementing the **Manager Services** part after the registration of a manager or executive. 
  This microsevice provides the endpoints for getting the executive related details and storing executive details.

  * #### --Endpoints : 
    <table>
        <thead>
            <th>Method</th>
            <th>Endpoint Path</th>
            <th>Returns</th>
        </thead>
        <tbody>
            <tr>
                <td>POST</td>
                <td>/createExecutive</td>
                <td>ResponseEntity of String type</td>
            </tr>
            <tr>
                <td>GET</td>
                <td>/getAllExecutives</td>
                <td>List of "Executive"</td>
            </tr>
            <tr>
                <td>GET</td>
                <td>/getAllExecutivesByLocality/{locality}</td>
                <td>List of "Executive" in "locality"</td>
            </tr>
            <tr>
                <td>GET</td>
                <td>/getAllCustomers</td>
                <td>List of "Customer"</td>
            </tr>
          <tr>
              <td>GET</td>
              <td>/getCustomersById/{id}</td>
              <td>Customer</td>
            </tr>
          <tr>
              <td>PUT</td>
              <td>/{executiveId}/assignExecutive/{customerId}</td>
              <td>ResponseEntity of String type</td>
            </tr>
        </tbody>
    </table>
    
  * #### --Dependencies on Other microsevices : **Authorization-Microservice**, **Customer-Microservice**

  * #### --Application Properties Toggle : <br/>
      spring.application.name=manager-service<br/>
      server.port=8083<br/>
      server.servlet.context-path=/manager<br/>
      User Database : H2(In-Memory)<br/>

