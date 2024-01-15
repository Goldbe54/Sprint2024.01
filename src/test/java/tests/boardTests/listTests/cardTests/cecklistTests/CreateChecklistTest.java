package tests.boardTests.listTests.cardTests.cecklistTests;

import api.clients.ApiBoardClient;
import api.clients.ApiCardClient;
import api.clients.ApiChecklistClient;
import api.clients.ApiListClient;
import api.pojo.requests.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.TestInit;

public class CreateChecklistTest extends TestInit {
    private final ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);
    private final ApiChecklistClient apiChecklistClient = new ApiChecklistClient(BASE_URL);
    private final BoardBuilder boardBody = BoardBuilder.builder().build();
    private final ListBuilder listBody = ListBuilder.builder().build();
    private final CardBuilder cardBody = CardBuilder.builder().build();
    private final CheckitemBuilder checkitemBody = CheckitemBuilder.builder().build();
    private final ChecklistBuilder checklistBody = ChecklistBuilder.builder().build();
    private String boardId;
    private String listId;
    private String cardId;
    private String checklistId;
    private String checkitemId;

    @Test
    private void setUp() {
        boardId = apiBoardClient.createNewBoard(boardBody,200).getId();
        listId = apiListClient.createNewList(listBody,boardId,200).getId();
        cardId = apiCardClient.createNewCard(cardBody,listId,200).getId();
        checklistId = apiChecklistClient.createNewChecklist(checklistBody,cardId,200).getId();
        checklistId = apiChecklistClient.createNewCheckitem(checkitemBody,checklistId,200).getId();
    }
}
