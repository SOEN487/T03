package com.example.rest;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Path("/customerform")
public class CustomerRestForm {
    /**
     * Class for holding the list of customers and handling the requests
     */

    private static ArrayList<Customer> customers = new ArrayList<>();

    /**
     * Meant for returning the list of customers
     * @return A concatenation of the toString method for all customers
     */
    @GET
    @Produces("application/xml")
    public ArrayList<Customer> getCustomer() {
        return customers;
    }

    /**
     * Meant for getting a customer with a specific ID
     * @param id of the customer
     * @return toString method of customer
     */
    @GET
    @Path("{id}")
    @Produces("application/xml")
    public Customer getCustomerList(@PathParam("id") int id) {
        Customer customer = customers.stream().filter(customer1 -> customer1.getId() == id)
                .findFirst()
                .orElse(null);
        return customer;
    }

    /**
     * Meant for creating customers using the post method
     * @param name of the customer
     * @param age of the customer
     */
    @POST
    public void createCustomer(@FormParam("name") String name, @FormParam("age") int age) {
        Customer newCustomer = new Customer(name, age);
        customers.add(newCustomer);
    }

    /**
     * Meant for replacing customer with specific ID
     * @param id of the customer
     * @param name of the customer
     * @param age of the customer
     */
    @PUT
    @Path("{id}")
    @Consumes("application/xml")
    public void modifyCustomer(@PathParam("id") int id, @FormParam("name") String name, @FormParam("age") int age) {
        deleteCustomer(id);
        customers.add(new Customer(name, age));
    }

    /**
     * Meant for deleting customer with specific ID
     * @param id of the customer
     */
    @DELETE
    @Path("{id}")
    public void deleteCustomer(@PathParam("id") int id) {
        customers = customers.stream().filter(customer -> customer.getId() != id)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
