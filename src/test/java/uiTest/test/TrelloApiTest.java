package uiTest.test;

import org.junit.Test;

public class TrelloApiTest extends apiTest.TrelloApiTest {

    @Test
    public void aPiCreateBoard (){
        createBoard();

    }
    @Test
    public void apiCreateList (){
        createList();
    }
    @Test
    public void apiCreateCard (){
        createCard();
        createCard();
    }
    @Test
    public void apiUpdateCard (){
        updateCard();
    }
    @Test
    public void apiDeleteCard (){
        deleteCard();
        deleteCard();
    }
    @Test
    public void apiDeleteBoard(){
        deleteBoard();
    }



}
