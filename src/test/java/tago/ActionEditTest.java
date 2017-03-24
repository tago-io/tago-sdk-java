package tago;

import domain.StringResult;
import model.account.Account;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActionEditTest {
    
    public ActionEditTest() {
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
        System.out.println("Action edit");
        Account account = new Account();
        account.action.name = "Created by JAVA SDK";
        account.action.create();
        account.action.name = "Edited by JAVA SDK";
        StringResult res = account.action.edit();
        account.action.delete();
        
        assertEquals(res.status, true);        
    }
}
