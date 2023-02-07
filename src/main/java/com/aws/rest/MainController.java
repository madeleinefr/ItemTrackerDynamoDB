package com.aws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @ComponentScan annotation is used to specify the packages that we want to be scanned
 * Without an argument it tells Spring to scan the current package and all of its sub-packages
 * As we specified in brackets, it will scan the com.aws.rest package and all its sub-packages
 *
 * @CrossOrigin annotation enables cross-origin resource sharing on all handler methods of this class.
 * by default it allows all origins, all headers and the HTTP methods specified in the @RequestMapping
 *
 * @RestController annotation is a specialized version of the controller.
 * It already includes the @Controller and @ResponseBody annotations, so the implementation of the controller is simplified.
 * (e.g. you don't need to add @ResponseBody anymore). Every request handling method of the controller class automatically serializes return objects into HttpResponse
 *
 * @RequestMapping annotation is used to map web requests onto specific handler classes and methods and make web resources addressable
 * The value parameter is used to specify the request URI path on controller class name
 */

@ComponentScan(basePackages = {"com.aws.rest"})
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/items")
public class MainController {
    private final DynamoDBService dbService;

    /**
     * @Autowired
     * here we use the @Autowired annotation on a constructor
     * the instance of DynamoDBService is injected by Spring as an argument to the MainController constructor
     */
    @Autowired
    MainController(
            DynamoDBService dbService
    ) {
        this.dbService = dbService;
    }
    /**
     * @GetMapping annotated methods handle the HTTP GET requests matched with the given URI expression
     * GTTP GET requests are normally used for getting objects from the database
     * HTTP GET /api/items - returns all the items in the DynamoDB table in Json format
    */
    @GetMapping("" )
    public List<WorkItem> getItems(@RequestParam(required=false) String archived) {
        Iterable<WorkItem> result;
        if (archived != null && archived.compareTo("false")==0)
            result = dbService.getOpenItems();
        else if (archived != null && archived.compareTo("true")==0)
            result = dbService.getClosedItems();
        else
            result = dbService.getAllItems();

        return StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toUnmodifiableList());
    }
    /**
     * @PutMapping annotated methods handle the HTTP PUT requests matched with the given URI expression
     * HTTP PUT requests are normally used to update existing fields in the database
     * HTTP PUT /api/items/{id}:archive - archives the item with the specified id in the DynamoDB table
     */
    @PutMapping("{id}:archive")
    public String modUser(@PathVariable String id) {
        dbService.archiveItemEC(id);
        return id +" was archived";
    }
    /**
     * @PostMapping annotated methods handle the HTTP POST requests matched with the given URI expression
     * HTTP POST requests are normally used for adding new objects to the database
     * HTTP POST /api/items - adds a new item in the DynamoDB table
     */
    @PostMapping("")
    public List<WorkItem> addItem(@RequestBody Map<String, String> payload) {
        String name = payload.get("name");
        String guide = payload.get("guide");
        String description = payload.get("description");
        String status = payload.get("status");

        WorkItem item = new WorkItem();
        String workId = UUID.randomUUID().toString();
        String date = LocalDateTime.now().toString();
        item.setId(workId);
        item.setGuide(guide);
        item.setDescription(description);
        item.setName(name);
        item.setDate(date);
        item.setStatus(status);
        dbService.setItem(item);
        Iterable<WorkItem> result= dbService.getOpenItems();
        return StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toUnmodifiableList());
    }
}
