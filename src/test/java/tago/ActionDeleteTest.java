package tago;

import domain.StringResult;
import model.account.Account;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActionDeleteTest {
    
    public ActionDeleteTest() {
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
        System.out.println("Action delete");
        Account account = new Account();
        account.action.name = "Created by JAVA SDK";
        account.action.create();
        StringResult res = account.action.delete();
        
        assertEquals(res.status, true);        
    }
}
