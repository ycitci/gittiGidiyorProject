package uiTest.test;

import org.junit.Test;
import uiTest.pages.GittiGidiyorHomePage;


public class GittiGidiyorTest extends GittiGidiyorHomePage {
    @Test
    public void Run (){

        goToHomePage().
                productSearch().
                selectWindow().
                productSelect().goToMyBasket();
    }

}
