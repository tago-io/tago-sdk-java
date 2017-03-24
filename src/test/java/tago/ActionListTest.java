package tago;

import domain.ActionCreateResult;
import domain.ActionListResult;
import model.account.Account;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActionListTest {
    
    public ActionListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void test() {
        System.out.println("Action list");
        Account account = new Account();
        
        ActionListResult res = account.action.list();

        assertEquals(res.status, true);        
    }
}
