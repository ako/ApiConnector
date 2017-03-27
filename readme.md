# Mendix Api connector

Example project to illustrate how you can use the Mendix Connector Kit to create APIs for your
Mendix application using microflows.

## Usage

This project implements a connector to create an API for a mendix project implemented using microflows.
The following Java actions are implemented to enable this:
* Register API endpoint
* Register API endpoint with mapping
* API Header Location
* API Status 201 - Created
* API Status 404 - Not found

 ![API connector toolbox actions][4]

### Register a new API

The following action illustrates how to register a new API:

 ![Register API][1]
 
#### Register an OQL API

The following action illustrates how to register an OQL endpoint:

 ![Register OQL API][5]

### Implement API

The following microflow implements the *get product by EAN* API:

 ![Get product by EAN][3]

Another example, a microflow to implement *create new product* API:

 ![Create product][2]

## Build

To run this project from the Mendix modeler you need to download the required java dependensies.
You can do this by running *runivy.cmd*

 [1]: docs/config-get-product-with-mapping.png
 [2]: docs/mf-create-product.png
 [3]: docs/mf-get-product.png
 [4]: docs/toolbox-actions.png
 [5]: docs/register_oql_endpoint.png
