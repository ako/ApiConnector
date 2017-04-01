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

#### A microflow API

The following action illustrates how to register a new API:

 ![Register API][1]

 The following microflow implements the *get product by EAN* API:
 
  ![Get product by EAN][3]
 
 Another example, a microflow to implement *create new product* API:
 
  ![Create product][2]

#### Register an OQL API

The following action illustrates how to register an OQL endpoint:

 ![Register OQL API][5]

#### Register a binary API implemented using a Microflow

The following registered an endpoint to return images:

 ![Register Binary Microflow API][6]
 
The implementation returns a subclass of a FileDocument entity:

 ![Microflow API implementation for a binary endpoint][7]
 
The microflow also illustrates how you can set the content type of the response and how you can control caching.

#### Register a pdf report API

The following registers an API endpoint returning a pdf report:

  ![Register PDF Endpoint][8]
  
Implementation of the endpoint generating a pdf report:
  
  ![Generate PDF report Microflow][9]
  
## Build

To run this project from the Mendix modeler you need to download the required java dependensies.
You can do this by running *runivy.cmd*

 [1]: docs/config-get-product-with-mapping.png
 [2]: docs/mf-create-product.png
 [3]: docs/mf-get-product.png
 [4]: docs/toolbox-actions.png
 [5]: docs/register_oql_endpoint.png
 [6]: docs/register_binary_endpoint.png
 [7]: docs/microflow_binary_endpoint.png
 [8]: docs/register_pdf_endpoint.png
 [9]: docs/generate_pdf_report_mf.png