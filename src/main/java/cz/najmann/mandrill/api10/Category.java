package cz.najmann.mandrill.api10;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ales Najmann
 * @version 1.0
 */
public final class Category {

    /**
     * This class is only a holder for particular Categories of Calls
     * It isn't supposed for instantiation
     */
    private Category() {
    }

    /**
     * @link https://mandrillapp.com/api/docs/users.JSON.html
     */
    public static interface Users {

        /**
         * Return the information about the API-connected user
         *
         * @return struct
         */
        Struct info();

        /**
         * Validate an API key and respond to a ping
         *
         * @return string containing "PONG!"
         */
        String ping();

        /**
         * Validate an API key and respond to a ping (anal JSON parser version)
         *
         * @return struct containing "PONG!" in field "PING"
         */
        Struct ping2();

        /**
         * Return the senders that have tried to use this account, both verified and unverified
         *
         * @return array containing information about senders
         */
        StructArray senders();
    }

    /**
     * @link https://mandrillapp.com/api/docs/tags.JSON.html
     */
    public static interface Tags {

        /**
         * Return all of the user-defined tag information
         *
         * @return array
         */
        StructArray list();

        /**
         * Deletes a tag permanently. Deleting a tag removes the tag from any messages that have
         * been sent, and also deletes the tag's stats. There is no way to undo this operation,
         * so use it carefully.
         *
         * @param tag text tag
         * @return struct
         */
        Struct delete(@Param("tag") String tag);

        /**
         * Return more detailed information about a single tag, including aggregates of recent stats
         *
         * @param tag text tag
         * @return struct
         */
        Struct info(@Param("tag") String tag);

        /**
         * Return the recent history (hourly stats for the last 30 days) for a tag
         *
         * @param tag text tag
         * @return array
         */
        StructArray timeSeries(@Param("tag") String tag);

        /**
         * Return the recent history (hourly stats for the last 30 days) for all tags
         *
         * @return array
         */
        StructArray allTimeSeries();
    }

    /**
     * @link https://mandrillapp.com/api/docs/messages.JSON.html
     */
    public static interface Messages {

        /**
         * Send a new transactional message through Mandrill
         *
         * @param message message struct with message details
         * @param async
         * @param ip_pool
         * @param send_at
         * @return array
         */
        StructArray send(@Param("message") Struct message,
                         @Param("async") boolean async,
                         @Param("ip_pool") String ip_pool,
                         @Param("send_at") String send_at);

        /**
         * Send a new transactional message through Mandrill using a template
         *
         * @param template_name    template name
         * @param template_content array of content
         * @param message          struct with message details
         * @param async            send the message asynchronously?
         * @param ip_pool          name of the ip pool
         * @param send_at          date when to deliver
         * @return array
         */
        StructArray sendTemplate(@Param("template_name") String template_name,
                                 @Param("template_content") StructArray template_content,
                                 @Param("message") Struct message,
                                 @Param("async") boolean async,
                                 @Param("ip_pool") String ip_pool,
                                 @Param("send_at") String send_at);

        /**
         * Search the content of recently sent messages and optionally narrow by date range, tags and senders
         *
         * @param query
         * @param date_from
         * @param date_to
         * @param tags
         * @param senders
         * @param limit
         * @return array
         */
        StructArray search(@Param("query") String query,
                           @Param("date_from") String date_from,
                           @Param("data_to") String date_to,
                           @Param("tags") StructArray tags,
                           @Param("senders") StructArray senders,
                           @Param("limit") Integer limit);

        /**
         * Search the content of recently sent messages and return the aggregated hourly stats for matching messages
         *
         * @param query
         * @param date_from
         * @param date_to
         * @param tags
         * @param senders
         * @return array
         */
        StructArray searchTimeSeries(@Param("query") String query,
                                     @Param("date_from") String date_from,
                                     @Param("date_to") String date_to,
                                     @Param("tags") StructArray tags,
                                     @Param("senders") StructArray senders);

        /**
         * Get the information for a single recently sent message
         *
         * @param id message identifier
         * @return struct
         */
        Struct info(@Param("id") String id);

        /**
         * Parse the full MIME document for an email message, returning the content of the message broken
         * into its constituent pieces
         *
         * @param raw_message raw MIME message
         * @return struct
         */
        Struct parse(@Param("raw_message") String raw_message);

        /**
         * Take a raw MIME document for a message, and send it exactly as if it were sent over the SMTP protocol
         *
         * @param raw_message raw MIME message
         * @param from_email  the email of a sender
         * @param from_name   the name of a sender
         * @param to          receiver of the email
         * @param async       is message sent asynchronously?
         * @param ip_pool     name of the ip pool
         * @param send_at     date when to deliver
         * @return array
         */
        StructArray sendRaw(@Param("raw_message") String raw_message,
                            @Param("from_email") String from_email,
                            @Param("from_name") String from_name,
                            @Param("to") StructArray to,
                            @Param("async") boolean async,
                            @Param("ip_pool") String ip_pool,
                            @Param("send_at") String send_at);

        StructArray listScheduled(@Param("to") String to);

        Struct cancelScheduled(@Param("id") String id);

        Struct reschedule(@Param("id") String id,
                          @Param("sent_at") String send_at);

    }

    public static interface Senders {

        /**
         * Return the senders that have tried to use this account.
         *
         * @return
         */
        StructArray list();

        /**
         * Returns the sender domains that have been added to this account.
         *
         * @return
         */
        StructArray domains();

        /**
         * Return more detailed information about a single sender, including aggregates of recent stats
         *
         * @param address
         * @return
         */
        Struct info(@Param("address") String address);

        /**
         * Return the recent history (hourly stats for the last 30 days) for a sender
         *
         * @param address
         * @return
         */
        StructArray timeSeries(@Param("address") String address);
    }

    public static interface Templates {

        /**
         * Add a new template
         *
         * @param name
         * @param from_email
         * @param from_name
         * @param subject
         * @param code
         * @param text
         * @param publish
         * @return fluentStruct
         */
        Struct add(@Param("name") String name,
                   @Param("from_email") String from_email,
                   @Param("from_name") String from_name,
                   @Param("subject") String subject,
                   @Param("code") String code,
                   @Param("text") String text,
                   @Param("publish") boolean publish);

        /**
         * Get the information for an existing template
         *
         * @param name
         * @return
         */
        Struct info(@Param("name") String name);

        /**
         * Update the code for an existing template. If null is provided for any fields,
         * the values will remain unchanged.
         *
         * @param name
         * @param from_email
         * @param from_name
         * @param subject
         * @param code
         * @param text
         * @param publish
         * @return
         */
        Struct update(@Param("name") String name,
                      @Param("from_email") String from_email,
                      @Param("from_name") String from_name,
                      @Param("subject") String subject,
                      @Param("code") String code,
                      @Param("text") String text,
                      @Param("publish") boolean publish);

        /**
         * Publish the content for the template. Any new messages sent using this template will start
         * using the content that was previously in draft.
         *
         * @param name
         * @return
         */
        Struct publish(@Param("name") String name);

        /**
         * Delete a template
         *
         * @param name name
         * @return fluentStruct
         */
        Struct delete(@Param("name") String name);

        /**
         * Return a list of all the templates available to this user
         *
         * @return fluentArray
         */
        StructArray list();

        /**
         * Return the recent history (hourly stats for the last 30 days) for a template
         *
         * @param name
         * @return
         */
        StructArray timeSeries(@Param("name") String name);

        /**
         * Inject content and optionally merge fields into a template, returning the HTML
         * that results
         *
         * @param template_name    template name
         * @param template_content content of the template
         * @param merge_vars       array of variables to merge
         * @return struct
         */
        Struct render(@Param("template_name") String template_name,
                      @Param("template_content") StructArray template_content,
                      @Param("merge_vars") StructArray merge_vars);
    }

    public static interface Whitelists {

        /**
         * Adds an email to your email rejection whitelist. If the address is currently on your blacklist,
         * that blacklist entry will be removed automatically.
         *
         * @param email email address
         * @return array
         */
        Struct add(@Param("email") String email);

        /**
         * Retrieves your email rejection whitelist. You can provide an email address or search prefix to limit the results.
         * Returns up to 1000 results.
         *
         * @param email email address
         * @return array
         */
        StructArray list(@Param("email") String email);

        /**
         * Removes an email address from the whitelist.
         *
         * @param email email address
         * @return struct
         */
        Struct delete(@Param("email") String email);
    }

    public static interface Webhooks {

        /**
         * Get the list of all webhooks defined on the account
         *
         * @return array
         */
        StructArray list();

        /**
         * Add a new webhook
         *
         * @param url
         * @param description
         * @param events
         * @return fluentStruct
         */
        Struct add(@Param("url") String url,
                   @Param("description") String description,
                   @Param("events") StructArray events);

        /**
         * Given the ID of an existing webhook, return the data about it
         *
         * @param id
         * @return fluentStruct
         */
        Struct info(@Param("id") long id);

        /**
         * Update an existing webhook
         *
         * @param id
         * @param url
         * @param description
         * @param events
         * @return fluentStruct
         */
        Struct update(@Param("id") long id,
                      @Param("url") String url,
                      @Param("description") String description,
                      @Param("events") StructArray events);

        /**
         * Delete an existing webhook
         *
         * @param id
         * @return fluentStruct
         */
        Struct delete(@Param("id") long id);
    }

    public static interface Rejects {

        /**
         * Adds an email to your email rejection blacklist. Addresses that you add manually will never expire and
         * there is no reputation penalty for removing them from your blacklist. Attempting to blacklist an address
         * that has been whitelisted will have no effect.
         *
         * @param email
         * @return
         */
        Struct add(@Param("email") String email);

        /**
         * Retrieves your email rejection blacklist. You can provide an email address to limit the results.
         * Returns up to 1000 results. By default, entries that have expired are excluded from the results;
         * set include_expired to true to include them.
         *
         * @param email
         * @param include_expired
         * @return
         */
        StructArray list(@Param("email") String email,
                         @Param("include_expired") boolean include_expired);

        /**
         * Deletes an email rejection. There is no limit to how many rejections you can remove from your blacklist,
         * but keep in mind that each deletion has an affect on your reputation.
         *
         * @param email
         * @return
         */
        Struct delete(@Param("email") String email);
    }

    public static interface Urls {

        /**
         * Get the 100 most clicked URLs
         *
         * @return
         */
        StructArray list();

        /**
         * Return the 100 most clicked URLs that match the search query given
         *
         * @param q
         * @return
         */
        StructArray search(@Param("q") String q);

        /**
         * Return the recent history (hourly stats for the last 30 days) for a url
         *
         * @param url
         * @return
         */
        StructArray timeSeries(@Param("url") String url);
    }

    public static interface Exports {

        /**
         * Returns information about an export job. If the export job's state is 'complete', the returned
         * data will include a URL you can use to fetch the results. Every export job produces a zip archive,
         * but the format of the archive is distinct for each job type. The api calls that initiate exports
         * include more details about the output format for that job type.
         *
         * @param id
         * @return fluentStruct
         */
        Struct info(@Param("id") String id);

        /**
         * Returns a list of your exports.
         *
         * @return fluentArray
         */
        StructArray list();

        /**
         * Begins an export of your rejection blacklist. The blacklist will be exported to a zip
         * archive containing a single file named rejects.csv that includes the following fields:
         * email, reason, detail, created_at, expires_at, last_event_at, expires_at.
         *
         * @param notify_email email address to notify
         * @return fluentStruct
         */
        Struct rejects(@Param("notify_email") String notify_email);

        /**
         * Begins an export of your rejection whitelist. The whitelist will be exported to a
         * zip archive containing a single file named whitelist.csv that includes the following
         * fields: email, detail, created_at.
         *
         * @param notify_email
         * @return fluentStruct
         */
        Struct whitelist(@Param("notify_email") String notify_email);

        /**
         * Begins an export of your activity history. The activity will be exported to a zip archive containing
         * a single file named activity.csv in the same format as you would be able to export from your account's
         * activity view. It includes the following fields:
         * Date, Email Address, Sender, Subject, Status, Tags, Opens, Clicks, Bounce Detail.
         * <p/>
         * If you have configured any custom metadata fields, they will be included in the exported data.
         *
         * @param notify_email
         * @param date_from
         * @param date_to
         * @param tags
         * @param senders
         * @param states
         * @return
         */
        Struct activity(@Param("notify_email") String notify_email,
                        @Param("date_from") String date_from,
                        @Param("date_to") String date_to,
                        @Param("tags") StructArray tags,
                        @Param("senders") StructArray senders,
                        @Param("states") StructArray states);
    }

    public static interface Inbound {

        /**
         * List the domains that have been configured for inbound delivery
         *
         * @return array
         */
        StructArray domains();

        /**
         * List the mailbox routes defined for an inbound domain
         *
         * @param domain an inbound domain
         * @return array
         */
        StructArray routes(@Param("domain") String domain);

        /**
         * Take a raw MIME document destined for a domain with inbound domains set up, and send it
         * to the inbound hook exactly as if it had been sent over SMTP
         *
         * @param raw_message    raw MIME message
         * @param to             recipient of the message
         * @param mail_from      senders email address
         * @param helo
         * @param client_address
         * @return array
         */
        StructArray sendRaw(@Param("raw_message") String raw_message,
                            @Param("to") String to,
                            @Param("mail_from") String mail_from,
                            @Param("helo") String helo,
                            @Param("client_address") String client_address);
    }

    /**
     * This annotation gives arguments of the interfaces their serializable names.
     * This allows us to write normal method signatures, but serialize them with
     * custom structures, maps and lists.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    static @interface Param {

        public String value();
    }
}
