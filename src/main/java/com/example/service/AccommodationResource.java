// // package com.example.service;

// // import jakarta.inject.Inject;
// // import jakarta.ws.rs.*;
// // import jakarta.ws.rs.core.MediaType;
// // import jakarta.ws.rs.core.Response;

// // import java.io.IOException;
// // import java.util.Map;

// // @Path("/accommodations")
// // @Produces(MediaType.APPLICATION_JSON)
// // @Consumes(MediaType.APPLICATION_JSON)
// // public class AccommodationResource {

// //     @Inject
// //     AccommodationService service;

// //     @POST
// //     @Path("/{id}")
// //     public Response create(@PathParam("id") String id, Map<String, Object> document) {
// //         try {
// //             if (document == null || document.isEmpty()) {
// //                 return Response.status(Response.Status.BAD_REQUEST)
// //                         .entity("Document payload is missing or empty").build();
// //             }
// //             service.createDocument(id, document);
// //             return Response.status(Response.Status.CREATED).entity("Document created successfully").build();
// //         } catch (IOException e) {
// //             return Response.serverError().entity("Error creating document: " + e.getMessage()).build();
// //         }
// //     }

// //     @GET
// //     @Path("/{id}")
// //     public Response get(@PathParam("id") String id) {
// //         try {
// //             Map<String, Object> document = service.getDocument(id);
// //             if (document == null || document.isEmpty()) {
// //                 return Response.status(Response.Status.NOT_FOUND).entity("Document not found").build();
// //             }
// //             return Response.ok(document).build();
// //         } catch (IOException e) {
// //             return Response.serverError().entity("Error fetching document: " + e.getMessage()).build();
// //         }
// //     }

// //     @PUT
// //     @Path("/{id}")
// //     public Response update(@PathParam("id") String id, Map<String, Object> document) {
// //         try {
// //             if (document == null || document.isEmpty()) {
// //                 return Response.status(Response.Status.BAD_REQUEST)
// //                         .entity("Document payload is missing or empty").build();
// //             }

// //             // Check if the document exists before updating
// //             Map<String, Object> existingDocument = service.getDocument(id);
// //             if (existingDocument == null || existingDocument.isEmpty()) {
// //                 return Response.status(Response.Status.NOT_FOUND).entity("Document not found").build();
// //             }

// //             // Update the document using the service
// //             service.updateDocument(id, document);
// //             return Response.ok("Document updated successfully").build();
// //         } catch (IOException e) {
// //             return Response.serverError().entity("Error updating document: " + e.getMessage()).build();
// //         }
// //     }

// //     @DELETE
// //     @Path("/{id}")
// //     public Response delete(@PathParam("id") String id) {
// //         try {
// //             service.deleteDocument(id);
// //             return Response.ok("Document deleted successfully").build();
// //         } catch (IOException e) {
// //             return Response.serverError().entity("Error deleting document: " + e.getMessage()).build();
// //         }
// //     }

// //     @GET
// //     @Path("/search")
// //     public Response search(@QueryParam("field") String field, @QueryParam("value") String value) {
// //         try {
// //             if (field == null || field.isEmpty() || value == null || value.isEmpty()) {
// //                 return Response.status(Response.Status.BAD_REQUEST)
// //                         .entity("Search parameters 'field' and 'value' are required").build();
// //             }
// //             return Response.ok(service.searchDocuments(field, value)).build();
// //         } catch (IOException e) {
// //             return Response.serverError().entity("Error searching documents: " + e.getMessage()).build();
// //         }
// //     }
// // }


//=======================================================

package com.example.service;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * REST API for managing accommodations stored in OpenSearch.
 */
@Path("/accommodations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccommodationResource {

    @Inject
    AccommodationService service;

    /**
     * Creates a new accommodation document.
     *
     * @param id       Document ID
     * @param document Document data
     * @return Response indicating success or failure
     */
    @POST
    @Path("/{id}")
    public Response create(@PathParam("id") String id, Map<String, Object> document) {
        try {
            if (document == null || document.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Document payload is missing or empty").build();
            }
            service.createDocument(id, document);
            return Response.status(Response.Status.CREATED).entity("Document created successfully").build();
        } catch (IOException e) {
            return Response.serverError().entity("Error creating document: " + e.getMessage()).build();
        }
    }

    /**
     * Retrieves a specific accommodation document by ID.
     *
     * @param id Document ID
     * @return Response with document data or error
     */
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") String id) {
        try {
            Map<String, Object> document = service.getDocument(id);
            if (document == null || document.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("Document not found").build();
            }
            return Response.ok(document).build();
        } catch (IOException e) {
            return Response.serverError().entity("Error fetching document: " + e.getMessage()).build();
        }
    }

    /**
     * Updates an existing accommodation document.
     *
     * @param id       Document ID
     * @param document Updated document data
     * @return Response indicating success or failure
     */
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, Map<String, Object> document) {
        try {
            if (document == null || document.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Document payload is missing or empty").build();
            }

            Map<String, Object> existingDocument = service.getDocument(id);
            if (existingDocument == null || existingDocument.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("Document not found").build();
            }

            service.updateDocument(id, document);
            return Response.ok("Document updated successfully").build();
        } catch (IOException e) {
            return Response.serverError().entity("Error updating document: " + e.getMessage()).build();
        }
    }

    /**
     * Deletes an accommodation document by ID.
     *
     * @param id Document ID
     * @return Response indicating success or failure
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        try {
            service.deleteDocument(id);
            return Response.ok("Document deleted successfully").build();
        } catch (IOException e) {
            return Response.serverError().entity("Error deleting document: " + e.getMessage()).build();
        }
    }

    /**
     * Retrieves all accommodation documents.
     *
     * @return Response with a list of documents or error
     */
    @GET
    public Response getAll() {
        try {
            List<Map<String, Object>> documents = service.getAllDocuments();
            if (documents.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("No documents found").build();
            }
            return Response.ok(documents).build();
        } catch (IOException e) {
            return Response.serverError().entity("Error fetching documents: " + e.getMessage()).build();
        }
    }

    /**
     * Searches for accommodation documents by field and value.
     *
     * @param field Field to search
     * @param value Value to search
     * @return Response with search results or error
     */
    @GET
    @Path("/search")
    public Response search(@QueryParam("field") String field, @QueryParam("value") String value) {
        try {
            if (field == null || field.isEmpty() || value == null || value.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Search parameters 'field' and 'value' are required").build();
            }
            return Response.ok(service.searchDocuments(field, value)).build();
        } catch (IOException e) {
            return Response.serverError().entity("Error searching documents: " + e.getMessage()).build();
        }
    }
}



