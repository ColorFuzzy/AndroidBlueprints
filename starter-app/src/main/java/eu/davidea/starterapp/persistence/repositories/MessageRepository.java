package eu.davidea.starterapp.persistence.repositories;

import java.util.List;

import javax.inject.Inject;

import eu.davidea.starterapp.persistence.api.MessageApi;
import eu.davidea.starterapp.persistence.db.MessageDao;
import eu.davidea.starterapp.persistence.db.StarterDatabase;
import eu.davidea.starterapp.viewmodels.message.Message;
import io.reactivex.Flowable;
import timber.log.Timber;

/**
 * @author Davide
 * @since 17/09/2017
 */
public class MessageRepository {

    private MessageApi api;
    private MessageDao messageDao;

    @Inject
    public MessageRepository(StarterDatabase database, MessageApi api) {
        this.messageDao = database.messageDao();
        this.api = api;
    }

    public Flowable<List<Message>> loadConversation(Long threadId, Long messageId) {
        // TODO Caching: https://medium.com/@Miqubel/caching-with-realm-and-rxjava-80f48c5f5e37
        return api.getConversation(threadId, messageId);
    }

    private void saveMessages(List<Message> messages) {
        Timber.d("Saving %d messages to DB", messages.size());
        messageDao.saveMessages(messages);
    }

}