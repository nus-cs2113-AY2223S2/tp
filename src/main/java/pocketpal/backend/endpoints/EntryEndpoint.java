package pocketpal.backend.endpoints;

import pocketpal.communication.Request;
import pocketpal.communication.RequestParams;
import pocketpal.communication.Response;
import pocketpal.communication.ResponseStatus;
import pocketpal.data.entry.Entry;
import pocketpal.data.entrylog.EntryLog;
import pocketpal.data.parsing.EntryParser;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.frontend.util.CategoryUtil;

import java.util.logging.Logger;

public class EntryEndpoint extends Endpoint {
    private static final Logger logger = Logger.getLogger(EntryEndpoint.class.getName());
    private final EntryLog entries;

    public EntryEndpoint(EntryLog entries) {
        this.entries = entries;
    }

    /**
     * Delete an entry from the list
     *
     * @param request The request should have the following data
     *                - data: 1-based index of entry
     * @return Response containing deleted entry if ID is valid, otherwise not found
     */
    @Override
    public Response handleDelete(Request request) {
        logger.info("/entry [DELETE]: request received");
        try {
            int targetId = getPositiveIntegerFromString(request.getBody());
            Entry deletedEntry = entries.deleteEntry(targetId - 1);
            logger.info("/entry [DELETE]: OK");
            return new Response(ResponseStatus.OK, deletedEntry.serialise());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            logger.warning("/entry [DELETE]: received invalid entry ID");
            return new Response(ResponseStatus.NOT_FOUND, getInvalidIDMessage());
        }
    }

    @Override
    public Response handleGet(Request request) {
        logger.info("/entry [GET]: request received - " + request.getBody());
        try {
            int targetEntryId = getPositiveIntegerFromString(request.getBody());
            Entry entry = entries.getEntry(targetEntryId);
            if (entry == null) {
                throw new NumberFormatException();
            }
            logger.info("/entry [GET]: OK");
            return new Response(ResponseStatus.OK, entry.serialise());
        } catch (NumberFormatException e) {
            logger.warning("/entry [GET]: received invalid entry ID " + request.getBody());
            return new Response(ResponseStatus.NOT_FOUND, getInvalidIDMessage());

        }
    }

    /**
     * Edit the fields in an entry
     *
     * @param request The request should have the following data
     *                - data: 1-based index of the entry to be patched
     *                - param?: EDIT_AMOUNT
     *                - param?: EDIT_CATEGORY
     *                - param?: EDIT_DESCRIPTION
     * @return Response with the updated entry
     */
    @Override
    public Response handlePatch(Request request) {
        logger.info("/entry [PATCH]: request received");
        try {
            Entry editEntry;
            int targetEntryId = getPositiveIntegerFromString(request.getBody());
            editEntry = entries.getEntry(targetEntryId);
            if (editEntry == null) {
                throw new NumberFormatException();
            }

            if (request.hasParam(RequestParams.EDIT_CATEGORY)) {
                String category = request.getParam(RequestParams.EDIT_CATEGORY);
                editEntry.setCategory(CategoryUtil.convertStringToCategory(category));
                logger.info("/entry [PATCH]: update category" + request.getBody());
            }
            if (request.hasParam(RequestParams.EDIT_AMOUNT)) {
                double amount = getAmountFromString(request.getParam(RequestParams.EDIT_AMOUNT));
                editEntry.setAmount(amount);
                logger.info("/entry [PATCH]: update amount" + request.getBody());
            }
            if (request.hasParam(RequestParams.EDIT_DESCRIPTION)) {
                String description = request.getParam(RequestParams.EDIT_DESCRIPTION);
                editEntry.setDescription(description);
                logger.info("/entry [PATCH]: update description" + request.getBody());
            }

            logger.info("/entry [PATCH]: OK");
            return new Response(ResponseStatus.OK, editEntry.serialise());
        } catch (NumberFormatException e) {
            logger.warning("/entry [PATCH]: received invalid entry ID " + request.getBody());
            return new Response(ResponseStatus.NOT_FOUND, getInvalidIDMessage());
        } catch (InvalidCategoryException e) {
            logger.warning("/entry [PATCH]: received invalid category" + request.getBody());
            return new Response(ResponseStatus.UNPROCESSABLE_CONTENT,
                    MessageConstants.MESSAGE_INVALID_CATEGORY);
        } catch (InvalidArgumentsException e) {
            logger.warning("/entry [PATCH]: received invalid arguments" + request.getBody());
            return new Response(ResponseStatus.UNPROCESSABLE_CONTENT, e.getMessage());
        }
    }

    /**
     * Add a new entry to the list.
     *
     * @param request The request should have the following data
     *                - data: Serialised Entry to be added
     * @return Created response
     */
    @Override
    public Response handlePost(Request request) {
        logger.info("/entry [POST]: request received");
        String json = request.getBody();
        Entry entry = EntryParser.deserialise(json);
        entries.addEntry(entry);
        logger.info("/entry [POST]: CREATED");
        return new Response(ResponseStatus.CREATED, "");
    }

    /**
     * Utility method when user inputs invalid entry ID
     * @return Invalid ID Message
     */
    private String getInvalidIDMessage() {
        return MessageConstants.MESSAGE_ID_NOT_FOUND + entries.getSize() + ".";
    }
}
