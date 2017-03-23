package tago.analysis;

import domain.AnalysisCreateResult;
import java.util.ArrayList;
import model.account.Account;
import model.analysis.KeyVal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CreateTest {
    
    public CreateTest() {
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
        System.out.println("Analysis create");
        Account account = new Account();
        account.analysis.name = "JAVA SDK test Analysis";
        account.analysis.description = "Created by JAVA SDK test Analysis";
        account.analysis.interval = "1 day";
        account.analysis.active = true;
        
        account.analysis.tags = new ArrayList<>();
        KeyVal kv = new KeyVal();
        kv.key = "var1";
        kv.value = "var1Value";
        account.analysis.tags.add(kv);
        kv = new KeyVal();
        kv.key = "var2";
        kv.value = "var2Value";
        account.analysis.tags.add(kv);
        account.analysis.variables = new ArrayList<>();
        kv = new KeyVal();
        kv.key = "env1";
        kv.value = "env1Value";
        account.analysis.variables.add(kv);
        kv = new KeyVal();
        kv.key = "env2";
        kv.value = "env2Value";
        account.analysis.variables.add(kv);
        
        AnalysisCreateResult acr = account.analysis.create();
        account.analysis.delete();

        assertEquals(acr.status, true);
    }
}
