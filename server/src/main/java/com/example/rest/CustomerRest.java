package com.example.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Path("customer")
public class CustomerRest {

    /**
     * Class for holding the list of customers and handling the requests
     */

    private static ArrayList<Customer> customers = new ArrayList<>();

    /**
     * Meant for returning the list of customers
     * @return A concatenation of the toString method for all customers
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getCustomer() {
        return customers.stream().map(Object::toString).collect(Collectors.joining(".\n"));
    }

    /**
     * Meant for getting a customer with a specific ID
     * @param id of the customer
     * @return toString method of customer
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public String getCustomerList(@PathParam("id") int id) {
        Customer customer = customers.stream().filter(customer1 -> customer1.getId() == id)
                .findFirst()
                .orElse(null);
        if (customer != null) {
            return customer.toString();
        } else {
            return "Customer not found!";
        }
    }

    /**
     * Meant for creating customers using the post method
     * @param name of the customer
     * @param age of the customer
     */
    @POST
    @Path("{name}/{age}")
    public void createCustomer(@PathParam("name") String name, @PathParam("age") int age) {
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
    @Path("{id}/{name}/{age}")
    public void modifyCustomer(@PathParam("id") int id, @PathParam("name") String name, @PathParam("age") int age) {
        deleteCustomer(id);
        createCustomer(name, age);
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

    /**
     * Debugging statement that prints the current state of the list of customers
     */
    private void printCustomers() {
        for(Customer customer: customers) {
            System.out.println(customer);
        }
    }

}
