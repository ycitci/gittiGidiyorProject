package uiTest.test;

import org.junit.Test;

public class TrelloApiTest extends apiTest.TrelloApiTest {

    @Test
    public void apiRun(){
        createBoard().createList().createCard().createCard().updateCard().deleteCard().deleteCard().deleteBoard();
    }


}
